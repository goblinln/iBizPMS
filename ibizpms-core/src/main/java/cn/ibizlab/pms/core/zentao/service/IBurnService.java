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

import cn.ibizlab.pms.core.zentao.domain.Burn;
import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Burn] 服务对象接口
 */
public interface IBurnService extends IService<Burn> {

    boolean create(Burn et);
    void createBatch(List<Burn> list);
    boolean update(Burn et);
    boolean sysUpdate(Burn et);
    void updateBatch(List<Burn> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    Burn get(String key);
    Burn sysGet(String key);
    Burn getDraft(Burn et);
    boolean checkKey(Burn et);
    Burn computeBurn(Burn et);
    boolean computeBurnBatch(List<Burn> etList);
    boolean save(Burn et);
    void saveBatch(List<Burn> list);
    List<Burn> select(BurnSearchContext context);
    List<Burn> selectQueryByDefault(BurnSearchContext context);
    List<Burn> selectQueryByESTIMATEANDLEFT(BurnSearchContext context);
    List<Burn> selectQueryByView(BurnSearchContext context);

    Page<Burn> searchDefault(BurnSearchContext context);
    Page<Burn> searchESTIMATEANDLEFT(BurnSearchContext context);
    List<Burn> selectByProject(Long id);
    void removeByProject(Long id);
    List<Burn> selectByTask(Long id);
    void removeByTask(Long id);
    Burn dynamicCall(String key, String action, Burn et);
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

    List<Burn> getBurnByIds(List<String> ids);
    List<Burn> getBurnByEntities(List<Burn> entities);
}


