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

import cn.ibizlab.pms.core.zentao.domain.UserGroup;
import cn.ibizlab.pms.core.zentao.filter.UserGroupSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserGroup] 服务对象接口
 */
public interface IUserGroupService extends IService<UserGroup> {

    boolean create(UserGroup et);
    boolean update(UserGroup et);
    boolean sysUpdate(UserGroup et);
    boolean remove(String key);
    UserGroup get(String key);
    UserGroup sysGet(String key);
    UserGroup getDraft(UserGroup et);
    boolean checkKey(UserGroup et);
    boolean save(UserGroup et);
    List<UserGroup> select(UserGroupSearchContext context);
    List<UserGroup> selectDefault(UserGroupSearchContext context);
    List<UserGroup> selectView(UserGroupSearchContext context);

    Page<UserGroup> searchDefault(UserGroupSearchContext context);
    UserGroup dynamicCall(String key, String action, UserGroup et);
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


