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
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProjectTeamMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目团队] 服务对象接口实现
 */
@Slf4j
@Service("ProjectTeamServiceImpl")
public class ProjectTeamServiceImpl extends ServiceImpl<ProjectTeamMapper, ProjectTeam> implements IProjectTeamService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProjectTeam et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProjectTeam> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(ProjectTeam et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProjectTeam> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProjectTeam et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
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
    public ProjectTeam get(Long key) {
        ProjectTeam et = getById(key);
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
    public ProjectTeam sysGet(Long key) {
        ProjectTeam et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProjectTeam getDraft(ProjectTeam et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(ProjectTeam et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProjectTeam getUserRole(ProjectTeam et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean getUserRoleBatch(List<ProjectTeam> etList) {
        for(ProjectTeam et : etList) {
            getUserRole(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(ProjectTeam et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProjectTeam et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProjectTeam> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectTeam> create = new ArrayList<>();
        List<ProjectTeam> update = new ArrayList<>();
        for (ProjectTeam et : list) {
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
    public void saveBatch(List<ProjectTeam> list) {
        list.forEach(item->fillParentData(item));
        List<ProjectTeam> create = new ArrayList<>();
        List<ProjectTeam> update = new ArrayList<>();
        for (ProjectTeam et : list) {
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
    public List<ProjectTeam> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<ProjectTeam>().eq("root",id));
    }

    public IProjectTeamService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByRoot(Long id,List<ProjectTeam> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<ProjectTeam> _update=new ArrayList<ProjectTeam>();
        List<ProjectTeam> _create=new ArrayList<ProjectTeam>();
        for(ProjectTeam before:selectByRoot(id)){
            delIds.add(before.getId());
        }
        for(ProjectTeam sub:list) {
            sub.setRoot(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProjectTeam> searchDefault(ProjectTeamSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectTeam> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectTeam>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目成员（项目经理）
     */
    @Override
    public Page<ProjectTeam> searchProjectTeamPm(ProjectTeamSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectTeam> pages=baseMapper.searchProjectTeamPm(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectTeam>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 行编辑查询
     */
    @Override
    public Page<ProjectTeam> searchRowEditDefault(ProjectTeamSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectTeam> pages=baseMapper.searchRowEditDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectTeam>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据查询
     */
    @Override
    public Page<ProjectTeam> searchTaskCntEstimateConsumedLeft(ProjectTeamSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectTeam> pages=baseMapper.searchTaskCntEstimateConsumedLeft(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectTeam>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(ProjectTeam et){
        //实体关系[DER1N_IBZ_PROJECTTEAM_ZT_PROJECT_ROOT]
        if(!ObjectUtils.isEmpty(et.getRoot())){
            cn.ibizlab.pms.core.zentao.domain.Project projectteam=et.getProjectteam();
            if(ObjectUtils.isEmpty(projectteam)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getRoot());
                et.setProjectteam(majorEntity);
                projectteam=majorEntity;
            }
            et.setPm(projectteam.getPm());
            et.setProjectname(projectteam.getName());
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
    @Transactional
    public ProjectTeam dynamicCall(Long key, String action, ProjectTeam et) {
        return et;
    }
}



