package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.PayTrade;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class IBZPayFallback implements IBZPayFeignClient {

	@Override
	public JSONObject preCreate(PayTrade trade) {
		return null;
	}

	@Override
	public JSONObject query(PayTrade trade) {
		return null;
	}

	@Override
	public JSONObject cancel(PayTrade trade) {
		return null;
	}

	@Override
	public String pagePay(PayTrade trade) {
		return null;
	}
}
