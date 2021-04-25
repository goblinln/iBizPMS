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

import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzMyTerritory] 服务对象接口
 */
public interface IIbzMyTerritoryService extends IService<IbzMyTerritory> {

    boolean create(IbzMyTerritory et);
    void createBatch(List<IbzMyTerritory> list);
    boolean update(IbzMyTerritory et);
    boolean sysUpdate(IbzMyTerritory et);
    void updateBatch(List<IbzMyTerritory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzMyTerritory get(Long key);
    IbzMyTerritory sysGet(Long key);
    IbzMyTerritory getDraft(IbzMyTerritory et);
    boolean checkKey(IbzMyTerritory et);
    IbzMyTerritory mobMenuCount(IbzMyTerritory et);
    boolean mobMenuCountBatch(List<IbzMyTerritory> etList);
    IbzMyTerritory myFavoriteCount(IbzMyTerritory et);
    boolean myFavoriteCountBatch(List<IbzMyTerritory> etList);
    IbzMyTerritory myTerritoryCount(IbzMyTerritory et);
    boolean myTerritoryCountBatch(List<IbzMyTerritory> etList);
    boolean save(IbzMyTerritory et);
    void saveBatch(List<IbzMyTerritory> list);
    List<IbzMyTerritory> select(IbzMyTerritorySearchContext context);
    
    List<IbzMyTerritory> selectQueryByDefault(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByMyWork(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByMyWorkMob(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByMyWorkPm(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByPersonInfo(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByView(IbzMyTerritorySearchContext context);
    List<IbzMyTerritory> selectQueryByWelcome(IbzMyTerritorySearchContext context);

    Page<IbzMyTerritory> searchDefault(IbzMyTerritorySearchContext context);
    Page<IbzMyTerritory> searchMyWork(IbzMyTerritorySearchContext context);
    Page<IbzMyTerritory> searchMyWorkMob(IbzMyTerritorySearchContext context);
    Page<IbzMyTerritory> searchMyWorkPm(IbzMyTerritorySearchContext context);
    Page<IbzMyTerritory> searchPersonInfo(IbzMyTerritorySearchContext context);
    Page<IbzMyTerritory> searchWelcome(IbzMyTerritorySearchContext context);
    IbzMyTerritory dynamicCall(Long key, String action, IbzMyTerritory et);
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


