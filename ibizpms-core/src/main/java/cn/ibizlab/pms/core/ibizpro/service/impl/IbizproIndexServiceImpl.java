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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproIndexService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproIndexMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[索引检索] 服务对象接口实现
 */
@Slf4j
@Service("IbizproIndexServiceImpl")
public class IbizproIndexServiceImpl extends ServiceImpl<IbizproIndexMapper, IbizproIndex> implements IIbizproIndexService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IbizproIndexRuntime ibizproindexRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbizproIndex> select(IbizproIndexSearchContext context) {
        return null;
    }

    @Override
    public List<IbizproIndex> selectQueryByDefault(IbizproIndexSearchContext context) {
        return null;
    }

    @Override
    public List<IbizproIndex> selectQueryByView(IbizproIndexSearchContext context) {
        return null;
    }

    @Override
    @Transactional
    public boolean create(IbizproIndex et) {
//        if(!this.retBool(this.baseMapper.insert(et))) {
//            return false;
//        }
//        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproIndex> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbizproIndex et) {
//        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("indexid", et.getIndexid()))) {
//            return false;
//        }
//        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproIndex> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbizproIndex et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("indexid", et.getIndexid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
//        boolean result = removeById(key);
        return true ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbizproIndex get(Long key) {
        IbizproIndex et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
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
    public IbizproIndex sysGet(Long key) {
        IbizproIndex et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbizproIndex getDraft(IbizproIndex et) {
        return et;
    }

    @Override
    public boolean checkKey(IbizproIndex et) {
        return (!ObjectUtils.isEmpty(et.getIndexid())) && (!Objects.isNull(this.getById(et.getIndexid())));
    }
    @Override
    @Transactional
    public boolean save(IbizproIndex et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproIndex et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproIndex> list) {
        List<IbizproIndex> create = new ArrayList<>();
        List<IbizproIndex> update = new ArrayList<>();
        for (IbizproIndex et : list) {
            if (ObjectUtils.isEmpty(et.getIndexid()) || ObjectUtils.isEmpty(getById(et.getIndexid()))) {
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
    public void saveBatch(List<IbizproIndex> list) {
        List<IbizproIndex> create = new ArrayList<>();
        List<IbizproIndex> update = new ArrayList<>();
        for (IbizproIndex et : list) {
            if (ObjectUtils.isEmpty(et.getIndexid()) || ObjectUtils.isEmpty(getById(et.getIndexid()))) {
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
    public Page<IbizproIndex> searchDefault(IbizproIndexSearchContext context) {
//        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproIndex> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproIndex>(new ArrayList<>(), context.getPageable(), 10);
    }

    /**
     * 查询集合 全文检索
     */
    @Override
    public Page<IbizproIndex> searchESquery(IbizproIndexSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproIndex> pages=baseMapper.searchESquery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproIndex>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集2
     */
    @Override
    public Page<IbizproIndex> searchIndexDER(IbizproIndexSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproIndex> pages=baseMapper.searchIndexDER(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproIndex>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbizproIndex> getIbizproindexByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproIndex> getIbizproindexByEntities(List<IbizproIndex> entities) {
        List ids =new ArrayList();
        for(IbizproIndex entity : entities){
            Serializable id=entity.getIndexid();
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


    public IIbizproIndexService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproIndex dynamicCall(Long key, String action, IbizproIndex et) {
        return et;
    }
}



