package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface UserYearWorkStatsMapper extends BaseMapper<UserYearWorkStats> {

    List<UserYearWorkStats> selectDefault(@Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    List<UserYearWorkStats> selectMonthFinishTaskAndBug(@Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    List<UserYearWorkStats> selectMonthOpenedBugAndCase(@Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    List<UserYearWorkStats> selectMonthOpenedStory(@Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    List<UserYearWorkStats> selectView(@Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);

    Page<UserYearWorkStats> searchDefault(IPage page, @Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    Page<UserYearWorkStats> searchMonthFinishTaskAndBug(IPage page, @Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    Page<UserYearWorkStats> searchMonthOpenedBugAndCase(IPage page, @Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    Page<UserYearWorkStats> searchMonthOpenedStory(IPage page, @Param("srf") UserYearWorkStatsSearchContext context, @Param("ew") Wrapper<UserYearWorkStats> wrapper);
    @Override
    UserYearWorkStats selectById(Serializable id);
    @Override
    int insert(UserYearWorkStats entity);
    @Override
    int updateById(@Param(Constants.ENTITY) UserYearWorkStats entity);
    @Override
    int update(@Param(Constants.ENTITY) UserYearWorkStats entity, @Param("ew") Wrapper<UserYearWorkStats> updateWrapper);
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

}
