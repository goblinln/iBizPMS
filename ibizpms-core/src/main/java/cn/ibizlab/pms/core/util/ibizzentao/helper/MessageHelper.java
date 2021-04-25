package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.ImMessage;
import cn.ibizlab.pms.core.zentao.mapper.ImMessageMapper;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class MessageHelper extends ZTBaseHelper<ImMessageMapper, ImMessage> {

    /**
     * edit 编辑
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(ImMessage et) {
        return this.internalUpdate(et);
    }


    @Transactional(rollbackFor = Exception.class)
    public void send(String objectType, Long objectID, String actionType, Long actionID, String actor) {
        //全局配置
        JSONObject setting = new JSONObject();
        log.info("发送消息：未实现。");
    }
}