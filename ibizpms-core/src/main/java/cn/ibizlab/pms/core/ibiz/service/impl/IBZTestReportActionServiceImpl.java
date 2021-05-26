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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestReportActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZTestReportActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[报告日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZTestReportActionServiceImpl")
public class IBZTestReportActionServiceImpl extends ServiceImpl<IBZTestReportActionMapper, IBZTestReportAction> implements IIBZTestReportActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZTestReportActionRuntime ibztestreportactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZTestReportHistoryService ibztestreporthistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestReportService testreportService;

    protected int batchSize = 500;

    @Override
    public List<IBZTestReportAction> select(IBZTestReportActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestReportAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZTestReportAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibztestreportactionRuntime.isRtmodel()){
            ibztestreporthistoryService.saveByAction(et.getId(), et.getIbztestreporthistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(IBZTestReportAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibztestreportactionRuntime.isRtmodel()){
            ibztestreporthistoryService.saveByAction(et.getId(), et.getIbztestreporthistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(IBZTestReportAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibztestreportactionRuntime.isRtmodel()){
            ibztestreporthistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public IBZTestReportAction get(Long key) {
        IBZTestReportAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibztestreportactionRuntime.isRtmodel()){
                et.setIbztestreporthistory(ibztestreporthistoryService.selectByAction(key));
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
    public IBZTestReportAction sysGet(Long key) {
        IBZTestReportAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZTestReportAction getDraft(IBZTestReportAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZTestReportAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZTestReportAction comment(IBZTestReportAction et) {
         return et ;
    }

    @Override
    @Transactional
    public IBZTestReportAction createHis(IBZTestReportAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZTestReportAction editComment(IBZTestReportAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZTestReportAction managePmsEe(IBZTestReportAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(IBZTestReportAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZTestReportAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }


    @Override
    @Transactional
    public IBZTestReportAction sendMarkDone(IBZTestReportAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZTestReportAction sendTodo(IBZTestReportAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZTestReportAction sendToread(IBZTestReportAction et) {
        //自定义代码
        return et;
    }


	@Override
    public List<IBZTestReportAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IBZTestReportAction>().eq("objectid",id));
    }


    public List<IBZTestReportAction> selectDefault(IBZTestReportActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZTestReportAction> selectSimple(IBZTestReportActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZTestReportAction> selectType(IBZTestReportActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZTestReportAction> selectView(IBZTestReportActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZTestReportAction> searchDefault(IBZTestReportActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestReportAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTestReportAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZTestReportAction> searchType(IBZTestReportActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZTestReportAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZTestReportAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZTestReportAction> getIbztestreportactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZTestReportAction> getIbztestreportactionByEntities(List<IBZTestReportAction> entities) {
        List ids =new ArrayList();
        for(IBZTestReportAction entity : entities){
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
    public IBZTestReportActionSearchContext getSearchContext(){
        return new IBZTestReportActionSearchContext();
    }
    public IIBZTestReportActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZTestReportAction dynamicCall(Long key, String action, IBZTestReportAction et) {
        return et;
    }
}



