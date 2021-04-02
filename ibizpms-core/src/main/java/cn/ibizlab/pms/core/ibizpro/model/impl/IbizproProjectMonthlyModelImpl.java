package cn.ibizlab.pms.core.ibizpro.model.impl;

import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectMonthlyService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IbizproProjectMonthlyModelImpl")
public class IbizproProjectMonthlyModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIbizproProjectMonthlyService ibizproprojectmonthlyService;

    public IbizproProjectMonthlyModelImpl(){
        this.entity = "IBIZPRO_PROJECTMONTHLY";
        this.pkey = "T1.IBIZPRO_PROJECTMONTHLYID";
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
        IbizproProjectMonthlySearchContext context = new IbizproProjectMonthlySearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibizproprojectmonthlyService.searchDefault(context).getTotalElements() == 0) {
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
        IbizproProjectMonthlySearchContext context = new IbizproProjectMonthlySearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibizproprojectmonthlyService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

