package cn.ibizlab.pms.mob.config;

import cn.ibizlab.pms.util.client.SuperLoginClient;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.web.IgnorePrivInterceptor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Arrays;
import java.util.Collections;

@Component
@Slf4j
public class MobHeaderFilter extends ZuulFilter {

	@Autowired
	private Environment env;

    @Autowired
    OutsideAccessorUtils outsideAccessorUtils;

    @Value("${ibiz.ref.service.uaa:ibizrt4ebsx-rt4ebsx}")
    String uaaservice;

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        if (pathMatcher.match("/sysemployees/**", requestURI))
            return true;
        if (pathMatcher.match("/systeams/**", requestURI))
            return true;
        if (pathMatcher.match("/sysposts/**", requestURI))
            return true;
        if (pathMatcher.match("/sysdepartments/**", requestURI))
            return true;
        if (pathMatcher.match("/systeammembers/**", requestURI))
            return true;
        if (pathMatcher.match("/sysorganizations/**", requestURI))
            return true;
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
		String requestURI = ctx.getRequest().getRequestURI();
        if (pathMatcher.match("/sysemployees/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        if (pathMatcher.match("/systeams/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        if (pathMatcher.match("/sysposts/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        if (pathMatcher.match("/sysdepartments/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        if (pathMatcher.match("/systeammembers/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        if (pathMatcher.match("/sysorganizations/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system","f7ad7e05-9031-11eb-b882-00163e06e68c"));
            if (env.getProperty("ibiz.ref.service.ibzou-api.super", Boolean.class, false)) {
                AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
                SuperLoginClient superLoginClient = outsideAccessorUtils.buildAccessor(SuperLoginClient.class, null, uaaservice, Arrays.asList(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("srfsystemid", Collections.emptyList());
                        requestTemplate.header("srforgid", Collections.emptyList());
                        requestTemplate.header("Authorization", Collections.emptyList());
                    }
                }));
                String login = env.getProperty("ibiz.ref.service.ibzou-api.login", "login");
                String password = env.getProperty("ibiz.ref.service.ibzou-api.password", "password");
                IgnorePrivInterceptor ignorePrivInterceptor = new IgnorePrivInterceptor(superLoginClient, login, password);
                ctx.addZuulRequestHeader("srfdcid", curUser.getSrfdcid());
                ctx.addZuulRequestHeader("Authorization", "Bearer " + ignorePrivInterceptor.getToken());
            }
		}
        return null;
    }
}

