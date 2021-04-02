package cn.ibizlab.pms.core.ibizpro.service;

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

import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzproProductUserTask] 服务对象接口
 */
public interface IIbzproProductUserTaskService extends IService<IbzproProductUserTask> {

    boolean create(IbzproProductUserTask et);
    void createBatch(List<IbzproProductUserTask> list);
    boolean update(IbzproProductUserTask et);
    void updateBatch(List<IbzproProductUserTask> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzproProductUserTask get(Long key);
    IbzproProductUserTask getDraft(IbzproProductUserTask et);
    boolean checkKey(IbzproProductUserTask et);
    boolean save(IbzproProductUserTask et);
    void saveBatch(List<IbzproProductUserTask> list);
    Page<IbzproProductUserTask> searchDefault(IbzproProductUserTaskSearchContext context);
    Page<IbzproProductUserTask> searchProductDailyUserTaskStats(IbzproProductUserTaskSearchContext context);
    Page<IbzproProductUserTask> searchProductMonthlyUserTaskStats(IbzproProductUserTaskSearchContext context);
    Page<IbzproProductUserTask> searchProductWeeklyUserTaskStats(IbzproProductUserTaskSearchContext context);
    IbzproProductUserTask dynamicCall(Long key, String action, IbzproProductUserTask et);
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

}


