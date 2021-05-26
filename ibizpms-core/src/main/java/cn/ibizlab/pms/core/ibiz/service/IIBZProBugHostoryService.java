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

import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProBugHostorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProBugHostory] 服务对象接口
 */
public interface IIBZProBugHostoryService extends IService<IBZProBugHostory> {

    boolean create(IBZProBugHostory et);
    boolean update(IBZProBugHostory et);
    boolean sysUpdate(IBZProBugHostory et);
    boolean remove(Long key);
    IBZProBugHostory get(Long key);
    IBZProBugHostory sysGet(Long key);
    IBZProBugHostory getDraft(IBZProBugHostory et);
    boolean checkKey(IBZProBugHostory et);
    boolean save(IBZProBugHostory et);
    List<IBZProBugHostory> select(IBZProBugHostorySearchContext context);
    List<IBZProBugHostory> selectDefault(IBZProBugHostorySearchContext context);
    List<IBZProBugHostory> selectSimple(IBZProBugHostorySearchContext context);
    List<IBZProBugHostory> selectView(IBZProBugHostorySearchContext context);

    Page<IBZProBugHostory> searchDefault(IBZProBugHostorySearchContext context);
    List<IBZProBugHostory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProBugHostory> list) ;
    IBZProBugHostory dynamicCall(Long key, String action, IBZProBugHostory et);
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

    List<IBZProBugHostory> getIbzprobughostoryByIds(List<Long> ids);
    List<IBZProBugHostory> getIbzprobughostoryByEntities(List<IBZProBugHostory> entities);
}


