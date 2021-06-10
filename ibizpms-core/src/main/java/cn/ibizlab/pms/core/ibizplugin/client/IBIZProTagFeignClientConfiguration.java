package cn.ibizlab.pms.core.ibizplugin.client;

import cn.ibizlab.pms.util.client.SuperLoginClient;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import cn.ibizlab.pms.util.web.IgnorePrivInterceptor;
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
 * 实体[IBIZProTag] 服务对象接口配置
 */
@Configuration
public class IBIZProTagFeignClientConfiguration {

    @Value("${ibiz.ref.service.pmspro-pluginserviceapi.name:pmspro-pluginserviceapi}")
    String serviceValue;

     @Value("${ibiz.ref.service.pmspro-pluginserviceapi.system:}")
    String serviceSystem;

     @Value("${ibiz.ref.service.pmspro-pluginserviceapi.super:}")
    boolean superapi;

     @Value("${ibiz.ref.service.uaa:ibizrt4ebsx-rt4ebsx}")
    String uaaservice;

     @Value("${ibiz.ref.service.pmspro-pluginserviceapi.login:}")
    String login;

     @Value("${ibiz.ref.service.pmspro-pluginserviceapi.password:}")
    String password;

    @Autowired
    Feign.Builder builder;

    @Bean
    public IBIZProTagFeignClient iBIZProTagFeignClient(Decoder decoder, Encoder encoder, Client client, Contract contract, List<RequestInterceptor> requestInterceptors) {
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
            RequestInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
            requestInterceptors.add(ignorePrivInterceptor);
        }
        return outsideAccessorUtils.buildAccessor(IBIZProTagFeignClient.class, new IBIZProTagFallback(), serviceValue, requestInterceptors);
    }
}
