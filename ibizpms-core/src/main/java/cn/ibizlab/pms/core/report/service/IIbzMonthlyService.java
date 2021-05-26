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

import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzMonthly] 服务对象接口
 */
public interface IIbzMonthlyService extends IService<IbzMonthly> {

    boolean create(IbzMonthly et);
    boolean update(IbzMonthly et);
    boolean sysUpdate(IbzMonthly et);
    boolean remove(Long key);
    IbzMonthly get(Long key);
    IbzMonthly sysGet(Long key);
    IbzMonthly getDraft(IbzMonthly et);
    boolean checkKey(IbzMonthly et);
    IbzMonthly createGetInfo(IbzMonthly et);
    IbzMonthly createUserMonthly(IbzMonthly et);
    IbzMonthly editGetCompleteTask(IbzMonthly et);
    IbzMonthly haveRead(IbzMonthly et);
    IbzMonthly pushUserMonthly(IbzMonthly et);
    boolean save(IbzMonthly et);
    IbzMonthly submit(IbzMonthly et);
    List<IbzMonthly> select(IbzMonthlySearchContext context);
    List<IbzMonthly> selectDefault(IbzMonthlySearchContext context);
    List<IbzMonthly> selectMyMonthly(IbzMonthlySearchContext context);
    List<IbzMonthly> selectMyMonthlyMob(IbzMonthlySearchContext context);
    List<IbzMonthly> selectMyReceivedMonthly(IbzMonthlySearchContext context);
    List<IbzMonthly> selectMySubmitMonthly(IbzMonthlySearchContext context);
    List<IbzMonthly> selectProductMonthly(IbzMonthlySearchContext context);
    List<IbzMonthly> selectProjectMonthly(IbzMonthlySearchContext context);
    List<IbzMonthly> selectView(IbzMonthlySearchContext context);

    Page<IbzMonthly> searchDefault(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchMyMonthly(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchMyMonthlyMob(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchMyReceivedMonthly(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchMySubmitMonthly(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchProductMonthly(IbzMonthlySearchContext context);
    Page<IbzMonthly> searchProjectMonthly(IbzMonthlySearchContext context);
    IbzMonthly dynamicCall(Long key, String action, IbzMonthly et);
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

    List<IbzMonthly> getIbzmonthlyByIds(List<Long> ids);
    List<IbzMonthly> getIbzmonthlyByEntities(List<IbzMonthly> entities);
}


