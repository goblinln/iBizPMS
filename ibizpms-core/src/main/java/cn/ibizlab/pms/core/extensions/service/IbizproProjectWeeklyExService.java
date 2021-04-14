package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProjectWeeklyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目周报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbizproProjectWeeklyExService")
public class IbizproProjectWeeklyExService extends IbizproProjectWeeklyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [PushSumProjectWeekly:定时推送项目周报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbizproProjectWeekly pushSumProjectWeekly(IbizproProjectWeekly et) {
        return super.pushSumProjectWeekly(et);
    }
}

