package cn.ibizlab.pms.core.ibiz.model.impl;

import cn.ibizlab.pms.core.ibiz.filter.IbzCaseSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzCaseService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IbzCaseModelImpl")
public class IbzCaseModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIbzCaseService ibzcaseService;

    public IbzCaseModelImpl(){
        this.entity = "IBZ_CASE";
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
        IbzCaseSearchContext context = new IbzCaseSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibzcaseService.searchDefault(context).getTotalElements() == 0) {
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
        IbzCaseSearchContext context = new IbzCaseSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibzcaseService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

