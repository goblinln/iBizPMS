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

import cn.ibizlab.pms.core.ibiz.domain.IbzProBugAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBugActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProBugAction] 服务对象接口
 */
public interface IIbzProBugActionService extends IService<IbzProBugAction> {

    boolean create(IbzProBugAction et);
    void createBatch(List<IbzProBugAction> list);
    boolean update(IbzProBugAction et);
    boolean sysUpdate(IbzProBugAction et);
    void updateBatch(List<IbzProBugAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProBugAction get(Long key);
    IbzProBugAction sysGet(Long key);
    IbzProBugAction getDraft(IbzProBugAction et);
    boolean checkKey(IbzProBugAction et);
    IbzProBugAction comment(IbzProBugAction et);
    IbzProBugAction createHis(IbzProBugAction et);
    IbzProBugAction editComment(IbzProBugAction et);
    IbzProBugAction managePmsEe(IbzProBugAction et);
    boolean save(IbzProBugAction et);
    void saveBatch(List<IbzProBugAction> list);
    IbzProBugAction sendMarkDone(IbzProBugAction et);
    IbzProBugAction sendTodo(IbzProBugAction et);
    IbzProBugAction sendToread(IbzProBugAction et);
    List<IbzProBugAction> select(IbzProBugActionSearchContext context);
    List<IbzProBugAction> selectDefault(IbzProBugActionSearchContext context);
    List<IbzProBugAction> selectSimple(IbzProBugActionSearchContext context);
    List<IbzProBugAction> selectType(IbzProBugActionSearchContext context);
    List<IbzProBugAction> selectView(IbzProBugActionSearchContext context);

    Page<IbzProBugAction> searchDefault(IbzProBugActionSearchContext context);
    Page<IbzProBugAction> searchType(IbzProBugActionSearchContext context);
    List<IbzProBugAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IbzProBugAction dynamicCall(Long key, String action, IbzProBugAction et);
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

    List<IbzProBugAction> getIbzprobugactionByIds(List<Long> ids);
    List<IbzProBugAction> getIbzprobugactionByEntities(List<IbzProBugAction> entities);
}


