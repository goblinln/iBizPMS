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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzReportlyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[汇报] 服务对象接口实现
 */
@Slf4j
@Service("IbzReportlyServiceImpl")
public class IbzReportlyServiceImpl extends ServiceImpl<IbzReportlyMapper, IbzReportly> implements IIbzReportlyService {


    protected int batchSize = 500;

        @Override
    @Transactional
    public boolean create(IbzReportly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzReportlyHelper.class).create(et);
    }

    @Override
    public void createBatch(List<IbzReportly> list) {

    }
        @Override
    @Transactional
    public boolean update(IbzReportly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzReportlyHelper.class).edit(et);
    }

    @Override
    public void updateBatch(List<IbzReportly> list) {

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
    public IbzReportly get(Long key) {
        IbzReportly et = getById(key);
        if (et == null) {
            et = new IbzReportly();
            et.setIbzreportlyid(key);
        }
        else {
        }
        return et;
    }

    @Override
    public IbzReportly getDraft(IbzReportly et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzReportly et) {
        return (!ObjectUtils.isEmpty(et.getIbzreportlyid())) && (!Objects.isNull(this.getById(et.getIbzreportlyid())));
    }
       @Override
    @Transactional
    public IbzReportly haveRead(IbzReportly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzReportlyHelper.class).haveRead(et);
    }
	
	@Override
    @Transactional
    public boolean haveReadBatch (List<IbzReportly> etList) {
		 for(IbzReportly et : etList) {
		   haveRead(et);
		 }
	 	 return true;
    }

    @Override
    @Transactional
    public boolean save(IbzReportly et) {
        if (!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzReportly et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzReportly> list) {
        List<IbzReportly> create = new ArrayList<>();
        List<IbzReportly> update = new ArrayList<>();
        for (IbzReportly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzreportlyid()) || ObjectUtils.isEmpty(getById(et.getIbzreportlyid()))) {
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
    public void saveBatch(List<IbzReportly> list) {
        List<IbzReportly> create = new ArrayList<>();
        List<IbzReportly> update = new ArrayList<>();
        for (IbzReportly et : list) {
            if (ObjectUtils.isEmpty(et.getIbzreportlyid()) || ObjectUtils.isEmpty(getById(et.getIbzreportlyid()))) {
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
    public IbzReportly submit(IbzReportly et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.IbzReportlyHelper.class).submit(et);
    }
	
	@Override
    @Transactional
    public boolean submitBatch (List<IbzReportly> etList) {
		 for(IbzReportly et : etList) {
		   submit(et);
		 }
	 	 return true;
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzReportly> searchDefault(IbzReportlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportly> pages=baseMapper.searchDefault(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzReportly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我所有的汇报
     */
    @Override
    public Page<IbzReportly> searchMyAllReportly(IbzReportlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportly> pages=baseMapper.searchMyAllReportly(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzReportly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我收到的汇报
     */
    @Override
    public Page<IbzReportly> searchMyReceived(IbzReportlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportly> pages=baseMapper.searchMyReceived(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzReportly>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的未提交汇报
     */
    @Override
    public Page<IbzReportly> searchMyReportlyMob(IbzReportlySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportly> pages=baseMapper.searchMyReportlyMob(context.getPages(), context, context.getSelectCond());
        return new PageImpl<IbzReportly>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzReportly> getIbzreportlyByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzReportly> getIbzreportlyByEntities(List<IbzReportly> entities) {
        List ids =new ArrayList();
        for(IbzReportly entity : entities){
            Serializable id=entity.getIbzreportlyid();
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





    public IIbzReportlyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzReportly dynamicCall(Long key, String action, IbzReportly et) {
        return et;
    }
}


