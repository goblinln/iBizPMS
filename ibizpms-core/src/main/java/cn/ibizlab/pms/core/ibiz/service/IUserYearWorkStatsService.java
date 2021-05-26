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

import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserYearWorkStats] 服务对象接口
 */
public interface IUserYearWorkStatsService extends IService<UserYearWorkStats> {

    boolean create(UserYearWorkStats et);
    boolean update(UserYearWorkStats et);
    boolean sysUpdate(UserYearWorkStats et);
    boolean remove(Long key);
    UserYearWorkStats get(Long key);
    UserYearWorkStats sysGet(Long key);
    UserYearWorkStats getDraft(UserYearWorkStats et);
    boolean checkKey(UserYearWorkStats et);
    UserYearWorkStats getDevInfomation(UserYearWorkStats et);
    UserYearWorkStats getPoInfomation(UserYearWorkStats et);
    UserYearWorkStats getQaInfomation(UserYearWorkStats et);
    UserYearWorkStats getUserYearAction(UserYearWorkStats et);
    boolean save(UserYearWorkStats et);
    UserYearWorkStats updateTitleByYear(UserYearWorkStats et);
    List<UserYearWorkStats> select(UserYearWorkStatsSearchContext context);
    List<UserYearWorkStats> selectDefault(UserYearWorkStatsSearchContext context);
    List<UserYearWorkStats> selectMonthFinishTaskAndBug(UserYearWorkStatsSearchContext context);
    List<UserYearWorkStats> selectMonthOpenedBugAndCase(UserYearWorkStatsSearchContext context);
    List<UserYearWorkStats> selectMonthOpenedStory(UserYearWorkStatsSearchContext context);
    List<UserYearWorkStats> selectView(UserYearWorkStatsSearchContext context);

    Page<UserYearWorkStats> searchDefault(UserYearWorkStatsSearchContext context);
    Page<UserYearWorkStats> searchMonthFinishTaskAndBug(UserYearWorkStatsSearchContext context);
    Page<UserYearWorkStats> searchMonthOpenedBugAndCase(UserYearWorkStatsSearchContext context);
    Page<UserYearWorkStats> searchMonthOpenedStory(UserYearWorkStatsSearchContext context);
    UserYearWorkStats dynamicCall(Long key, String action, UserYearWorkStats et);
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


