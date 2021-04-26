package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.core.ibiz.service.impl.IbzCaseServiceImpl;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Primary
@Service("IbzCaseExService")
public class IbzCaseExService extends IbzCaseServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    ICaseService iCaseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzCase et) {
        List<IbzLibCaseSteps> list = et.getIbzlibcasesteps();
        List<CaseStep> caseStepList = new ArrayList<>();
        CachedBeanCopier.copy(list, caseStepList);
        Case cases = new Case();
        CachedBeanCopier.copy(et, cases);
        cases.setCasesteps(caseStepList);
        return iCaseService.create(cases);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(IbzCase et) {
        List<IbzLibCaseSteps> list = et.getIbzlibcasesteps();
        List<CaseStep> caseStepList = new ArrayList<>();
        CachedBeanCopier.copy(list, caseStepList);
        Case cases = new Case();
        CachedBeanCopier.copy(et, cases);
        cases.setCasesteps(caseStepList);
        return iCaseService.update(cases);
    }
}
