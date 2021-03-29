package cn.ibizlab.pms.util.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class IBZLiteFallback implements IBZLiteFeignClient {

	@Override
	public Boolean syncSysModel(JSONObject system) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getDynamicModel(String systemId) {
		return null;
	}
}
