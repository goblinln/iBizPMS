package cn.ibizlab.pms.core.ibiz.model.impl;

import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("SysUpdateLogModelImpl")
public class SysUpdateLogModelImpl extends DataEntityModelImpl {


    @Autowired
    public ISysUpdateLogService sysupdatelogService;

    public SysUpdateLogModelImpl(){
        this.entity = "SYS_UPDATE_LOG";
        this.pkey = "T1.SYS_UPDATE_LOGID";
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
        SysUpdateLogSearchContext context = new SysUpdateLogSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (sysupdatelogService.searchDefault(context).getTotalElements() == 0) {
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
        SysUpdateLogSearchContext context = new SysUpdateLogSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (sysupdatelogService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

