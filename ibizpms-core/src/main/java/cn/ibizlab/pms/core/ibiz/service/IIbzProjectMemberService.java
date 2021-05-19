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

import cn.ibizlab.pms.core.ibiz.domain.IbzProjectMember;
import cn.ibizlab.pms.core.ibiz.filter.IbzProjectMemberSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProjectMember] 服务对象接口
 */
public interface IIbzProjectMemberService extends IService<IbzProjectMember> {

    boolean create(IbzProjectMember et);
    void createBatch(List<IbzProjectMember> list);
    boolean update(IbzProjectMember et);
    boolean sysUpdate(IbzProjectMember et);
    void updateBatch(List<IbzProjectMember> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProjectMember get(Long key);
    IbzProjectMember sysGet(Long key);
    IbzProjectMember getDraft(IbzProjectMember et);
    boolean checkKey(IbzProjectMember et);
    boolean save(IbzProjectMember et);
    void saveBatch(List<IbzProjectMember> list);
    List<IbzProjectMember> select(IbzProjectMemberSearchContext context);
    List<IbzProjectMember> selectDefault(IbzProjectMemberSearchContext context);
    List<IbzProjectMember> selectDeveloperQuery(IbzProjectMemberSearchContext context);
    List<IbzProjectMember> selectOpenByQuery(IbzProjectMemberSearchContext context);
    List<IbzProjectMember> selectView(IbzProjectMemberSearchContext context);

    Page<IbzProjectMember> searchDefault(IbzProjectMemberSearchContext context);
    Page<IbzProjectMember> searchDeveloperQuery(IbzProjectMemberSearchContext context);
    Page<IbzProjectMember> searchOpenByQuery(IbzProjectMemberSearchContext context);
    IbzProjectMember dynamicCall(Long key, String action, IbzProjectMember et);
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


