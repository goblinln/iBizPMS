package cn.ibizlab.pms.core.util.job;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.exception.JobsException;
import com.baomidou.jobs.handler.IJobsHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("TodoServiceJobHandler")
public class TodoServiceJobHandler implements IJobsHandler {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITodoService todoService;


    @Override
    public JobsResponse execute(String tenantId, String param) throws JobsException {
        log.info("执行 TodoServiceJobHandler tenantId=" + tenantId + ",param=" + param);
        return JobsResponse.ok();
    }
}