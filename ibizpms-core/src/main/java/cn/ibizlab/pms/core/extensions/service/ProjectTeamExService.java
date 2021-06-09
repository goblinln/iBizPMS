package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.ProjectTeamServiceImpl;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.util.dict.StaticDict;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目团队] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProjectTeamExService")
public class ProjectTeamExService extends ProjectTeamServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    ISysEmployeeService iSysEmployeeService;

    /**
     * [GetUserRole:获取成员角色] 行为扩展：根据成员获取成员的角色信息
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTeam getUserRole(ProjectTeam et) {
        return super.getUserRole(et);
    }

    @Override
    public Page<ProjectTeam> searchTaskCntEstimateConsumedLeft(ProjectTeamSearchContext context) {
        Map<String, Object> params = context.getParams();
        List<ProjectTeam> projectTeams = new ArrayList<>();
        if(params.get("teams") != null) {
            projectTeams.addAll(super.selectRowEditDefault(context));
        }else {
            projectTeams.addAll(super.selectTaskCntEstimateConsumedLeft(context));
        }
        if(params.get("dept") != null) {
            List<SysEmployee> list = iSysEmployeeService.selectByMdeptid(params.get("dept").toString());
            Long root = params.get("root") != null ? Long.parseLong(params.get("root").toString()) : 0L;
            for(SysEmployee sysEmployee : list) {
                ProjectTeam projectTeam = new ProjectTeam();
                projectTeam.setRoot(root);
                projectTeam.setDays(5);
                projectTeam.setHours(7D);
                projectTeam.setLimited(StaticDict.YesNo.ITEM_0.getValue());
                projectTeam.setAccount(sysEmployee.getLoginname());
                projectTeams.add(projectTeam);
            }
        }
        return new PageImpl<ProjectTeam>(projectTeams, context.getPageable(), projectTeams.size());
    }
}

