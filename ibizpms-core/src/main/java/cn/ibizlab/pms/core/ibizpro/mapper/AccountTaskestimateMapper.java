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
import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface AccountTaskestimateMapper extends BaseMapper<AccountTaskestimate> {

    List<AccountTaskestimate> selectAllAccountEstimate(@Param("srf") AccountTaskestimateSearchContext context, @Param("ew") Wrapper<AccountTaskestimate> wrapper);
    List<AccountTaskestimate> selectDefault(@Param("srf") AccountTaskestimateSearchContext context, @Param("ew") Wrapper<AccountTaskestimate> wrapper);
    List<AccountTaskestimate> selectView(@Param("srf") AccountTaskestimateSearchContext context, @Param("ew") Wrapper<AccountTaskestimate> wrapper);

    Page<AccountTaskestimate> searchAllAccountEstimate(IPage page, @Param("srf") AccountTaskestimateSearchContext context, @Param("ew") Wrapper<AccountTaskestimate> wrapper);
    Page<AccountTaskestimate> searchDefault(IPage page, @Param("srf") AccountTaskestimateSearchContext context, @Param("ew") Wrapper<AccountTaskestimate> wrapper);
    @Override
    AccountTaskestimate selectById(Serializable id);
    @Override
    int insert(AccountTaskestimate entity);
    @Override
    int updateById(@Param(Constants.ENTITY) AccountTaskestimate entity);
    @Override
    int update(@Param(Constants.ENTITY) AccountTaskestimate entity, @Param("ew") Wrapper<AccountTaskestimate> updateWrapper);
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
