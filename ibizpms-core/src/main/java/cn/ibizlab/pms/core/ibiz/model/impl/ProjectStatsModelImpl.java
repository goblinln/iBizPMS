package cn.ibizlab.pms.core.ibiz.model.impl;

import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("ProjectStatsModelImpl")
public class ProjectStatsModelImpl extends DataEntityModelImpl {


    @Autowired
    public IProjectStatsService projectstatsService;

    public ProjectStatsModelImpl(){
        this.entity = "IBZ_PROJECTSTATS";
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
        ProjectStatsSearchContext context = new ProjectStatsSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (projectstatsService.searchDefault(context).getTotalElements() == 0) {
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
        ProjectStatsSearchContext context = new ProjectStatsSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (projectstatsService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

