package cn.ibizlab.pms.core.ibiz.client;

import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.core.ibiz.filter.DynaDashboardSearchContext;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.Errors;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * 实体[DynaDashboard] 服务对象接口
 */
public class DynaDashboardFallback implements FallbackFactory<DynaDashboardFeignClient> {

    @Override
    public DynaDashboardFeignClient create(Throwable cause) {
        String errorMessage = "";
        if (cause instanceof FeignException) {
            FeignException ex = (FeignException) cause;
            errorMessage = "[[R7RT标准接口]动态模型]调用异常，错误状态：" + ex.status() + "." + cause.getMessage();
        } else {
            errorMessage = "[[R7RT标准接口]动态模型]异常，错误：" + cause.getMessage();
        }
        String finalErrorMessage = errorMessage;
        return new DynaDashboardFeignClient(){


        };
    }

}
