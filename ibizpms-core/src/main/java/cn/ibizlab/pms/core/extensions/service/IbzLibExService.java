package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.service.impl.IbzLibServiceImpl;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.ITestSuiteService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import liquibase.pro.packaged.I;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Primary
@Service("IbzLibExService")
public class IbzLibExService extends IbzLibServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    ITestSuiteService iTestSuiteService;

    @Autowired
    IActionService iActionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzLib et) {
        if (!super.create(et)) {
            return false;
        }
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.CASELIB.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                "","", null,iActionService);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(IbzLib et) {
        IbzLib old = new IbzLib();
        CachedBeanCopier.copy(get(et.getId()), old);

        if (!super.update(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.CASELIB.getValue(),changes,StaticDict.Action__type.EDITED.getValue(),
                    "","", null,iActionService);
        }
        return true;
    }

}
