package cn.ibizlab.pms.core.ibiz.service;

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

import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProToDoActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProToDoAction] 服务对象接口
 */
public interface IIBZProToDoActionService extends IService<IBZProToDoAction> {

    boolean create(IBZProToDoAction et);
    void createBatch(List<IBZProToDoAction> list);
    boolean update(IBZProToDoAction et);
    boolean sysUpdate(IBZProToDoAction et);
    void updateBatch(List<IBZProToDoAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProToDoAction get(Long key);
    IBZProToDoAction sysGet(Long key);
    IBZProToDoAction getDraft(IBZProToDoAction et);
    boolean checkKey(IBZProToDoAction et);
    IBZProToDoAction comment(IBZProToDoAction et);
    IBZProToDoAction createHis(IBZProToDoAction et);
    IBZProToDoAction editComment(IBZProToDoAction et);
    IBZProToDoAction managePmsEe(IBZProToDoAction et);
    boolean save(IBZProToDoAction et);
    void saveBatch(List<IBZProToDoAction> list);
    IBZProToDoAction sendMarkDone(IBZProToDoAction et);
    IBZProToDoAction sendTodo(IBZProToDoAction et);
    IBZProToDoAction sendToread(IBZProToDoAction et);
    List<IBZProToDoAction> select(IBZProToDoActionSearchContext context);
    List<IBZProToDoAction> selectDefault(IBZProToDoActionSearchContext context);
    List<IBZProToDoAction> selectSimple(IBZProToDoActionSearchContext context);
    List<IBZProToDoAction> selectType(IBZProToDoActionSearchContext context);
    List<IBZProToDoAction> selectView(IBZProToDoActionSearchContext context);

    Page<IBZProToDoAction> searchDefault(IBZProToDoActionSearchContext context);
    Page<IBZProToDoAction> searchType(IBZProToDoActionSearchContext context);
    List<IBZProToDoAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZProToDoAction dynamicCall(Long key, String action, IBZProToDoAction et);
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

    List<IBZProToDoAction> getIbzprotodoactionByIds(List<Long> ids);
    List<IBZProToDoAction> getIbzprotodoactionByEntities(List<IBZProToDoAction> entities);
}


