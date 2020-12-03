package cn.ibizlab.pms.core.report.service.impl;

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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzWeeklyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[周报] 服务对象接口实现
 */
@Slf4j
@Service("IbzWeeklyServiceImpl")
public class IbzWeeklyServiceImpl extends ServiceImpl<IbzWeeklyMapper, IbzWeekly> implements IIbzWeeklyService {


    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.report.service.logic.IIbzWeeklyUpdate__MSDenyLogic update__msdenyLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.report.service.logic.IIbzWeeklyRemove__MSDenyLogic remove__msdenyLogic;

    protected int batchSize = 500;

        @Override
    @Transactional
    public boolean create(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).create(et);
    }

    @Override
    public void createBatch(List<IbzWeekly> list) {

    }
        @Override
    @Transactional
    public boolean update(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).edit(et);
    }

    @Override
    public void updateBatch(List<IbzWeekly> list) {

    }
        @Override
    @Transactional
    public boolean remove(Long key) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).delete(key);
    }

    @Override
    public void removeBatch(Collection<Long> idList){
        if (idList != null && !idList.isEmpty()) {
            for (Long id : idList) {
                this.remove(id);
            }
        }
    }
    @Override
    @Transactional
    public IbzWeekly get(Long key) {
        IbzWeekly et = getById(key);
        if (et == null) {
            et = new IbzWeekly();
            et.setIbzweeklyid(key);
        }
        else {
        }
        return et;
    }

    @Override
    public IbzWeekly getDraft(IbzWeekly et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzWeekly et) {
        return (!ObjectUtils.isEmpty(et.getIbzweeklyid())) && (!Objects.isNull(this.getById(et.getIbzweeklyid())));
    }
        @Override
    @Transactional
    public IbzWeekly createEveryWeekReport(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).createEveryWeekReport(et);
    }

        @Override
    @Transactional
    public IbzWeekly haveRead(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).haveRead(et);
    }

    @Override
    @Transactional
    public IbzWeekly jugThisWeekCreateWeekly(IbzWeekly et) {
        //自定义代码
        return et;
    }

        @Override
    @Transactional
    public IbzWeekly pushUserWeekly(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).pushUserWeekly(et);
    }

    @Override
    @Transactional
    public boolean save(IbzWeekly et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzWeekly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzWeekly> list) {
        saveOrUpdateBatch(list, batchSize);
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<IbzWeekly> list) {
        saveOrUpdateBatch(list, batchSize);
    }

        @Override
    @Transactional
    public IbzWeekly submit(IbzWeekly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzWeeklyHelper.class).submit(et);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzWeekly> searchDefault(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的周报
     */
    @Override
    public Page<IbzWeekly> searchMyNotSubmit(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchMyNotSubmit(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的周报
     */
    @Override
    public Page<IbzWeekly> searchMyWeekly(IbzWeeklySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzWeekly> pages=baseMapper.searchMyWeekly(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzWeekly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }







    @Override
    public List<JSONObject> select(String sql, Map param) {
        return this.baseMapper.selectBySQL(sql, param);
    }

    @Override
    @Transactional
    public boolean execute(String sql, Map param) {
        if (sql == null || sql.isEmpty()) {
            return false;
        }
        if (sql.toLowerCase().trim().startsWith("insert")) {
            return this.baseMapper.insertBySQL(sql, param);
        }
        if (sql.toLowerCase().trim().startsWith("update")) {
            return this.baseMapper.updateBySQL(sql, param);
        }
        if (sql.toLowerCase().trim().startsWith("delete")) {
            return this.baseMapper.deleteBySQL(sql, param);
        }
        log.warn("暂未支持的SQL语法");
        return true;
    }

    @Override
    public List<IbzWeekly> getIbzweeklyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzWeekly> getIbzweeklyByEntities(List<IbzWeekly> entities) {
        List ids =new ArrayList();
        for(IbzWeekly entity : entities){
            Serializable id=entity.getIbzweeklyid();
            if (!ObjectUtils.isEmpty(id)) {
                ids.add(id);
            }
        }
        if (ids.size() > 0) {
            return this.listByIds(ids);
        }
        else {
            return entities;
        }
    }




}



