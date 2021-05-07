package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.util.IEntity;
import com.alibaba.fastjson.JSONObject;

@Slf4j
@Component("WFCallbackDeamonJobHandler")
public class WFCallbackDeamonJobHandler implements IJobsHandler {


    @Autowired
    @Lazy
    ISystemRuntime systemRuntime;

    @Override
    public JobsResponse execute(String tenantId, String param) throws JobsException {
        log.info("执行 WFCallbackDeamonJobHandler tenantId=" + tenantId + ",param=" + param);
        JSONObject arg = JSONObject.parseObject(param);
        String deName = arg.getString("dEName");
        String action = arg.getString("action");
        String data = arg.getString("data");
        try {
            IDataEntityRuntime callBackDERuntime = systemRuntime.getDataEntityRuntime(deName);
            IEntity entity = (IEntity)callBackDERuntime.deserializeEntity(data);
            entity.set(callBackDERuntime.getKeyPSDEField().getCodeName(),entity.get("businessKey"));
            callBackDERuntime.executeAction(action, null, new Object[]{entity});
        }catch (Exception e){
            throw new RuntimeException(String.format("工作流回调发生错误:%1$s", e.getMessage()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return JobsResponse.ok();
    }
}