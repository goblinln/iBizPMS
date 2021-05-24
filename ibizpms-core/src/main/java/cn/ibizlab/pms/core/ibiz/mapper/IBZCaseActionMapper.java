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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZCaseActionMapper extends BaseMapper<IBZCaseAction> {

    List<IBZCaseAction> selectDefault(@Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);
    List<IBZCaseAction> selectSimple(@Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);
    List<IBZCaseAction> selectType(@Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);
    List<IBZCaseAction> selectView(@Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);

    Page<IBZCaseAction> searchDefault(IPage page, @Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);
    Page<IBZCaseAction> searchType(IPage page, @Param("srf") IBZCaseActionSearchContext context, @Param("ew") Wrapper<IBZCaseAction> wrapper);
    @Override
    IBZCaseAction selectById(Serializable id);
    @Override
    int insert(IBZCaseAction entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZCaseAction entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZCaseAction entity, @Param("ew") Wrapper<IBZCaseAction> updateWrapper);
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

    List<IBZCaseAction> selectByObjectid(@Param("id") Serializable id);

}
