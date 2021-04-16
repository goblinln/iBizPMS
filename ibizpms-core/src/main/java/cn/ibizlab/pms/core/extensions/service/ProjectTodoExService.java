package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.ProjectTodoServiceImpl;
import cn.ibizlab.pms.core.util.ibizzentao.helper.FileHelper;
import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目其他活动] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProjectTodoExService")
public class ProjectTodoExService extends ProjectTodoServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    ITodoService iTodoService;

    @Autowired
    FileHelper fileHelper;

    @Override
    public boolean create(ProjectTodo et) {
        Todo todo = new Todo();
        String files = et.getFiles();
        CachedBeanCopier.copy(et, todo);
        boolean flag = iTodoService.create(todo);
        CachedBeanCopier.copy(todo, et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TODO.getValue(), files, "");
        return flag;
    }

    @Override
    public boolean update(ProjectTodo et) {
        Todo todo = new Todo();
        String files = et.getFiles();
        CachedBeanCopier.copy(et, todo);
        boolean flag = iTodoService.update(todo);
        CachedBeanCopier.copy(todo, et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TODO.getValue(), files, "");
        return flag;
    }

    @Override
    public boolean remove(Long key) {
        return iTodoService.remove(key);
    }

    /**
     * [Activate:Activate] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo activate(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.activate(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [AssignTo:AssignTo] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo assignTo(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.assignTo(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [Close:Close] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo close(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(get(et.getId()), todo);
        iTodoService.close(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [CreateCycle:定时创建周期] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo createCycle(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.createCycle(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [Finish:Finish] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo finish(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.finish(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo sendMessage(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.sendMessage(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo sendMsgPreProcess(ProjectTodo et) {
        Todo todo = new Todo();
        CachedBeanCopier.copy(et, todo);
        iTodoService.sendMsgPreProcess(todo);
        CachedBeanCopier.copy(todo, et);
        return et;
    }
}

