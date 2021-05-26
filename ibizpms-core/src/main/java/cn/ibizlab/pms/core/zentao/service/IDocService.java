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
    boolean checkKey(Doc et);
    Doc collect(Doc et);
    Doc getDocStatus(Doc et);
    Doc onlyCollectDoc(Doc et);
    Doc onlyUnCollectDoc(Doc et);
    boolean save(Doc et);
    void saveBatch(List<Doc> list);
    Doc unCollect(Doc et);
    List<Doc> select(DocSearchContext context);
    List<Doc> selectChildDocLibDoc(DocSearchContext context);
    List<Doc> selectDefault(DocSearchContext context);
    List<Doc> selectDocLibAndDoc(DocSearchContext context);
    List<Doc> selectDocLibDoc(DocSearchContext context);
    List<Doc> selectDocModuleDoc(DocSearchContext context);
    List<Doc> selectDocStatus(DocSearchContext context);
    List<Doc> selectModuleDocChild(DocSearchContext context);
    List<Doc> selectMyFavourite(DocSearchContext context);
    List<Doc> selectMyFavouritesOnlyDoc(DocSearchContext context);
    List<Doc> selectNotRootDoc(DocSearchContext context);
    List<Doc> selectRootDoc(DocSearchContext context);
    List<Doc> selectView(DocSearchContext context);

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


