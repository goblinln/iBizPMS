package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.mapper.IbzReportlyMapper;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 吴龙虎
 */

@Component
@Slf4j
public class IbzReportlyHelper extends ZTBaseHelper<IbzReportlyMapper, IbzReportly> {

    @Autowired
    FileHelper fileHelper;

    @Autowired
    ActionHelper actionHelper;

    String[] diffAttrs = {"content"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzReportly et) {
        String files = et.getFiles();
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);
        fileHelper.updateObjectID(et.getIbzreportlyid(), StaticDict.File__object_type.REPORTLY.getValue(), files, "");
        actionHelper.create(StaticDict.Action__object_type.REPORTLY.getValue(), et.getIbzreportlyid(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(IbzReportly et) {
        IbzReportly old = new IbzReportly();
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), old);
        String files = et.getFiles();
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq("Ibz_reportlyid", et.getIbzreportlyid()))) {
            return false;
        }

        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);

        fileHelper.updateObjectID(et.getIbzreportlyid(), StaticDict.File__object_type.REPORTLY.getValue(), files, "");
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        if (changes.size() > 0) {
            String strAction = StaticDict.Action__type.EDITED.getValue();
            Action action = actionHelper.create(StaticDict.Action__object_type.REPORTLY.getValue(), et.getIbzreportlyid(), strAction,
                    "", "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
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
        Action action = actionHelper.create(StaticDict.Action__object_type.REPORTLY.getValue(), et.getIbzreportlyid(), strAction,
                "", "", null, true);

        // 给汇报人，抄送人 待阅
        String ss = "已经提交给您了，请查收哦！";
        actionHelper.sendToread(et.getIbzreportlyid(), et.getIbzreportlyname() + ss, "", et.getReportto(), et.getMailto(), "汇报", StaticDict.Action__object_type.REPORTLY.getValue(), "ibzreportlies", StaticDict.Action__type.SUBMIT.getText());
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public IbzReportly haveRead(IbzReportly et) {
        CachedBeanCopier.copy(get(et.getIbzreportlyid()), et);
        List<Action> list = actionHelper.list(new QueryWrapper<Action>().eq("objecttype", StaticDict.Action__object_type.REPORTLY.getValue()).eq("action", StaticDict.Action__type.READ.getValue()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("objectid", et.getIbzreportlyid()));
        if (list.size() == 0) {
            actionHelper.create(StaticDict.Action__object_type.REPORTLY.getValue(), et.getIbzreportlyid(), StaticDict.Action__type.READ.getValue(), "", "", null, true);
        }
        return et;
    }
}
