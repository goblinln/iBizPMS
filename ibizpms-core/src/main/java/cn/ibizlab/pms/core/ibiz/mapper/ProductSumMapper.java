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
import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProductSumMapper extends BaseMapper<ProductSum> {

    Page<ProductSum> searchDefault(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductBugcnt_QA(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductCreateStory(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductStoryHoursSum(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductStorySum(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductStorycntAndPlancnt(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    Page<ProductSum> searchProductSumBugType(IPage page, @Param("srf") ProductSumSearchContext context, @Param("ew") Wrapper<ProductSum> wrapper);
    @Override
    ProductSum selectById(Serializable id);
    @Override
    int insert(ProductSum entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProductSum entity);
    @Override
    int update(@Param(Constants.ENTITY) ProductSum entity, @Param("ew") Wrapper<ProductSum> updateWrapper);
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
