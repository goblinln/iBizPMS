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
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserYearWorkStats] 服务对象接口
 */
public interface IUserYearWorkStatsService extends IService<UserYearWorkStats>{

    boolean create(UserYearWorkStats et) ;
    void createBatch(List<UserYearWorkStats> list) ;
    boolean update(UserYearWorkStats et) ;
    void updateBatch(List<UserYearWorkStats> list) ;
    boolean remove(Long key) ;
    void removeBatch(Collection<Long> idList) ;
    UserYearWorkStats get(Long key) ;
    UserYearWorkStats getDraft(UserYearWorkStats et) ;
    boolean checkKey(UserYearWorkStats et) ;
    UserYearWorkStats getDevInfomation(UserYearWorkStats et) ;
    UserYearWorkStats getPoInfomation(UserYearWorkStats et) ;
    UserYearWorkStats getQaInfomation(UserYearWorkStats et) ;
    UserYearWorkStats getUserYearAction(UserYearWorkStats et) ;
    boolean save(UserYearWorkStats et) ;
    void saveBatch(List<UserYearWorkStats> list) ;
    UserYearWorkStats updateTitleByYear(UserYearWorkStats et) ;
    Page<UserYearWorkStats> searchDefault(UserYearWorkStatsSearchContext context) ;
    Page<UserYearWorkStats> searchMonthFinishTaskAndBug(UserYearWorkStatsSearchContext context) ;
    Page<UserYearWorkStats> searchMonthOpenedBugAndCase(UserYearWorkStatsSearchContext context) ;
    Page<UserYearWorkStats> searchMonthOpenedStory(UserYearWorkStatsSearchContext context) ;
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


