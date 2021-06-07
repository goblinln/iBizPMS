package cn.ibizlab.pms.core.ibizplugin.client;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;

/**
 * 实体[IBIZProMessage] 服务对象接口配置
 */
@Configuration
public class IBIZProMessageFeignClientConfiguration {

    @Value("${ibiz.ref.service.pmspro-pluginserviceapi.name:pmspro-pluginserviceapi}")
    String serviceValue;

    @Value("${ibiz.ref.service.pmspro-pluginserviceapi.system:}")
    String serviceSystem;

    @Bean
    public IBIZProMessageFeignClient iBIZProMessageFeignClient(Decoder decoder, Encoder encoder, Client client, Contract contract, List<RequestInterceptor> requestInterceptors) {
        requestInterceptors.add(new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("srfsystemid", Collections.emptyList());
                requestTemplate.header("srfsystemid", serviceSystem);
            }
        });
        ReflectiveFeign.Builder nameBuilder = ReflectiveFeign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptors(requestInterceptors);
                
        return nameBuilder.target(IBIZProMessageFeignClient.class, "http://" + serviceValue);
        //, new IBIZProMessageFallback()
    }
}
