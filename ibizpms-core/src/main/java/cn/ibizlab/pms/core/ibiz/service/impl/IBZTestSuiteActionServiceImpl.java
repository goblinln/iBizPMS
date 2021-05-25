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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuiteAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuiteActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestSuiteActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZTestSuiteActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[套件日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZTestSuiteActionServiceImpl")
public class IBZTestSuiteActionServiceImpl extends ServiceImpl<IBZTestSuiteActionMapper, IBZTestSuiteAction> implements IIBZTestSuiteActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZTestSuiteActionRuntime ibztestsuiteactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZTestSuitHistoryService ibztestsuithistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestSuiteService testsuiteService;

    protected int batchSize = 500;

    @Override
    public List<IBZTestSuiteAction> select(IBZTestSuiteActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestSuiteAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZTestSuiteAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibztestsuiteactionRuntime.isRtmodel()){
            ibztestsuithistoryService.saveByAction(et.getId(), et.getIbztestsuithistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZTestSuiteAction> list) {
        if(ibztestsuiteactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (IBZTestSuiteAction et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZTestSuiteAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibztestsuiteactionRuntime.isRtmodel()){
            ibztestsuithistoryService.saveByAction(et.getId(), et.getIbztestsuithistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZTestSuiteAction> list) {
        if(ibztestsuiteactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZTestSuiteAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibztestsuiteactionRuntime.isRtmodel()){
            ibztestsuithistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibztestsuiteactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZTestSuiteAction get(Long key) {
        IBZTestSuiteAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibztestsuiteactionRuntime.isRtmodel()){
                et.setIbztestsuithistory(ibztestsuithistoryService.selectByAction(key));
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
    public IBZTestSuiteAction sysGet(Long key) {
        IBZTestSuiteAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZTestSuiteAction getDraft(IBZTestSuiteAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZTestSuiteAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZTestSuiteAction comment(IBZTestSuiteAction et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean commentBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            comment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTestSuiteAction createHis(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createHisBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            createHis(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTestSuiteAction editComment(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean editCommentBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            editComment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTestSuiteAction managePmsEe(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean managePmsEeBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            managePmsEe(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IBZTestSuiteAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZTestSuiteAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZTestSuiteAction> list) {
        List<IBZTestSuiteAction> create = new ArrayList<>();
        List<IBZTestSuiteAction> update = new ArrayList<>();
        for (IBZTestSuiteAction et : list) {
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
    public void saveBatch(List<IBZTestSuiteAction> list) {
        List<IBZTestSuiteAction> create = new ArrayList<>();
        List<IBZTestSuiteAction> update = new ArrayList<>();
        for (IBZTestSuiteAction et : list) {
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
    public IBZTestSuiteAction sendMarkDone(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendMarkDoneBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            sendMarkDone(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTestSuiteAction sendTodo(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendTodoBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            sendTodo(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBZTestSuiteAction sendToread(IBZTestSuiteAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendToreadBatch(List<IBZTestSuiteAction> etList) {
        for(IBZTestSuiteAction et : etList) {
            sendToread(et);
        }
        return true;
    }


	@Override
    public List<IBZTestSuiteAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IBZTestSuiteAction>().eq("objectid",id));
    }


    public List<IBZTestSuiteAction> selectDefault(IBZTestSuiteActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZTestSuiteAction> selectSimple(IBZTestSuiteActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZTestSuiteAction> selectType(IBZTestSuiteActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZTestSuiteAction> selectView(IBZTestSuiteActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZTestSuiteAction> searchDefault(IBZTestSuiteActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestSuiteAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTestSuiteAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZTestSuiteAction> searchType(IBZTestSuiteActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestSuiteAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTestSuiteAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZTestSuiteAction> getIbztestsuiteactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZTestSuiteAction> getIbztestsuiteactionByEntities(List<IBZTestSuiteAction> entities) {
        List ids =new ArrayList();
        for(IBZTestSuiteAction entity : entities){
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
    public IBZTestSuiteActionSearchContext getSearchContext(){
        return new IBZTestSuiteActionSearchContext();
    }
    public IIBZTestSuiteActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZTestSuiteAction dynamicCall(Long key, String action, IBZTestSuiteAction et) {
        return et;
    }
}



