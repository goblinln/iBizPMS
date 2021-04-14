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

import cn.ibizlab.pms.core.zentao.domain.UserTpl;
import cn.ibizlab.pms.core.zentao.filter.UserTplSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserTpl] 服务对象接口
 */
public interface IUserTplService extends IService<UserTpl> {

    boolean create(UserTpl et);
    void createBatch(List<UserTpl> list);
    boolean update(UserTpl et);
    boolean sysUpdate(UserTpl et);
    void updateBatch(List<UserTpl> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    UserTpl get(Long key);
    UserTpl sysGet(Long key);
    UserTpl getDraft(UserTpl et);
    boolean checkKey(UserTpl et);
    boolean save(UserTpl et);
    void saveBatch(List<UserTpl> list);
    Page<UserTpl> searchDefault(UserTplSearchContext context);
    Page<UserTpl> searchMyUserTpl(UserTplSearchContext context);
    UserTpl dynamicCall(Long key, String action, UserTpl et);
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

    List<UserTpl> getUsertplByIds(List<Long> ids);
    List<UserTpl> getUsertplByEntities(List<UserTpl> entities);
}


