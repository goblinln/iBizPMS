package cn.ibizlab.pms.util.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * ZT过度代码
 */
@Component
public class IPMSFeignFallback implements IPMSFeignClient {


    @Override
    public JSONObject doZTLogin(String uaaloginname, String token) {
        return null;
    }

    @Override
    public JSONObject getZTUser(String commiter) {
        return null;
    }

    @Override
    public Boolean recordLoginLog(String username) {
        return null;
    }

    @Override
    public JSONObject getSrfMStatus() {
        return null;
    }
}
