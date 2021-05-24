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

import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProReportlyActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProReportlyAction] 服务对象接口
 */
public interface IIbzProReportlyActionService extends IService<IbzProReportlyAction> {

    boolean create(IbzProReportlyAction et);
    void createBatch(List<IbzProReportlyAction> list);
    boolean update(IbzProReportlyAction et);
    boolean sysUpdate(IbzProReportlyAction et);
    void updateBatch(List<IbzProReportlyAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProReportlyAction get(Long key);
    IbzProReportlyAction sysGet(Long key);
    IbzProReportlyAction getDraft(IbzProReportlyAction et);
    boolean checkKey(IbzProReportlyAction et);
    boolean save(IbzProReportlyAction et);
    void saveBatch(List<IbzProReportlyAction> list);
    List<IbzProReportlyAction> select(IbzProReportlyActionSearchContext context);
    List<IbzProReportlyAction> selectDefault(IbzProReportlyActionSearchContext context);
    List<IbzProReportlyAction> selectSimple(IbzProReportlyActionSearchContext context);
    List<IbzProReportlyAction> selectView(IbzProReportlyActionSearchContext context);

    Page<IbzProReportlyAction> searchDefault(IbzProReportlyActionSearchContext context);
    IbzProReportlyAction dynamicCall(Long key, String action, IbzProReportlyAction et);
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

    List<IbzProReportlyAction> getIbzproreportlyactionByIds(List<Long> ids);
    List<IbzProReportlyAction> getIbzproreportlyactionByEntities(List<IbzProReportlyAction> entities);
}


