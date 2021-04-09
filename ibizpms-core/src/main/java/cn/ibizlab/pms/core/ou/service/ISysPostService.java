package cn.ibizlab.pms.core.ou.service;

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

import cn.ibizlab.pms.core.ou.domain.SysPost;
import cn.ibizlab.pms.core.ou.filter.SysPostSearchContext;


/**
 * 实体[SysPost] 服务对象接口
 */
public interface ISysPostService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "岗位";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "sysposts";

    boolean create(SysPost et);
    void createBatch(List<SysPost> list);
    boolean update(SysPost et);
    void updateBatch(List<SysPost> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysPost get(String key);
    SysPost getDraft(SysPost et);
    boolean checkKey(SysPost et);
    boolean save(SysPost et);
    void saveBatch(List<SysPost> list);
    Page<SysPost> searchDefault(SysPostSearchContext context);
    SysPost dynamicCall(String key, String action, SysPost et);


}



