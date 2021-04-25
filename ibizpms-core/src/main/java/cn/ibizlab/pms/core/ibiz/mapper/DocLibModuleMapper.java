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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface DocLibModuleMapper extends BaseMapper<DocLibModule> {

    List<DocLibModule> selectQueryByAllDoclibModule_Custom(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByChildModuleByParent(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByChildModuleByRealParent(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByDefault(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByDefaultDoclib(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByMyFavourites(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByParentModule(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByRootModuleMuLu(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByRootModuleMuLuByRoot(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByRootModuleMuLuBysrfparentkey(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    List<DocLibModule> selectQueryByView(@Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);

    Page<DocLibModule> searchAllDocLibModule_Custom(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchAllDoclibModule(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchChildModuleByParent(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchChildModuleByRealParent(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchDefault(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchMyFavourites(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchParentModule(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchRootModuleMuLu(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchRootModuleMuLuByRoot(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    Page<DocLibModule> searchRootModuleMuLuBysrfparentkey(IPage page, @Param("srf") DocLibModuleSearchContext context, @Param("ew") Wrapper<DocLibModule> wrapper);
    @Override
    DocLibModule selectById(Serializable id);
    @Override
    int insert(DocLibModule entity);
    @Override
    int updateById(@Param(Constants.ENTITY) DocLibModule entity);
    @Override
    int update(@Param(Constants.ENTITY) DocLibModule entity, @Param("ew") Wrapper<DocLibModule> updateWrapper);
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

    List<DocLibModule> selectByParent(@Param("id") Serializable id);

    List<DocLibModule> selectByRoot(@Param("id") Serializable id);

}
