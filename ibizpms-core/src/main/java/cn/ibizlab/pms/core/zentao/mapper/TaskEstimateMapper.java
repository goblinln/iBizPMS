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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TaskEstimateMapper extends BaseMapper<TaskEstimate> {

    Page<TaskEstimate> searchActionMonth(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchActionYear(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchAllAccounts(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchAllProjects(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchDefault(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchDefaults(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchMyAccounts(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchProjectActionMonth(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchProjectActionYear(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    Page<TaskEstimate> searchProjectTaskEstimate(IPage page, @Param("srf") TaskEstimateSearchContext context, @Param("ew") Wrapper<TaskEstimate> wrapper);
    @Override
    TaskEstimate selectById(Serializable id);
    @Override
    int insert(TaskEstimate entity);
    @Override
    int updateById(@Param(Constants.ENTITY) TaskEstimate entity);
    @Override
    int update(@Param(Constants.ENTITY) TaskEstimate entity, @Param("ew") Wrapper<TaskEstimate> updateWrapper);
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

    List<TaskEstimate> selectByTask(@Param("id") Serializable id);

}
