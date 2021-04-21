package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[任务预计] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TaskEstimateExService")
public class TaskEstimateExService extends TaskEstimateServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [PMEvaluation:项目经理评估] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TaskEstimate pMEvaluation(TaskEstimate et) {
        return super.pMEvaluation(et);
    }
}

