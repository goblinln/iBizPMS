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
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ActionMapper extends BaseMapper<Action> {

    List<Action> selectAccount(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectBianGengLineHistory(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectDefault(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectMobType(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectMy(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectMyAction(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectMyTrends(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectProductTrends(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectProjectTrends(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectQueryUserYEAR(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectType(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    List<Action> selectView(@Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);

    Page<Action> searchAccount(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchDefault(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchMobType(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchMy(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchMyAction(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchMyTrends(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchProductTrends(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchProjectTrends(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchQueryUserYEAR(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    Page<Action> searchType(IPage page, @Param("srf") ActionSearchContext context, @Param("ew") Wrapper<Action> wrapper);
    @Override
    Action selectById(Serializable id);
    @Override
    int insert(Action entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Action entity);
    @Override
    int update(@Param(Constants.ENTITY) Action entity, @Param("ew") Wrapper<Action> updateWrapper);
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

    List<Action> selectByProject(@Param("id") Serializable id);

}
