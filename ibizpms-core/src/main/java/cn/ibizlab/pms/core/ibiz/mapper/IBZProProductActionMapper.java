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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductActionSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProProductActionMapper extends BaseMapper<IBZProProductAction> {

    List<IBZProProductAction> selectDefault(@Param("srf") IBZProProductActionSearchContext context, @Param("ew") Wrapper<IBZProProductAction> wrapper);
    List<IBZProProductAction> selectSimple(@Param("srf") IBZProProductActionSearchContext context, @Param("ew") Wrapper<IBZProProductAction> wrapper);
    List<IBZProProductAction> selectView(@Param("srf") IBZProProductActionSearchContext context, @Param("ew") Wrapper<IBZProProductAction> wrapper);

    Page<IBZProProductAction> searchDefault(IPage page, @Param("srf") IBZProProductActionSearchContext context, @Param("ew") Wrapper<IBZProProductAction> wrapper);
    @Override
    IBZProProductAction selectById(Serializable id);
    @Override
    int insert(IBZProProductAction entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProProductAction entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProProductAction entity, @Param("ew") Wrapper<IBZProProductAction> updateWrapper);
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

    List<IBZProProductAction> selectByObjectid(@Param("id") Serializable id);

}
