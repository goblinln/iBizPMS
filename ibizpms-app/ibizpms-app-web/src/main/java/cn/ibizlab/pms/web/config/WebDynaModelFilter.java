package cn.ibizlab.pms.web.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class WebDynaModelFilter extends ZuulFilter {

	public static final String MODELPATHROOT = "/PSSYSAPPS/Web";

	public static final String STDREQPREFIX = "/dynamodel/";

	public static final String MODELREQPREFIX = "/dynamodel2/";

	public static final String INSTTAGPRAMANAME = "srfInstTag";

	public static final String INSTTAG2PRAMANAME = "srfInstTag2";

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Object proxyKey = ctx.get(FilterConstants.PROXY_KEY);
		if("dynamodel".equals(proxyKey))
			return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		modifiedRequestPath();
		return null;
	}

	public void modifiedRequestPath()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		String originalRequestPath =(String)  ctx.get(FilterConstants.REQUEST_URI_KEY);
		if(StringUtils.isEmpty(originalRequestPath))
			return;
		String modifiedRequestPath =MODELPATHROOT + originalRequestPath;
		String prefix=STDREQPREFIX;
		HttpServletRequest request = ctx.getRequest();
		Map<String, String[]> param = request.getParameterMap();
		if(param.containsKey(INSTTAGPRAMANAME)||param.containsKey(INSTTAG2PRAMANAME)) {
			String srfInstTag= param.get(INSTTAGPRAMANAME)[0];
			String srfInstTag2= param.get(INSTTAG2PRAMANAME)[0];
			modifiedRequestPath = srfInstTag+"/"+srfInstTag2 + modifiedRequestPath;
			prefix=MODELREQPREFIX;
		}
		ctx.put(FilterConstants.REQUEST_URI_KEY, prefix+modifiedRequestPath);
	}
}
