package cn.ibizlab.pms.util.client;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

//@Configuration
public class IBZUAAFeignClientConfiguration {

    @Value("${ibiz.ref.service.uaa.name:ibzuaa-api}")
    String serviceValue;

    @Value("${ibiz.ref.service.uaa.system:}")
    String serviceSystem;

    @Bean
    public IBZUAAFeignClient uaaFeignClient(Decoder decoder, Encoder encoder, Client client, Contract contract, List<RequestInterceptor> requestInterceptors) {
        requestInterceptors.add(new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("srfsystemid", Collections.emptyList());
                requestTemplate.header("srfsystemid", serviceSystem);
            }
        });
        HystrixFeign.Builder nameBuilder = HystrixFeign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptors(requestInterceptors);
        return nameBuilder.target(IBZUAAFeignClient.class, "http://" + serviceValue, new IBZUAAFallback());
    }

}
