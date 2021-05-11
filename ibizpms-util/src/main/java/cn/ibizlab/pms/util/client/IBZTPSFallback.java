package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.SysLog;
import net.ibizsys.runtime.util.domain.MsgSendQueue;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

@Component
public class IBZTPSFallback implements IBZTPSFeignClient {

    @Override
    public Boolean produce(Message message) {
        return null;
    }

    @Override
    public Boolean custome(String topic) {
        return null;
    }

    @Override
    public Boolean syslog(SysLog syslog) {
        return null;
    }

    @Override
    public Boolean send(MsgSendQueue msgSendQueue) {
        return null;
    }

    @Override
    public Boolean sendById(String msgid) {
        return null;
    }
    
}
