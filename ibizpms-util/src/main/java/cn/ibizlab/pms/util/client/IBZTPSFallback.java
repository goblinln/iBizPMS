package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.SysAudit;
import cn.ibizlab.pms.util.domain.SysEvent;
import cn.ibizlab.pms.util.domain.SysLog;
import cn.ibizlab.pms.util.domain.SysPO;
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
    public JSONObject execute(Long id, JSONObject params) {
        return null;
    }

    @Override
    public Boolean start(Long id) {
        return null;
    }

    @Override
    public Boolean stop(Long id) {
        return null;
    }

    @Override
    public Boolean audit(SysAudit audit) {
        return null;
    }

    @Override
    public Boolean event(SysEvent event) {
        return null;
    }
    
    @Override
    public Boolean po(SysPO event) {
        return null;
    }

}
