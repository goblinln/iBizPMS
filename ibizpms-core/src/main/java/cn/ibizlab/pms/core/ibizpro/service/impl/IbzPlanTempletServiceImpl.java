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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbzPlanTempletMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[计划模板] 服务对象接口实现
 */
@Slf4j
@Service("IbzPlanTempletServiceImpl")
public class IbzPlanTempletServiceImpl extends ServiceImpl<IbzPlanTempletMapper, IbzPlanTemplet> implements IIbzPlanTempletService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletDetailService ibzplantempletdetailService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzPlanTemplet et) {
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        ibzplantempletdetailService.saveByPlantempletid(et.getIbzplantempletid(), et.getIbzplantempletdetail());
        CachedBeanCopier.copy(get(et.getIbzplantempletid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzPlanTemplet> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzPlanTemplet et) {
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_plantempletid", et.getIbzplantempletid()))) {
            return false;
        }
        ibzplantempletdetailService.saveByPlantempletid(et.getIbzplantempletid(), et.getIbzplantempletdetail());
        CachedBeanCopier.copy(get(et.getIbzplantempletid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzPlanTemplet> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        ibzplantempletdetailService.removeByPlantempletid(key);
        boolean result = removeById(key);
        return result;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbzPlanTemplet get(String key) {
        IbzPlanTemplet et = getById(key);
        if (et == null) {
            et = new IbzPlanTemplet();
            et.setIbzplantempletid(key);
        }
        else {
            et.setIbzplantempletdetail(ibzplantempletdetailService.selectByPlantempletid(key));
        }
        return et;
    }

    @Override
    public IbzPlanTemplet getDraft(IbzPlanTemplet et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzPlanTemplet et) {
        return (!ObjectUtils.isEmpty(et.getIbzplantempletid())) && (!Objects.isNull(this.getById(et.getIbzplantempletid())));
    }
    @Override
    @Transactional
    public boolean save(IbzPlanTemplet et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzPlanTemplet et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzPlanTemplet> list) {
        saveOrUpdateBatch(list, batchSize);
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<IbzPlanTemplet> list) {
        saveOrUpdateBatch(list, batchSize);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzPlanTemplet> searchDefault(IbzPlanTempletSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzPlanTemplet> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzPlanTemplet>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }







    @Override
    public List<JSONObject> select(String sql, Map param) {
        return this.baseMapper.selectBySQL(sql, param);
    }

    @Override
    @Transactional
    public boolean execute(String sql, Map param) {
        if (sql == null || sql.isEmpty()) {
            return false;
        }
        if (sql.toLowerCase().trim().startsWith("insert")) {
            return this.baseMapper.insertBySQL(sql, param);
        }
        if (sql.toLowerCase().trim().startsWith("update")) {
            return this.baseMapper.updateBySQL(sql, param);
        }
        if (sql.toLowerCase().trim().startsWith("delete")) {
            return this.baseMapper.deleteBySQL(sql, param);
        }
        log.warn("暂未支持的SQL语法");
        return true;
    }

    @Override
    public List<IbzPlanTemplet> getIbzplantempletByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzPlanTemplet> getIbzplantempletByEntities(List<IbzPlanTemplet> entities) {
        List ids =new ArrayList();
        for(IbzPlanTemplet entity : entities){
            Serializable id=entity.getIbzplantempletid();
            if (!ObjectUtils.isEmpty(id)) {
                ids.add(id);
            }
        }
        if (ids.size() > 0) {
            return this.listByIds(ids);
        }
        else {
            return entities;
        }
    }




}



