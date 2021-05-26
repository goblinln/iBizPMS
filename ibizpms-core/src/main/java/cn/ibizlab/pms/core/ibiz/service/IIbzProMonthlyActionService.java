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

import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProMonthlyActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProMonthlyAction] 服务对象接口
 */
public interface IIbzProMonthlyActionService extends IService<IbzProMonthlyAction> {

    boolean create(IbzProMonthlyAction et);
    void createBatch(List<IbzProMonthlyAction> list);
    boolean update(IbzProMonthlyAction et);
    boolean sysUpdate(IbzProMonthlyAction et);
    void updateBatch(List<IbzProMonthlyAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProMonthlyAction get(Long key);
    IbzProMonthlyAction sysGet(Long key);
    IbzProMonthlyAction getDraft(IbzProMonthlyAction et);
    boolean checkKey(IbzProMonthlyAction et);
    IbzProMonthlyAction createHis(IbzProMonthlyAction et);
    IbzProMonthlyAction managePmsEe(IbzProMonthlyAction et);
    boolean save(IbzProMonthlyAction et);
    void saveBatch(List<IbzProMonthlyAction> list);
    IbzProMonthlyAction sendMarkDone(IbzProMonthlyAction et);
    IbzProMonthlyAction sendTodo(IbzProMonthlyAction et);
    IbzProMonthlyAction sendToread(IbzProMonthlyAction et);
    List<IbzProMonthlyAction> select(IbzProMonthlyActionSearchContext context);
    List<IbzProMonthlyAction> selectDefault(IbzProMonthlyActionSearchContext context);
    List<IbzProMonthlyAction> selectSimple(IbzProMonthlyActionSearchContext context);
    List<IbzProMonthlyAction> selectType(IbzProMonthlyActionSearchContext context);
    List<IbzProMonthlyAction> selectView(IbzProMonthlyActionSearchContext context);

    Page<IbzProMonthlyAction> searchDefault(IbzProMonthlyActionSearchContext context);
    Page<IbzProMonthlyAction> searchType(IbzProMonthlyActionSearchContext context);
    List<IbzProMonthlyAction> selectByObjectid(Long ibzmonthlyid);
    void removeByObjectid(Long ibzmonthlyid);
    IbzProMonthlyAction dynamicCall(Long key, String action, IbzProMonthlyAction et);
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

    List<IbzProMonthlyAction> getIbzpromonthlyactionByIds(List<Long> ids);
    List<IbzProMonthlyAction> getIbzpromonthlyactionByEntities(List<IbzProMonthlyAction> entities);
}


