package cn.ibizlab.pms.core.zentao.model.impl;

import cn.ibizlab.pms.core.zentao.domain.Config;
import cn.ibizlab.pms.core.zentao.filter.ConfigSearchContext;
import cn.ibizlab.pms.core.zentao.service.IConfigService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("ConfigModelImpl")
public class ConfigModelImpl extends DataEntityModelImpl {


    @Autowired
    public IConfigService configService;

    public ConfigModelImpl(){
        this.entity = "ZT_CONFIG";
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
        ConfigSearchContext context = new ConfigSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        List<Config> domains = configService.searchDefault(context).getContent();
        if (domains.size() == 0) {
            return false;
        }
        try {
            return SpringContextHolder.getBean(cn.ibizlab.pms.core.zentao.runtime.ConfigRuntime.class).testDataAccessAction(domains.get(0),action);
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
        ConfigSearchContext context = new ConfigSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        List<Config> domains = configService.searchDefault(context).getContent();
        if (domains.size() != keys.size()) {
            return false;
        }
        try {
            for(Config domain : domains){
                if(SpringContextHolder.getBean(cn.ibizlab.pms.core.zentao.runtime.ConfigRuntime.class).testDataAccessAction(domain,action)){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}

