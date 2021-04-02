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

import cn.ibizlab.pms.core.zentao.domain.ImConferenceaction;
import cn.ibizlab.pms.core.zentao.filter.ImConferenceactionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ImConferenceaction] 服务对象接口
 */
public interface IImConferenceactionService extends IService<ImConferenceaction> {

    boolean create(ImConferenceaction et);
    void createBatch(List<ImConferenceaction> list);
    boolean update(ImConferenceaction et);
    void updateBatch(List<ImConferenceaction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ImConferenceaction get(Long key);
    ImConferenceaction getDraft(ImConferenceaction et);
    boolean checkKey(ImConferenceaction et);
    boolean save(ImConferenceaction et);
    void saveBatch(List<ImConferenceaction> list);
    Page<ImConferenceaction> searchDefault(ImConferenceactionSearchContext context);
    ImConferenceaction dynamicCall(Long key, String action, ImConferenceaction et);
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


