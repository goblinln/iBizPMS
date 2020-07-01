package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.HashMap;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.IBZ_PROJECTTEAM;
import cn.ibizlab.pms.core.ibiz.filter.IBZ_PROJECTTEAMSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZ_PROJECTTEAMMapper extends BaseMapper<IBZ_PROJECTTEAM>{

    Page<IBZ_PROJECTTEAM> searchDefault(IPage page, @Param("srf") IBZ_PROJECTTEAMSearchContext context, @Param("ew") Wrapper<IBZ_PROJECTTEAM> wrapper) ;
    @Override
    IBZ_PROJECTTEAM selectById(Serializable id);
    @Override
    int insert(IBZ_PROJECTTEAM entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZ_PROJECTTEAM entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZ_PROJECTTEAM entity, @Param("ew") Wrapper<IBZ_PROJECTTEAM> updateWrapper);
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

    List<IBZ_PROJECTTEAM> selectByRoot(@Param("id") Serializable id) ;

}
