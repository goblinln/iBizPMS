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

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproProductWeekly] 服务对象接口
 */
public interface IIbizproProductWeeklyService extends IService<IbizproProductWeekly> {

    boolean create(IbizproProductWeekly et);
    boolean update(IbizproProductWeekly et);
    boolean sysUpdate(IbizproProductWeekly et);
    boolean remove(Long key);
    IbizproProductWeekly get(Long key);
    IbizproProductWeekly sysGet(Long key);
    IbizproProductWeekly getDraft(IbizproProductWeekly et);
    boolean checkKey(IbizproProductWeekly et);
    boolean save(IbizproProductWeekly et);
    IbizproProductWeekly sumProductWeekly(IbizproProductWeekly et);
    List<IbizproProductWeekly> select(IbizproProductWeeklySearchContext context);
    List<IbizproProductWeekly> selectDefault(IbizproProductWeeklySearchContext context);
    List<IbizproProductWeekly> selectView(IbizproProductWeeklySearchContext context);

    Page<IbizproProductWeekly> searchDefault(IbizproProductWeeklySearchContext context);
    List<IbizproProductWeekly> selectByProduct(Long id);
    void removeByProduct(Long id);
    IbizproProductWeekly dynamicCall(Long key, String action, IbizproProductWeekly et);
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

    List<IbizproProductWeekly> getIbizproproductweeklyByIds(List<Long> ids);
    List<IbizproProductWeekly> getIbizproproductweeklyByEntities(List<IbizproProductWeekly> entities);
}


