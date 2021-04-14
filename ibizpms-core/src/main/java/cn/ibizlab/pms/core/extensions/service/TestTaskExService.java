package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.TestTaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[测试版本] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TestTaskExService")
public class TestTaskExService extends TestTaskServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:激活] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask activate(TestTask et) {
        return super.activate(et);
    }
    /**
     * [Block:阻塞] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask block(TestTask et) {
        return super.block(et);
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask close(TestTask et) {
        return super.close(et);
    }
    /**
     * [LinkCase:关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask linkCase(TestTask et) {
        return super.linkCase(et);
    }
    /**
     * [Start:开始] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask start(TestTask et) {
        return super.start(et);
    }
    /**
     * [UnlinkCase:关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask unlinkCase(TestTask et) {
        return super.unlinkCase(et);
    }
}

