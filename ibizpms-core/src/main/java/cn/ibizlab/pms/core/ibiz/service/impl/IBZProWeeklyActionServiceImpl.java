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
import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProWeeklyActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZProWeeklyActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZProWeeklyActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[周报日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZProWeeklyActionServiceImpl")
public class IBZProWeeklyActionServiceImpl extends ServiceImpl<IBZProWeeklyActionMapper, IBZProWeeklyAction> implements IIBZProWeeklyActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZProWeeklyActionRuntime ibzproweeklyactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzProWeeklyHistoryService ibzproweeklyhistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.report.service.IIbzWeeklyService ibzweeklyService;

    protected int batchSize = 500;

    @Override
    public List<IBZProWeeklyAction> select(IBZProWeeklyActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProWeeklyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProWeeklyAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzproweeklyactionRuntime.isRtmodel()){
            ibzproweeklyhistoryService.saveByAction(et.getId(), et.getIbzproweeklyhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProWeeklyAction> list) {
        if(ibzproweeklyactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (IBZProWeeklyAction et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZProWeeklyAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzproweeklyactionRuntime.isRtmodel()){
            ibzproweeklyhistoryService.saveByAction(et.getId(), et.getIbzproweeklyhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProWeeklyAction> list) {
        if(ibzproweeklyactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProWeeklyAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzproweeklyactionRuntime.isRtmodel()){
            ibzproweeklyhistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzproweeklyactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZProWeeklyAction get(Long key) {
        IBZProWeeklyAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzproweeklyactionRuntime.isRtmodel()){
                et.setIbzproweeklyhistory(ibzproweeklyhistoryService.selectByAction(key));
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
    public IBZProWeeklyAction sysGet(Long key) {
        IBZProWeeklyAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProWeeklyAction getDraft(IBZProWeeklyAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProWeeklyAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZProWeeklyAction createHis(IBZProWeeklyAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createHisBatch(List<IBZProWeeklyAction> etList) {
        for(IBZProWeeklyAction et : etList) {
            createHis(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZProWeeklyAction managePmsEe(IBZProWeeklyAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean managePmsEeBatch(List<IBZProWeeklyAction> etList) {
        for(IBZProWeeklyAction et : etList) {
            managePmsEe(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IBZProWeeklyAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProWeeklyAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProWeeklyAction> list) {
        List<IBZProWeeklyAction> create = new ArrayList<>();
        List<IBZProWeeklyAction> update = new ArrayList<>();
        for (IBZProWeeklyAction et : list) {
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
    public void saveBatch(List<IBZProWeeklyAction> list) {
        List<IBZProWeeklyAction> create = new ArrayList<>();
        List<IBZProWeeklyAction> update = new ArrayList<>();
        for (IBZProWeeklyAction et : list) {
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
    public IBZProWeeklyAction sendMarkDone(IBZProWeeklyAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendMarkDoneBatch(List<IBZProWeeklyAction> etList) {
        for(IBZProWeeklyAction et : etList) {
            sendMarkDone(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZProWeeklyAction sendTodo(IBZProWeeklyAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendTodoBatch(List<IBZProWeeklyAction> etList) {
        for(IBZProWeeklyAction et : etList) {
            sendTodo(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZProWeeklyAction sendToread(IBZProWeeklyAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendToreadBatch(List<IBZProWeeklyAction> etList) {
        for(IBZProWeeklyAction et : etList) {
            sendToread(et);
        }
        return true;
    }


	@Override
    public List<IBZProWeeklyAction> selectByObjectid(Long ibzweeklyid) {
        return baseMapper.selectByObjectid(ibzweeklyid);
    }
    @Override
    public void removeByObjectid(Long ibzweeklyid) {
        this.remove(new QueryWrapper<IBZProWeeklyAction>().eq("objectid",ibzweeklyid));
    }


    public List<IBZProWeeklyAction> selectDefault(IBZProWeeklyActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProWeeklyAction> selectSimple(IBZProWeeklyActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZProWeeklyAction> selectType(IBZProWeeklyActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZProWeeklyAction> selectView(IBZProWeeklyActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProWeeklyAction> searchDefault(IBZProWeeklyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProWeeklyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProWeeklyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZProWeeklyAction> searchType(IBZProWeeklyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProWeeklyAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProWeeklyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProWeeklyAction> getIbzproweeklyactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProWeeklyAction> getIbzproweeklyactionByEntities(List<IBZProWeeklyAction> entities) {
        List ids =new ArrayList();
        for(IBZProWeeklyAction entity : entities){
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


    /**
     * 获取searchContext
     * @return
     */
    public IBZProWeeklyActionSearchContext getSearchContext(){
        return new IBZProWeeklyActionSearchContext();
    }
    public IIBZProWeeklyActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProWeeklyAction dynamicCall(Long key, String action, IBZProWeeklyAction et) {
        return et;
    }
}



