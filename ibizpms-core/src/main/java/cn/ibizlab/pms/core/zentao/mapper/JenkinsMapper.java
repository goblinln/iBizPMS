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
import cn.ibizlab.pms.core.zentao.domain.Jenkins;
import cn.ibizlab.pms.core.zentao.filter.JenkinsSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface JenkinsMapper extends BaseMapper<Jenkins> {

    List<Jenkins> selectQueryByDefault(@Param("srf") JenkinsSearchContext context, @Param("ew") Wrapper<Jenkins> wrapper);
    List<Jenkins> selectQueryByView(@Param("srf") JenkinsSearchContext context, @Param("ew") Wrapper<Jenkins> wrapper);

    Page<Jenkins> searchDefault(IPage page, @Param("srf") JenkinsSearchContext context, @Param("ew") Wrapper<Jenkins> wrapper);
    @Override
    Jenkins selectById(Serializable id);
    @Override
    int insert(Jenkins entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Jenkins entity);
    @Override
    int update(@Param(Constants.ENTITY) Jenkins entity, @Param("ew") Wrapper<Jenkins> updateWrapper);
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
