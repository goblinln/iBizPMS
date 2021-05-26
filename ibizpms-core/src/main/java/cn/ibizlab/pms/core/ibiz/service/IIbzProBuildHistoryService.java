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

import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProBuildHistory] 服务对象接口
 */
public interface IIbzProBuildHistoryService extends IService<IbzProBuildHistory> {

    boolean create(IbzProBuildHistory et);
    boolean update(IbzProBuildHistory et);
    boolean sysUpdate(IbzProBuildHistory et);
    boolean remove(Long key);
    IbzProBuildHistory get(Long key);
    IbzProBuildHistory sysGet(Long key);
    IbzProBuildHistory getDraft(IbzProBuildHistory et);
    boolean checkKey(IbzProBuildHistory et);
    boolean save(IbzProBuildHistory et);
    List<IbzProBuildHistory> select(IbzProBuildHistorySearchContext context);
    List<IbzProBuildHistory> selectDefault(IbzProBuildHistorySearchContext context);
    List<IbzProBuildHistory> selectSimple(IbzProBuildHistorySearchContext context);
    List<IbzProBuildHistory> selectView(IbzProBuildHistorySearchContext context);

    Page<IbzProBuildHistory> searchDefault(IbzProBuildHistorySearchContext context);
    List<IbzProBuildHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IbzProBuildHistory> list) ;
    IbzProBuildHistory dynamicCall(Long key, String action, IbzProBuildHistory et);
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

    List<IbzProBuildHistory> getIbzprobuildhistoryByIds(List<Long> ids);
    List<IbzProBuildHistory> getIbzprobuildhistoryByEntities(List<IbzProBuildHistory> entities);
}


