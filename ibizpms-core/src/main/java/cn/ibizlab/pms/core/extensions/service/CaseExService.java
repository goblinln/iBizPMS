package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.util.ibizzentao.helper.PHPSerializerHelper;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import liquibase.pro.packaged.O;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[测试用例] 自定义服务对象
 */
@Slf4j
@Primary
@Service("CaseExService")
public class CaseExService extends CaseServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ICaseStepService iCaseStepService;

    @Autowired
    ITestTaskService iTestTaskService;

    @Autowired
    ITestSuiteService iTestSuiteService;

    @Autowired
    ITestRunService iTestRunService;

    @Autowired
    ISuiteCaseService iSuiteCaseService;

    @Autowired
    ITestResultService iTestResultService;

    @Override
    public boolean create(Case et) {
        List<CaseStep> caseSteps = et.getCasesteps();
        et.reset("casesteps");
        if(et.getFromcaseid()!=0){
            Case cas = this.get(et.getFromcaseid());
            et.setFromcaseversion(cas.getVersion());
        }
        if(et.getStory() != null && et.getStory() != 0) {
            et.setStoryversion(iStoryService.get(et.getStory()).getVersion());
        }
        String files = et.getFiles();
        if (!super.create(et)) {
            return false;
        }
        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.CASE.getValue(), files,"", iFileService);
        createCaseStep(et,caseSteps);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.CASE.getValue(), null,  StaticDict.Action__type.OPENED.getValue(), "","", null, iActionService);
        return true;
    }

    public void createCaseStep(Case et, List<CaseStep> caseSteps) {
        if (caseSteps != null) {
            Long parent = 0L;
            for (CaseStep caseStep : caseSteps) {
                if(StringUtils.isBlank(caseStep.getDesc())) {
                    continue;
                }
                caseStep.setType(caseStep.getType() != null && StaticDict.Casestep__type.ITEM.getValue().equals(caseStep.getType()) && parent == 0 ? StaticDict.Casestep__type.STEP.getValue() : caseStep.getType());
                String type = caseStep.getType();
                if(StaticDict.Casestep__type.ITEM.getValue().equals(type)) {
                    caseStep.setParent(parent);
                }
                caseStep.setIbizcase(et.getId());
                caseStep.setVersion(et.getVersion());
                iCaseStepService.create(caseStep);
                if(StaticDict.Casestep__type.GROUP.getValue().equals(type)) {parent = caseStep.getId();}
                if(StaticDict.Casestep__type.STEP.getValue().equals(type)) {parent = 0L;}
            }
        }
    }

    @Override
    public boolean update(Case et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        List<CaseStep> caseSteps = et.getCasesteps() ;
        Case old = new Case();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        et.setStoryversion(old.getStoryversion());

        List<CaseStep> list = iCaseStepService.list(new QueryWrapper<CaseStep>().eq("`case`", et.getId()).eq("version", old.getVersion()));
        boolean caseStepFlag = false;
        et.reset("casesteps");
        if(list.size() == caseSteps.size()) {
            int i = 0;
            for(CaseStep caseStep : list) {
                CaseStep oldCaseStep = new CaseStep();
                CachedBeanCopier.copy(caseStep, oldCaseStep);
                oldCaseStep.setParent(0L);
                CaseStep newCaseStep = new CaseStep();
                CachedBeanCopier.copy(caseSteps.get(i), newCaseStep);
                newCaseStep.setParent(0L);
                if(newCaseStep.getId() == null || newCaseStep.getId() ==0) {
                    caseStepFlag = true;
                    break;
                }

                List<History> changes = ChangeUtil.diff(oldCaseStep, newCaseStep);
                if(changes.size() > 0){
                    caseStepFlag = true;
                    break;
                }

                i ++;
            }
        }else {
            caseStepFlag = true;
        }
        if (caseStepFlag) {
            et.setVersion(old.getVersion() + 1);
        }
        et.setLastrunresult(old.getLastrunresult());
        et.setLastrundate(old.getLastrundate());
        et.setLastrunner(old.getLastrunner());
        String files = et.getFiles();
        if (!super.update(et)) {
            return false;
        }

        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.CASE.getValue(), files,"", iFileService);
        if (caseStepFlag) {
            createCaseStep(et,caseSteps);
        }

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.CASE.getValue(), changes,  StaticDict.Action__type.EDITED.getValue(), comment,"", null, iActionService);
        }
        return true;
    }

    @Override
    public boolean remove(Long key) {
        return super.remove(key);
    }

    /**
     * [ConfirmChange:确认用例变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case confirmChange(Case et) {
        Case cas = this.getById(et.getId());
        TestRun testRun = new TestRun();
        testRun.setVersion(cas.getVersion());
        Map<String,Object> param = new HashMap<>();
        param.put("`case`",cas.getId());
        param.put(StaticDict.Action__object_type.TASK.getValue(), et.getTask());
        iTestRunService.update(testRun,(Wrapper) testRun.getUpdateWrapper(true).allEq(param));
        return et;
    }
    /**
     * [Confirmstorychange:确认需求变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case confirmstorychange(Case et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Case old = new Case();
        CachedBeanCopier.copy(get(et.getId()), old);
        if (old.getStory() != 0) {
            Story story = iStoryService.getById(old.getStory());
            old.setStoryversion(story.getVersion());
        }
        this.sysUpdate(old);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.CASE.getValue(), null,  StaticDict.Action__type.CONFIRMED.getValue(), comment,"", null, iActionService);

        return et;
    }
    /**
     * [GetByTestTask:根据测试单获取或者状态] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case getByTestTask(Case et) {
        return super.getByTestTask(et);
    }
    /**
     * [GetTestTaskCntRun:获取测试单执行结果] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case getTestTaskCntRun(Case et) {
        return super.getTestTaskCntRun(et);
    }
    /**
     * [LinkCase:测试单关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case linkCase(Case et) {
        if (et.getTask() == null) {
            return et;
        }
        if (et.get("ids") == null) {
            return et;
        }
        TestTask testTask = new TestTask();
        testTask.setId(Long.parseLong(et.getTask().split(",")[0]));

        testTask.set("cases",et.get("ids"));
        testTask.set("versions", et.get("versions"));
        iTestTaskService.linkCase(testTask);
        return et;
    }
    /**
     * [MobLinkCase:移动端关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case mobLinkCase(Case et) {
        if (et.getTask() == null) {
            return et;
        }
        if (et.get("ids") == null) {
            return et;
        }
        TestTask testTask = new TestTask();
        testTask.setId(Long.parseLong(et.getTask().split(",")[0]));

        String ids = "";
        String version = "";
        ArrayList<Map> list = (ArrayList) et.get("srfactionparam");
        for (Map data: list) {
            if (!"".equals(ids)){
                ids += ",";
            }
            if (!"".equals(version)){
                version += ",";
            }
            ids += data.get("id");
            version += data.get("version");
        }

        testTask.set("cases",ids);
        testTask.set("versions", version);
        iTestTaskService.linkCase(testTask);
        return et;
    }
    /**
     * [RunCase:执行测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case runCase(Case et) {
        createResult(et);
        return et;
    }

    public void createResult(Case et) {
        Long caseId = Long.parseLong(et.get(StaticDict.Action__object_type.CASE.getValue()).toString());
        if(caseId == null || caseId == 0L) {
            return;
        }
        Long runId = et.getId();

        Map<Integer, Object> stepResults = new HashMap<>();
        List<CaseStep> caseSteps = et.getCasesteps();
        et = this.get(caseId);
        String caseResult = StaticDict.Testcase__result.PASS.getValue();

        if(caseSteps != null) {
            for(CaseStep caseStep : caseSteps) {
                if(caseStep.getSteps() == null || "".equals(caseStep.getSteps())) {
                    if(caseStep.getReals() == null || "".equals(caseStep.getReals())) {
                        caseStep.setSteps(StaticDict.Testcase__result.PASS.getValue());
                    }else {
                        caseStep.setSteps(StaticDict.Testcase__result.FAIL.getValue());
                    }
                }
                if(!StaticDict.Testcase__result.N_A.getValue().equals(caseStep.getSteps()) && !StaticDict.Testcase__result.PASS.getValue().equals(caseStep.getSteps())) {
                    caseResult = caseStep.getSteps();
                    break;
                }
            }
            for(CaseStep caseStep : caseSteps) {
                if(caseStep.getSteps() == null || "".equals(caseStep.getSteps())) {
                    if(caseStep.getReals() == null || "".equals(caseStep.getReals())) {
                        caseStep.setSteps(StaticDict.Testcase__result.PASS.getValue());
                    }else {
                        caseStep.setSteps(StaticDict.Testcase__result.FAIL.getValue());
                    }
                }
                Map<String, String> jsonObject = new HashMap<>();
                jsonObject.put("real",caseStep.getReals() != null ? caseStep.getReals() : "");
                jsonObject.put("result",caseStep.getSteps());
                stepResults.put(Integer.parseInt(String.valueOf(caseStep.getId())), jsonObject);
            }
        }
        PHPSerializerHelper phpSerializer = new PHPSerializerHelper();
        String ss = "";
        try {
            ss = new String(phpSerializer.serialize(stepResults), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        TestResult testResult = new TestResult();
        testResult.setRun(runId);
        testResult.setCaseresult(caseResult);
        testResult.setStepresults(ss);
        testResult.setIbizcase(caseId);
        testResult.setVersion(et.getVersion());
        testResult.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
        testResult.setDate(ZTDateUtil.now());
        iTestResultService.create(testResult);
        Case cases = new Case();
        cases.setId(caseId);
        cases.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
        cases.setLastrundate(ZTDateUtil.now());
        cases.setLastrunresult(caseResult);
        this.sysUpdate(cases);
        if(runId != null && runId != 0) {
            TestRun testRun = new TestRun();
            testRun.setId(runId);
            testRun.setStatus(StaticDict.Testrun__status.BLOCKED.getValue().equals(caseResult) ? StaticDict.Testrun__status.BLOCKED.getValue() : StaticDict.Testrun__status.DONE.getValue());
            testRun.setLastrundate(ZTDateUtil.now());
            testRun.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
            testRun.setLastrunresult(caseResult);
            iTestRunService.sysUpdate(testRun);
        }
    }
    /**
     * [TestRunCase:执行测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case testRunCase(Case et) {
        createResult(et);
        return et;
    }
    /**
     * [TestsuitelinkCase:套件关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case testsuitelinkCase(Case et) {
        if (et.get("suite") == null) {
            return et;
        }
        if (et.get("ids") == null) {
            return et;
        }
        TestSuite testSuite = new TestSuite();
        testSuite.setId(Long.parseLong(et.get("suite").toString().split(",")[0]));
        testSuite.set("cases",et.get("ids"));
        testSuite.set("versions", et.get("versions"));
        iTestSuiteService.linkCase(testSuite);
        return et;
    }
    /**
     * [UnlinkCase:移除用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case unlinkCase(Case et) {
        iTestRunService.remove(et.getId());
        return et;
    }
    /**
     * [UnlinkSuiteCase:移除用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case unlinkSuiteCase(Case et) {
        iSuiteCaseService.remove(new QueryWrapper<SuiteCase>().eq("suite",et.get("suite")).eq("`case`",et.get(StaticDict.Action__object_type.CASE.getValue()))) ;
        return et;
    }

    @Override
    public Case sysGet(Long key) {
        Case aCase = super.sysGet(key);
        String sql = "SELECT COUNT(1) AS ISFAVORITES FROM `t_ibz_favorites` t WHERE t.IBZ_FAVORITESID = #{et.id} AND t.TYPE = 'case' AND t.ACCOUNT = #{et.account}";
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",aCase.getId());
        param.put("account",AuthenticationUser.getAuthenticationUser().getLoginname());
        List<JSONObject> result = this.select(sql, param);
        if (result.size()>0){
            aCase.setIsfavorites(result.get(0).getString("ISFAVORITES"));
        }

        return aCase;
    }
}

