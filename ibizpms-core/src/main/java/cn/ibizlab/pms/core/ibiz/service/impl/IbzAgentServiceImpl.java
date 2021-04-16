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
import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.filter.IbzAgentSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzAgentService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzAgentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[代理] 服务对象接口实现
 */
@Slf4j
@Service("IbzAgentServiceImpl")
public class IbzAgentServiceImpl extends ServiceImpl<IbzAgentMapper, IbzAgent> implements IIbzAgentService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.IbzAgentRuntime ibzagentRuntime;


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzAgent et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzagentid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzAgent> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzAgent et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_agentid", et.getIbzagentid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzagentid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzAgent> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzAgent et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_agentid", et.getIbzagentid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzagentid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbzAgent get(Long key) {
        IbzAgent et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IbzAgent sysGet(Long key) {
        IbzAgent et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzAgent getDraft(IbzAgent et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzAgent et) {
        return (!ObjectUtils.isEmpty(et.getIbzagentid())) && (!Objects.isNull(this.getById(et.getIbzagentid())));
    }
    @Override
    @Transactional
    public boolean save(IbzAgent et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzAgent et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzAgent> list) {
        List<IbzAgent> create = new ArrayList<>();
        List<IbzAgent> update = new ArrayList<>();
        for (IbzAgent et : list) {
            if (ObjectUtils.isEmpty(et.getIbzagentid()) || ObjectUtils.isEmpty(getById(et.getIbzagentid()))) {
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
    public void saveBatch(List<IbzAgent> list) {
        List<IbzAgent> create = new ArrayList<>();
        List<IbzAgent> update = new ArrayList<>();
        for (IbzAgent et : list) {
            if (ObjectUtils.isEmpty(et.getIbzagentid()) || ObjectUtils.isEmpty(getById(et.getIbzagentid()))) {
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



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzAgent> searchDefault(IbzAgentSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzAgent> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzAgent>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzAgent> getIbzagentByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzAgent> getIbzagentByEntities(List<IbzAgent> entities) {
        List ids =new ArrayList();
        for(IbzAgent entity : entities){
            Serializable id=entity.getIbzagentid();
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


    public IIbzAgentService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzAgent dynamicCall(Long key, String action, IbzAgent et) {
        return et;
    }
}



