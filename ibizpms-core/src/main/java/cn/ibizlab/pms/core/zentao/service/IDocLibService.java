package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[DocLib] 服务对象接口
 */
public interface IDocLibService extends IService<DocLib> {

    boolean create(DocLib et);
    void createBatch(List<DocLib> list);
    boolean update(DocLib et);
    boolean sysUpdate(DocLib et);
    void updateBatch(List<DocLib> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    DocLib get(Long key);
    DocLib sysGet(Long key);
    DocLib getDraft(DocLib et);
    boolean checkKey(DocLib et);
    DocLib collect(DocLib et);
    boolean collectBatch(List<DocLib> etList);
    boolean save(DocLib et);
    void saveBatch(List<DocLib> list);
    DocLib unCollect(DocLib et);
    boolean unCollectBatch(List<DocLib> etList);
    List<DocLib> select(DocLibSearchContext context);
    
    List<DocLib> selectQueryByByCustom(DocLibSearchContext context);
    List<DocLib> selectQueryByByProduct(DocLibSearchContext context);
    List<DocLib> selectQueryByByProductNotFiles(DocLibSearchContext context);
    List<DocLib> selectQueryByByProject(DocLibSearchContext context);
    List<DocLib> selectQueryByByProjectNotFiles(DocLibSearchContext context);
    List<DocLib> selectQueryByCurDocLib(DocLibSearchContext context);
    List<DocLib> selectQueryByDefault(DocLibSearchContext context);
    List<DocLib> selectQueryByMyFavourites(DocLibSearchContext context);
    List<DocLib> selectQueryByRootModuleMuLu(DocLibSearchContext context);
    List<DocLib> selectQueryByView(DocLibSearchContext context);

    Page<DocLib> searchByCustom(DocLibSearchContext context);
    Page<DocLib> searchByProduct(DocLibSearchContext context);
    Page<DocLib> searchByProductNotFiles(DocLibSearchContext context);
    Page<DocLib> searchByProject(DocLibSearchContext context);
    Page<DocLib> searchByProjectNotFiles(DocLibSearchContext context);
    Page<DocLib> searchCurDocLib(DocLibSearchContext context);
    Page<DocLib> searchDefault(DocLibSearchContext context);
    Page<DocLib> searchMyFavourites(DocLibSearchContext context);
    Page<DocLib> searchRootModuleMuLu(DocLibSearchContext context);
    List<DocLib> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<DocLib> selectByProject(Long id);
    void removeByProject(Long id);
    DocLib dynamicCall(Long key, String action, DocLib et);
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

    List<DocLib> getDoclibByIds(List<Long> ids);
    List<DocLib> getDoclibByEntities(List<DocLib> entities);
}


