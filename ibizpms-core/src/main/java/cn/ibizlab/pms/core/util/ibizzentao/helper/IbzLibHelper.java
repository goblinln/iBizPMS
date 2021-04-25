package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.mapper.IbzLibMapper;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
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
public class IbzLibHelper  extends ZTBaseHelper<IbzLibMapper, IbzLib>  {

    @Autowired
    TestSuiteHelper testSuiteHelper;

    @Autowired
    ActionHelper actionHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzLib et) {
        if (!super.create(et)) {
            return false;
        }
        actionHelper.create(StaticDict.Action__object_type.CASELIB.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(IbzLib et) {
        IbzLib old = new IbzLib();
        CachedBeanCopier.copy(get(et.getId()), old);

        if (!super.edit(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.CASELIB.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        return testSuiteHelper.delete(key);
    }
}
