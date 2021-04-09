package cn.ibizlab.pms.core.util.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import cn.ibizlab.pms.util.helper.UniqueNameGenerator;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Properties;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import org.apache.ibatis.mapping.MappedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis全局配置类
 */
@Configuration
@MapperScan(value="cn.ibizlab.pms.core.*.mapper",nameGenerator = UniqueNameGenerator.class)
public class MybatisConfiguration {
    @Autowired
    private cn.ibizlab.pms.core.util.config.SaaSTenantProperties saaSTenantProperties;

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

    /**
     * mybatis-plus分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(SaaSTenantHandler saaSTenantHandler) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(-1);

         // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 创建租户SQL解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        // 设置租户处理器
        tenantSqlParser.setTenantHandler(saaSTenantHandler);
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        
        // 设置租户忽略
        paginationInterceptor.setSqlParserFilter(ignoreParserFilter());

        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    
    @Bean
    public ISqlParserFilter ignoreParserFilter() {
        return metaObject -> {
            // 此处就过滤
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            if (saaSTenantProperties.getIgnoreMappers().contains(ms.getId())) {
                return true;
            }
            return false;
        };
    }

}