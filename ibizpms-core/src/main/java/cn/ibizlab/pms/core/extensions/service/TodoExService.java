package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Todo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[待办] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TodoExService")
public class TodoExService extends TodoServiceImpl {

    @Autowired
    IActionService iActionService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:Activate] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo activate(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) == 0 || StringUtils.compare(et.getStatus(), StaticDict.Todo__status.CLOSED.getValue()) == 0) {
            et.setStatus(StaticDict.Todo__status.WAIT.getValue());
            super.update(et);
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.ACTIVATED.getValue(),
                    "", "", null,iActionService);
        }

        return et;
    }
    /**
     * [AssignTo:AssignTo] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo assignTo(Todo et) {
        if (StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        if (StringUtils.isBlank(et.getAssignedby())) {
            et.setAssignedby(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        et.setAssigneddate(ZTDateUtil.now());

        super.update(et);

        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.ASSIGNED.getValue(),
                "", "", null,iActionService);


        return et;
    }
    /**
     * [Close:Close] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo close(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) == 0) {
            et.setStatus(StaticDict.Todo__status.CLOSED.getValue());
            et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
            et.setCloseddate(ZTDateUtil.now());
            et.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
            et.setAssigneddate(ZTDateUtil.now());
            super.update(et);

            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.CLOSED.getValue(),
                    "", "", null,iActionService);
        }

        return et;
    }
    /**
     * [CreateCycle:定时创建周期] 行为扩展
     * @param todo
     * @return
     */
    @Override
    @Transactional
    public Todo createCycle(Todo todo) {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = sdf.parse(sdf.format(now));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject config = JSONObject.parseObject(todo.getConfig());
        Date begin = config.getDate(FIELD_BEGIN);
        if(begin == null) {
            begin = todo.getDate();
        }
        if(begin == null) {
            begin = today;
        }
        Date end = config.getDate(FIELD_END);
        Integer beforeDays = config.getInteger(FIELD_BEFOREDAYS);
        Calendar calendar = Calendar.getInstance();
        if (beforeDays != null && beforeDays > 0) {
            if (begin != null) {
                calendar.setTime(begin);
            }
            calendar.add(Calendar.DATE, -beforeDays);
            begin = calendar.getTime();
        }
        if (today.before(begin) || (end != null && today.after(end))) {
            return todo;
        }

        Todo newTodo = new Todo();
        CachedBeanCopier.copy(todo,newTodo);

        newTodo.setType(StaticDict.TypeAll.CYCLE.getValue());
        newTodo.setIdvalue(todo.getId());
        newTodo.setCycle(0);

        newTodo.setStatus(StaticDict.Todo__status.WAIT.getValue());
        newTodo.setConfig("");

        if (newTodo.getAssignedto() != null) {
            newTodo.setAssigneddate(new Timestamp(now.getTime()));
        }

        calendar.setTime(today);
        if (beforeDays != null) {
            calendar.add(Calendar.DATE, beforeDays);
        }
        Date finish = calendar.getTime();
        for (long time = begin.getTime(); time <= finish.getTime(); time += 86400000) {

            Date today1 = new Date(time);
            List<Todo> lastCycleList = this.list(new QueryWrapper<Todo>().eq(FIELD_IDVALUE, todo.getId()).orderByDesc(FIELD_DATE));

            Todo lastCycleJson = null;
            if(lastCycleList.size() > 0) {
                lastCycleJson = lastCycleList.get(0);
            }

            Date date = null;

            if (StaticDict.CycleType.DAY.getValue().equals(config.getString(FIELD_TYPE))) {
                Integer day = config.getInteger(StaticDict.CycleType.DAY.getValue());
                if (day <= 0) {
                    continue;
                }
                if (lastCycleJson == null) {
                    calendar.setTime(today1);
                    calendar.add(Calendar.DATE, day - 1);
                    date = calendar.getTime();
                }
                else if (lastCycleJson.getDate() != null) {
                    calendar.setTime(lastCycleJson.getDate());
                    calendar.add(Calendar.DATE, day);
                    date = calendar.getTime();
                }
            } else if (StaticDict.CycleType.WEEK.getValue().equals(config.getString(FIELD_TYPE))) {
                calendar.setTime(today1);
                int week = calendar.get(Calendar.DAY_OF_WEEK);
                if (config.getString(StaticDict.CycleType.WEEK.getValue()).contains("" + week)) {
                    if (lastCycleJson == null) {
                        date = today1;
                    }
                    else if (lastCycleJson.getDate() != null && lastCycleJson.getDate().before(today1)) {
                        date = today1;
                    }
                }
            } else if (StaticDict.CycleType.MONTH.getValue().equals(config.getString(FIELD_TYPE))) {
                calendar.setTime(today1);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                if (config.getString(StaticDict.CycleType.MONTH.getValue()).contains("" + day)) {
                    if (lastCycleJson == null) {
                        date = today1;
                    }
                    else if (lastCycleJson.getDate() != null && lastCycleJson.getDate().before(today1)) {
                        date = today1;
                    }
                }
            }

            if (date == null) {
                continue;
            }
            Date configBegin = config.getDate(FIELD_BEGIN);

            if (configBegin == null) {
                configBegin = todo.getDate();
            }
            if (configBegin == null || date.before(configBegin)) {
                continue;
            }

            if (date.before(today)) {
                continue;
            }
            if (date.after(finish)) {
                continue;
            }
            if (end != null && date.after(end)) {
                continue;
            }

            newTodo.setDate(new Timestamp(date.getTime()));
            newTodo.setId(null);
            this.baseMapper.insert(newTodo);
            ActionHelper.createHis(newTodo.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                    "", "", newTodo.getAccount(),iActionService);
        }
        return newTodo;
    }
    /**
     * [Finish:Finish] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo finish(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) != 0) {
            et.setStatus(StaticDict.Todo__status.DONE.getValue());
            et.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            et.setFinisheddate(ZTDateUtil.now());
            super.update(et);
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.FINISHED.getValue(),
                    "", "", null,iActionService);
        }

        return et;
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo sendMessage(Todo et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo sendMsgPreProcess(Todo et) {
        return super.sendMsgPreProcess(et);
    }

    @Override
    public boolean create(Todo et) {
        if (StringUtils.compare(et.getType(), StaticDict.Type.TASK.getValue()) == 0) {
            et.setName(et.getTask());
        } else if (StringUtils.compare(et.getType(), StaticDict.Type.BUG.getValue()) == 0) {
            et.setName(et.getBug());
        } else if (StringUtils.compare(et.getType(), StaticDict.Type.STORY.getValue()) == 0) {
            et.setName(et.getStory());
        }
        et.setDate(et.getDate() == null ? ZTDateUtil.now() : et.getDate());
        if (et.getCycle() != null && et.getCycle() == 1) {
            et.setType(StaticDict.TypeAll.CYCLE.getValue());
            JSONObject config = new JSONObject();
            config.put(FIELD_BEGIN, et.getDate());
            config.put(FIELD_TYPE, et.getConfigType());
            config.put(FIELD_BEFOREDAYS, et.getConfigBeforedays());
            if (et.getConfigEnd() != null) {
                config.put(FIELD_END, et.getConfigEnd());
            }
            if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.DAY.getValue()) == 0) {
                config.put(StaticDict.CycleType.DAY.getValue(), et.getConfigDay());
            } else if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.MONTH.getValue()) == 0) {
                config.put(StaticDict.CycleType.MONTH.getValue(), et.getConfigMonth());
            } else if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.WEEK.getValue()) == 0) {
                config.put(StaticDict.CycleType.WEEK.getValue(), et.getConfigWeek());
            }
            et.setConfig(config.toString());
        } else {
            et.setCycle(0);
            et.setConfig("");
        }

        if (!super.create(et)) {
            return false;
        }

        //周期循环处理
        if (et.getCycle() != null && et.getCycle() == 1) {
            createCycle(et);
        }

        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                "", "", null,iActionService);

        return true;
    }

    @Override
    public boolean update(Todo et) {
        Todo old = new Todo();
        CachedBeanCopier.copy(get(et.getId()), old);

//        if (StringUtils.compare(et.getType(), StaticDict.Type.TASK.getValue()) == 0) {
//            et.setName(et.getTask());
//        } else if (StringUtils.compare(et.getType(), StaticDict.Type.BUG.getValue()) == 0) {
//            et.setName(et.getBug());
//        } else if (StringUtils.compare(et.getType(), StaticDict.Type.STORY.getValue()) == 0) {
//            et.setName(et.getStory());
//        }
        if (et.getCycle() != null && et.getCycle() == 1) {
            JSONObject config = new JSONObject();
            config.put(FIELD_BEGIN, et.getDate());
            config.put(FIELD_TYPE, et.getConfigType());
            config.put(FIELD_BEFOREDAYS, et.getConfigBeforedays());
            if (et.getConfigEnd() != null) {
                config.put(FIELD_END, et.getConfigEnd());
            }
            if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.DAY.getValue()) == 0) {
                config.put(StaticDict.CycleType.DAY.getValue(), et.getConfigDay());
            } else if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.MONTH.getValue()) == 0) {
                config.put(StaticDict.CycleType.MONTH.getValue(), et.getConfigMonth());
            } else if (StringUtils.compare(et.getConfigType(), StaticDict.CycleType.WEEK.getValue()) == 0) {
                config.put(StaticDict.CycleType.WEEK.getValue(), et.getConfigWeek());
            }
            et.setConfig(config.toString());
        } else {
            et.setCycle(0);
            et.setConfig("");
        }

        if (!super.update(et)) {
            return false;
        }
        //周期循环处理
        if (et.getCycle() != null && et.getCycle() == 1) {
            createCycle(et);
        }
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TODO.getValue(),changes,StaticDict.Action__type.EDITED.getValue(),
                    "","",null,iActionService);
        }
        return true;
    }
}

