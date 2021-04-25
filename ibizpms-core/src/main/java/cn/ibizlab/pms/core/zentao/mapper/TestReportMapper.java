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
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TestReportMapper extends BaseMapper<TestReport> {

    List<TestReport> selectQueryByDefault(@Param("srf") TestReportSearchContext context, @Param("ew") Wrapper<TestReport> wrapper);
    List<TestReport> selectQueryByView(@Param("srf") TestReportSearchContext context, @Param("ew") Wrapper<TestReport> wrapper);

    Page<TestReport> searchDefault(IPage page, @Param("srf") TestReportSearchContext context, @Param("ew") Wrapper<TestReport> wrapper);
    @Override
    TestReport selectById(Serializable id);
    @Override
    int insert(TestReport entity);
    @Override
    int updateById(@Param(Constants.ENTITY) TestReport entity);
    @Override
    int update(@Param(Constants.ENTITY) TestReport entity, @Param("ew") Wrapper<TestReport> updateWrapper);
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

    List<TestReport> selectByProduct(@Param("id") Serializable id);

    List<TestReport> selectByProject(@Param("id") Serializable id);

}
