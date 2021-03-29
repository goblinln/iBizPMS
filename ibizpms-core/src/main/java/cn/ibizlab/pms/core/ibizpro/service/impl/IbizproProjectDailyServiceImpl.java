package cn.ibizlab.pms.core.ibizpro.service.impl;

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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IbizproProjectDailyMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目日报] 服务对象接口实现
 */
@Slf4j
@Service("IbizproProjectDailyServiceImpl")
public class IbizproProjectDailyServiceImpl extends ServiceImpl<IbizproProjectDailyMapper, IbizproProjectDaily> implements IIbizproProjectDailyService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(IbizproProjectDaily et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproprojectdailyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IbizproProjectDaily> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(IbizproProjectDaily et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibizpro_projectdailyid", et.getIbizproprojectdailyid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbizproprojectdailyid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IbizproProjectDaily> list) {
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
    public IbizproProjectDaily get(String key) {
        IbizproProjectDaily et = getById(key);
        if(et == null){
            et = new IbizproProjectDaily();
            et.setIbizproprojectdailyid(key);
        }
        else {
        }
        return et;
    }

    @Override
    public IbizproProjectDaily getDraft(IbizproProjectDaily et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(IbizproProjectDaily et) {
        return (!ObjectUtils.isEmpty(et.getIbizproprojectdailyid())) && (!Objects.isNull(this.getById(et.getIbizproprojectdailyid())));
    }
    @Override
    @Transactional
    public boolean save(IbizproProjectDaily et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IbizproProjectDaily et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IbizproProjectDaily> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProjectDaily> create = new ArrayList<>();
        List<IbizproProjectDaily> update = new ArrayList<>();
        for (IbizproProjectDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproprojectdailyid()) || ObjectUtils.isEmpty(getById(et.getIbizproprojectdailyid()))) {
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
    public void saveBatch(List<IbizproProjectDaily> list) {
        list.forEach(item->fillParentData(item));
        List<IbizproProjectDaily> create = new ArrayList<>();
        List<IbizproProjectDaily> update = new ArrayList<>();
        for (IbizproProjectDaily et : list) {
            if (ObjectUtils.isEmpty(et.getIbizproprojectdailyid()) || ObjectUtils.isEmpty(getById(et.getIbizproprojectdailyid()))) {
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
    public IbizproProjectDaily sumProjectDaily(IbizproProjectDaily et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean sumProjectDailyBatch(List<IbizproProjectDaily> etList) {
        for(IbizproProjectDaily et : etList) {
            sumProjectDaily(et);
        }
        return true;
    }


	@Override
    public List<IbizproProjectDaily> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<IbizproProjectDaily>().eq("project",id));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IbizproProjectDaily> searchDefault(IbizproProjectDailySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IbizproProjectDaily> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IbizproProjectDaily>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(IbizproProjectDaily et){
        //实体关系[DER1N_IBIZPRO_PROJECTDAILY_ZT_PROJECT_PROJECT]
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

    @Override
    public List<IbizproProjectDaily> getIbizproprojectdailyByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IbizproProjectDaily> getIbizproprojectdailyByEntities(List<IbizproProjectDaily> entities) {
        List ids =new ArrayList();
        for(IbizproProjectDaily entity : entities){
            Serializable id=entity.getIbizproprojectdailyid();
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


    public IIbizproProjectDailyService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IbizproProjectDaily dynamicCall(String key, String action, IbizproProjectDaily et) {
        return et;
    }
}


