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

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[DocLibModule] 服务对象接口
 */
public interface IDocLibModuleService extends IService<DocLibModule> {

    boolean create(DocLibModule et);
    void createBatch(List<DocLibModule> list);
    boolean update(DocLibModule et);
    boolean sysUpdate(DocLibModule et);
    void updateBatch(List<DocLibModule> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    DocLibModule get(Long key);
    DocLibModule sysGet(Long key);
    DocLibModule getDraft(DocLibModule et);
    boolean checkKey(DocLibModule et);
    DocLibModule collect(DocLibModule et);
    boolean collectBatch(List<DocLibModule> etList);
    DocLibModule docLibModuleNFavorite(DocLibModule et);
    boolean docLibModuleNFavoriteBatch(List<DocLibModule> etList);
    DocLibModule doclibModuleFavorite(DocLibModule et);
    boolean doclibModuleFavoriteBatch(List<DocLibModule> etList);
    DocLibModule fix(DocLibModule et);
    boolean fixBatch(List<DocLibModule> etList);
    boolean save(DocLibModule et);
    void saveBatch(List<DocLibModule> list);
    DocLibModule unCollect(DocLibModule et);
    boolean unCollectBatch(List<DocLibModule> etList);
    Page<DocLibModule> searchAllDocLibModule_Custom(DocLibModuleSearchContext context);
    Page<DocLibModule> searchAllDoclibModule(DocLibModuleSearchContext context);
    Page<DocLibModule> searchChildModuleByParent(DocLibModuleSearchContext context);
    Page<DocLibModule> searchChildModuleByRealParent(DocLibModuleSearchContext context);
    Page<DocLibModule> searchDefault(DocLibModuleSearchContext context);
    Page<DocLibModule> searchMyFavourites(DocLibModuleSearchContext context);
    Page<DocLibModule> searchParentModule(DocLibModuleSearchContext context);
    Page<DocLibModule> searchRootModuleMuLu(DocLibModuleSearchContext context);
    Page<DocLibModule> searchRootModuleMuLuByRoot(DocLibModuleSearchContext context);
    Page<DocLibModule> searchRootModuleMuLuBysrfparentkey(DocLibModuleSearchContext context);
    List<DocLibModule> selectByParent(Long id);
    void removeByParent(Long id);
    List<DocLibModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    DocLibModule dynamicCall(Long key, String action, DocLibModule et);
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

}


