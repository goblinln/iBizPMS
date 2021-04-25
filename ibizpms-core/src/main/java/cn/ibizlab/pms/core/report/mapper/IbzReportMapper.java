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
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzReportMapper extends BaseMapper<IbzReport> {

    List<IbzReport> selectQueryByAllReport(@Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    List<IbzReport> selectQueryByDefault(@Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    List<IbzReport> selectQueryByMyReAllReport(@Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    List<IbzReport> selectQueryByView(@Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);

    Page<IbzReport> searchAllReport(IPage page, @Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    Page<IbzReport> searchDefault(IPage page, @Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    Page<IbzReport> searchMyReAllReport(IPage page, @Param("srf") IbzReportSearchContext context, @Param("ew") Wrapper<IbzReport> wrapper);
    @Override
    IbzReport selectById(Serializable id);
    @Override
    int insert(IbzReport entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzReport entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzReport entity, @Param("ew") Wrapper<IbzReport> updateWrapper);
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
