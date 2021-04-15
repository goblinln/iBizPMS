package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProjectMonthlyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目月报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProjectMonthlyExService")
public class IbizproProjectMonthlyExService extends IbizproProjectMonthlyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ManualCreateMonthly:手动生成项目月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProjectMonthly manualCreateMonthly(IbizproProjectMonthly et) {
        return super.manualCreateMonthly(et);
    }
    /**
     * [SumProjectMonthly:汇总项目月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProjectMonthly sumProjectMonthly(IbizproProjectMonthly et) {
        return super.sumProjectMonthly(et);
    }
}

