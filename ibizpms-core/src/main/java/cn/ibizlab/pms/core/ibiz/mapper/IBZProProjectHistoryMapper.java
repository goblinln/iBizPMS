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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectHistorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProProjectHistoryMapper extends BaseMapper<IBZProProjectHistory> {

    List<IBZProProjectHistory> selectDefault(@Param("srf") IBZProProjectHistorySearchContext context, @Param("ew") Wrapper<IBZProProjectHistory> wrapper);
    List<IBZProProjectHistory> selectSimple(@Param("srf") IBZProProjectHistorySearchContext context, @Param("ew") Wrapper<IBZProProjectHistory> wrapper);
    List<IBZProProjectHistory> selectView(@Param("srf") IBZProProjectHistorySearchContext context, @Param("ew") Wrapper<IBZProProjectHistory> wrapper);

    Page<IBZProProjectHistory> searchDefault(IPage page, @Param("srf") IBZProProjectHistorySearchContext context, @Param("ew") Wrapper<IBZProProjectHistory> wrapper);
    @Override
    IBZProProjectHistory selectById(Serializable id);
    @Override
    int insert(IBZProProjectHistory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProProjectHistory entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProProjectHistory entity, @Param("ew") Wrapper<IBZProProjectHistory> updateWrapper);
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

    List<IBZProProjectHistory> selectByAction(@Param("id") Serializable id);

}
