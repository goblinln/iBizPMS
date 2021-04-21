package cn.ibizlab.pms.core.ibizpro.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproConfigSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbzproConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[系统配置表] 服务对象接口实现
 */
@Slf4j
@Service("IbzproConfigServiceImpl")
public class IbzproConfigServiceImpl extends ServiceImpl<IbzproConfigMapper, IbzproConfig> implements IIbzproConfigService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IbzproConfigRuntime ibzproconfigRuntime;


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzproConfig et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzproconfigid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzproConfig> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzproConfig et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_configid", et.getIbzproconfigid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzproconfigid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzproConfig> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzproConfig et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_configid", et.getIbzproconfigid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzproconfigid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbzproConfig get(String key) {
        IbzproConfig et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IbzproConfig sysGet(String key) {
        IbzproConfig et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IbzproConfig getDraft(IbzproConfig et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzproConfig et) {
        return (!ObjectUtils.isEmpty(et.getIbzproconfigid())) && (!Objects.isNull(this.getById(et.getIbzproconfigid())));
    }
    @Override
    @Transactional
    public IbzproConfig getSystemConfig(IbzproConfig et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getSystemConfigBatch(List<IbzproConfig> etList) {
        for(IbzproConfig et : etList) {
            getSystemConfig(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IbzproConfig et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzproConfig et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzproConfig> list) {
        List<IbzproConfig> create = new ArrayList<>();
        List<IbzproConfig> update = new ArrayList<>();
        for (IbzproConfig et : list) {
            if (ObjectUtils.isEmpty(et.getIbzproconfigid()) || ObjectUtils.isEmpty(getById(et.getIbzproconfigid()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<IbzproConfig> list) {
        List<IbzproConfig> create = new ArrayList<>();
        List<IbzproConfig> update = new ArrayList<>();
        for (IbzproConfig et : list) {
            if (ObjectUtils.isEmpty(et.getIbzproconfigid()) || ObjectUtils.isEmpty(getById(et.getIbzproconfigid()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzproConfig> searchDefault(IbzproConfigSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproConfig> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproConfig>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }







    @Override
    public List<JSONObject> select(String sql, Map param){
        return this.baseMapper.selectBySQL(sql,param);
    }

    @Override
    @Transactional
    public boolean execute(String sql , Map param){
        if (sql == null || sql.isEmpty()) {
            return false;
        }
        if (sql.toLowerCase().trim().startsWith("insert")) {
            return this.baseMapper.insertBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("update")) {
            return this.baseMapper.updateBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("delete")) {
            return this.baseMapper.deleteBySQL(sql,param);
        }
        log.warn("暂未支持的SQL语法");
        return true;
    }

    @Override
    public List<IbzproConfig> getIbzproconfigByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzproConfig> getIbzproconfigByEntities(List<IbzproConfig> entities) {
        List ids =new ArrayList();
        for(IbzproConfig entity : entities){
            Serializable id=entity.getIbzproconfigid();
            if(!ObjectUtils.isEmpty(id)){
                ids.add(id);
            }
        }
        if(ids.size()>0) {
            return this.listByIds(ids);
        }
        else {
            return entities;
        }
    }


    public IIbzproConfigService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzproConfig dynamicCall(String key, String action, IbzproConfig et) {
        return et;
    }
}



