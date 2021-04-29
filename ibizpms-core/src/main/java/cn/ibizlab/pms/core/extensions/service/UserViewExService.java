package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.UserView;
import cn.ibizlab.pms.core.zentao.service.impl.UserViewServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Primary
@Service("UserViewExService")
public class UserViewExService extends UserViewServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(UserView et) {
        et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getAccount()).getBytes()));
        return super.create(et);
    }
}

