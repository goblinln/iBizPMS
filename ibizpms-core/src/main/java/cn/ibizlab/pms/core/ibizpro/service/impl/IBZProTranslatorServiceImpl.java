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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProTranslator;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProTranslatorSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProTranslatorService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IBZProTranslatorMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[业务值转换] 服务对象接口实现
 */
@Slf4j
@Service("IBZProTranslatorServiceImpl")
public class IBZProTranslatorServiceImpl extends ServiceImpl<IBZProTranslatorMapper, IBZProTranslator> implements IIBZProTranslatorService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IBZProTranslatorRuntime ibzprotranslatorRuntime;


    protected int batchSize = 500;

    @Override
    public List<IBZProTranslator> select(IBZProTranslatorSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProTranslator> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProTranslator et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprotranslatorid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProTranslator> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IBZProTranslator et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_translatorid", et.getIbzprotranslatorid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprotranslatorid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProTranslator> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProTranslator et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_translatorid", et.getIbzprotranslatorid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprotranslatorid()), et);
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
    public IBZProTranslator get(String key) {
        IBZProTranslator et = getById(key);
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
    public IBZProTranslator sysGet(String key) {
        IBZProTranslator et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IBZProTranslator getDraft(IBZProTranslator et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProTranslator et) {
        return (!ObjectUtils.isEmpty(et.getIbzprotranslatorid())) && (!Objects.isNull(this.getById(et.getIbzprotranslatorid())));
    }
    @Override
    @Transactional
    public boolean save(IBZProTranslator et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProTranslator et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProTranslator> list) {
        List<IBZProTranslator> create = new ArrayList<>();
        List<IBZProTranslator> update = new ArrayList<>();
        for (IBZProTranslator et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprotranslatorid()) || ObjectUtils.isEmpty(getById(et.getIbzprotranslatorid()))) {
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
    public void saveBatch(List<IBZProTranslator> list) {
        List<IBZProTranslator> create = new ArrayList<>();
        List<IBZProTranslator> update = new ArrayList<>();
        for (IBZProTranslator et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprotranslatorid()) || ObjectUtils.isEmpty(getById(et.getIbzprotranslatorid()))) {
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
    public Page<IBZProTranslator> searchDefault(IBZProTranslatorSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProTranslator> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProTranslator>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProTranslator> getIbzprotranslatorByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProTranslator> getIbzprotranslatorByEntities(List<IBZProTranslator> entities) {
        List ids =new ArrayList();
        for(IBZProTranslator entity : entities){
            Serializable id=entity.getIbzprotranslatorid();
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


    public IIBZProTranslatorService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProTranslator dynamicCall(String key, String action, IBZProTranslator et) {
        return et;
    }
}



