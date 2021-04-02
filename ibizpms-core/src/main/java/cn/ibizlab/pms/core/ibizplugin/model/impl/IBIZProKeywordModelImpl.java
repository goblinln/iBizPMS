package cn.ibizlab.pms.core.ibizplugin.model.impl;

import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProKeywordService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IBIZProKeywordModelImpl")
public class IBIZProKeywordModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIBIZProKeywordService ibizprokeywordService;

    public IBIZProKeywordModelImpl(){
        this.entity = "IBIZPRO_KEYWORD";
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
        IBIZProKeywordSearchContext context = new IBIZProKeywordSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibizprokeywordService.searchDefault(context).getTotalElements() == 0) {
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
        IBIZProKeywordSearchContext context = new IBIZProKeywordSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibizprokeywordService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

