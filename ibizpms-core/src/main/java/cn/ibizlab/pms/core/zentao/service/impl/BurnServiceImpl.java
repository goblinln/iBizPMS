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
import cn.ibizlab.pms.core.zentao.domain.Burn;
import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;
import cn.ibizlab.pms.core.zentao.service.IBurnService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.BurnMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[burn] 服务对象接口实现
 */
@Slf4j
@Service("BurnServiceImpl")
public class BurnServiceImpl extends ServiceImpl<BurnMapper, Burn> implements IBurnService {

    @Autowired
    cn.ibizlab.pms.core.zentao.runtime.BurnRuntime burnRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(Burn et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Burn> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(Burn et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<Burn> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(Burn et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
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
    public Burn get(String key) {
        Burn et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
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
    public Burn sysGet(String key) {
        Burn et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public Burn getDraft(Burn et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(Burn et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Burn computeBurn(Burn et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean computeBurnBatch(List<Burn> etList) {
        for(Burn et : etList) {
            computeBurn(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(Burn et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Burn et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Burn> list) {
        list.forEach(item->fillParentData(item));
        List<Burn> create = new ArrayList<>();
        List<Burn> update = new ArrayList<>();
        for (Burn et : list) {
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
    public void saveBatch(List<Burn> list) {
        list.forEach(item->fillParentData(item));
        List<Burn> create = new ArrayList<>();
        List<Burn> update = new ArrayList<>();
        for (Burn et : list) {
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
    public List<Burn> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<Burn>().eq("project",id));
    }

	@Override
    public List<Burn> selectByTask(Long id) {
        return baseMapper.selectByTask(id);
    }
    @Override
    public void removeByTask(Long id) {
        this.remove(new QueryWrapper<Burn>().eq("task",id));
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Burn> searchDefault(BurnSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Burn> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Burn>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 燃尽图预计（含周末）
     */
    @Override
    public Page<Burn> searchESTIMATEANDLEFT(BurnSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Burn> pages=baseMapper.searchESTIMATEANDLEFT(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Burn>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Burn et){
        //实体关系[DER1N_ZT_BURN_ZT_TASK_TASK]
        if(!ObjectUtils.isEmpty(et.getTask())){
            cn.ibizlab.pms.core.zentao.domain.Task zttask=et.getZttask();
            if(ObjectUtils.isEmpty(zttask)){
                cn.ibizlab.pms.core.zentao.domain.Task majorEntity=taskService.get(et.getTask());
                et.setZttask(majorEntity);
                zttask=majorEntity;
            }
            et.setConsumed(zttask.getConsumed());
            et.setLeft(zttask.getLeft());
            et.setEstimate(zttask.getEstimate());
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
    public List<Burn> getBurnByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Burn> getBurnByEntities(List<Burn> entities) {
        List ids =new ArrayList();
        for(Burn entity : entities){
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


    public IBurnService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Burn dynamicCall(String key, String action, Burn et) {
        return et;
    }
}



