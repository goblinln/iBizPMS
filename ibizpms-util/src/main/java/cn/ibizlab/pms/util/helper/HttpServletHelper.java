package cn.ibizlab.pms.util.helper;

import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

@Slf4j
public class HttpServletHelper {
    /**
     * 获取请求参数
     * @param requestAttributes
     * @param paramName
     * @return
     */
    public static String getAttribute(RequestAttributes requestAttributes , String paramName){
        String paramValue = null;
        if(requestAttributes == null || paramName == null){
            return  paramValue;
        }
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            if(servletRequestAttributes != null){
                HttpServletRequest request = servletRequestAttributes.getRequest();
                if(request != null && !ObjectUtils.isEmpty(request.getAttribute(paramName))){
                    paramValue = request.getAttribute(paramName).toString();
                }
            }
        }
        return paramValue;
    }

    /**
     * 设置请求参数
     * @param requestAttributes
     * @param paramName
     * @param paramValue
     * @return
     */
    public static boolean setAttribute(RequestAttributes requestAttributes , String paramName ,String paramValue){
        boolean result = false;
        if(requestAttributes == null || paramName == null || paramValue == null){
            return result;
        }
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            if(servletRequestAttributes != null){
                HttpServletRequest request = servletRequestAttributes.getRequest();
                if(request != null){
                    request.setAttribute(paramName,paramValue);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 获取响应头参数
     * @param response
     * @param headerName
     * @return
     */
    public static String getResponseHeader(Response response, String headerName){
        String headerValue = null;
        if(response == null || headerName == null || ObjectUtils.isEmpty(response.headers()) || ObjectUtils.isEmpty(response.headers().get(headerName))){
            return  headerName;
        }
        Optional<String> values = response.headers().get(headerName).stream().findFirst();
        if(!ObjectUtils.isEmpty(values))
            headerValue = values.get();
        return headerValue;
    }

    /**
     * 获取响应内容
     * @return
     */
    public static String getResponseBody(Response response){
        String result= null;
        if(response == null || ObjectUtils.isEmpty(response.body())){
            return result;
        }
        try {
            Response.Body body = response.body();
            result = IOUtils.toString(body.asReader(Charset.forName("utf-8")));
        } catch (Exception e) {
            throw new BadRequestAlertException("解析请求响应内容出现异常，"+e,"","");
        }
        return result;
    }

}
