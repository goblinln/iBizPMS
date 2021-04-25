package cn.ibizlab.pms.core.report.service.impl;

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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzWeeklyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[周报] 服务对象接口实现
 */
@Slf4j
@Service("IbzWeeklyServiceImpl")
public class IbzWeeklyServiceImpl extends ServiceImpl<IbzWeeklyMapper, IbzWeekly> implements IIbzWeeklyService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.report.runtime.IbzWeeklyRuntime ibzweeklyRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbzWeekly> select(IbzWeeklySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzWeekly et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzWeekly> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzWeekly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_weeklyid", et.getIbzweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzWeekly> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzWeekly et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_weeklyid", et.getIbzweeklyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzweeklyid()), et);
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
    public IbzWeekly get(Long key) {
        IbzWeekly et = getById(key);
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
    public IbzWeekly sysGet(Long key) {
        IbzWeekly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzWeekly getDraft(IbzWeekly et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzWeekly et) {
        return (!ObjectUtils.isEmpty(et.getIbzweeklyid())) && (!Objects.isNull(this.getById(et.getIbzweeklyid())));
    }
    @Override
    @Transactional
    public IbzWeekly createEveryWeekReport(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createEveryWeekReportBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            createEveryWeekReport(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzWeekly createGetLastWeekPlanAndWork(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createGetLastWeekPlanAndWorkBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            createGetLastWeekPlanAndWork(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzWeekly editGetLastWeekTaskAndComTask(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean editGetLastWeekTaskAndComTaskBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            editGetLastWeekTaskAndComTask(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzWeekly haveRead(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean haveReadBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            haveRead(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzWeekly jugThisWeekCreateWeekly(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean jugThisWeekCreateWeeklyBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            jugThisWeekCreateWeekly(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzWeekly pushUserWeekly(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean pushUserWeeklyBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            pushUserWeekly(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IbzWeekly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzWeekly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzWeekly> list) {
        List<IbzWeekly> create = new ArrayList<>();
        List<IbzWeekly> update = new ArrayList<>();
        for (IbzWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzweeklyid()) || ObjectUtils.isEmpty(getById(et.getIbzweeklyid()))) {
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
    public void saveBatch(List<IbzWeekly> list) {
        List<IbzWeekly> create = new ArrayList<>();
        List<IbzWeekly> update = new ArrayList<>();
        for (IbzWeekly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzweeklyid()) || ObjectUtils.isEmpty(getById(et.getIbzweeklyid()))) {
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
    public IbzWeekly submit(IbzWeekly et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean submitBatch(List<IbzWeekly> etList) {
        for(IbzWeekly et : etList) {
            submit(et);
        }
        return true;
    }



    public List<IbzWeekly> selectDefault(IbzWeeklySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzWeekly> selectMyNotSubmit(IbzWeeklySearchContext context){
        return baseMapper.selectMyNotSubmit(context, context.getSelectCond());
    }
    public List<IbzWeekly> selectMyWeekly(IbzWeeklySearchContext context){
        return baseMapper.selectMyWeekly(context, context.getSelectCond());
    }
    public List<IbzWeekly> selectProductTeamMemberWeekly(IbzWeeklySearchContext context){
        return baseMapper.selectProductTeamMemberWeekly(context, context.getSelectCond());
    }
    public List<IbzWeekly> selectProjectWeekly(IbzWeeklySearchContext context){
        return baseMapper.selectProjectWeekly(context, context.getSelectCond());
    }
    public List<IbzWeekly> selectView(IbzWeeklySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzWeekly> searchDefault(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的周报
     */
    @Override
    public Page<IbzWeekly> searchMyNotSubmit(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchMyNotSubmit(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的周报
     */
    @Override
    public Page<IbzWeekly> searchMyWeekly(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchMyWeekly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品团队成员周报
     */
    @Override
    public Page<IbzWeekly> searchProductTeamMemberWeekly(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchProductTeamMemberWeekly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目周报
     */
    @Override
    public Page<IbzWeekly> searchProjectWeekly(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchProjectWeekly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzWeekly> getIbzweeklyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzWeekly> getIbzweeklyByEntities(List<IbzWeekly> entities) {
        List ids =new ArrayList();
        for(IbzWeekly entity : entities){
            Serializable id=entity.getIbzweeklyid();
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


    public IIbzWeeklyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzWeekly dynamicCall(Long key, String action, IbzWeekly et) {
        return et;
    }
}



