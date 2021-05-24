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
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanHistory;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanHistorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProductPlanHistoryMapper extends BaseMapper<ProductPlanHistory> {

    List<ProductPlanHistory> selectDefault(@Param("srf") ProductPlanHistorySearchContext context, @Param("ew") Wrapper<ProductPlanHistory> wrapper);
    List<ProductPlanHistory> selectSimple(@Param("srf") ProductPlanHistorySearchContext context, @Param("ew") Wrapper<ProductPlanHistory> wrapper);
    List<ProductPlanHistory> selectView(@Param("srf") ProductPlanHistorySearchContext context, @Param("ew") Wrapper<ProductPlanHistory> wrapper);

    Page<ProductPlanHistory> searchDefault(IPage page, @Param("srf") ProductPlanHistorySearchContext context, @Param("ew") Wrapper<ProductPlanHistory> wrapper);
    @Override
    ProductPlanHistory selectById(Serializable id);
    @Override
    int insert(ProductPlanHistory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProductPlanHistory entity);
    @Override
    int update(@Param(Constants.ENTITY) ProductPlanHistory entity, @Param("ew") Wrapper<ProductPlanHistory> updateWrapper);
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

    List<ProductPlanHistory> selectByAction(@Param("id") Serializable id);

}
