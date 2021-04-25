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

import cn.ibizlab.pms.core.zentao.domain.ImChatuser;
import cn.ibizlab.pms.core.zentao.filter.ImChatuserSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ImChatuser] 服务对象接口
 */
public interface IImChatuserService extends IService<ImChatuser> {

    boolean create(ImChatuser et);
    void createBatch(List<ImChatuser> list);
    boolean update(ImChatuser et);
    boolean sysUpdate(ImChatuser et);
    void updateBatch(List<ImChatuser> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ImChatuser get(Long key);
    ImChatuser sysGet(Long key);
    ImChatuser getDraft(ImChatuser et);
    boolean checkKey(ImChatuser et);
    boolean save(ImChatuser et);
    void saveBatch(List<ImChatuser> list);
    List<ImChatuser> select(ImChatuserSearchContext context);
    Page<ImChatuser> searchDefault(ImChatuserSearchContext context);
    ImChatuser dynamicCall(Long key, String action, ImChatuser et);
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


