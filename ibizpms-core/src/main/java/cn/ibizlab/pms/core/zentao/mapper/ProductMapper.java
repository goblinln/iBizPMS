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
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProductMapper extends BaseMapper<Product> {

    Page<Product> searchAllList(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchAllProduct(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchCheckNameOrCode(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchCurDefault(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchCurProject(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchCurUer(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchDefault(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchDeveloperQuery(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchESBulk(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchOpenQuery(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchProductManagerQuery(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchProductPM(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchProductTeam(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    Page<Product> searchStoryCurProject(IPage page, @Param("srf") ProductSearchContext context, @Param("ew") Wrapper<Product> wrapper);
    @Override
    Product selectById(Serializable id);
    @Override
    int insert(Product entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Product entity);
    @Override
    int update(@Param(Constants.ENTITY) Product entity, @Param("ew") Wrapper<Product> updateWrapper);
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

    List<Product> selectByLine(@Param("id") Serializable id);

}
