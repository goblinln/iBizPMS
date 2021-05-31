package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Slf4j
@Component("DENotifyDeamonJobHandler")
public class DENotifyDeamonJobHandler implements IJobsHandler {



    @Override
    public JobsResponse execute(String tenantId, String param) throws JobsException {
        log.info("执行 DENotifyDeamonJobHandler tenantId=" + tenantId + ",param=" + param);
        return JobsResponse.ok();
    }
}