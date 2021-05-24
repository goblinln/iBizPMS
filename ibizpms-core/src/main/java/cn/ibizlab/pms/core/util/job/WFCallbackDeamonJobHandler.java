package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.util.IEntity;
import net.ibizsys.runtime.util.IEntityBase;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;
import org.springframework.data.domain.Page;
import com.alibaba.fastjson.JSON;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String deName = arg.getString("dename");
        String action = arg.getString("action");
        String data = arg.getString("data");
        try {
            IDataEntityRuntime callBackDERuntime = systemRuntime.getDataEntityRuntime(deName);
            if(callBackDERuntime != null) {
                IEntity entity = (IEntity) callBackDERuntime.deserializeEntity(data);
                Map dataMap = (Map) JSON.parse(data);
                Object actionType = dataMap.get("actiontype");
                Map map = new HashMap(16);
                if (!ObjectUtils.isEmpty(actionType) && !ObjectUtils.isEmpty(action)) {
                    callBackDERuntime.getPSDataEntity().getAllPSDEDataSets().forEach(ipsdeDataSet -> {
                        if (action.equals(ipsdeDataSet.getCodeName())) {
                            Page<? extends IEntityBase> dataSet = callBackDERuntime
                                    .searchDataSet(ipsdeDataSet, callBackDERuntime.createSearchContext());
                            if (!ObjectUtils.isEmpty(dataSet)) {
                                map.put("content", dataSet.getContent());
                            }
                        }
                    });
                }
                List<IPSDEAction> allPSDEAction = callBackDERuntime.getPSDataEntity().getAllPSDEActions();
                boolean flag = false;
                for (IPSDEAction psdeAction : allPSDEAction) {
                    if (action.equals(psdeAction.getCodeName().toLowerCase())) {
                        callBackDERuntime.executeAction(action, psdeAction, new Object[]{entity});
                        flag = true;
                        break;
                    }
                }
                if (action != null && !flag) {
                    callBackDERuntime.executeAction(action, null, new Object[]{entity});
                }
                return JobsResponse.ok().setMsg(JSONObject.toJSONString(map));
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("工作流回调发生错误:%1$s", e.getMessage()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return JobsResponse.ok();
    }
}