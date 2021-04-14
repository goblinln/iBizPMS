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

import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[User] 服务对象接口
 */
public interface IUserService extends IService<User> {

    boolean create(User et);
    void createBatch(List<User> list);
    boolean update(User et);
    boolean sysUpdate(User et);
    void updateBatch(List<User> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    User get(Long key);
    User sysGet(Long key);
    User getDraft(User et);
    boolean checkKey(User et);
    User getByCommiter(User et);
    boolean save(User et);
    void saveBatch(List<User> list);
    User syncAccount(User et);
    boolean syncAccountBatch(List<User> etList);
    Page<User> searchBugUser(UserSearchContext context);
    Page<User> searchDefault(UserSearchContext context);
    Page<User> searchGetByCommiter(UserSearchContext context);
    Page<User> searchProjectTeamM(UserSearchContext context);
    Page<User> searchProjectTeamUser(UserSearchContext context);
    Page<User> searchProjectTeamUserTask(UserSearchContext context);
    Page<User> searchTaskTeam(UserSearchContext context);
    User dynamicCall(Long key, String action, User et);
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


