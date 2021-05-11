package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.SysLog;
import net.ibizsys.runtime.util.domain.MsgSendQueue;
import org.apache.rocketmq.common.message.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${ibiz.ref.service.tps:ibiztpsmgr4ebsx-tps}",contextId = "tps",fallback = IBZTPSFallback.class)
public interface IBZTPSFeignClient
{
	@RequestMapping(method = RequestMethod.POST, value = "/message/produce")
	Boolean produce(@RequestBody Message message);

	@RequestMapping(method = RequestMethod.GET, value = "/message/consume/{topic}")
	Boolean custome(@PathVariable("topic") String topic);

	@RequestMapping(method = RequestMethod.POST, value = "/syslog")
	Boolean syslog(@RequestBody SysLog syslog);

	@RequestMapping(method = RequestMethod.POST, value = "/msgsend/send")
	Boolean send(@RequestBody MsgSendQueue msgSendQueue);

	@RequestMapping(method = RequestMethod.GET, value = "/msgsend/send/{msgid}")
	Boolean sendById(@PathVariable("msgid") String msgid);
}
