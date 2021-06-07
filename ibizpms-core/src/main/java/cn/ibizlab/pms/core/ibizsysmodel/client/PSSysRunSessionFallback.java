package cn.ibizlab.pms.core.ibizsysmodel.client;

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysRunSession;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;
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
 * 实体[PSSysRunSession] 服务对象接口
 */
public class PSSysRunSessionFallback implements FallbackFactory<PSSysRunSessionFeignClient> {

    @Override
    public PSSysRunSessionFeignClient create(Throwable cause) {
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
        return new PSSysRunSessionFeignClient(){

            public Page<PSSysRunSession> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysRunSession create(PSSysRunSession pssysrunsession) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<PSSysRunSession> pssysrunsessions) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysRunSession update(String pssysrunsessionid, PSSysRunSession pssysrunsession) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<PSSysRunSession> pssysrunsessions) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String pssysrunsessionid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public PSSysRunSession get(String pssysrunsessionid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public PSSysRunSession getDraft(PSSysRunSession entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(PSSysRunSession pssysrunsession) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Object saveEntity(PSSysRunSession pssysrunsession) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(PSSysRunSession pssysrunsession) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<PSSysRunSession> pssysrunsessions) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<PSSysRunSession> searchDefault(PSSysRunSessionSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
