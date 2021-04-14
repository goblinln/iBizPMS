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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[IBIZProTag] 服务对象接口
 */
@Component
public class IBIZProTagFallback implements IBIZProTagFeignClient {

    public Page<IBIZProTag> select() {
        return null;
    }

    public IBIZProTag create(IBIZProTag ibizprotag) {
        return null;
    }
    public Boolean createBatch(List<IBIZProTag> ibizprotags) {
        return false;
    }

    public IBIZProTag update(String id, IBIZProTag ibizprotag) {
        return null;
    }
    public Boolean updateBatch(List<IBIZProTag> ibizprotags) {
        return false;
    }


    public Boolean remove(String id) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public IBIZProTag get(String id) {
        return null;
    }


    public IBIZProTag getDraft(IBIZProTag entity){
        return null;
    }



    public Boolean checkKey(IBIZProTag ibizprotag) {
        return false;
    }


    public Object saveEntity(IBIZProTag ibizprotag) {
        return null;
    }

    public Boolean save(IBIZProTag ibizprotag) {
        return false;
    }
    public Boolean saveBatch(List<IBIZProTag> ibizprotags) {
        return false;
    }

    public Page<IBIZProTag> searchDefault(IBIZProTagSearchContext context) {
        return null;
    }


}
