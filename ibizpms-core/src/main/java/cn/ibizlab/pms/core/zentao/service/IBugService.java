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
    boolean update(Bug et);
    boolean sysUpdate(Bug et);
    boolean remove(Long key);
    Bug get(Long key);
    Bug sysGet(Long key);
    Bug getDraft(Bug et);
    Bug activate(Bug et);
    Bug assignTo(Bug et);
    Bug batchUnlinkBug(Bug et);
    Bug bugFavorites(Bug et);
    Bug bugNFavorites(Bug et);
    Bug buildBatchUnlinkBug(Bug et);
    Bug buildLinkBug(Bug et);
    Bug buildUnlinkBug(Bug et);
    boolean checkKey(Bug et);
    Bug close(Bug et);
    Bug confirm(Bug et);
    Bug linkBug(Bug et);
    Bug releaaseBatchUnlinkBug(Bug et);
    Bug releaseLinkBugbyBug(Bug et);
    Bug releaseLinkBugbyLeftBug(Bug et);
    Bug releaseUnLinkBugbyLeftBug(Bug et);
    Bug releaseUnlinkBug(Bug et);
    Bug resolve(Bug et);
    boolean save(Bug et);
    Bug sendMessage(Bug et);
    Bug sendMsgPreProcess(Bug et);
    Bug testScript(Bug et);
    Bug toStory(Bug et);
    Bug unlinkBug(Bug et);
    Bug updateStoryVersion(Bug et);
    List<Bug> select(BugSearchContext context);
    List<Bug> selectAssignedToMyBug(BugSearchContext context);
    List<Bug> selectAssignedToMyBugPc(BugSearchContext context);
    List<Bug> selectBuildBugs(BugSearchContext context);
    List<Bug> selectBuildLinkResolvedBugs(BugSearchContext context);
    List<Bug> selectBuildOpenBugs(BugSearchContext context);
    List<Bug> selectBuildProduceBug(BugSearchContext context);
    List<Bug> selectBuildProduceBugModule(BugSearchContext context);
    List<Bug> selectBuildProduceBugModule_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugOpenedBy(BugSearchContext context);
    List<Bug> selectBuildProduceBugOpenedBy_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugRES(BugSearchContext context);
    List<Bug> selectBuildProduceBugRESOLVEDBY(BugSearchContext context);
    List<Bug> selectBuildProduceBugRESOLVEDBY_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugResolution_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugSeverity_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugStatus_Project(BugSearchContext context);
    List<Bug> selectBuildProduceBugType_Project(BugSearchContext context);
    List<Bug> selectCurUserResolve(BugSearchContext context);
    List<Bug> selectDefault(BugSearchContext context);
    List<Bug> selectESBulk(BugSearchContext context);
    List<Bug> selectMyAgentBug(BugSearchContext context);
    List<Bug> selectMyCreateOrPartake(BugSearchContext context);
    List<Bug> selectMyCurOpenedBug(BugSearchContext context);
    List<Bug> selectMyFavorites(BugSearchContext context);
    List<Bug> selectNotCurPlanLinkBug(BugSearchContext context);
    List<Bug> selectReleaseBugs(BugSearchContext context);
    List<Bug> selectReleaseLeftBugs(BugSearchContext context);
    List<Bug> selectReleaseLinkableLeftBug(BugSearchContext context);
    List<Bug> selectReleaseLinkableResolvedBug(BugSearchContext context);
    List<Bug> selectReportBugs(BugSearchContext context);
    List<Bug> selectSelectBugByBuild(BugSearchContext context);
    List<Bug> selectSelectBugsByProject(BugSearchContext context);
    List<Bug> selectSimple(BugSearchContext context);
    List<Bug> selectStoryFormBug(BugSearchContext context);
    List<Bug> selectTaskBug(BugSearchContext context);
    List<Bug> selectView(BugSearchContext context);

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
    Page<Bug> searchMyCreateOrPartake(BugSearchContext context);
    Page<Bug> searchMyCurOpenedBug(BugSearchContext context);
    Page<Bug> searchMyFavorites(BugSearchContext context);
    Page<Bug> searchNotCurPlanLinkBug(BugSearchContext context);
    Page<Bug> searchProjectBugs(BugSearchContext context);
    Page<Bug> searchReleaseBugs(BugSearchContext context);
    Page<Bug> searchReleaseLeftBugs(BugSearchContext context);
    Page<Bug> searchReleaseLinkableLeftBug(BugSearchContext context);
    Page<Bug> searchReleaseLinkableResolvedBug(BugSearchContext context);
    Page<Bug> searchReportBugs(BugSearchContext context);
    Page<Bug> searchStoryFormBug(BugSearchContext context);
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


