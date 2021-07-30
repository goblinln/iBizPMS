package cn.ibizlab.pms.core.zentao.service.impl;

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
import cn.ibizlab.pms.core.zentao.domain.Entity2;
import cn.ibizlab.pms.core.zentao.filter.Entity2SearchContext;
import cn.ibizlab.pms.core.zentao.service.IEntity2Service;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.Entity2Mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[实体2] 服务对象接口实现
 */
@Slf4j
@Service("Entity2ServiceImpl")
public class Entity2ServiceImpl extends ServiceImpl<Entity2Mapper, Entity2> implements IEntity2Service {


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(Entity2 et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getEntity2id()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Entity2> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(Entity2 et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("entity2id", et.getEntity2id()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getEntity2id()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<Entity2> list) {
        updateBatchById(list, batchSize);
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
    public Entity2 get(String key) {
        Entity2 et = getById(key);
        if(et == null){
            et = new Entity2();
            et.setEntity2id(key);
        }
        else {
        }
        return et;
    }

    @Override
    public Entity2 getDraft(Entity2 et) {
        return et;
    }

    @Override
    public boolean checkKey(Entity2 et) {
        return (!ObjectUtils.isEmpty(et.getEntity2id())) && (!Objects.isNull(this.getById(et.getEntity2id())));
    }
    @Override
    @Transactional
    public boolean save(Entity2 et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Entity2 et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Entity2> list) {
        List<Entity2> create = new ArrayList<>();
        List<Entity2> update = new ArrayList<>();
        for (Entity2 et : list) {
            if (ObjectUtils.isEmpty(et.getEntity2id()) || ObjectUtils.isEmpty(getById(et.getEntity2id()))) {
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
    public void saveBatch(List<Entity2> list) {
        List<Entity2> create = new ArrayList<>();
        List<Entity2> update = new ArrayList<>();
        for (Entity2 et : list) {
            if (ObjectUtils.isEmpty(et.getEntity2id()) || ObjectUtils.isEmpty(getById(et.getEntity2id()))) {
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
    public Page<Entity2> searchDefault(Entity2SearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Entity2> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Entity2>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<Entity2> getEntity2ByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Entity2> getEntity2ByEntities(List<Entity2> entities) {
        List ids =new ArrayList();
        for(Entity2 entity : entities){
            Serializable id=entity.getEntity2id();
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


    public IEntity2Service getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
}


