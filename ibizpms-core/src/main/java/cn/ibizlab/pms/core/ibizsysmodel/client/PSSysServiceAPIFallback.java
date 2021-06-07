package cn.ibizlab.pms.core.ibizsysmodel.client;

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysServiceAPISearchContext;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.Errors;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * 实体[PSSysServiceAPI] 服务对象接口
 */
public class PSSysServiceAPIFallback implements FallbackFactory<PSSysServiceAPIFeignClient> {

    @Override
    public PSSysServiceAPIFeignClient create(Throwable cause) {
        String errorMessage = "";
        if (cause instanceof FeignException) {
            FeignException ex = (FeignException) cause;
            errorMessage = "[iBiz平台系统模型接口]调用异常，错误状态：" + ex.status() + "." + cause.getMessage();
        } else if (cause instanceof HystrixTimeoutException) {
            errorMessage = "[RT服务接口]调用超时。";
        } else {
            errorMessage = "[iBiz平台系统模型接口]异常，错误：" + cause.getMessage();
        }
        String finalErrorMessage = errorMessage;
        return new PSSysServiceAPIFeignClient(){

            public Page<PSSysServiceAPI> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysServiceAPI create(PSSysServiceAPI pssysserviceapi) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<PSSysServiceAPI> pssysserviceapis) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysServiceAPI update(String pssysserviceapiid, PSSysServiceAPI pssysserviceapi) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<PSSysServiceAPI> pssysserviceapis) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String pssysserviceapiid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysServiceAPI get(String pssysserviceapiid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public PSSysServiceAPI getDraft(PSSysServiceAPI entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(PSSysServiceAPI pssysserviceapi) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Object saveEntity(PSSysServiceAPI pssysserviceapi) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(PSSysServiceAPI pssysserviceapi) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<PSSysServiceAPI> pssysserviceapis) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<PSSysServiceAPI> searchDefault(PSSysServiceAPISearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
