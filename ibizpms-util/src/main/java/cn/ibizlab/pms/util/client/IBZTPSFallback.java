package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.SysLog;
import com.alibaba.fastjson.JSONObject;
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
    
    @Override
    public MsgSendQueue[] getByIds(String[] msgids) {
        return new MsgSendQueue[0];
    }

    @Override
    public JSONObject execute(String id, String params) {
        return null;
    }

    @Override
    public Boolean start(String id) {
        return null;
    }

    @Override
    public Boolean stop(String id) {
        return null;
    }
    
}
