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
public interface IStoryService extends IService<Story>{

    boolean create(Story et) ;
    void createBatch(List<Story> list) ;
    boolean update(Story et) ;
    void updateBatch(List<Story> list) ;
    boolean remove(Long key) ;
    void removeBatch(Collection<Long> idList) ;
    Story get(Long key) ;
    Story getDraft(Story et) ;
    Story activate(Story et) ;
    Story allPush(Story et) ;
    Story assignTo(Story et) ;
    Story batchAssignTo(Story et) ;
    Story batchChangeBranch(Story et) ;
    Story batchChangeModule(Story et) ;
    Story batchChangePlan(Story et) ;
    Story batchChangeStage(Story et) ;
    Story batchClose(Story et) ;
    Story batchReview(Story et) ;
    Story batchUnlinkStory(Story et) ;
    Story bugToStory(Story et) ;
    Story buildBatchUnlinkStory(Story et) ;
    Story buildLinkStory(Story et) ;
    Story buildUnlinkStory(Story et) ;
    Story buildUnlinkStorys(Story et) ;
    Story change(Story et) ;
    boolean checkKey(Story et) ;
    Story close(Story et) ;
    Story getStorySpec(Story et) ;
    Story getStorySpecs(Story et) ;
    Story importPlanStories(Story et) ;
    Story linkStory(Story et) ;
    Story projectBatchUnlinkStory(Story et) ;
    Story projectLinkStory(Story et) ;
    Story projectUnlinkStory(Story et) ;
    Story projectUnlinkStorys(Story et) ;
    Story push(Story et) ;
    Story releaseBatchUnlinkStory(Story et) ;
    Story releaseLinkStory(Story et) ;
    Story releaseUnlinkStory(Story et) ;
    Story resetReviewedBy(Story et) ;
    Story review(Story et) ;
    boolean save(Story et) ;
    void saveBatch(List<Story> list) ;
    Story sendMessage(Story et) ;
    Story sendMsgPreProcess(Story et) ;
    Story storyFavorites(Story et) ;
    Story storyNFavorites(Story et) ;
    Story syncFromIBIZ(Story et) ;
    Story unlinkStory(Story et) ;
    Page<Story> searchAssignedToMyStory(StorySearchContext context) ;
    Page<Story> searchBugStory(StorySearchContext context) ;
    Page<Story> searchBuildLinkCompletedStories(StorySearchContext context) ;
    Page<Story> searchBuildLinkableStories(StorySearchContext context) ;
    Page<Story> searchBuildStories(StorySearchContext context) ;
    Page<Story> searchByModule(StorySearchContext context) ;
    Page<Story> searchCaseStory(StorySearchContext context) ;
    Page<Story> searchDefault(StorySearchContext context) ;
    Page<Story> searchGetProductStories(StorySearchContext context) ;
    Page<Story> searchMyCurOpenedStory(StorySearchContext context) ;
    Page<Story> searchMyFavorites(StorySearchContext context) ;
    Page<Story> searchParentDefault(StorySearchContext context) ;
    Page<Story> searchParentDefaultQ(StorySearchContext context) ;
    Page<Story> searchProductPlanStories(StorySearchContext context) ;
    Page<Story> searchProjectLinkStory(StorySearchContext context) ;
    Page<Story> searchProjectStories(StorySearchContext context) ;
    Page<Story> searchReleaseLinkableStories(StorySearchContext context) ;
    Page<Story> searchReleaseStories(StorySearchContext context) ;
    Page<Story> searchReportStories(StorySearchContext context) ;
    Page<Story> searchStoryChild(StorySearchContext context) ;
    Page<Story> searchStoryRelated(StorySearchContext context) ;
    Page<Story> searchSubStory(StorySearchContext context) ;
    Page<Story> searchTaskRelatedStory(StorySearchContext context) ;
    List<Story> selectByModule(Long id);
    void removeByModule(Long id) ;
    List<Story> selectByBranch(Long id);
    void removeByBranch(Long id) ;
    List<Story> selectByFrombug(Long id);
    void removeByFrombug(Long id) ;
    List<Story> selectByTobug(Long id);
    void removeByTobug(Long id) ;
    List<Story> selectByProduct(Long id);
    void removeByProduct(Long id) ;
    List<Story> selectByDuplicatestory(Long id);
    void removeByDuplicatestory(Long id) ;
    List<Story> selectByParent(Long id);
    void removeByParent(Long id) ;
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


