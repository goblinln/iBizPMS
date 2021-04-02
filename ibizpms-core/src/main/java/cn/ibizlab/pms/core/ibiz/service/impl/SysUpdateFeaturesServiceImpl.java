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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateFeaturesService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.SysUpdateFeaturesMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[系统更新功能] 服务对象接口实现
 */
@Slf4j
@Service("SysUpdateFeaturesServiceImpl")
public class SysUpdateFeaturesServiceImpl extends ServiceImpl<SysUpdateFeaturesMapper, SysUpdateFeatures> implements ISysUpdateFeaturesService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService sysupdatelogService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(SysUpdateFeatures et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getSysupdatefeaturesid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<SysUpdateFeatures> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(SysUpdateFeatures et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("sys_update_featuresid", et.getSysupdatefeaturesid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getSysupdatefeaturesid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<SysUpdateFeatures> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
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
    public SysUpdateFeatures get(String key) {
        SysUpdateFeatures et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
        }
        return et;
    }

    @Override
    public SysUpdateFeatures getDraft(SysUpdateFeatures et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(SysUpdateFeatures et) {
        return (!ObjectUtils.isEmpty(et.getSysupdatefeaturesid())) && (!Objects.isNull(this.getById(et.getSysupdatefeaturesid())));
    }
    @Override
    @Transactional
    public boolean save(SysUpdateFeatures et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(SysUpdateFeatures et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<SysUpdateFeatures> list) {
        list.forEach(item->fillParentData(item));
        List<SysUpdateFeatures> create = new ArrayList<>();
        List<SysUpdateFeatures> update = new ArrayList<>();
        for (SysUpdateFeatures et : list) {
            if (ObjectUtils.isEmpty(et.getSysupdatefeaturesid()) || ObjectUtils.isEmpty(getById(et.getSysupdatefeaturesid()))) {
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
    public void saveBatch(List<SysUpdateFeatures> list) {
        list.forEach(item->fillParentData(item));
        List<SysUpdateFeatures> create = new ArrayList<>();
        List<SysUpdateFeatures> update = new ArrayList<>();
        for (SysUpdateFeatures et : list) {
            if (ObjectUtils.isEmpty(et.getSysupdatefeaturesid()) || ObjectUtils.isEmpty(getById(et.getSysupdatefeaturesid()))) {
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
    public List<SysUpdateFeatures> selectBySysupdatelogid(String sysupdatelogid) {
        return baseMapper.selectBySysupdatelogid(sysupdatelogid);
    }
    @Override
    public void removeBySysupdatelogid(Collection<String> ids) {
        this.remove(new QueryWrapper<SysUpdateFeatures>().in("sys_update_logid",ids));
    }

    @Override
    public void removeBySysupdatelogid(String sysupdatelogid) {
        this.remove(new QueryWrapper<SysUpdateFeatures>().eq("sys_update_logid",sysupdatelogid));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysUpdateFeatures> searchDefault(SysUpdateFeaturesSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SysUpdateFeatures> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<SysUpdateFeatures>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(SysUpdateFeatures et){
        //实体关系[DER1N_SYS_UPDATE_FEATURES_SYS_UPDATE_LOG_SYS_UPDATE_LOGID]
        if(!ObjectUtils.isEmpty(et.getSysupdatelogid())){
            cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog sysupdatelog=et.getSysupdatelog();
            if(ObjectUtils.isEmpty(sysupdatelog)){
                cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog majorEntity=sysupdatelogService.get(et.getSysupdatelogid());
                et.setSysupdatelog(majorEntity);
                sysupdatelog=majorEntity;
            }
            et.setSysupdatelogname(sysupdatelog.getSysupdatelogname());
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
    public List<SysUpdateFeatures> getSysupdatefeaturesByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<SysUpdateFeatures> getSysupdatefeaturesByEntities(List<SysUpdateFeatures> entities) {
        List ids =new ArrayList();
        for(SysUpdateFeatures entity : entities){
            Serializable id=entity.getSysupdatefeaturesid();
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


    public ISysUpdateFeaturesService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public SysUpdateFeatures dynamicCall(String key, String action, SysUpdateFeatures et) {
        return et;
    }
}



