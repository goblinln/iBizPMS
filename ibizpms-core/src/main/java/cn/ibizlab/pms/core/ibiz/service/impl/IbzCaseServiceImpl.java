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
import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.core.ibiz.filter.IbzCaseSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzCaseService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.IbzCaseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试用例] 服务对象接口实现
 */
@Slf4j
@Service("IbzCaseServiceImpl")
public class IbzCaseServiceImpl extends ServiceImpl<IbzCaseMapper, IbzCase> implements IIbzCaseService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepsService ibzlibcasestepsService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzLibModuleService ibzlibmoduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIbzLibService ibzlibService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbzCase et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        ibzlibcasestepsService.saveByIbizcase(et.getId(), et.getIbzlibcasesteps());
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbzCase> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbzCase et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        ibzlibcasestepsService.saveByIbizcase(et.getId(), et.getIbzlibcasesteps());
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbzCase> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(IbzCase et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        ibzlibcasestepsService.removeByIbizcase(key) ;
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
    public IbzCase get(Long key) {
        IbzCase et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            et.setIbzlibcasesteps(ibzlibcasestepsService.selectByIbizcase(key));
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IbzCase sysGet(Long key) {
        IbzCase et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public IbzCase getDraft(IbzCase et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(IbzCase et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(IbzCase et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbzCase et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbzCase> list) {
        list.forEach(item->fillParentData(item));
        List<IbzCase> create = new ArrayList<>();
        List<IbzCase> update = new ArrayList<>();
        for (IbzCase et : list) {
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
    public void saveBatch(List<IbzCase> list) {
        list.forEach(item->fillParentData(item));
        List<IbzCase> create = new ArrayList<>();
        List<IbzCase> update = new ArrayList<>();
        for (IbzCase et : list) {
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
    public List<IbzCase> selectByModule(Long id) {
        return baseMapper.selectByModule(id);
    }
    @Override
    public void removeByModule(Long id) {
        this.remove(new QueryWrapper<IbzCase>().eq("module",id));
    }

	@Override
    public List<IbzCase> selectByLib(Long id) {
        return baseMapper.selectByLib(id);
    }
    @Override
    public void removeByLib(Long id) {
        this.remove(new QueryWrapper<IbzCase>().eq("lib",id));
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<IbzCase> searchDefault(IbzCaseSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbzCase> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbzCase>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbzCase et){
        //实体关系[DER1N_IBZ_CASE_IBZ_LIBMODULE_MODULE]
        if(!ObjectUtils.isEmpty(et.getModule())){
            cn.ibizlab.pms.core.ibiz.domain.IbzLibModule libmodule=et.getLibmodule();
            if(ObjectUtils.isEmpty(libmodule)){
                cn.ibizlab.pms.core.ibiz.domain.IbzLibModule majorEntity=ibzlibmoduleService.get(et.getModule());
                et.setLibmodule(majorEntity);
                libmodule=majorEntity;
            }
            et.setModulename(libmodule.getName());
        }
        //实体关系[DER1N_IBZ_CASE_IBZ_LIB_LIB]
        if(!ObjectUtils.isEmpty(et.getLib())){
            cn.ibizlab.pms.core.ibiz.domain.IbzLib caselib=et.getCaselib();
            if(ObjectUtils.isEmpty(caselib)){
                cn.ibizlab.pms.core.ibiz.domain.IbzLib majorEntity=ibzlibService.get(et.getLib());
                et.setCaselib(majorEntity);
                caselib=majorEntity;
            }
            et.setLibname(caselib.getName());
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



    public IIbzCaseService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbzCase dynamicCall(Long key, String action, IbzCase et) {
        return et;
    }
}



