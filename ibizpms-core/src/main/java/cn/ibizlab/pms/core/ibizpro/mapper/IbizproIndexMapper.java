package cn.ibizlab.pms.core.ibizpro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbizproIndexMapper extends BaseMapper<IbizproIndex> {

    List<IbizproIndex> selectDefault(@Param("srf") IbizproIndexSearchContext context, @Param("ew") Wrapper<IbizproIndex> wrapper);
    List<IbizproIndex> selectView(@Param("srf") IbizproIndexSearchContext context, @Param("ew") Wrapper<IbizproIndex> wrapper);

    Page<IbizproIndex> searchDefault(IPage page, @Param("srf") IbizproIndexSearchContext context, @Param("ew") Wrapper<IbizproIndex> wrapper);
    Page<IbizproIndex> searchESquery(IPage page, @Param("srf") IbizproIndexSearchContext context, @Param("ew") Wrapper<IbizproIndex> wrapper);
    Page<IbizproIndex> searchIndexDER(IPage page, @Param("srf") IbizproIndexSearchContext context, @Param("ew") Wrapper<IbizproIndex> wrapper);
    @Override
    IbizproIndex selectById(Serializable id);
    @Override
    int insert(IbizproIndex entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbizproIndex entity);
    @Override
    int update(@Param(Constants.ENTITY) IbizproIndex entity, @Param("ew") Wrapper<IbizproIndex> updateWrapper);
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
