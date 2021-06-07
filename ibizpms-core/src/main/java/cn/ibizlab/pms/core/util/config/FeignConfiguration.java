package cn.ibizlab.pms.core.util.config;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new OptionalDecoder(
                new ResponseEntityDecoder(new FeignDecoder(messageConverters)));
    }

    @Bean
    public feign.QueryMapEncoder feignEncoder(){
        return new FeignEncoder();
    }

    @Bean
    @ConditionalOnProperty(name = "feign.hystrix.enabled")
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
        return new RequestContextHystrixConcurrencyStrategy();
    }

}