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
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.PRODUCTTEAMMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品团队] 服务对象接口实现
 */
@Slf4j
@Service("PRODUCTTEAMServiceImpl")
public class PRODUCTTEAMServiceImpl extends ServiceImpl<PRODUCTTEAMMapper, PRODUCTTEAM> implements IPRODUCTTEAMService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.PRODUCTTEAMRuntime productteamRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<PRODUCTTEAM> select(PRODUCTTEAMSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PRODUCTTEAM> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(PRODUCTTEAM et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<PRODUCTTEAM> list) {
        if(productteamRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(PRODUCTTEAM et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<PRODUCTTEAM> list) {
        if(productteamRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(PRODUCTTEAM et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!productteamRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(productteamRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public PRODUCTTEAM get(Long key) {
        PRODUCTTEAM et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!productteamRuntime.isRtmodel()){
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
    public PRODUCTTEAM sysGet(Long key) {
        PRODUCTTEAM et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public PRODUCTTEAM getDraft(PRODUCTTEAM et) {
        return et;
    }

    @Override
    public boolean checkKey(PRODUCTTEAM et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public PRODUCTTEAM productTeamGuoLv(PRODUCTTEAM et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(PRODUCTTEAM et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(PRODUCTTEAM et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<PRODUCTTEAM> list) {
        List<PRODUCTTEAM> create = new ArrayList<>();
        List<PRODUCTTEAM> update = new ArrayList<>();
        for (PRODUCTTEAM et : list) {
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
    public void saveBatch(List<PRODUCTTEAM> list) {
        List<PRODUCTTEAM> create = new ArrayList<>();
        List<PRODUCTTEAM> update = new ArrayList<>();
        for (PRODUCTTEAM et : list) {
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
    public List<PRODUCTTEAM> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<PRODUCTTEAM>().eq("root",id));
    }

    public IPRODUCTTEAMService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByRoot(Long id,List<PRODUCTTEAM> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<PRODUCTTEAM> _update=new ArrayList<PRODUCTTEAM>();
        List<PRODUCTTEAM> _create=new ArrayList<PRODUCTTEAM>();
        for(PRODUCTTEAM before:selectByRoot(id)){
            delIds.add(before.getId());
        }
        for(PRODUCTTEAM sub:list) {
            sub.setRoot(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}


    public List<PRODUCTTEAM> selectDefault(PRODUCTTEAMSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<PRODUCTTEAM> selectProductTeamInfo(PRODUCTTEAMSearchContext context){
        return baseMapper.selectProductTeamInfo(context, context.getSelectCond());
    }
    public List<PRODUCTTEAM> selectProjectApp(PRODUCTTEAMSearchContext context){
        return baseMapper.selectProjectApp(context, context.getSelectCond());
    }
    public List<PRODUCTTEAM> selectRowEditDefaultProductTeam(PRODUCTTEAMSearchContext context){
        return baseMapper.selectRowEditDefaultProductTeam(context, context.getSelectCond());
    }
    public List<PRODUCTTEAM> selectView(PRODUCTTEAMSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PRODUCTTEAM> searchDefault(PRODUCTTEAMSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PRODUCTTEAM> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<PRODUCTTEAM>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品团队成员信息
     */
    @Override
    public Page<PRODUCTTEAM> searchProductTeamInfo(PRODUCTTEAMSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PRODUCTTEAM> pages=baseMapper.searchProductTeamInfo(context.getPages(),context,context.getSelectCond());
        return new PageImpl<PRODUCTTEAM>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目立项
     */
    @Override
    public Page<PRODUCTTEAM> searchProjectApp(PRODUCTTEAMSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PRODUCTTEAM> pages=baseMapper.searchProjectApp(context.getPages(),context,context.getSelectCond());
        return new PageImpl<PRODUCTTEAM>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品团队管理
     */
    @Override
    public Page<PRODUCTTEAM> searchRowEditDefaultProductTeam(PRODUCTTEAMSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<PRODUCTTEAM> pages=baseMapper.searchRowEditDefaultProductTeam(context.getPages(),context,context.getSelectCond());
        return new PageImpl<PRODUCTTEAM>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    @Transactional
    public PRODUCTTEAM dynamicCall(Long key, String action, PRODUCTTEAM et) {
        return et;
    }
}



