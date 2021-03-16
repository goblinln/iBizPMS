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
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.TaskTeamMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务团队] 服务对象接口实现
 */
@Slf4j
@Service("TaskTeamServiceImpl")
public class TaskTeamServiceImpl extends ServiceImpl<TaskTeamMapper, TaskTeam> implements ITaskTeamService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(TaskTeam et) {
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<TaskTeam> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TaskTeam et) {
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<TaskTeam> list) {
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
    public TaskTeam get(Long key) {
        TaskTeam et = getById(key);
        if (et == null) {
            et = new TaskTeam();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public TaskTeam getDraft(TaskTeam et) {
        return et;
    }

    @Override
    public boolean checkKey(TaskTeam et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(TaskTeam et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TaskTeam et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TaskTeam> list) {
        List<TaskTeam> create = new ArrayList<>();
        List<TaskTeam> update = new ArrayList<>();
        for (TaskTeam et : list) {
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
    public void saveBatch(List<TaskTeam> list) {
        List<TaskTeam> create = new ArrayList<>();
        List<TaskTeam> update = new ArrayList<>();
        for (TaskTeam et : list) {
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
    public List<TaskTeam> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<TaskTeam>().eq("root", id));
    }

    public ITaskTeamService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    public void saveByRoot(Long id, List<TaskTeam> list) {
        if (list == null) {
            return;
        }
        Set<Long> delIds=new HashSet<Long>();
        List<TaskTeam> _update=new ArrayList<TaskTeam>();
        List<TaskTeam> _create=new ArrayList<TaskTeam>();
        for (TaskTeam before:selectByRoot(id)){
            delIds.add(before.getId());
        }
        for (TaskTeam sub : list) {
            sub.setRoot(id);
            if (ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if (delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else {
                _create.add(sub);
            }
        }
        if (_update.size() > 0) {
            getProxyService().updateBatch(_update);
        }
        if (_create.size() > 0) {
            getProxyService().createBatch(_create);
        }
        if (delIds.size() > 0) {
            getProxyService().removeBatch(delIds);
        }
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TaskTeam> searchDefault(TaskTeamSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskTeam> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<TaskTeam>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    @Transactional
    public TaskTeam dynamicCall(Long key, String action, TaskTeam et) {
        return et;
    }
}


