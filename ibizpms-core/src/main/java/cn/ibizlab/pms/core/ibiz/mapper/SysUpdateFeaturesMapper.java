package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface SysUpdateFeaturesMapper extends BaseMapper<SysUpdateFeatures> {

    Page<SysUpdateFeatures> searchDefault(IPage page, @Param("srf") SysUpdateFeaturesSearchContext context, @Param("ew") Wrapper<SysUpdateFeatures> wrapper);
    @Override
    SysUpdateFeatures selectById(Serializable id);
    @Override
    int insert(SysUpdateFeatures entity);
    @Override
    int updateById(@Param(Constants.ENTITY) SysUpdateFeatures entity);
    @Override
    int update(@Param(Constants.ENTITY) SysUpdateFeatures entity, @Param("ew") Wrapper<SysUpdateFeatures> updateWrapper);
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

    List<SysUpdateFeatures> selectBySysupdatelogid(@Param("sysupdatelogid") Serializable sysupdatelogid);

}
