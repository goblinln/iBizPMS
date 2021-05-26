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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductMonthlySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductMonthlyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproProductMonthlyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品月报] 服务对象接口实现
 */
@Slf4j
@Service("IbizproProductMonthlyServiceImpl")
public class IbizproProductMonthlyServiceImpl extends ServiceImpl<IbizproProductMonthlyMapper, IbizproProductMonthly> implements IIbizproProductMonthlyService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductMonthlyRuntime ibizproproductmonthlyRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<IbizproProductMonthly> select(IbizproProductMonthlySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProductMonthly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbizproProductMonthly et) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproproductmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproProductMonthly> list) {
        if(ibizproproductmonthlyRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
            list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IbizproProductMonthly et) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_productmonthlyid", et.getIbizproproductmonthlyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproproductmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproProductMonthly> list) {
        if(ibizproproductmonthlyRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
            list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbizproProductMonthly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_productmonthlyid", et.getIbizproproductmonthlyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproproductmonthlyid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibizproproductmonthlyRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IbizproProductMonthly get(Long key) {
        IbizproProductMonthly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibizproproductmonthlyRuntime.isRtmodel()){
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
    public IbizproProductMonthly sysGet(Long key) {
        IbizproProductMonthly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbizproProductMonthly getDraft(IbizproProductMonthly et) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(IbizproProductMonthly et) {
        return (!ObjectUtils.isEmpty(et.getIbizproproductmonthlyid())) && (!Objects.isNull(this.getById(et.getIbizproproductmonthlyid())));
    }
    @Override
    @Transactional
    public IbizproProductMonthly manualCreateMonthly(IbizproProductMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(IbizproProductMonthly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproProductMonthly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproProductMonthly> list) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbizproProductMonthly> create = new ArrayList<>();
        List<IbizproProductMonthly> update = new ArrayList<>();
        for (IbizproProductMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproproductmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbizproproductmonthlyid()))) {
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
    public void saveBatch(List<IbizproProductMonthly> list) {
        if(!ibizproproductmonthlyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbizproProductMonthly> create = new ArrayList<>();
        List<IbizproProductMonthly> update = new ArrayList<>();
        for (IbizproProductMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproproductmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbizproproductmonthlyid()))) {
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
    public IbizproProductMonthly statsProductMonthly(IbizproProductMonthly et) {
        //自定义代码
        return et;
    }


	@Override
    public List<IbizproProductMonthly> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<IbizproProductMonthly>().eq("product",id));
    }


    public List<IbizproProductMonthly> selectDefault(IbizproProductMonthlySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbizproProductMonthly> selectView(IbizproProductMonthlySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproProductMonthly> searchDefault(IbizproProductMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProductMonthly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProductMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbizproProductMonthly et){
        //实体关系[DER1N_IBIZPRO_PRODUCTMONTHLY_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setPo(ztproduct.getPo());
            et.setProductname(ztproduct.getName());
        }
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
    public List<IbizproProductMonthly> getIbizproproductmonthlyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproProductMonthly> getIbizproproductmonthlyByEntities(List<IbizproProductMonthly> entities) {
        List ids =new ArrayList();
        for(IbizproProductMonthly entity : entities){
            Serializable id=entity.getIbizproproductmonthlyid();
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


    public IIbizproProductMonthlyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproProductMonthly dynamicCall(Long key, String action, IbizproProductMonthly et) {
        return et;
    }
}



