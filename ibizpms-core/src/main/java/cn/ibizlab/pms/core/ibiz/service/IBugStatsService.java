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

import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[BugStats] 服务对象接口
 */
public interface IBugStatsService extends IService<BugStats> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "Bug统计";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "bugstats";

    boolean create(BugStats et);
    void createBatch(List<BugStats> list);
    boolean update(BugStats et);
    void updateBatch(List<BugStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    BugStats get(Long key);
    BugStats getDraft(BugStats et);
    boolean checkKey(BugStats et);
    boolean save(BugStats et);
    void saveBatch(List<BugStats> list);
    Page<BugStats> searchBugCountInResolution(BugStatsSearchContext context);
    Page<BugStats> searchBugResolvedBy(BugStatsSearchContext context);
    Page<BugStats> searchBugResolvedGird(BugStatsSearchContext context);
    Page<BugStats> searchBugassignedTo(BugStatsSearchContext context);
    Page<BugStats> searchDefault(BugStatsSearchContext context);
    Page<BugStats> searchProductBugResolutionStats(BugStatsSearchContext context);
    Page<BugStats> searchProductBugStatusSum(BugStatsSearchContext context);
    Page<BugStats> searchProductCreateBug(BugStatsSearchContext context);
    Page<BugStats> searchProjectBugStatusCount(BugStatsSearchContext context);
    List<BugStats> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<BugStats> selectByProject(Long id);
    void removeByProject(Long id);
    BugStats dynamicCall(Long key, String action, BugStats et);
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


