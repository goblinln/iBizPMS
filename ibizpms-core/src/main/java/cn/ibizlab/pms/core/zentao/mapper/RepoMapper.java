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
import cn.ibizlab.pms.core.zentao.domain.Repo;
import cn.ibizlab.pms.core.zentao.filter.RepoSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface RepoMapper extends BaseMapper<Repo> {

    List<Repo> selectQueryByDefault(@Param("srf") RepoSearchContext context, @Param("ew") Wrapper<Repo> wrapper);
    List<Repo> selectQueryByView(@Param("srf") RepoSearchContext context, @Param("ew") Wrapper<Repo> wrapper);

    Page<Repo> searchDefault(IPage page, @Param("srf") RepoSearchContext context, @Param("ew") Wrapper<Repo> wrapper);
    @Override
    Repo selectById(Serializable id);
    @Override
    int insert(Repo entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Repo entity);
    @Override
    int update(@Param(Constants.ENTITY) Repo entity, @Param("ew") Wrapper<Repo> updateWrapper);
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
