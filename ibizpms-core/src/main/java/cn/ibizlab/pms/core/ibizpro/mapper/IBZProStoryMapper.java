package cn.ibizlab.pms.core.ibizpro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStory;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProStoryMapper extends BaseMapper<IBZProStory> {

    List<IBZProStory> selectDefault(@Param("srf") IBZProStorySearchContext context, @Param("ew") Wrapper<IBZProStory> wrapper);
    List<IBZProStory> selectView(@Param("srf") IBZProStorySearchContext context, @Param("ew") Wrapper<IBZProStory> wrapper);

    Page<IBZProStory> searchDefault(IPage page, @Param("srf") IBZProStorySearchContext context, @Param("ew") Wrapper<IBZProStory> wrapper);
    @Override
    IBZProStory selectById(Serializable id);
    @Override
    int insert(IBZProStory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProStory entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProStory entity, @Param("ew") Wrapper<IBZProStory> updateWrapper);
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

    List<IBZProStory> selectByProduct(@Param("id") Serializable id);

    List<IBZProStory> selectByModule(@Param("id") Serializable id);

}
