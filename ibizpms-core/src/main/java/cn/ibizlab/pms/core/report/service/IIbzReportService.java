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

import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzReport] 服务对象接口
 */
public interface IIbzReportService extends IService<IbzReport> {

    boolean create(IbzReport et);
    void createBatch(List<IbzReport> list);
    boolean update(IbzReport et);
    boolean sysUpdate(IbzReport et);
    void updateBatch(List<IbzReport> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzReport get(Long key);
    IbzReport sysGet(Long key);
    IbzReport getDraft(IbzReport et);
    boolean checkKey(IbzReport et);
    IbzReport myReportINotSubmit(IbzReport et);
    IbzReport reportIReceived(IbzReport et);
    boolean save(IbzReport et);
    void saveBatch(List<IbzReport> list);
    List<IbzReport> select(IbzReportSearchContext context);
    List<IbzReport> selectAllReport(IbzReportSearchContext context);
    List<IbzReport> selectDefault(IbzReportSearchContext context);
    List<IbzReport> selectMyReAllReport(IbzReportSearchContext context);
    List<IbzReport> selectView(IbzReportSearchContext context);

    Page<IbzReport> searchAllReport(IbzReportSearchContext context);
    Page<IbzReport> searchDefault(IbzReportSearchContext context);
    Page<IbzReport> searchMyReAllReport(IbzReportSearchContext context);
    IbzReport dynamicCall(Long key, String action, IbzReport et);
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

    List<IbzReport> getIbzreportByIds(List<Long> ids);
    List<IbzReport> getIbzreportByEntities(List<IbzReport> entities);
}


