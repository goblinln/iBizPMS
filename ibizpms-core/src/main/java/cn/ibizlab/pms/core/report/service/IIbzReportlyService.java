package cn.ibizlab.pms.core.report.service;

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

import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzReportly] 服务对象接口
 */
public interface IIbzReportlyService extends IService<IbzReportly> {

    boolean create(IbzReportly et);
    void createBatch(List<IbzReportly> list);
    boolean update(IbzReportly et);
    boolean sysUpdate(IbzReportly et);
    void updateBatch(List<IbzReportly> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzReportly get(Long key);
    IbzReportly sysGet(Long key);
    IbzReportly getDraft(IbzReportly et);
    boolean checkKey(IbzReportly et);
    IbzReportly haveRead(IbzReportly et);
    boolean haveReadBatch(List<IbzReportly> etList);
    boolean save(IbzReportly et);
    void saveBatch(List<IbzReportly> list);
    IbzReportly submit(IbzReportly et);
    boolean submitBatch(List<IbzReportly> etList);
    List<IbzReportly> select(IbzReportlySearchContext context);
    
    List<IbzReportly> selectQueryByDefault(IbzReportlySearchContext context);
    List<IbzReportly> selectQueryByMyAllReportly(IbzReportlySearchContext context);
    List<IbzReportly> selectQueryByMyReceived(IbzReportlySearchContext context);
    List<IbzReportly> selectQueryByMyReportlyMob(IbzReportlySearchContext context);
    List<IbzReportly> selectQueryByView(IbzReportlySearchContext context);

    Page<IbzReportly> searchDefault(IbzReportlySearchContext context);
    Page<IbzReportly> searchMyAllReportly(IbzReportlySearchContext context);
    Page<IbzReportly> searchMyReceived(IbzReportlySearchContext context);
    Page<IbzReportly> searchMyReportlyMob(IbzReportlySearchContext context);
    IbzReportly dynamicCall(Long key, String action, IbzReportly et);
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

    List<IbzReportly> getIbzreportlyByIds(List<Long> ids);
    List<IbzReportly> getIbzreportlyByEntities(List<IbzReportly> entities);
}


