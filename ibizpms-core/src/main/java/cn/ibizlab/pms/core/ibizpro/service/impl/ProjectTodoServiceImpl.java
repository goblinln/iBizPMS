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
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTodo;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTodoSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IProjectTodoService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.ProjectTodoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目其他活动] 服务对象接口实现
 */
@Slf4j
@Service("ProjectTodoServiceImpl")
public class ProjectTodoServiceImpl extends ServiceImpl<ProjectTodoMapper, ProjectTodo> implements IProjectTodoService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProjectTodo et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProjectTodo> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(ProjectTodo et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProjectTodo> list) {
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
    public ProjectTodo get(Long key) {
        ProjectTodo et = getById(key);
        if(et == null){
            et = new ProjectTodo();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public ProjectTodo getDraft(ProjectTodo et) {
        return et;
    }

    @Override
    @Transactional
    public ProjectTodo activate(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean activateBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            activate(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectTodo assignTo(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean assignToBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            assignTo(et);
        }
        return true;
    }

    @Override
    public boolean checkKey(ProjectTodo et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProjectTodo close(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean closeBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            close(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectTodo createCycle(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean createCycleBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            createCycle(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectTodo finish(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean finishBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            finish(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(ProjectTodo et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProjectTodo et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProjectTodo> list) {
        List<ProjectTodo> create = new ArrayList<>();
        List<ProjectTodo> update = new ArrayList<>();
        for (ProjectTodo et : list) {
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
    public void saveBatch(List<ProjectTodo> list) {
        List<ProjectTodo> create = new ArrayList<>();
        List<ProjectTodo> update = new ArrayList<>();
        for (ProjectTodo et : list) {
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
    public ProjectTodo sendMessage(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean sendMessageBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            sendMessage(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectTodo sendMsgPreProcess(ProjectTodo et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean sendMsgPreProcessBatch(List<ProjectTodo> etList) {
        for(ProjectTodo et : etList) {
            sendMsgPreProcess(et);
        }
        return true;
    }


	@Override
    public List<ProjectTodo> selectByIdvalue(Long id) {
        return baseMapper.selectByIdvalue(id);
    }
    @Override
    public void removeByIdvalue(Long id) {
        this.remove(new QueryWrapper<ProjectTodo>().eq("idvalue",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<ProjectTodo> searchDefault(ProjectTodoSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectTodo> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectTodo>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IProjectTodoService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
}


