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
import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IAccountTaskestimateService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.AccountTaskestimateMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用户工时统计] 服务对象接口实现
 */
@Slf4j
@Service("AccountTaskestimateServiceImpl")
public class AccountTaskestimateServiceImpl extends ServiceImpl<AccountTaskestimateMapper, AccountTaskestimate> implements IAccountTaskestimateService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.AccountTaskestimateRuntime accounttaskestimateRuntime;


    protected int batchSize = 500;

    @Override
    public List<AccountTaskestimate> select(AccountTaskestimateSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    @Transactional
    public boolean create(AccountTaskestimate et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<AccountTaskestimate> list) {
        if(accounttaskestimateRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(AccountTaskestimate et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<AccountTaskestimate> list) {
        if(accounttaskestimateRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(AccountTaskestimate et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!accounttaskestimateRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        if(accounttaskestimateRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public AccountTaskestimate get(String key) {
        AccountTaskestimate et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!accounttaskestimateRuntime.isRtmodel()){
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
    public AccountTaskestimate sysGet(String key) {
        AccountTaskestimate et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public AccountTaskestimate getDraft(AccountTaskestimate et) {
        return et;
    }

    @Override
    public boolean checkKey(AccountTaskestimate et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(AccountTaskestimate et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(AccountTaskestimate et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<AccountTaskestimate> list) {
        List<AccountTaskestimate> create = new ArrayList<>();
        List<AccountTaskestimate> update = new ArrayList<>();
        for (AccountTaskestimate et : list) {
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
    public void saveBatch(List<AccountTaskestimate> list) {
        List<AccountTaskestimate> create = new ArrayList<>();
        List<AccountTaskestimate> update = new ArrayList<>();
        for (AccountTaskestimate et : list) {
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



    public List<AccountTaskestimate> selectAllAccountEstimate(AccountTaskestimateSearchContext context){
        return baseMapper.selectAllAccountEstimate(context, context.getSelectCond());
    }
    public List<AccountTaskestimate> selectDefault(AccountTaskestimateSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<AccountTaskestimate> selectView(AccountTaskestimateSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 所有用户工时
     */
    @Override
    public Page<AccountTaskestimate> searchAllAccountEstimate(AccountTaskestimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AccountTaskestimate> pages=baseMapper.searchAllAccountEstimate(context.getPages(),context,context.getSelectCond());
        return new PageImpl<AccountTaskestimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<AccountTaskestimate> searchDefault(AccountTaskestimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AccountTaskestimate> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<AccountTaskestimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IAccountTaskestimateService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public AccountTaskestimate dynamicCall(String key, String action, AccountTaskestimate et) {
        return et;
    }
}



