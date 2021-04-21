package cn.ibizlab.pms.util.helper;

import cn.ibizlab.pms.util.domain.DELogic;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.action.IPSDEActionLogic;
import net.ibizsys.model.dataentity.action.PSDELogicActionImpl;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.logic.IPSDELogicNode;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ExtensionAttribute;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.Process;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 实体逻辑执行器
 */
@Slf4j
@Component
public class DELogicExecutor {

    private static BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
    private final ExpressionParser parser = new SpelExpressionParser();
    private ConcurrentMap<String, DELogic> deLogicMap = new ConcurrentHashMap<>();

    /**
     * 执行实体处理逻辑
     *
     * @param entity
     * @param action
     * @param logic
     * @param runtime
     */
    public void executeLogic(EntityBase entity, IPSDEAction action, IPSDELogic logic, IDynaInstRuntime runtime) throws Exception {
        Resource bpmn = getBpmn(logic, entity, runtime);
        if (bpmn != null && bpmn.exists() && isNeedLoad(bpmn,runtime)) {
            DELogic deLogic = getDELogic(logic, entity, runtime);
            if (deLogic != null) {
                executeLogic(deLogic, entity , action);
            }
        }
    }

    /**
     * 执行实体附加逻辑
     *
     * @param entity
     * @param action
     * @param attachMode
     * @param runtime
     */
    public void executeAttachLogic(EntityBase entity, IPSDEAction action, String attachMode, IDynaInstRuntime runtime) throws Exception {
        Resource bpmn = getBpmn(action, attachMode, entity, runtime);
        if (bpmn != null && bpmn.exists() && isNeedLoad(bpmn,runtime)) {
            DELogic deLogic = getDELogic(action, attachMode, entity, runtime);
            if (deLogic != null) {
                executeLogic(deLogic, entity , action);
            }
        }
    }


    /**
     * 是否需要远程加载逻辑
     * @param bpmn
     * @param runtime
     * @return
     */
    private boolean isNeedLoad(Resource bpmn, IDynaInstRuntime runtime) throws IOException {
        String bpmnId = DigestUtils.md5DigestAsHex(bpmn.getURL().toString().getBytes());
        if (!StringUtils.isEmpty(bpmnId) && !ObjectUtils.isEmpty(runtime.getLoadedTime())) {
            DELogic logic = deLogicMap.get(bpmnId);
            if (logic != null && !ObjectUtils.isEmpty(logic.getLoadedTime())) {
                return !(logic.getLoadedTime() == runtime.getLoadedTime());
            }
        }
        return true;
    }

    /**
     * 编译并执行规则（bpmn、drl）
     *
     * @param logic
     * @param entity
     */
    private void executeLogic(DELogic logic, EntityBase entity , IPSDEAction action) {
            String logicMode = logic.getLogicMode() == 1 ? "远程模式" : "本地模式";
            String actionName = action.getCodeName();
        try {
            log.debug("开始执行实体处理逻辑[{}:{}:{}]", getEntityName(entity), actionName, logicMode);
            String bpmnId = logic.getId();
            if (deLogicMap.containsKey(bpmnId) && logic.getMd5().equals(deLogicMap.get(bpmnId).getMd5())) {
                logic = deLogicMap.get(bpmnId);
            } else {
                reloadLogic(logic);
                deLogicMap.put(bpmnId, logic);
            }
            KieContainer container = logic.getContainer();
            KieSession kieSession = container.getKieBase().newKieSession();
            Process mainProcess = logic.getProcess();
            //主流程参数
            fillGlobalParam(kieSession, mainProcess, entity);
            //子流程参数
            if (!ObjectUtils.isEmpty(logic.getRefLogic())) {
                for (DELogic subLogic : logic.getRefLogic()) {
                    fillGlobalParam(kieSession, subLogic.getProcess(), entity);
                }
            }
            kieSession.startProcess(mainProcess.getId());
            log.debug("实体处理逻辑[{}:{}:{}]执行结束", getEntityName(entity), actionName, logicMode);
        } catch (Exception e) {
            log.error("执行实体处理逻辑[{}:{}:{}]发生异常" + e.getMessage(), getEntityName(entity), actionName, logicMode);
            throw new BadRequestAlertException("执行实体处理逻辑发生异常" + e.getMessage(), "DELogicExecutor", "executeLogic");
        }
    }


    /**
     * 获取实体逻辑
     * @param deLogic
     * @param entity
     * @param iDynaInstRuntime
     * @return
     */
    private DELogic getDELogic(IPSDELogic deLogic, EntityBase entity, IDynaInstRuntime iDynaInstRuntime) {
        DELogic logic = null;
        XMLStreamReader reader = null;
        InputStream bpmn = null;
        try {
            Resource bpmnFile = getBpmn(deLogic, entity, iDynaInstRuntime);
            if (bpmnFile !=null && bpmnFile.exists()) {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                bpmn = bpmnFile.getInputStream();
                reader = factory.createXMLStreamReader(bpmn);
                BpmnModel model = bpmnXMLConverter.convertToBpmnModel(reader);
                Process mainProcess = model.getMainProcess();
                if (mainProcess == null) {
                    return null;
                }
                log.debug("正在加载 BPMN:{}", bpmnFile.getURL().toString());
                List<DELogic> refLogics = new ArrayList<>();
                List<Resource> refFiles = new ArrayList<>();
                //加载主bpmn、drl
                refFiles.add(bpmnFile);
                Resource drlFile = getDrl(bpmnFile);
                if (drlFile.exists()) {
                    refFiles.add(drlFile);
                    log.debug("正在加载 DRL:{}", drlFile.getURL().toString());
                }
                for (IPSDELogicNode logicNode : deLogic.getPSDELogicNodes()) {
                    if ("deaction".equalsIgnoreCase(logicNode.getLogicNodeType()) && logicNode.getDstPSDEAction() instanceof PSDELogicActionImpl) {
                        IPSDELogic tempDELogic = ((PSDELogicActionImpl) logicNode.getDstPSDEAction()).getPSDELogic();
                        DELogic refLogic = getDELogic(tempDELogic, entity, iDynaInstRuntime);
                        if (refLogic != null) {
                            refLogics.add(refLogic);
                            if (!ObjectUtils.isEmpty(refLogic.getRefRuleFiles())) {
                                refFiles.addAll(refLogic.getRefRuleFiles());
                            }
                        }
                    }
                }
                logic = new DELogic();
                logic.setId(DigestUtils.md5DigestAsHex(bpmnFile.getURL().toString().getBytes()));
                logic.setName(mainProcess.getName());
                logic.setProcess(mainProcess);
                logic.setRefLogic(refLogics);
                logic.setRefRuleFiles(refFiles);
                logic.setMd5(getMd5(refFiles));
                logic.setLogicMode(bpmnFile instanceof UrlResource ? 1 : 0);
                logic.setLoadedTime(iDynaInstRuntime.getLoadedTime());
            }
        } catch (Exception e) {
            log.error("执行处理逻辑失败" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (bpmn != null) {
                    bpmn.close();
                }
            } catch (Exception e) {
                log.error("执行处理逻辑失败" + e);
            }
        }
        return logic;
    }

    /**
     * 获取实体附加逻辑
     * @param action
     * @param attachMode
     * @param entity
     * @param runtime
     * @return
     */
    private DELogic getDELogic(IPSDEAction action, String attachMode, EntityBase entity, IDynaInstRuntime runtime) {
        DELogic logic = null;
        XMLStreamReader reader = null;
        InputStream bpmn = null;
        try {
            Resource bpmnFile = getBpmn(action, attachMode, entity, runtime);
            if (bpmnFile !=null && bpmnFile.exists()) {
                List<IPSDEActionLogic> actionLogics;
                if ("before".equalsIgnoreCase(attachMode))
                    actionLogics = action.getBeforePSDEActionLogics();
                else if ("after".equalsIgnoreCase(attachMode))
                    actionLogics = action.getAfterPSDEActionLogics();
                else
                    return logic;

                XMLInputFactory factory = XMLInputFactory.newInstance();
                bpmn = bpmnFile.getInputStream();
                reader = factory.createXMLStreamReader(bpmn);
                BpmnModel model = bpmnXMLConverter.convertToBpmnModel(reader);
                Process mainProcess = model.getMainProcess();
                if (mainProcess == null) {
                    return null;
                }
                log.debug("正在加载 BPMN:{}", bpmnFile.getURL().toString());
                List<DELogic> refLogics = new ArrayList<>();
                List<Resource> refFiles = new ArrayList<>();
                //加载主bpmn、drl
                refFiles.add(bpmnFile);
                Resource drlFile = getDrl(bpmnFile);
                if (drlFile.exists()) {
                    refFiles.add(drlFile);
                    log.debug("正在加载 DRL:{}", drlFile.getURL().toString());
                }
                //附加逻辑bpmn、drl
                for (IPSDEActionLogic actionLogic : actionLogics) {
                    IPSDELogic tempLogic = null;
                    IPSDEAction deaction = actionLogic.getDstPSDEAction();
                    //内部逻辑
                    if(actionLogic.getPSDELogic() != null){
                        tempLogic = actionLogic.getPSDELogic();
                    }
                    //附加行为
                    else if (deaction != null && deaction instanceof  PSDELogicActionImpl){
                        tempLogic = ((PSDELogicActionImpl) deaction).getPSDELogic();
                    }
                    if (tempLogic!=null) {
                        DELogic refLogic = getDELogic(tempLogic, entity, runtime);
                        if (refLogic != null) {
                            refLogics.add(refLogic);
                            if (!ObjectUtils.isEmpty(refLogic.getRefRuleFiles())) {
                                refFiles.addAll(refLogic.getRefRuleFiles());
                            }
                        }
                    }
                }
                logic = new DELogic();
                logic.setId(DigestUtils.md5DigestAsHex(bpmnFile.getURL().toString().getBytes()));
                logic.setName(mainProcess.getName());
                logic.setProcess(mainProcess);
                logic.setRefLogic(refLogics);
                logic.setRefRuleFiles(refFiles);
                logic.setMd5(getMd5(refFiles));
                logic.setLogicMode(bpmnFile instanceof UrlResource ? 1 : 0);
            }
        } catch (Exception e) {
            log.error("执行处理逻辑失败" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (bpmn != null) {
                    bpmn.close();
                }
            } catch (Exception e) {
                log.error("执行处理逻辑失败" + e);
            }
        }
        return logic;
    }

    /**
     * 获取实体附加逻辑bpmn
     * @param action
     * @param strAttachMode
     * @param entity
     * @param iDynaInstRuntime
     * @return
     * @throws MalformedURLException
     */
    private Resource getBpmn(IPSDEAction action, String strAttachMode, EntityBase entity, IDynaInstRuntime iDynaInstRuntime) throws MalformedURLException {
        Resource bpmnFile;
        if (iDynaInstRuntime != null && !StringUtils.isEmpty(iDynaInstRuntime.getDynaInstFolderPath())) {
            String bpmnFilePath = String.format("%s/%s.%s.bpmn", iDynaInstRuntime.getDynaInstFolderPath(), action.getDynaModelFilePath(), strAttachMode.toLowerCase()).replace("\\", "/");
            bpmnFile = new UrlResource(bpmnFilePath);
            if (bpmnFile != null && bpmnFile.exists()) {
                return bpmnFile;
            }
        }
        bpmnFile = new ClassPathResource("rules" + File.separator + entity.getClass().getSimpleName() + File.separator + action.getCodeName().toLowerCase() + File.separator + strAttachMode.toLowerCase() + ".bpmn");
        if (bpmnFile != null && bpmnFile.exists()) {
            return bpmnFile;
        }
        return null;
    }

    /**
     * 获取实体处理逻辑bpmn
     * @param logic
     * @param entity
     * @param iDynaInstRuntime
     * @return
     * @throws MalformedURLException
     */
    private Resource getBpmn(IPSDELogic logic, EntityBase entity, IDynaInstRuntime iDynaInstRuntime) throws MalformedURLException {
        Resource bpmnFile;
        if (iDynaInstRuntime != null && !StringUtils.isEmpty(iDynaInstRuntime.getDynaInstFolderPath())) {
            String bpmnFilePath = String.format("%s/%s.bpmn", iDynaInstRuntime.getDynaInstFolderPath(), logic.getDynaModelFilePath()).replace("\\", "/");
            bpmnFile = new UrlResource(bpmnFilePath);
            if (bpmnFile != null && bpmnFile.exists()) {
                return bpmnFile;
            }
        }
        bpmnFile = new ClassPathResource("rules" + File.separator + String.format("%s%sRuleFlow.bpmn", getEntityName(entity), logic.getCodeName()));
        if (bpmnFile != null && bpmnFile.exists()) {
            return bpmnFile;
        }
        return null;
    }

    /**
     * 编译规则文件 bpmn、drl
     * @param logic
     * @throws IOException
     */
    private void reloadLogic(DELogic logic) throws IOException {
        KieServices kieServices = KieServices.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (Resource bpmn : logic.getRefRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newUrlResource(bpmn.getURL()));
        }
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new BadRequestAlertException(String.format("编译实体处理逻辑 [%s] 发生异常, %s", logic.getName(), results.getMessages()), "DELogicExecutor", "reloadLogic");
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        logic.setContainer(kieContainer);
    }

    /**
     * 填充逻辑参数
     *
     * @param kieSession
     * @param process
     * @param entity
     */
    private void fillGlobalParam(KieSession kieSession, Process process, Object entity) {
        Map<String, List<ExtensionElement>> params = process.getExtensionElements();
        for (Map.Entry<String, List<ExtensionElement>> param : params.entrySet()) {
            if ("metaData".equalsIgnoreCase(param.getKey())) {
                List<ExtensionElement> globalParams = param.getValue();
                for (ExtensionElement globalParam : globalParams) {
                    Object value = null;
                    Map<String, List<ExtensionAttribute>> globalParamAttr = globalParam.getAttributes();
                    if (globalParamAttr.containsKey("name") && globalParamAttr.containsKey("type") && globalParamAttr.containsKey("express")) {
                        ExtensionAttribute name = globalParamAttr.get("name").get(0);
                        ExtensionAttribute type = globalParamAttr.get("type").get(0);
                        ExtensionAttribute express = globalParamAttr.get("express").get(0);
                        String express_value = express.getValue();
                        EvaluationContext oldContext = new StandardEvaluationContext();
                        if ("entity".equalsIgnoreCase(type.getValue())) {
                            value = entity;
                        }
                        if (!ObjectUtils.isEmpty(type.getValue()) && ObjectUtils.isEmpty(value)) {
                            Expression oldExp = parser.parseExpression(express_value);
                            value = oldExp.getValue(oldContext);
                        }
                        if ("entity".equalsIgnoreCase(type.getValue()) || "refentity".equalsIgnoreCase(type.getValue())) {
                            kieSession.insert(value);
                        }
                        kieSession.setGlobal(name.getValue(), value);
                    }
                }
            }
        }
    }

    /**
     * 获取bpmn md5
     *
     * @param subFiles
     * @return
     */
    private String getMd5(List<Resource> subFiles) {
        try {
            StringBuffer buffer = new StringBuffer();
            for (Resource file : subFiles) {
                InputStream bpmnFile = null;
                try {
                    bpmnFile = file.getInputStream();
                    if (!ObjectUtils.isEmpty(bpmnFile)) {
                        String strBpmn = IOUtils.toString(bpmnFile, "UTF-8");
                        buffer.append(strBpmn);
                    }
                } catch (Exception e) {
                    log.error("处理逻辑版本检查失败" + e);
                } finally {
                    if (bpmnFile != null) {
                        bpmnFile.close();
                    }
                }
            }
            if (!StringUtils.isEmpty(buffer.toString())) {
                return DigestUtils.md5DigestAsHex(buffer.toString().getBytes());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("处理逻辑版本检查失败" + e);
            return null;
        }
    }

    /**
     * 处理逻辑 drl
     *
     * @param bpmn
     * @return
     */
    private Resource getDrl(Resource bpmn) throws IOException {
        String filePath = bpmn instanceof UrlResource ? bpmn.getURL().toString() : ((ClassPathResource) bpmn).getPath();
        filePath = filePath.endsWith("RuleFlow.bpmn") ? filePath.replace("RuleFlow.bpmn", "Rule.drl") : filePath.replace(".bpmn", ".drl");
        return bpmn instanceof UrlResource ? new UrlResource(filePath) : new ClassPathResource(filePath);
    }

    /**
     * 判断类是否被代理类代理
     */
    public String getEntityName(Object entity) {
        String entityName = entity.getClass().getSimpleName();
        if (entityName.contains("$$")) {
            entityName = ClassUtils.getUserClass(entity.getClass()).getSimpleName();
        }
        return entityName;
    }
}


