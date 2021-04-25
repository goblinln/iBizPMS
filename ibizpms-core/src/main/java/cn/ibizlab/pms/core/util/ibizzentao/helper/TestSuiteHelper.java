package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.mapper.TestSuiteMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class TestSuiteHelper extends ZTBaseHelper<TestSuiteMapper, TestSuite> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    CaseHelper caseHelper;

    @Autowired
    SuiteCaseHelper suiteCaseHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(TestSuite et) {

        if (!super.create(et)) {
            return false;
        }
        actionHelper.create(StaticDict.Action__object_type.TESTSUITE.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(TestSuite et) {

        TestSuite old = new TestSuite();
        CachedBeanCopier.copy(get(et.getId()), old);

        if (!super.edit(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et,null,null,new String[]{"desc"});
        Action action = actionHelper.create(StaticDict.Action__object_type.TESTSUITE.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
        if (changes.size() > 0) {
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public TestSuite linkCase(TestSuite et) {
        if (et.get(FIELD_CASES) == null) {
            return et;
        }
        int i = 0;
        String[] versions = et.get(FIELD_VERSIONS).toString().split(MULTIPLE_CHOICE);
        for (String caseId : et.get(FIELD_CASES).toString().split(MULTIPLE_CHOICE)) {
            Case cas = caseHelper.get(Long.parseLong(caseId));
            Integer version = cas.getVersion();
            if(versions.length > i && versions[i] != null && !"".equals(versions[i])) {
                version = Integer.parseInt(versions[i]);
            }
            SuiteCase suiteCase = suiteCaseHelper.getOne(new QueryWrapper<SuiteCase>().eq(FIELD_SUITE, et.getId()).eq("`case`", cas.getId()).eq("`version`", version).last("limit 0,1"));
            if (suiteCase == null) {
                suiteCase = new SuiteCase();
                suiteCase.setSuite(et.getId());
                suiteCase.setIbizcase(cas.getId());
                suiteCase.setVersion(version);
                suiteCaseHelper.create(suiteCase);
            }
            i ++;
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public TestSuite unlinkCase(TestSuite et) {
        return et;
    }
}
