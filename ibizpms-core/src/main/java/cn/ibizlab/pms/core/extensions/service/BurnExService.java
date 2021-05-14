package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.BurnServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Burn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.util.DigestUtils;

import java.util.UUID;

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

    @Override
    public boolean create(Burn et) {
        et.setId(UUID.randomUUID().toString());
        // et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getProject(), et.getTask(),et.getDate()).getBytes()));
        return super.create(et);
    }
}

