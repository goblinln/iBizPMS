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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProductActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IBZProProductActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品日志] 服务对象接口实现
 */
@Slf4j
@Service("IBZProProductActionServiceImpl")
public class IBZProProductActionServiceImpl extends ServiceImpl<IBZProProductActionMapper, IBZProProductAction> implements IIBZProProductActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IBZProProductActionRuntime ibzproproductactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZProProductHistoryService ibzproproducthistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<IBZProProductAction> select(IBZProProductActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProProductAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!ibzproproductactionRuntime.isRtmodel()){
            ibzproproducthistoryService.saveByAction(et.getId(), et.getIbzproproductactions());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(IBZProProductAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!ibzproproductactionRuntime.isRtmodel()){
            ibzproproducthistoryService.saveByAction(et.getId(), et.getIbzproproductactions());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(IBZProProductAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!ibzproproductactionRuntime.isRtmodel()){
            ibzproproducthistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public IBZProProductAction get(Long key) {
        IBZProProductAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!ibzproproductactionRuntime.isRtmodel()){
                et.setIbzproproductactions(ibzproproducthistoryService.selectByAction(key));
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
    public IBZProProductAction sysGet(Long key) {
        IBZProProductAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IBZProProductAction getDraft(IBZProProductAction et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProProductAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public IBZProProductAction comment(IBZProProductAction et) {
         return et ;
    }

    @Override
    @Transactional
    public IBZProProductAction createHis(IBZProProductAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZProProductAction editComment(IBZProProductAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZProProductAction managePmsEe(IBZProProductAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(IBZProProductAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProProductAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }


    @Override
    @Transactional
    public IBZProProductAction sendMarkDone(IBZProProductAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZProProductAction sendTodo(IBZProProductAction et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public IBZProProductAction sendToread(IBZProProductAction et) {
        //自定义代码
        return et;
    }


	@Override
    public List<IBZProProductAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<IBZProProductAction>().eq("objectid",id));
    }


    public List<IBZProProductAction> selectDefault(IBZProProductActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProProductAction> selectMobType(IBZProProductActionSearchContext context){
        return baseMapper.selectMobType(context, context.getSelectCond());
    }
    public List<IBZProProductAction> selectProductTrends(IBZProProductActionSearchContext context){
        return baseMapper.selectProductTrends(context, context.getSelectCond());
    }
    public List<IBZProProductAction> selectSimple(IBZProProductActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IBZProProductAction> selectType(IBZProProductActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IBZProProductAction> selectView(IBZProProductActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProProductAction> searchDefault(IBZProProductActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZProProductAction> searchMobType(IBZProProductActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductAction> pages=baseMapper.searchMobType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品动态(产品相关所有)
     */
    @Override
    public Page<IBZProProductAction> searchProductTrends(IBZProProductActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductAction> pages=baseMapper.searchProductTrends(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IBZProProductAction> searchType(IBZProProductActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProProductAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProProductAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IBZProProductAction> getIbzproproductactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProProductAction> getIbzproproductactionByEntities(List<IBZProProductAction> entities) {
        List ids =new ArrayList();
        for(IBZProProductAction entity : entities){
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
    public IBZProProductActionSearchContext getSearchContext(){
        return new IBZProProductActionSearchContext();
    }
    public IIBZProProductActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProProductAction dynamicCall(Long key, String action, IBZProProductAction et) {
        return et;
    }
}



