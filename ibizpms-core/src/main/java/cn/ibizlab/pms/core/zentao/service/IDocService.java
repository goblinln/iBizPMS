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

import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Doc] 服务对象接口
 */
public interface IDocService extends IService<Doc> {

    boolean create(Doc et);
    void createBatch(List<Doc> list);
    boolean update(Doc et);
    boolean sysUpdate(Doc et);
    void updateBatch(List<Doc> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Doc get(Long key);
    Doc sysGet(Long key);
    Doc getDraft(Doc et);
    Doc byVersionUpdateContext(Doc et);
    boolean byVersionUpdateContextBatch(List<Doc> etList);
    boolean checkKey(Doc et);
    Doc collect(Doc et);
    boolean collectBatch(List<Doc> etList);
    Doc getDocStatus(Doc et);
    boolean getDocStatusBatch(List<Doc> etList);
    Doc onlyCollectDoc(Doc et);
    boolean onlyCollectDocBatch(List<Doc> etList);
    Doc onlyUnCollectDoc(Doc et);
    boolean onlyUnCollectDocBatch(List<Doc> etList);
    boolean save(Doc et);
    void saveBatch(List<Doc> list);
    Doc unCollect(Doc et);
    boolean unCollectBatch(List<Doc> etList);
    List<Doc> select(DocSearchContext context);
    
    List<Doc> selectQueryByChildDocLibDoc(DocSearchContext context);
    List<Doc> selectQueryByDefault(DocSearchContext context);
    List<Doc> selectQueryByDocLibAndDoc(DocSearchContext context);
    List<Doc> selectQueryByDocLibDoc(DocSearchContext context);
    List<Doc> selectQueryByDocModuleDoc(DocSearchContext context);
    List<Doc> selectQueryByDocStatus(DocSearchContext context);
    List<Doc> selectQueryByModuleDocChild(DocSearchContext context);
    List<Doc> selectQueryByMyFavourite(DocSearchContext context);
    List<Doc> selectQueryByMyFavouritesOnlyDoc(DocSearchContext context);
    List<Doc> selectQueryByNotRootDoc(DocSearchContext context);
    List<Doc> selectQueryByRootDoc(DocSearchContext context);
    List<Doc> selectQueryByView(DocSearchContext context);

    Page<Doc> searchChildDocLibDoc(DocSearchContext context);
    Page<Doc> searchDefault(DocSearchContext context);
    Page<Doc> searchDocLibAndDoc(DocSearchContext context);
    Page<Doc> searchDocLibDoc(DocSearchContext context);
    Page<Doc> searchDocModuleDoc(DocSearchContext context);
    Page<Doc> searchDocStatus(DocSearchContext context);
    Page<Doc> searchModuleDocChild(DocSearchContext context);
    Page<Doc> searchMyFavourite(DocSearchContext context);
    Page<Doc> searchMyFavouritesOnlyDoc(DocSearchContext context);
    Page<Doc> searchNotRootDoc(DocSearchContext context);
    Page<Doc> searchRootDoc(DocSearchContext context);
    List<Doc> selectByLib(Long id);
    void removeByLib(Long id);
    List<Doc> selectByModule(Long id);
    void removeByModule(Long id);
    List<Doc> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<Doc> selectByProject(Long id);
    void removeByProject(Long id);
    Doc dynamicCall(Long key, String action, Doc et);
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

    List<Doc> getDocByIds(List<Long> ids);
    List<Doc> getDocByEntities(List<Doc> entities);
}


