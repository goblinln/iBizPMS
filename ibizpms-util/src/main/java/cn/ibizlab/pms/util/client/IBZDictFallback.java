package cn.ibizlab.pms.util.client;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class IBZDictFallback implements IBZDictFeignClient {

    @Override
    public Boolean syncRuntimeDict(JSONArray catalogs) {
        return null;
    }
}
