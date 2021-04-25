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
    void createBatch(List<IbzMonthly> list);
    boolean update(IbzMonthly et);
    boolean sysUpdate(IbzMonthly et);
    void updateBatch(List<IbzMonthly> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzMonthly get(Long key);
    IbzMonthly sysGet(Long key);
    IbzMonthly getDraft(IbzMonthly et);
    boolean checkKey(IbzMonthly et);
    IbzMonthly createGetInfo(IbzMonthly et);
    boolean createGetInfoBatch(List<IbzMonthly> etList);
    IbzMonthly createUserMonthly(IbzMonthly et);
    boolean createUserMonthlyBatch(List<IbzMonthly> etList);
    IbzMonthly editGetCompleteTask(IbzMonthly et);
    boolean editGetCompleteTaskBatch(List<IbzMonthly> etList);
    IbzMonthly haveRead(IbzMonthly et);
    boolean haveReadBatch(List<IbzMonthly> etList);
    IbzMonthly pushUserMonthly(IbzMonthly et);
    boolean pushUserMonthlyBatch(List<IbzMonthly> etList);
    boolean save(IbzMonthly et);
    void saveBatch(List<IbzMonthly> list);
    IbzMonthly submit(IbzMonthly et);
    boolean submitBatch(List<IbzMonthly> etList);
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


