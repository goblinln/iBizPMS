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
import cn.ibizlab.pms.core.ibiz.domain.IBZDailyAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZDailyActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZDailyActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZDailyActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[日报日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZDailyActionServiceImpl")
public class IBZDailyActionServiceImpl extends ServiceImpl<IBZDailyActionMapper, IBZDailyAction> implements IIBZDailyActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZDailyActionRuntime ibzdailyactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZDailyHistoryService ibzdailyhistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.report.service.IIbzDailyService ibzdailyService;

    protected int batchSize = 500;

    @Override
    public List<IBZDailyAction> select(IBZDailyActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZDailyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZDailyAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzdailyactionRuntime.isRtmodel()){
            ibzdailyhistoryService.saveByAction(et.getId(), et.getIbzdailyhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZDailyAction> list) {
        if(ibzdailyactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (IBZDailyAction et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZDailyAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzdailyactionRuntime.isRtmodel()){
            ibzdailyhistoryService.saveByAction(et.getId(), et.getIbzdailyhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZDailyAction> list) {
        if(ibzdailyactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZDailyAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzdailyactionRuntime.isRtmodel()){
            ibzdailyhistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzdailyactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZDailyAction get(Long key) {
        IBZDailyAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzdailyactionRuntime.isRtmodel()){
                et.setIbzdailyhistory(ibzdailyhistoryService.selectByAction(key));
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
    public IBZDailyAction sysGet(Long key) {
        IBZDailyAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZDailyAction getDraft(IBZDailyAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZDailyAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZDailyAction comment(IBZDailyAction et) {
         return et ;
    }

    @Override
    @Transactional
    public IBZDailyAction createHis(IBZDailyAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZDailyAction editComment(IBZDailyAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZDailyAction managePmsEe(IBZDailyAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(IBZDailyAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZDailyAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZDailyAction> list) {
        List<IBZDailyAction> create = new ArrayList<>();
        List<IBZDailyAction> update = new ArrayList<>();
        for (IBZDailyAction et : list) {
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
    public void saveBatch(List<IBZDailyAction> list) {
        List<IBZDailyAction> create = new ArrayList<>();
        List<IBZDailyAction> update = new ArrayList<>();
        for (IBZDailyAction et : list) {
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
    public IBZDailyAction sendMarkDone(IBZDailyAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZDailyAction sendTodo(IBZDailyAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZDailyAction sendToread(IBZDailyAction et) {
        //自定义代码
        return et;
    }


	@Override
    public List<IBZDailyAction> selectByObjectid(Long ibzdailyid) {
        return baseMapper.selectByObjectid(ibzdailyid);
    }
    @Override
    public void removeByObjectid(Long ibzdailyid) {
        this.remove(new QueryWrapper<IBZDailyAction>().eq("objectid",ibzdailyid));
    }


    public List<IBZDailyAction> selectDefault(IBZDailyActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZDailyAction> selectSimple(IBZDailyActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZDailyAction> selectType(IBZDailyActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZDailyAction> selectView(IBZDailyActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZDailyAction> searchDefault(IBZDailyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZDailyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZDailyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZDailyAction> searchType(IBZDailyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZDailyAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZDailyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZDailyAction> getIbzdailyactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZDailyAction> getIbzdailyactionByEntities(List<IBZDailyAction> entities) {
        List ids =new ArrayList();
        for(IBZDailyAction entity : entities){
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
    public IBZDailyActionSearchContext getSearchContext(){
        return new IBZDailyActionSearchContext();
    }
    public IIBZDailyActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZDailyAction dynamicCall(Long key, String action, IBZDailyAction et) {
        return et;
    }
}



