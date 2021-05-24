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

import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuiteAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuiteActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTestSuiteAction] 服务对象接口
 */
public interface IIBZTestSuiteActionService extends IService<IBZTestSuiteAction> {

    boolean create(IBZTestSuiteAction et);
    void createBatch(List<IBZTestSuiteAction> list);
    boolean update(IBZTestSuiteAction et);
    boolean sysUpdate(IBZTestSuiteAction et);
    void updateBatch(List<IBZTestSuiteAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZTestSuiteAction get(Long key);
    IBZTestSuiteAction sysGet(Long key);
    IBZTestSuiteAction getDraft(IBZTestSuiteAction et);
    boolean checkKey(IBZTestSuiteAction et);
    boolean save(IBZTestSuiteAction et);
    void saveBatch(List<IBZTestSuiteAction> list);
    List<IBZTestSuiteAction> select(IBZTestSuiteActionSearchContext context);
    List<IBZTestSuiteAction> selectDefault(IBZTestSuiteActionSearchContext context);
    List<IBZTestSuiteAction> selectSimple(IBZTestSuiteActionSearchContext context);
    List<IBZTestSuiteAction> selectView(IBZTestSuiteActionSearchContext context);

    Page<IBZTestSuiteAction> searchDefault(IBZTestSuiteActionSearchContext context);
    List<IBZTestSuiteAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZTestSuiteAction dynamicCall(Long key, String action, IBZTestSuiteAction et);
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

    List<IBZTestSuiteAction> getIbztestsuiteactionByIds(List<Long> ids);
    List<IBZTestSuiteAction> getIbztestsuiteactionByEntities(List<IBZTestSuiteAction> entities);
}


