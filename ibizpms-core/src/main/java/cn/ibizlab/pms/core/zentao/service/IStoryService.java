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

import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Story] 服务对象接口
 */
public interface IStoryService extends IService<Story> {

    boolean create(Story et);
    void createBatch(List<Story> list);
    boolean update(Story et);
    boolean sysUpdate(Story et);
    void updateBatch(List<Story> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Story get(Long key);
    Story sysGet(Long key);
    Story getDraft(Story et);
    Story activate(Story et);
    boolean activateBatch(List<Story> etList);
    Story allPush(Story et);
    boolean allPushBatch(List<Story> etList);
    Story assignTo(Story et);
    boolean assignToBatch(List<Story> etList);
    Story batchAssignTo(Story et);
    boolean batchAssignToBatch(List<Story> etList);
    Story batchChangeBranch(Story et);
    boolean batchChangeBranchBatch(List<Story> etList);
    Story batchChangeModule(Story et);
    boolean batchChangeModuleBatch(List<Story> etList);
    Story batchChangePlan(Story et);
    boolean batchChangePlanBatch(List<Story> etList);
    Story batchChangeStage(Story et);
    boolean batchChangeStageBatch(List<Story> etList);
    Story batchClose(Story et);
    boolean batchCloseBatch(List<Story> etList);
    Story batchReview(Story et);
    boolean batchReviewBatch(List<Story> etList);
    Story batchUnlinkStory(Story et);
    boolean batchUnlinkStoryBatch(List<Story> etList);
    Story bugToStory(Story et);
    boolean bugToStoryBatch(List<Story> etList);
    Story buildBatchUnlinkStory(Story et);
    boolean buildBatchUnlinkStoryBatch(List<Story> etList);
    Story buildLinkStory(Story et);
    boolean buildLinkStoryBatch(List<Story> etList);
    Story buildUnlinkStory(Story et);
    boolean buildUnlinkStoryBatch(List<Story> etList);
    Story buildUnlinkStorys(Story et);
    boolean buildUnlinkStorysBatch(List<Story> etList);
    Story change(Story et);
    boolean changeBatch(List<Story> etList);
    boolean checkKey(Story et);
    Story close(Story et);
    boolean closeBatch(List<Story> etList);
    Story createTasks(Story et);
    boolean createTasksBatch(List<Story> etList);
    Story getStorySpec(Story et);
    boolean getStorySpecBatch(List<Story> etList);
    Story getStorySpecs(Story et);
    boolean getStorySpecsBatch(List<Story> etList);
    Story importPlanStories(Story et);
    boolean importPlanStoriesBatch(List<Story> etList);
    Story linkStory(Story et);
    boolean linkStoryBatch(List<Story> etList);
    Story projectBatchUnlinkStory(Story et);
    boolean projectBatchUnlinkStoryBatch(List<Story> etList);
    Story projectLinkStory(Story et);
    boolean projectLinkStoryBatch(List<Story> etList);
    Story projectUnlinkStory(Story et);
    boolean projectUnlinkStoryBatch(List<Story> etList);
    Story projectUnlinkStorys(Story et);
    boolean projectUnlinkStorysBatch(List<Story> etList);
    Story push(Story et);
    boolean pushBatch(List<Story> etList);
    Story releaseBatchUnlinkStory(Story et);
    boolean releaseBatchUnlinkStoryBatch(List<Story> etList);
    Story releaseLinkStory(Story et);
    boolean releaseLinkStoryBatch(List<Story> etList);
    Story releaseUnlinkStory(Story et);
    boolean releaseUnlinkStoryBatch(List<Story> etList);
    Story resetReviewedBy(Story et);
    boolean resetReviewedByBatch(List<Story> etList);
    Story review(Story et);
    boolean reviewBatch(List<Story> etList);
    boolean save(Story et);
    void saveBatch(List<Story> list);
    Story sendMessage(Story et);
    boolean sendMessageBatch(List<Story> etList);
    Story sendMsgPreProcess(Story et);
    boolean sendMsgPreProcessBatch(List<Story> etList);
    Story setStage(Story et);
    boolean setStageBatch(List<Story> etList);
    Story storyFavorites(Story et);
    boolean storyFavoritesBatch(List<Story> etList);
    Story storyNFavorites(Story et);
    boolean storyNFavoritesBatch(List<Story> etList);
    Story syncFromIbiz(Story et);
    boolean syncFromIbizBatch(List<Story> etList);
    Story unlinkStory(Story et);
    boolean unlinkStoryBatch(List<Story> etList);
    List<Story> select(StorySearchContext context);
    List<Story> selectAssignedToMyStory(StorySearchContext context);
    List<Story> selectAssignedToMyStoryCalendar(StorySearchContext context);
    List<Story> selectBugStory(StorySearchContext context);
    List<Story> selectBuildLinkCompletedStories(StorySearchContext context);
    List<Story> selectBuildLinkableStories(StorySearchContext context);
    List<Story> selectBuildStories(StorySearchContext context);
    List<Story> selectByModule(StorySearchContext context);
    List<Story> selectCaseStory(StorySearchContext context);
    List<Story> selectDefault(StorySearchContext context);
    List<Story> selectESBulk(StorySearchContext context);
    List<Story> selectGetProductStories(StorySearchContext context);
    List<Story> selectMyAgentStory(StorySearchContext context);
    List<Story> selectMyCurOpenedStory(StorySearchContext context);
    List<Story> selectMyFavorites(StorySearchContext context);
    List<Story> selectNotCurPlanLinkStory(StorySearchContext context);
    List<Story> selectParentDefault(StorySearchContext context);
    List<Story> selectParentDefaultQ(StorySearchContext context);
    List<Story> selectProjectLinkStory(StorySearchContext context);
    List<Story> selectProjectStories(StorySearchContext context);
    List<Story> selectReleaseLinkableStories(StorySearchContext context);
    List<Story> selectReleaseStories(StorySearchContext context);
    List<Story> selectReportStories(StorySearchContext context);
    List<Story> selectStoryChild(StorySearchContext context);
    List<Story> selectStoryRelated(StorySearchContext context);
    List<Story> selectSubStory(StorySearchContext context);
    List<Story> selectTaskRelatedStory(StorySearchContext context);
    List<Story> selectView(StorySearchContext context);

    Page<Story> searchAssignedToMyStory(StorySearchContext context);
    Page<Story> searchAssignedToMyStoryCalendar(StorySearchContext context);
    Page<Story> searchBugStory(StorySearchContext context);
    Page<Story> searchBuildLinkCompletedStories(StorySearchContext context);
    Page<Story> searchBuildLinkableStories(StorySearchContext context);
    Page<Story> searchBuildStories(StorySearchContext context);
    Page<Story> searchByModule(StorySearchContext context);
    Page<Story> searchCaseStory(StorySearchContext context);
    Page<Story> searchDefault(StorySearchContext context);
    Page<Story> searchESBulk(StorySearchContext context);
    Page<Story> searchGetProductStories(StorySearchContext context);
    Page<Story> searchMyAgentStory(StorySearchContext context);
    Page<Story> searchMyCurOpenedStory(StorySearchContext context);
    Page<Story> searchMyFavorites(StorySearchContext context);
    Page<Story> searchNotCurPlanLinkStory(StorySearchContext context);
    Page<Story> searchParentDefault(StorySearchContext context);
    Page<Story> searchParentDefaultQ(StorySearchContext context);
    Page<Story> searchProjectLinkStory(StorySearchContext context);
    Page<Story> searchProjectStories(StorySearchContext context);
    Page<Story> searchReleaseLinkableStories(StorySearchContext context);
    Page<Story> searchReleaseStories(StorySearchContext context);
    Page<Story> searchReportStories(StorySearchContext context);
    Page<Story> searchStoryChild(StorySearchContext context);
    Page<Story> searchStoryRelated(StorySearchContext context);
    Page<Story> searchSubStory(StorySearchContext context);
    Page<Story> searchTaskRelatedStory(StorySearchContext context);
    Page<Story> searchView(StorySearchContext context);
    List<Story> selectByModule(Long id);
    void removeByModule(Long id);
    List<Story> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<Story> selectByFrombug(Long id);
    void removeByFrombug(Long id);
    List<Story> selectByTobug(Long id);
    void removeByTobug(Long id);
    List<Story> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<Story> selectByDuplicatestory(Long id);
    void removeByDuplicatestory(Long id);
    List<Story> selectByParent(Long id);
    void removeByParent(Long id);
    Story dynamicCall(Long key, String action, Story et);
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

    List<Story> getStoryByIds(List<Long> ids);
    List<Story> getStoryByEntities(List<Story> entities);
}


