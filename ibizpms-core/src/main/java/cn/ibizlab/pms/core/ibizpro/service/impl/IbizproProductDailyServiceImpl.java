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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductDailySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductDailyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproProductDailyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品日报] 服务对象接口实现
 */
@Slf4j
@Service("IbizproProductDailyServiceImpl")
public class IbizproProductDailyServiceImpl extends ServiceImpl<IbizproProductDailyMapper, IbizproProductDaily> implements IIbizproProductDailyService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbizproProductDaily et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproproductdailyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproProductDaily> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbizproProductDaily et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_productdailyid", et.getIbizproproductdailyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproproductdailyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproProductDaily> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
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
    public IbizproProductDaily get(Long key) {
        IbizproProductDaily et = getById(key);
        if(et == null){
            et = new IbizproProductDaily();
            et.setIbizproproductdailyid(key);
        }
        else {
        }
        return et;
    }

    @Override
    public IbizproProductDaily getDraft(IbizproProductDaily et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(IbizproProductDaily et) {
        return (!ObjectUtils.isEmpty(et.getIbizproproductdailyid())) && (!Objects.isNull(this.getById(et.getIbizproproductdailyid())));
    }
    @Override
    @Transactional
    public IbizproProductDaily manualCreateDaily(IbizproProductDaily et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean manualCreateDailyBatch(List<IbizproProductDaily> etList) {
        for(IbizproProductDaily et : etList) {
            manualCreateDaily(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IbizproProductDaily et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproProductDaily et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproProductDaily> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProductDaily> create = new ArrayList<>();
        List<IbizproProductDaily> update = new ArrayList<>();
        for (IbizproProductDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproproductdailyid()) || ObjectUtils.isEmpty(getById(et.getIbizproproductdailyid()))) {
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
    public void saveBatch(List<IbizproProductDaily> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProductDaily> create = new ArrayList<>();
        List<IbizproProductDaily> update = new ArrayList<>();
        for (IbizproProductDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproproductdailyid()) || ObjectUtils.isEmpty(getById(et.getIbizproproductdailyid()))) {
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
    public IbizproProductDaily statsProductDaily(IbizproProductDaily et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean statsProductDailyBatch(List<IbizproProductDaily> etList) {
        for(IbizproProductDaily et : etList) {
            statsProductDaily(et);
        }
        return true;
    }


	@Override
    public List<IbizproProductDaily> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<IbizproProductDaily>().eq("product",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproProductDaily> searchDefault(IbizproProductDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProductDaily> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProductDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品日报
     */
    @Override
    public Page<IbizproProductDaily> searchProductDaily(IbizproProductDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProductDaily> pages=baseMapper.searchProductDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProductDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbizproProductDaily et){
        //实体关系[DER1N_IBIZPRO_PRODUCTDAILY_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
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
    public List<IbizproProductDaily> getIbizproproductdailyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproProductDaily> getIbizproproductdailyByEntities(List<IbizproProductDaily> entities) {
        List ids =new ArrayList();
        for(IbizproProductDaily entity : entities){
            Serializable id=entity.getIbizproproductdailyid();
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


    public IIbizproProductDailyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproProductDaily dynamicCall(Long key, String action, IbizproProductDaily et) {
        return et;
    }
}


