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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzDailyMapper extends BaseMapper<IbzDaily> {

    List<IbzDaily> selectDefault(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectMyAllDaily(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectMyDaily(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectMyNotSubmit(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectMySubmitDaily(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectProductDaily(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectProjectDaily(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    List<IbzDaily> selectView(@Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);

    Page<IbzDaily> searchDefault(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchMyAllDaily(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchMyDaily(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchMyNotSubmit(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchMySubmitDaily(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchProductDaily(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    Page<IbzDaily> searchProjectDaily(IPage page, @Param("srf") IbzDailySearchContext context, @Param("ew") Wrapper<IbzDaily> wrapper);
    @Override
    IbzDaily selectById(Serializable id);
    @Override
    int insert(IbzDaily entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzDaily entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzDaily entity, @Param("ew") Wrapper<IbzDaily> updateWrapper);
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
