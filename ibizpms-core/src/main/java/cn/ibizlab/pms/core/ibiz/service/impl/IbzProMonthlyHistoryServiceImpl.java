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
import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProMonthlyHistorySearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzProMonthlyHistoryService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzProMonthlyHistoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[月报操作历史] 服务对象接口实现
 */
@Slf4j
@Service("IbzProMonthlyHistoryServiceImpl")
public class IbzProMonthlyHistoryServiceImpl extends ServiceImpl<IbzProMonthlyHistoryMapper, IbzProMonthlyHistory> implements IIbzProMonthlyHistoryService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzProMonthlyHistoryRuntime ibzpromonthlyhistoryRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzProMonthlyActionService ibzpromonthlyactionService;

    protected int batchSize = 500;

    @Override
    public List<IbzProMonthlyHistory> select(IbzProMonthlyHistorySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProMonthlyHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzProMonthlyHistory et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzProMonthlyHistory> list) {
        if(ibzpromonthlyhistoryRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IbzProMonthlyHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzProMonthlyHistory> list) {
        if(ibzpromonthlyhistoryRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzProMonthlyHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzpromonthlyhistoryRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzpromonthlyhistoryRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IbzProMonthlyHistory get(Long key) {
        IbzProMonthlyHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzpromonthlyhistoryRuntime.isRtmodel()){
            }
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IbzProMonthlyHistory sysGet(Long key) {
        IbzProMonthlyHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzProMonthlyHistory getDraft(IbzProMonthlyHistory et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzProMonthlyHistory et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzProMonthlyHistory et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzProMonthlyHistory et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzProMonthlyHistory> list) {
        List<IbzProMonthlyHistory> create = new ArrayList<>();
        List<IbzProMonthlyHistory> update = new ArrayList<>();
        for (IbzProMonthlyHistory et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
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
    public void saveBatch(List<IbzProMonthlyHistory> list) {
        List<IbzProMonthlyHistory> create = new ArrayList<>();
        List<IbzProMonthlyHistory> update = new ArrayList<>();
        for (IbzProMonthlyHistory et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
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


	@Override
    public List<IbzProMonthlyHistory> selectByAction(Long id) {
        return baseMapper.selectByAction(id);
    }
    @Override
    public void removeByAction(Long id) {
        this.remove(new QueryWrapper<IbzProMonthlyHistory>().eq("action",id));
    }

    public IIbzProMonthlyHistoryService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByAction(Long id,List<IbzProMonthlyHistory> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IbzProMonthlyHistory> _update=new ArrayList<IbzProMonthlyHistory>();
        List<IbzProMonthlyHistory> _create=new ArrayList<IbzProMonthlyHistory>();
        for(IbzProMonthlyHistory before:selectByAction(id)){
            delIds.add(before.getId());
        }
        for(IbzProMonthlyHistory sub:list) {
            sub.setAction(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}


    public List<IbzProMonthlyHistory> selectDefault(IbzProMonthlyHistorySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzProMonthlyHistory> selectSimple(IbzProMonthlyHistorySearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IbzProMonthlyHistory> selectView(IbzProMonthlyHistorySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzProMonthlyHistory> searchDefault(IbzProMonthlyHistorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProMonthlyHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProMonthlyHistory>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzProMonthlyHistory> getIbzpromonthlyhistoryByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzProMonthlyHistory> getIbzpromonthlyhistoryByEntities(List<IbzProMonthlyHistory> entities) {
        List ids =new ArrayList();
        for(IbzProMonthlyHistory entity : entities){
            Serializable id=entity.getId();
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


    @Override
    @Transactional
    public IbzProMonthlyHistory dynamicCall(Long key, String action, IbzProMonthlyHistory et) {
        return et;
    }
}



