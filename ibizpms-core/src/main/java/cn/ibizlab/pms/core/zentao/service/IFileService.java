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

import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[File] 服务对象接口
 */
public interface IFileService extends IService<File> {

    boolean create(File et);
    void createBatch(List<File> list);
    boolean update(File et);
    boolean sysUpdate(File et);
    void updateBatch(List<File> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    File get(Long key);
    File sysGet(Long key);
    File getDraft(File et);
    boolean checkKey(File et);
    boolean save(File et);
    void saveBatch(List<File> list);
    File updateObjectIDForPmsEe(File et);
    boolean updateObjectIDForPmsEeBatch(List<File> etList);
    List<File> select(FileSearchContext context);
    
    List<File> selectQueryByDefault(FileSearchContext context);
    List<File> selectQueryByDocLibFile(FileSearchContext context);
    List<File> selectQueryByProductDocLibFile(FileSearchContext context);
    List<File> selectQueryByType(FileSearchContext context);
    List<File> selectQueryByTypeNotBySrfparentkey(FileSearchContext context);
    List<File> selectQueryByView(FileSearchContext context);

    Page<File> searchDefault(FileSearchContext context);
    Page<File> searchDocLibFile(FileSearchContext context);
    Page<File> searchProductDocLibFile(FileSearchContext context);
    Page<File> searchType(FileSearchContext context);
    Page<File> searchTypeNotBySrfparentkey(FileSearchContext context);
    File dynamicCall(Long key, String action, File et);
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

    List<File> getFileByIds(List<Long> ids);
    List<File> getFileByEntities(List<File> entities);
}


