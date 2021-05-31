package cn.ibizlab.pms.core.ibizplugin.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;


/**
 * 实体[IBIZProPlugin] 服务对象接口
 */
public interface IIBIZProPluginService {

    boolean create(IBIZProPlugin et);
    void createBatch(List<IBIZProPlugin> list);
    boolean update(IBIZProPlugin et);
    boolean sysUpdate(IBIZProPlugin et);
    void updateBatch(List<IBIZProPlugin> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBIZProPlugin get(String key);
    IBIZProPlugin sysGet(String key);
    IBIZProPlugin getDraft(IBIZProPlugin et);
    boolean checkKey(IBIZProPlugin et);
    boolean save(IBIZProPlugin et);
    void saveBatch(List<IBIZProPlugin> list);

    Page<IBIZProPlugin> searchDefault(IBIZProPluginSearchContext context);
    IBIZProPlugin dynamicCall(String key, String action, IBIZProPlugin et);
}



