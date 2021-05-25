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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZTaskActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZTaskActionServiceImpl")
public class IBZTaskActionServiceImpl extends ServiceImpl<IBZTaskActionMapper, IBZTaskAction> implements IIBZTaskActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZTaskActionRuntime ibztaskactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZTaskHistoryService ibztaskhistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;

    protected int batchSize = 500;

    @Override
    public List<IBZTaskAction> select(IBZTaskActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTaskAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZTaskAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibztaskactionRuntime.isRtmodel()){
            ibztaskhistoryService.saveByAction(et.getId(), et.getIbztaskhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZTaskAction> list) {
        if(ibztaskactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (IBZTaskAction et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZTaskAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibztaskactionRuntime.isRtmodel()){
            ibztaskhistoryService.saveByAction(et.getId(), et.getIbztaskhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZTaskAction> list) {
        if(ibztaskactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZTaskAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibztaskactionRuntime.isRtmodel()){
            ibztaskhistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibztaskactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZTaskAction get(Long key) {
        IBZTaskAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibztaskactionRuntime.isRtmodel()){
                et.setIbztaskhistory(ibztaskhistoryService.selectByAction(key));
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
    public IBZTaskAction sysGet(Long key) {
        IBZTaskAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZTaskAction getDraft(IBZTaskAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZTaskAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZTaskAction comment(IBZTaskAction et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean commentBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            comment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTaskAction createHis(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createHisBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            createHis(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTaskAction editComment(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean editCommentBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            editComment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTaskAction managePmsEe(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean managePmsEeBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            managePmsEe(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IBZTaskAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZTaskAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZTaskAction> list) {
        List<IBZTaskAction> create = new ArrayList<>();
        List<IBZTaskAction> update = new ArrayList<>();
        for (IBZTaskAction et : list) {
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
    public void saveBatch(List<IBZTaskAction> list) {
        List<IBZTaskAction> create = new ArrayList<>();
        List<IBZTaskAction> update = new ArrayList<>();
        for (IBZTaskAction et : list) {
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
    public IBZTaskAction sendMarkDone(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendMarkDoneBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            sendMarkDone(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTaskAction sendTodo(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendTodoBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            sendTodo(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTaskAction sendToread(IBZTaskAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendToreadBatch(List<IBZTaskAction> etList) {
        for(IBZTaskAction et : etList) {
            sendToread(et);
        }
        return true;
    }


	@Override
    public List<IBZTaskAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IBZTaskAction>().eq("objectid",id));
    }


    public List<IBZTaskAction> selectDefault(IBZTaskActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZTaskAction> selectSimple(IBZTaskActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZTaskAction> selectType(IBZTaskActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZTaskAction> selectView(IBZTaskActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZTaskAction> searchDefault(IBZTaskActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTaskAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTaskAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZTaskAction> searchType(IBZTaskActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTaskAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTaskAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZTaskAction> getIbztaskactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZTaskAction> getIbztaskactionByEntities(List<IBZTaskAction> entities) {
        List ids =new ArrayList();
        for(IBZTaskAction entity : entities){
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
    public IBZTaskActionSearchContext getSearchContext(){
        return new IBZTaskActionSearchContext();
    }
    public IIBZTaskActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZTaskAction dynamicCall(Long key, String action, IBZTaskAction et) {
        return et;
    }
}



