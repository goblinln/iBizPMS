package cn.ibizlab.pms.core.ibizsysmodel.model.impl;

import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysRunSessionService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("PSSysRunSessionModelImpl")
public class PSSysRunSessionModelImpl extends DataEntityModelImpl {


    @Autowired
    public IPSSysRunSessionService pssysrunsessionService;

    public PSSysRunSessionModelImpl(){
        this.entity = "PSSYSRUNSESSION";
        this.pkey = "T1.PSSYSRUNSESSIONID";
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
        PSSysRunSessionSearchContext context = new PSSysRunSessionSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (pssysrunsessionService.searchDefault(context).getTotalElements() == 0) {
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
        PSSysRunSessionSearchContext context = new PSSysRunSessionSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (pssysrunsessionService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

