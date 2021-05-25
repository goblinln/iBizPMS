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

import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTestReportAction] 服务对象接口
 */
public interface IIBZTestReportActionService extends IService<IBZTestReportAction> {

    boolean create(IBZTestReportAction et);
    void createBatch(List<IBZTestReportAction> list);
    boolean update(IBZTestReportAction et);
    boolean sysUpdate(IBZTestReportAction et);
    void updateBatch(List<IBZTestReportAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZTestReportAction get(Long key);
    IBZTestReportAction sysGet(Long key);
    IBZTestReportAction getDraft(IBZTestReportAction et);
    boolean checkKey(IBZTestReportAction et);
    IBZTestReportAction comment(IBZTestReportAction et);
    boolean commentBatch(List<IBZTestReportAction> etList);
    IBZTestReportAction createHis(IBZTestReportAction et);
    boolean createHisBatch(List<IBZTestReportAction> etList);
    IBZTestReportAction editComment(IBZTestReportAction et);
    boolean editCommentBatch(List<IBZTestReportAction> etList);
    IBZTestReportAction managePmsEe(IBZTestReportAction et);
    boolean managePmsEeBatch(List<IBZTestReportAction> etList);
    boolean save(IBZTestReportAction et);
    void saveBatch(List<IBZTestReportAction> list);
    IBZTestReportAction sendMarkDone(IBZTestReportAction et);
    boolean sendMarkDoneBatch(List<IBZTestReportAction> etList);
    IBZTestReportAction sendTodo(IBZTestReportAction et);
    boolean sendTodoBatch(List<IBZTestReportAction> etList);
    IBZTestReportAction sendToread(IBZTestReportAction et);
    boolean sendToreadBatch(List<IBZTestReportAction> etList);
    List<IBZTestReportAction> select(IBZTestReportActionSearchContext context);
    List<IBZTestReportAction> selectDefault(IBZTestReportActionSearchContext context);
    List<IBZTestReportAction> selectSimple(IBZTestReportActionSearchContext context);
    List<IBZTestReportAction> selectType(IBZTestReportActionSearchContext context);
    List<IBZTestReportAction> selectView(IBZTestReportActionSearchContext context);

    Page<IBZTestReportAction> searchDefault(IBZTestReportActionSearchContext context);
    Page<IBZTestReportAction> searchType(IBZTestReportActionSearchContext context);
    List<IBZTestReportAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZTestReportAction dynamicCall(Long key, String action, IBZTestReportAction et);
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

    List<IBZTestReportAction> getIbztestreportactionByIds(List<Long> ids);
    List<IBZTestReportAction> getIbztestreportactionByEntities(List<IBZTestReportAction> entities);
}


