package cn.ibizlab.pms.core.ou.model.impl;

import cn.ibizlab.pms.core.ou.filter.SysTeamSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysTeamService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("SysTeamModelImpl")
public class SysTeamModelImpl extends DataEntityModelImpl {


    @Autowired
    public ISysTeamService systeamService;

    public SysTeamModelImpl(){
        this.entity = "SYS_TEAM";
        this.pkey = "T1.TEAMID";
        this.orgDRField = "" ;
        this.deptDRField = "";
    }

    @Override
    public boolean test(Serializable key, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return true;
            
        //检查能力
        if(!test(action))
            return false ;

        //检查数据范围
        SysTeamSearchContext context = new SysTeamSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (systeamService.searchDefault(context).getTotalElements() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean test(List<Serializable> keys, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return true;

        //检查能力
        if(!test(action))
            return false ;

        //检查数据范围
        SysTeamSearchContext context = new SysTeamSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (systeamService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

