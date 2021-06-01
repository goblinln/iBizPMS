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
import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TodoMapper extends BaseMapper<Todo> {

    List<Todo> selectAccount(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectDefault(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectMy(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectMyCreateTodo(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectMyTodo(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectMyTodoPc(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectMyUpcoming(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    List<Todo> selectView(@Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);

    Page<Todo> searchAccount(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchDefault(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchMy(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchMyCreateTodo(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchMyTodo(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchMyTodoPc(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    Page<Todo> searchMyUpcoming(IPage page, @Param("srf") TodoSearchContext context, @Param("ew") Wrapper<Todo> wrapper);
    @Override
    Todo selectById(Serializable id);
    @Override
    int insert(Todo entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Todo entity);
    @Override
    int update(@Param(Constants.ENTITY) Todo entity, @Param("ew") Wrapper<Todo> updateWrapper);
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
