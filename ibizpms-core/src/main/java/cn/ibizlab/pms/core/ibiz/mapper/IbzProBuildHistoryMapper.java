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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildHistorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzProBuildHistoryMapper extends BaseMapper<IbzProBuildHistory> {

    List<IbzProBuildHistory> selectDefault(@Param("srf") IbzProBuildHistorySearchContext context, @Param("ew") Wrapper<IbzProBuildHistory> wrapper);
    List<IbzProBuildHistory> selectSimple(@Param("srf") IbzProBuildHistorySearchContext context, @Param("ew") Wrapper<IbzProBuildHistory> wrapper);
    List<IbzProBuildHistory> selectView(@Param("srf") IbzProBuildHistorySearchContext context, @Param("ew") Wrapper<IbzProBuildHistory> wrapper);

    Page<IbzProBuildHistory> searchDefault(IPage page, @Param("srf") IbzProBuildHistorySearchContext context, @Param("ew") Wrapper<IbzProBuildHistory> wrapper);
    @Override
    IbzProBuildHistory selectById(Serializable id);
    @Override
    int insert(IbzProBuildHistory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzProBuildHistory entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzProBuildHistory entity, @Param("ew") Wrapper<IbzProBuildHistory> updateWrapper);
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
