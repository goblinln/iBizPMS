package cn.ibizlab.pms.core.ibizpro.service.impl;

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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProductUserTaskService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbzproProductUserTaskMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品汇报用户任务] 服务对象接口实现
 */
@Slf4j
@Service("IbzproProductUserTaskServiceImpl")
public class IbzproProductUserTaskServiceImpl extends ServiceImpl<IbzproProductUserTaskMapper, IbzproProductUserTask> implements IIbzproProductUserTaskService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IbzproProductUserTaskRuntime ibzproproductusertaskRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbzproProductUserTask> select(IbzproProductUserTaskSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProductUserTask> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzproProductUserTask et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzproProductUserTask> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzproProductUserTask et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzproProductUserTask> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzproProductUserTask et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
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
    public IbzproProductUserTask get(Long key) {
        IbzproProductUserTask et = getById(key);
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
    public IbzproProductUserTask sysGet(Long key) {
        IbzproProductUserTask et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzproProductUserTask getDraft(IbzproProductUserTask et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzproProductUserTask et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzproProductUserTask et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzproProductUserTask et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzproProductUserTask> list) {
        List<IbzproProductUserTask> create = new ArrayList<>();
        List<IbzproProductUserTask> update = new ArrayList<>();
        for (IbzproProductUserTask et : list) {
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
    public void saveBatch(List<IbzproProductUserTask> list) {
        List<IbzproProductUserTask> create = new ArrayList<>();
        List<IbzproProductUserTask> update = new ArrayList<>();
        for (IbzproProductUserTask et : list) {
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



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzproProductUserTask> searchDefault(IbzproProductUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProductUserTask> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProductUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品日报用户任务统计
     */
    @Override
    public Page<IbzproProductUserTask> searchProductDailyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProductUserTask> pages=baseMapper.searchProductDailyUserTaskStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProductUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品月报用户任务统计
     */
    @Override
    public Page<IbzproProductUserTask> searchProductMonthlyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProductUserTask> pages=baseMapper.searchProductMonthlyUserTaskStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProductUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品周报用户任务统计
     */
    @Override
    public Page<IbzproProductUserTask> searchProductWeeklyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProductUserTask> pages=baseMapper.searchProductWeeklyUserTaskStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProductUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IIbzproProductUserTaskService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzproProductUserTask dynamicCall(Long key, String action, IbzproProductUserTask et) {
        return et;
    }
}



