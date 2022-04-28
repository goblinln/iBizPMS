package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskRuntime;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;

@Slf4j
@Api(tags = {"任务"})
@RestController("WebApi-task")
@RequestMapping("")
public class TaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public TaskMapping taskMapping;

    @Autowired
    private ITaskTeamService taskteamService;
    @Autowired
    private ITaskEstimateService taskestimateService;
    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "新建任务", tags = {"任务" },  notes = "新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks")
    @Transactional
    public ResponseEntity<TaskDTO> create(@Validated @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
		taskService.create(domain);
        if(!taskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "批量新建任务", tags = {"任务" },  notes = "批量新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.createBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @PreAuthorize("test('ZT_TASK', #task_id, 'READ')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}")
    public ResponseEntity<TaskDTO> get(@PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(task_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TASK', #task_id, 'DELETE')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("task_id") Long task_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DELETE')")
    @ApiOperation(value = "批量删除任务", tags = {"任务" },  notes = "批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_TASK', #task_id, 'UPDATE')")
    @ApiOperation(value = "更新任务", tags = {"任务" },  notes = "更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}")
    @Transactional
    public ResponseEntity<TaskDTO> update(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
		Task domain  = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
		taskService.update(domain );
        if(!taskRuntime.test(task_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(task_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.activate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'ASSIGNTO')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignTo(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.assignTo(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'CANCEL')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancel(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.cancel(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "检查任务", tags = {"任务" },  notes = "检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("test('ZT_TASK', #task_id, 'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"任务" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> close(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.close(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "计算开始时间和完成时间", tags = {"任务" },  notes = "计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEnd(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "更新父任务时间", tags = {"任务" },  notes = "更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4Multiple(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "更新工作时间", tags = {"任务" },  notes = "更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHours(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "需求变更确认", tags = {"任务" },  notes = "需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChange(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycle(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasks(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> delete(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.delete(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'FINISH')")
    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finish(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.finish(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraft(TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_TASK', #task_id, 'READ')")
    @ApiOperation(value = "获取下一个团队成员(完成)", tags = {"任务" },  notes = "获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUser(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivity(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'READ')")
    @ApiOperation(value = "获取团队成员", tags = {"任务" },  notes = "获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernames(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "关联计划", tags = {"任务" },  notes = "关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlan(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'UPDATE')")
    @ApiOperation(value = "其他更新", tags = {"任务" },  notes = "其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'PAUSE')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pause(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.pause(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'RECORDESTIMATE')")
    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "继续任务时填入预计剩余为0设置为进行中", tags = {"任务" },  notes = "继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinue(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "预计剩余为0进行中", tags = {"任务" },  notes = "预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeft(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "开始任务时填入预计剩余为0设为进行中", tags = {"任务" },  notes = "开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'RESTART')")
    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.restart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/save")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        taskService.save(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'CREATE')")
    @ApiOperation(value = "批量保存任务", tags = {"任务" },  notes = "批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.saveBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "行为", tags = {"任务" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessage(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "发送消息前置处理", tags = {"任务" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcess(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'START')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> start(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.start(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'FAVORITES')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "检查多人任务操作权限", tags = {"任务" },  notes = "检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForward(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskForward(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'NFAVORITES')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'UPDATE')")
    @ApiOperation(value = "更新父任务状态", tags = {"任务" },  notes = "更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatus(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'UPDATE')")
    @ApiOperation(value = "更新父任务计划状态", tags = {"任务" },  notes = "更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatus(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("test('ZT_TASK', #task_id, 'UPDATE')")
    @ApiOperation(value = "更新需求版本", tags = {"任务" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersion(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"任务" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchaccount")
	public ResponseEntity<List<TaskDTO>> fetchaccount(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAccount(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取指派给我任务", tags = {"任务" } ,notes = "获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchassignedtomytask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取指派给我任务（PC）", tags = {"任务" } ,notes = "获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchassignedtomytaskpc(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'NONE')")
	@ApiOperation(value = "获取Bug相关任务", tags = {"任务" } ,notes = "获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchbugtask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取通过模块查询", tags = {"任务" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchbymodule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取数据查询（子任务）", tags = {"任务" } ,notes = "获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchchilddefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取子任务（更多）", tags = {"任务" } ,notes = "获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchchilddefaultmore(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取子任务", tags = {"任务" } ,notes = "获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchchildtask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取子任务（树）", tags = {"任务" } ,notes = "获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchchildtasktree(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取用户年度完成任务", tags = {"任务" } ,notes = "获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchcurfinishtask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取当前项目任务", tags = {"任务" } ,notes = "获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchcurprojecttaskquery(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchdefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取DefaultRow", tags = {"任务" } ,notes = "获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchdefaultrow(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"任务" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchesbulk(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"任务" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmy")
	public ResponseEntity<List<TaskDTO>> fetchmy(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMy(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我代理的任务", tags = {"任务" } ,notes = "获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchmyagenttask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我相关的任务", tags = {"任务" } ,notes = "获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchmyalltask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchmycompletetask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchmycompletetaskmobdaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchmycompletetaskmobmonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchmycompletetaskmonthlyzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchmycompletetaskzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"任务" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchmyfavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchmyplanstaskmobmonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchmytomorrowplantask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchmytomorrowplantaskmobdaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchnextweekcompletetaskmobzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchnextweekcompletetaskzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchnextweekplancompletetask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取相关任务（计划）", tags = {"任务" } ,notes = "获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchplantask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取项目任务（项目立项）", tags = {"任务" } ,notes = "获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchprojectapptask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取项目任务", tags = {"任务" } ,notes = "获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchprojecttask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取透视表结果集", tags = {"任务" } ,notes = "获取透视表结果集")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchreportds")
	public ResponseEntity<List<TaskDTO>> fetchreportds(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchReportDS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取根任务", tags = {"任务" } ,notes = "获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchroottask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchtasklinkplan(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchthismonthcompletetaskchoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchthisweekcompletetask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchthisweekcompletetaskchoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchthisweekcompletetaskmobzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchthisweekcompletetaskzs(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取todo列表查询", tags = {"任务" } ,notes = "获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchtodolisttask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取任务类型分组", tags = {"任务" } ,notes = "获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchtypegroup(@RequestBody TaskSearchContext context) {
        Page<Map> domains = taskService.searchTypeGroup(context) ;
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取任务类型分组（计划）", tags = {"任务" } ,notes = "获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchtypegroupplan(@RequestBody TaskSearchContext context) {
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @ApiOperation(value = "生成任务报表", tags = {"任务"}, notes = "生成任务报表")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TaskSearchContext context, HttpServletResponse response) {
        try {
            taskRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", taskRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, taskRuntime);
        }
    }

    @ApiOperation(value = "打印任务", tags = {"任务"}, notes = "打印任务")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("task_ids") Set<Long> task_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = taskRuntime.getDEPrintRuntime(print_id);
        try {
            List<Task> domains = new ArrayList<>();
            for (Long task_id : task_ids) {
                domains.add(taskService.get( task_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Task[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", taskRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", task_ids, e.getMessage()), Errors.INTERNALERROR, taskRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }


    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目批量建立任务", tags = {"任务" },  notes = "根据项目批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setProject(project_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        if (domain == null || !(project_id.equals(domain.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task testget = taskService.get(task_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task testget = taskService.get(task_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"任务" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"任务" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'CANCEL')")
    @ApiOperation(value = "根据项目取消", tags = {"任务" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目检查任务", tags = {"任务" },  notes = "根据项目检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"任务" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目计算开始时间和完成时间", tags = {"任务" },  notes = "根据项目计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目更新父任务时间", tags = {"任务" },  notes = "根据项目更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目更新工作时间", tags = {"任务" },  notes = "根据项目更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目需求变更确认", tags = {"任务" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目删除工时", tags = {"任务" },  notes = "根据项目删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目编辑工时", tags = {"任务" },  notes = "根据项目编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'FINISH')")
    @ApiOperation(value = "根据项目完成", tags = {"任务" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)", tags = {"任务" },  notes = "根据项目获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "根据项目获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "根据项目获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取团队成员", tags = {"任务" },  notes = "根据项目获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目关联计划", tags = {"任务" },  notes = "根据项目关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目其他更新", tags = {"任务" },  notes = "根据项目其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'PAUSE')")
    @ApiOperation(value = "根据项目暂停", tags = {"任务" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'RECORDESTIMATE')")
    @ApiOperation(value = "根据项目工时录入", tags = {"任务" },  notes = "根据项目工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中", tags = {"任务" },  notes = "根据项目继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目预计剩余为0进行中", tags = {"任务" },  notes = "根据项目预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中", tags = {"任务" },  notes = "根据项目开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'RESTART')")
    @ApiOperation(value = "根据项目继续", tags = {"任务" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'CREATE')")
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目批量保存任务", tags = {"任务" },  notes = "根据项目批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setProject(project_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目行为", tags = {"任务" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"任务" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'START')")
    @ApiOperation(value = "根据项目开始", tags = {"任务" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'FAVORITES')")
    @ApiOperation(value = "根据项目任务收藏", tags = {"任务" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目检查多人任务操作权限", tags = {"任务" },  notes = "根据项目检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'NFAVORITES')")
    @ApiOperation(value = "根据项目任务收藏", tags = {"任务" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务状态", tags = {"任务" },  notes = "根据项目更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务计划状态", tags = {"任务" },  notes = "根据项目更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"任务" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指定用户数据", tags = {"任务" } ,notes = "根据项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchaccount")
	public ResponseEntity<List<TaskDTO>> fetchAccountByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAccount(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'NONE')")
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"任务" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"任务" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我的数据", tags = {"任务" } ,notes = "根据项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmy")
	public ResponseEntity<List<TaskDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMy(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"任务" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"任务" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"任务" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"任务" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取透视表结果集", tags = {"任务" } ,notes = "根据项目获取透视表结果集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchreportds")
	public ResponseEntity<List<TaskDTO>> fetchReportDSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchReportDS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @Autowired
    cn.ibizlab.pms.core.zentao.mapping.TaskDataImport dataimportImpMapping;
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/import")
    public ResponseEntity<JSONObject> importData(@RequestParam(value = "config") String config , @RequestBody List<Task> dtos){
        JSONObject rs=new JSONObject();
        if(dtos.size()==0){
            rs.put("rst", 1);
            rs.put("msg", "未传入内容");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rs);
        }
        else{
            if("DataImport".equals(config)){
                rs=taskService.importData(dataimportImpMapping.toDomain(dtos),1000,false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
    }
}

