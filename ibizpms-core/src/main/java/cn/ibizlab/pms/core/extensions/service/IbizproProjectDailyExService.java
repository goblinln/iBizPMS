package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProjectDailyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目日报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProjectDailyExService")
public class IbizproProjectDailyExService extends IbizproProjectDailyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SumProjectDaily:汇总项目日报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProjectDaily sumProjectDaily(IbizproProjectDaily et) {
        return super.sumProjectDaily(et);
    }
}

