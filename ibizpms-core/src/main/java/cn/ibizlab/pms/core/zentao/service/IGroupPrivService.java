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

import cn.ibizlab.pms.core.zentao.domain.GroupPriv;
import cn.ibizlab.pms.core.zentao.filter.GroupPrivSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[GroupPriv] 服务对象接口
 */
public interface IGroupPrivService extends IService<GroupPriv> {

    boolean create(GroupPriv et);
    void createBatch(List<GroupPriv> list);
    boolean update(GroupPriv et);
    boolean sysUpdate(GroupPriv et);
    void updateBatch(List<GroupPriv> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    GroupPriv get(String key);
    GroupPriv sysGet(String key);
    GroupPriv getDraft(GroupPriv et);
    boolean checkKey(GroupPriv et);
    boolean save(GroupPriv et);
    void saveBatch(List<GroupPriv> list);
    List<GroupPriv> select(GroupPrivSearchContext context);
    List<GroupPriv> selectDefault(GroupPrivSearchContext context);
    List<GroupPriv> selectView(GroupPrivSearchContext context);

    Page<GroupPriv> searchDefault(GroupPrivSearchContext context);
    GroupPriv dynamicCall(String key, String action, GroupPriv et);
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


