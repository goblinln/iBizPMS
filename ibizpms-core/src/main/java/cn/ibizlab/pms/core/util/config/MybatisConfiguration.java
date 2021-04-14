package cn.ibizlab.pms.core.util.config;

import cn.ibizlab.pms.util.helper.UniqueNameGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import java.util.Properties;
import cn.ibizlab.pms.core.util.config.tenant.TenantHandler;
import cn.ibizlab.pms.core.util.config.tenant.TenantInterceptor;
import cn.ibizlab.pms.core.util.config.tenant.TenantProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;

/**
 * mybatis全局配置类
 */
@Configuration
@MapperScan(value="cn.ibizlab.pms.core.*.mapper",nameGenerator = UniqueNameGenerator.class)
@EnableConfigurationProperties(TenantProperties.class)
public class MybatisConfiguration {

    /**
     * mybatis适配多数据库
     * @return
     */
    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("MySQL", "mysql");
        p.setProperty("PostgreSQL", "postgresql");
        p.setProperty("DM", "oracle");//达梦数据库使用oracle模式
        p.setProperty("H2", "mysql");//根据当前运行的数据库设置h2对应的databaseid
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        return paginationInnerInterceptor;
    }

    @Bean
    public TenantHandler tenantHandler(){
        return new TenantHandler();
    }

    @Bean
    public TenantInterceptor tenantInterceptor(){
        TenantInterceptor tenantInterceptor = new TenantInterceptor();
        tenantInterceptor.setTenantLineHandler(tenantHandler());
        return tenantInterceptor;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //租户
        interceptor.addInnerInterceptor(tenantInterceptor());
        //分页
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        return interceptor;
    }



    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

}
