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

import cn.ibizlab.pms.core.ibizpro.domain.IBZProTranslator;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProTranslatorSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProTranslator] 服务对象接口
 */
public interface IIBZProTranslatorService extends IService<IBZProTranslator> {

    boolean create(IBZProTranslator et);
    void createBatch(List<IBZProTranslator> list);
    boolean update(IBZProTranslator et);
    boolean sysUpdate(IBZProTranslator et);
    void updateBatch(List<IBZProTranslator> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBZProTranslator get(String key);
    IBZProTranslator sysGet(String key);
    IBZProTranslator getDraft(IBZProTranslator et);
    boolean checkKey(IBZProTranslator et);
    boolean save(IBZProTranslator et);
    void saveBatch(List<IBZProTranslator> list);
    Page<IBZProTranslator> searchDefault(IBZProTranslatorSearchContext context);
    IBZProTranslator dynamicCall(String key, String action, IBZProTranslator et);
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

    List<IBZProTranslator> getIbzprotranslatorByIds(List<String> ids);
    List<IBZProTranslator> getIbzprotranslatorByEntities(List<IBZProTranslator> entities);
}


