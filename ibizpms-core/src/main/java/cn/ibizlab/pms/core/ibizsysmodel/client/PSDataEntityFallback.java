package cn.ibizlab.pms.core.ibizsysmodel.client;

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDataEntitySearchContext;
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
 * 实体[PSDataEntity] 服务对象接口
 */
public class PSDataEntityFallback implements FallbackFactory<PSDataEntityFeignClient> {

    @Override
    public PSDataEntityFeignClient create(Throwable cause) {
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
        return new PSDataEntityFeignClient(){

            public Page<PSDataEntity> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSDataEntity create(PSDataEntity psdataentity) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<PSDataEntity> psdataentities) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSDataEntity update(String psdataentityid, PSDataEntity psdataentity) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<PSDataEntity> psdataentities) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String psdataentityid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSDataEntity get(String psdataentityid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public PSDataEntity getDraft(PSDataEntity entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(PSDataEntity psdataentity) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Object saveEntity(PSDataEntity psdataentity) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(PSDataEntity psdataentity) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<PSDataEntity> psdataentities) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<PSDataEntity> searchDefault(PSDataEntitySearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
