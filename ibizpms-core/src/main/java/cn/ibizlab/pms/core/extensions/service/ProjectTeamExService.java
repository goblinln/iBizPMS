package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.ProjectTeamServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
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
}

