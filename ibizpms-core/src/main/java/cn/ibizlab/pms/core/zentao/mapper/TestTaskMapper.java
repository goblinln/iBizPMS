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
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TestTaskMapper extends BaseMapper<TestTask> {

    List<TestTask> selectQueryByDefault(@Param("srf") TestTaskSearchContext context, @Param("ew") Wrapper<TestTask> wrapper);
    List<TestTask> selectQueryByMyTestTaskPc(@Param("srf") TestTaskSearchContext context, @Param("ew") Wrapper<TestTask> wrapper);
    List<TestTask> selectQueryByView(@Param("srf") TestTaskSearchContext context, @Param("ew") Wrapper<TestTask> wrapper);

    Page<TestTask> searchDefault(IPage page, @Param("srf") TestTaskSearchContext context, @Param("ew") Wrapper<TestTask> wrapper);
    Page<TestTask> searchMyTestTaskPc(IPage page, @Param("srf") TestTaskSearchContext context, @Param("ew") Wrapper<TestTask> wrapper);
    @Override
    TestTask selectById(Serializable id);
    @Override
    int insert(TestTask entity);
    @Override
    int updateById(@Param(Constants.ENTITY) TestTask entity);
    @Override
    int update(@Param(Constants.ENTITY) TestTask entity, @Param("ew") Wrapper<TestTask> updateWrapper);
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

    List<TestTask> selectByBuild(@Param("id") Serializable id);

    List<TestTask> selectByProduct(@Param("id") Serializable id);

    List<TestTask> selectByProject(@Param("id") Serializable id);

}
