package cn.ibizlab.pms.core.util.config;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.google.common.collect.Lists;
import feign.FeignException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class FeignDecoder extends SpringDecoder {

    public FeignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public Object decode(final Response response, Type type)
            throws IOException, FeignException {
        if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType() == Page.class) {
            try {
                ((ParameterizedType) type).getActualTypeArguments()[0].getClass();
                ParameterizedTypeImpl ss = new ParameterizedTypeImpl(((ParameterizedType) type).getActualTypeArguments(), null, List.class);
                Object ret = super.decode(response, ss);
                return new PageImpl((List) ret,
                        PageRequest.of(
                                Integer.parseInt(response.headers().get("x-page").toArray()[0].toString()),
                                Integer.parseInt(response.headers().get("x-per-page").toArray()[0].toString()))
                        , Long.parseLong(response.headers().get("x-total").toArray()[0].toString()));
            } catch (Exception e) {
                log.error("FeignDecode转换Page发生错误：" + e.getMessage());
                return new PageImpl(Lists.newArrayList(), PageRequest.of(0, 0), 0);
            }
        }
        return super.decode(response, type);
    }
}