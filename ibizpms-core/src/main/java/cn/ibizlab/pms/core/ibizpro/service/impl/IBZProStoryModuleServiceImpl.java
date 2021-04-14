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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStoryModuleSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IBZProStoryModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[需求模块] 服务对象接口实现
 */
@Slf4j
@Service("IBZProStoryModuleServiceImpl")
public class IBZProStoryModuleServiceImpl extends ServiceImpl<IBZProStoryModuleMapper, IBZProStoryModule> implements IIBZProStoryModuleService {


    protected cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryModuleService ibzprostorymoduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryService ibzprostoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIBZProProductService ibzproproductService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IBZProStoryModule et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProStoryModule> list) {
        list.forEach(item->fillParentData(item));
        for (IBZProStoryModule et : list) {
            getProxyService().save(et);
        }
    }

    @Override
    @Transactional
    public boolean update(IBZProStoryModule et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProStoryModule> list) {
        list.forEach(item->fillParentData(item));
        for (IBZProStoryModule et : list) {
            getProxyService().update(et);
        }
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProStoryModule et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
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
    public IBZProStoryModule get(Long key) {
        IBZProStoryModule et = getById(key);
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
    public IBZProStoryModule sysGet(Long key) {
        IBZProStoryModule et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProStoryModule getDraft(IBZProStoryModule et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(IBZProStoryModule et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IBZProStoryModule et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProStoryModule et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProStoryModule> list) {
        list.forEach(item->fillParentData(item));
        List<IBZProStoryModule> create = new ArrayList<>();
        List<IBZProStoryModule> update = new ArrayList<>();
        for (IBZProStoryModule et : list) {
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
    public void saveBatch(List<IBZProStoryModule> list) {
        list.forEach(item->fillParentData(item));
        List<IBZProStoryModule> create = new ArrayList<>();
        List<IBZProStoryModule> update = new ArrayList<>();
        for (IBZProStoryModule et : list) {
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
    @Transactional
    public IBZProStoryModule syncFromIBIZ(IBZProStoryModule et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean syncFromIBIZBatch(List<IBZProStoryModule> etList) {
        for(IBZProStoryModule et : etList) {
            syncFromIBIZ(et);
        }
        return true;
    }


	@Override
    public List<IBZProStoryModule> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<IBZProStoryModule>().eq("root",id));
    }

	@Override
    public List<IBZProStoryModule> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<IBZProStoryModule>().eq("parent",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProStoryModule> searchDefault(IBZProStoryModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProStoryModule> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProStoryModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IBZProStoryModule et){
        //实体关系[DER1N_IBZPRO_STORYMODULE_IBZPRO_PRODUCT_ROOT]
        if(!ObjectUtils.isEmpty(et.getRoot())){
            cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct ibzproproduct=et.getIbzproproduct();
            if(ObjectUtils.isEmpty(ibzproproduct)){
                cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct majorEntity=ibzproproductService.get(et.getRoot());
                et.setIbzproproduct(majorEntity);
                ibzproproduct=majorEntity;
            }
            et.setProductname(ibzproproduct.getName());
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



    public IIBZProStoryModuleService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProStoryModule dynamicCall(Long key, String action, IBZProStoryModule et) {
        return et;
    }
}



