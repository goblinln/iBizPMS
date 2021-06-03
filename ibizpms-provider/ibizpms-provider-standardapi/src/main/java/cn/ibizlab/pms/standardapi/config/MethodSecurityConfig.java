package cn.ibizlab.pms.standardapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected org.springframework.security.access.expression.method.MethodSecurityExpressionHandler createExpressionHandler() {
        MethodSecurityExpressionHandler expressionHandler = new MethodSecurityExpressionHandler();
        return expressionHandler;
    }
}
