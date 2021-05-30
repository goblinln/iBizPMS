package cn.ibizlab.pms.core.util.config;

import com.baomidou.jobs.starter.EnableJobs;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@EnableJobs
@Configuration
@ConditionalOnProperty(name = "jobs.enabled", havingValue = "true", matchIfMissing = false)
public class JobsConfiguration {

}
