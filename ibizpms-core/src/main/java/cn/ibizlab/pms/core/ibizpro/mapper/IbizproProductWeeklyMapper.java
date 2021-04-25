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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbizproProductWeeklyMapper extends BaseMapper<IbizproProductWeekly> {

    List<IbizproProductWeekly> selectQueryByDefault(@Param("srf") IbizproProductWeeklySearchContext context, @Param("ew") Wrapper<IbizproProductWeekly> wrapper);
    List<IbizproProductWeekly> selectQueryByView(@Param("srf") IbizproProductWeeklySearchContext context, @Param("ew") Wrapper<IbizproProductWeekly> wrapper);

    Page<IbizproProductWeekly> searchDefault(IPage page, @Param("srf") IbizproProductWeeklySearchContext context, @Param("ew") Wrapper<IbizproProductWeekly> wrapper);
    @Override
    IbizproProductWeekly selectById(Serializable id);
    @Override
    int insert(IbizproProductWeekly entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbizproProductWeekly entity);
    @Override
    int update(@Param(Constants.ENTITY) IbizproProductWeekly entity, @Param("ew") Wrapper<IbizproProductWeekly> updateWrapper);
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

    List<IbizproProductWeekly> selectByProduct(@Param("id") Serializable id);

}
