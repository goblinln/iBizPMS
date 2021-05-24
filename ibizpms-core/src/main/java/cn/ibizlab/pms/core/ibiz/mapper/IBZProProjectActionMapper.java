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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectActionSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProProjectActionMapper extends BaseMapper<IBZProProjectAction> {

    List<IBZProProjectAction> selectDefault(@Param("srf") IBZProProjectActionSearchContext context, @Param("ew") Wrapper<IBZProProjectAction> wrapper);
    List<IBZProProjectAction> selectSimple(@Param("srf") IBZProProjectActionSearchContext context, @Param("ew") Wrapper<IBZProProjectAction> wrapper);
    List<IBZProProjectAction> selectView(@Param("srf") IBZProProjectActionSearchContext context, @Param("ew") Wrapper<IBZProProjectAction> wrapper);

    Page<IBZProProjectAction> searchDefault(IPage page, @Param("srf") IBZProProjectActionSearchContext context, @Param("ew") Wrapper<IBZProProjectAction> wrapper);
    @Override
    IBZProProjectAction selectById(Serializable id);
    @Override
    int insert(IBZProProjectAction entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProProjectAction entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProProjectAction entity, @Param("ew") Wrapper<IBZProProjectAction> updateWrapper);
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

    List<IBZProProjectAction> selectByObjectid(@Param("id") Serializable id);

}
