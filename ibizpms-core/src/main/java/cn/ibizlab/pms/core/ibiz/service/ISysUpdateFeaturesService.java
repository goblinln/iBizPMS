package cn.ibizlab.pms.core.ibiz.service;

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

import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[SysUpdateFeatures] 服务对象接口
 */
public interface ISysUpdateFeaturesService extends IService<SysUpdateFeatures> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统更新功能";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "sysupdatefeatures";

    boolean create(SysUpdateFeatures et);
    void createBatch(List<SysUpdateFeatures> list);
    boolean update(SysUpdateFeatures et);
    void updateBatch(List<SysUpdateFeatures> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysUpdateFeatures get(String key);
    SysUpdateFeatures getDraft(SysUpdateFeatures et);
    boolean checkKey(SysUpdateFeatures et);
    boolean save(SysUpdateFeatures et);
    void saveBatch(List<SysUpdateFeatures> list);
    Page<SysUpdateFeatures> searchDefault(SysUpdateFeaturesSearchContext context);
    List<SysUpdateFeatures> selectBySysupdatelogid(String sysupdatelogid);
    void removeBySysupdatelogid(Collection<String> ids);
    void removeBySysupdatelogid(String sysupdatelogid);
    SysUpdateFeatures dynamicCall(String key, String action, SysUpdateFeatures et);
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

    List<SysUpdateFeatures> getSysupdatefeaturesByIds(List<String> ids);
    List<SysUpdateFeatures> getSysupdatefeaturesByEntities(List<SysUpdateFeatures> entities);
}


