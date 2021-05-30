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

import cn.ibizlab.pms.core.zentao.domain.UserContact;
import cn.ibizlab.pms.core.zentao.filter.UserContactSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserContact] 服务对象接口
 */
public interface IUserContactService extends IService<UserContact> {

    boolean create(UserContact et);
    void createBatch(List<UserContact> list);
    boolean update(UserContact et);
    boolean sysUpdate(UserContact et);
    void updateBatch(List<UserContact> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    UserContact get(Long key);
    UserContact sysGet(Long key);
    UserContact getDraft(UserContact et);
    boolean checkKey(UserContact et);
    boolean save(UserContact et);
    void saveBatch(List<UserContact> list);
    List<UserContact> select(UserContactSearchContext context);
    List<UserContact> selectCurUSERCONTACT(UserContactSearchContext context);
    List<UserContact> selectDefault(UserContactSearchContext context);
    List<UserContact> selectMyUSERCONTACT(UserContactSearchContext context);
    List<UserContact> selectView(UserContactSearchContext context);

    Page<UserContact> searchCurUSERCONTACT(UserContactSearchContext context);
    Page<UserContact> searchDefault(UserContactSearchContext context);
    Page<UserContact> searchMyUSERCONTACT(UserContactSearchContext context);
    UserContact dynamicCall(Long key, String action, UserContact et);
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

    List<UserContact> getUsercontactByIds(List<Long> ids);
    List<UserContact> getUsercontactByEntities(List<UserContact> entities);
}


