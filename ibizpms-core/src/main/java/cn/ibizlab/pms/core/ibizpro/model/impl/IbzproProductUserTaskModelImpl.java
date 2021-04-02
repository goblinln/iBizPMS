package cn.ibizlab.pms.core.ibizpro.model.impl;

import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProductUserTaskService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IbzproProductUserTaskModelImpl")
public class IbzproProductUserTaskModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIbzproProductUserTaskService ibzproproductusertaskService;

    public IbzproProductUserTaskModelImpl(){
        this.entity = "IBIZPRO_PRODUCTUSERTASK";
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
        IbzproProductUserTaskSearchContext context = new IbzproProductUserTaskSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibzproproductusertaskService.searchDefault(context).getTotalElements() == 0) {
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
        IbzproProductUserTaskSearchContext context = new IbzproProductUserTaskSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibzproproductusertaskService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

