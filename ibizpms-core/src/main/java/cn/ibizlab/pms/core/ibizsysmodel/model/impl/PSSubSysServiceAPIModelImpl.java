package cn.ibizlab.pms.core.ibizsysmodel.model.impl;

import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysServiceAPISearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSubSysServiceAPIService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("PSSubSysServiceAPIModelImpl")
public class PSSubSysServiceAPIModelImpl extends DataEntityModelImpl {


    @Autowired
    public IPSSubSysServiceAPIService pssubsysserviceapiService;

    public PSSubSysServiceAPIModelImpl(){
        this.entity = "PSSUBSYSSERVICEAPI";
        this.pkey = "T1.PSSUBSYSSERVICEAPIID";
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
        PSSubSysServiceAPISearchContext context = new PSSubSysServiceAPISearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (pssubsysserviceapiService.searchDefault(context).getTotalElements() == 0) {
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
        PSSubSysServiceAPISearchContext context = new PSSubSysServiceAPISearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (pssubsysserviceapiService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

