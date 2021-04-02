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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzMonthlyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[月报] 服务对象接口实现
 */
@Slf4j
@Service("IbzMonthlyServiceImpl")
public class IbzMonthlyServiceImpl extends ServiceImpl<IbzMonthlyMapper, IbzMonthly> implements IIbzMonthlyService {


    protected int batchSize = 500;

        @Override
    @Transactional
    public boolean create(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).create(et);
    }

    @Override
    public void createBatch(List<IbzMonthly> list) {

    }
        @Override
    @Transactional
    public boolean update(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).edit(et);
    }

    @Override
    public void updateBatch(List<IbzMonthly> list) {

    }
        @Override
    @Transactional
    public boolean remove(Long key) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).delete(key);
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
    public IbzMonthly get(Long key) {
        IbzMonthly et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        }
        return et;
    }

    @Override
    public IbzMonthly getDraft(IbzMonthly et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzMonthly et) {
        return (!ObjectUtils.isEmpty(et.getIbzmonthlyid())) && (!Objects.isNull(this.getById(et.getIbzmonthlyid())));
    }
    @Override
    @Transactional
    public IbzMonthly createGetInfo(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean createGetInfoBatch(List<IbzMonthly> etList) {
        for(IbzMonthly et : etList) {
            createGetInfo(et);
        }
        return true;
    }

       @Override
    @Transactional
    public IbzMonthly createUserMonthly(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).createUserMonthly(et);
    }
	
	@Override
    @Transactional
    public boolean createUserMonthlyBatch (List<IbzMonthly> etList) {
		 for(IbzMonthly et : etList) {
		   createUserMonthly(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public IbzMonthly editGetCompleteTask(IbzMonthly et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean editGetCompleteTaskBatch(List<IbzMonthly> etList) {
        for(IbzMonthly et : etList) {
            editGetCompleteTask(et);
        }
        return true;
    }

       @Override
    @Transactional
    public IbzMonthly haveRead(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).haveRead(et);
    }
	
	@Override
    @Transactional
    public boolean haveReadBatch (List<IbzMonthly> etList) {
		 for(IbzMonthly et : etList) {
		   haveRead(et);
		 }
	 	 return true;
    }

       @Override
    @Transactional
    public IbzMonthly pushUserMonthly(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).pushUserMonthly(et);
    }
	
	@Override
    @Transactional
    public boolean pushUserMonthlyBatch (List<IbzMonthly> etList) {
		 for(IbzMonthly et : etList) {
		   pushUserMonthly(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public boolean save(IbzMonthly et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzMonthly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzMonthly> list) {
        List<IbzMonthly> create = new ArrayList<>();
        List<IbzMonthly> update = new ArrayList<>();
        for (IbzMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbzmonthlyid()))) {
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
    public void saveBatch(List<IbzMonthly> list) {
        List<IbzMonthly> create = new ArrayList<>();
        List<IbzMonthly> update = new ArrayList<>();
        for (IbzMonthly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzmonthlyid()) || ObjectUtils.isEmpty(getById(et.getIbzmonthlyid()))) {
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
    public IbzMonthly submit(IbzMonthly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzMonthlyHelper.class).submit(et);
    }
	
	@Override
    @Transactional
    public boolean submitBatch (List<IbzMonthly> etList) {
		 for(IbzMonthly et : etList) {
		   submit(et);
		 }
	 	 return true;
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzMonthly> searchDefault(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的月报
     */
    @Override
    public Page<IbzMonthly> searchMyMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的月报（移动端）
     */
    @Override
    public Page<IbzMonthly> searchMyMonthlyMob(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyMonthlyMob(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的月报
     */
    @Override
    public Page<IbzMonthly> searchMyReceivedMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMyReceivedMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我提交的月报
     */
    @Override
    public Page<IbzMonthly> searchMySubmitMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchMySubmitMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品月报
     */
    @Override
    public Page<IbzMonthly> searchProductMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchProductMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目月报
     */
    @Override
    public Page<IbzMonthly> searchProjectMonthly(IbzMonthlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzMonthly> pages=baseMapper.searchProjectMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzMonthly>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzMonthly> getIbzmonthlyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzMonthly> getIbzmonthlyByEntities(List<IbzMonthly> entities) {
        List ids =new ArrayList();
        for(IbzMonthly entity : entities){
            Serializable id=entity.getIbzmonthlyid();
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


    public IIbzMonthlyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzMonthly dynamicCall(Long key, String action, IbzMonthly et) {
        return et;
    }
}



