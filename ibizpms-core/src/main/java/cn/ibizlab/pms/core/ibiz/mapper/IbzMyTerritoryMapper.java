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
import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzMyTerritoryMapper extends BaseMapper<IbzMyTerritory> {

    List<IbzMyTerritory> selectDefault(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectMyWork(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectMyWorkMob(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectMyWorkPm(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectPersonInfo(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectView(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    List<IbzMyTerritory> selectWelcome(@Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);

    Page<IbzMyTerritory> searchDefault(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    Page<IbzMyTerritory> searchMyWork(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    Page<IbzMyTerritory> searchMyWorkMob(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    Page<IbzMyTerritory> searchMyWorkPm(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    Page<IbzMyTerritory> searchPersonInfo(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    Page<IbzMyTerritory> searchWelcome(IPage page, @Param("srf") IbzMyTerritorySearchContext context, @Param("ew") Wrapper<IbzMyTerritory> wrapper);
    @Override
    IbzMyTerritory selectById(Serializable id);
    @Override
    int insert(IbzMyTerritory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzMyTerritory entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzMyTerritory entity, @Param("ew") Wrapper<IbzMyTerritory> updateWrapper);
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
