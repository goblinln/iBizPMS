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

import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProReleaseHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProReleaseHistory] 服务对象接口
 */
public interface IIBZProReleaseHistoryService extends IService<IBZProReleaseHistory> {

    boolean create(IBZProReleaseHistory et);
    boolean update(IBZProReleaseHistory et);
    boolean sysUpdate(IBZProReleaseHistory et);
    boolean remove(Long key);
    IBZProReleaseHistory get(Long key);
    IBZProReleaseHistory sysGet(Long key);
    IBZProReleaseHistory getDraft(IBZProReleaseHistory et);
    boolean checkKey(IBZProReleaseHistory et);
    boolean save(IBZProReleaseHistory et);
    List<IBZProReleaseHistory> select(IBZProReleaseHistorySearchContext context);
    List<IBZProReleaseHistory> selectDefault(IBZProReleaseHistorySearchContext context);
    List<IBZProReleaseHistory> selectSimple(IBZProReleaseHistorySearchContext context);
    List<IBZProReleaseHistory> selectView(IBZProReleaseHistorySearchContext context);

    Page<IBZProReleaseHistory> searchDefault(IBZProReleaseHistorySearchContext context);
    List<IBZProReleaseHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProReleaseHistory> list) ;
    IBZProReleaseHistory dynamicCall(Long key, String action, IBZProReleaseHistory et);
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

    List<IBZProReleaseHistory> getIbzproreleasehistoryByIds(List<Long> ids);
    List<IBZProReleaseHistory> getIbzproreleasehistoryByEntities(List<IBZProReleaseHistory> entities);
}


