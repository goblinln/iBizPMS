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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSysTpl;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProSysTplSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProSysTplService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IBZProSysTplMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[系统模板] 服务对象接口实现
 */
@Slf4j
@Service("IBZProSysTplServiceImpl")
public class IBZProSysTplServiceImpl extends ServiceImpl<IBZProSysTplMapper, IBZProSysTpl> implements IIBZProSysTplService {

    @Autowired
    cn.ibizlab.pms.core.ibizpro.runtime.IBZProSysTplRuntime ibzprosystplRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IFileService fileService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IBZProSysTpl et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystplid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProSysTpl> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IBZProSysTpl et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_systplid", et.getIbzprosystplid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystplid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProSysTpl> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProSysTpl et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_systplid", et.getIbzprosystplid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystplid()), et);
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
    public IBZProSysTpl get(String key) {
        IBZProSysTpl et = getById(key);
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
    public IBZProSysTpl sysGet(String key) {
        IBZProSysTpl et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IBZProSysTpl getDraft(IBZProSysTpl et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProSysTpl et) {
        return (!ObjectUtils.isEmpty(et.getIbzprosystplid())) && (!Objects.isNull(this.getById(et.getIbzprosystplid())));
    }
    @Override
    @Transactional
    public boolean save(IBZProSysTpl et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProSysTpl et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProSysTpl> list) {
        List<IBZProSysTpl> create = new ArrayList<>();
        List<IBZProSysTpl> update = new ArrayList<>();
        for (IBZProSysTpl et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprosystplid()) || ObjectUtils.isEmpty(getById(et.getIbzprosystplid()))) {
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
    public void saveBatch(List<IBZProSysTpl> list) {
        List<IBZProSysTpl> create = new ArrayList<>();
        List<IBZProSysTpl> update = new ArrayList<>();
        for (IBZProSysTpl et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprosystplid()) || ObjectUtils.isEmpty(getById(et.getIbzprosystplid()))) {
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
    public List<IBZProSysTpl> selectByFile(Long id) {
        return baseMapper.selectByFile(id);
    }
    @Override
    public void removeByFile(Long id) {
        this.remove(new QueryWrapper<IBZProSysTpl>().eq("file",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProSysTpl> searchDefault(IBZProSysTplSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProSysTpl> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProSysTpl>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProSysTpl> getIbzprosystplByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProSysTpl> getIbzprosystplByEntities(List<IBZProSysTpl> entities) {
        List ids =new ArrayList();
        for(IBZProSysTpl entity : entities){
            Serializable id=entity.getIbzprosystplid();
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


    public IIBZProSysTplService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProSysTpl dynamicCall(String key, String action, IBZProSysTpl et) {
        return et;
    }
}



