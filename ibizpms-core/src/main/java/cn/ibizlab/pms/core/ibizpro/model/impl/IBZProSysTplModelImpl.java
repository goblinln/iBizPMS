package cn.ibizlab.pms.core.ibizpro.model.impl;

import cn.ibizlab.pms.core.ibizpro.filter.IBZProSysTplSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProSysTplService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IBZProSysTplModelImpl")
public class IBZProSysTplModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIBZProSysTplService ibzprosystplService;

    public IBZProSysTplModelImpl(){
        this.entity = "IBZPRO_SYSTPL";
        this.pkey = "T1.IBZPRO_SYSTPLID";
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
        IBZProSysTplSearchContext context = new IBZProSysTplSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibzprosystplService.searchDefault(context).getTotalElements() == 0) {
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
        IBZProSysTplSearchContext context = new IBZProSysTplSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibzprosystplService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

