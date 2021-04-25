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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletDetailSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface IbzPlanTempletDetailMapper extends BaseMapper<IbzPlanTempletDetail> {

    List<IbzPlanTempletDetail> selectQueryByDefault(@Param("srf") IbzPlanTempletDetailSearchContext context, @Param("ew") Wrapper<IbzPlanTempletDetail> wrapper);
    List<IbzPlanTempletDetail> selectQueryByView(@Param("srf") IbzPlanTempletDetailSearchContext context, @Param("ew") Wrapper<IbzPlanTempletDetail> wrapper);

    Page<IbzPlanTempletDetail> searchDefault(IPage page, @Param("srf") IbzPlanTempletDetailSearchContext context, @Param("ew") Wrapper<IbzPlanTempletDetail> wrapper);
    @Override
    IbzPlanTempletDetail selectById(Serializable id);
    @Override
    int insert(IbzPlanTempletDetail entity);
    @Override
    int updateById(@Param(Constants.ENTITY) IbzPlanTempletDetail entity);
    @Override
    int update(@Param(Constants.ENTITY) IbzPlanTempletDetail entity, @Param("ew") Wrapper<IbzPlanTempletDetail> updateWrapper);
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

    List<IbzPlanTempletDetail> selectByPlantempletid(@Param("ibzplantempletid") Serializable ibzplantempletid);

}
