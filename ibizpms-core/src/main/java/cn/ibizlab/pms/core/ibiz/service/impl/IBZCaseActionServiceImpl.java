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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZCaseActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZCaseActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试用例日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZCaseActionServiceImpl")
public class IBZCaseActionServiceImpl extends ServiceImpl<IBZCaseActionMapper, IBZCaseAction> implements IIBZCaseActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZCaseActionRuntime ibzcaseactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZCaseHistoryService ibzcasehistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;

    protected int batchSize = 500;

    @Override
    public List<IBZCaseAction> select(IBZCaseActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZCaseAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZCaseAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzcaseactionRuntime.isRtmodel()){
            ibzcasehistoryService.saveByAction(et.getId(), et.getIbzcasehistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZCaseAction> list) {
        if(ibzcaseactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZCaseAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzcaseactionRuntime.isRtmodel()){
            ibzcasehistoryService.saveByAction(et.getId(), et.getIbzcasehistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZCaseAction> list) {
        if(ibzcaseactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZCaseAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzcaseactionRuntime.isRtmodel()){
            ibzcasehistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzcaseactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZCaseAction get(Long key) {
        IBZCaseAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzcaseactionRuntime.isRtmodel()){
                et.setIbzcasehistory(ibzcasehistoryService.selectByAction(key));
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
    public IBZCaseAction sysGet(Long key) {
        IBZCaseAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZCaseAction getDraft(IBZCaseAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZCaseAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IBZCaseAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZCaseAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZCaseAction> list) {
        List<IBZCaseAction> create = new ArrayList<>();
        List<IBZCaseAction> update = new ArrayList<>();
        for (IBZCaseAction et : list) {
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
    public void saveBatch(List<IBZCaseAction> list) {
        List<IBZCaseAction> create = new ArrayList<>();
        List<IBZCaseAction> update = new ArrayList<>();
        for (IBZCaseAction et : list) {
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
    public List<IBZCaseAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IBZCaseAction>().eq("objectid",id));
    }


    public List<IBZCaseAction> selectDefault(IBZCaseActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZCaseAction> selectSimple(IBZCaseActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZCaseAction> selectType(IBZCaseActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZCaseAction> selectView(IBZCaseActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZCaseAction> searchDefault(IBZCaseActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZCaseAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZCaseAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZCaseAction> searchType(IBZCaseActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZCaseAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZCaseAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZCaseAction> getIbzcaseactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZCaseAction> getIbzcaseactionByEntities(List<IBZCaseAction> entities) {
        List ids =new ArrayList();
        for(IBZCaseAction entity : entities){
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


    /**
     * 获取searchContext
     * @return
     */
    public IBZCaseActionSearchContext getSearchContext(){
        return new IBZCaseActionSearchContext();
    }
    public IIBZCaseActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZCaseAction dynamicCall(Long key, String action, IBZCaseAction et) {
        return et;
    }
}



