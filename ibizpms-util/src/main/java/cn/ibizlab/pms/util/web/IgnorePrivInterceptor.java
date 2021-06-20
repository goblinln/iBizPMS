package cn.ibizlab.pms.util.web;

import cn.ibizlab.pms.util.client.SuperLoginClient;
import cn.ibizlab.pms.util.security.AuthTokenUtil;
import cn.ibizlab.pms.util.security.AuthenticationInfo;
import cn.ibizlab.pms.util.security.AuthorizationLogin;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

/**
 * 忽略权限系统级调用
 */
public class IgnorePrivInterceptor implements RequestInterceptor {

    private String authUser;
    private String authPassword;
    private String token;
    private  AuthenticationInfo authenticationInfo;
    private SuperLoginClient superLoginClient;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public IgnorePrivInterceptor(SuperLoginClient superLoginClient, String authUser, String authPassword) {
        this.authUser = authUser;
        this.authPassword = authPassword;
        this.superLoginClient = superLoginClient;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            requestTemplate.header("Authorization", Collections.emptyList());
            requestTemplate.header("Authorization", "Bearer " + getToken());
        }
    }


    public String getToken() {
        AuthTokenUtil authTokenUtil = SpringContextHolder.getBean(AuthTokenUtil.class);
        if (StringUtils.isNotBlank(this.token)) {
            if (authTokenUtil.getExpirationDateFromToken(this.token).after(new Date())) {
                return this.token;
            }
        }
        AuthorizationLogin login = new AuthorizationLogin();
        login.setLoginname(authUser);
        login.setPassword(authPassword);
        try {
            AuthenticationInfo info = superLoginClient.login(login);
            this.token = info.getToken();
            this.authenticationInfo = info;
            return this.token;
        } catch (Exception e) {
            throw new RuntimeException(String.format("忽略接口权限认证发生错误[%s]-[%s]：%s", authUser, authPassword, e.getMessage()));
        }
    }

    public AuthenticationInfo getAuthenticationInfo() {
        AuthorizationLogin login = new AuthorizationLogin();
        login.setLoginname(authUser);
        login.setPassword(authPassword);
        try {
            AuthenticationInfo info = superLoginClient.login(login);
            this.token = info.getToken();
            this.authenticationInfo = info;
            return authenticationInfo;
        } catch (Exception e) {
            throw new RuntimeException(String.format("忽略接口权限认证发生错误[%s]-[%s]：%s", authUser, authPassword, e.getMessage()));
        }
    }
}
