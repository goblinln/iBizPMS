package cn.ibizlab.pms.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

@Slf4j
@Import({
    com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration.class,
    FeignClientsConfiguration.class
})
@EnableDiscoveryClient
@Configuration
@EnableFeignClients(basePackages = {"cn.ibizlab.pms" })
@EnableZuulProxy
@ComponentScan(basePackages = {"cn.ibizlab.pms.web","cn.ibizlab.pms.util"})
@MapperScan("cn.ibizlab.pms.*.mapper")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
})
public class WebApplication extends WebMvcConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new cn.ibizlab.pms.util.web.SearchContextHandlerMethodArgumentResolver());
    }
}
