package cn.ibizlab.pms.mob.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Component
@Slf4j
public class SampleHeaderFilter extends ZuulFilter {

	@Autowired
	private Environment env;

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
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        if (pathMatcher.match("/systeams/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        if (pathMatcher.match("/sysposts/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        if (pathMatcher.match("/sysdepartments/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        if (pathMatcher.match("/systeammembers/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        if (pathMatcher.match("/sysorganizations/**", requestURI)){
			ctx.addZuulRequestHeader("srfsystemid", env.getProperty("ibiz.ref.service.ibzou-api.system",""));
		}
        return null;
    }
}

