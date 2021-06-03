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
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface DocMapper extends BaseMapper<Doc> {

    List<Doc> selectChildDocLibDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectDefault(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectDocLibAndDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectDocLibDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectDocModuleDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectDocStatus(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectLastedModify(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectModuleDocChild(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectMyCreateOrUpdateDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectMyFavourite(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectMyFavouritesOnlyDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectNotRootDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectRootDoc(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    List<Doc> selectView(@Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);

    Page<Doc> searchChildDocLibDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchDefault(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchDocLibAndDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchDocLibDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchDocModuleDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchDocStatus(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchLastedModify(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchModuleDocChild(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchMyCreateOrUpdateDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchMyFavourite(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchMyFavouritesOnlyDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchNotRootDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    Page<Doc> searchRootDoc(IPage page, @Param("srf") DocSearchContext context, @Param("ew") Wrapper<Doc> wrapper);
    @Override
    Doc selectById(Serializable id);
    @Override
    int insert(Doc entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Doc entity);
    @Override
    int update(@Param(Constants.ENTITY) Doc entity, @Param("ew") Wrapper<Doc> updateWrapper);
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

    List<Doc> selectByLib(@Param("id") Serializable id);

    List<Doc> selectByModule(@Param("id") Serializable id);

    List<Doc> selectByProduct(@Param("id") Serializable id);

    List<Doc> selectByProject(@Param("id") Serializable id);

}
