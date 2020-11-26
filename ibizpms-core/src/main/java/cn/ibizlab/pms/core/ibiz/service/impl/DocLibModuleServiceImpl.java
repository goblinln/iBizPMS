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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.DocLibModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[文档库分类] 服务对象接口实现
 */
@Slf4j
@Service("DocLibModuleServiceImpl")
public class DocLibModuleServiceImpl extends ServiceImpl<DocLibModuleMapper, DocLibModule> implements IDocLibModuleService {


    protected cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService doclibmoduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocLibService doclibService;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleFixPathLogic fixpathLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleUpdate__MSDenyLogic update__msdenyLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleRemove__MSDenyLogic remove__msdenyLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleCurUserIsFLogic curuserisfLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleDoclibModuleNFavoriteLogic doclibmodulenfavoriteLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.logic.IDocLibModuleDocLibModuleFavoriteLogic doclibmodulefavoriteLogic;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(DocLibModule et) {
        fillParentData(et);
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        fixpathLogic.execute(et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<DocLibModule> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(DocLibModule et) {
        fillParentData(et);
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        fixpathLogic.execute(et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<DocLibModule> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        DocLibModule et = new DocLibModule();
        et.set("id", key);
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
    public DocLibModule get(Long key) {
        DocLibModule tempET = new DocLibModule();
        tempET.set("id", key);
        DocLibModule et = getById(key);
        if (et == null) {
            et = new DocLibModule();
            et.setId(key);
        }
        else {
        }
        curuserisfLogic.execute(et);
        return et;
    }

    @Override
    public DocLibModule getDraft(DocLibModule et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(DocLibModule et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public DocLibModule collect(DocLibModule et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public DocLibModule docLibModuleNFavorite(DocLibModule et) {
        doclibmodulenfavoriteLogic.execute(et);
         return et;
    }

    @Override
    @Transactional
    public DocLibModule doclibModuleFavorite(DocLibModule et) {
        doclibmodulefavoriteLogic.execute(et);
         return et;
    }

    @Override
    @Transactional
    public DocLibModule fix(DocLibModule et) {
        fixpathLogic.execute(et);
         return et;
    }

    @Override
    @Transactional
    public boolean save(DocLibModule et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(DocLibModule et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<DocLibModule> list) {
        list.forEach(item->fillParentData(item));
        saveOrUpdateBatch(list, batchSize);
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<DocLibModule> list) {
        list.forEach(item -> fillParentData(item));
        saveOrUpdateBatch(list, batchSize);
    }

    @Override
    @Transactional
    public DocLibModule unCollect(DocLibModule et) {
        //自定义代码
        return et;
    }


    @Override
    public List<DocLibModule> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<DocLibModule>().eq("parent", id));
    }

    @Override
    public List<DocLibModule> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<DocLibModule>().eq("root", id));
    }


    /**
     * 查询集合 自定义文档库的模块
     */
    @Override
    public Page<DocLibModule> searchAllDocLibModule_Custom(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchAllDocLibModule_Custom(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 所有文档库模块
     */
    @Override
    public Page<DocLibModule> searchAllDoclibModule(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchAllDoclibModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子模块目录
     */
    @Override
    public Page<DocLibModule> searchChildModuleByParent(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchChildModuleByParent(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档库分类子模块
     */
    @Override
    public Page<DocLibModule> searchChildModuleByRealParent(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchChildModuleByRealParent(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<DocLibModule> searchDefault(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 父集合
     */
    @Override
    public Page<DocLibModule> searchParentModule(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchParentModule(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 所有根模块目录
     */
    @Override
    public Page<DocLibModule> searchRootModuleMuLu(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchRootModuleMuLu(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块目录
     */
    @Override
    public Page<DocLibModule> searchRootModuleMuLuByRoot(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchRootModuleMuLuByRoot(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块目录动态
     */
    @Override
    public Page<DocLibModule> searchRootModuleMuLuBysrfparentkey(DocLibModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DocLibModule> pages=baseMapper.searchRootModuleMuLuBysrfparentkey(context.getPages(), context, context.getSelectCond());
        return new PageImpl<DocLibModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(DocLibModule et){
        //实体关系[DER1N_IBZ_DOCLIBMODULE_IBZ_DOCLIBMODULE_PARENT]
        if (!ObjectUtils.isEmpty(et.getParent())) {
            cn.ibizlab.pms.core.ibiz.domain.DocLibModule pdoclibmodule=et.getPdoclibmodule();
            if (ObjectUtils.isEmpty(pdoclibmodule)) {
                cn.ibizlab.pms.core.ibiz.domain.DocLibModule majorEntity=doclibmoduleService.get(et.getParent());
                et.setPdoclibmodule(majorEntity);
                pdoclibmodule = majorEntity;
            }
            et.setModulename(pdoclibmodule.getName());
        }
        //实体关系[DER1N_IBZ_DOCLIBMODULE_ZT_DOCLIB_ROOT]
        if (!ObjectUtils.isEmpty(et.getRoot())) {
            cn.ibizlab.pms.core.zentao.domain.DocLib doclib=et.getDoclib();
            if (ObjectUtils.isEmpty(doclib)) {
                cn.ibizlab.pms.core.zentao.domain.DocLib majorEntity=doclibService.get(et.getRoot());
                et.setDoclib(majorEntity);
                doclib = majorEntity;
            }
            et.setDoclibname(doclib.getName());
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





}



