package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.ProjectProductSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[系统日志] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ActionExService")
public class ActionExService extends ActionServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    String[] processType = new String[]{StaticDict.Action__object_type.STORY.getValue(),
            StaticDict.Action__object_type.PRODUCTPLAN.getValue(),
            StaticDict.Action__object_type.RELEASE.getValue(),
            StaticDict.Action__object_type.TASK.getValue(),
            StaticDict.Action__object_type.BUILD.getValue(),
            StaticDict.Action__object_type.BUG.getValue(),
            StaticDict.Action__object_type.CASE.getValue(),
            StaticDict.Action__object_type.TESTTASK.getValue(),
            StaticDict.Action__object_type.DOC.getValue()};

    /**
     * 只关联产品
     */
    private static String PRODUCT_IN = StaticDict.Action__object_type.STORY.getValue() +
            "," + StaticDict.Action__object_type.CASE.getValue() +
            "," + StaticDict.Action__object_type.PRODUCTPLAN.getValue();

    /**
     * 产品和项目都有关联
     */
    private static String PRODUCT_PROJECT_IN = StaticDict.Action__object_type.BUILD.getValue() +
            "," + StaticDict.Action__object_type.BUG.getValue() +
            "," + StaticDict.Action__object_type.DOC.getValue() +
            "," + StaticDict.Action__object_type.TESTTASK.getValue();

    @Autowired
    IProjectProductService projectProductService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ICaseService iCaseService;

    @Autowired
    ITaskService iTaskService;

    @Autowired
    IBugService iBugService;

    @Autowired
    IIBIZProMessageService iibizProMessageService;

    @Autowired
    ISysEmployeeService iSysEmployeeService;

    @Override
    public boolean create(Action et) {
        et.setComment(et.getComment() == null ? "" : et.getComment());
        String noticeusers = et.getNoticeusers();
        String files = et.getFiles();
        this.createHis(et);
        // 保存文件
        // 更新file
        File file = new File();
        file.set("files",files);
        String extra = "0";
        if(files != null) {
            if (StaticDict.Action__object_type.STORY.getValue().equals(et.getObjecttype())) {
                Story story = iStoryService.get(et.getObjectid());
                if (story != null && story.getVersion() != null) {
                    extra = String.valueOf(story.getVersion());
                }
            } else if (StaticDict.Action__object_type.CASE.getValue().equals(et.getObjecttype())) {
                Case case1 = iCaseService.get(et.getObjectid());
                if (case1 != null && case1.getVersion() != null) {
                    extra = String.valueOf(case1.getVersion());
                }
            }
        }
        file.setExtra(extra);
        iFileService.updateObjectID(file);
        return true;
    }

    /**
     * [CreateHis:创建历史日志] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action createHis(Action et) {
        if (et.getActor() == null) {
            et.setActor(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        Long objectID = et.getObjectid();
        String objectType = et.getObjecttype().replace("`", "");
        et.setDate(ZTDateUtil.now());
        et.setRead(StaticDict.YesNo.ITEM_0.getValue());
        et.setProduct(",0,");
        et.setProject(0L);
        if (StringUtils.compare(objectType, StaticDict.Action__object_type.PRODUCT.getValue()) == 0) {
            et.setProduct("," + objectID + ",");
        } else if (StringUtils.compare(objectType, StaticDict.Action__object_type.PROJECT.getValue()) == 0) {
            ProjectProductSearchContext ctx = new ProjectProductSearchContext();
            ctx.setN_project_eq(objectID);
            List<ProjectProduct> projectProducts = projectProductService.searchDefault(ctx).getContent();
            String products = "";
            for (int i = 0; i < projectProducts.size(); i++) {
                if (i > 0) {
                    products += ",";
                }
                products += String.valueOf(projectProducts.get(i).getProduct());
            }
            et.setProduct("," + products + ",");
            et.setProject(objectID);
        } else if (Arrays.deepToString(processType).contains(objectType)) {
            JSONObject jsonObject = getProductAndProject(objectType, objectID);
            et.setProduct(jsonObject.getString(StaticDict.Action__object_type.PRODUCT.getValue()));
            et.setProject(jsonObject.getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            log.info(processType + "product、project设置未实现");
        }
        super.create(et);
        return et;
    }

    /**
     * @param objectType
     * @param objectId
     * @return
     */
    public JSONObject getProductAndProject(String objectType, Long objectId) {
        String fields = "";
        if (PRODUCT_IN.contains(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product";
        } else if (StaticDict.Action__object_type.TASK.getValue().equals(objectType)) {
            fields = "project, story";
        } else if (PRODUCT_PROJECT_IN.contains(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product, project";
        } else if (StaticDict.Action__object_type.RELEASE.getValue().equals(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product, build";
        }

        String sql = String.format("select %1$s from zt_%2$s where id = %3$s ", fields, objectType, objectId);

        List<JSONObject> jsonObjectList = projectProductService.select(sql, null);
        JSONObject record = jsonObjectList.get(0);

        if (objectType.equals(StaticDict.Action__object_type.STORY.getValue())) {
            String storySql = String.format("select project from zt_projectstory where story = %1$s ORDER BY project desc", objectId);
            List<JSONObject> list = projectProductService.select(storySql, null);
            if (list.size() > 0) {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), list.get(0).getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            } else {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), 0L);
            }
        } else if (objectType.equals(StaticDict.Action__object_type.RELEASE.getValue())) {
            String releaseSql = String.format("select project from zt_build where id = %1$s ", record.getLongValue(StaticDict.Action__object_type.BUILD.getValue()));
            List<JSONObject> list = projectProductService.select(releaseSql, null);
            if (list.size() > 0) {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), list.get(0).getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            } else {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), 0L);
            }
        } else if (objectType.equals(StaticDict.Action__object_type.TASK.getValue())) {
            if (record.getLongValue(StaticDict.Action__object_type.STORY.getValue()) != 0L) {
                String storySql = String.format("select CONCAT_WS('',',',product,',') as product from zt_story where id = %1$s ", record.getLongValue(StaticDict.Action__object_type.STORY.getValue()));
                List<JSONObject> list = projectProductService.select(storySql, null);
                if (list.size() > 0) {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), list.get(0).getString(StaticDict.Action__object_type.PRODUCT.getValue()));
                } else {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), ",0,");
                }
            } else {
                String projectProductSql = String.format("select CONCAT_WS('',',',ifnull(GROUP_CONCAT(product ORDER BY product asc),0),',') as product from zt_projectproduct where project = %1$s GROUP BY project ", record.getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
                List<JSONObject> list = projectProductService.select(projectProductSql, null);
                if (list.size() > 0) {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), list.get(0).getString(StaticDict.Action__object_type.PRODUCT.getValue()));
                } else {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), ",0,");
                }
            }
        }

        return record;
    }
    /**
     * [EditComment:编辑备注信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action editComment(Action et) {
        et.setDate(ZTDateUtil.now());
        this.update(et);
        return et;
    }
    /**
     * [ManagePmsEe:Pms企业专用] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action managePmsEe(Action et) {
        return super.managePmsEe(et);
    }

    @Override
    public Action sendTodo(Action et) {
        Long id = et.getObjectid();
        String name = "";
        if(et.get("name") != null) {
            name = et.get("name").toString();
        }
        String noticeusers = "";
        if(et.get("noticeusers") != null) {
            noticeusers = et.get("noticeusers").toString();
        }
        String touser = "";
        if(et.get("touser") != null) {
            touser = et.get("touser").toString();
        }
        String ccuser = "";
        if(et.get("ccuser") != null) {
            ccuser = et.get("ccuser").toString();
        }
        String logicname = "";
        if(et.get("logicname") != null) {
            logicname = et.get("logicname").toString();
        }
        String type = et.getObjecttype();
        String path = "";
        if(et.get("path") != null) {
            path = et.get("path").toString();
        }
        String actiontextname = "";
        if(et.get("actiontextname") != null) {
            actiontextname = et.get("actiontextname").toString();
        }
        String noticeuserss = "";
        if(touser != null && !"".equals(touser)) {
            noticeuserss += touser + ",";
        }
        JSONObject param = new JSONObject();
        if(noticeusers != null && !"".equals(noticeusers)) {
            noticeuserss += noticeusers;
        }
        if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() == 0) {
            noticeuserss += ccuser;
        }
        else if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() > 0) {
            noticeuserss += "," + ccuser;
        }
        if(noticeuserss.length() == 0) {
            return et;
        }

        IBIZProMessage ibizProMessage = new IBIZProMessage();
        if (touser == null || "".equals(touser)){
            return et;
        }
        ibizProMessage.setTo(touser);

        ibizProMessage.setCc(noticeuserss);

        ibizProMessage.setFrom(AuthenticationUser.getAuthenticationUser().getUsername());

        ibizProMessage.setType(StaticDict.Message__type.TODO.getValue());
        ibizProMessage.setIbizpromessagename(name);
        param.put("objectid", id);
        param.put("objecttype", type);
        param.put("objectsourcepath", path);
        param.put("objecttextname", logicname);
        param.put("actiontextname", actiontextname);
        ibizProMessage.setParam(param.toJSONString());
        iibizProMessageService.send(ibizProMessage);
        log.info("待办消息发送成功！");
        return et;
    }

    @Override
    public Action sendMarkDone(Action et) {
        Long id = et.getObjectid();
        String name = "";
        if(et.get("name") != null) {
            name = et.get("name").toString();
        }
        String toUser = "";
        if(et.get("touser") != null) {
            toUser = et.get("touser").toString();
        }
        String logicname = "";
        if(et.get("logicname") != null) {
            logicname = et.get("logicname").toString();
        }
        String type = et.getObjecttype();
        String path = "";
        if(et.get("path") != null) {
            path = et.get("path").toString();
        }
        String actiontextname = "";
        if(et.get("actiontextname") != null) {
            actiontextname = et.get("actiontextname").toString();
        }
        if (toUser == null || toUser.equals("")){
            return et;
        }

        SysEmployeeSearchContext toSearchContext = new SysEmployeeSearchContext();
        toSearchContext.setN_username_in(toUser);
        List<SysEmployee> toList =  iSysEmployeeService.searchDefault(toSearchContext).getContent();
        IBIZProMessage ibizProMessage = new IBIZProMessage();
        if(toList.size() > 0) {
            ibizProMessage.setTo(toList.get(0).getUserid());
        }else {
            return  et;
        }
        SysEmployeeSearchContext fromSearchContext = new SysEmployeeSearchContext();
        fromSearchContext.setN_username_in(AuthenticationUser.getAuthenticationUser().getUsername());
        List<SysEmployee> fromList = iSysEmployeeService.searchDefault(fromSearchContext).getContent();
        for(fromList.size() > 0) {
            ibizProMessage.setFrom(fromList.get(0).toString());
        }else {
            return  et;
        }

        ibizProMessage.setIbizpromessagename(name);
        ibizProMessage.setType("已办");
        JSONObject param = new JSONObject();
        param.put("objectid", id);
        param.put("objecttype", type);
        param.put("objectsourcepath", path);
        param.put("objecttextname", logicname);
        param.put("actiontextname", actiontextname);
        ibizProMessage.setParam(param.toJSONString());
        iibizProMessageService.markDone(ibizProMessage);
        return et;
    }

    @Override
    public Action sendToread(Action et) {
        Long id = et.getObjectid();
        String name = "";
        if(et.get("name") != null) {
            name = et.get("name").toString();
        }
        String noticeusers = "";
        if(et.get("noticeusers") != null) {
            noticeusers = et.get("noticeusers").toString();
        }
        String touser = "";
        if(et.get("touser") != null) {
            touser = et.get("touser").toString();
        }
        String ccuser = "";
        if(et.get("ccuser") != null) {
            ccuser = et.get("ccuser").toString();
        }
        String logicname = "";
        if(et.get("logicname") != null) {
            logicname = et.get("logicname").toString();
        }
        String type = et.getObjecttype();
        String path = "";
        if(et.get("path") != null) {
            path = et.get("path").toString();
        }
        String actiontextname = "";
        if(et.get("actiontextname") != null) {
            actiontextname = et.get("actiontextname").toString();
        }
        String noticeuserss = "";
        if(touser!= null && !"".equals(touser)) {
            noticeuserss += touser + ",";
        }
        JSONObject param = new JSONObject();
        if(noticeusers != null && !"".equals(noticeusers)) {
            noticeuserss += noticeusers;
        }
        if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() == 0) {
            noticeuserss += ccuser;
        }
        else if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() > 0) {
            noticeuserss += "," + ccuser;
        }
        if(noticeuserss.length() == 0) {
            return et;
        }
        IBIZProMessage ibizProMessage = new IBIZProMessage();

        ibizProMessage.setCc(noticeuserss);

        ibizProMessage.setFrom(AuthenticationUser.getAuthenticationUser().getUsername());

        ibizProMessage.setType(StaticDict.Message__type.TOREAD.getValue());
        ibizProMessage.setIbizpromessagename(name);
        param.put("objectid", id);
        param.put("objecttype", type);
        param.put("objectsourcepath", path);
        param.put("objecttextname", logicname);
        param.put("actiontextname", actiontextname);
        ibizProMessage.setParam(param.toJSONString());
        iibizProMessageService.send(ibizProMessage);
        log.info("待阅消息发送成功！");
        return et;
    }

    /**
     * @param noticeusers
     * @param et
     */
    public void send(String noticeusers, Action et) {
        if (StaticDict.Action__object_type.TASK.getValue().equals(et.getObjecttype())) {
            Task task = iTaskService.get(et.getObjectid());
            Action action = new Action();
            action.setObjectid(task.getId());
            action.set("name", task.getName());
            action.set("noticeusers", noticeusers);
            action.set("ccuser", task.getMailto());
            action.set("touser", task.getAssignedto());
            action.set("logicname", "任務");
            action.setObjecttype(StaticDict.Action__object_type.TASK.getValue());
            action.set("path", "tasks");
            action.set("actiontextname", StaticDict.Action__type.COMMENTED.getText());
            sendToread(action);
        } else if (StaticDict.Action__object_type.STORY.getValue().equals(et.getObjecttype())) {
            Story story = iStoryService.get(et.getObjectid());
            Action action = new Action();
            action.setObjectid(story.getId());
            action.set("name", story.getTitle());
            action.set("noticeusers", noticeusers);
            action.set("ccuser", story.getMailto());
            action.set("touser", story.getAssignedto());
            action.set("logicname", "需求");
            action.setObjecttype(StaticDict.Action__object_type.STORY.getValue());
            action.set("path", "storys");
            action.set("actiontextname", StaticDict.Action__type.COMMENTED.getText());
            sendToread(action);
        } else if (StaticDict.Action__object_type.BUG.getValue().equals(et.getObjecttype())) {
            Bug bug = iBugService.get(et.getObjectid());
            Action action = new Action();
            action.setObjectid(bug.getId());
            action.set("name", bug.getTitle());
            action.set("noticeusers", noticeusers);
            action.set("ccuser", bug.getMailto());
            action.set("touser", bug.getAssignedto());
            action.set("logicname", "Bug");
            action.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
            action.set("path", "bugs");
            action.set("actiontextname", StaticDict.Action__type.COMMENTED.getText());
            sendToread(action);
        } else {
            log.info("其他暂不支持！");
        }
    }

}

