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
import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
import cn.ibizlab.pms.core.zentao.service.IModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.ModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[模块] 服务对象接口实现
 */
@Slf4j
@Service("ModuleServiceImpl")
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ICaseStatsService casestatsService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocService docService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected cn.ibizlab.pms.core.zentao.service.IModuleService moduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBranchService branchService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(Module et) {
        fillParentData(et);
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Module> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

        @Override
    @Transactional
    public boolean update(Module et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.ModuleHelper.class).edit(et);
    }

    @Override
    public void updateBatch(List<Module> list) {

    }
        @Override
    @Transactional
    public boolean remove(Long key) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.ModuleHelper.class).delete(key);
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
    public Module get(Long key) {
        Module et = getById(key);
        if (et == null) {
            et = new Module();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public Module getDraft(Module et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(Module et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
       @Override
    @Transactional
    public Module fix(Module et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.ModuleHelper.class).fix(et);
    }
	
	@Override
    @Transactional
    public boolean fixBatch (List<Module> etList) {
		 for(Module et : etList) {
		   fix(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public boolean save(Module et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Module et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Module> list) {
        list.forEach(item->fillParentData(item));
        List<Module> create = new ArrayList<>();
        List<Module> update = new ArrayList<>();
        for (Module et : list) {
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
    public void saveBatch(List<Module> list) {
        list.forEach(item -> fillParentData(item));
        List<Module> create = new ArrayList<>();
        List<Module> update = new ArrayList<>();
        for (Module et : list) {
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
    public List<Module> selectByBranch(Long id) {
        return baseMapper.selectByBranch(id);
    }
    @Override
    public void removeByBranch(Long id) {
        this.remove(new QueryWrapper<Module>().eq("branch", id));
    }

    @Override
    public List<Module> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<Module>().eq("parent", id));
    }


    /**
     * 查询集合 BugModule
     */
    @Override
    public Page<Module> searchBugModule(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchBugModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<Module> searchBugModuleCodeList(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchBugModuleCodeList(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Module> searchDefault(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档目录
     */
    @Override
    public Page<Module> searchDocModule(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchDocModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品线
     */
    @Override
    public Page<Module> searchLine(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchLine(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 需求模块
     */
    @Override
    public Page<Module> searchStoryModule(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchStoryModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务模块
     */
    @Override
    public Page<Module> searchTaskModule(ModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Module> pages=baseMapper.searchTaskModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<Module>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Module et){
        //实体关系[DER1N__ZT_MODULE__ZT_MODULE__PARENT]
        if (!ObjectUtils.isEmpty(et.getParent())) {
            cn.ibizlab.pms.core.zentao.domain.Module ibizparent=et.getIbizparent();
            if (ObjectUtils.isEmpty(ibizparent)) {
                cn.ibizlab.pms.core.zentao.domain.Module majorEntity=moduleService.get(et.getParent());
                et.setIbizparent(majorEntity);
                ibizparent = majorEntity;
            }
            et.setParentname(ibizparent.getName());
        }
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
    public List<Module> getModuleByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Module> getModuleByEntities(List<Module> entities) {
        List ids =new ArrayList();
        for(Module entity : entities){
            Serializable id=entity.getId();
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





    public IModuleService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Module dynamicCall(Long key, String action, Module et) {
        return et;
    }
}


