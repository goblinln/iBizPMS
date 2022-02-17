package cn.ibizlab.pms.core.ibizplugin.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[IBIZProMessage] 服务对象接口
 */
@Component
public class IBIZProMessageFallback implements IBIZProMessageFeignClient {

    public IBIZProMessage create(IBIZProMessage et) {
        return null;
    }
    public Boolean createBatch(List<IBIZProMessage> ibizpromessages) {
        return false;
    }

    public IBIZProMessage get(String ibizpromessageid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public Boolean remove(String ibizpromessageid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public IBIZProMessage update(String ibizpromessageid, IBIZProMessage et) {
        return null;
    }
    public Boolean updateBatch(List<IBIZProMessage> ibizpromessages) {
        return false;
    }


    public Boolean checkKey(IBIZProMessage et) {
        return false;
    }


    public Page<IBIZProMessage> searchDefault(IBIZProMessageSearchContext context) {
        return null;
    }


    public Page<IBIZProMessage> searchUserAllMessages(IBIZProMessageSearchContext context) {
        return null;
    }


    public Page<IBIZProMessage> searchUserUnreadMessages(IBIZProMessageSearchContext context) {
        return null;
    }


    public IBIZProMessage getDraft(IBIZProMessage entity){
        return null;
    }



    public IBIZProMessage markDone( String ibizpromessageid, IBIZProMessage et) {
        return null;
    }

    public IBIZProMessage markRead( String ibizpromessageid, IBIZProMessage et) {
        return null;
    }

    public Object saveEntity(IBIZProMessage et) {
        return null;
    }
    public Boolean save(IBIZProMessage et) {
        return false;
    }
    public Boolean saveBatch(List<IBIZProMessage> ibizpromessages) {
        return false;
    }

    public Page<IBIZProMessage> select() {
        return null;
    }

    public IBIZProMessage send( String ibizpromessageid, IBIZProMessage et) {
        return null;
    }

}
