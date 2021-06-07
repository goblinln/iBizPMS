package cn.ibizlab.pms.core.ibizplugin.client;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;
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
 * 实体[IBIZProMessage] 服务对象接口
 */
public class IBIZProMessageFallback implements FallbackFactory<IBIZProMessageFeignClient> {

    @Override
    public IBIZProMessageFeignClient create(Throwable cause) {
        String errorMessage = "";
        if (cause instanceof FeignException) {
            FeignException ex = (FeignException) cause;
            errorMessage = "[插件接入接口]调用异常，错误状态：" + ex.status() + "." + cause.getMessage();
        } else if (cause instanceof HystrixTimeoutException) {
            errorMessage = "[RT服务接口]调用超时。";
        } else {
            errorMessage = "[插件接入接口]异常，错误：" + cause.getMessage();
        }
        String finalErrorMessage = errorMessage;
        return new IBIZProMessageFeignClient(){

            public Page<IBIZProMessage> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProMessage create(IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<IBIZProMessage> ibizpromessages) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProMessage update(String ibizpromessageid, IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<IBIZProMessage> ibizpromessages) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String ibizpromessageid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProMessage get(String ibizpromessageid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public IBIZProMessage getDraft(IBIZProMessage entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public IBIZProMessage markDone( String ibizpromessageid, IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProMessage markRead( String ibizpromessageid, IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Object saveEntity(IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<IBIZProMessage> ibizpromessages) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public IBIZProMessage send( String ibizpromessageid, IBIZProMessage ibizpromessage) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<IBIZProMessage> searchDefault(IBIZProMessageSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Page<IBIZProMessage> searchUserAllMessages(IBIZProMessageSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Page<IBIZProMessage> searchUserUnreadMessages(IBIZProMessageSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
