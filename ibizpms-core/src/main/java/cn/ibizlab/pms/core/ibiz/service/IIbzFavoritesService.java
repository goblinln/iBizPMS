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

import cn.ibizlab.pms.core.ibiz.domain.IbzFavorites;
import cn.ibizlab.pms.core.ibiz.filter.IbzFavoritesSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzFavorites] 服务对象接口
 */
public interface IIbzFavoritesService extends IService<IbzFavorites> {

    boolean create(IbzFavorites et);
    boolean update(IbzFavorites et);
    boolean sysUpdate(IbzFavorites et);
    boolean remove(String key);
    IbzFavorites get(String key);
    IbzFavorites sysGet(String key);
    IbzFavorites getDraft(IbzFavorites et);
    boolean checkKey(IbzFavorites et);
    boolean save(IbzFavorites et);
    List<IbzFavorites> select(IbzFavoritesSearchContext context);
    List<IbzFavorites> selectDefault(IbzFavoritesSearchContext context);
    List<IbzFavorites> selectView(IbzFavoritesSearchContext context);

    Page<IbzFavorites> searchDefault(IbzFavoritesSearchContext context);
    IbzFavorites dynamicCall(String key, String action, IbzFavorites et);
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

    List<IbzFavorites> getIbzfavoritesByIds(List<String> ids);
    List<IbzFavorites> getIbzfavoritesByEntities(List<IbzFavorites> entities);
}


