package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.BurnServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Burn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[burn] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BurnExService")
public class BurnExService extends BurnServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ComputeBurn:更新燃尽图] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Burn computeBurn(Burn et) {
        return super.computeBurn(et);
    }
}

