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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectWeeklySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectWeeklyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproProjectWeeklyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目周报] 服务对象接口实现
 */
@Slf4j
@Service("IbizproProjectWeeklyServiceImpl")
public class IbizproProjectWeeklyServiceImpl extends ServiceImpl<IbizproProjectWeeklyMapper, IbizproProjectWeekly> implements IIbizproProjectWeeklyService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectWeeklyRuntime ibizproprojectweeklyRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    public List<IbizproProjectWeekly> select(IbizproProjectWeeklySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProjectWeekly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbizproProjectWeekly et) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProjectweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproProjectWeekly> list) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbizproProjectWeekly et) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_projectweeklyid", et.getProjectweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProjectweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproProjectWeekly> list) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbizproProjectWeekly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_projectweeklyid", et.getProjectweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProjectweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
        }
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
    public IbizproProjectWeekly get(String key) {
        IbizproProjectWeekly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!ibizproprojectweeklyRuntime.isRtmodel()){
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
    public IbizproProjectWeekly sysGet(String key) {
        IbizproProjectWeekly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IbizproProjectWeekly getDraft(IbizproProjectWeekly et) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(IbizproProjectWeekly et) {
        return (!ObjectUtils.isEmpty(et.getProjectweeklyid())) && (!Objects.isNull(this.getById(et.getProjectweeklyid())));
    }
    @Override
    @Transactional
    public IbizproProjectWeekly pushSumProjectWeekly(IbizproProjectWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean pushSumProjectWeeklyBatch(List<IbizproProjectWeekly> etList) {
        for(IbizproProjectWeekly et : etList) {
            pushSumProjectWeekly(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IbizproProjectWeekly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproProjectWeekly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproProjectWeekly> list) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbizproProjectWeekly> create = new ArrayList<>();
        List<IbizproProjectWeekly> update = new ArrayList<>();
        for (IbizproProjectWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getProjectweeklyid()) || ObjectUtils.isEmpty(getById(et.getProjectweeklyid()))) {
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
    public void saveBatch(List<IbizproProjectWeekly> list) {
        if(!ibizproprojectweeklyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<IbizproProjectWeekly> create = new ArrayList<>();
        List<IbizproProjectWeekly> update = new ArrayList<>();
        for (IbizproProjectWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getProjectweeklyid()) || ObjectUtils.isEmpty(getById(et.getProjectweeklyid()))) {
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
    public List<IbizproProjectWeekly> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<IbizproProjectWeekly>().eq("project",id));
    }


    public List<IbizproProjectWeekly> selectDefault(IbizproProjectWeeklySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbizproProjectWeekly> selectView(IbizproProjectWeeklySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproProjectWeekly> searchDefault(IbizproProjectWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProjectWeekly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProjectWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbizproProjectWeekly et){
        //实体关系[DER1N_IBZPRO_PROJECTWEEKLY_ZT_PROJECT_PROJECT]
        if(!ObjectUtils.isEmpty(et.getProject())){
            cn.ibizlab.pms.core.zentao.domain.Project ztproject=et.getZtproject();
            if(ObjectUtils.isEmpty(ztproject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getProject());
                et.setZtproject(majorEntity);
                ztproject=majorEntity;
            }
            et.setProjectname(ztproject.getName());
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
    public List<IbizproProjectWeekly> getIbizproprojectweeklyByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproProjectWeekly> getIbizproprojectweeklyByEntities(List<IbizproProjectWeekly> entities) {
        List ids =new ArrayList();
        for(IbizproProjectWeekly entity : entities){
            Serializable id=entity.getProjectweeklyid();
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


    public IIbizproProjectWeeklyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproProjectWeekly dynamicCall(String key, String action, IbizproProjectWeekly et) {
        return et;
    }
}



