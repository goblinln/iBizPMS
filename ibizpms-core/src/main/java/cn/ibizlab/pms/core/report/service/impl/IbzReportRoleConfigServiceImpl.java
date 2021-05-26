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
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.filter.IbzReportRoleConfigSearchContext;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.report.mapper.IbzReportRoleConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[汇报角色配置] 服务对象接口实现
 */
@Slf4j
@Service("IbzReportRoleConfigServiceImpl")
public class IbzReportRoleConfigServiceImpl extends ServiceImpl<IbzReportRoleConfigMapper, IbzReportRoleConfig> implements IIbzReportRoleConfigService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.report.runtime.IbzReportRoleConfigRuntime ibzreportroleconfigRuntime;


    protected int batchSize = 500;

    @Override
    public List<IbzReportRoleConfig> select(IbzReportRoleConfigSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportRoleConfig> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IbzReportRoleConfig et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzreportroleconfigid()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(IbzReportRoleConfig et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_report_role_configid", et.getIbzreportroleconfigid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzreportroleconfigid()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(IbzReportRoleConfig et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_report_role_configid", et.getIbzreportroleconfigid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzreportroleconfigid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!ibzreportroleconfigRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public IbzReportRoleConfig get(String key) {
        IbzReportRoleConfig et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!ibzreportroleconfigRuntime.isRtmodel()){
            }
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IbzReportRoleConfig sysGet(String key) {
        IbzReportRoleConfig et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IbzReportRoleConfig getDraft(IbzReportRoleConfig et) {
        return et;
    }

    @Override
    public boolean checkKey(IbzReportRoleConfig et) {
        return (!ObjectUtils.isEmpty(et.getIbzreportroleconfigid())) && (!Objects.isNull(this.getById(et.getIbzreportroleconfigid())));
    }
    @Override
    @Transactional
    public boolean save(IbzReportRoleConfig et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzReportRoleConfig et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }




    public List<IbzReportRoleConfig> selectDefault(IbzReportRoleConfigSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IbzReportRoleConfig> selectView(IbzReportRoleConfigSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbzReportRoleConfig> searchDefault(IbzReportRoleConfigSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzReportRoleConfig> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzReportRoleConfig>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<IbzReportRoleConfig> getIbzreportroleconfigByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbzReportRoleConfig> getIbzreportroleconfigByEntities(List<IbzReportRoleConfig> entities) {
        List ids =new ArrayList();
        for(IbzReportRoleConfig entity : entities){
            Serializable id=entity.getIbzreportroleconfigid();
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


    public IIbzReportRoleConfigService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzReportRoleConfig dynamicCall(String key, String action, IbzReportRoleConfig et) {
        return et;
    }
}



