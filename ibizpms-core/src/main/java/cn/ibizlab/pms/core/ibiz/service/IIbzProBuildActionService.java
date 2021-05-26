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

import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProBuildAction] 服务对象接口
 */
public interface IIbzProBuildActionService extends IService<IbzProBuildAction> {

    boolean create(IbzProBuildAction et);
    void createBatch(List<IbzProBuildAction> list);
    boolean update(IbzProBuildAction et);
    boolean sysUpdate(IbzProBuildAction et);
    void updateBatch(List<IbzProBuildAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProBuildAction get(Long key);
    IbzProBuildAction sysGet(Long key);
    IbzProBuildAction getDraft(IbzProBuildAction et);
    boolean checkKey(IbzProBuildAction et);
    IbzProBuildAction comment(IbzProBuildAction et);
    IbzProBuildAction createHis(IbzProBuildAction et);
    IbzProBuildAction editComment(IbzProBuildAction et);
    IbzProBuildAction managePmsEe(IbzProBuildAction et);
    boolean save(IbzProBuildAction et);
    void saveBatch(List<IbzProBuildAction> list);
    IbzProBuildAction sendMarkDone(IbzProBuildAction et);
    IbzProBuildAction sendTodo(IbzProBuildAction et);
    IbzProBuildAction sendToread(IbzProBuildAction et);
    List<IbzProBuildAction> select(IbzProBuildActionSearchContext context);
    List<IbzProBuildAction> selectDefault(IbzProBuildActionSearchContext context);
    List<IbzProBuildAction> selectSimple(IbzProBuildActionSearchContext context);
    List<IbzProBuildAction> selectType(IbzProBuildActionSearchContext context);
    List<IbzProBuildAction> selectView(IbzProBuildActionSearchContext context);

    Page<IbzProBuildAction> searchDefault(IbzProBuildActionSearchContext context);
    Page<IbzProBuildAction> searchType(IbzProBuildActionSearchContext context);
    List<IbzProBuildAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IbzProBuildAction dynamicCall(Long key, String action, IbzProBuildAction et);
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

    List<IbzProBuildAction> getIbzprobuildactionByIds(List<Long> ids);
    List<IbzProBuildAction> getIbzprobuildactionByEntities(List<IbzProBuildAction> entities);
}


