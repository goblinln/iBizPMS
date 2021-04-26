package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;

import java.util.List;

public class ActionHelper {


    public static void createHis(Long objectId, String objecttype, List<History> histories, String actions, String comment, String extra, String actor, IActionService iActionService) {
        Action action = new Action();
        action.setObjecttype(objecttype);
        action.setObjectid(objectId);
        action.setAction(actions);
        action.setComment(comment);
        action.setExtra(extra);
        action.setHistorys(histories);
        action.setActor(actor);
        iActionService.createHis(action);
    }

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
