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
import cn.ibizlab.pms.core.zentao.domain.Effort;
import cn.ibizlab.pms.core.zentao.filter.EffortSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface EffortMapper extends BaseMapper<Effort> {

    List<Effort> selectQueryByDefault(@Param("srf") EffortSearchContext context, @Param("ew") Wrapper<Effort> wrapper);
    List<Effort> selectQueryByView(@Param("srf") EffortSearchContext context, @Param("ew") Wrapper<Effort> wrapper);

    Page<Effort> searchDefault(IPage page, @Param("srf") EffortSearchContext context, @Param("ew") Wrapper<Effort> wrapper);
    @Override
    Effort selectById(Serializable id);
    @Override
    int insert(Effort entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Effort entity);
    @Override
    int update(@Param(Constants.ENTITY) Effort entity, @Param("ew") Wrapper<Effort> updateWrapper);
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
