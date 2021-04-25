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
import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.core.zentao.filter.TestSuiteSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TestSuiteMapper extends BaseMapper<TestSuite> {

    List<TestSuite> selectQueryByDefault(@Param("srf") TestSuiteSearchContext context, @Param("ew") Wrapper<TestSuite> wrapper);
    List<TestSuite> selectQueryByPublicTestSuite(@Param("srf") TestSuiteSearchContext context, @Param("ew") Wrapper<TestSuite> wrapper);
    List<TestSuite> selectQueryByView(@Param("srf") TestSuiteSearchContext context, @Param("ew") Wrapper<TestSuite> wrapper);

    Page<TestSuite> searchDefault(IPage page, @Param("srf") TestSuiteSearchContext context, @Param("ew") Wrapper<TestSuite> wrapper);
    Page<TestSuite> searchPublicTestSuite(IPage page, @Param("srf") TestSuiteSearchContext context, @Param("ew") Wrapper<TestSuite> wrapper);
    @Override
    TestSuite selectById(Serializable id);
    @Override
    int insert(TestSuite entity);
    @Override
    int updateById(@Param(Constants.ENTITY) TestSuite entity);
    @Override
    int update(@Param(Constants.ENTITY) TestSuite entity, @Param("ew") Wrapper<TestSuite> updateWrapper);
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

    List<TestSuite> selectByProduct(@Param("id") Serializable id);

}
