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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductHistorySearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProductHistoryService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZProProductHistoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品操作历史] 服务对象接口实现
 */
@Slf4j
@Service("IBZProProductHistoryServiceImpl")
public class IBZProProductHistoryServiceImpl extends ServiceImpl<IBZProProductHistoryMapper, IBZProProductHistory> implements IIBZProProductHistoryService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZProProductHistoryRuntime ibzproproducthistoryRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZProProductActionService ibzproproductactionService;

    protected int batchSize = 500;

    @Override
    public List<IBZProProductHistory> select(IBZProProductHistorySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProProductHistory et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(IBZProProductHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(IBZProProductHistory et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzproproducthistoryRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public IBZProProductHistory get(Long key) {
        IBZProProductHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzproproducthistoryRuntime.isRtmodel()){
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
    public IBZProProductHistory sysGet(Long key) {
        IBZProProductHistory et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProProductHistory getDraft(IBZProProductHistory et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProProductHistory et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IBZProProductHistory et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProProductHistory et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }



	@Override
    public List<IBZProProductHistory> selectByAction(Long id) {
        return baseMapper.selectByAction(id);
    }
    @Override
    public void removeByAction(Long id) {
        this.remove(new QueryWrapper<IBZProProductHistory>().eq("action",id));
    }

    public IIBZProProductHistoryService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByAction(Long id,List<IBZProProductHistory> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IBZProProductHistory> _update=new ArrayList<IBZProProductHistory>();
        List<IBZProProductHistory> _create=new ArrayList<IBZProProductHistory>();
        for(IBZProProductHistory before:selectByAction(id)){
            delIds.add(before.getId());
        }
        for(IBZProProductHistory sub:list) {
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


    public List<IBZProProductHistory> selectDefault(IBZProProductHistorySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProProductHistory> selectSimple(IBZProProductHistorySearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZProProductHistory> selectView(IBZProProductHistorySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProProductHistory> searchDefault(IBZProProductHistorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductHistory> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductHistory>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProProductHistory> getIbzproproducthistoryByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProProductHistory> getIbzproproducthistoryByEntities(List<IBZProProductHistory> entities) {
        List ids =new ArrayList();
        for(IBZProProductHistory entity : entities){
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
    public IBZProProductHistory dynamicCall(Long key, String action, IBZProProductHistory et) {
        return et;
    }
}



