package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.*;
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

    @Override
    public DataObj createTask(SysTodo task) {
        return null;
    }

    @Override
    public DataObj createSendCopyTask(SysTodo task) {
        return null;
    }

    @Override
    public DataObj completeTask(String taskId, String userId) {
        return null;
    }

    @Override
    public DataObj cancelTask(String taskId) {
        return null;
    }

    @Override
    public DataObj delegateTask(String taskId, DataObj dataObj) {
        return null;
    }

    @Override
    public DataObj resolveTask(String taskId, String userId) {
        return null;
    }

    @Override
    public DataObj reassignTask(String taskId, DataObj dataObj) {
        return null;
    }

    @Override
    public DataObj markReadTask(String taskId, String userId) {
        return null;
    }

    @Override
    public DataObj markReadTask(String taskId, String userId) {
        return null;
    }

    @Override
    public DataObj markReadSendCopyTask(String taskId, String userId) {
        return null;
    }

}
