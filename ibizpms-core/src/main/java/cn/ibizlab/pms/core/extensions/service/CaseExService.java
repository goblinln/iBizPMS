package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Case;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[测试用例] 自定义服务对象
 */
@Slf4j
@Primary
@Service("CaseExService")
public class CaseExService extends CaseServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ConfirmChange:确认用例变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case confirmChange(Case et) {
        return super.confirmChange(et);
    }
    /**
     * [Confirmstorychange:确认需求变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case confirmstorychange(Case et) {
        return super.confirmstorychange(et);
    }
    /**
     * [GetByTestTask:根据测试单获取或者状态] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case getByTestTask(Case et) {
        return super.getByTestTask(et);
    }
    /**
     * [GetTestTaskCntRun:获取测试单执行结果] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case getTestTaskCntRun(Case et) {
        return super.getTestTaskCntRun(et);
    }
    /**
     * [LinkCase:测试单关联测试用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case linkCase(Case et) {
        return super.linkCase(et);
    }
    /**
     * [MobLinkCase:移动端关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case mobLinkCase(Case et) {
        return super.mobLinkCase(et);
    }
    /**
     * [RunCase:执行测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case runCase(Case et) {
        return super.runCase(et);
    }
    /**
     * [TestRunCase:执行测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case testRunCase(Case et) {
        return super.testRunCase(et);
    }
    /**
     * [TestsuitelinkCase:套件关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case testsuitelinkCase(Case et) {
        return super.testsuitelinkCase(et);
    }
    /**
     * [UnlinkCase:移除用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case unlinkCase(Case et) {
        return super.unlinkCase(et);
    }
    /**
     * [UnlinkSuiteCase:移除用例] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Case unlinkSuiteCase(Case et) {
        return super.unlinkSuiteCase(et);
    }
}

