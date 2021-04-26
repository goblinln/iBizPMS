package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.service.ITestRunService;
import cn.ibizlab.pms.core.zentao.service.impl.TestTaskServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[测试版本] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TestTaskExService")
public class TestTaskExService extends TestTaskServiceImpl {

    @Autowired
    IActionService iActionService;

    @Autowired
    ICaseService iCaseService;

    @Autowired
    ITestRunService iTestRunService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(TestTask et) {
        if (!super.create(et)) {
            return false;
        }

        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),null,StaticDict.Action__type.EDITED.getValue(),
                "","", null,iActionService);
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

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),changes,StaticDict.Action__type.EDITED.getValue(),
                    comment,"", null,iActionService);

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
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        TestTask old = new TestTask();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setStatus(StaticDict.Testrun__status.DOING.getValue());

        super.update(et);

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),changes,StaticDict.Action__type.ACTIVATED.getValue(),
                    comment,"", null,iActionService);

        }

        return et;
    }
    /**
     * [Block:阻塞] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask block(TestTask et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        TestTask old = new TestTask();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setStatus(StaticDict.Testrun__status.BLOCKED.getValue());

        super.update(et);

        List<History> changes = ChangeUtil.diff(old, et);

        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),changes,StaticDict.Action__type.BLOCKED.getValue(),
                    comment,"", null,iActionService);

        }

        return et;
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask close(TestTask et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        TestTask old = new TestTask();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setStatus(StaticDict.Testrun__status.DONE.getValue());

        super.update(et);

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),changes,StaticDict.Action__type.CLOSED.getValue(),
                    comment,"", null,iActionService);

        }

        return et;
    }
    /**
     * [LinkCase:关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask linkCase(TestTask et) {
        if (et.get(FIELD_CASES) == null) {
            return et;
        }
        int i = 0;
        String[] versions = et.get(FIELD_VERSIONS).toString().split(MULTIPLE_CHOICE);
        for (String caseId : et.get(FIELD_CASES).toString().split(MULTIPLE_CHOICE)) {
            Case cas = iCaseService.get(Long.parseLong(caseId));
            Integer version = cas.getVersion();
            if (versions.length > i && versions[i] != null && !"".equals(versions[i])) {
                version = Integer.parseInt(versions[i]);
                TestRun testRun = iTestRunService.getOne(new QueryWrapper<TestRun>().eq(StaticDict.Action__object_type.TASK.getValue(), et.getId()).eq("`case`", cas.getId()).eq("`version`", version).last("limit 0,1"));
                if (testRun == null) {
                    testRun = new TestRun();
                    testRun.setTask(et.getId());
                    testRun.setIbizcase(cas.getId());
                    testRun.setVersion(version);
                    testRun.setStatus(StaticDict.Testrun__status.WAIT.getValue());
                    iTestRunService.create(testRun);
                }
                i++;
            }
        }
        return et;
    }
    /**
     * [Start:开始] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask start(TestTask et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        TestTask old = new TestTask();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setStatus(StaticDict.Testrun__status.DOING.getValue());

        super.update(et);

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TESTTASK.getValue(),changes,StaticDict.Action__type.STARTED.getValue(),
                    comment,"", null,iActionService);

        }

        return et;
    }
    /**
     * [UnlinkCase:关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestTask unlinkCase(TestTask et) {

        if (et.get(FIELD_CASES) == null) {
            return et;
        }
        for (String caseId : et.get(FIELD_CASES).toString().split(MULTIPLE_CHOICE)) {
            Case cas = iCaseService.get(Long.parseLong(caseId));
            iTestRunService.remove(new QueryWrapper<TestRun>().eq(StaticDict.Action__object_type.TASK.getValue(),et.getId()).eq("`case`",cas.getId()).eq("`version`",cas.getVersion()));
        }

        return et;
    }
}

