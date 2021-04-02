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

import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[PRODUCTTEAM] 服务对象接口
 */
public interface IPRODUCTTEAMService extends IService<PRODUCTTEAM> {

    boolean create(PRODUCTTEAM et);
    void createBatch(List<PRODUCTTEAM> list);
    boolean update(PRODUCTTEAM et);
    void updateBatch(List<PRODUCTTEAM> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    PRODUCTTEAM get(Long key);
    PRODUCTTEAM getDraft(PRODUCTTEAM et);
    boolean checkKey(PRODUCTTEAM et);
    PRODUCTTEAM productTeamGuoLv(PRODUCTTEAM et);
    boolean productTeamGuoLvBatch(List<PRODUCTTEAM> etList);
    boolean save(PRODUCTTEAM et);
    void saveBatch(List<PRODUCTTEAM> list);
    Page<PRODUCTTEAM> searchDefault(PRODUCTTEAMSearchContext context);
    Page<PRODUCTTEAM> searchProductTeamInfo(PRODUCTTEAMSearchContext context);
    Page<PRODUCTTEAM> searchProjectApp(PRODUCTTEAMSearchContext context);
    Page<PRODUCTTEAM> searchRowEditDefaultProductTeam(PRODUCTTEAMSearchContext context);
    List<PRODUCTTEAM> selectByRoot(Long id);
    void removeByRoot(Long id);
    void saveByRoot(Long id, List<PRODUCTTEAM> list) ;
    PRODUCTTEAM dynamicCall(Long key, String action, PRODUCTTEAM et);
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


