package cn.ibizlab.pms.core.report.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzWeeklyMapper extends BaseMapper<IbzWeekly> {

    List<IbzWeekly> selectQueryByDefault(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    List<IbzWeekly> selectQueryByMyNotSubmit(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    List<IbzWeekly> selectQueryByMyWeekly(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    List<IbzWeekly> selectQueryByProductTeamMemberWeekly(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    List<IbzWeekly> selectQueryByProjectWeekly(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    List<IbzWeekly> selectQueryByView(@Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);

    Page<IbzWeekly> searchDefault(IPage page, @Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    Page<IbzWeekly> searchMyNotSubmit(IPage page, @Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    Page<IbzWeekly> searchMyWeekly(IPage page, @Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    Page<IbzWeekly> searchProductTeamMemberWeekly(IPage page, @Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    Page<IbzWeekly> searchProjectWeekly(IPage page, @Param("srf") IbzWeeklySearchContext context, @Param("ew") Wrapper<IbzWeekly> wrapper);
    @Override
    IbzWeekly selectById(Serializable id);
    @Override
    int insert(IbzWeekly entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzWeekly entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzWeekly entity, @Param("ew") Wrapper<IbzWeekly> updateWrapper);
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
