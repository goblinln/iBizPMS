package cn.ibizlab.pms.util.helper;

import cn.ibizlab.pms.util.web.IgnorePrivInterceptor;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.FallbackFactory;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 外部接口访问工具类  Feign接口定义
 */
@Component
public class OutsideAccessorUtils {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Contract contract;

    @Autowired
    Encoder encoder;

    @Autowired
    Decoder decoder;

    @Autowired
    Client client;

    @Autowired
    List<RequestInterceptor> defaultInterceptors;

    /**
     * 启用熔断
     */
    @Value(value = "${feign.hystrix.enabled:false}")
    private boolean hystrix;

    /**
     * 缓存
     */
    private final Cache<MultiKey, Object> cache = CacheBuilder.newBuilder().weakValues().build();

    /**
     * @param accessInterface
     * @param accessfallback
     * @param server
     * @param <T>
     * @return
     */
    public <T> T buildAccessor(Class<T> accessInterface, FallbackFactory accessfallback, String server) {
        try {
            return (T) cache.get(new MultiKey(accessInterface, server), () -> construct(accessInterface, accessfallback, server, null));
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    public <T> T buildAccessor(Class<T> accessInterface, FallbackFactory accessfallback, String server, List<RequestInterceptor> interceptors) {
        try {
            return (T) cache.get(new MultiKey(accessInterface, server), () -> construct(accessInterface, accessfallback, server, interceptors));
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    private <T> T construct(Class<T> accessInterface, FallbackFactory accessfallback, String server, List<RequestInterceptor> interceptors) throws Exception {
        if (server.startsWith("http")) {
            if (client instanceof LoadBalancerFeignClient) { // 无需均衡负载
                client = ((LoadBalancerFeignClient) client).getDelegate();
            }
        }
        Feign.Builder builder = Feign.builder().client(client).contract(contract).encoder(encoder).decoder(decoder);
        builder.invocationHandlerFactory(new InvocationHandlerFactory() {
                                             @Override
                                             public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
                                                 if (accessfallback != null && hystrix) {
                                                     return new OutsideInvocationHandler(target, dispatch, accessfallback);
                                                 } else {
                                                     return new OutsideInvocationHandler(target, dispatch);
                                                 }
                                             }
                                         }
        );
        List<RequestInterceptor> requestInterceptors = null;
        if (interceptors != null) {
            requestInterceptors = ListUtils.union(defaultInterceptors, interceptors);
        } else {
            requestInterceptors = defaultInterceptors;
        }
        builder.requestInterceptors(requestInterceptors);
        return builder.target(accessInterface, server.startsWith("http") ? server : "http" + "://" + server);

    }

}