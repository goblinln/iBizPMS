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

import cn.ibizlab.pms.core.zentao.domain.StoryStage;
import cn.ibizlab.pms.core.zentao.filter.StoryStageSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[StoryStage] 服务对象接口
 */
public interface IStoryStageService extends IService<StoryStage> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "需求阶段";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "storystages";

    boolean create(StoryStage et);
    void createBatch(List<StoryStage> list);
    boolean update(StoryStage et);
    void updateBatch(List<StoryStage> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    StoryStage get(String key);
    StoryStage getDraft(StoryStage et);
    boolean checkKey(StoryStage et);
    boolean save(StoryStage et);
    void saveBatch(List<StoryStage> list);
    Page<StoryStage> searchDefault(StoryStageSearchContext context);
    List<StoryStage> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<StoryStage> selectByStory(Long id);
    void removeByStory(Long id);
    StoryStage dynamicCall(String key, String action, StoryStage et);
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

    List<StoryStage> getStorystageByIds(List<String> ids);
    List<StoryStage> getStorystageByEntities(List<StoryStage> entities);
}


