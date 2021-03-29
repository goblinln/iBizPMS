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

import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.filter.IbzReportRoleConfigSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzReportRoleConfig] 服务对象接口
 */
public interface IIbzReportRoleConfigService extends IService<IbzReportRoleConfig> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "汇报角色配置";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "ibzreportroleconfigs";

    boolean create(IbzReportRoleConfig et);
    void createBatch(List<IbzReportRoleConfig> list);
    boolean update(IbzReportRoleConfig et);
    void updateBatch(List<IbzReportRoleConfig> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbzReportRoleConfig get(String key);
    IbzReportRoleConfig getDraft(IbzReportRoleConfig et);
    boolean checkKey(IbzReportRoleConfig et);
    boolean save(IbzReportRoleConfig et);
    void saveBatch(List<IbzReportRoleConfig> list);
    Page<IbzReportRoleConfig> searchDefault(IbzReportRoleConfigSearchContext context);
    IbzReportRoleConfig dynamicCall(String key, String action, IbzReportRoleConfig et);
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

    List<IbzReportRoleConfig> getIbzreportroleconfigByIds(List<String> ids);
    List<IbzReportRoleConfig> getIbzreportroleconfigByEntities(List<IbzReportRoleConfig> entities);
}


