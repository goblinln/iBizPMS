package cn.ibizlab.pms.core.report.service;

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

import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzDaily] 服务对象接口
 */
public interface IIbzDailyService extends IService<IbzDaily> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "日报";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "ibzdailies";

    boolean create(IbzDaily et);
    void createBatch(List<IbzDaily> list);
    boolean update(IbzDaily et);
    void updateBatch(List<IbzDaily> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzDaily get(Long key);
    IbzDaily getDraft(IbzDaily et);
    boolean checkKey(IbzDaily et);
    IbzDaily createUserDaily(IbzDaily et);
    boolean createUserDailyBatch(List<IbzDaily> etList);
    IbzDaily getYeaterdayDailyPlansTaskEdit(IbzDaily et);
    boolean getYeaterdayDailyPlansTaskEditBatch(List<IbzDaily> etList);
    IbzDaily getYesterdayDailyPlansTask(IbzDaily et);
    boolean getYesterdayDailyPlansTaskBatch(List<IbzDaily> etList);
    IbzDaily haveRead(IbzDaily et);
    boolean haveReadBatch(List<IbzDaily> etList);
    IbzDaily linkCompleteTask(IbzDaily et);
    boolean linkCompleteTaskBatch(List<IbzDaily> etList);
    IbzDaily pushUserDaily(IbzDaily et);
    boolean pushUserDailyBatch(List<IbzDaily> etList);
    boolean save(IbzDaily et);
    void saveBatch(List<IbzDaily> list);
    IbzDaily submit(IbzDaily et);
    boolean submitBatch(List<IbzDaily> etList);
    Page<IbzDaily> searchDefault(IbzDailySearchContext context);
    Page<IbzDaily> searchMyAllDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchMyDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchMyNotSubmit(IbzDailySearchContext context);
    Page<IbzDaily> searchMySubmitDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchProductDaily(IbzDailySearchContext context);
    Page<IbzDaily> searchProjectDaily(IbzDailySearchContext context);
    IbzDaily dynamicCall(Long key, String action, IbzDaily et);
    /**
     * 自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     * 自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<IbzDaily> getIbzdailyByIds(List<Long> ids);
    List<IbzDaily> getIbzdailyByEntities(List<IbzDaily> entities);
}


