package cn.ibizlab.pms.util.client;

import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

@Component
public class IBZTPSFallback implements IBZTPSFeignClient {

    @Override
    public Boolean send(Message message) {
        return null;
    }
    
}
