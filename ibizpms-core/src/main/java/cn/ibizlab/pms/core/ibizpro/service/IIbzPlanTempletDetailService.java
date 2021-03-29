package cn.ibizlab.pms.core.ibizpro.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletDetailSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzPlanTempletDetail] 服务对象接口
 */
public interface IIbzPlanTempletDetailService extends IService<IbzPlanTempletDetail> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "计划模板详情";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "ibzplantempletdetails";

    boolean create(IbzPlanTempletDetail et);
    void createBatch(List<IbzPlanTempletDetail> list);
    boolean update(IbzPlanTempletDetail et);
    void updateBatch(List<IbzPlanTempletDetail> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbzPlanTempletDetail get(String key);
    IbzPlanTempletDetail getDraft(IbzPlanTempletDetail et);
    boolean checkKey(IbzPlanTempletDetail et);
    boolean save(IbzPlanTempletDetail et);
    void saveBatch(List<IbzPlanTempletDetail> list);
    Page<IbzPlanTempletDetail> searchDefault(IbzPlanTempletDetailSearchContext context);
    List<IbzPlanTempletDetail> selectByPlantempletid(String ibzplantempletid);
    void removeByPlantempletid(String ibzplantempletid);
    void saveByPlantempletid(String ibzplantempletid, List<IbzPlanTempletDetail> list) ;
    IbzPlanTempletDetail dynamicCall(String key, String action, IbzPlanTempletDetail et);
    /**
     *自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     *自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<IbzPlanTempletDetail> getIbzplantempletdetailByIds(List<String> ids);
    List<IbzPlanTempletDetail> getIbzplantempletdetailByEntities(List<IbzPlanTempletDetail> entities);
}


