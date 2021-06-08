package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.TestModuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Primary
@Service("TestModuleExService")
public class TestModuleExService extends TestModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public Page<TestModule> searchDefault(TestModuleSearchContext context) {
        Map<String, Object> params = context.getParams();
        if(params.get("action") != null && "update".equals(params.get("action"))) {
            super.searchParentModule(context);
        }
        return super.searchDefault(context);
    }
}
