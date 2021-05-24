package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.IBZProProductActionServiceImpl;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品日志] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IBZProProductActionExService")
public class IBZProProductActionExService extends IBZProProductActionServiceImpl {

    @Autowired
    IFileService iFileService;

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ICaseService iCaseService;

    @Autowired
    ITaskService iTaskService;

    @Autowired
    IBugService iBugService;

    @Autowired
    IHistoryService iHistoryService;

    @Autowired
    IIBIZProMessageService iibizProMessageService;

    @Autowired
    ISysEmployeeService iSysEmployeeService;

    @Autowired
    IActionService iActionService;

    @Override
    public boolean create(IBZProProductAction et) {
        et.setComment(et.getComment() == null ? "" : et.getComment());
        String noticeusers = et.getNoticeusers();
        String files = et.getFiles();
        et.setObjecttype(StaticDict.Action__object_type.PRODUCT.getValue());
        this.createHis(et);
        // send(noticeusers, et);
        // 保存文件
        // 更新file
        String extra = "0";

        FileHelper.updateObjectID(et.getObjectid(), et.getObjecttype(), files, extra, iFileService);
        return true;
    }

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [CreateHis:创建历史日志] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction createHis(IBZProProductAction et) {
        Action action = new Action();
        CachedBeanCopier.copy(et, action);
        CachedBeanCopier.copy(iActionService.createHis(action), et);
        return et;
    }
    /**
     * [EditComment:编辑备注信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction editComment(IBZProProductAction et) {
        return super.editComment(et);
    }
    /**
     * [ManagePmsEe:Pms企业专用] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction managePmsEe(IBZProProductAction et) {
        return super.managePmsEe(et);
    }
    /**
     * [SendMarkDone:已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction sendMarkDone(IBZProProductAction et) {
        return super.sendMarkDone(et);
    }
    /**
     * [SendTodo:发送待办] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction sendTodo(IBZProProductAction et) {
        return super.sendTodo(et);
    }
    /**
     * [SendToread:发送待阅] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProProductAction sendToread(IBZProProductAction et) {
        return super.sendToread(et);
    }
}

