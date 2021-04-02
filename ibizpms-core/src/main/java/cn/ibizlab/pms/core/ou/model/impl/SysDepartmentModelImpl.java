package cn.ibizlab.pms.core.ou.model.impl;

import cn.ibizlab.pms.core.ou.filter.SysDepartmentSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysDepartmentService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("SysDepartmentModelImpl")
public class SysDepartmentModelImpl extends DataEntityModelImpl {


    @Autowired
    public ISysDepartmentService sysdepartmentService;

    public SysDepartmentModelImpl(){
        this.entity = "SYS_DEPT";
        this.pkey = "T1.DEPTID";
        this.orgDRField = "T1.ORGID" ;
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
        SysDepartmentSearchContext context = new SysDepartmentSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (sysdepartmentService.searchDefault(context).getTotalElements() == 0) {
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
        SysDepartmentSearchContext context = new SysDepartmentSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (sysdepartmentService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

