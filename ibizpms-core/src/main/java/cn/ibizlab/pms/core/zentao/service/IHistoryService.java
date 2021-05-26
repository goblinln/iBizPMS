package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[History] 服务对象接口
 */
public interface IHistoryService extends IService<History> {

    boolean create(History et);
    boolean update(History et);
    boolean sysUpdate(History et);
    boolean remove(Long key);
    History get(Long key);
    History sysGet(Long key);
    History getDraft(History et);
    boolean checkKey(History et);
    boolean save(History et);
    List<History> select(HistorySearchContext context);
    List<History> selectDefault(HistorySearchContext context);
    List<History> selectView(HistorySearchContext context);

    Page<History> searchDefault(HistorySearchContext context);
    List<History> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<History> list) ;
    History dynamicCall(Long key, String action, History et);
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

    List<History> getHistoryByIds(List<Long> ids);
    List<History> getHistoryByEntities(List<History> entities);
}


