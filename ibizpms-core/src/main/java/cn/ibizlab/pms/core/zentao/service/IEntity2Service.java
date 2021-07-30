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

import cn.ibizlab.pms.core.zentao.domain.Entity2;
import cn.ibizlab.pms.core.zentao.filter.Entity2SearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Entity2] 服务对象接口
 */
public interface IEntity2Service extends IService<Entity2> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "实体2";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "entity2s";

    boolean create(Entity2 et);
    void createBatch(List<Entity2> list);
    boolean update(Entity2 et);
    void updateBatch(List<Entity2> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    Entity2 get(String key);
    Entity2 getDraft(Entity2 et);
    boolean checkKey(Entity2 et);
    boolean save(Entity2 et);
    void saveBatch(List<Entity2> list);
    Page<Entity2> searchDefault(Entity2SearchContext context);
    /**
     *自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     *自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<Entity2> getEntity2ByIds(List<String> ids);
    List<Entity2> getEntity2ByEntities(List<Entity2> entities);
}


