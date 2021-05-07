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
import cn.ibizlab.pms.core.ibiz.domain.EmpLoyeeload;
import cn.ibizlab.pms.core.ibiz.filter.EmpLoyeeloadSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IEmpLoyeeloadService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.EmpLoyeeloadMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[员工负载表] 服务对象接口实现
 */
@Slf4j
@Service("EmpLoyeeloadServiceImpl")
public class EmpLoyeeloadServiceImpl extends ServiceImpl<EmpLoyeeloadMapper, EmpLoyeeload> implements IEmpLoyeeloadService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.EmpLoyeeloadRuntime employeeloadRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    public List<EmpLoyeeload> select(EmpLoyeeloadSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<EmpLoyeeload> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(EmpLoyeeload et) {
        if(!employeeloadRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<EmpLoyeeload> list) {
        if(!employeeloadRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(EmpLoyeeload et) {
        if(!employeeloadRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<EmpLoyeeload> list) {
        if(!employeeloadRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(EmpLoyeeload et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!employeeloadRuntime.isRtmodel()){
        }
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
    public EmpLoyeeload get(Long key) {
        EmpLoyeeload et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        if(!employeeloadRuntime.isRtmodel()){
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
    public EmpLoyeeload sysGet(Long key) {
        EmpLoyeeload et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public EmpLoyeeload getDraft(EmpLoyeeload et) {
        if(!employeeloadRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(EmpLoyeeload et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(EmpLoyeeload et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(EmpLoyeeload et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<EmpLoyeeload> list) {
        if(!employeeloadRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<EmpLoyeeload> create = new ArrayList<>();
        List<EmpLoyeeload> update = new ArrayList<>();
        for (EmpLoyeeload et : list) {
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
    public void saveBatch(List<EmpLoyeeload> list) {
        if(!employeeloadRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<EmpLoyeeload> create = new ArrayList<>();
        List<EmpLoyeeload> update = new ArrayList<>();
        for (EmpLoyeeload et : list) {
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
    public List<EmpLoyeeload> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<EmpLoyeeload>().eq("project",id));
    }


    public List<EmpLoyeeload> selectDefault(EmpLoyeeloadSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<EmpLoyeeload> selectGETWOERKLOAD(EmpLoyeeloadSearchContext context){
        return baseMapper.selectGETWOERKLOAD(context, context.getSelectCond());
    }
    public List<EmpLoyeeload> selectView(EmpLoyeeloadSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<EmpLoyeeload> searchDefault(EmpLoyeeloadSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<EmpLoyeeload> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<EmpLoyeeload>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取员工负载表
     */
    @Override
    public Page<EmpLoyeeload> searchGETWOERKLOAD(EmpLoyeeloadSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<EmpLoyeeload> pages=baseMapper.searchGETWOERKLOAD(context.getPages(),context,context.getSelectCond());
        return new PageImpl<EmpLoyeeload>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(EmpLoyeeload et){
        //实体关系[DER1N_IBZ_EMPLOYEELOAD_ZT_PROJECT_PROJECT]
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



    public IEmpLoyeeloadService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public EmpLoyeeload dynamicCall(Long key, String action, EmpLoyeeload et) {
        return et;
    }
}



