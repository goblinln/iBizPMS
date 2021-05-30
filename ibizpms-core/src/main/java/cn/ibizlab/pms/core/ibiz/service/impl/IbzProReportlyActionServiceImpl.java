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
import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProReportlyActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzProReportlyActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzProReportlyActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[汇报日志] 服务对象接口实现
 */
@Slf4j
@Service("IbzProReportlyActionServiceImpl")
public class IbzProReportlyActionServiceImpl extends ServiceImpl<IbzProReportlyActionMapper, IbzProReportlyAction> implements IIbzProReportlyActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzProReportlyActionRuntime ibzproreportlyactionRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbzProReportlyAction> select(IbzProReportlyActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProReportlyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }



    public List<IbzProReportlyAction> selectDefault(IbzProReportlyActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzProReportlyAction> selectSimple(IbzProReportlyActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<IbzProReportlyAction> selectType(IbzProReportlyActionSearchContext context){
        return baseMapper.selectType(context, context.getSelectCond());
    }
    public List<IbzProReportlyAction> selectView(IbzProReportlyActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzProReportlyAction> searchDefault(IbzProReportlyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProReportlyAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProReportlyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 动态(根据类型过滤)
     */
    @Override
    public Page<IbzProReportlyAction> searchType(IbzProReportlyActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProReportlyAction> pages=baseMapper.searchType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzProReportlyAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzProReportlyAction> getIbzproreportlyactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzProReportlyAction> getIbzproreportlyactionByEntities(List<IbzProReportlyAction> entities) {
        List ids =new ArrayList();
        for(IbzProReportlyAction entity : entities){
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
    public IbzProReportlyActionSearchContext getSearchContext(){
        return new IbzProReportlyActionSearchContext();
    }
    public IIbzProReportlyActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzProReportlyAction dynamicCall(Long key, String action, IbzProReportlyAction et) {
        return et;
    }
}



