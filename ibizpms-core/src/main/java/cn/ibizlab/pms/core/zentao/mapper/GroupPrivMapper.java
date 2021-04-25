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
import cn.ibizlab.pms.core.zentao.domain.GroupPriv;
import cn.ibizlab.pms.core.zentao.filter.GroupPrivSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface GroupPrivMapper extends BaseMapper<GroupPriv> {

    List<GroupPriv> selectQueryByDefault(@Param("srf") GroupPrivSearchContext context, @Param("ew") Wrapper<GroupPriv> wrapper);
    List<GroupPriv> selectQueryByView(@Param("srf") GroupPrivSearchContext context, @Param("ew") Wrapper<GroupPriv> wrapper);

    Page<GroupPriv> searchDefault(IPage page, @Param("srf") GroupPrivSearchContext context, @Param("ew") Wrapper<GroupPriv> wrapper);
    @Override
    GroupPriv selectById(Serializable id);
    @Override
    int insert(GroupPriv entity);
    @Override
    int updateById(@Param(Constants.ENTITY) GroupPriv entity);
    @Override
    int update(@Param(Constants.ENTITY) GroupPriv entity, @Param("ew") Wrapper<GroupPriv> updateWrapper);
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
