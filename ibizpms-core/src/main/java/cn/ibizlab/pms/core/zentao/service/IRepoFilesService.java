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

import cn.ibizlab.pms.core.zentao.domain.RepoFiles;
import cn.ibizlab.pms.core.zentao.filter.RepoFilesSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[RepoFiles] 服务对象接口
 */
public interface IRepoFilesService extends IService<RepoFiles> {

    boolean create(RepoFiles et);
    void createBatch(List<RepoFiles> list);
    boolean update(RepoFiles et);
    boolean sysUpdate(RepoFiles et);
    void updateBatch(List<RepoFiles> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    RepoFiles get(Long key);
    RepoFiles sysGet(Long key);
    RepoFiles getDraft(RepoFiles et);
    boolean checkKey(RepoFiles et);
    boolean save(RepoFiles et);
    void saveBatch(List<RepoFiles> list);
    List<RepoFiles> select(RepoFilesSearchContext context);
    
    List<RepoFiles> selectQueryByDefault(RepoFilesSearchContext context);
    List<RepoFiles> selectQueryByView(RepoFilesSearchContext context);

    Page<RepoFiles> searchDefault(RepoFilesSearchContext context);
    List<RepoFiles> selectByParent(Long id);
    void removeByParent(Long id);
    RepoFiles dynamicCall(Long key, String action, RepoFiles et);
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


