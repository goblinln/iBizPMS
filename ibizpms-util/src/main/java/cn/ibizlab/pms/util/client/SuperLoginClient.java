package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.security.AuthenticationInfo;
import cn.ibizlab.pms.util.security.AuthorizationLogin;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 实体[PSDevUser] 服务对象接口
 */
public interface SuperLoginClient {

    @PostMapping(value = "/v7/login")
    public AuthenticationInfo login(@Validated @RequestBody AuthorizationLogin authorizationLogin);

}
