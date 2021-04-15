package cn.ibizlab.pms.webapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import cn.ibizlab.pms.util.web.SearchContextHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Slf4j
@EnableDiscoveryClient
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"cn.ibizlab.pms"}
//        ,excludeFilters={
//                @ComponentScan.Filter(type= org.springframework.context.annotation.FilterType.REGEX,pattern="cn.ibizlab.pms.webapi.rest.xxx"),
//        }
)
@EnableMongoRepositories(basePackages = {"cn.ibizlab.pms"})
@MapperScan("cn.ibizlab.pms.*.mapper")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
})
@Import({
        org.springframework.cloud.openfeign.FeignClientsConfiguration.class
})
@EnableFeignClients(basePackages = {"cn.ibizlab.pms" })
@EnableAsync
@EnableScheduling
public class iBizPMSWebApiApplication extends WebMvcConfigurerAdapter{

    @Autowired
    SearchContextHandlerMethodArgumentResolver resolver;

    public static void main(String[] args) {
        SpringApplication.run(iBizPMSWebApiApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(resolver);
    }
}
