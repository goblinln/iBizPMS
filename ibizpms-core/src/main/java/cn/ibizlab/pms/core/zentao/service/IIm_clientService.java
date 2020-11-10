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

import cn.ibizlab.pms.core.zentao.domain.Im_client;
import cn.ibizlab.pms.core.zentao.filter.Im_clientSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Im_client] 服务对象接口
 */
public interface IIm_clientService extends IService<Im_client>{

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "im_client";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "im_clients";

    boolean create(Im_client et) ;
    void createBatch(List<Im_client> list) ;
    boolean update(Im_client et) ;
    void updateBatch(List<Im_client> list) ;
    boolean remove(Long key) ;
    void removeBatch(Collection<Long> idList) ;
    Im_client get(Long key) ;
    Im_client getDraft(Im_client et) ;
    boolean checkKey(Im_client et) ;
    boolean save(Im_client et) ;
    void saveBatch(List<Im_client> list) ;
    Page<Im_client> searchDefault(Im_clientSearchContext context) ;
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


