package cn.ibizlab.pms.core.ibizplugin.model.impl;

import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProPluginService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IBIZProPluginModelImpl")
public class IBIZProPluginModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIBIZProPluginService ibizpropluginService;

    public IBIZProPluginModelImpl(){
        this.entity = "IBIZPRO_PLUGIN";
        this.pkey = "T1.IBIZPRO_PLUGINID";
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
        IBIZProPluginSearchContext context = new IBIZProPluginSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibizpropluginService.searchDefault(context).getTotalElements() == 0) {
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
        IBIZProPluginSearchContext context = new IBIZProPluginSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibizpropluginService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

