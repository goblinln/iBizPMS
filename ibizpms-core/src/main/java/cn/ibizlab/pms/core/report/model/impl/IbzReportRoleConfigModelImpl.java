package cn.ibizlab.pms.core.report.model.impl;

import cn.ibizlab.pms.core.report.filter.IbzReportRoleConfigSearchContext;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("IbzReportRoleConfigModelImpl")
public class IbzReportRoleConfigModelImpl extends DataEntityModelImpl {


    @Autowired
    public IIbzReportRoleConfigService ibzreportroleconfigService;

    public IbzReportRoleConfigModelImpl(){
        this.entity = "IBZ_REPORT_ROLE_CONFIG";
        this.pkey = "T1.IBZ_REPORT_ROLE_CONFIGID";
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
        IbzReportRoleConfigSearchContext context = new IbzReportRoleConfigSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (ibzreportroleconfigService.searchDefault(context).getTotalElements() == 0) {
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
        IbzReportRoleConfigSearchContext context = new IbzReportRoleConfigSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (ibzreportroleconfigService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

