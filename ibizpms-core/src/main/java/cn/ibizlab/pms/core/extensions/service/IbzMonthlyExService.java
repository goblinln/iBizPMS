package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.report.service.impl.IbzMonthlyServiceImpl;
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import cn.ibizlab.pms.core.uaa.service.ISysUserRoleService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
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
 * 实体[月报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzMonthlyExService")
public class IbzMonthlyExService extends IbzMonthlyServiceImpl {
    @Autowired
    ITaskService iTaskService;
    @Autowired
    IActionService iActionService;
    @Autowired
    ISysEmployeeService iSysEmployeeService;
    @Autowired
    IIbzReportRoleConfigService iIbzReportRoleConfigService;
    @Autowired
    ISysUserRoleService iSysUserRoleService;
    @Autowired
    IFileService iFileService;

    String[] diffAttrs = {"workthismonth", "comment", "plansnextmonth"};

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(IbzMonthly et) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        List<IbzMonthly> monthlyList = this.list(new QueryWrapper<IbzMonthly>().eq("account", et.getAccount()).last(" and DATE_FORMAT(date,'%Y-%m') = DATE_FORMAT('" + et.getDate() + "','%Y-%m')"));
        if (monthlyList.size() > 0) {
            throw new BadRequestAlertException("您的" + dateFormat.format(et.getDate()) + "的月报已经存在，请勿重复创建！", StaticDict.ReportType.MONTHLY.getValue(), "");
        }

        et.setIbzmonthlyname(String.format("%1$s-%2$s的月报", et.getIbzmonthlyname(), dateFormat.format(et.getDate())));
        String files = et.getFiles();
        filterNCorrectTasks(et);
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        FileHelper.updateObjectID(et.getIbzmonthlyid(), StaticDict.File__object_type.MONTHLY.getValue(), files, "", iFileService);
        ActionHelper.createHis(et.getIbzmonthlyid(), StaticDict.Action__object_type.MONTHLY.getValue(), null, StaticDict.Action__type.OPENED.getValue(), "", "", null, iActionService);
        return true;

    }

    @Override
    public boolean update(IbzMonthly et) {
        IbzMonthly old = new IbzMonthly();
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), old);
        String files = et.getFiles();
        filterNCorrectTasks(et);
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_monthlyid", et.getIbzmonthlyid()))) {
            return false;
        }

        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);

        FileHelper.updateObjectID(et.getIbzmonthlyid(), StaticDict.File__object_type.MONTHLY.getValue(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            ActionHelper.createHis(et.getIbzmonthlyid(), StaticDict.Action__object_type.MONTHLY.getValue(), changes, strAction,
                    "", "", null, iActionService);
        }
        return true;

    }

    /**
     * [CreateGetInfo:新建时获取信息] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly createGetInfo(IbzMonthly et) {
        et.setDate(ZTDateUtil.now());
        et.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        return getThisMonthlyCompleteTasks(getLastMonthlyPlans(et));
    }

    /**
     * [CreateUserMonthly:定时生成用户月报] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly createUserMonthly(IbzMonthly et) {
        // 月报角色 遍历角色
        List<IbzReportRoleConfig> iIbzReportRoleConfigList = iIbzReportRoleConfigService.list(new QueryWrapper<IbzReportRoleConfig>().eq("type", StaticDict.ReportType.MONTHLY.getValue()).orderByDesc("updatedate"));

        if (iIbzReportRoleConfigList.size() == 0 || iIbzReportRoleConfigList.get(0).getReportRole() == null) {
            return et;
        }
        String[] roleIds = iIbzReportRoleConfigList.get(0).getReportRole().split(",");

        for (String roleId : roleIds) {
            SysUserRoleSearchContext sysUserRoleSearchContext = new SysUserRoleSearchContext();
            sysUserRoleSearchContext.setN_sys_roleid_eq(roleId);
            sysUserRoleSearchContext.setSize(1000);
            Page<SysUserRole> userRolePage = iSysUserRoleService.searchDefault(sysUserRoleSearchContext);
            List<SysUserRole> userRoleList = userRolePage.getContent();
            if (userRoleList.size() == 0) {
                continue;
            }
            String account = "";
            for (SysUserRole sysUserRole : userRoleList) {
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
            Timestamp now = ZTDateUtil.now();
            for (SysEmployee sysEmployee : list) {
                IbzMonthly ibzMonthly = new IbzMonthly();
                ibzMonthly.setIbzmonthlyname(sysEmployee.getPersonname());
                ibzMonthly.setAccount(sysEmployee.getUsername());
                ibzMonthly.setDate(now);
                ibzMonthly.setIssubmit(StaticDict.YesNo.ITEM_0.getValue());
                ibzMonthly.setReportstatus(StaticDict.ReportStatus.ITEM_0.getValue());
                this.create(filterNCorrectTasks(getLastMonthlyPlans(ibzMonthly)));
            }
        }
        return et;
    }

    /**
     * [EditGetCompleteTask:编辑时获取完成任务] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly editGetCompleteTask(IbzMonthly et) {
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        return getThisMonthlyCompleteTasks(et);
    }

    /**
     * [HaveRead:已读] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly haveRead(IbzMonthly et) {
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        List<Action> list = iActionService.list(new QueryWrapper<Action>().eq("objecttype", StaticDict.Action__object_type.MONTHLY.getValue()).eq("action", StaticDict.Action__type.READ.getValue()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("objectid", et.getIbzmonthlyid()));
        if (list.size() == 0) {
            ActionHelper.createHis(et.getIbzmonthlyid(), StaticDict.Action__object_type.MONTHLY.getValue(), null, StaticDict.Action__type.READ.getValue(), "", "", null, iActionService);
        }
        return et;
    }

    /**
     * [PushUserMonthly:定时推送待阅提醒用户月报] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly pushUserMonthly(IbzMonthly et) {
        List<IbzMonthly> list = this.list(new QueryWrapper<IbzMonthly>().last(" where issubmit = '0' and DATE_FORMAT(date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m')"));
        for (IbzMonthly ibzMonthly : list) {
            ActionHelper.sendTodoOrToread(ibzMonthly.getIbzmonthlyid(), "您的" + ibzMonthly.getIbzmonthlyname() + "的月报还未提交，请及时填写！", ibzMonthly.getAccount(), "", "", "月报", StaticDict.Action__object_type.MONTHLY.getValue(), "ibzmonthlies", StaticDict.Action__type.REMIND.getText(), false, iActionService);
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
    public IbzMonthly submit(IbzMonthly et) {
        IbzMonthly newMonthly = new IbzMonthly();
        newMonthly.setIbzmonthlyid(et.getIbzmonthlyid());
        IbzMonthly old = new IbzMonthly();
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), old);
        newMonthly.setIssubmit(StaticDict.YesNo.ITEM_1.getValue());
        newMonthly.setSubmittime(ZTDateUtil.now());
        if ((old.getThismonthtask() == null && old.getWorkthismonth() == null) || old.getReportto() == null) {
            throw new RuntimeException("请填写本月工作或本月完成任务并且指定汇报人后提交！");
        }
        if (!update(newMonthly, (Wrapper) newMonthly.getUpdateWrapper(true).eq("Ibz_monthlyid", newMonthly.getIbzmonthlyid()))) {
            return newMonthly;
        }

        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), newMonthly);
        String strAction = StaticDict.Action__type.SUBMIT.getValue();
        ActionHelper.createHis(newMonthly.getIbzmonthlyid(), StaticDict.Action__object_type.MONTHLY.getValue(), null, strAction,
                "", "", null, iActionService);

        // 给汇报人，抄送人 发送待阅
        String ss = "已经提交给您了，请查收哦！";
        ActionHelper.sendTodoOrToread(newMonthly.getIbzmonthlyid(), newMonthly.getIbzmonthlyname() + ss, "", newMonthly.getReportto(), newMonthly.getMailto(), "月报", StaticDict.Action__object_type.MONTHLY.getValue(), "ibzmonthlies", StaticDict.Action__type.SUBMIT.getText(), false, iActionService);
        return et;
    }

    private String notAccount() {
        String notaccount = "";
        List<IbzMonthly> list = this.list(new QueryWrapper<IbzMonthly>().last(" where DATE_FORMAT(date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m')"));
        for (IbzMonthly ibzMonthly : list) {
            if ("".equals(notaccount)) {
                notaccount += ibzMonthly.getAccount();
                continue;
            }
            notaccount += ";" + ibzMonthly.getAccount();
        }

        return notaccount;
    }

    // 获取上个月计划本月完成的任务
    public IbzMonthly getLastMonthlyPlans(IbzMonthly et) {
        List<IbzMonthly> list = this.list(new QueryWrapper<IbzMonthly>().eq("account", et.getAccount()).last(" and DATE_FORMAT(date,'%Y-%m') = DATE_FORMAT(DATE_SUB('" + et.getDate() + "', INTERVAL 1 MONTH),'%Y-%m')"));
        if (list.size() > 0) {
            IbzMonthly last = list.get(0);
            et.setThismonthtask(last.getNextmonthplanstask());
            et.setWorkthismonth(last.getPlansnextmonth());
        }
        return et;
    }

    // 获取本月完成的任务
    public IbzMonthly getThisMonthlyCompleteTasks(IbzMonthly et) {
        List<Task> list = iTaskService.list(new QueryWrapper<Task>().eq("finishedBy", et.getAccount()).last(" and DATE_FORMAT(finishedDate,'%Y-%m') = DATE_FORMAT('" + et.getDate() + "','%Y-%m')"));
        String taskIds = et.getThismonthtask() == null ? "" : et.getThismonthtask();

        Set<String> taskIdSet = new HashSet<String>(Arrays.asList(taskIds.split(",")));
        for (Task task : list) {
            taskIdSet.add(task.getId().toString());
        }

        et.setThismonthtask(Joiner.on(",").join(taskIdSet));
        return et;
    }

    // 过滤任务，只保留参与过的任务
    public IbzMonthly filterNCorrectTasks(IbzMonthly et) {
        if (et.getThismonthtask() == null) {
            return et;
        }
        String taskIds = et.getThismonthtask();
        List<Task> list = iTaskService.list(new QueryWrapper<Task>().last(" and id in (select task from zt_taskestimate where account = '" + et.getAccount() + "' and DATE_FORMAT( date, '%Y-%m' ) = DATE_FORMAT( '" + et.getDate() + "', '%Y-%m' )) and FIND_IN_SET(id, '" + taskIds + "') and project in (select id from zt_project where deleted = '0')"));
        taskIds = "";
        for (Task task : list) {
            if (!"".equals(taskIds)) {
                taskIds += ",";
            }
            taskIds += task.getId();
        }
        et.setThismonthtask(taskIds);
        return et;
    }
}

