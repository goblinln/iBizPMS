package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.mapper.TestReportMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
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
public class TestReportHelper extends ZTBaseHelper<TestReportMapper, TestReport> {

    @Autowired
    ActionHelper actionHelper;
    @Autowired
    FileHelper fileHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(TestReport et) {
        String files = et.getFiles();
        if (!super.create(et)) {
            return false;
        }
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TESTREPORT.getValue(),files, "");
        actionHelper.create(StaticDict.Action__object_type.TESTREPORT.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(TestReport et) {
        TestReport old = new TestReport();
        CachedBeanCopier.copy(get(et.getId()), old);

        String files = et.getFiles();
        if (!super.edit(et)) {
            return false;
        }
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TESTREPORT.getValue(),files, "");
        List<History> changes = ChangeUtil.diff(old, et,null,null,new String[]{"report"});
        Action action = actionHelper.create(StaticDict.Action__object_type.TESTREPORT.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
        if (changes.size() > 0) {
            actionHelper.logHistory(action.getId(), changes);
        }

        return true;
    }
}
