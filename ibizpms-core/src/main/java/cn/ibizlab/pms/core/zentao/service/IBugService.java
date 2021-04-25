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

import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Bug] 服务对象接口
 */
public interface IBugService extends IService<Bug> {

    boolean create(Bug et);
    void createBatch(List<Bug> list);
    boolean update(Bug et);
    boolean sysUpdate(Bug et);
    void updateBatch(List<Bug> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Bug get(Long key);
    Bug sysGet(Long key);
    Bug getDraft(Bug et);
    Bug activate(Bug et);
    boolean activateBatch(List<Bug> etList);
    Bug assignTo(Bug et);
    boolean assignToBatch(List<Bug> etList);
    Bug batchUnlinkBug(Bug et);
    boolean batchUnlinkBugBatch(List<Bug> etList);
    Bug bugFavorites(Bug et);
    boolean bugFavoritesBatch(List<Bug> etList);
    Bug bugNFavorites(Bug et);
    boolean bugNFavoritesBatch(List<Bug> etList);
    Bug buildBatchUnlinkBug(Bug et);
    boolean buildBatchUnlinkBugBatch(List<Bug> etList);
    Bug buildLinkBug(Bug et);
    boolean buildLinkBugBatch(List<Bug> etList);
    Bug buildUnlinkBug(Bug et);
    boolean buildUnlinkBugBatch(List<Bug> etList);
    boolean checkKey(Bug et);
    Bug close(Bug et);
    boolean closeBatch(List<Bug> etList);
    Bug confirm(Bug et);
    boolean confirmBatch(List<Bug> etList);
    Bug linkBug(Bug et);
    boolean linkBugBatch(List<Bug> etList);
    Bug releaaseBatchUnlinkBug(Bug et);
    boolean releaaseBatchUnlinkBugBatch(List<Bug> etList);
    Bug releaseLinkBugbyBug(Bug et);
    boolean releaseLinkBugbyBugBatch(List<Bug> etList);
    Bug releaseLinkBugbyLeftBug(Bug et);
    boolean releaseLinkBugbyLeftBugBatch(List<Bug> etList);
    Bug releaseUnLinkBugbyLeftBug(Bug et);
    boolean releaseUnLinkBugbyLeftBugBatch(List<Bug> etList);
    Bug releaseUnlinkBug(Bug et);
    boolean releaseUnlinkBugBatch(List<Bug> etList);
    Bug resolve(Bug et);
    boolean resolveBatch(List<Bug> etList);
    boolean save(Bug et);
    void saveBatch(List<Bug> list);
    Bug sendMessage(Bug et);
    boolean sendMessageBatch(List<Bug> etList);
    Bug sendMsgPreProcess(Bug et);
    boolean sendMsgPreProcessBatch(List<Bug> etList);
    Bug testScript(Bug et);
    Bug toStory(Bug et);
    boolean toStoryBatch(List<Bug> etList);
    Bug unlinkBug(Bug et);
    boolean unlinkBugBatch(List<Bug> etList);
    Bug updateStoryVersion(Bug et);
    boolean updateStoryVersionBatch(List<Bug> etList);
    List<Bug> select(BugSearchContext context);
    List<Bug> selectQueryByAssignedToMyBug(BugSearchContext context);
    List<Bug> selectQueryByAssignedToMyBugPc(BugSearchContext context);
    List<Bug> selectQueryByBuildBugs(BugSearchContext context);
    List<Bug> selectQueryByBuildLinkResolvedBugs(BugSearchContext context);
    List<Bug> selectQueryByBuildOpenBugs(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBug(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugModule(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugModule_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugOpenedBy(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugOpenedBy_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugRES(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugRESOLVEDBY(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugRESOLVEDBY_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugResolution_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugSeverity_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugStatus_Project(BugSearchContext context);
    List<Bug> selectQueryByBuildProduceBugType_Project(BugSearchContext context);
    List<Bug> selectQueryByCurUserResolve(BugSearchContext context);
    List<Bug> selectQueryByDefault(BugSearchContext context);
    List<Bug> selectQueryByESBulk(BugSearchContext context);
    List<Bug> selectQueryByMyAgentBug(BugSearchContext context);
    List<Bug> selectQueryByMyCurOpenedBug(BugSearchContext context);
    List<Bug> selectQueryByMyFavorites(BugSearchContext context);
    List<Bug> selectQueryByNotCurPlanLinkBug(BugSearchContext context);
    List<Bug> selectQueryByReleaseBugs(BugSearchContext context);
    List<Bug> selectQueryByReleaseLeftBugs(BugSearchContext context);
    List<Bug> selectQueryByReleaseLinkableLeftBug(BugSearchContext context);
    List<Bug> selectQueryByReleaseLinkableResolvedBug(BugSearchContext context);
    List<Bug> selectQueryByReportBugs(BugSearchContext context);
    List<Bug> selectQueryBySelectBugByBuild(BugSearchContext context);
    List<Bug> selectQueryBySelectBugsByProject(BugSearchContext context);
    List<Bug> selectQueryByTaskBug(BugSearchContext context);
    List<Bug> selectQueryByView(BugSearchContext context);

    Page<Bug> searchAssignedToMyBug(BugSearchContext context);
    Page<Bug> searchAssignedToMyBugPc(BugSearchContext context);
    Page<Bug> searchBugsByBuild(BugSearchContext context);
    Page<Bug> searchBuildBugs(BugSearchContext context);
    Page<Bug> searchBuildLinkResolvedBugs(BugSearchContext context);
    Page<Bug> searchBuildOpenBugs(BugSearchContext context);
    Page<Bug> searchBuildProduceBug(BugSearchContext context);
    Page<Bug> searchBuildProduceBugModule(BugSearchContext context);
    Page<Bug> searchBuildProduceBugModule_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugOpenedBy(BugSearchContext context);
    Page<Bug> searchBuildProduceBugOpenedBy_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugRES(BugSearchContext context);
    Page<Bug> searchBuildProduceBugRESOLVEDBY(BugSearchContext context);
    Page<Bug> searchBuildProduceBugRESOLVEDBY_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugResolution_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugSeverity_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugStatus_Project(BugSearchContext context);
    Page<Bug> searchBuildProduceBugType_Project(BugSearchContext context);
    Page<Bug> searchCurUserResolve(BugSearchContext context);
    Page<Bug> searchDefault(BugSearchContext context);
    Page<Bug> searchESBulk(BugSearchContext context);
    Page<Bug> searchMyAgentBug(BugSearchContext context);
    Page<Bug> searchMyCurOpenedBug(BugSearchContext context);
    Page<Bug> searchMyFavorites(BugSearchContext context);
    Page<Bug> searchNotCurPlanLinkBug(BugSearchContext context);
    Page<Bug> searchProjectBugs(BugSearchContext context);
    Page<Bug> searchReleaseBugs(BugSearchContext context);
    Page<Bug> searchReleaseLeftBugs(BugSearchContext context);
    Page<Bug> searchReleaseLinkableLeftBug(BugSearchContext context);
    Page<Bug> searchReleaseLinkableResolvedBug(BugSearchContext context);
    Page<Bug> searchReportBugs(BugSearchContext context);
    Page<Bug> searchTaskRelatedBug(BugSearchContext context);
    List<Bug> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<Bug> selectByDuplicatebug(Long id);
    void removeByDuplicatebug(Long id);
    List<Bug> selectByIbizcase(Long id);
    void removeByIbizcase(Long id);
    List<Bug> selectByEntry(Long id);
    void removeByEntry(Long id);
    List<Bug> selectByModule(Long id);
    void removeByModule(Long id);
    List<Bug> selectByPlan(Long id);
    void removeByPlan(Long id);
    List<Bug> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<Bug> selectByProject(Long id);
    void removeByProject(Long id);
    List<Bug> selectByRepo(Long id);
    void removeByRepo(Long id);
    List<Bug> selectByStory(Long id);
    void removeByStory(Long id);
    List<Bug> selectByTostory(Long id);
    void removeByTostory(Long id);
    List<Bug> selectByTask(Long id);
    void removeByTask(Long id);
    List<Bug> selectByTotask(Long id);
    void removeByTotask(Long id);
    List<Bug> selectByTesttask(Long id);
    void removeByTesttask(Long id);
    Bug dynamicCall(Long key, String action, Bug et);
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

    List<Bug> getBugByIds(List<Long> ids);
    List<Bug> getBugByEntities(List<Bug> entities);
}


