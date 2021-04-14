package cn.ibizlab.pms.core.zentao.service.impl;

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
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.CaseStepMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用例步骤] 服务对象接口实现
 */
@Slf4j
@Service("CaseStepServiceImpl")
public class CaseStepServiceImpl extends ServiceImpl<CaseStepMapper, CaseStep> implements ICaseStepService {


    protected cn.ibizlab.pms.core.zentao.service.ICaseStepService casestepService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(CaseStep et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        casestepService.saveByParent(et.getId(), et.getCasestep());
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<CaseStep> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(CaseStep et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        casestepService.saveByParent(et.getId(), et.getCasestep());
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<CaseStep> list) {
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
    public CaseStep get(Long key) {
        CaseStep et = getById(key);
        if(et == null){
            et = new CaseStep();
            et.setId(key);
        }
        else {
            et.setCasestep(casestepService.selectByParent(key));
        }
        return et;
    }

    @Override
    public CaseStep getDraft(CaseStep et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(CaseStep et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(CaseStep et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(CaseStep et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<CaseStep> list) {
        list.forEach(item->fillParentData(item));
        List<CaseStep> create = new ArrayList<>();
        List<CaseStep> update = new ArrayList<>();
        for (CaseStep et : list) {
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
    public void saveBatch(List<CaseStep> list) {
        list.forEach(item->fillParentData(item));
        List<CaseStep> create = new ArrayList<>();
        List<CaseStep> update = new ArrayList<>();
        for (CaseStep et : list) {
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
    public List<CaseStep> selectByIbizcase(Long id) {
        return baseMapper.selectByIbizcase(id);
    }
    @Override
    public void removeByIbizcase(Long id) {
        this.remove(new QueryWrapper<CaseStep>().eq("case",id));
    }

    public ICaseStepService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByIbizcase(Long id,List<CaseStep> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<CaseStep> _update=new ArrayList<CaseStep>();
        List<CaseStep> _create=new ArrayList<CaseStep>();
        for(CaseStep before:selectByIbizcase(id)){
            delIds.add(before.getId());
        }
        for(CaseStep sub:list) {
            sub.setIbizcase(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}

	@Override
    public List<CaseStep> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<CaseStep>().eq("parent",id));
    }

	@Override
    public void saveByParent(Long id,List<CaseStep> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<CaseStep> _update=new ArrayList<CaseStep>();
        List<CaseStep> _create=new ArrayList<CaseStep>();
        for(CaseStep before:selectByParent(id)){
            delIds.add(before.getId());
        }
        for(CaseStep sub:list) {
            sub.setParent(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}


    /**
     * 查询集合 当前测试步骤
     */
    @Override
    public Page<CaseStep> searchCurTest(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchCurTest(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<CaseStep> searchDefault(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT1
     */
    @Override
    public Page<CaseStep> searchDefault1(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchDefault1(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Mob
     */
    @Override
    public Page<CaseStep> searchMob(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchMob(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 版本
     */
    @Override
    public Page<CaseStep> searchVersion(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchVersion(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 版本1
     */
    @Override
    public Page<CaseStep> searchVersions(CaseStepSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CaseStep> pages=baseMapper.searchVersions(context.getPages(),context,context.getSelectCond());
        return new PageImpl<CaseStep>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(CaseStep et){
        //实体关系[DER1N_ZT_CASESTEP_ZT_CASE_CASE]
        if(!ObjectUtils.isEmpty(et.getIbizcase())){
            cn.ibizlab.pms.core.zentao.domain.Case ztcase=et.getZtcase();
            if(ObjectUtils.isEmpty(ztcase)){
                cn.ibizlab.pms.core.zentao.domain.Case majorEntity=caseService.get(et.getIbizcase());
                et.setZtcase(majorEntity);
                ztcase=majorEntity;
            }
            et.setVersion(ztcase.getVersion());
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
    public List<CaseStep> getCasestepByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<CaseStep> getCasestepByEntities(List<CaseStep> entities) {
        List ids =new ArrayList();
        for(CaseStep entity : entities){
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


    @Override
    @Transactional
    public CaseStep dynamicCall(Long key, String action, CaseStep et) {
        return et;
    }
}


