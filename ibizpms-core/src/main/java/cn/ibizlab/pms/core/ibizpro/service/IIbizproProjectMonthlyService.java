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

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproProjectMonthly] 服务对象接口
 */
public interface IIbizproProjectMonthlyService extends IService<IbizproProjectMonthly> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "项目月报";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "ibizproprojectmonthlies";

    boolean create(IbizproProjectMonthly et);
    void createBatch(List<IbizproProjectMonthly> list);
    boolean update(IbizproProjectMonthly et);
    void updateBatch(List<IbizproProjectMonthly> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbizproProjectMonthly get(String key);
    IbizproProjectMonthly getDraft(IbizproProjectMonthly et);
    boolean checkKey(IbizproProjectMonthly et);
    IbizproProjectMonthly manualCreateMonthly(IbizproProjectMonthly et);
    boolean manualCreateMonthlyBatch(List<IbizproProjectMonthly> etList);
    boolean save(IbizproProjectMonthly et);
    void saveBatch(List<IbizproProjectMonthly> list);
    IbizproProjectMonthly sumProjectMonthly(IbizproProjectMonthly et);
    boolean sumProjectMonthlyBatch(List<IbizproProjectMonthly> etList);
    Page<IbizproProjectMonthly> searchDefault(IbizproProjectMonthlySearchContext context);
    List<IbizproProjectMonthly> selectByProject(Long id);
    void removeByProject(Long id);
    IbizproProjectMonthly dynamicCall(String key, String action, IbizproProjectMonthly et);
    /**
     * 自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     * 自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<IbizproProjectMonthly> getIbizproprojectmonthlyByIds(List<String> ids);
    List<IbizproProjectMonthly> getIbizproprojectmonthlyByEntities(List<IbizproProjectMonthly> entities);
}


