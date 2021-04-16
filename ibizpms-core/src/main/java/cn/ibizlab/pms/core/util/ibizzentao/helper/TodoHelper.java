package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.mapper.TodoMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author chenxiang
 */
@Component
@Slf4j
public class TodoHelper extends ZTBaseHelper<TodoMapper, Todo> {

    @Autowired
    ActionHelper actionHelper;

    @Override
    public boolean hasDeleted(){
        return false ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Todo et) {

        if (StringUtils.compare(et.getType(),StaticDict.Type.TASK.getValue()) == 0) {
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
            createByCycle(et);
        }

        actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createByCycle(Todo todo) {
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
            return;
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
            actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), newTodo.getId(), StaticDict.Action__type.OPENED.getValue(),
                    "", "", newTodo.getAccount(), true);

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Todo et) {
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

        if (!super.edit(et)) {
            return false;
        }
        //周期循环处理
        if (et.getCycle() != null && et.getCycle() == 1) {
            createByCycle(et);
        }
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Todo assignTo(Todo et) {

        if (StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        if (StringUtils.isBlank(et.getAssignedby())) {
            et.setAssignedby(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        et.setAssigneddate(ZTDateUtil.now());

        this.internalUpdate(et);

        actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.ASSIGNED.getValue(),
                "", et.getAssignedto(), null, true);

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Todo activate(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) == 0 || StringUtils.compare(et.getStatus(), StaticDict.Todo__status.CLOSED.getValue()) == 0) {
            et.setStatus(StaticDict.Todo__status.WAIT.getValue());
            this.internalUpdate(et);
            actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.ACTIVATED.getValue(),
                    "", et.getStatus(), null, true);
        }

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Todo close(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) == 0) {
            et.setStatus(StaticDict.Todo__status.CLOSED.getValue());
            et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
            et.setCloseddate(ZTDateUtil.now());
            et.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
            et.setAssigneddate(ZTDateUtil.now());
            this.internalUpdate(et);
            actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.CLOSED.getValue(),
                    "", et.getAssignedto(), null, true);
        }

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Todo finish(Todo et) {
        et = this.get(et.getId());

        if (StringUtils.compare(et.getStatus(), StaticDict.Todo__status.DONE.getValue()) != 0) {
            et.setStatus(StaticDict.Todo__status.DONE.getValue());
            et.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            et.setFinisheddate(ZTDateUtil.now());
            this.internalUpdate(et);
            actionHelper.create(StaticDict.Action__object_type.TODO.getValue(), et.getId(), StaticDict.Action__type.FINISHED.getValue(),
                    "", et.getStatus(), null, true);
        }

        return et;
    }

}
