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

import cn.ibizlab.pms.core.zentao.domain.Compile;
import cn.ibizlab.pms.core.zentao.filter.CompileSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Compile] 服务对象接口
 */
public interface ICompileService extends IService<Compile> {

    boolean create(Compile et);
    void createBatch(List<Compile> list);
    boolean update(Compile et);
    boolean sysUpdate(Compile et);
    void updateBatch(List<Compile> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Compile get(Long key);
    Compile sysGet(Long key);
    Compile getDraft(Compile et);
    boolean checkKey(Compile et);
    boolean save(Compile et);
    void saveBatch(List<Compile> list);
    List<Compile> select(CompileSearchContext context);
    List<Compile> selectQueryByDefault(CompileSearchContext context);
    List<Compile> selectQueryByView(CompileSearchContext context);

    Page<Compile> searchDefault(CompileSearchContext context);
    Compile dynamicCall(Long key, String action, Compile et);
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


