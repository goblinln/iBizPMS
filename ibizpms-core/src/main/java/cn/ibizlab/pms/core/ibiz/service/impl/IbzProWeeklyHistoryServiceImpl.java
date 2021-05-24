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
import cn.ibizlab.pms.core.ibiz.domain.IbzProWeeklyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProWeeklyHistorySearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzProWeeklyHistoryService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzProWeeklyHistoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[周报操作历史] 服务对象接口实现
 */
@Slf4j
@Service("IbzProWeeklyHistoryServiceImpl")
public class IbzProWeeklyHistoryServiceImpl extends ServiceImpl<IbzProWeeklyHistoryMapper, IbzProWeeklyHistory> implements IIbzProWeeklyHistoryService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzProWeeklyHistoryRuntime ibzproweeklyhistoryRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZProWeeklyActionService ibzproweeklyactionService;

    protected int batchSize = 500;

    @Override
    public List<IbzProWeeklyHistory> select(IbzProWeeklyHistorySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProWeeklyHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzProWeeklyHistory et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzProWeeklyHistory> list) {
        if(ibzproweeklyhistoryRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IbzProWeeklyHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzProWeeklyHistory> list) {
        if(ibzproweeklyhistoryRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzProWeeklyHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzproweeklyhistoryRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzproweeklyhistoryRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IbzProWeeklyHistory get(Long key) {
        IbzProWeeklyHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzproweeklyhistoryRuntime.isRtmodel()){
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
    public IbzProWeeklyHistory sysGet(Long key) {
        IbzProWeeklyHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzProWeeklyHistory getDraft(IbzProWeeklyHistory et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzProWeeklyHistory et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzProWeeklyHistory et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzProWeeklyHistory et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzProWeeklyHistory> list) {
        List<IbzProWeeklyHistory> create = new ArrayList<>();
        List<IbzProWeeklyHistory> update = new ArrayList<>();
        for (IbzProWeeklyHistory et : list) {
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
    public void saveBatch(List<IbzProWeeklyHistory> list) {
        List<IbzProWeeklyHistory> create = new ArrayList<>();
        List<IbzProWeeklyHistory> update = new ArrayList<>();
        for (IbzProWeeklyHistory et : list) {
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
    public List<IbzProWeeklyHistory> selectByAction(Long id) {
        return baseMapper.selectByAction(id);
    }
    @Override
    public void removeByAction(Long id) {
        this.remove(new QueryWrapper<IbzProWeeklyHistory>().eq("action",id));
    }

    public IIbzProWeeklyHistoryService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByAction(Long id,List<IbzProWeeklyHistory> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IbzProWeeklyHistory> _update=new ArrayList<IbzProWeeklyHistory>();
        List<IbzProWeeklyHistory> _create=new ArrayList<IbzProWeeklyHistory>();
        for(IbzProWeeklyHistory before:selectByAction(id)){
            delIds.add(before.getId());
        }
        for(IbzProWeeklyHistory sub:list) {
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


    public List<IbzProWeeklyHistory> selectDefault(IbzProWeeklyHistorySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzProWeeklyHistory> selectSimple(IbzProWeeklyHistorySearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IbzProWeeklyHistory> selectView(IbzProWeeklyHistorySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzProWeeklyHistory> searchDefault(IbzProWeeklyHistorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProWeeklyHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProWeeklyHistory>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzProWeeklyHistory> getIbzproweeklyhistoryByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzProWeeklyHistory> getIbzproweeklyhistoryByEntities(List<IbzProWeeklyHistory> entities) {
        List ids =new ArrayList();
        for(IbzProWeeklyHistory entity : entities){
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
    public IbzProWeeklyHistory dynamicCall(Long key, String action, IbzProWeeklyHistory et) {
        return et;
    }
}



