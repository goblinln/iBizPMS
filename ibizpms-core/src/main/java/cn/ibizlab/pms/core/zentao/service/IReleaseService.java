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

import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Release] 服务对象接口
 */
public interface IReleaseService extends IService<Release> {

    boolean create(Release et);
    boolean update(Release et);
    boolean sysUpdate(Release et);
    boolean remove(Long key);
    Release get(Long key);
    Release sysGet(Long key);
    Release getDraft(Release et);
    Release activate(Release et);
    Release batchUnlinkBug(Release et);
    Release changeStatus(Release et);
    boolean checkKey(Release et);
    Release linkBug(Release et);
    Release linkBugbyBug(Release et);
    Release linkBugbyLeftBug(Release et);
    Release linkStory(Release et);
    Release mobReleaseCounter(Release et);
    Release oneClickRelease(Release et);
    boolean save(Release et);
    Release terminate(Release et);
    Release unlinkBug(Release et);
    List<Release> select(ReleaseSearchContext context);
    List<Release> selectDefault(ReleaseSearchContext context);
    List<Release> selectGetList(ReleaseSearchContext context);
    List<Release> selectReportRelease(ReleaseSearchContext context);
    List<Release> selectView(ReleaseSearchContext context);

    Page<Release> searchDefault(ReleaseSearchContext context);
    Page<Release> searchReportRelease(ReleaseSearchContext context);
    List<Release> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<Release> selectByBuild(Long id);
    void removeByBuild(Long id);
    List<Release> selectByProduct(Long id);
    void removeByProduct(Long id);
    Release dynamicCall(Long key, String action, Release et);
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

    List<Release> getReleaseByIds(List<Long> ids);
    List<Release> getReleaseByEntities(List<Release> entities);
}


