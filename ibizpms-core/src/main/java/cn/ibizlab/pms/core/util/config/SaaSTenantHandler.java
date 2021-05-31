package cn.ibizlab.pms.core.util.config;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@EnableConfigurationProperties(SaaSTenantProperties.class)
public class SaaSTenantHandler implements TenantHandler {

    @Autowired
    private SaaSTenantProperties saaSTenantProperties;

    @Override
    public Expression getTenantId(boolean where) {
        AuthenticationUser user = AuthenticationUser.getAuthenticationUser();
        return new StringValue(user.getSrfdcid());
    }

    @Override
    public String getTenantIdColumn() {
        return saaSTenantProperties.getColumn();
    }

    @Override
    public boolean doTableFilter(String tableName) {
        String strTableName = tableName.replace("`","") ;
        if (saaSTenantProperties.getSysTables().stream().anyMatch(table -> table.equalsIgnoreCase(strTableName)))
            return true;
        return false;
    }

}
