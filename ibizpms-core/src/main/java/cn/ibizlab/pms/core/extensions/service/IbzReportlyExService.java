package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.report.service.impl.IbzReportlyServiceImpl;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.util.*;

/**
 * 实体[汇报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzReportlyExService")
public class IbzReportlyExService extends IbzReportlyServiceImpl {

    @Autowired
    IFileService iFileService;
    @Autowired
    IActionService iActionService;


    String[] diffAttrs = {"content"};

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(IbzReportly et) {
        String files = et.getFiles();
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);
        FileHelper.updateObjectID(et.getIbzreportlyid(), StaticDict.File__object_type.REPORTLY.getValue(), files, "", iFileService);
        ActionHelper.createHis(et.getIbzreportlyid(), StaticDict.Action__object_type.REPORTLY.getValue(), null, StaticDict.Action__type.OPENED.getValue(), "", "", null, iActionService);
        return true;

    }

    @Override
    public boolean update(IbzReportly et) {
        IbzReportly old = new IbzReportly();
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), old);
        String files = et.getFiles();
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_reportlyid", et.getIbzreportlyid()))) {
            return false;
        }

        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);

        FileHelper.updateObjectID(et.getIbzreportlyid(), StaticDict.File__object_type.REPORTLY.getValue(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            ActionHelper.createHis(et.getIbzreportlyid(), StaticDict.Action__object_type.REPORTLY.getValue(), changes, strAction,
                    "", "", null, iActionService);
        }
        return true;

    }

    /**
     * [HaveRead:已读] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzReportly haveRead(IbzReportly et) {
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);
        List<Action> list = iActionService.list(new QueryWrapper<Action>().eq("objecttype", StaticDict.Action__object_type.REPORTLY.getValue()).eq("action", StaticDict.Action__type.READ.getValue()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("objectid", et.getIbzreportlyid()));
        if (list.size() == 0) {
            ActionHelper.createHis(et.getIbzreportlyid(), StaticDict.Action__object_type.REPORTLY.getValue(), null, StaticDict.Action__type.READ.getValue(), "", "", null, iActionService);
        }
        return et;
    }

    /**
     * [Submit:提交] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzReportly submit(IbzReportly et) {
        IbzReportly old = new IbzReportly();
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), old);
        et.setIssubmit(StaticDict.YesNo.ITEM_1.getValue());
        et.setSubmittime(ZTDateUtil.now());
        boolean flag = old.getContent() == null || old.getReportto() == null;
        if (flag) {
            throw new RuntimeException("请填写工作内容并且指定汇报人后提交！");
        }
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_reportlyid", et.getIbzreportlyid()))) {
            return et;
        }

        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);
        String strAction = StaticDict.Action__type.SUBMIT.getValue();
        ActionHelper.createHis(et.getIbzreportlyid(), StaticDict.Action__object_type.REPORTLY.getValue(), null, strAction,
                "", "", null, iActionService);

        // 给汇报人，抄送人 待阅
        String ss = "已经提交给您了，请查收哦！";
        ActionHelper.sendTodoOrToread(et.getIbzreportlyid(), et.getIbzreportlyname() + ss, "", et.getReportto(), et.getMailto(), "汇报", StaticDict.Action__object_type.REPORTLY.getValue(), "ibzreportlies", StaticDict.Action__type.SUBMIT.getText(), false, iActionService);
        return et;
    }
}

