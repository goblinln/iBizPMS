package cn.ibizlab.pms.core.util.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * MQ订阅消息处理
 */
@Slf4j
@Component
@ConditionalOnExpression("'${rocketmq.consumer.isOnOff:off}'.equals('on')")
public class RocketMQListenerProcessor implements MessageListenerOrderly {


    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        if (CollectionUtils.isEmpty(list)) {
            log.info("MQ接收消息为空，直接返回成功");
            return ConsumeOrderlyStatus.SUCCESS;
        }
        for (MessageExt messageExt : list) {
            log.info("MQ接收到的消息为：" + messageExt.toString());
            try {
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String body = new String(messageExt.getBody(),"utf-8");
                log.info("MQ消息topic={}, tags={}, 消息内容={}", topic, tags, body);
                log.info("接收到[{}]消息，但未配置实体输入过滤行为，消息将被忽略。"+new String(messageExt.getBody()));
            } catch (Exception e) {
                log.error("获取MQ消息内容异常{}", e);
            }

        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
