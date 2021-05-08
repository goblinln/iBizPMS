package cn.ibizlab.pms.util.web;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;

/**
 * feign请求拦截器
 * 拦截所有使用feign发出的请求，附加原始请求Header参数及Token
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${ibiz.deploysystemid:NONE}")
    private String strDeploySystemId;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestTemplate.header("srfdeploysystemid", strDeploySystemId);
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if(curUser != null){
            requestTemplate.header("srfsystemid", curUser.getSrfsystemid());
            requestTemplate.header("srfdcid", curUser.getSrfdcid());
            requestTemplate.header("srfdcsystemid", curUser.getSrfdcsystemid());
        }
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if (name.equalsIgnoreCase("transfer-encoding")) {
                        continue;
                    }
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
                logger.info("feign interceptor header:{}", requestTemplate);
            }
        }
    }
}