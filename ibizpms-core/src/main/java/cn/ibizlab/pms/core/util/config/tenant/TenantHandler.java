package cn.ibizlab.pms.core.util.config.tenant;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;


public class TenantHandler implements TenantLineHandler {

    @Autowired
    private TenantProperties tenantProperties;

    @Override
    public Expression getTenantId() {
        AuthenticationUser user = AuthenticationUser.getAuthenticationUser();
        return new StringValue(user.getSrfdcid());
    }

    @Override
    public String getTenantIdColumn() {
        return tenantProperties.getColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        tableName = tableName.replace("`","") ;
        if (tenantProperties.getSysTables().contains(tableName))
            return true;
        return false;
    }

}
