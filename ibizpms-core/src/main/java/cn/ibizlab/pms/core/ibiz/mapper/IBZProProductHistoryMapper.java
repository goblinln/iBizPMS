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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductHistorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProProductHistoryMapper extends BaseMapper<IBZProProductHistory> {

    List<IBZProProductHistory> selectDefault(@Param("srf") IBZProProductHistorySearchContext context, @Param("ew") Wrapper<IBZProProductHistory> wrapper);
    List<IBZProProductHistory> selectSimple(@Param("srf") IBZProProductHistorySearchContext context, @Param("ew") Wrapper<IBZProProductHistory> wrapper);
    List<IBZProProductHistory> selectView(@Param("srf") IBZProProductHistorySearchContext context, @Param("ew") Wrapper<IBZProProductHistory> wrapper);

    Page<IBZProProductHistory> searchDefault(IPage page, @Param("srf") IBZProProductHistorySearchContext context, @Param("ew") Wrapper<IBZProProductHistory> wrapper);
    @Override
    IBZProProductHistory selectById(Serializable id);
    @Override
    int insert(IBZProProductHistory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProProductHistory entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProProductHistory entity, @Param("ew") Wrapper<IBZProProductHistory> updateWrapper);
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

    List<IBZProProductHistory> selectByAction(@Param("id") Serializable id);

}
