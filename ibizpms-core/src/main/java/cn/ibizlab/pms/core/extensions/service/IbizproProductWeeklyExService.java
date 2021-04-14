package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProductWeeklyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品周报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProductWeeklyExService")
public class IbizproProductWeeklyExService extends IbizproProductWeeklyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SumProductWeekly:统计产品周报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProductWeekly sumProductWeekly(IbizproProductWeekly et) {
        return super.sumProductWeekly(et);
    }
}

