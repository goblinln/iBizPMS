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
import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProBugHostorySearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZProBugHostoryService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZProBugHostoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[Bug操作历史] 服务对象接口实现
 */
@Slf4j
@Service("IBZProBugHostoryServiceImpl")
public class IBZProBugHostoryServiceImpl extends ServiceImpl<IBZProBugHostoryMapper, IBZProBugHostory> implements IIBZProBugHostoryService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZProBugHostoryRuntime ibzprobughostoryRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzProBugActionService ibzprobugactionService;

    protected int batchSize = 500;

    @Override
    public List<IBZProBugHostory> select(IBZProBugHostorySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProBugHostory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProBugHostory et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProBugHostory> list) {
        if(ibzprobughostoryRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZProBugHostory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProBugHostory> list) {
        if(ibzprobughostoryRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProBugHostory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzprobughostoryRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzprobughostoryRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZProBugHostory get(Long key) {
        IBZProBugHostory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzprobughostoryRuntime.isRtmodel()){
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
    public IBZProBugHostory sysGet(Long key) {
        IBZProBugHostory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProBugHostory getDraft(IBZProBugHostory et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProBugHostory et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IBZProBugHostory et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProBugHostory et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProBugHostory> list) {
        List<IBZProBugHostory> create = new ArrayList<>();
        List<IBZProBugHostory> update = new ArrayList<>();
        for (IBZProBugHostory et : list) {
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
    public void saveBatch(List<IBZProBugHostory> list) {
        List<IBZProBugHostory> create = new ArrayList<>();
        List<IBZProBugHostory> update = new ArrayList<>();
        for (IBZProBugHostory et : list) {
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
    public List<IBZProBugHostory> selectByAction(Long id) {
        return baseMapper.selectByAction(id);
    }
    @Override
    public void removeByAction(Long id) {
        this.remove(new QueryWrapper<IBZProBugHostory>().eq("action",id));
    }

    public IIBZProBugHostoryService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByAction(Long id,List<IBZProBugHostory> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IBZProBugHostory> _update=new ArrayList<IBZProBugHostory>();
        List<IBZProBugHostory> _create=new ArrayList<IBZProBugHostory>();
        for(IBZProBugHostory before:selectByAction(id)){
            delIds.add(before.getId());
        }
        for(IBZProBugHostory sub:list) {
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


    public List<IBZProBugHostory> selectDefault(IBZProBugHostorySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProBugHostory> selectSimple(IBZProBugHostorySearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZProBugHostory> selectView(IBZProBugHostorySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProBugHostory> searchDefault(IBZProBugHostorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProBugHostory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProBugHostory>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProBugHostory> getIbzprobughostoryByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProBugHostory> getIbzprobughostoryByEntities(List<IBZProBugHostory> entities) {
        List ids =new ArrayList();
        for(IBZProBugHostory entity : entities){
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
    public IBZProBugHostory dynamicCall(Long key, String action, IBZProBugHostory et) {
        return et;
    }
}



