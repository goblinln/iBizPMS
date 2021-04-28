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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProductPlanMapper extends BaseMapper<ProductPlan> {

    List<ProductPlan> selectChildPlan(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectCurProductPlan(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectDefault(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectDefaultParent(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectGetList(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectPlanCodeList(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectPlanTasks(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectProductQuery(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectProjectApp(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectProjectPlan(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectRootPlan(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectTaskPlan(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    List<ProductPlan> selectView(@Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);

    Page<ProductPlan> searchChildPlan(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchCurProductPlan(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchDefault(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchDefaultParent(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchPlanCodeList(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchPlanTasks(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchProductQuery(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchProjectApp(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchProjectPlan(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchRootPlan(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    Page<ProductPlan> searchTaskPlan(IPage page, @Param("srf") ProductPlanSearchContext context, @Param("ew") Wrapper<ProductPlan> wrapper);
    @Override
    ProductPlan selectById(Serializable id);
    @Override
    int insert(ProductPlan entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProductPlan entity);
    @Override
    int update(@Param(Constants.ENTITY) ProductPlan entity, @Param("ew") Wrapper<ProductPlan> updateWrapper);
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

    List<ProductPlan> selectByBranch(@Param("id") Serializable id);

    List<ProductPlan> selectByProduct(@Param("id") Serializable id);

    List<ProductPlan> selectByParent(@Param("id") Serializable id);

}
