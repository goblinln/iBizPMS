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
import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
import cn.ibizlab.pms.core.zentao.service.IUserService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[用户] 服务对象接口实现
 */
@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.UserRuntime userRuntime;


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(User et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<User> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(User et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<User> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(User et) {
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
    public User get(Long key) {
        User et = getById(key);
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
    public User sysGet(Long key) {
        User et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public User getDraft(User et) {
        return et;
    }

    @Override
    public boolean checkKey(User et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public User getByCommiter(User et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(User et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(User et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<User> list) {
        List<User> create = new ArrayList<>();
        List<User> update = new ArrayList<>();
        for (User et : list) {
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
    public void saveBatch(List<User> list) {
        List<User> create = new ArrayList<>();
        List<User> update = new ArrayList<>();
        for (User et : list) {
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
    public User syncAccount(User et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean syncAccountBatch(List<User> etList) {
        for(User et : etList) {
            syncAccount(et);
        }
        return true;
    }



    /**
     * 查询集合 Bug用户
     */
    @Override
    public Page<User> searchBugUser(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchBugUser(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<User> searchDefault(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根据源代码账户获取登录名
     */
    @Override
    public Page<User> searchGetByCommiter(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchGetByCommiter(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目团队管理
     */
    @Override
    public Page<User> searchProjectTeamM(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchProjectTeamM(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目团队成员
     */
    @Override
    public Page<User> searchProjectTeamUser(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchProjectTeamUser(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目团队成员
     */
    @Override
    public Page<User> searchProjectTeamUserTask(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchProjectTeamUserTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 TASKTEAM
     */
    @Override
    public Page<User> searchTaskTeam(UserSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> pages=baseMapper.searchTaskTeam(context.getPages(),context,context.getSelectCond());
        return new PageImpl<User>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IUserService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public User dynamicCall(Long key, String action, User et) {
        return et;
    }
}



