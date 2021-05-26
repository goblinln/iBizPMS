package cn.ibizlab.pms.core.ibiz.service;

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

import cn.ibizlab.pms.core.ibiz.domain.IbiLogin;
import cn.ibizlab.pms.core.ibiz.filter.IbiLoginSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbiLogin] 服务对象接口
 */
public interface IIbiLoginService extends IService<IbiLogin> {

    boolean create(IbiLogin et);
    boolean update(IbiLogin et);
    boolean sysUpdate(IbiLogin et);
    boolean remove(Long key);
    IbiLogin get(Long key);
    IbiLogin sysGet(Long key);
    IbiLogin getUser(IbiLogin et);
    IbiLogin ztlogin(IbiLogin et);
    List<IbiLogin> select(IbiLoginSearchContext context);
    List<IbiLogin> selectDefault(IbiLoginSearchContext context);
    List<IbiLogin> selectView(IbiLoginSearchContext context);

    Page<IbiLogin> searchDefault(IbiLoginSearchContext context);
    IbiLogin dynamicCall(Long key, String action, IbiLogin et);
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


