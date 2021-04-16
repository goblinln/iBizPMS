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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductWeeklyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproProductWeeklyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品周报] 服务对象接口实现
 */
@Slf4j
@Service("IbizproProductWeeklyServiceImpl")
public class IbizproProductWeeklyServiceImpl extends ServiceImpl<IbizproProductWeeklyMapper, IbizproProductWeekly> implements IIbizproProductWeeklyService {

    @Autowired
    cn.ibizlab.pms.core.ibizpro.runtime IbizproProductWeeklyRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbizproProductWeekly et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproProductweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproProductWeekly> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbizproProductWeekly et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_productweeklyid", et.getIbizproProductweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproProductweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproProductWeekly> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbizproProductWeekly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_productweeklyid", et.getIbizproProductweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproProductweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbizproProductWeekly get(Long key) {
        IbizproProductWeekly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
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
    public IbizproProductWeekly sysGet(Long key) {
        IbizproProductWeekly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbizproProductWeekly getDraft(IbizproProductWeekly et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(IbizproProductWeekly et) {
        return (!ObjectUtils.isEmpty(et.getIbizproProductweeklyid())) && (!Objects.isNull(this.getById(et.getIbizproProductweeklyid())));
    }
    @Override
    @Transactional
    public boolean save(IbizproProductWeekly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproProductWeekly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproProductWeekly> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProductWeekly> create = new ArrayList<>();
        List<IbizproProductWeekly> update = new ArrayList<>();
        for (IbizproProductWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproProductweeklyid()) || ObjectUtils.isEmpty(getById(et.getIbizproProductweeklyid()))) {
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
    public void saveBatch(List<IbizproProductWeekly> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProductWeekly> create = new ArrayList<>();
        List<IbizproProductWeekly> update = new ArrayList<>();
        for (IbizproProductWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproProductweeklyid()) || ObjectUtils.isEmpty(getById(et.getIbizproProductweeklyid()))) {
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
    public IbizproProductWeekly sumProductWeekly(IbizproProductWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sumProductWeeklyBatch(List<IbizproProductWeekly> etList) {
        for(IbizproProductWeekly et : etList) {
            sumProductWeekly(et);
        }
        return true;
    }


	@Override
    public List<IbizproProductWeekly> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<IbizproProductWeekly>().eq("product",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproProductWeekly> searchDefault(IbizproProductWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProductWeekly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProductWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbizproProductWeekly et){
        //实体关系[DER1N_IBIZPRO_PRODUCTWEEKLY_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product productweekly=et.getProductweekly();
            if(ObjectUtils.isEmpty(productweekly)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setProductweekly(majorEntity);
                productweekly=majorEntity;
            }
            et.setProductname(productweekly.getName());
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
    public List<IbizproProductWeekly> getIbizproproductweeklyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproProductWeekly> getIbizproproductweeklyByEntities(List<IbizproProductWeekly> entities) {
        List ids =new ArrayList();
        for(IbizproProductWeekly entity : entities){
            Serializable id=entity.getIbizproProductweeklyid();
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


    public IIbizproProductWeeklyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproProductWeekly dynamicCall(Long key, String action, IbizproProductWeekly et) {
        return et;
    }
}



