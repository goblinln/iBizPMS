package cn.ibizlab.pms.core.report.mapper;

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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzReportlyMapper extends BaseMapper<IbzReportly> {

    Page<IbzReportly> searchDefault(IPage page, @Param("srf") IbzReportlySearchContext context, @Param("ew") Wrapper<IbzReportly> wrapper);
    Page<IbzReportly> searchMyAllReportly(IPage page, @Param("srf") IbzReportlySearchContext context, @Param("ew") Wrapper<IbzReportly> wrapper);
    Page<IbzReportly> searchMyReceived(IPage page, @Param("srf") IbzReportlySearchContext context, @Param("ew") Wrapper<IbzReportly> wrapper);
    Page<IbzReportly> searchMyReportlyMob(IPage page, @Param("srf") IbzReportlySearchContext context, @Param("ew") Wrapper<IbzReportly> wrapper);
    @Override
    IbzReportly selectById(Serializable id);
    @Override
    int insert(IbzReportly entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzReportly entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzReportly entity, @Param("ew") Wrapper<IbzReportly> updateWrapper);
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
