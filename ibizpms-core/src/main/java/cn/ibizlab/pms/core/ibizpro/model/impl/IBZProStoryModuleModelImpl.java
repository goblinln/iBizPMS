package cn.ibizlab.pms.core.ibizpro.model.impl;

import cn.ibizlab.pms.core.ibizpro.filter.IBZProStoryModuleSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryModuleService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IBZProStoryModuleModelImpl")
public class IBZProStoryModuleModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIBZProStoryModuleService ibzprostorymoduleService;

    public IBZProStoryModuleModelImpl(){
        this.entity = "IBZPRO_STORYMODULE";
        this.pkey = "T1.ID";
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
        IBZProStoryModuleSearchContext context = new IBZProStoryModuleSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        List<IBZProStoryModule> domains = ibzprostorymoduleService.searchDefault(context).getContent();
        if (domains.size() == 0) {
            return false;
        }

        try {
            return SpringContextHolder.getBean(cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryModuleRuntime.class).testDataAccessAction(domains.get(0),action);
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
        IBZProStoryModuleSearchContext context = new IBZProStoryModuleSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        List<IBZProStoryModule> domains = ibzprostorymoduleService.searchDefault(context).getContent();
        if (domains.size() != keys.size()) {
            return false;
        }

        try {
            for(IBZProStoryModule domain : domains){
                if(SpringContextHolder.getBean(cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryModuleRuntime.class).testDataAccessAction(domain,action)){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}

