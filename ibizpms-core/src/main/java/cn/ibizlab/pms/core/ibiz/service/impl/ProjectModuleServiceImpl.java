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
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProjectModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务模块] 服务对象接口实现
 */
@Slf4j
@Service("ProjectModuleServiceImpl")
public class ProjectModuleServiceImpl extends ServiceImpl<ProjectModuleMapper, ProjectModule> implements IProjectModuleService {

    @Autowired
    cn.ibizlab.pms.core.ibiz.runtime ProjectModuleRuntime;


    protected cn.ibizlab.pms.core.ibiz.service.IProjectModuleService projectmoduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProjectModule et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProjectModule> list) {
        list.forEach(item->fillParentData(item));
        for (ProjectModule et : list) {
            getProxyService().save(et);
        }
    }

    @Override
    @Transactional
    public boolean update(ProjectModule et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProjectModule> list) {
        list.forEach(item->fillParentData(item));
        for (ProjectModule et : list) {
            getProxyService().update(et);
        }
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProjectModule et) {
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
    public ProjectModule get(Long key) {
        ProjectModule et = getById(key);
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
    public ProjectModule sysGet(Long key) {
        ProjectModule et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProjectModule getDraft(ProjectModule et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(ProjectModule et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProjectModule fix(ProjectModule et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean fixBatch(List<ProjectModule> etList) {
        for(ProjectModule et : etList) {
            fix(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectModule removeModule(ProjectModule et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean removeModuleBatch(List<ProjectModule> etList) {
        for(ProjectModule et : etList) {
            removeModule(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(ProjectModule et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProjectModule et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProjectModule> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectModule> create = new ArrayList<>();
        List<ProjectModule> update = new ArrayList<>();
        for (ProjectModule et : list) {
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
    public void saveBatch(List<ProjectModule> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectModule> create = new ArrayList<>();
        List<ProjectModule> update = new ArrayList<>();
        for (ProjectModule et : list) {
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
    public List<ProjectModule> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<ProjectModule>().eq("parent",id));
    }

	@Override
    public List<ProjectModule> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<ProjectModule>().eq("root",id));
    }


    /**
     * 查询集合 BYPATH
     */
    @Override
    public Page<ProjectModule> searchByPath(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchByPath(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProjectModule> searchDefault(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 父模块
     */
    @Override
    public Page<ProjectModule> searchParentModule(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchParentModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块
     */
    @Override
    public Page<ProjectModule> searchRoot(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchRoot(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块_无分支
     */
    @Override
    public Page<ProjectModule> searchRoot_NoBranch(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchRoot_NoBranch(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块
     */
    @Override
    public Page<ProjectModule> searchRoot_Task(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchRoot_Task(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务模块
     */
    @Override
    public Page<ProjectModule> searchTaskModules(ProjectModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectModule> pages=baseMapper.searchTaskModules(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(ProjectModule et){
        //实体关系[DER1N_IBZ_PROJECTMODULE_IBZ_PROJECTMODULE_PARENT]
        if(!ObjectUtils.isEmpty(et.getParent())){
            cn.ibizlab.pms.core.ibiz.domain.ProjectModule parentmodule=et.getParentmodule();
            if(ObjectUtils.isEmpty(parentmodule)){
                cn.ibizlab.pms.core.ibiz.domain.ProjectModule majorEntity=projectmoduleService.get(et.getParent());
                et.setParentmodule(majorEntity);
                parentmodule=majorEntity;
            }
            et.setParentname(parentmodule.getName());
        }
        //实体关系[DER1N_IBZ_PROJECTMODULE_ZT_PROJECT_ROOT]
        if(!ObjectUtils.isEmpty(et.getRoot())){
            cn.ibizlab.pms.core.zentao.domain.Project ztproject=et.getZtproject();
            if(ObjectUtils.isEmpty(ztproject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getRoot());
                et.setZtproject(majorEntity);
                ztproject=majorEntity;
            }
            et.setRootname(ztproject.getName());
        }
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



    public IProjectModuleService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProjectModule dynamicCall(Long key, String action, ProjectModule et) {
        return et;
    }
}



