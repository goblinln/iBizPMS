package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.security.AuthenticationInfo;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.AuthorizationLogin;
import org.springframework.util.MultiValueMap;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;

@Component
public class IBZUAAFallback implements IBZUAAFeignClient {

    @Override
    public Boolean syncSysAuthority(JSONObject system) {
        return null;
    }

    @Override
    public AuthenticationUser login(AuthorizationLogin authorizationLogin) {
        return null;
    }

    @Override
    public AuthenticationInfo v7Login(AuthorizationLogin authorizationLogin) {
        return null;
    }

    @Override
    public String getDynaModelIdByInstId(String instid) {
        return null;
    }
    @Override
    public AuthenticationUser loginByUsername(String system,String orgId,String username) {
        return null;
    }


    @Override
    public String getPublicKey() {
        return null;
    }

    @Override
    public JSONObject getDingtalkAppId(String id) {
        return null;
    }

    @Override
    public AuthenticationInfo getUserByToken(String code, String id) {
        return null;
    }

    @Override
    public JSONObject getDingTalkJSSign(String openAccessId, String url) {
        return null;
    }

    @Override
    public Boolean changepwd(JSONObject jsonObject) {
        return null;
    }
}
