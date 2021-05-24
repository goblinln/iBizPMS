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

import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Build] 服务对象接口
 */
public interface IBuildService extends IService<Build> {

    boolean create(Build et);
    void createBatch(List<Build> list);
    boolean update(Build et);
    boolean sysUpdate(Build et);
    void updateBatch(List<Build> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Build get(Long key);
    Build sysGet(Long key);
    Build getDraft(Build et);
    boolean checkKey(Build et);
    Build linkBug(Build et);
    boolean linkBugBatch(List<Build> etList);
    Build linkStory(Build et);
    boolean linkStoryBatch(List<Build> etList);
    Build mobProjectBuildCounter(Build et);
    boolean mobProjectBuildCounterBatch(List<Build> etList);
    Build oneClickRelease(Build et);
    boolean oneClickReleaseBatch(List<Build> etList);
    boolean save(Build et);
    void saveBatch(List<Build> list);
    Build unlinkBug(Build et);
    boolean unlinkBugBatch(List<Build> etList);
    Build unlinkStory(Build et);
    boolean unlinkStoryBatch(List<Build> etList);
    List<Build> select(BuildSearchContext context);
    List<Build> selectBugProductBuild(BuildSearchContext context);
    List<Build> selectBugProductOrProjectBuild(BuildSearchContext context);
    List<Build> selectCurProduct(BuildSearchContext context);
    List<Build> selectDefault(BuildSearchContext context);
    List<Build> selectSimple(BuildSearchContext context);
    List<Build> selectTestBuild(BuildSearchContext context);
    List<Build> selectTestRounds(BuildSearchContext context);
    List<Build> selectUpdateLog(BuildSearchContext context);
    List<Build> selectView(BuildSearchContext context);

    Page<Build> searchBugProductBuild(BuildSearchContext context);
    Page<Build> searchBugProductOrProjectBuild(BuildSearchContext context);
    Page<Build> searchCurProduct(BuildSearchContext context);
    Page<Build> searchDefault(BuildSearchContext context);
    Page<Build> searchTestBuild(BuildSearchContext context);
    Page<Build> searchTestRounds(BuildSearchContext context);
    Page<Build> searchUpdateLog(BuildSearchContext context);
    List<Build> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<Build> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<Build> selectByProject(Long id);
    void removeByProject(Long id);
    Build dynamicCall(Long key, String action, Build et);
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

    List<Build> getBuildByIds(List<Long> ids);
    List<Build> getBuildByEntities(List<Build> entities);
}


