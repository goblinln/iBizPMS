package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.impl.TestReportServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实体[测试套件] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TestReportExService")
public class TestReportExService extends TestReportServiceImpl {

    @Autowired
    IFileService iFileService;
    @Autowired
    IActionService iActionService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(TestReport et) {
        String files = et.getFiles();
        if (!super.create(et)) {
            return false;
        }
        // 更新file
        FileHelper.updateObjectID(et.getId(), et.getObjecttype(), files, "", iFileService);
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.TESTREPORT.getValue());
        action.setObjectid(et.getId());
        action.setAction(StaticDict.Action__type.OPENED.getValue());
        action.setComment("");
        action.setExtra("");
        action.setActor(null);
        iActionService.createHis(action);
        return true;
    }

    @Override
    public boolean update(TestReport et) {
        TestReport old = new TestReport();
        CachedBeanCopier.copy(get(et.getId()), old);

        String files = et.getFiles();
        if (!super.update(et)) {
            return false;
        }
        FileHelper.updateObjectID(et.getId(), et.getObjecttype(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et, null, null, new String[]{"report"});
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.TESTREPORT.getValue());
        action.setObjectid(et.getId());
        action.setAction(StaticDict.Action__type.EDITED.getValue());
        action.setComment("");
        action.setExtra("");
        action.setActor(null);
        if (changes.size() > 0) {
            action.setHistorys(changes);
        }
        iActionService.createHis(action);
        return true;
    }
}

