package cn.ibizlab.pms.util.client;

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
    
}
