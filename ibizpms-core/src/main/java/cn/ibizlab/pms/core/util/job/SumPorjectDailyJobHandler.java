package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("SumPorjectDailyJobHandler")
public class SumPorjectDailyJobHandler implements IJobsHandler {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService ibizproprojectdailyService;

    @Override
    public JobsResponse execute(String tenantId, String param) throws JobsException {
        cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily entity = cn.ibizlab.pms.util.helper.Setting.getEntity(param, new cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily());
        entity.set("tenantid", tenantId);
        entity.set("param", param);
        ibizproprojectdailyService.sumProjectDaily(entity);
        log.info("执行 DemoJobHandler tenantId=" + tenantId + ",param=" + param);
        return JobsResponse.ok();
    }
}