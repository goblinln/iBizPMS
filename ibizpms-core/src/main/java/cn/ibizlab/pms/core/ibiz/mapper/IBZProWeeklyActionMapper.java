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
import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProWeeklyActionSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProWeeklyActionMapper extends BaseMapper<IBZProWeeklyAction> {

    List<IBZProWeeklyAction> selectDefault(@Param("srf") IBZProWeeklyActionSearchContext context, @Param("ew") Wrapper<IBZProWeeklyAction> wrapper);
    List<IBZProWeeklyAction> selectSimple(@Param("srf") IBZProWeeklyActionSearchContext context, @Param("ew") Wrapper<IBZProWeeklyAction> wrapper);
    List<IBZProWeeklyAction> selectType(@Param("srf") IBZProWeeklyActionSearchContext context, @Param("ew") Wrapper<IBZProWeeklyAction> wrapper);
    List<IBZProWeeklyAction> selectView(@Param("srf") IBZProWeeklyActionSearchContext context, @Param("ew") Wrapper<IBZProWeeklyAction> wrapper);

    Page<IBZProWeeklyAction> searchDefault(IPage page, @Param("srf") IBZProWeeklyActionSearchContext context, @Param("ew") Wrapper<IBZProWeeklyAction> wrapper);
    @Override
    IBZProWeeklyAction selectById(Serializable id);
    @Override
    int insert(IBZProWeeklyAction entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProWeeklyAction entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProWeeklyAction entity, @Param("ew") Wrapper<IBZProWeeklyAction> updateWrapper);
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

    List<IBZProWeeklyAction> selectByObjectid(@Param("ibzweeklyid") Serializable ibzweeklyid);

}
