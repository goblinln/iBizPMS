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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface BuildMapper extends BaseMapper<Build> {

    List<Build> selectBugProductBuild(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectBugProductOrProjectBuild(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectCurProduct(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectDefault(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectSimple(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectTestBuild(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectTestRounds(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectUpdateLog(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    List<Build> selectView(@Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);

    Page<Build> searchBugProductBuild(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchBugProductOrProjectBuild(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchCurProduct(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchDefault(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchProductBuildDS(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchTestBuild(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchTestRounds(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    Page<Build> searchUpdateLog(IPage page, @Param("srf") BuildSearchContext context, @Param("ew") Wrapper<Build> wrapper);
    @Override
    Build selectById(Serializable id);
    @Override
    int insert(Build entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Build entity);
    @Override
    int update(@Param(Constants.ENTITY) Build entity, @Param("ew") Wrapper<Build> updateWrapper);
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

    List<Build> selectByBranch(@Param("id") Serializable id);

    List<Build> selectByProduct(@Param("id") Serializable id);

    List<Build> selectByProject(@Param("id") Serializable id);

}
