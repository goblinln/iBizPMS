package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.impl.TestTaskServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IActionService iActionService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(TestTask et) {
        if (!super.create(et)) {
            return false;
        }
        // 创建日志
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.TESTTASK.getValue());
        action.setObjectid(et.getId());
        action.setAction(StaticDict.Action__type.OPENED.getValue());
        action.setComment("");
        action.setExtra("");
        iActionService.createHis(action);
        return true;
    }


    @Override
    public boolean update(TestTask et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        TestTask old = new TestTask();
        CachedBeanCopier.copy(get(et.getId()), old);

        if(!super.update(et)) {
            return false ;
        }
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = new Action();
            action.setObjecttype(StaticDict.Action__object_type.TESTTASK.getValue());
            action.setObjectid(et.getId());
            action.setAction( StaticDict.Action__type.EDITED.getValue());
            action.setComment(comment);
            action.setExtra("");
            action.setActor(null);
            if (changes.size() > 0) {
               action.setHistorys(changes);
            }
            iActionService.createHis(action);
        }
        return true;
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

