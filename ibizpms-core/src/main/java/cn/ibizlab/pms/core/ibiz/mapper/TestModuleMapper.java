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
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TestModuleMapper extends BaseMapper<TestModule> {

    Page<TestModule> searchByPath(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    Page<TestModule> searchDefault(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    Page<TestModule> searchParentModule(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    Page<TestModule> searchRoot(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    Page<TestModule> searchRoot_NoBranch(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    Page<TestModule> searchTestModule(IPage page, @Param("srf") TestModuleSearchContext context, @Param("ew") Wrapper<TestModule> wrapper);
    @Override
    TestModule selectById(Serializable id);
    @Override
    int insert(TestModule entity);
    @Override
    int updateById(@Param(Constants.ENTITY) TestModule entity);
    @Override
    int update(@Param(Constants.ENTITY) TestModule entity, @Param("ew") Wrapper<TestModule> updateWrapper);
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

    List<TestModule> selectByParent(@Param("id") Serializable id);

    List<TestModule> selectByRoot(@Param("id") Serializable id);

}
