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
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface DocLibMapper extends BaseMapper<DocLib> {

    Page<DocLib> searchByCustom(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchByProduct(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchByProductNotFiles(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchByProject(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchByProjectNotFiles(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchCurDocLib(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchDefault(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchMyFavourites(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    Page<DocLib> searchRootModuleMuLu(IPage page, @Param("srf") DocLibSearchContext context, @Param("ew") Wrapper<DocLib> wrapper);
    @Override
    DocLib selectById(Serializable id);
    @Override
    int insert(DocLib entity);
    @Override
    int updateById(@Param(Constants.ENTITY) DocLib entity);
    @Override
    int update(@Param(Constants.ENTITY) DocLib entity, @Param("ew") Wrapper<DocLib> updateWrapper);
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

    List<DocLib> selectByProduct(@Param("id") Serializable id);

    List<DocLib> selectByProject(@Param("id") Serializable id);

}
