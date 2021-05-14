package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
import cn.ibizlab.pms.core.zentao.service.impl.SuiteCaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Slf4j
@Primary
@Service("SuiteCaseExService")
public class SuiteCaseExService extends SuiteCaseServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(SuiteCase et) {
        et.setId(UUID.randomUUID().toString());
        // et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getSuite(), et.getIbizcase()).getBytes()));
        return super.create(et);
    }
}

