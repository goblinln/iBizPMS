package cn.ibizlab.pms.core.ibizplugin.client;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.Errors;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * 实体[IBIZProPlugin] 服务对象接口
 */
public class IBIZProPluginFallback implements FallbackFactory<IBIZProPluginFeignClient> {

    @Override
    public IBIZProPluginFeignClient create(Throwable cause) {
        String errorMessage = "";
        if (cause instanceof FeignException) {
            FeignException ex = (FeignException) cause;
            errorMessage = "[插件接入接口]调用异常，错误状态：" + ex.status() + "." + cause.getMessage();
        } else {
            errorMessage = "[插件接入接口]异常，错误：" + cause.getMessage();
        }
        String finalErrorMessage = errorMessage;
        return new IBIZProPluginFeignClient(){

            public Page<IBIZProPlugin> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProPlugin create(IBIZProPlugin ibizproplugin) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<IBIZProPlugin> ibizproplugins) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProPlugin update(String ibizpropluginid, IBIZProPlugin ibizproplugin) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<IBIZProPlugin> ibizproplugins) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String ibizpropluginid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProPlugin get(String ibizpropluginid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public IBIZProPlugin getDraft(IBIZProPlugin entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(IBIZProPlugin ibizproplugin) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Object saveEntity(IBIZProPlugin ibizproplugin) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(IBIZProPlugin ibizproplugin) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<IBIZProPlugin> ibizproplugins) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<IBIZProPlugin> searchDefault(IBIZProPluginSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
