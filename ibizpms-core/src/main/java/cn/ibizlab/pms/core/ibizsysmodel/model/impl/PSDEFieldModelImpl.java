package cn.ibizlab.pms.core.ibizsysmodel.model.impl;

import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSDEFieldService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("PSDEFieldModelImpl")
public class PSDEFieldModelImpl extends DataEntityModelImpl {


    @Autowired
    public IPSDEFieldService psdefieldService;

    public PSDEFieldModelImpl(){
        this.entity = "PSDEFIELD";
        this.pkey = "T1.PSDEFIELDID";
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
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (psdefieldService.searchDefault(context).getTotalElements() == 0) {
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
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (psdefieldService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

