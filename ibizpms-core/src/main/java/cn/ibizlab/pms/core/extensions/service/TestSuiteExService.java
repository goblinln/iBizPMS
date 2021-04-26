package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.service.ISuiteCaseService;
import cn.ibizlab.pms.core.zentao.service.impl.TestSuiteServiceImpl;
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

/**
 * 实体[测试套件] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TestSuiteExService")
public class TestSuiteExService extends TestSuiteServiceImpl {

    @Autowired
    IActionService iActionService;
    @Autowired
    ICaseService iCaseService;
    @Autowired
    ISuiteCaseService iSuiteCaseService;

    /**
     * 版本属性名称
     */
    public static String FIELD_VERSION = "version";

    /**
     * 版本列表属性名称
     */
    public static String FIELD_VERSIONS = "versions";

    /**
     * 测试用例列表属性名称
     */
    public static String FIELD_CASES = "cases";

    /**
     * 套件属性名称
     */
    public static String FIELD_SUITE = "suite";

    /**
     * 多项选择分隔符
     */
    public static String MULTIPLE_CHOICE = ",";


    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(TestSuite et) {
        if (!super.create(et)) {
            return false;
        }
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.TESTSUITE.getValue());
        action.setObjectid(et.getId());
        action.setAction( StaticDict.Action__type.OPENED.getValue());
        action.setComment("");
        action.setExtra("");
        action.setActor(null);
        iActionService.createHis(action);
        return true;
    }

    @Override
    public boolean update(TestSuite et) {
        TestSuite old = new TestSuite();
        CachedBeanCopier.copy(get(et.getId()), old);

        if (!super.update(et)) {
            return false;
        }
        List<History> changes = ChangeUtil.diff(old, et,null,null,new String[]{"desc"});
            Action action = new Action();
            action.setObjecttype(StaticDict.Action__object_type.TESTSUITE.getValue());
            action.setObjectid(et.getId());
            action.setAction( StaticDict.Action__type.EDITED.getValue());
            action.setComment("");
            action.setExtra("");
            action.setActor(null);
            if (changes.size() > 0) {
                action.setHistorys(changes);
            }
            iActionService.createHis(action);
        return true;

    }

    /**
     * [LinkCase:关联测试] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TestSuite linkCase(TestSuite et) {
        if (et.get(FIELD_CASES) == null) {
            return et;
        }
        int i = 0;
        String[] versions = et.get(FIELD_VERSIONS).toString().split(MULTIPLE_CHOICE);
        for (String caseId : et.get(FIELD_CASES).toString().split(MULTIPLE_CHOICE)) {
            Case cas = iCaseService.get(Long.parseLong(caseId));
            Integer version = cas.getVersion();
            if(versions.length > i && versions[i] != null && !"".equals(versions[i])) {
                version = Integer.parseInt(versions[i]);
            }
            SuiteCase suiteCase = iSuiteCaseService.getOne(new QueryWrapper<SuiteCase>().eq(FIELD_SUITE, et.getId()).eq("`case`", cas.getId()).eq("`version`", version).last("limit 0,1"));
            if (suiteCase == null) {
                suiteCase = new SuiteCase();
                suiteCase.setSuite(et.getId());
                suiteCase.setIbizcase(cas.getId());
                suiteCase.setVersion(version);
                iSuiteCaseService.create(suiteCase);
            }
            i ++;
        }
        return et;
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

