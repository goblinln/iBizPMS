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
import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProductLifeMapper extends BaseMapper<ProductLife> {

    List<ProductLife> selectDefault(@Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    List<ProductLife> selectGetRoadmap(@Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    List<ProductLife> selectGetRoadmapS(@Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    List<ProductLife> selectRoadMapYear(@Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    List<ProductLife> selectView(@Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);

    Page<ProductLife> searchDefault(IPage page, @Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    Page<ProductLife> searchGetRoadmap(IPage page, @Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    Page<ProductLife> searchGetRoadmapS(IPage page, @Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    Page<ProductLife> searchRoadMapYear(IPage page, @Param("srf") ProductLifeSearchContext context, @Param("ew") Wrapper<ProductLife> wrapper);
    @Override
    ProductLife selectById(Serializable id);
    @Override
    int insert(ProductLife entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProductLife entity);
    @Override
    int update(@Param(Constants.ENTITY) ProductLife entity, @Param("ew") Wrapper<ProductLife> updateWrapper);
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
