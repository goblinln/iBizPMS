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

import cn.ibizlab.pms.core.zentao.domain.Entry;
import cn.ibizlab.pms.core.zentao.filter.EntrySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Entry] 服务对象接口
 */
public interface IEntryService extends IService<Entry> {

    boolean create(Entry et);
    boolean update(Entry et);
    boolean sysUpdate(Entry et);
    boolean remove(Long key);
    Entry get(Long key);
    Entry sysGet(Long key);
    Entry getDraft(Entry et);
    boolean checkKey(Entry et);
    boolean save(Entry et);
    List<Entry> select(EntrySearchContext context);
    List<Entry> selectDefault(EntrySearchContext context);
    List<Entry> selectView(EntrySearchContext context);

    Page<Entry> searchDefault(EntrySearchContext context);
    Entry dynamicCall(Long key, String action, Entry et);
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


