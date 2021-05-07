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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibCaseStepsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzLibCaseStepsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用例库用例步骤] 服务对象接口实现
 */
@Slf4j
@Service("IbzLibCaseStepsServiceImpl")
public class IbzLibCaseStepsServiceImpl extends ServiceImpl<IbzLibCaseStepsMapper, IbzLibCaseSteps> implements IIbzLibCaseStepsService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzLibCaseStepsRuntime ibzlibcasestepsRuntime;


    protected cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepsService ibzlibcasestepsService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzCaseService ibzcaseService;

    protected int batchSize = 500;

    @Override
    public List<IbzLibCaseSteps> select(IbzLibCaseStepsSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzLibCaseSteps> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzLibCaseSteps et) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            ibzlibcasestepsService.saveByParent(et.getId(), et.getIbzlibcasesteps());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzLibCaseSteps> list) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzLibCaseSteps et) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            ibzlibcasestepsService.saveByParent(et.getId(), et.getIbzlibcasesteps());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzLibCaseSteps> list) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzLibCaseSteps et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
        }
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
    public IbzLibCaseSteps get(Long key) {
        IbzLibCaseSteps et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
                et.setIbzlibcasesteps(ibzlibcasestepsService.selectByParent(key));
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
    public IbzLibCaseSteps sysGet(Long key) {
        IbzLibCaseSteps et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzLibCaseSteps getDraft(IbzLibCaseSteps et) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(IbzLibCaseSteps et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzLibCaseSteps et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzLibCaseSteps et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzLibCaseSteps> list) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbzLibCaseSteps> create = new ArrayList<>();
        List<IbzLibCaseSteps> update = new ArrayList<>();
        for (IbzLibCaseSteps et : list) {
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
    public void saveBatch(List<IbzLibCaseSteps> list) {
        if(!ibzlibcasestepsRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbzLibCaseSteps> create = new ArrayList<>();
        List<IbzLibCaseSteps> update = new ArrayList<>();
        for (IbzLibCaseSteps et : list) {
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
    public List<IbzLibCaseSteps> selectByIbizcase(Long id) {
        return baseMapper.selectByIbizcase(id);
    }
    @Override
    public void removeByIbizcase(Long id) {
        this.remove(new QueryWrapper<IbzLibCaseSteps>().eq("case",id));
    }

    public IIbzLibCaseStepsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByIbizcase(Long id,List<IbzLibCaseSteps> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IbzLibCaseSteps> _update=new ArrayList<IbzLibCaseSteps>();
        List<IbzLibCaseSteps> _create=new ArrayList<IbzLibCaseSteps>();
        for(IbzLibCaseSteps before:selectByIbizcase(id)){
            delIds.add(before.getId());
        }
        for(IbzLibCaseSteps sub:list) {
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
    public List<IbzLibCaseSteps> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<IbzLibCaseSteps>().eq("parent",id));
    }

	@Override
    public void saveByParent(Long id,List<IbzLibCaseSteps> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<IbzLibCaseSteps> _update=new ArrayList<IbzLibCaseSteps>();
        List<IbzLibCaseSteps> _create=new ArrayList<IbzLibCaseSteps>();
        for(IbzLibCaseSteps before:selectByParent(id)){
            delIds.add(before.getId());
        }
        for(IbzLibCaseSteps sub:list) {
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


    public List<IbzLibCaseSteps> selectDefault(IbzLibCaseStepsSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzLibCaseSteps> selectView(IbzLibCaseStepsSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<IbzLibCaseSteps> searchDefault(IbzLibCaseStepsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzLibCaseSteps> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzLibCaseSteps>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbzLibCaseSteps et){
        //实体关系[DER1N_IBZ_LIBCASESTEPS_IBZ_CASE_CASE]
        if(!ObjectUtils.isEmpty(et.getIbizcase())){
            cn.ibizlab.pms.core.ibiz.domain.IbzCase ibzcase=et.getIbzcase();
            if(ObjectUtils.isEmpty(ibzcase)){
                cn.ibizlab.pms.core.ibiz.domain.IbzCase majorEntity=ibzcaseService.get(et.getIbizcase());
                et.setIbzcase(majorEntity);
                ibzcase=majorEntity;
            }
            et.setVersion(ibzcase.getVersion());
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
    @Transactional
    public IbzLibCaseSteps dynamicCall(Long key, String action, IbzLibCaseSteps et) {
        return et;
    }
}



