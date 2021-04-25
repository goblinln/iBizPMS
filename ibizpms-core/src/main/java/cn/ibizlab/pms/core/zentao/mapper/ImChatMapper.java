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
import cn.ibizlab.pms.core.zentao.domain.ImChat;
import cn.ibizlab.pms.core.zentao.filter.ImChatSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ImChatMapper extends BaseMapper<ImChat> {

    List<ImChat> selectQueryByDefault(@Param("srf") ImChatSearchContext context, @Param("ew") Wrapper<ImChat> wrapper);
    List<ImChat> selectQueryByView(@Param("srf") ImChatSearchContext context, @Param("ew") Wrapper<ImChat> wrapper);

    Page<ImChat> searchDefault(IPage page, @Param("srf") ImChatSearchContext context, @Param("ew") Wrapper<ImChat> wrapper);
    @Override
    ImChat selectById(Serializable id);
    @Override
    int insert(ImChat entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ImChat entity);
    @Override
    int update(@Param(Constants.ENTITY) ImChat entity, @Param("ew") Wrapper<ImChat> updateWrapper);
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
