package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.TestModuleServiceImpl;
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
@Service("TestModuleExService")
public class TestModuleExService extends TestModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public Page<TestModule> searchDefault(TestModuleSearchContext context) {
        Map<String, Object> params = context.getParams();
        if(params.get("type") != null && "TestModule".equals(params.get("type"))) {
            return this.searchTestModule(context);
        }
        if(params.get("action") != null && "update".equals(params.get("action"))) {
            return this.searchParentModule(context);
        }
        return super.searchDefault(context);
    }

    @Override
    public Page<TestModule> searchTestModule(TestModuleSearchContext context) {
        Page<TestModule> page = super.searchTestModule(context);
        List<TestModule> list = new ArrayList<>();
        TestModule testModule = new TestModule();
        testModule.setRoot(context.getN_root_eq());
        testModule.setType(StaticDict.Module__type.STORY.getValue());
        testModule.setId(0L);
        testModule.setName("/");
        list.add(testModule);
        list.addAll(page.getContent());
        return new PageImpl<TestModule>(list, context.getPageable(), list.size());
    }
}
