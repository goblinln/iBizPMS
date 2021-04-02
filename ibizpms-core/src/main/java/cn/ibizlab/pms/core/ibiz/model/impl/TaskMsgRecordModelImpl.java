package cn.ibizlab.pms.core.ibiz.model.impl;

import cn.ibizlab.pms.core.ibiz.filter.TaskMsgRecordSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ITaskMsgRecordService;
import cn.ibizlab.pms.core.util.model.DataEntityModelGlobalHelper;
import cn.ibizlab.pms.core.util.model.DataEntityModelImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("TaskMsgRecordModelImpl")
public class TaskMsgRecordModelImpl extends DataEntityModelImpl {


    @Autowired
    public ITaskMsgRecordService taskmsgrecordService;

    public TaskMsgRecordModelImpl(){
        this.entity = "TASKMSGRECORD";
        this.pkey = "T1.TASKMSGRECORDID";
        this.orgDRField = "" ;
        this.deptDRField = "";
    }

    @Override
    public boolean test(Serializable key, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return true;
            
        //检查能力
        if(!test(action))
            return false ;

        //检查数据范围
        TaskMsgRecordSearchContext context = new TaskMsgRecordSearchContext();
        context.getSelectCond().eq(this.pkey, key);
        addAuthorityConditions(context, action);

        if (taskmsgrecordService.searchDefault(context).getTotalElements() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean test(List<Serializable> keys, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getSuperuser() == 1)
            return true;

        //检查能力
        if(!test(action))
            return false ;

        //检查数据范围
        TaskMsgRecordSearchContext context = new TaskMsgRecordSearchContext();
        context.getSelectCond().in(this.pkey, keys);
        addAuthorityConditions(context, action);

        if (taskmsgrecordService.searchDefault(context).getTotalElements() != keys.size()) {
            return false;
        }

        return true;
    }

}

