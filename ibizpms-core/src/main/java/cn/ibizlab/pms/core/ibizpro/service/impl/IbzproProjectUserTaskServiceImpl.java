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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProjectUserTask;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProjectUserTaskSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProjectUserTaskService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbzproProjectUserTaskMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目汇报用户任务] 服务对象接口实现
 */
@Slf4j
@Service("IbzproProjectUserTaskServiceImpl")
public class IbzproProjectUserTaskServiceImpl extends ServiceImpl<IbzproProjectUserTaskMapper, IbzproProjectUserTask> implements IIbzproProjectUserTaskService {


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzproProjectUserTask et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzproProjectUserTask> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzproProjectUserTask et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzproProjectUserTask> list) {
        updateBatchById(list, batchSize);
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
    public IbzproProjectUserTask get(Long key) {
        IbzproProjectUserTask et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        }
        return et;
    }

    @Override
    public IbzproProjectUserTask getDraft(IbzproProjectUserTask et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzproProjectUserTask et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzproProjectUserTask et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzproProjectUserTask et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzproProjectUserTask> list) {
        List<IbzproProjectUserTask> create = new ArrayList<>();
        List<IbzproProjectUserTask> update = new ArrayList<>();
        for (IbzproProjectUserTask et : list) {
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
    public void saveBatch(List<IbzproProjectUserTask> list) {
        List<IbzproProjectUserTask> create = new ArrayList<>();
        List<IbzproProjectUserTask> update = new ArrayList<>();
        for (IbzproProjectUserTask et : list) {
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
    public Page<IbzproProjectUserTask> searchDefault(IbzproProjectUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProjectUserTask> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProjectUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目日报任务
     */
    @Override
    public Page<IbzproProjectUserTask> searchProjectDailyTask(IbzproProjectUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProjectUserTask> pages=baseMapper.searchProjectDailyTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProjectUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目日报任务
     */
    @Override
    public Page<IbzproProjectUserTask> searchProjectMonthlyTask(IbzproProjectUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProjectUserTask> pages=baseMapper.searchProjectMonthlyTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProjectUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目周报任务
     */
    @Override
    public Page<IbzproProjectUserTask> searchProjectWeeklyTask(IbzproProjectUserTaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzproProjectUserTask> pages=baseMapper.searchProjectWeeklyTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzproProjectUserTask>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IIbzproProjectUserTaskService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzproProjectUserTask dynamicCall(Long key, String action, IbzproProjectUserTask et) {
        return et;
    }
}



