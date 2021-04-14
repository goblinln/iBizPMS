package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface BugStatsMapper extends BaseMapper<BugStats> {

    Page<BugStats> searchBugCountInResolution(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchBugResolvedBy(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchBugResolvedGird(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchBugassignedTo(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchDefault(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchProductBugResolutionStats(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchProductBugStatusSum(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchProductCreateBug(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    Page<BugStats> searchProjectBugStatusCount(IPage page, @Param("srf") BugStatsSearchContext context, @Param("ew") Wrapper<BugStats> wrapper);
    @Override
    BugStats selectById(Serializable id);
    @Override
    int insert(BugStats entity);
    @Override
    int updateById(@Param(Constants.ENTITY) BugStats entity);
    @Override
    int update(@Param(Constants.ENTITY) BugStats entity, @Param("ew") Wrapper<BugStats> updateWrapper);
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

    List<BugStats> selectByProduct(@Param("id") Serializable id);

    List<BugStats> selectByProject(@Param("id") Serializable id);

}
