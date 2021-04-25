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

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;


/**
 * 实体[IBIZProTag] 服务对象接口
 */
public interface IIBIZProTagService {

    boolean create(IBIZProTag et);
    void createBatch(List<IBIZProTag> list);
    boolean update(IBIZProTag et);
    boolean sysUpdate(IBIZProTag et);
    void updateBatch(List<IBIZProTag> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBIZProTag get(String key);
    IBIZProTag sysGet(String key);
    IBIZProTag getDraft(IBIZProTag et);
    boolean checkKey(IBIZProTag et);
    boolean save(IBIZProTag et);
    void saveBatch(List<IBIZProTag> list);

    Page<IBIZProTag> searchDefault(IBIZProTagSearchContext context);
    IBIZProTag dynamicCall(String key, String action, IBIZProTag et);
}



