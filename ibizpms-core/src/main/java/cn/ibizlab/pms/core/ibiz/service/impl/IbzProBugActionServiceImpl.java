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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBugAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBugActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBugActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzProBugActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[Bug日志] 服务对象接口实现
 */
@Slf4j
@Service("IbzProBugActionServiceImpl")
public class IbzProBugActionServiceImpl extends ServiceImpl<IbzProBugActionMapper, IbzProBugAction> implements IIbzProBugActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzProBugActionRuntime ibzprobugactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZProBugHostoryService ibzprobughostoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;

    protected int batchSize = 500;

    @Override
    public List<IbzProBugAction> select(IbzProBugActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProBugAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzProBugAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzprobugactionRuntime.isRtmodel()){
            ibzprobughostoryService.saveByAction(et.getId(), et.getIbzprobughostory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzProBugAction> list) {
        if(ibzprobugactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (IbzProBugAction et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(IbzProBugAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzprobugactionRuntime.isRtmodel()){
            ibzprobughostoryService.saveByAction(et.getId(), et.getIbzprobughostory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzProBugAction> list) {
        if(ibzprobugactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzProBugAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzprobugactionRuntime.isRtmodel()){
            ibzprobughostoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(ibzprobugactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IbzProBugAction get(Long key) {
        IbzProBugAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzprobugactionRuntime.isRtmodel()){
                et.setIbzprobughostory(ibzprobughostoryService.selectByAction(key));
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
    public IbzProBugAction sysGet(Long key) {
        IbzProBugAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzProBugAction getDraft(IbzProBugAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzProBugAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IbzProBugAction comment(IbzProBugAction et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean commentBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            comment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzProBugAction createHis(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean createHisBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            createHis(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzProBugAction editComment(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean editCommentBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            editComment(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzProBugAction managePmsEe(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean managePmsEeBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            managePmsEe(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IbzProBugAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzProBugAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzProBugAction> list) {
        List<IbzProBugAction> create = new ArrayList<>();
        List<IbzProBugAction> update = new ArrayList<>();
        for (IbzProBugAction et : list) {
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
    public void saveBatch(List<IbzProBugAction> list) {
        List<IbzProBugAction> create = new ArrayList<>();
        List<IbzProBugAction> update = new ArrayList<>();
        for (IbzProBugAction et : list) {
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
    public IbzProBugAction sendMarkDone(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendMarkDoneBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            sendMarkDone(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzProBugAction sendTodo(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendTodoBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            sendTodo(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzProBugAction sendToread(IbzProBugAction et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean sendToreadBatch(List<IbzProBugAction> etList) {
        for(IbzProBugAction et : etList) {
            sendToread(et);
        }
        return true;
    }


	@Override
    public List<IbzProBugAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IbzProBugAction>().eq("objectid",id));
    }


    public List<IbzProBugAction> selectDefault(IbzProBugActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzProBugAction> selectSimple(IbzProBugActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IbzProBugAction> selectType(IbzProBugActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IbzProBugAction> selectView(IbzProBugActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzProBugAction> searchDefault(IbzProBugActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProBugAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProBugAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IbzProBugAction> searchType(IbzProBugActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProBugAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProBugAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzProBugAction> getIbzprobugactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzProBugAction> getIbzprobugactionByEntities(List<IbzProBugAction> entities) {
        List ids =new ArrayList();
        for(IbzProBugAction entity : entities){
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
    public IbzProBugActionSearchContext getSearchContext(){
        return new IbzProBugActionSearchContext();
    }
    public IIbzProBugActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzProBugAction dynamicCall(Long key, String action, IbzProBugAction et) {
        return et;
    }
}



