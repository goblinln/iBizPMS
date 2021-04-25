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
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.core.zentao.service.IDocService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.DocMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[文档] 服务对象接口实现
 */
@Slf4j
@Service("DocServiceImpl")
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.DocRuntime docRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocContentService doccontentService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocLibService doclibService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IModuleService moduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    public List<Doc> select(DocSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    	@Override
    @Transactional
    public boolean create(Doc et) {
  		if(!docRuntime.isRtmodel()){
		  
        }
		if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.DocHelper.class).create(et)) {
			 return false;
		}
		
  		return true;
    }

    @Override
	@Transactional
    public void createBatch(List<Doc> list) {
		if(!docRuntime.isRtmodel()){
		  
        }
		this.saveBatch(list, batchSize);
    }
    	@Override
    @Transactional
    public boolean update(Doc et) {
  		if(!docRuntime.isRtmodel()){
		  
        }
		if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.DocHelper.class).edit(et)) {
			 return false;
		}
		
  		return true;
    }

    @Override
	@Transactional
    public void updateBatch(List<Doc> list) {
	  if(!docRuntime.isRtmodel()){
		
	  }
		updateBatchById(list, batchSize);
    }
	
	@Override
    @Transactional
    public boolean sysUpdate(Doc et) {
	  if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.DocHelper.class).edit(et)) {
		return false;
     }
    
     return true;
   }
    @Override
    @Transactional
    public boolean remove(Long key) {
        doccontentService.removeByDoc(key) ;
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
    public Doc get(Long key) {
        Doc et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            et.setDocconents(doccontentService.selectByDoc(key));
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public Doc sysGet(Long key) {
        Doc et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Doc getDraft(Doc et) {
        if(!docRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    @Transactional
    public Doc byVersionUpdateContext(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean byVersionUpdateContextBatch(List<Doc> etList) {
        for(Doc et : etList) {
            byVersionUpdateContext(et);
        }
        return true;
    }

    @Override
    public boolean checkKey(Doc et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Doc collect(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean collectBatch(List<Doc> etList) {
        for(Doc et : etList) {
            collect(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Doc getDocStatus(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getDocStatusBatch(List<Doc> etList) {
        for(Doc et : etList) {
            getDocStatus(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Doc onlyCollectDoc(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean onlyCollectDocBatch(List<Doc> etList) {
        for(Doc et : etList) {
            onlyCollectDoc(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Doc onlyUnCollectDoc(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean onlyUnCollectDocBatch(List<Doc> etList) {
        for(Doc et : etList) {
            onlyUnCollectDoc(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(Doc et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Doc et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Doc> list) {
        if(!docRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Doc> create = new ArrayList<>();
        List<Doc> update = new ArrayList<>();
        for (Doc et : list) {
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
    public void saveBatch(List<Doc> list) {
        if(!docRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Doc> create = new ArrayList<>();
        List<Doc> update = new ArrayList<>();
        for (Doc et : list) {
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
    public Doc unCollect(Doc et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean unCollectBatch(List<Doc> etList) {
        for(Doc et : etList) {
            unCollect(et);
        }
        return true;
    }


	@Override
    public List<Doc> selectByLib(Long id) {
        return baseMapper.selectByLib(id);
    }
    @Override
    public void removeByLib(Long id) {
        this.remove(new QueryWrapper<Doc>().eq("lib",id));
    }

	@Override
    public List<Doc> selectByModule(Long id) {
        return baseMapper.selectByModule(id);
    }
    @Override
    public void removeByModule(Long id) {
        this.remove(new QueryWrapper<Doc>().eq("module",id));
    }

	@Override
    public List<Doc> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<Doc>().eq("product",id));
    }

	@Override
    public List<Doc> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<Doc>().eq("project",id));
    }


    public List<Doc> selectQueryByChildDocLibDoc(DocSearchContext context){
        return baseMapper.selectQueryByChildDocLibDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByDefault(DocSearchContext context){
        return baseMapper.selectQueryByDefault(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByDocLibAndDoc(DocSearchContext context){
        return baseMapper.selectQueryByDocLibAndDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByDocLibDoc(DocSearchContext context){
        return baseMapper.selectQueryByDocLibDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByDocModuleDoc(DocSearchContext context){
        return baseMapper.selectQueryByDocModuleDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByDocStatus(DocSearchContext context){
        return baseMapper.selectQueryByDocStatus(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByModuleDocChild(DocSearchContext context){
        return baseMapper.selectQueryByModuleDocChild(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByMyFavourite(DocSearchContext context){
        return baseMapper.selectQueryByMyFavourite(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByMyFavouritesOnlyDoc(DocSearchContext context){
        return baseMapper.selectQueryByMyFavouritesOnlyDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByNotRootDoc(DocSearchContext context){
        return baseMapper.selectQueryByNotRootDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByRootDoc(DocSearchContext context){
        return baseMapper.selectQueryByRootDoc(context, context.getSelectCond());
    }
    public List<Doc> selectQueryByView(DocSearchContext context){
        return baseMapper.selectQueryByView(context, context.getSelectCond());
    }


    /**
     * 查询集合 文档库文档（子库）
     */
    @Override
    public Page<Doc> searchChildDocLibDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchChildDocLibDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Doc> searchDefault(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档库文档
     */
    @Override
    public Page<Doc> searchDocLibAndDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDocLibAndDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档库文档
     */
    @Override
    public Page<Doc> searchDocLibDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDocLibDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档库分类文档
     */
    @Override
    public Page<Doc> searchDocModuleDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDocModuleDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文档统计
     */
    @Override
    public Page<Doc> searchDocStatus(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchDocStatus(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 文件夹文档（子目录）
     */
    @Override
    public Page<Doc> searchModuleDocChild(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchModuleDocChild(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的收藏
     */
    @Override
    public Page<Doc> searchMyFavourite(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchMyFavourite(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的收藏
     */
    @Override
    public Page<Doc> searchMyFavouritesOnlyDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchMyFavouritesOnlyDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子目录文档
     */
    @Override
    public Page<Doc> searchNotRootDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchNotRootDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根目录文档
     */
    @Override
    public Page<Doc> searchRootDoc(DocSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Doc> pages=baseMapper.searchRootDoc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Doc>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Doc et){
        //实体关系[DER1N_ZT_DOC_ZT_DOCLIB_LIB]
        if(!ObjectUtils.isEmpty(et.getLib())){
            cn.ibizlab.pms.core.zentao.domain.DocLib ztDoclib=et.getZtDoclib();
            if(ObjectUtils.isEmpty(ztDoclib)){
                cn.ibizlab.pms.core.zentao.domain.DocLib majorEntity=doclibService.get(et.getLib());
                et.setZtDoclib(majorEntity);
                ztDoclib=majorEntity;
            }
            et.setLibname(ztDoclib.getName());
        }
        //实体关系[DER1N_ZT_DOC_ZT_MODULE_MODULE]
        if(!ObjectUtils.isEmpty(et.getModule())){
            cn.ibizlab.pms.core.zentao.domain.Module ztModule=et.getZtModule();
            if(ObjectUtils.isEmpty(ztModule)){
                cn.ibizlab.pms.core.zentao.domain.Module majorEntity=moduleService.get(et.getModule());
                et.setZtModule(majorEntity);
                ztModule=majorEntity;
            }
            et.setModulename(ztModule.getName());
        }
        //实体关系[DER1N_ZT_DOC_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztProduct=et.getZtProduct();
            if(ObjectUtils.isEmpty(ztProduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtProduct(majorEntity);
                ztProduct=majorEntity;
            }
            et.setProductname(ztProduct.getName());
        }
        //实体关系[DER1N_ZT_DOC_ZT_PROJECT_PROJECT]
        if(!ObjectUtils.isEmpty(et.getProject())){
            cn.ibizlab.pms.core.zentao.domain.Project ztProject=et.getZtProject();
            if(ObjectUtils.isEmpty(ztProject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getProject());
                et.setZtProject(majorEntity);
                ztProject=majorEntity;
            }
            et.setProjectname(ztProject.getName());
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

    @Override
    public List<Doc> getDocByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Doc> getDocByEntities(List<Doc> entities) {
        List ids =new ArrayList();
        for(Doc entity : entities){
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


    public IDocService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Doc dynamicCall(Long key, String action, Doc et) {
        return et;
    }
}



