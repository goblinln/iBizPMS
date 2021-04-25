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

import cn.ibizlab.pms.core.zentao.domain.Block;
import cn.ibizlab.pms.core.zentao.filter.BlockSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Block] 服务对象接口
 */
public interface IBlockService extends IService<Block> {

    boolean create(Block et);
    void createBatch(List<Block> list);
    boolean update(Block et);
    boolean sysUpdate(Block et);
    void updateBatch(List<Block> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Block get(Long key);
    Block sysGet(Long key);
    Block getDraft(Block et);
    boolean checkKey(Block et);
    boolean save(Block et);
    void saveBatch(List<Block> list);
    List<Block> select(BlockSearchContext context);
    Page<Block> searchDefault(BlockSearchContext context);
    Block dynamicCall(Long key, String action, Block et);
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


