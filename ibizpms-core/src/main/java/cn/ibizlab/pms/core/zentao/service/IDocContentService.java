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

import cn.ibizlab.pms.core.zentao.domain.DocContent;
import cn.ibizlab.pms.core.zentao.filter.DocContentSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[DocContent] 服务对象接口
 */
public interface IDocContentService extends IService<DocContent> {

    boolean create(DocContent et);
    void createBatch(List<DocContent> list);
    boolean update(DocContent et);
    boolean sysUpdate(DocContent et);
    void updateBatch(List<DocContent> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    DocContent get(Long key);
    DocContent sysGet(Long key);
    DocContent getDraft(DocContent et);
    boolean checkKey(DocContent et);
    boolean save(DocContent et);
    void saveBatch(List<DocContent> list);
    List<DocContent> select(DocContentSearchContext context);
    List<DocContent> selectCurVersion(DocContentSearchContext context);
    List<DocContent> selectDefault(DocContentSearchContext context);
    List<DocContent> selectView(DocContentSearchContext context);

    Page<DocContent> searchCurVersion(DocContentSearchContext context);
    Page<DocContent> searchDefault(DocContentSearchContext context);
    List<DocContent> selectByDoc(Long id);
    void removeByDoc(Long id);
    void saveByDoc(Long id, List<DocContent> list) ;
    DocContent dynamicCall(Long key, String action, DocContent et);
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

    List<DocContent> getDoccontentByIds(List<Long> ids);
    List<DocContent> getDoccontentByEntities(List<DocContent> entities);
}


