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
import cn.ibizlab.pms.core.zentao.domain.ProjectProduct;
import cn.ibizlab.pms.core.zentao.filter.ProjectProductSearchContext;
import cn.ibizlab.pms.core.zentao.service.IProjectProductService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.ProjectProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目产品] 服务对象接口实现
 */
@Slf4j
@Service("ProjectProductServiceImpl")
public class ProjectProductServiceImpl extends ServiceImpl<ProjectProductMapper, ProjectProduct> implements IProjectProductService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBranchService branchService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductPlanService productplanService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProjectProduct et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProjectProduct> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(ProjectProduct et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProjectProduct> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
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
    public ProjectProduct get(String key) {
        ProjectProduct et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
        }
        return et;
    }

    @Override
    public ProjectProduct getDraft(ProjectProduct et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(ProjectProduct et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(ProjectProduct et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProjectProduct et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProjectProduct> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectProduct> create = new ArrayList<>();
        List<ProjectProduct> update = new ArrayList<>();
        for (ProjectProduct et : list) {
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
    public void saveBatch(List<ProjectProduct> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectProduct> create = new ArrayList<>();
        List<ProjectProduct> update = new ArrayList<>();
        for (ProjectProduct et : list) {
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
    public List<ProjectProduct> selectByBranch(Long id) {
        return baseMapper.selectByBranch(id);
    }
    @Override
    public void removeByBranch(Long id) {
        this.remove(new QueryWrapper<ProjectProduct>().eq("branch",id));
    }

	@Override
    public List<ProjectProduct> selectByPlan(Long id) {
        return baseMapper.selectByPlan(id);
    }
    @Override
    public void removeByPlan(Long id) {
        this.remove(new QueryWrapper<ProjectProduct>().eq("plan",id));
    }

	@Override
    public List<ProjectProduct> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<ProjectProduct>().eq("product",id));
    }

	@Override
    public List<ProjectProduct> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<ProjectProduct>().eq("project",id));
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProjectProduct> searchDefault(ProjectProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectProduct> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectProduct>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 关联计划
     */
    @Override
    public Page<ProjectProduct> searchRelationPlan(ProjectProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectProduct> pages=baseMapper.searchRelationPlan(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectProduct>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(ProjectProduct et){
        //实体关系[DER1N_ZT_PROJECTPRODUCT_ZT_PRODUCTPLAN_PLAN]
        if(!ObjectUtils.isEmpty(et.getPlan())){
            cn.ibizlab.pms.core.zentao.domain.ProductPlan ztproductplan=et.getZtproductplan();
            if(ObjectUtils.isEmpty(ztproductplan)){
                cn.ibizlab.pms.core.zentao.domain.ProductPlan majorEntity=productplanService.get(et.getPlan());
                et.setZtproductplan(majorEntity);
                ztproductplan=majorEntity;
            }
            et.setPlanname(ztproductplan.getTitle());
        }
        //实体关系[DER1N_ZT_PROJECTPRODUCT_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setProductname(ztproduct.getName());
        }
        //实体关系[DER1N_ZT_PROJECTPRODUCT_ZT_PROJECT_PROJECT]
        if(!ObjectUtils.isEmpty(et.getProject())){
            cn.ibizlab.pms.core.zentao.domain.Project ztproject=et.getZtproject();
            if(ObjectUtils.isEmpty(ztproject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getProject());
                et.setZtproject(majorEntity);
                ztproject=majorEntity;
            }
            et.setProjectname(ztproject.getName());
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



    public IProjectProductService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProjectProduct dynamicCall(String key, String action, ProjectProduct et) {
        return et;
    }
}



