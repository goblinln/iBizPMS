package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.ProjectModuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
        if(params.get("allmodules") != null && "1".equals(params.get("allmodules"))) {
            return super.searchTaskModules(context);
        }else if(params.get("allmodules") != null && "0".equals(params.get("allmodules"))) {
            return super.searchTaskModules(context);
        }
        return super.searchDefault(context);
    }
}
