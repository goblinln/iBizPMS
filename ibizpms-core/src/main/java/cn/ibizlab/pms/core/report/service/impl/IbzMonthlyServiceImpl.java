package cn.ibizlab.pms.core.report.service.impl;

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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzMonthlyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[月报] 服务对象接口实现
 */
@Slf4j
@Service("IbzMonthlyServiceImpl")
public class IbzMonthlyServiceImpl extends ServiceImpl<IbzMonthlyMapper, IbzMonthly> implements IIbzMonthlyService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.report.runtime.IbzMonthlyRuntime ibzmonthlyRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzProMonthlyActionService ibzpromonthlyactionService;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.report.service.IIbzMonthlyService ibzmonthlyService;

    protected int batchSize = 500;

    @Override
    public List<IbzMonthly> select(IbzMonthlySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzMonthly et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzMonthly> list) {
        if(ibzmonthlyRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IbzMonthly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_monthlyid", et.getIbzmonthlyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzMonthly> list) {
        if(ibzmonthlyRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzMonthly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_monthlyid", et.getIbzmonthlyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzmonthlyRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzmonthlyRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IbzMonthly get(Long key) {
        IbzMonthly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzmonthlyRuntime.isRtmodel()){
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
    public IbzMonthly sysGet(Long key) {
        IbzMonthly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzMonthly getDraft(IbzMonthly et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzMonthly et) {
        return (!ObjectUtils.isEmpty(et.getIbzmonthlyid())) && (!Objects.isNull(this.getById(et.getIbzmonthlyid())));
    }
    @Override
    @Transactional
    public IbzMonthly createGetInfo(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IbzMonthly createUserMonthly(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IbzMonthly editGetCompleteTask(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IbzMonthly haveRead(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IbzMonthly pushUserMonthly(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(IbzMonthly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzMonthly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzMonthly> list) {
        List<IbzMonthly> create = new ArrayList<>();
        List<IbzMonthly> update = new ArrayList<>();
        for (IbzMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbzmonthlyid()))) {
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
    public void saveBatch(List<IbzMonthly> list) {
        List<IbzMonthly> create = new ArrayList<>();
        List<IbzMonthly> update = new ArrayList<>();
        for (IbzMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbzmonthlyid()))) {
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
    @Transactional
    public IbzMonthly submit(IbzMonthly et) {
        //自定义代码
        return et;
    }



    public List<IbzMonthly> selectDefault(IbzMonthlySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectMyMonthly(IbzMonthlySearchContext context){
        return baseMapper.selectMyMonthly(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectMyMonthlyMob(IbzMonthlySearchContext context){
        return baseMapper.selectMyMonthlyMob(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectMyReceivedMonthly(IbzMonthlySearchContext context){
        return baseMapper.selectMyReceivedMonthly(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectMySubmitMonthly(IbzMonthlySearchContext context){
        return baseMapper.selectMySubmitMonthly(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectProductMonthly(IbzMonthlySearchContext context){
        return baseMapper.selectProductMonthly(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectProjectMonthly(IbzMonthlySearchContext context){
        return baseMapper.selectProjectMonthly(context, context.getSelectCond());
    }
    public List<IbzMonthly> selectView(IbzMonthlySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzMonthly> searchDefault(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的月报
     */
    @Override
    public Page<IbzMonthly> searchMyMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的月报（移动端）
     */
    @Override
    public Page<IbzMonthly> searchMyMonthlyMob(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyMonthlyMob(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的月报
     */
    @Override
    public Page<IbzMonthly> searchMyReceivedMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyReceivedMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我提交的月报
     */
    @Override
    public Page<IbzMonthly> searchMySubmitMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMySubmitMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品月报
     */
    @Override
    public Page<IbzMonthly> searchProductMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchProductMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目月报
     */
    @Override
    public Page<IbzMonthly> searchProjectMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchProjectMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzMonthly> getIbzmonthlyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzMonthly> getIbzmonthlyByEntities(List<IbzMonthly> entities) {
        List ids =new ArrayList();
        for(IbzMonthly entity : entities){
            Serializable id=entity.getIbzmonthlyid();
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


    public IIbzMonthlyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzMonthly dynamicCall(Long key, String action, IbzMonthly et) {
        return et;
    }
}



