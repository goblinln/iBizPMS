package cn.ibizlab.pms.core.ou.model.impl;

import cn.ibizlab.pms.core.ou.filter.SysPostSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysPostService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("SysPostModelImpl")
public class SysPostModelImpl extends DataEntityModelImpl {


    @Autowired
    public ISysPostService syspostService;

    public SysPostModelImpl(){
        this.entity = "SYS_POST";
        this.pkey = "T1.POSTID";
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
        SysPostSearchContext context = new SysPostSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (syspostService.searchDefault(context).getTotalElements() == 0) {
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
        SysPostSearchContext context = new SysPostSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (syspostService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

