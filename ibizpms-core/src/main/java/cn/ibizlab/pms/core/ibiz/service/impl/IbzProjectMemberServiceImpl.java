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
import cn.ibizlab.pms.core.ibiz.domain.IbzProjectMember;
import cn.ibizlab.pms.core.ibiz.filter.IbzProjectMemberSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzProjectMemberService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzProjectMemberMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目相关成员] 服务对象接口实现
 */
@Slf4j
@Service("IbzProjectMemberServiceImpl")
public class IbzProjectMemberServiceImpl extends ServiceImpl<IbzProjectMemberMapper, IbzProjectMember> implements IIbzProjectMemberService {

    @Autowired
    @Lazy
    IIbzProjectMemberService proxyService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzProjectMember et) {
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzProjectMember> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzProjectMember et) {
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzProjectMember> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        boolean result = removeById(key);
        return result;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public IbzProjectMember get(Long key) {
        IbzProjectMember et = getById(key);
        if (et == null) {
            et = new IbzProjectMember();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public IbzProjectMember getDraft(IbzProjectMember et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzProjectMember et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzProjectMember et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzProjectMember et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? proxyService.update(et) : proxyService.create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzProjectMember> list) {
        List<IbzProjectMember> create = new ArrayList<>();
        List<IbzProjectMember> update = new ArrayList<>();
        for (IbzProjectMember et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            proxyService.createBatch(create);
        }
        if (update.size() > 0) {
            proxyService.updateBatch(update);
        }
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<IbzProjectMember> list) {
        List<IbzProjectMember> create = new ArrayList<>();
        List<IbzProjectMember> update = new ArrayList<>();
        for (IbzProjectMember et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            proxyService.createBatch(create);
        }
        if (update.size() > 0) {
            proxyService.updateBatch(update);
        }
    }



    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<IbzProjectMember> searchDefault(IbzProjectMemberSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzProjectMember> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzProjectMember>(pages.getRecords(), context.getPageable(), pages.getTotal());
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





}



