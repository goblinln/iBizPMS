package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProductDailyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品日报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProductDailyExService")
public class IbizproProductDailyExService extends IbizproProductDailyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ManualCreateDaily:手动生成产品日报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProductDaily manualCreateDaily(IbizproProductDaily et) {
        return super.manualCreateDaily(et);
    }
    /**
     * [StatsProductDaily:汇总产品日报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProductDaily statsProductDaily(IbizproProductDaily et) {
        return super.statsProductDaily(et);
    }
}

