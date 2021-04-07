package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProjectRuntime")
public class ProjectRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectService projectService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Project();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Project.json";
    }

    @Override
    public String getName() {
        return "ZT_PROJECT";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Project domain = (Project) o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Project domain = (Project) o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        Project domain = (Project) o;
        try {
            return domain.contains(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        Project domain = (Project) o;
        try {
            domain.reset(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object createEntity() {
        return new Project();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProjectServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        if (action.equals("manageMembers")) {
            return aroundAction("ManageMembers", point);
        }
        if (action.equals("pmsEeProjectAllTaskCount")) {
            return aroundAction("PmsEeProjectAllTaskCount", point);
        }
        if (action.equals("pmsEeProjectTodoTaskCount")) {
            return aroundAction("PmsEeProjectTodoTaskCount", point);
        }
        if (action.equals("putoff")) {
            return aroundAction("Putoff", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("start")) {
            return aroundAction("Start", point);
        }
        if (action.equals("suspend")) {
            return aroundAction("Suspend", point);
        }
        if (action.equals("unlinkMember")) {
            return aroundAction("UnlinkMember", point);
        }
        if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }
        if (action.equals("updateOrder")) {
            return aroundAction("UpdateOrder", point);
        }

        if (action.equals("searchBugProject")) {
            return aroundDataSet("BugProject", point);
        }
        if (action.equals("searchCurPlanProject")) {
            return aroundDataSet("CurPlanProject", point);
        }
        if (action.equals("searchCurProduct")) {
            return aroundDataSet("CurProduct", point);
        }
        if (action.equals("searchCurUser")) {
            return aroundDataSet("CurUser", point);
        }
        if (action.equals("searchCurUserSa")) {
            return aroundDataSet("CurUserSa", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        if (action.equals("searchInvolvedProject")) {
            return aroundDataSet("InvolvedProject", point);
        }
        if (action.equals("searchInvolvedProject_StoryTaskBug")) {
            return aroundDataSet("InvolvedProject_StoryTaskBug", point);
        }
        if (action.equals("searchMyProject")) {
            return aroundDataSet("MyProject", point);
        }
        if (action.equals("searchProjectTeam")) {
            return aroundDataSet("ProjectTeam", point);
        }
        if (action.equals("searchStoryProject")) {
            return aroundDataSet("StoryProject", point);
        }
        if (action.equals("searchUnDoneProject")) {
            return aroundDataSet("UnDoneProject", point);
        }
        return point.proceed();
    }

}
