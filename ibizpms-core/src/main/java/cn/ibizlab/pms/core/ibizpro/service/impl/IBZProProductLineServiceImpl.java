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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductLineSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProProductLineService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IBZProProductLineMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品线] 服务对象接口实现
 */
@Slf4j
@Service("IBZProProductLineServiceImpl")
public class IBZProProductLineServiceImpl extends ServiceImpl<IBZProProductLineMapper, IBZProProductLine> implements IIBZProProductLineService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IBZProProductLineRuntime ibzproproductlineRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<IBZProProductLine> select(IBZProProductLineSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    @Transactional
    public boolean create(IBZProProductLine et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProProductLine> list) {
        if(ibzproproductlineRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZProProductLine et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProProductLine> list) {
        if(ibzproproductlineRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProProductLine et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzproproductlineRuntime.isRtmodel()){
        if(!ObjectUtils.isEmpty(productService.selectByLine(key)))
            throw new BadRequestAlertException("删除数据失败，当前数据存在关系实体[Product]数据，无法删除!","","");
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzproproductlineRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        if(!ObjectUtils.isEmpty(productService.selectByLine(idList)))
            throw new BadRequestAlertException("删除数据失败，当前数据存在关系实体[Product]数据，无法删除!","","");
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZProProductLine get(Long key) {
        IBZProProductLine et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzproproductlineRuntime.isRtmodel()){
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
    public IBZProProductLine sysGet(Long key) {
        IBZProProductLine et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProProductLine getDraft(IBZProProductLine et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProProductLine et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IBZProProductLine et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProProductLine et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProProductLine> list) {
        List<IBZProProductLine> create = new ArrayList<>();
        List<IBZProProductLine> update = new ArrayList<>();
        for (IBZProProductLine et : list) {
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
    public void saveBatch(List<IBZProProductLine> list) {
        List<IBZProProductLine> create = new ArrayList<>();
        List<IBZProProductLine> update = new ArrayList<>();
        for (IBZProProductLine et : list) {
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



    public List<IBZProProductLine> selectDefault(IBZProProductLineSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProProductLine> selectSimple(IBZProProductLineSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZProProductLine> selectView(IBZProProductLineSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProProductLine> searchDefault(IBZProProductLineSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductLine> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductLine>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProProductLine> getIbzproproductlineByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProProductLine> getIbzproproductlineByEntities(List<IBZProProductLine> entities) {
        List ids =new ArrayList();
        for(IBZProProductLine entity : entities){
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


    public IIBZProProductLineService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProProductLine dynamicCall(Long key, String action, IBZProProductLine et) {
        return et;
    }
}



