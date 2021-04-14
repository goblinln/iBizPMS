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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[IBIZProPlugin] 服务对象接口
 */
@Component
public class IBIZProPluginFallback implements IBIZProPluginFeignClient {

    public Page<IBIZProPlugin> select() {
        return null;
    }

    public IBIZProPlugin create(IBIZProPlugin ibizproplugin) {
        return null;
    }
    public Boolean createBatch(List<IBIZProPlugin> ibizproplugins) {
        return false;
    }

    public IBIZProPlugin update(String ibizpropluginid, IBIZProPlugin ibizproplugin) {
        return null;
    }
    public Boolean updateBatch(List<IBIZProPlugin> ibizproplugins) {
        return false;
    }


    public Boolean remove(String ibizpropluginid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public IBIZProPlugin get(String ibizpropluginid) {
        return null;
    }


    public IBIZProPlugin getDraft(IBIZProPlugin entity){
        return null;
    }



    public Boolean checkKey(IBIZProPlugin ibizproplugin) {
        return false;
    }


    public Object saveEntity(IBIZProPlugin ibizproplugin) {
        return null;
    }

    public Boolean save(IBIZProPlugin ibizproplugin) {
        return false;
    }
    public Boolean saveBatch(List<IBIZProPlugin> ibizproplugins) {
        return false;
    }

    public Page<IBIZProPlugin> searchDefault(IBIZProPluginSearchContext context) {
        return null;
    }


}
