package cn.ibizlab.pms.core.ibizplugin.client;

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

import java.util.Collections;
import java.util.List;

/**
 * 实体[IBIZProPlugin] 服务对象接口配置
 */
@Configuration
public class IBIZProPluginFeignClientConfiguration {

    @Value("${ibiz.ref.service.pmspro-pluginserviceapi.name:pmspro-pluginserviceapi}")
    String serviceValue;

    @Value("${ibiz.ref.service.pmspro-pluginserviceapi.system:}")
    String serviceSystem;

    @Autowired
    Feign.Builder builder;

    @Bean
    public IBIZProPluginFeignClient iBIZProPluginFeignClient(Decoder decoder, Encoder encoder, Client client, Contract contract, List<RequestInterceptor> requestInterceptors) {
        requestInterceptors.add(new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                requestTemplate.header("srfsystemid", Collections.emptyList());
                requestTemplate.header("srfsystemid", serviceSystem);
            }
        });
        if (builder instanceof HystrixFeign.Builder) {
            HystrixFeign.Builder nameBuilder = HystrixFeign.builder()
                    .client(client)
                    .encoder(encoder)
                    .decoder(decoder)
                    .contract(contract)
                    .requestInterceptors(requestInterceptors);

            return nameBuilder.target(IBIZProPluginFeignClient.class, "http://" + serviceValue, new IBIZProPluginFallback());
        } else {
            Feign.Builder nameBuilder = Feign.builder()
                    .client(client)
                    .encoder(encoder)
                    .decoder(decoder)
                    .contract(contract)
                    .requestInterceptors(requestInterceptors);
            return nameBuilder.target(IBIZProPluginFeignClient.class, "http://" + serviceValue);
        }
    }
}
