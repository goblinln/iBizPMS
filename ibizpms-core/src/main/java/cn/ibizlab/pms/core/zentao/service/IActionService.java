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
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Action] 服务对象接口
 */
public interface IActionService extends IService<Action>{

    boolean create(Action et) ;
    void createBatch(List<Action> list) ;
    boolean update(Action et) ;
    void updateBatch(List<Action> list) ;
    boolean remove(BigInteger key) ;
    void removeBatch(Collection<BigInteger> idList) ;
    Action get(BigInteger key) ;
    Action getDraft(Action et) ;
    boolean checkKey(Action et) ;
    Action editComment(Action et) ;
    boolean save(Action et) ;
    void saveBatch(List<Action> list) ;
    Page<Action> searchDefault(ActionSearchContext context) ;
    Page<Action> searchProductTrends(ActionSearchContext context) ;
    Page<Action> searchProjectTrends(ActionSearchContext context) ;
    Page<Action> searchType(ActionSearchContext context) ;
    List<Action> selectByProject(BigInteger id) ;
    void removeByProject(BigInteger id) ;
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


