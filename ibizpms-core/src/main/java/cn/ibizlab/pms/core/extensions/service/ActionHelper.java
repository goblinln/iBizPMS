package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import liquibase.pro.packaged.A;

import java.util.List;

public class ActionHelper {


    /**
     * 创建系统日志
     *
     * @param objectId
     * @param objecttype
     * @param histories
     * @param actions
     * @param comment
     * @param extra
     * @param actor
     * @param iActionService
     */
    public static Action createHis(Long objectId, String objecttype, List<History> histories, String actions, String comment, String extra, String actor, IActionService iActionService) {
        Action action = new Action();
        action.setObjecttype(objecttype);
        action.setObjectid(objectId);
        action.setAction(actions);
        action.setComment(comment);
        action.setExtra(extra);
        action.setHistorys(histories);
        action.setActor(actor);
        return   iActionService.createHis(action);
    }

    public static void logHistory(Long actionId, List<History> changes, IHistoryService iHistoryService) {
        for (History change : changes) {
            change.setAction(actionId);
            iHistoryService.create(change);
        }
    }

    /**
     * 发送待办待阅
     *
     * @param id 处理对象标识
     * @param name 处理对象名称
     * @param noticeusers 备注中@对象
     * @param touser 指派给
     * @param ccuser 抄送给
     * @param logicname 处理对象的逻辑名称
     * @param type 处理对象
     * @param path 路由
     * @param actiontextname 处理方法
     * @param todo 待办or待阅
     * @param iActionService
     */
    public static void sendTodoOrToread(Long id, String name, String noticeusers, String touser, String ccuser, String logicname, String type, String path, String actiontextname, boolean todo, IActionService iActionService) {
        Action actionTodoOrToread = new Action();
        actionTodoOrToread.setObjectid(id);
        actionTodoOrToread.set("name", name);
        actionTodoOrToread.set("noticeusers", noticeusers);
        actionTodoOrToread.set("ccuser", ccuser);
        actionTodoOrToread.set("touser", touser);
        actionTodoOrToread.set("logicname", logicname);
        actionTodoOrToread.setObjecttype(type);
        actionTodoOrToread.set("path", path);
        actionTodoOrToread.set("actiontextname", actiontextname);
        if(todo) {
            iActionService.sendTodo(actionTodoOrToread);
        }else {
            iActionService.sendToread(actionTodoOrToread);
        }
    }

    /**
     * 消息已读
     *
     * @param id
     * @param name
     * @param toUser
     * @param logicname
     * @param type
     * @param path
     * @param actiontextname
     * @param iActionService
     */
    public static void sendMarkDone(Long id, String name, String toUser,String logicname, String type, String path, String actiontextname, IActionService iActionService){
        Action actionMarkDone = new Action();
        actionMarkDone.setObjectid(id);
        actionMarkDone.set("name", name);
        actionMarkDone.set("touser", toUser);
        actionMarkDone.set("logicname", logicname);
        actionMarkDone.setObjecttype(type);
        actionMarkDone.set("path", path);
        actionMarkDone.set("actiontextname", actiontextname);
        iActionService.sendMarkDone(actionMarkDone);
    }
}
