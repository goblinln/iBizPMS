package cn.ibizlab.pms.core.report.service.impl;

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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzDailyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[日报] 服务对象接口实现
 */
@Slf4j
@Service("IbzDailyServiceImpl")
public class IbzDailyServiceImpl extends ServiceImpl<IbzDailyMapper, IbzDaily> implements IIbzDailyService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.report.runtime.IbzDailyRuntime ibzdailyRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbzDaily> select(IbzDailySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    	@Override
    @Transactional
    public boolean create(IbzDaily et) {
  		if(!ibzdailyRuntime.isRtmodel()){
		  
        }
		if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).create(et)) {
			 return false;
		}
		
  		return true;
    }

    @Override
	@Transactional
    public void createBatch(List<IbzDaily> list) {
		if(!ibzdailyRuntime.isRtmodel()){
		  
        }
		this.saveBatch(list, batchSize);
    }
    	@Override
    @Transactional
    public boolean update(IbzDaily et) {
  		if(!ibzdailyRuntime.isRtmodel()){
		  
        }
		if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).edit(et)) {
			 return false;
		}
		
  		return true;
    }

    @Override
	@Transactional
    public void updateBatch(List<IbzDaily> list) {
	  if(!ibzdailyRuntime.isRtmodel()){
		
	  }
		updateBatchById(list, batchSize);
    }
	
	@Override
    @Transactional
    public boolean sysUpdate(IbzDaily et) {
	  if(!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).edit(et)) {
		return false;
     }
    
     return true;
   }
        @Override
    @Transactional
    public boolean remove(Long key) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).delete(key);
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
    public IbzDaily get(Long key) {
        IbzDaily et = getById(key);
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
    public IbzDaily sysGet(Long key) {
        IbzDaily et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzDaily getDraft(IbzDaily et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzDaily et) {
        return (!ObjectUtils.isEmpty(et.getIbzdailyid())) && (!Objects.isNull(this.getById(et.getIbzdailyid())));
    }
       @Override
    @Transactional
    public IbzDaily createUserDaily(IbzDaily et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).createUserDaily(et);
    }
	
	@Override
    @Transactional
    public boolean createUserDailyBatch (List<IbzDaily> etList) {
		 for(IbzDaily et : etList) {
		   createUserDaily(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public IbzDaily getYeaterdayDailyPlansTaskEdit(IbzDaily et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getYeaterdayDailyPlansTaskEditBatch(List<IbzDaily> etList) {
        for(IbzDaily et : etList) {
            getYeaterdayDailyPlansTaskEdit(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IbzDaily getYesterdayDailyPlansTask(IbzDaily et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getYesterdayDailyPlansTaskBatch(List<IbzDaily> etList) {
        for(IbzDaily et : etList) {
            getYesterdayDailyPlansTask(et);
        }
        return true;
    }

       @Override
    @Transactional
    public IbzDaily haveRead(IbzDaily et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).haveRead(et);
    }
	
	@Override
    @Transactional
    public boolean haveReadBatch (List<IbzDaily> etList) {
		 for(IbzDaily et : etList) {
		   haveRead(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public IbzDaily linkCompleteTask(IbzDaily et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean linkCompleteTaskBatch(List<IbzDaily> etList) {
        for(IbzDaily et : etList) {
            linkCompleteTask(et);
        }
        return true;
    }

       @Override
    @Transactional
    public IbzDaily pushUserDaily(IbzDaily et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).pushUserDaily(et);
    }
	
	@Override
    @Transactional
    public boolean pushUserDailyBatch (List<IbzDaily> etList) {
		 for(IbzDaily et : etList) {
		   pushUserDaily(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public boolean save(IbzDaily et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzDaily et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzDaily> list) {
        List<IbzDaily> create = new ArrayList<>();
        List<IbzDaily> update = new ArrayList<>();
        for (IbzDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbzdailyid()) || ObjectUtils.isEmpty(getById(et.getIbzdailyid()))) {
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
    public void saveBatch(List<IbzDaily> list) {
        List<IbzDaily> create = new ArrayList<>();
        List<IbzDaily> update = new ArrayList<>();
        for (IbzDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbzdailyid()) || ObjectUtils.isEmpty(getById(et.getIbzdailyid()))) {
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
    public IbzDaily submit(IbzDaily et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzDailyHelper.class).submit(et);
    }
	
	@Override
    @Transactional
    public boolean submitBatch (List<IbzDaily> etList) {
		 for(IbzDaily et : etList) {
		   submit(et);
		 }
	 	 return true;
    }



    public List<IbzDaily> selectQueryByDefault(IbzDailySearchContext context){
        return baseMapper.selectQueryByDefault(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByMyAllDaily(IbzDailySearchContext context){
        return baseMapper.selectQueryByMyAllDaily(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByMyDaily(IbzDailySearchContext context){
        return baseMapper.selectQueryByMyDaily(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByMyNotSubmit(IbzDailySearchContext context){
        return baseMapper.selectQueryByMyNotSubmit(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByMySubmitDaily(IbzDailySearchContext context){
        return baseMapper.selectQueryByMySubmitDaily(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByProductDaily(IbzDailySearchContext context){
        return baseMapper.selectQueryByProductDaily(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByProjectDaily(IbzDailySearchContext context){
        return baseMapper.selectQueryByProjectDaily(context, context.getSelectCond());
    }
    public List<IbzDaily> selectQueryByView(IbzDailySearchContext context){
        return baseMapper.selectQueryByView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzDaily> searchDefault(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的日报（已提交和未提交）
     */
    @Override
    public Page<IbzDaily> searchMyAllDaily(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchMyAllDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的日报
     */
    @Override
    public Page<IbzDaily> searchMyDaily(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchMyDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的日报
     */
    @Override
    public Page<IbzDaily> searchMyNotSubmit(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchMyNotSubmit(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我提交的日报
     */
    @Override
    public Page<IbzDaily> searchMySubmitDaily(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchMySubmitDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品日报
     */
    @Override
    public Page<IbzDaily> searchProductDaily(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchProductDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目日报
     */
    @Override
    public Page<IbzDaily> searchProjectDaily(IbzDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzDaily> pages=baseMapper.searchProjectDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzDaily> getIbzdailyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzDaily> getIbzdailyByEntities(List<IbzDaily> entities) {
        List ids =new ArrayList();
        for(IbzDaily entity : entities){
            Serializable id=entity.getIbzdailyid();
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


    public IIbzDailyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzDaily dynamicCall(Long key, String action, IbzDaily et) {
        return et;
    }
}



