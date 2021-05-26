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

import cn.ibizlab.pms.core.ibiz.domain.IBZStoryAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZStoryActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZStoryAction] 服务对象接口
 */
public interface IIBZStoryActionService extends IService<IBZStoryAction> {

    boolean create(IBZStoryAction et);
    void createBatch(List<IBZStoryAction> list);
    boolean update(IBZStoryAction et);
    boolean sysUpdate(IBZStoryAction et);
    void updateBatch(List<IBZStoryAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZStoryAction get(Long key);
    IBZStoryAction sysGet(Long key);
    IBZStoryAction getDraft(IBZStoryAction et);
    boolean checkKey(IBZStoryAction et);
    IBZStoryAction comment(IBZStoryAction et);
    IBZStoryAction createHis(IBZStoryAction et);
    IBZStoryAction editComment(IBZStoryAction et);
    IBZStoryAction managePmsEe(IBZStoryAction et);
    boolean save(IBZStoryAction et);
    void saveBatch(List<IBZStoryAction> list);
    IBZStoryAction sendMarkDone(IBZStoryAction et);
    IBZStoryAction sendTodo(IBZStoryAction et);
    IBZStoryAction sendToread(IBZStoryAction et);
    List<IBZStoryAction> select(IBZStoryActionSearchContext context);
    List<IBZStoryAction> selectDefault(IBZStoryActionSearchContext context);
    List<IBZStoryAction> selectSimple(IBZStoryActionSearchContext context);
    List<IBZStoryAction> selectType(IBZStoryActionSearchContext context);
    List<IBZStoryAction> selectView(IBZStoryActionSearchContext context);

    Page<IBZStoryAction> searchDefault(IBZStoryActionSearchContext context);
    Page<IBZStoryAction> searchType(IBZStoryActionSearchContext context);
    List<IBZStoryAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    void saveByObjectid(Long id, List<IBZStoryAction> list) ;
    IBZStoryAction dynamicCall(Long key, String action, IBZStoryAction et);
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

    List<IBZStoryAction> getIbzstoryactionByIds(List<Long> ids);
    List<IBZStoryAction> getIbzstoryactionByEntities(List<IBZStoryAction> entities);
}


