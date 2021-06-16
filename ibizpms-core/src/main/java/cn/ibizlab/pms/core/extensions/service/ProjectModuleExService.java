package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.ProjectModuleServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Service("ProjectModuleExService")
public class ProjectModuleExService extends ProjectModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public Page<ProjectModule> searchDefault(ProjectModuleSearchContext context) {
        Map<String, Object> params = context.getParams();
        if(params.get("type") != null && "TaskModules".equals(params.get("type"))) {
            return this.searchTaskModules(context);
        }
        if(params.get("allmodules") != null && "1".equals(params.get("allmodules"))) {
            return this.searchTaskModules(context);
        }else if(params.get("allmodules") != null && "0".equals(params.get("allmodules"))) {
            return this.searchTaskModules(context);
        }
        if(params.get("action") != null && "update".equals(params.get("action"))) {
            this.searchParentModule(context);
        }
        return super.searchDefault(context);
    }

    @Override
    public Page<ProjectModule> searchTaskModules(ProjectModuleSearchContext context) {
        Page<ProjectModule> page = super.searchTaskModules(context);
        List<ProjectModule> list = new ArrayList<>();
        ProjectModule projectModule = new ProjectModule();
        projectModule.setRoot(context.getN_root_eq());
        projectModule.setType(StaticDict.Module__type.TASK.getValue());
        projectModule.setId(0L);
        projectModule.setName("/");
        list.add(projectModule);
        list.addAll(page.getContent());
        return new PageImpl<ProjectModule>(list, context.getPageable(), list.size());
    }
}
