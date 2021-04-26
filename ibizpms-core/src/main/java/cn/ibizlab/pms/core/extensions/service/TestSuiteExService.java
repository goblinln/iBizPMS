package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.TestSuiteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[测试套件] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TestSuiteExService")
public class TestSuiteExService extends TestSuiteServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [LinkCase:关联测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestSuite linkCase(TestSuite et) {
        return super.linkCase(et);
    }
    /**
     * [UnlinkCase:未关联测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestSuite unlinkCase(TestSuite et) {
        return super.unlinkCase(et);
    }
}

