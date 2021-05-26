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

import cn.ibizlab.pms.core.zentao.domain.ImMessage;
import cn.ibizlab.pms.core.zentao.filter.ImMessageSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ImMessage] 服务对象接口
 */
public interface IImMessageService extends IService<ImMessage> {

    boolean create(ImMessage et);
    boolean update(ImMessage et);
    boolean sysUpdate(ImMessage et);
    boolean remove(Long key);
    ImMessage get(Long key);
    ImMessage sysGet(Long key);
    ImMessage getDraft(ImMessage et);
    boolean checkKey(ImMessage et);
    boolean save(ImMessage et);
    List<ImMessage> select(ImMessageSearchContext context);
    List<ImMessage> selectDefault(ImMessageSearchContext context);
    List<ImMessage> selectView(ImMessageSearchContext context);

    Page<ImMessage> searchDefault(ImMessageSearchContext context);
    ImMessage dynamicCall(Long key, String action, ImMessage et);
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


