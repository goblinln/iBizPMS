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


import cn.ibizlab.pms.core.ibizpro.repository.IbizproIndexRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import javax.annotation.Resource;
import com.mongodb.QueryBuilder;
/**
 * 实体[索引检索] 服务对象接口实现
 */
@Slf4j
@Service
public class IbizproIndexServiceImpl implements IIbizproIndexService {

    @Autowired
    protected IbizproIndexRepository repository;

    @Override
    @Transactional
    public boolean create(IbizproIndex et) {
        repository.insert(et);
        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true ;
    }

    @Override
    public void createBatch(List<IbizproIndex> list) {
        repository.insert(list);
    }

    @Override
    @Transactional
    public boolean update(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true ;
    }

    @Override
    public void updateBatch(List<IbizproIndex> list) {
        repository.saveAll(list);
    }

     @Override
    @Transactional
    public boolean sysUpdate(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true ;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        repository.deleteById(key);
        return true ;
    }

    @Override
    public void removeBatch(Collection<Long> idList) {
        repository.deleteAll(repository.findAllById(idList));
    }

    @Override
    @Transactional
    public IbizproIndex get(Long key) {
        Optional<IbizproIndex> result = repository.findById(key);
        if(!result.isPresent()) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else{
            IbizproIndex et=result.get();
            return et;
        }
    }

    @Override
    @Transactional
    public IbizproIndex sysGet(Long key) {
        Optional<IbizproIndex> result = repository.findById(key);
        if(!result.isPresent()) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else{
            IbizproIndex et=result.get();
            return et;
        }
    }

    @Override
    public IbizproIndex getDraft(IbizproIndex et) {
        return et;
    }

    @Override
    public boolean checkKey(IbizproIndex et) {
        return repository.findById(et.getIndexid()).isPresent();
    }

    @Override
    @Transactional
    public boolean save(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getIndexid()), et);
        return true ;
    }


    @Override
    public void saveBatch(List<IbizproIndex> list) {
        repository.saveAll(list);
    }





    @Resource
    protected MongoTemplate mongoTemplate;

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproIndex> searchDefault(IbizproIndexSearchContext context) {
        Query query = new BasicQuery(context.getSelectCond().get().toString());
        long total = mongoTemplate.count(query, IbizproIndex.class);
        List<IbizproIndex> list=mongoTemplate.find(query.with(context.getPageable()),IbizproIndex.class);
        return new PageImpl<IbizproIndex>(list,context.getPageable(),total);
    }

    /**
     * 查询集合 全文检索
     */
    @Override
    public Page<IbizproIndex> searchESquery(IbizproIndexSearchContext context) {
        Query query = new BasicQuery(context.getSelectCond().get().toString());
        long total = mongoTemplate.count(query, IbizproIndex.class);
        List<IbizproIndex> list=mongoTemplate.find(query.with(context.getPageable()),IbizproIndex.class);
        return new PageImpl<IbizproIndex>(list,context.getPageable(),total);
    }

    /**
     * 查询集合 数据集2
     */
    @Override
    public Page<IbizproIndex> searchIndexDER(IbizproIndexSearchContext context) {
        Query query = new BasicQuery(context.getSelectCond().get().toString());
        long total = mongoTemplate.count(query, IbizproIndex.class);
        List<IbizproIndex> list=mongoTemplate.find(query.with(context.getPageable()),IbizproIndex.class);
        return new PageImpl<IbizproIndex>(list,context.getPageable(),total);
    }


    @Override
    public List<IbizproIndex> getIbizproindexByIds(List<Long> ids) {
        QueryBuilder permissionCond=new QueryBuilder();
        permissionCond.and("indexid").in(ids);
        Query query = new BasicQuery(permissionCond.get().toString());
        return mongoTemplate.find(query,IbizproIndex.class);
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
        if(ids.size()>0){
            QueryBuilder permissionCond=new QueryBuilder();
            permissionCond.and("indexid").in(ids);
            Query query = new BasicQuery(permissionCond.get().toString());
            return mongoTemplate.find(query,IbizproIndex.class);
        }
        else
            return entities;
    }
    @Autowired
    @Lazy
    cn.ibizlab.pms.core.es.service.IIbizproIndexESService esService;

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.mapping.IbizproIndexESMapping esMapping;

    /**
     * 获取es service
     * @return
     */
    public cn.ibizlab.pms.core.es.service.IIbizproIndexESService getESService(){
        return esService;
    }

    /**
     * 获取es mapping
     * @return
     */
    public cn.ibizlab.pms.core.ibizpro.mapping.IbizproIndexESMapping getESMapping(){
        return esMapping;
    }
    @Override
    @Transactional
    public IbizproIndex dynamicCall(Long key, String action, IbizproIndex et) {
        return et;
    }
}




