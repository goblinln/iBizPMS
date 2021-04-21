package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IBZProSequenceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSequence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[业务序列表] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IBZProSequenceExService")
public class IBZProSequenceExService extends IBZProSequenceServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Init:初始化序列] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProSequence init(IBZProSequence et) {
        return super.init(et);
    }
}

