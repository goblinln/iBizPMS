package cn.ibizlab.pms.core.ibizpro.model.impl;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IbizproProjectDailyModelImpl")
public class IbizproProjectDailyModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIbizproProjectDailyService ibizproprojectdailyService;

    public IbizproProjectDailyModelImpl(){
        this.entity = "IBIZPRO_PROJECTDAILY";
        this.pkey = "T1.IBIZPRO_PROJECTDAILYID";
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
        IbizproProjectDailySearchContext context = new IbizproProjectDailySearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        List<IbizproProjectDaily> domains = ibizproprojectdailyService.searchDefault(context).getContent();
        if (domains.size() == 0) {
            return false;
        }
        try {
            return SpringContextHolder.getBean(cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectDailyRuntime.class).testDataAccessAction(domains.get(0),action);
        } catch (Exception e) {
            return false;
        }
        
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
        IbizproProjectDailySearchContext context = new IbizproProjectDailySearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        List<IbizproProjectDaily> domains = ibizproprojectdailyService.searchDefault(context).getContent();
        if (domains.size() != keys.size()) {
            return false;
        }
        try {
            for(IbizproProjectDaily domain : domains){
                if(SpringContextHolder.getBean(cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectDailyRuntime.class).testDataAccessAction(domain,action)){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}

