package cn.ibizlab.pms.core.zentao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface StoryMapper extends BaseMapper<Story> {

    List<Story> selectAssignedToMyStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectAssignedToMyStoryCalendar(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectBugStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectBuildLinkCompletedStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectBuildLinkableStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectBuildStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectByModule(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectCaseStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectChildMore(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectDefault(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectESBulk(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectGetProductStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectMyAgentStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectMyCreateOrPartake(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectMyCurOpenedStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectMyFavorites(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectNotCurPlanLinkStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectParentDefault(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectParentDefaultQ(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectProjectLinkStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectProjectStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectReleaseLinkableStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectReleaseStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectReportStories(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectStoryChild(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectStoryRelated(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectSubStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectTaskRelatedStory(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    List<Story> selectView(@Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);

    Page<Story> searchAssignedToMyStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchAssignedToMyStoryCalendar(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchBugStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchBuildLinkCompletedStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchBuildLinkableStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchBuildStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchByModule(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchCaseStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchChildMore(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchDefault(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchESBulk(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchGetProductStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchMyAgentStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchMyCreateOrPartake(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchMyCurOpenedStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchMyFavorites(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchNotCurPlanLinkStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchParentDefault(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchParentDefaultQ(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchProjectLinkStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchProjectStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchReleaseLinkableStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchReleaseStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchReportStories(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchStoryChild(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchStoryRelated(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchSubStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchTaskRelatedStory(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    Page<Story> searchView(IPage page, @Param("srf") StorySearchContext context, @Param("ew") Wrapper<Story> wrapper);
    @Override
    Story selectById(Serializable id);
    @Override
    int insert(Story entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Story entity);
    @Override
    int update(@Param(Constants.ENTITY) Story entity, @Param("ew") Wrapper<Story> updateWrapper);
    @Override
    int deleteById(Serializable id);
    /**
    * 自定义查询SQL
    * @param sql
    * @return
    */
    @Select("${sql}")
    List<JSONObject> selectBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义更新SQL
    * @param sql
    * @return
    */
    @Update("${sql}")
    boolean updateBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义插入SQL
    * @param sql
    * @return
    */
    @Insert("${sql}")
    boolean insertBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义删除SQL
    * @param sql
    * @return
    */
    @Delete("${sql}")
    boolean deleteBySQL(@Param("sql") String sql, @Param("et")Map param);

    List<Story> selectByModule(@Param("id") Serializable id);

    List<Story> selectByBranch(@Param("id") Serializable id);

    List<Story> selectByFrombug(@Param("id") Serializable id);

    List<Story> selectByTobug(@Param("id") Serializable id);

    List<Story> selectByProduct(@Param("id") Serializable id);

    List<Story> selectByDuplicatestory(@Param("id") Serializable id);

    List<Story> selectByParent(@Param("id") Serializable id);

}
