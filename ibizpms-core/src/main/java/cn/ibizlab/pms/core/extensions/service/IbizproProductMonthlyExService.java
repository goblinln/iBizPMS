package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProductMonthlyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品月报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProductMonthlyExService")
public class IbizproProductMonthlyExService extends IbizproProductMonthlyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ManualCreateMonthly:手动生成产品月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProductMonthly manualCreateMonthly(IbizproProductMonthly et) {
        return super.manualCreateMonthly(et);
    }
    /**
     * [StatsProductMonthly:汇总产品月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProductMonthly statsProductMonthly(IbizproProductMonthly et) {
        return super.statsProductMonthly(et);
    }
}

