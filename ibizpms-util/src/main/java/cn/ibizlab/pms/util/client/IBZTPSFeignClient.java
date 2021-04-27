package cn.ibizlab.pms.util.client;

import org.apache.rocketmq.common.message.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${ibiz.ref.service.tps:ibiztpsmgr4ebsx-tps}",contextId = "tps",fallback = IBZTPSFallback.class)
public interface IBZTPSFeignClient
{
	@RequestMapping(method = RequestMethod.POST, value = "/mqmessage")
	Boolean send(@RequestBody Message message);
}
