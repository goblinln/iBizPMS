package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.ImQueue;
import cn.ibizlab.pms.core.zentao.filter.ImQueueSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ImQueue] 服务对象接口
 */
public interface IImQueueService extends IService<ImQueue> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "ImQueue";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "imqueues";

    boolean create(ImQueue et);
    void createBatch(List<ImQueue> list);
    boolean update(ImQueue et);
    void updateBatch(List<ImQueue> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ImQueue get(Long key);
    ImQueue getDraft(ImQueue et);
    boolean checkKey(ImQueue et);
    boolean save(ImQueue et);
    void saveBatch(List<ImQueue> list);
    Page<ImQueue> searchDefault(ImQueueSearchContext context);
    ImQueue dynamicCall(Long key, String action, ImQueue et);
    /**
     * 自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     * 自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

}


