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

import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzWeekly] 服务对象接口
 */
public interface IIbzWeeklyService extends IService<IbzWeekly> {

    boolean create(IbzWeekly et);
    boolean update(IbzWeekly et);
    boolean sysUpdate(IbzWeekly et);
    boolean remove(Long key);
    IbzWeekly get(Long key);
    IbzWeekly sysGet(Long key);
    IbzWeekly getDraft(IbzWeekly et);
    boolean checkKey(IbzWeekly et);
    IbzWeekly createEveryWeekReport(IbzWeekly et);
    IbzWeekly createGetLastWeekPlanAndWork(IbzWeekly et);
    IbzWeekly editGetLastWeekTaskAndComTask(IbzWeekly et);
    IbzWeekly haveRead(IbzWeekly et);
    IbzWeekly jugThisWeekCreateWeekly(IbzWeekly et);
    IbzWeekly pushUserWeekly(IbzWeekly et);
    boolean save(IbzWeekly et);
    IbzWeekly submit(IbzWeekly et);
    List<IbzWeekly> select(IbzWeeklySearchContext context);
    List<IbzWeekly> selectDefault(IbzWeeklySearchContext context);
    List<IbzWeekly> selectMyNotSubmit(IbzWeeklySearchContext context);
    List<IbzWeekly> selectMyWeekly(IbzWeeklySearchContext context);
    List<IbzWeekly> selectProductTeamMemberWeekly(IbzWeeklySearchContext context);
    List<IbzWeekly> selectProjectWeekly(IbzWeeklySearchContext context);
    List<IbzWeekly> selectView(IbzWeeklySearchContext context);

    Page<IbzWeekly> searchDefault(IbzWeeklySearchContext context);
    Page<IbzWeekly> searchMyNotSubmit(IbzWeeklySearchContext context);
    Page<IbzWeekly> searchMyWeekly(IbzWeeklySearchContext context);
    Page<IbzWeekly> searchProductTeamMemberWeekly(IbzWeeklySearchContext context);
    Page<IbzWeekly> searchProjectWeekly(IbzWeeklySearchContext context);
    IbzWeekly dynamicCall(Long key, String action, IbzWeekly et);
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

    List<IbzWeekly> getIbzweeklyByIds(List<Long> ids);
    List<IbzWeekly> getIbzweeklyByEntities(List<IbzWeekly> entities);
}


