package cn.ibizlab.pms.core.ibiz.model.impl;

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("DocLibModuleModelImpl")
public class DocLibModuleModelImpl extends DataEntityModelImpl {


    @Autowired
    public IDocLibModuleService doclibmoduleService;

    public DocLibModuleModelImpl(){
        this.entity = "IBZ_DOCLIBMODULE";
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
        DocLibModuleSearchContext context = new DocLibModuleSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        List<DocLibModule> domains = doclibmoduleService.searchDefault(context).getContent();
        if (domains.size() == 0) {
            return false;
        }

        try {
            return SpringContextHolder.getBean(cn.ibizlab.pms.core.ibiz.runtime.DocLibModuleRuntime.class).testDataAccessAction(domains.get(0),action);
        } catch (Exception e) {
            return false;
        }
        
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
        DocLibModuleSearchContext context = new DocLibModuleSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        List<DocLibModule> domains = doclibmoduleService.searchDefault(context).getContent();
        if (domains.size() != keys.size()) {
            return false;
        }

        try {
            for(DocLibModule domain : domains){
                if(SpringContextHolder.getBean(cn.ibizlab.pms.core.ibiz.runtime.DocLibModuleRuntime.class).testDataAccessAction(domain,action)){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}

