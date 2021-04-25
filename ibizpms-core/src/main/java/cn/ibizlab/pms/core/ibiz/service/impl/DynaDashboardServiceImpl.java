package cn.ibizlab.pms.core.ibiz.service.impl;

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
import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.core.ibiz.filter.DynaDashboardSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IDynaDashboardService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.DynaDashboardMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[动态数据看板] 服务对象接口实现
 */
@Slf4j
@Service("DynaDashboardServiceImpl")
public class DynaDashboardServiceImpl extends ServiceImpl<DynaDashboardMapper, DynaDashboard> implements IDynaDashboardService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.DynaDashboardRuntime dynadashboardRuntime;


    protected int batchSize = 500;

    @Override
    public List<DynaDashboard> select(DynaDashboardSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DynaDashboard> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(DynaDashboard et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynadashboardid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<DynaDashboard> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(DynaDashboard et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("dynadashboardid", et.getDynadashboardid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynadashboardid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<DynaDashboard> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(DynaDashboard et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("dynadashboardid", et.getDynadashboardid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynadashboardid()), et);
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
    public DynaDashboard get(String key) {
        DynaDashboard et = getById(key);
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
    public DynaDashboard sysGet(String key) {
        DynaDashboard et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public DynaDashboard getDraft(DynaDashboard et) {
        return et;
    }

    @Override
    public boolean checkKey(DynaDashboard et) {
        return (!ObjectUtils.isEmpty(et.getDynadashboardid())) && (!Objects.isNull(this.getById(et.getDynadashboardid())));
    }
    @Override
    @Transactional
    public boolean save(DynaDashboard et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(DynaDashboard et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<DynaDashboard> list) {
        List<DynaDashboard> create = new ArrayList<>();
        List<DynaDashboard> update = new ArrayList<>();
        for (DynaDashboard et : list) {
            if (ObjectUtils.isEmpty(et.getDynadashboardid()) || ObjectUtils.isEmpty(getById(et.getDynadashboardid()))) {
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
    public void saveBatch(List<DynaDashboard> list) {
        List<DynaDashboard> create = new ArrayList<>();
        List<DynaDashboard> update = new ArrayList<>();
        for (DynaDashboard et : list) {
            if (ObjectUtils.isEmpty(et.getDynadashboardid()) || ObjectUtils.isEmpty(getById(et.getDynadashboardid()))) {
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
     * 查询集合 DEFAULT
     */
    @Override
    public Page<DynaDashboard> searchDefault(DynaDashboardSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DynaDashboard> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<DynaDashboard>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<DynaDashboard> getDynadashboardByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<DynaDashboard> getDynadashboardByEntities(List<DynaDashboard> entities) {
        List ids =new ArrayList();
        for(DynaDashboard entity : entities){
            Serializable id=entity.getDynadashboardid();
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


    public IDynaDashboardService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public DynaDashboard dynamicCall(String key, String action, DynaDashboard et) {
        return et;
    }
}



