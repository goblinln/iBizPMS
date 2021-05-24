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
import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProBugHostorySearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IBZProBugHostoryMapper extends BaseMapper<IBZProBugHostory> {

    List<IBZProBugHostory> selectDefault(@Param("srf") IBZProBugHostorySearchContext context, @Param("ew") Wrapper<IBZProBugHostory> wrapper);
    List<IBZProBugHostory> selectSimple(@Param("srf") IBZProBugHostorySearchContext context, @Param("ew") Wrapper<IBZProBugHostory> wrapper);
    List<IBZProBugHostory> selectView(@Param("srf") IBZProBugHostorySearchContext context, @Param("ew") Wrapper<IBZProBugHostory> wrapper);

    Page<IBZProBugHostory> searchDefault(IPage page, @Param("srf") IBZProBugHostorySearchContext context, @Param("ew") Wrapper<IBZProBugHostory> wrapper);
    @Override
    IBZProBugHostory selectById(Serializable id);
    @Override
    int insert(IBZProBugHostory entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IBZProBugHostory entity);
    @Override
    int update(@Param(Constants.ENTITY) IBZProBugHostory entity, @Param("ew") Wrapper<IBZProBugHostory> updateWrapper);
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

    List<IBZProBugHostory> selectByAction(@Param("id") Serializable id);

}
