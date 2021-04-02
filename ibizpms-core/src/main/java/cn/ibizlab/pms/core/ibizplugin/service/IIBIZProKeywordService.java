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

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;


/**
 * 实体[IBIZProKeyword] 服务对象接口
 */
public interface IIBIZProKeywordService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "关键字";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "ibizprokeywords";

    boolean create(IBIZProKeyword et);
    void createBatch(List<IBIZProKeyword> list);
    boolean update(IBIZProKeyword et);
    void updateBatch(List<IBIZProKeyword> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBIZProKeyword get(String key);
    IBIZProKeyword getDraft(IBIZProKeyword et);
    boolean checkKey(IBIZProKeyword et);
    boolean save(IBIZProKeyword et);
    void saveBatch(List<IBIZProKeyword> list);
    Page<IBIZProKeyword> searchDefault(IBIZProKeywordSearchContext context);
    IBIZProKeyword dynamicCall(String key, String action, IBIZProKeyword et);


}



