package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizplugin.service.impl.IBIZProMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[消息] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IBIZProMessageExService")
public class IBIZProMessageExService extends IBIZProMessageServiceImpl {


    /**
     * [MarkDone:标记已完成] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBIZProMessage markDone(IBIZProMessage et) {
        return et;
    }
    /**
     * [MarkRead:标记已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBIZProMessage markRead(IBIZProMessage et) {
        return et;
    }
    /**
     * [Send:发送消息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBIZProMessage send(IBIZProMessage et) {
        return et;
    }
}

