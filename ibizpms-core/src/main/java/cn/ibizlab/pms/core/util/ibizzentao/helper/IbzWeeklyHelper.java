package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.mapper.IbzWeeklyMapper;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import cn.ibizlab.pms.core.uaa.service.ISysUserRoleService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
@Slf4j
public class IbzWeeklyHelper  extends ZTBaseHelper<IbzWeeklyMapper, IbzWeekly> {
    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;
    @Autowired
    TaskHelper taskHelper;
    @Autowired
    ISysEmployeeService iSysEmployeeService;

    @Autowired
    ISysUserRoleService iSysUserRoleService;
    @Autowired
    IIbzReportRoleConfigService iIbzReportRoleConfigService;

    String[] diffAttrs = {"workthisweek", "comment", "plannextweek"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzWeekly et) {

        String files = et.getFiles();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        int week = getWeekAtThisMonth();

        List<IbzWeekly> list = this.list(new QueryWrapper<IbzWeekly>().eq("account",et.getAccount()).last("and YEARWEEK(date,1) = YEARWEEK(now(),1)"));
        if (list.size() > 0){
            throw new BadRequestAlertException(String.format("%1$s-%2$s月第%3$s周的周报" ,et.getIbzweeklyname(), dateFormat.format(et.getDate()),week)+"已经存在，请勿重复创建！", StaticDict.ReportType.WEEKLY.getValue(), "");
        }
        et.setIbzweeklyname(String.format("%1$s-%2$s月第%3$s周的周报" ,et.getIbzweeklyname(), dateFormat.format(et.getDate()),week));
        removeSomeTask(et);
        if (!SqlHelper.retBool(this.baseMapper.insert(et))){
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);
        fileHelper.updateObjectID(et.getIbzweeklyid(), StaticDict.File__object_type.WEEKLY.getValue(), files, "");
        actionHelper.create(StaticDict.Action__object_type.WEEKLY.getValue(), et.getIbzweeklyid(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
        return true;
    }

    public int getWeekAtThisMonth(){
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int week = calendar.get(Calendar.WEEK_OF_MONTH); //当月第几周
        return week;
    }
    //判断任务状态是否时进行中，或者完成时间时本周
    public void removeSomeTask(IbzWeekly et){
        String ids = et.getThisweektask();
        Timestamp date = et.getDate() == null ? ZTDateUtil.now() : et.getDate();
        List<Task> tasks = taskHelper.list(new QueryWrapper<Task>().last("and find_in_set(id,'"+ids+"') and project IN ( SELECT id FROM zt_project WHERE deleted = '0' ) and  EXISTS(select 1 from zt_taskestimate t2 where zt_task.id = t2.task and t2.account = '"+ AuthenticationUser.getAuthenticationUser().getUsername()+"' and YEARWEEK(t2.date,1) = YEARWEEK('"+date+"',1) ) "));
        String newIds = "";
        for (Task t : tasks) {
            if (newIds.length() > 0){
                newIds += ",";
            }
            newIds += t.getId();
        }
        et.setThisweektask(newIds);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(IbzWeekly et) {
        IbzWeekly old = new IbzWeekly();
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), old);
        String files = et.getFiles();
        removeSomeTask(et);
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_weeklyid", et.getIbzweeklyid()))) {
            return false;
        }

        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);

        fileHelper.updateObjectID(et.getIbzweeklyid(), StaticDict.File__object_type.WEEKLY.getValue(), files, "");
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            Action action = actionHelper.create(StaticDict.Action__object_type.WEEKLY.getValue(), et.getIbzweeklyid(), strAction,
                    "", "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public IbzWeekly submit(IbzWeekly et) {
        IbzWeekly newWeekly = new IbzWeekly();
        newWeekly.setIbzweeklyid(et.getIbzweeklyid());
        newWeekly.setIssubmit(StaticDict.YesNo.ITEM_1.getValue());
        newWeekly.setSubmittime(ZTDateUtil.now());

        IbzWeekly old = new IbzWeekly();
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), old);
        boolean flag = (old.getWorkthisweek() == null && old.getThisweektask() == null) || old.getReportto() == null;
        if (flag) {
            throw new RuntimeException("请填写本周工作或本周完成任务并且指定汇报人后提交！");
        }
        //String files = et.getFiles();
        if (!update(newWeekly, (Wrapper) newWeekly.getUpdateWrapper(true).eq("Ibz_Weeklyid", newWeekly.getIbzweeklyid()))) {
            return et;
        }
        CachedBeanCopier.copy(get(newWeekly.getIbzweeklyid()), newWeekly);
       // fileHelper.updateObjectID(et.getIbzweeklyid(), StaticDict.File__object_type.WEEKLY.getValue(), files, "");
        List<History> changes = ChangeUtil.diff(old, newWeekly, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            Action action = actionHelper.create(StaticDict.Action__object_type.WEEKLY.getValue(), et.getIbzweeklyid(), strAction,
                    "", "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        // 给汇报人，抄送人 待阅
        String ss = "已经提交给您了，请查收哦！";
        actionHelper.sendToread(newWeekly.getIbzweeklyid(), newWeekly.getIbzweeklyname() + ss, "", newWeekly.getReportto(), newWeekly.getMailto(), "周报", StaticDict.Action__object_type.WEEKLY.getValue(), "ibzweeklies", StaticDict.Action__type.SUBMIT.getText());
        return newWeekly;
    }

    @Transactional(rollbackFor = Exception.class)
    public IbzWeekly createEveryWeekReport(IbzWeekly et){
        //获取角色id
        List<IbzReportRoleConfig> ibzReportRoleConfigList = iIbzReportRoleConfigService.list(new QueryWrapper<IbzReportRoleConfig>().eq("type", StaticDict.Action__object_type.WEEKLY.getValue()).orderByDesc("updatedate"));
        if (ibzReportRoleConfigList.size() == 0 || ibzReportRoleConfigList.get(0).getReportRole() == null){
            return et;
        }
        String[] reportRoleIds = ibzReportRoleConfigList.get(0).getReportRole().split(",");
        for (String roleId : reportRoleIds) {
            SysUserRoleSearchContext sysRoleSearchContext = new SysUserRoleSearchContext();
            sysRoleSearchContext.setSize(1000);
            sysRoleSearchContext.setN_sys_roleid_eq(roleId);
            Page<SysUserRole> page1 = iSysUserRoleService.searchDefault(sysRoleSearchContext);
            List<SysUserRole> sysRolesList = page1.getContent();
            String allRoles = "";
            for (SysUserRole role : sysRolesList) {
                if (role.getLoginname() == null){
                    continue;
                }
                if ("".equals(allRoles)){
                    allRoles += role.getLoginname();
                    continue;
                }
                allRoles += ";" +role.getLoginname();
            }
            if ("".equals(allRoles)) {
                continue;
            }

            SysEmployeeSearchContext sysEmployeeSearchContext = new SysEmployeeSearchContext();
            String notAccount = notAccount();
            if (!"".equals(notAccount)) {
                sysEmployeeSearchContext.setN_username_notin(notAccount);
            }
            sysEmployeeSearchContext.setN_username_in(allRoles);
            sysEmployeeSearchContext.setSize(1000);
            Page<SysEmployee> page = iSysEmployeeService.searchDefault(sysEmployeeSearchContext);
            List<SysEmployee> list = page.getContent();
            Timestamp now = ZTDateUtil.now();
            for (SysEmployee sysEmployee : list) {
                IbzWeekly ibzWeekly = new IbzWeekly();
                ibzWeekly.setIbzweeklyname(sysEmployee.getPersonname());
                ibzWeekly.setAccount(sysEmployee.getUsername());
                ibzWeekly.setDate(now);
                ibzWeekly.setIssubmit(StaticDict.YesNo.ITEM_0.getValue());
                ibzWeekly.setReportstatus(StaticDict.ReportStatus.ITEM_0.getValue());
                createGetLastWeekPlanAndWork(ibzWeekly);
                this.create(ibzWeekly);
            }
        }
        return et;
    }


    //根据用户名获取上周周报，根据周报获取下周计划工作
    public void createGetLastWeekPlanAndWork(IbzWeekly et){
        Set<String> taskIdSet = new HashSet<>();
        //本周已完成任务
        List<Task> tasks = taskHelper.list(new QueryWrapper<Task>().eq("deleted","0").eq("finishedBy",et.getAccount()).last("and YEARWEEK(date_format(DATE_SUB( finishedDate,INTERVAL 1 DAY),'%Y-%m-%d')) = YEARWEEK(now()) "));//YEARWEEK(date_format(finishedDate,'%Y-%m-%d')) = YEARWEEK(now())
        for (Task t: tasks) {
            taskIdSet.add(t.getId().toString());
        }


        String lastWeekNextWeekPlan = ""; //上周周报计划工作
        //获取上周的周报
        List<IbzWeekly> list = this.list(new QueryWrapper<IbzWeekly>().eq("account",et.getAccount()).last(" and YEARWEEK(DATE_FORMAT(DATE_SUB( date,INTERVAL 1 DAY),'%Y-%m-%d')) = YEARWEEK(now())-1")); //
        if (list.size() != 0){
            if (list.get(list.size()-1).getNextweektask() != null){
                String[] lastWeekPlanTask = list.get(list.size()-1).getNextweektask().split(","); //上周计划参与任务
                taskIdSet.addAll(Arrays.asList(lastWeekPlanTask));
            }
            lastWeekNextWeekPlan = list.get(list.size()-1).getPlannextweek(); //上周周报计划工作
        }
        if (taskIdSet.size() != 0){
            et.setThisweektask(Joiner.on(",").join(taskIdSet));
        }
        if (!"".equals(lastWeekNextWeekPlan)){
            et.setWorkthisweek(lastWeekNextWeekPlan); //将上周周报的下周计划工作填充到新建周报的本周计划工作
        }
    }

    public String notAccount() {
        Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday= getMondayAndSunday(c,today);  //今天是周几
        c.add(Calendar.DAY_OF_MONTH,weekday == 0 ? 0 : (8-weekday));
        Date sunday = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,-6);
        Date monday = c.getTime();


        String notaccount = "";
        List<IbzWeekly> list = this.list(new QueryWrapper<IbzWeekly>().last("where YEARWEEK(DATE_FORMAT(DATE_SUB( date,INTERVAL 1 DAY),'%Y-%m-%d')) = YEARWEEK(now())"));
        for(IbzWeekly ibzWeekly : list) {
            if ("".equals(notaccount)) {
                notaccount += ibzWeekly.getAccount();
                continue;
            }
            notaccount += ";" + ibzWeekly.getAccount();
        }

        return notaccount;
    }

    @Transactional(rollbackFor = Exception.class)
    public IbzWeekly haveRead(IbzWeekly et) {
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);
        String curAccount = AuthenticationUser.getAuthenticationUser().getUsername();
        if (curAccount == null){
            return et;
        }
        boolean exist = curAccount.equals(et.getReportto()) || (et.getMailto() != null && Arrays.asList(et.getMailto().split(",")).contains(curAccount));
        if (exist){
            List<Action> list = actionHelper.list(new QueryWrapper<Action>().eq("objecttype", StaticDict.Action__object_type.WEEKLY.getValue()).eq("action", StaticDict.Action__type.READ.getValue()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("objectid", et.getIbzweeklyid()));
            if(list.size() == 0) {
                actionHelper.create(StaticDict.Action__object_type.WEEKLY.getValue(), et.getIbzweeklyid(), StaticDict.Action__type.READ.getValue(), "", "", null, true);
            }
        }
        return et;
    }


    public IbzWeekly pushUserWeekly(IbzWeekly et) {
        List<IbzWeekly> list = this.list(new QueryWrapper<IbzWeekly>().eq("issubmit", StaticDict.YesNo.ITEM_0.getValue()).last("and YEARWEEK(DATE_FORMAT(DATE_SUB( date,INTERVAL 1 DAY),'%Y-%m-%d')) = YEARWEEK(now())"));
        int week = getWeekAtThisMonth();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        for (IbzWeekly ibzWeekly : list) {
            actionHelper.sendToread(ibzWeekly.getIbzweeklyid(), "您的"+String.format("%1$s月第%2$s",dateFormat.format(ibzWeekly.getDate()),week)+"的周报还未提交，请及时填写！", ibzWeekly.getAccount(), "", "", "周报", StaticDict.Action__object_type.WEEKLY.getValue(), "ibzweeklies", StaticDict.Action__type.REMIND.getText());
        }
        return et;
    }


    public int getMondayAndSunday(Calendar c,Date today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(today);
        try {
            today = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK)-1;  //今天是周几
        return weekday;
    }


}
