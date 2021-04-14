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

import cn.ibizlab.pms.core.zentao.domain.StorySpec;
import cn.ibizlab.pms.core.zentao.filter.StorySpecSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[StorySpec] 服务对象接口
 */
public interface IStorySpecService extends IService<StorySpec> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "需求描述";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "storyspecs";

    boolean create(StorySpec et);
    void createBatch(List<StorySpec> list);
    boolean update(StorySpec et);
    void updateBatch(List<StorySpec> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    StorySpec get(String key);
    StorySpec getDraft(StorySpec et);
    boolean checkKey(StorySpec et);
    boolean save(StorySpec et);
    void saveBatch(List<StorySpec> list);
    Page<StorySpec> searchDefault(StorySpecSearchContext context);
    Page<StorySpec> searchVersion(StorySpecSearchContext context);
    List<StorySpec> selectByStory(Long id);
    void removeByStory(Long id);
    StorySpec dynamicCall(String key, String action, StorySpec et);
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

    List<StorySpec> getStoryspecByIds(List<String> ids);
    List<StorySpec> getStoryspecByEntities(List<StorySpec> entities);
}


