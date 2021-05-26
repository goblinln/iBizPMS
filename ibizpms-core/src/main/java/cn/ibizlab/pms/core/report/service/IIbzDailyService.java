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

import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzDaily] 服务对象接口
 */
public interface IIbzDailyService extends IService<IbzDaily> {

    boolean create(IbzDaily et);
    boolean update(IbzDaily et);
    boolean sysUpdate(IbzDaily et);
    boolean remove(Long key);
    IbzDaily get(Long key);
    IbzDaily sysGet(Long key);
    IbzDaily getDraft(IbzDaily et);
    boolean checkKey(IbzDaily et);
    IbzDaily createUserDaily(IbzDaily et);
    IbzDaily getYeaterdayDailyPlansTaskEdit(IbzDaily et);
    IbzDaily getYesterdayDailyPlansTask(IbzDaily et);
    IbzDaily haveRead(IbzDaily et);
    IbzDaily linkCompleteTask(IbzDaily et);
    IbzDaily pushUserDaily(IbzDaily et);
    boolean save(IbzDaily et);
    IbzDaily submit(IbzDaily et);
    List<IbzDaily> select(IbzDailySearchContext context);
    List<IbzDaily> selectDefault(IbzDailySearchContext context);
    List<IbzDaily> selectMyAllDaily(IbzDailySearchContext context);
    List<IbzDaily> selectMyDaily(IbzDailySearchContext context);
    List<IbzDaily> selectMyNotSubmit(IbzDailySearchContext context);
    List<IbzDaily> selectMySubmitDaily(IbzDailySearchContext context);
    List<IbzDaily> selectProductDaily(IbzDailySearchContext context);
    List<IbzDaily> selectProjectDaily(IbzDailySearchContext context);
    List<IbzDaily> selectView(IbzDailySearchContext context);

    Page<IbzDaily> searchDefault(IbzDailySearchContext context);
    Page<IbzDaily> searchMyAllDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchMyDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchMyNotSubmit(IbzDailySearchContext context);
    Page<IbzDaily> searchMySubmitDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchProductDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchProjectDaily(IbzDailySearchContext context);
    IbzDaily dynamicCall(Long key, String action, IbzDaily et);
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

    List<IbzDaily> getIbzdailyByIds(List<Long> ids);
    List<IbzDaily> getIbzdailyByEntities(List<IbzDaily> entities);
}


