package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.ImMessagestatus;
import cn.ibizlab.pms.core.zentao.service.impl.ImMessagestatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Primary
@Service("ImMessagestatusExService")
public class ImMessagestatusExService extends ImMessagestatusServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(ImMessagestatus et) {
        et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getUser(),et.getMessage()).getBytes()));
        return super.create(et);
    }
}

