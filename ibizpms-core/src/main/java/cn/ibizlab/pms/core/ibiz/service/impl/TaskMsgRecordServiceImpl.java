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
import cn.ibizlab.pms.core.ibiz.domain.TaskMsgRecord;
import cn.ibizlab.pms.core.ibiz.filter.TaskMsgRecordSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ITaskMsgRecordService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.TaskMsgRecordMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[待办消息记录] 服务对象接口实现
 */
@Slf4j
@Service("TaskMsgRecordServiceImpl")
public class TaskMsgRecordServiceImpl extends ServiceImpl<TaskMsgRecordMapper, TaskMsgRecord> implements ITaskMsgRecordService {


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(TaskMsgRecord et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getTaskmsgrecordid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<TaskMsgRecord> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TaskMsgRecord et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("taskmsgrecordid", et.getTaskmsgrecordid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getTaskmsgrecordid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<TaskMsgRecord> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(TaskMsgRecord et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("taskmsgrecordid", et.getTaskmsgrecordid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getTaskmsgrecordid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public TaskMsgRecord get(String key) {
        TaskMsgRecord et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
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
    public TaskMsgRecord sysGet(String key) {
        TaskMsgRecord et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public TaskMsgRecord getDraft(TaskMsgRecord et) {
        return et;
    }

    @Override
    public boolean checkKey(TaskMsgRecord et) {
        return (!ObjectUtils.isEmpty(et.getTaskmsgrecordid())) && (!Objects.isNull(this.getById(et.getTaskmsgrecordid())));
    }
    @Override
    @Transactional
    public boolean save(TaskMsgRecord et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TaskMsgRecord et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TaskMsgRecord> list) {
        List<TaskMsgRecord> create = new ArrayList<>();
        List<TaskMsgRecord> update = new ArrayList<>();
        for (TaskMsgRecord et : list) {
            if (ObjectUtils.isEmpty(et.getTaskmsgrecordid()) || ObjectUtils.isEmpty(getById(et.getTaskmsgrecordid()))) {
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
    public void saveBatch(List<TaskMsgRecord> list) {
        List<TaskMsgRecord> create = new ArrayList<>();
        List<TaskMsgRecord> update = new ArrayList<>();
        for (TaskMsgRecord et : list) {
            if (ObjectUtils.isEmpty(et.getTaskmsgrecordid()) || ObjectUtils.isEmpty(getById(et.getTaskmsgrecordid()))) {
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
    public Page<TaskMsgRecord> searchDefault(TaskMsgRecordSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskMsgRecord> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskMsgRecord>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<TaskMsgRecord> getTaskmsgrecordByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TaskMsgRecord> getTaskmsgrecordByEntities(List<TaskMsgRecord> entities) {
        List ids =new ArrayList();
        for(TaskMsgRecord entity : entities){
            Serializable id=entity.getTaskmsgrecordid();
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


    public ITaskMsgRecordService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TaskMsgRecord dynamicCall(String key, String action, TaskMsgRecord et) {
        return et;
    }
}



