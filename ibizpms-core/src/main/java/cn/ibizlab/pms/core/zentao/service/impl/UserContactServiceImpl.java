package cn.ibizlab.pms.core.zentao.service.impl;

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
import cn.ibizlab.pms.core.zentao.domain.UserContact;
import cn.ibizlab.pms.core.zentao.filter.UserContactSearchContext;
import cn.ibizlab.pms.core.zentao.service.IUserContactService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.UserContactMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用户联系方式] 服务对象接口实现
 */
@Slf4j
@Service("UserContactServiceImpl")
public class UserContactServiceImpl extends ServiceImpl<UserContactMapper, UserContact> implements IUserContactService {


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(UserContact et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<UserContact> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(UserContact et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<UserContact> list) {
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
    public UserContact get(Long key) {
        UserContact et = getById(key);
        if(et == null){
            et = new UserContact();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public UserContact getDraft(UserContact et) {
        return et;
    }

    @Override
    public boolean checkKey(UserContact et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(UserContact et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(UserContact et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<UserContact> list) {
        List<UserContact> create = new ArrayList<>();
        List<UserContact> update = new ArrayList<>();
        for (UserContact et : list) {
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
    public void saveBatch(List<UserContact> list) {
        List<UserContact> create = new ArrayList<>();
        List<UserContact> update = new ArrayList<>();
        for (UserContact et : list) {
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
     * 查询集合 抄送联系人
     */
    @Override
    public Page<UserContact> searchCurUSERCONTACT(UserContactSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserContact> pages=baseMapper.searchCurUSERCONTACT(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserContact>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<UserContact> searchDefault(UserContactSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserContact> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserContact>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的联系人
     */
    @Override
    public Page<UserContact> searchMyUSERCONTACT(UserContactSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserContact> pages=baseMapper.searchMyUSERCONTACT(context.getPages(),context,context.getSelectCond());
        return new PageImpl<UserContact>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<UserContact> getUsercontactByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<UserContact> getUsercontactByEntities(List<UserContact> entities) {
        List ids =new ArrayList();
        for(UserContact entity : entities){
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


    public IUserContactService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public UserContact dynamicCall(Long key, String action, UserContact et) {
        return et;
    }
}


