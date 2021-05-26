package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.impl.SysEmployeeServiceImpl;
import cn.ibizlab.pms.core.zentao.domain.ProjectProduct;
import cn.ibizlab.pms.core.zentao.domain.Team;
import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.domain.UserContact;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Primary
@Service("SysEmployeeExService")
public class SysEmployeeExService extends SysEmployeeServiceImpl {

//    @Autowired
//    TeamHelper teamHelper;

    @Autowired
    ITaskService iTaskService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IUserContactService userContactService;

    @Autowired
    IProjectProductService projectProductService;

    @Autowired
    ITeamService iTeamService;

    @Override
    public Page<SysEmployee> searchDefault(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchDefault");
        try {
            context.setN_orgid_eq(AuthenticationUser.getAuthenticationUser().getOrgid());
            return super.searchDefault(context);
        }catch(Exception e) {
            List<SysEmployee> list = new ArrayList<>();
            SysEmployee sysEmployee = new SysEmployee();
            sysEmployee.setPersonname(AuthenticationUser.getAuthenticationUser().getPersonname());
            sysEmployee.setUsername(AuthenticationUser.getAuthenticationUser().getUsername());
            list.add(sysEmployee);
            Page<SysEmployee> page = new PageImpl<SysEmployee>(list);
            return page;
        }

    }

    @Override
    public Page<SysEmployee> searchBugUser(SysEmployeeSearchContext context) {
        Map<String,Object> params = context.getParams();
        log.info("SysEmployeeExService：searchBugUser");
        if(params.get("project") != null && !"0".equals(params.get("project"))) {
            // 项目团队
            log.info("SysEmployeeExService：searchBugUser-" + params.get("project").toString());
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
        }
        Page<SysEmployee> page = this.searchDefault(context);
        return page;
    }

    @Override
    public Page<SysEmployee> searchProjectTeamM(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchProjectTeamM");
        Map<String,Object> params = context.getParams();
        if(params.get("root") != null && !"0".equals(params.get("root"))) {
            // 项目团队
            context.setN_username_notin(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("root"), params.get("account") != null ?  params.get("account").toString() : null));

        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchStoryProductTeamPK(SysEmployeeSearchContext context){
        log.info("SysEmployeeExService：searchStoryProductTeamPK");
        Map<String,Object> params = context.getParams();
        if (params.get("product") != null && !"0".equals(params.get("product"))){
            //产品团队
            context.setN_username_in(getAccounts(StaticDict.Team__type.PRODUCT.getValue(),params.get("product")));
        }
        Page<SysEmployee> productSysPage = this.searchDefault(context);
        if (productSysPage.getContent().size() == 0){
            //产品团队没有人时，默认所有人员
            return this.searchDefault(new SysEmployeeSearchContext());
        }
        else {
            return productSysPage;
        }
    }

    @Override
    public Page<SysEmployee> searchProjectTeamMProduct(SysEmployeeSearchContext context){
        log.info("SysEmployeeExService：searchProjectTeamMProduct");
        Map<String,Object> params = context.getParams();
        if (params.get("root") != null && !"0".equals(params.get("root"))){
            String account = "";
            //项目团队，先去获取关联产品的产品团队成员
            List<ProjectProduct> list = projectProductService.list(new QueryWrapper<ProjectProduct>().eq("project",params.get("root")));
            for (int i = 0; i <list.size() ; i++) {
                if (account.length() > 0){
                    account += ",";
                }
                account += getAccounts(StaticDict.Team__type.PRODUCT.getValue(),list.get(i).getProduct());
            }
            if (!"".equals(account)){
                context.setN_username_in(account);
            }
//            context.setN_username_notin(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("root"), params.get("account") != null ?  params.get("account").toString() : null));
        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchProductTeamM(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchProductTeamM");
        Map<String,Object> params = context.getParams();
        if(params.get("root") != null && !"0".equals(params.get("root"))) {
            // 产品团队
            context.setN_username_notin(getAccounts(StaticDict.Team__type.PRODUCT.getValue(), params.get("root"), params.get("account") != null ?  params.get("account").toString() : null));

        }
        return this.searchDefault(context);
    }
    @Override
    public Page<SysEmployee> searchProjectTeamUser(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchProjectTeamUser");
        Map<String,Object> params = context.getParams();
        if(params.get("project") != null && !"0".equals(params.get("project"))) {
            // 项目团队
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
            if(params.get("account") != null) {
                context.setN_username_notin(params.get("account").toString().replace(",",";"));
            }

        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchProjectTeamUserTask(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchProjectTeamUserTask");
        Map<String,Object> params = context.getParams();
        if(params.get("srfparentkey") != null && !"0".equals(params.get("srfparentkey"))) {
            // 项目团队
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), iTaskService.get(Long.parseLong(params.get("srfparentkey").toString())).getProject()));
            if(params.get("account") != null) {
                context.setN_username_notin(params.get("account").toString().replace(",",";"));
            }
        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchProjectteamPk(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchProjectteamPk");
        Map<String,Object> params = context.getParams();
        if(params.get("srfparentkey") != null && !"0".equals(params.get("srfparentkey"))) {
            // 项目团队
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("srfparentkey")));
        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchTaskMTeam(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchTaskTeam");
        Map<String,Object> params = context.getParams();
        if((params.get("multiple") == null && params.get("project") != null) || (params.get("multiple") != null && "0".equals(params.get("multiple")))) {
            // 项目团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("project").toString());
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
        }else {
            // 任务团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("id").toString());
            if(iTeamService.list(new QueryWrapper<Team>().eq("type", StaticDict.Team__type.TASK.getValue()).gt("`left`", 0).notIn("account", AuthenticationUser.getAuthenticationUser().getLoginname()).eq("root", params.get("id").toString())).size() == 0) {
                context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
         }else {
                context.setN_username_in(getAccounts(StaticDict.Team__type.TASK.getValue(), params.get("id")));
            }
        }
        return this.searchDefault(context);
    }

    /**
     * 查询集合 联系人用户
     */
    @Override
    public Page<SysEmployee> searchContActList(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchContActList");
        Map<String,Object> params = context.getParams();
        if(params.get("srfparentkey") != null && !"0".equals(params.get("srfparentkey"))) {
            // 联系人
            UserContact userContact = userContactService.get(Long.parseLong(params.get("srfparentkey").toString()));
            context.setN_username_in(userContact.getUserlist().replaceAll(",", ";"));
        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchTaskTeam(SysEmployeeSearchContext context) {
        log.info("SysEmployeeExService：searchTaskTeam");
        Map<String,Object> params = context.getParams();
        if((params.get("multiple") == null && params.get("project") != null) || (params.get("multiple") != null && "0".equals(params.get("multiple")))) {
            // 项目团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("project").toString());
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
        }else {
            // 任务团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("id").toString());
            context.setN_username_in(getAccounts(StaticDict.Team__type.TASK.getValue(), params.get("id")));
        }
        return this.searchDefault(context);
    }

    @Override
    public Page<SysEmployee> searchProjectTeamTaskUserTemp(SysEmployeeSearchContext context){
        log.info("SysEmployeeExService：searchProjectTeamTaskUserTemp");
        Map<String,Object> params = context.getParams();
        if((params.get("multiple") == null && params.get("project") != null) || (params.get("multiple") != null && "0".equals(params.get("multiple")))) {
            // 项目团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("project").toString());
            context.setN_username_in(getAccounts(StaticDict.Team__type.PROJECT.getValue(), params.get("project")));
        }else {
            // 任务团队
            log.info("SysEmployeeExService：SysEmployeeExService-" + params.get("id").toString());
            context.setN_username_in(params.get("allTeamAccount").toString().replace(",",";"));
        }
        return this.searchDefault(context);
    }

    public String getAccounts(String type, Object root) {
        String accounts = "";
        List<Team> list = iTeamService.list(new QueryWrapper<Team>().eq("type", type).eq("root", root));
        for(Team team : list) {
            if(!"".equals(accounts)) {
                accounts += ";";
            }
            accounts += team.getAccount();
        }
        return accounts;
    }

    public String getAccounts(String type, Object root, String account) {
        String accounts = "";
        List<Team> list = iTeamService.list(new QueryWrapper<Team>().eq("type", type).eq("root", root));
        for(Team team : list) {
            if(account == null || !team.getAccount().equals(account)) {
                if (!"".equals(accounts)) {
                    accounts += ";";
                }
                accounts += team.getAccount();
            }
        }
        return accounts;
    }
}
