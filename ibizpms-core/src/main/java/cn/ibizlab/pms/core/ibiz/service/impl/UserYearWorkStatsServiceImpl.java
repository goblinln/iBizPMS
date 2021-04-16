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
import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IUserYearWorkStatsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.UserYearWorkStatsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用户年度工作内容统计] 服务对象接口实现
 */
@Slf4j
@Service("UserYearWorkStatsServiceImpl")
public class UserYearWorkStatsServiceImpl extends ServiceImpl<UserYearWorkStatsMapper, UserYearWorkStats> implements IUserYearWorkStatsService {

    //@Autowired
    //cn.ibizlab.pms.core.ibiz.runtime.UserYearWorkStatsRuntime useryearworkstatsRuntime;


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(UserYearWorkStats et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<UserYearWorkStats> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(UserYearWorkStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<UserYearWorkStats> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(UserYearWorkStats et) {
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
    public UserYearWorkStats get(Long key) {
        UserYearWorkStats et = getById(key);
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
    public UserYearWorkStats sysGet(Long key) {
        UserYearWorkStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public UserYearWorkStats getDraft(UserYearWorkStats et) {
        return et;
    }

    @Override
    public boolean checkKey(UserYearWorkStats et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public UserYearWorkStats getDevInfomation(UserYearWorkStats et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getDevInfomationBatch(List<UserYearWorkStats> etList) {
        for(UserYearWorkStats et : etList) {
            getDevInfomation(et);
        }
        return true;
    }

    @Override
    @Transactional
    public UserYearWorkStats getPoInfomation(UserYearWorkStats et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getPoInfomationBatch(List<UserYearWorkStats> etList) {
        for(UserYearWorkStats et : etList) {
            getPoInfomation(et);
        }
        return true;
    }

    @Override
    @Transactional
    public UserYearWorkStats getQaInfomation(UserYearWorkStats et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getQaInfomationBatch(List<UserYearWorkStats> etList) {
        for(UserYearWorkStats et : etList) {
            getQaInfomation(et);
        }
        return true;
    }

    @Override
    @Transactional
    public UserYearWorkStats getUserYearAction(UserYearWorkStats et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getUserYearActionBatch(List<UserYearWorkStats> etList) {
        for(UserYearWorkStats et : etList) {
            getUserYearAction(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(UserYearWorkStats et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(UserYearWorkStats et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<UserYearWorkStats> list) {
        List<UserYearWorkStats> create = new ArrayList<>();
        List<UserYearWorkStats> update = new ArrayList<>();
        for (UserYearWorkStats et : list) {
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
    public void saveBatch(List<UserYearWorkStats> list) {
        List<UserYearWorkStats> create = new ArrayList<>();
        List<UserYearWorkStats> update = new ArrayList<>();
        for (UserYearWorkStats et : list) {
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
    public UserYearWorkStats updateTitleByYear(UserYearWorkStats et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean updateTitleByYearBatch(List<UserYearWorkStats> etList) {
        for(UserYearWorkStats et : etList) {
            updateTitleByYear(et);
        }
        return true;
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<UserYearWorkStats> searchDefault(UserYearWorkStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserYearWorkStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserYearWorkStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 月完成任务数及累计工时和解决Bug数
     */
    @Override
    public Page<UserYearWorkStats> searchMonthFinishTaskAndBug(UserYearWorkStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserYearWorkStats> pages=baseMapper.searchMonthFinishTaskAndBug(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserYearWorkStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 月创建Bug数和创建用例数
     */
    @Override
    public Page<UserYearWorkStats> searchMonthOpenedBugAndCase(UserYearWorkStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserYearWorkStats> pages=baseMapper.searchMonthOpenedBugAndCase(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserYearWorkStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 月创建需求数
     */
    @Override
    public Page<UserYearWorkStats> searchMonthOpenedStory(UserYearWorkStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserYearWorkStats> pages=baseMapper.searchMonthOpenedStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserYearWorkStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IUserYearWorkStatsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public UserYearWorkStats dynamicCall(Long key, String action, UserYearWorkStats et) {
        return et;
    }
}



