package cn.ibizlab.pms.core.uaa.client;

import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
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
 * 实体[SysUserRole] 服务对象接口
 */
public class SysUserRoleFallback implements FallbackFactory<SysUserRoleFeignClient> {

    @Override
    public SysUserRoleFeignClient create(Throwable cause) {
        String errorMessage = "";
        if (cause instanceof FeignException) {
            FeignException ex = (FeignException) cause;
            errorMessage = "[ibzuaa-api]调用异常，错误状态：" + ex.status() + "." + cause.getMessage();
        } else if (cause instanceof HystrixTimeoutException) {
            errorMessage = "[RT服务接口]调用超时。";
        } else {
            errorMessage = "[ibzuaa-api]异常，错误：" + cause.getMessage();
        }
        String finalErrorMessage = errorMessage;
        return new SysUserRoleFeignClient(){

            public Page<SysUserRole> select() {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public SysUserRole create(SysUserRole sysuserrole) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean createBatch(List<SysUserRole> sysuserroles) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public SysUserRole update(String userroleid, SysUserRole sysuserrole) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean updateBatch(List<SysUserRole> sysuserroles) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Boolean remove(String userroleid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean removeBatch(Collection<String> idList) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public SysUserRole get(String userroleid) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public SysUserRole getDraft(SysUserRole entity){
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



            public Boolean checkKey(SysUserRole sysuserrole) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }


            public Object saveEntity(SysUserRole sysuserrole) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Boolean save(SysUserRole sysuserrole) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }
            public Boolean saveBatch(List<SysUserRole> sysuserroles) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }

            public Page<SysUserRole> searchDefault(SysUserRoleSearchContext context) {
                throw new DataEntityRuntimeException(finalErrorMessage, Errors.INTERNALERROR, null);
            }



        };
    }

}
