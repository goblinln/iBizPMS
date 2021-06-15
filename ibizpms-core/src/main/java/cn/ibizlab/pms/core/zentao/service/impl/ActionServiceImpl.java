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
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
import cn.ibizlab.pms.core.zentao.service.IActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.ActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[系统日志] 服务对象接口实现
 */
@Slf4j
@Service("ActionServiceImpl")
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements IActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.ActionRuntime actionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IHistoryService historyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    public List<Action> select(ActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    @Transactional
    public boolean create(Action et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!actionRuntime.isRtmodel()){
            historyService.saveByAction(et.getId(), et.getHistorys());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Action> list) {
        if(actionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (Action et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(Action et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!actionRuntime.isRtmodel()){
            historyService.saveByAction(et.getId(), et.getHistorys());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<Action> list) {
        if(actionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(Action et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!actionRuntime.isRtmodel()){
            historyService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(actionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public Action get(Long key) {
        Action et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!actionRuntime.isRtmodel()){
                et.setHistorys(historyService.selectByAction(key));
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
    public Action sysGet(Long key) {
        Action et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Action getDraft(Action et) {
        return et;
    }

    @Override
    public boolean checkKey(Action et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Action comment(Action et) {
         return et ;
    }

    @Override
    @Transactional
    public Action createHis(Action et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Action editComment(Action et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Action managePmsEe(Action et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(Action et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Action et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Action> list) {
        List<Action> create = new ArrayList<>();
        List<Action> update = new ArrayList<>();
        for (Action et : list) {
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
    public void saveBatch(List<Action> list) {
        List<Action> create = new ArrayList<>();
        List<Action> update = new ArrayList<>();
        for (Action et : list) {
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
    public Action sendMarkDone(Action et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Action sendTodo(Action et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Action sendToread(Action et) {
        //自定义代码
        return et;
    }


	@Override
    public List<Action> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<Action>().eq("project",id));
    }


    public List<Action> selectAccount(ActionSearchContext context){
        return baseMapper.selectAccount(context, context.getSelectCond());
    }
    public List<Action> selectBianGengLineHistory(ActionSearchContext context){
        return baseMapper.selectBianGengLineHistory(context, context.getSelectCond());
    }
    public List<Action> selectDefault(ActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<Action> selectMobType(ActionSearchContext context){
        return baseMapper.selectMobType(context, context.getSelectCond());
    }
    public List<Action> selectMy(ActionSearchContext context){
        return baseMapper.selectMy(context, context.getSelectCond());
    }
    public List<Action> selectMyAction(ActionSearchContext context){
        return baseMapper.selectMyAction(context, context.getSelectCond());
    }
    public List<Action> selectMyTrends(ActionSearchContext context){
        return baseMapper.selectMyTrends(context, context.getSelectCond());
    }
    public List<Action> selectProductTrends(ActionSearchContext context){
        return baseMapper.selectProductTrends(context, context.getSelectCond());
    }
    public List<Action> selectProjectTrends(ActionSearchContext context){
        return baseMapper.selectProjectTrends(context, context.getSelectCond());
    }
    public List<Action> selectQueryUserYEAR(ActionSearchContext context){
        return baseMapper.selectQueryUserYEAR(context, context.getSelectCond());
    }
    public List<Action> selectType(ActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<Action> selectView(ActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 指定用户数据
     */
    @Override
    public Page<Action> searchAccount(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchAccount(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Action> searchDefault(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 MobType
     */
    @Override
    public Page<Action> searchMobType(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchMobType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的数据
     */
    @Override
    public Page<Action> searchMy(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchMy(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的日志（权限）
     */
    @Override
    public Page<Action> searchMyAction(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchMyAction(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目动态(我的)
     */
    @Override
    public Page<Action> searchMyTrends(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchMyTrends(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 ProductTrends
     */
    @Override
    public Page<Action> searchProductTrends(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchProductTrends(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目动态(项目相关所有)
     */
    @Override
    public Page<Action> searchProjectTrends(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchProjectTrends(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 查询用户使用年
     */
    @Override
    public Page<Action> searchQueryUserYEAR(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchQueryUserYEAR(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Type
     */
    @Override
    public Page<Action> searchType(ActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Action> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Action>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<Action> getActionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Action> getActionByEntities(List<Action> entities) {
        List ids =new ArrayList();
        for(Action entity : entities){
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


    public IActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Action dynamicCall(Long key, String action, Action et) {
        return et;
    }
}



