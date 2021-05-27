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
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface CaseMapper extends BaseMapper<Case> {

    List<Case> selectBatchNew(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectCurOpenedCase(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectCurSuite(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectCurTestTask(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectDefault(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectESBulk(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectModuleRePortCase(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectModuleRePortCaseEntry(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectModuleRePortCase_Project(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectMyCreateOrUpdate(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectMyFavorite(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectNotCurTestSuite(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectNotCurTestTask(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectNotCurTestTaskProject(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRePortCase(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRePortCaseEntry(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRePortCase_Project(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunERRePortCase(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunERRePortCaseEntry(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunERRePortCase_Project(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunRePortCase(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunRePortCaseEntry(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectRunRePortCase_Project(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    List<Case> selectView(@Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);

    Page<Case> searchBatchNew(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchCurOpenedCase(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchCurSuite(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchCurTestTask(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchDefault(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchESBulk(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchModuleRePortCase(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchModuleRePortCaseEntry(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchModuleRePortCase_Project(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchMyCreateOrUpdate(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchMyFavorites(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchNotCurTestSuite(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchNotCurTestTask(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchNotCurTestTaskProject(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRePortCase(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRePortCaseEntry(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRePortCase_Project(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunERRePortCase(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunERRePortCaseEntry(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunERRePortCase_Project(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunRePortCase(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunRePortCaseEntry(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    Page<Case> searchRunRePortCase_Project(IPage page, @Param("srf") CaseSearchContext context, @Param("ew") Wrapper<Case> wrapper);
    @Override
    Case selectById(Serializable id);
    @Override
    int insert(Case entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Case entity);
    @Override
    int update(@Param(Constants.ENTITY) Case entity, @Param("ew") Wrapper<Case> updateWrapper);
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

    List<Case> selectByBranch(@Param("id") Serializable id);

    List<Case> selectByFrombug(@Param("id") Serializable id);

    List<Case> selectByFromcaseid(@Param("id") Serializable id);

    List<Case> selectByModule(@Param("id") Serializable id);

    List<Case> selectByProduct(@Param("id") Serializable id);

    List<Case> selectByStory(@Param("id") Serializable id);

    List<Case> selectByLib(@Param("id") Serializable id);

}
