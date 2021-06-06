package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import cn.ibizlab.util.filter.SearchContextBase;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import cn.ibizlab.pms.util.domain.EntityBase;
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
            IDataEntityRuntime deruntime = systemRuntime.getDataEntityRuntime(deName);
            if(deruntime != null) {
                Object objEntity = deruntime.deserializeEntity(data);
                if(objEntity instanceof EntityBase){
                    EntityBase entity = (EntityBase) objEntity;
                    if(!ObjectUtils.isEmpty(entity.get("businessKey"))){
                        entity.set(deruntime.getKeyPSDEField().getCodeName(),entity.get("businessKey"));
                    }
                    Object actionType = entity.get("actiontype");
                    Map callRS = new HashMap();
                    if (!ObjectUtils.isEmpty(actionType) && !ObjectUtils.isEmpty(action)) {
                        for (IPSDEDataSet dataset:deruntime.getPSDataEntity().getAllPSDEDataSets()) {
                            if (action.equalsIgnoreCase(dataset.getCodeName())) {
                                SearchContextBase ctx = (SearchContextBase) deruntime.createSearchContext();
                                ctx.setSize(Integer.MAX_VALUE);
                                Object activedata = arg.get("data");
                                if(activedata instanceof Map){
                                    ctx.setParams((Map)activedata);
                                }
                                Page<? extends IEntityBase> dataSet = deruntime.searchDataSet(dataset, ctx);
                                if (!ObjectUtils.isEmpty(dataSet)) {
                                    callRS.put("content", dataSet.getContent());
                                    return JobsResponse.ok().setMsg(JSONObject.toJSONString(callRS));
                                }
                            }
                        }
                    }
                    List<IPSDEAction> deactions = deruntime.getPSDataEntity().getAllPSDEActions();
                    IPSDEAction exeaction = null;
                    for(IPSDEAction deaction : deactions){
                        if (action.equalsIgnoreCase(deaction.getCodeName())) {
                            exeaction = deaction;
                            break;
                        }
                    }
                    if(exeaction!=null){
                        deruntime.executeAction(action, exeaction, new Object[]{entity});
                    }else{
                        deruntime.executeAction(action, null, new Object[]{entity});
                    }
                }
                return JobsResponse.ok();
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("工作流回调发生错误:%1$s", e.getMessage()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return JobsResponse.ok();
    }
}