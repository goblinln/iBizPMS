package cn.ibizlab.pms.core.ibizsysmodel.client;

import cn.ibizlab.pms.util.client.SuperLoginClient;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import cn.ibizlab.pms.util.web.SuperLoginInterceptor;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 实体[PSDataEntity] 服务对象接口配置
 */
@Configuration
public class PSDataEntityFeignClientConfiguration {

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.name:ibizpssysmodelapi-sysmodelapi}")
    String serviceValue;

     @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.name:}")
    String serviceSystem;

     @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.super:}")
    boolean superapi;

     @Value("${ibiz.ref.service.uaa:ibizrt4ebsx-rt4ebsx}")
    String uaaservice;

     @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.login:}")
    String login;

     @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:}")
    String password;

    @Autowired
    Feign.Builder builder;

    @Bean
    public PSDataEntityFeignClient pSDataEntityFeignClient(Decoder decoder, Encoder encoder, Client client, Contract contract, List<RequestInterceptor> requestInterceptors) {
        List<RequestInterceptor> requestInterceptors = new ArrayList<>();
        RequestInterceptor systemInterceptor = new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                requestTemplate.header("srfsystemid", Collections.emptyList());
                requestTemplate.header("srfsystemid", serviceSystem);
            }
        };
        requestInterceptors.add(systemInterceptor);

        //忽略权限
        if (superapi) {
            SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                @Override
                public void apply(RequestTemplate requestTemplate) {
                    requestTemplate.header("srfsystemid", Collections.emptyList());
                    requestTemplate.header("srforgid", Collections.emptyList());
                    requestTemplate.header("Authorization", Collections.emptyList());
                }
            }));
            RequestInterceptor superLoginInterceptor = new SuperLoginInterceptor(superLoginClient, login, password);
            requestInterceptors.add(superLoginInterceptor);
        }
        return outsideAccessorUtils.buildAccessor(SysEmployeeFeignClient.class, new SysEmployeeFallback(), serviceValue, requestInterceptors);
    }
}
