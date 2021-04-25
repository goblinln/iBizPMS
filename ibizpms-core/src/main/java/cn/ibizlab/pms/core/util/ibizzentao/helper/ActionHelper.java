package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.util.ibizzentao.common.Fixer;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.ProjectProductSearchContext;
import cn.ibizlab.pms.core.zentao.mapper.ActionMapper;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.service.IProjectProductService;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author huwei
 */
@Component
@Slf4j
public class ActionHelper extends ZTBaseHelper<ActionMapper, Action> {

    String[] processType = new String[]{StaticDict.Action__object_type.STORY.getValue(),
            StaticDict.Action__object_type.PRODUCTPLAN.getValue(),
            StaticDict.Action__object_type.RELEASE.getValue(),
            StaticDict.Action__object_type.TASK.getValue(),
            StaticDict.Action__object_type.BUILD.getValue(),
            StaticDict.Action__object_type.BUG.getValue(),
            StaticDict.Action__object_type.CASE.getValue(),
            StaticDict.Action__object_type.TESTTASK.getValue(),
            StaticDict.Action__object_type.DOC.getValue()};

    @Autowired
    FileHelper fileHelper;

    @Autowired
    MessageHelper messageHelper;

    @Autowired
    HistoryHelper historyHelper;

    @Autowired
    UserHelper userHelper;

    @Autowired
    IIBIZProMessageService iibizProMessageService;

    @Autowired
    IProjectProductService projectProductService;

    @Autowired
    TaskHelper taskHelper;

    @Autowired
    StoryHelper storyHelper;

    @Autowired
    CaseHelper caseHelper;

    @Autowired
    BugHelper bugHelper;

    /**
     * 只关联产品
     */
    private static String PRODUCT_IN = StaticDict.Action__object_type.STORY.getValue() +
            MULTIPLE_CHOICE + StaticDict.Action__object_type.CASE.getValue() +
            MULTIPLE_CHOICE + StaticDict.Action__object_type.PRODUCTPLAN.getValue();

    /**
     * 产品和项目都有关联
     */
    private static String PRODUCT_PROJECT_IN = StaticDict.Action__object_type.BUILD.getValue() +
            MULTIPLE_CHOICE + StaticDict.Action__object_type.BUG.getValue() +
            MULTIPLE_CHOICE + StaticDict.Action__object_type.DOC.getValue() +
            MULTIPLE_CHOICE + StaticDict.Action__object_type.TESTTASK.getValue();


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean create(Action et) {
        et.setComment(et.getComment() == null ? "" : et.getComment());
        String noticeusers = et.getNoticeusers();
        String files = et.getFiles();
        this.create(et.getObjecttype(), et.getObjectid(), StaticDict.Action__type.COMMENTED.getValue(), et.getComment(), "", null, true);
        // 发送待阅提醒
        send(noticeusers, et);
        // 保存文件
        String extra = "0";
        if(files != null) {
            if (StaticDict.Action__object_type.STORY.getValue().equals(et.getObjecttype())) {
                Story story = storyHelper.get(et.getObjectid());
                if (story != null && story.getVersion() != null) {
                    extra = String.valueOf(story.getVersion());
                }
            } else if (StaticDict.Action__object_type.CASE.getValue().equals(et.getObjecttype())) {
                Case case1 = caseHelper.get(et.getObjectid());
                if (case1 != null && case1.getVersion() != null) {
                    extra = String.valueOf(case1.getVersion());
                }
            }
        }
        //更新附件
        fileHelper.updateObjectID(et.getObjectid(), et.getObjecttype(), files, extra);
        return true;
    }

    /**
     *
     * @param id 主键
     * @param name 标题
     * @param noticeusers 消息通知用户
     * @param touser 指派用户
     * @param ccuser 抄送用户
     * @param logicname
     * @param type
     * @param path
     * @param actiontextname
     */
    public void sendToread(Long id, String name,String noticeusers,String touser,String ccuser,String logicname, String type, String path, String actiontextname) {
        try {
            String noticeuserss = "";
            if(touser!= null && !"".equals(touser)) {
                noticeuserss += touser + MULTIPLE_CHOICE;
            }
            JSONObject param = new JSONObject();
            if(noticeusers != null && !"".equals(noticeusers)) {
                noticeuserss += noticeusers;
            }
            if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() == 0) {
                noticeuserss += ccuser;
            }
            else if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() > 0) {
                noticeuserss += MULTIPLE_CHOICE + ccuser;
            }
            if(noticeuserss.length() == 0) {
                return;
            }
            IBIZProMessage ibizProMessage = new IBIZProMessage();

            ibizProMessage.setCc(noticeuserss);

            ibizProMessage.setFrom(AuthenticationUser.getAuthenticationUser().getUsername());

            ibizProMessage.setType(StaticDict.Message__type.TOREAD.getValue());
            ibizProMessage.setIbizpromessagename(name);
            param.put(PARAM_OBJECT_ID, id);
            param.put(PARAM_OBJECT_TYPE, type);
            param.put(PARAM_OBJECT_SOURCE_PATH, path);
            param.put(PARAM_OBJECT_TEXT_NAME, logicname);
            param.put(PARAM_ACTION_TEXT_NAME, actiontextname);
            ibizProMessage.setParam(param.toJSONString());
            iibizProMessageService.send(ibizProMessage);
            log.info("待阅消息发送成功！");
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            log.error("待阅消息发送失败！");
        }
    }

    /**
     *
     * @param id 主键
     * @param name 标题
     * @param noticeusers 消息通知用户
     * @param touser 指派用户
     * @param ccuser 抄送用户
     * @param logicname
     * @param type
     * @param path
     * @param actiontextname
     */
    public void sendTodo(Long id, String name, String noticeusers, String touser, String ccuser, String logicname, String type, String path, String actiontextname) {
        try {
            String noticeuserss = "";
            if(touser != null && !"".equals(touser)) {
                noticeuserss += touser + MULTIPLE_CHOICE;
            }
            JSONObject param = new JSONObject();
            if(noticeusers != null && !"".equals(noticeusers)) {
                noticeuserss += noticeusers;
            }
            if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() == 0) {
                noticeuserss += ccuser;
            }
            else if(ccuser != null && !"".equals(ccuser) && noticeuserss.length() > 0) {
                noticeuserss += MULTIPLE_CHOICE + ccuser;
            }
            if(noticeuserss.length() == 0) {
                return;
            }

            IBIZProMessage ibizProMessage = new IBIZProMessage();
            if (touser == null || "".equals(touser)){
                return;
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
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            log.error("待办消息发送失败！");
        }
    }


    public void sendMarkDone(Long id, String name, String toUser,String logicname, String type, String path, String actiontextname){
        try {
            if (toUser == null || toUser.equals("")){
                return;
            }
            User to = userHelper.getOne(new QueryWrapper<User>().eq("account",toUser));
            User from = userHelper.getOne(new QueryWrapper<User>().eq("account", AuthenticationUser.getAuthenticationUser().getUsername()));

            IBIZProMessage ibizProMessage = new IBIZProMessage();
            ibizProMessage.setTo(to.getId().toString());

            ibizProMessage.setFrom(from.toString());
            ibizProMessage.setIbizpromessagename(name);
            ibizProMessage.setType("已办");
            JSONObject param = new JSONObject();
            param.put(PARAM_OBJECT_ID, id);
            param.put(PARAM_OBJECT_TYPE, type);
            param.put(PARAM_OBJECT_SOURCE_PATH, path);
            param.put(PARAM_OBJECT_TEXT_NAME, logicname);
            param.put(PARAM_ACTION_TEXT_NAME, actiontextname);
            ibizProMessage.setParam(param.toJSONString());
            iibizProMessageService.markDone(ibizProMessage);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            log.error("已办消息发送失败！");
        }
    }


    /**
     * edit 编辑
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean edit(Action et) {
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(et);
        send(noticeusers, et);
        return true;
    }

    /**
     * @param noticeusers
     * @param et
     */
    public void send(String noticeusers, Action et) {
        if (StaticDict.Action__object_type.TASK.getValue().equals(et.getObjecttype())) {
            Task task = taskHelper.get(et.getObjectid());
            sendToread(task.getId(), task.getName(), noticeusers, task.getAssignedto(), task.getMailto(), "任務", StaticDict.Action__object_type.TASK.getValue(),"tasks", StaticDict.Action__type.COMMENTED.getText());
        } else if (StaticDict.Action__object_type.STORY.getValue().equals(et.getObjecttype())) {
            Story story = storyHelper.get(et.getObjectid());
            sendToread(story.getId(), story.getTitle(), noticeusers, story.getAssignedto(), story.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stroys", StaticDict.Action__type.COMMENTED.getText());
        } else if (StaticDict.Action__object_type.BUG.getValue().equals(et.getObjecttype())) {
            Bug bug = bugHelper.get(et.getObjectid());
            sendToread(bug.getId(), bug.getTitle(), noticeusers, bug.getAssignedto(), bug.getMailto(), "Bug", StaticDict.Action__object_type.TASK.getValue(), "bugs", StaticDict.Action__type.COMMENTED.getText());
        } else {
            log.info("其他暂不支持！");
        }
    }

    /**
     * @param objectType
     * @param objectID
     * @param actionType
     * @param comment
     * @param extra
     * @param actor
     * @param autoDelete
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Action create(String objectType, Long objectID, String actionType, String comment, String extra, String actor, boolean autoDelete) {
        Action et = new Action();
        if (actor == null) {
            actor = AuthenticationUser.getAuthenticationUser().getUsername();
        }
        objectType = objectType.replace("`", "");
        et.setObjecttype(objectType.toLowerCase());
        et.setObjectid(objectID);
        et.setActor(actor);
        et.setAction(actionType.toLowerCase());
        et.setDate(ZTDateUtil.now());
        et.setExtra(extra);
        et.setRead(StaticDict.YesNo.ITEM_0.getValue());
        et.setComment(Fixer.stripDataTags(comment));

        et.setProduct(",0,");
        et.setProject(0L);
        if (StringUtils.compare(objectType, StaticDict.Action__object_type.PRODUCT.getValue()) == 0) {
            et.setProduct(MULTIPLE_CHOICE + objectID + MULTIPLE_CHOICE);
        } else if (StringUtils.compare(objectType, StaticDict.Action__object_type.PROJECT.getValue()) == 0) {
            ProjectProductSearchContext ctx = new ProjectProductSearchContext();
            ctx.setN_project_eq(objectID);
            List<ProjectProduct> projectProducts = projectProductService.searchDefault(ctx).getContent();
            String products = "";
            for (int i = 0; i < projectProducts.size(); i++) {
                if (i > 0) {
                    products += MULTIPLE_CHOICE;
                }
                products += String.valueOf(projectProducts.get(i).getProduct());
            }
            et.setProduct(MULTIPLE_CHOICE + products + MULTIPLE_CHOICE);
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

    @Transactional(rollbackFor = RuntimeException.class)
    public void logHistory(Long actionId, List<History> changes) {
        for (History change : changes) {
            change.setAction(actionId);
            historyHelper.create(change);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Action editComment(Action et) {
        et.setDate(ZTDateUtil.now());
        this.edit(et);
        return et;
    }

    public Action managePmsEe(Action et){
        et.setId(null);
        String actor = et.getActor();
        String objectType = et.getObjecttype();
        if (actor == null) {
            actor = AuthenticationUser.getAuthenticationUser().getUsername();
        }
        objectType = objectType.replace("`", "");
        et.setObjecttype(objectType.toLowerCase());
        et.setActor(actor);
        et.setAction(et.getAction().toLowerCase());
        et.setDate(ZTDateUtil.now());
        et.setRead(StaticDict.YesNo.ITEM_0.getValue());
        et.setComment(Fixer.stripDataTags(et.getComment()));

        et.setProduct(",0,");
        et.setProject(0L);
        if (StringUtils.compare(objectType, StaticDict.Action__object_type.PRODUCT.getValue()) == 0) {
            et.setProduct(MULTIPLE_CHOICE + et.getObjectid() + MULTIPLE_CHOICE);
        } else if (StringUtils.compare(objectType, StaticDict.Action__object_type.PROJECT.getValue()) == 0) {
            ProjectProductSearchContext ctx = new ProjectProductSearchContext();
            ctx.setN_project_eq(et.getObjectid());
            List<ProjectProduct> projectProducts = projectProductService.searchDefault(ctx).getContent();
            String products = "";
            for (int i = 0; i < projectProducts.size(); i++) {
                if (i > 0) {
                    products += MULTIPLE_CHOICE;
                }
                products += String.valueOf(projectProducts.get(i).getProduct());
            }
            et.setProduct(MULTIPLE_CHOICE + products + MULTIPLE_CHOICE);
            et.setProject(et.getObjectid());
        } else if (Arrays.deepToString(processType).contains(objectType)) {
            JSONObject jsonObject = getProductAndProject(objectType, et.getObjectid());
            et.setProduct(jsonObject.getString(StaticDict.Action__object_type.PRODUCT.getValue()));
            et.setProject(jsonObject.getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            log.info(processType + "product、project设置未实现");

        }
        super.create(et);
        return et;
    }

}
