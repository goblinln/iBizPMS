package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.core.ibiz.mapper.IbzCaseMapper;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class IbzCaseHelper extends ZTBaseHelper<IbzCaseMapper, IbzCase> {

    @Autowired
    CaseHelper caseHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzCase et) {
        List<IbzLibCaseSteps> list = et.getIbzlibcasestepss();
        List<CaseStep> caseStepList = new ArrayList<>();
        CachedBeanCopier.copy(list, caseStepList);
        Case cases = new Case();
        CachedBeanCopier.copy(et, cases);
        cases.setCasesteps(caseStepList);
        return caseHelper.create(cases);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(IbzCase et) {
        List<IbzLibCaseSteps> list = et.getIbzlibcasestepss();
        List<CaseStep> caseStepList = new ArrayList<>();
        CachedBeanCopier.copy(list, caseStepList);
        Case cases = new Case();
        CachedBeanCopier.copy(et, cases);
        cases.setCasesteps(caseStepList);
        return caseHelper.edit(cases);
    }

    @Override
    public boolean delete(Long key) {
        return caseHelper.delete(key);
    }
}
