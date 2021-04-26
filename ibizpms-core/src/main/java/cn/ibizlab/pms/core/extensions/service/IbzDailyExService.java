package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.report.service.impl.IbzDailyServiceImpl;
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import cn.ibizlab.pms.core.uaa.service.ISysUserRoleService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实体[日报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzDailyExService")
public class IbzDailyExService extends IbzDailyServiceImpl {

    @Autowired
    IFileService iFileService;
    @Autowired
    IActionService iActionService;
    @Autowired
    ITaskService iTaskService;
    @Autowired
    ISysEmployeeService iSysEmployeeService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;
    @Autowired
    IIbzReportRoleConfigService iIbzReportRoleConfigService;


    String[] diffAttrs = {"worktoday", "comment", "planstomorrow"};

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(IbzDaily et) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<IbzDaily> dailyList = this.list(new QueryWrapper<IbzDaily>().eq("account", et.getAccount()).last(" and DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')"));
        if (dailyList.size() > 0) {
            throw new BadRequestAlertException(dateFormat.format(et.getDate()) + "的日报已经存在,请勿重复创建！", StaticDict.ReportType.DAILY.getValue(), "");
        }
        String files = et.getFiles();
        et.setIbzdailyname(String.format("%1$s-%2$s的日报", et.getIbzdailyname(), dateFormat.format(et.getDate())));
        removeSomeTasks(et);
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzdailyid()), et);
        FileHelper.updateObjectID(et.getIbzdailyid(), StaticDict.File__object_type.DAILY.getValue(), files, "", iFileService);
        ActionHelper.createHis(et.getIbzdailyid(), StaticDict.Action__object_type.DAILY.getValue(), null, StaticDict.Action__type.OPENED.getValue(), "", "", null, iActionService);
        return true;

    }

    @Override
    public boolean update(IbzDaily et) {
        IbzDaily old = new IbzDaily();
        CachedBeanCopier.copy(get(et.getIbzdailyid()), old);
        String files = et.getFiles();
        removeSomeTasks(et);
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_dailyid", et.getIbzdailyid()))) {
            return false;
        }

        CachedBeanCopier.copy(get(et.getIbzdailyid()), et);

        FileHelper.updateObjectID(et.getIbzdailyid(), StaticDict.File__object_type.DAILY.getValue(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            ActionHelper.createHis(et.getIbzdailyid(), StaticDict.Action__object_type.DAILY.getValue(), changes, strAction,
                    "", "", null, iActionService);
        }
        return true;

    }

    /**
     * [CreateUserDaily:定时生成用户日报] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily createUserDaily(IbzDaily et) {
        Timestamp now = ZTDateUtil.now();
        String weekOfDate = getWeekOfDate(now);
        if (!"星期六".equals(weekOfDate) && !"星期日".equals(weekOfDate)) {
            List<IbzReportRoleConfig> reportRoleConfigList = iIbzReportRoleConfigService.list(new QueryWrapper<IbzReportRoleConfig>().eq(ZTBaseHelper.FIELD_TYPE, StaticDict.ReportType.DAILY.getValue()).orderByDesc("updatedate"));
            if (reportRoleConfigList.size() == 0 || reportRoleConfigList.get(0).getReportRole() == null) {
                return et;
            }
            String[] roleIds = reportRoleConfigList.get(0).getReportRole().split(",");
            for (String roleId : roleIds) {
                SysUserRoleSearchContext sysUserRoleSearchContext = new SysUserRoleSearchContext();
                sysUserRoleSearchContext.setN_sys_roleid_eq(roleId);
                sysUserRoleSearchContext.setSize(100);
                Page<SysUserRole> sysUserRoles = iSysUserRoleService.searchDefault(sysUserRoleSearchContext);
                List<SysUserRole> sysUserRoleList = sysUserRoles.getContent();
                if (sysUserRoleList.size() == 0) {
                    continue;
                }
                String account = "";
                for (SysUserRole sysUserRole : sysUserRoleList) {
                    if (sysUserRole.getLoginname() == null) {
                        continue;
                    }
                    if ("".equals(account)) {
                        account += sysUserRole.getLoginname();
                        continue;
                    }
                    account += ";" + sysUserRole.getLoginname();
                }
                if ("".equals(account)) {
                    continue;
                }
                SysEmployeeSearchContext sysEmployeeSearchContext = new SysEmployeeSearchContext();
                String notAccount = notAccount();
                if (!"".equals(notAccount)) {
                    sysEmployeeSearchContext.setN_username_notin(notAccount);
                }
                sysEmployeeSearchContext.setN_username_in(account);
                sysEmployeeSearchContext.setSize(1000);
                Page<SysEmployee> page = iSysEmployeeService.searchDefault(sysEmployeeSearchContext);
                List<SysEmployee> list = page.getContent();
                for (SysEmployee sysEmployee : list) {
                    IbzDaily ibzDaily = new IbzDaily();
                    ibzDaily.setIbzdailyname(sysEmployee.getPersonname());
                    ibzDaily.setAccount(sysEmployee.getUsername());
                    ibzDaily.setDate(now);
                    ibzDaily.setIssubmit(StaticDict.YesNo.ITEM_0.getValue());
                    ibzDaily.setReportstatus(StaticDict.ReportStatus.ITEM_0.getValue());
                    this.create(getYesterdayPlans(ibzDaily));
                }
            }
        }
        return et;
    }

    /**
     * [GetYeaterdayDailyPlansTaskEdit:获取前一天日报计划参与任务（编辑）] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily getYeaterdayDailyPlansTaskEdit(IbzDaily et) {
        return super.getYeaterdayDailyPlansTaskEdit(et);
    }

    /**
     * [GetYesterdayDailyPlansTask:获取前一天日报计划参与任务（新建）] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily getYesterdayDailyPlansTask(IbzDaily et) {
        return super.getYesterdayDailyPlansTask(et);
    }

    /**
     * [HaveRead:已读] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily haveRead(IbzDaily et) {
        CachedBeanCopier.copy(get(et.getIbzdailyid()), et);
        String curAccount = AuthenticationUser.getAuthenticationUser().getUsername();
        if (curAccount == null) {
            return et;
        }
        boolean exits = curAccount.equals(et.getReportto()) || (et.getMailto() != null && et.getMailto().contains(curAccount));
        if (exits) {
            List<Action> list = iActionService.list(new QueryWrapper<Action>().eq("objecttype", StaticDict.Action__object_type.DAILY.getValue()).eq("action", StaticDict.Action__type.READ.getValue()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("objectid", et.getIbzdailyid()));
            if (list.size() == 0) {
                ActionHelper.createHis(et.getIbzdailyid(),StaticDict.Action__object_type.DAILY.getValue(),null,  StaticDict.Action__type.READ.getValue(), "", "", null, iActionService);
            }
        }
        return et;
    }

    /**
     * [LinkCompleteTask:关联完成任务] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily linkCompleteTask(IbzDaily et) {
        return super.linkCompleteTask(et);
    }

    /**
     * [PushUserDaily:定时推送待阅提醒用户日报] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily pushUserDaily(IbzDaily et) {
        List<IbzDaily> list = this.list(new QueryWrapper<IbzDaily>().eq("issubmit", StaticDict.YesNo.ITEM_0.getValue()).last(" and DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')"));
        for (IbzDaily ibzDaily : list) {
            ActionHelper.sendTodoOrToread(ibzDaily.getIbzdailyid(), "您的" + ibzDaily.getDate() + "的日报还未提交，请及时填写！", ibzDaily.getAccount(), "", "", "日报", StaticDict.Action__object_type.DAILY.getValue(), "ibzdailies", StaticDict.Action__type.REMIND.getText(), false, iActionService);
        }

        return et;
    }

    /**
     * [Submit:提交] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzDaily submit(IbzDaily et) {
        IbzDaily newDaily = new IbzDaily();
        newDaily.setIbzdailyid(et.getIbzdailyid());
        IbzDaily old = new IbzDaily();
        CachedBeanCopier.copy(get(et.getIbzdailyid()), old);
        newDaily.setSubmittime(ZTDateUtil.now());
        newDaily.setIssubmit(StaticDict.YesNo.ITEM_1.getValue());
        boolean flag = (old.getWorktoday() == null && old.getTodaytask() == null) || old.getReportto() == null;
        if (flag) {
            throw new RuntimeException("请填写今日工作或今日完成任务并且指定汇报人后提交！");
        }
        if (!update(newDaily, (Wrapper) newDaily.getUpdateWrapper(true).eq("Ibz_dailyid", newDaily.getIbzdailyid()))) {
            return newDaily;
        }
        CachedBeanCopier.copy(get(newDaily.getIbzdailyid()), newDaily);
        List<History> changes = ChangeUtil.diff(old, newDaily, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.SUBMIT.getValue();
             ActionHelper.createHis(newDaily.getIbzdailyid(),StaticDict.Action__object_type.DAILY.getValue(), changes, strAction,
                    "", "", null, iActionService);
        }
        // 给汇报人，抄送人 待阅
        String ss = "已经提交给您了，请查收哦！";
        ActionHelper.sendTodoOrToread(newDaily.getIbzdailyid(), newDaily.getIbzdailyname() + ss, "", newDaily.getReportto(), newDaily.getMailto(), "日报", StaticDict.Action__object_type.DAILY.getValue(),"ibzdailies", StaticDict.Action__type.SUBMIT.getText(),false,iActionService);
        return et;
    }

    //获取前一天的计划参与和明日工作
    public IbzDaily getYesterdayPlans(IbzDaily et) {
        List<IbzDaily> list = this.list(new QueryWrapper<IbzDaily>().eq("account", et.getAccount()).last(" and DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 day),'%Y-%m-%d')"));
        if (list.size() > 0) {
            IbzDaily yesterdayIbzDaily = list.get(0);
            et.setWorktoday(yesterdayIbzDaily.getPlanstomorrow());
            et.setTodaytask(yesterdayIbzDaily.getTomorrowplanstask());
        }
        return et;
    }

    //获取今天是星期几
    public static String getWeekOfDate(Timestamp timestamp) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public String notAccount() {
        String notaccount = "";
        List<IbzDaily> list = this.list(new QueryWrapper<IbzDaily>().last(" where DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')"));
        for (IbzDaily ibzDaily : list) {
            if ("".equals(notaccount)) {
                notaccount += ibzDaily.getAccount();
                continue;
            }
            notaccount += ";" + ibzDaily.getAccount();
        }

        return notaccount;
    }

    //判断任务状态是否时进行中，或者完成时间是今日
    public void removeSomeTasks(IbzDaily et) {
        String ids = et.getTodaytask();
        Timestamp date = et.getDate() == null ? ZTDateUtil.now() : et.getDate();
        List<Task> tasks = iTaskService.list(new QueryWrapper<Task>().last(" and id in (select task from zt_taskestimate where account = '" + et.getAccount() + "' and DATE_FORMAT( date, '%Y-%m-%d' ) = DATE_FORMAT( '" + date + "', '%Y-%m-%d' )) and FIND_IN_SET(id, '" + ids + "') and project in (select id from zt_project where deleted = '0')"));
        Set<String> taskIds = new HashSet<>();
        for (Task task : tasks) {
            taskIds.add(String.valueOf(task.getId()));
        }
        et.setTodaytask(Joiner.on(",").join(taskIds));
    }
}

