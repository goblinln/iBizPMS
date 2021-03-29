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

import cn.ibizlab.pms.core.zentao.domain.UserView;
import cn.ibizlab.pms.core.zentao.filter.UserViewSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[UserView] 服务对象接口
 */
public interface IUserViewService extends IService<UserView> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "用户视图";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "userviews";

    boolean create(UserView et);
    void createBatch(List<UserView> list);
    boolean update(UserView et);
    void updateBatch(List<UserView> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    UserView get(String key);
    UserView getDraft(UserView et);
    boolean checkKey(UserView et);
    boolean save(UserView et);
    void saveBatch(List<UserView> list);
    Page<UserView> searchDefault(UserViewSearchContext context);
    UserView dynamicCall(String key, String action, UserView et);
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


