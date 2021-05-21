package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
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
@Api(tags = {"任务" })
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

    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务", tags = {"任务" },  notes = "新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks")
    @Transactional
    public ResponseEntity<TaskDTO> create(@Validated @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
		taskService.create(domain);
        if(!taskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建任务", tags = {"任务" },  notes = "批量新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.createBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
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
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新任务", tags = {"任务" },  notes = "批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.updateBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("task_id") Long task_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@TaskRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除任务", tags = {"任务" },  notes = "批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}")
    public ResponseEntity<TaskDTO> get(@PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraft(TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.activate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[激活]", tags = {"任务" },  notes = "批量处理[激活]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignTo(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.assignTo(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[指派/转交]", tags = {"任务" },  notes = "批量处理[指派/转交]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancel(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.cancel(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[取消]", tags = {"任务" },  notes = "批量处理[取消]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查任务", tags = {"任务" },  notes = "检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"任务" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> close(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.close(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[关闭]", tags = {"任务" },  notes = "批量处理[关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "计算开始时间和完成时间", tags = {"任务" },  notes = "计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEnd(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[计算开始时间和完成时间]", tags = {"任务" },  notes = "批量处理[计算开始时间和完成时间]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "更新父任务时间", tags = {"任务" },  notes = "更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4Multiple(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[更新父任务时间]", tags = {"任务" },  notes = "批量处理[更新父任务时间]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "更新工作时间", tags = {"任务" },  notes = "更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHours(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[更新工作时间]", tags = {"任务" },  notes = "批量处理[更新工作时间]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "需求变更确认", tags = {"任务" },  notes = "需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChange(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[需求变更确认]", tags = {"任务" },  notes = "批量处理[需求变更确认]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycle(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[创建周期任务]", tags = {"任务" },  notes = "批量处理[创建周期任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasks(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[创建周期任务]", tags = {"任务" },  notes = "批量处理[创建周期任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> delete(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.delete(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[删除任务]", tags = {"任务" },  notes = "批量处理[删除任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[删除工时]", tags = {"任务" },  notes = "批量处理[删除工时]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[编辑工时]", tags = {"任务" },  notes = "批量处理[编辑工时]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finish(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.finish(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[完成]", tags = {"任务" },  notes = "批量处理[完成]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/finishbatch")
    public ResponseEntity<Boolean> finishBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取下一个团队成员(完成)", tags = {"任务" },  notes = "获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUser(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[获取下一个团队成员(完成)]", tags = {"任务" },  notes = "批量处理[获取下一个团队成员(完成)]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivity(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[获取团队成员剩余工时（激活）]", tags = {"任务" },  notes = "批量处理[获取团队成员剩余工时（激活）]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[获取团队成员剩余工时（开始或继续）]", tags = {"任务" },  notes = "批量处理[获取团队成员剩余工时（开始或继续）]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员", tags = {"任务" },  notes = "获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernames(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @ApiOperation(value = "关联计划", tags = {"任务" },  notes = "关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlan(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[关联计划]", tags = {"任务" },  notes = "批量处理[关联计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "其他更新", tags = {"任务" },  notes = "其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[其他更新]", tags = {"任务" },  notes = "批量处理[其他更新]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pause(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.pause(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[暂停]", tags = {"任务" },  notes = "批量处理[暂停]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[工时录入]", tags = {"任务" },  notes = "批量处理[工时录入]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "继续任务时填入预计剩余为0设置为进行中", tags = {"任务" },  notes = "继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinue(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[继续任务时填入预计剩余为0设置为进行中]", tags = {"任务" },  notes = "批量处理[继续任务时填入预计剩余为0设置为进行中]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "预计剩余为0进行中", tags = {"任务" },  notes = "预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeft(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[预计剩余为0进行中]", tags = {"任务" },  notes = "批量处理[预计剩余为0进行中]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "开始任务时填入预计剩余为0设为进行中", tags = {"任务" },  notes = "开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[开始任务时填入预计剩余为0设为进行中]", tags = {"任务" },  notes = "批量处理[开始任务时填入预计剩余为0设为进行中]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.restart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[继续]", tags = {"任务" },  notes = "批量处理[继续]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/restartbatch")
    public ResponseEntity<Boolean> restartBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/save")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存任务", tags = {"任务" },  notes = "批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.saveBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "行为", tags = {"任务" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessage(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[行为]", tags = {"任务" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送消息前置处理", tags = {"任务" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcess(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[发送消息前置处理]", tags = {"任务" },  notes = "批量处理[发送消息前置处理]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> start(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.start(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[开始]", tags = {"任务" },  notes = "批量处理[开始]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/startbatch")
    public ResponseEntity<Boolean> startBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @ApiOperation(value = "检查多人任务操作权限", tags = {"任务" },  notes = "检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForward(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskForward(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[检查多人任务操作权限]", tags = {"任务" },  notes = "批量处理[检查多人任务操作权限]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "更新父任务状态", tags = {"任务" },  notes = "更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatus(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[更新父任务状态]", tags = {"任务" },  notes = "批量处理[更新父任务状态]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "更新父任务计划状态", tags = {"任务" },  notes = "更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatus(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[更新父任务计划状态]", tags = {"任务" },  notes = "批量处理[更新父任务计划状态]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "更新需求版本", tags = {"任务" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersion(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我任务", tags = {"任务" } ,notes = "查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchAssignedToMyTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我任务（PC）", tags = {"任务" } ,notes = "查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchAssignedToMyTaskPc(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

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

	@ApiOperation(value = "查询Bug相关任务", tags = {"任务" } ,notes = "查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchBugTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询通过模块查询", tags = {"任务" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchByModule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据查询（子任务）", tags = {"任务" } ,notes = "查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchChildDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子任务（更多）", tags = {"任务" } ,notes = "查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchChildDefaultMore(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子任务", tags = {"任务" } ,notes = "查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchChildTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子任务（树）", tags = {"任务" } ,notes = "查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchChildTaskTree(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询用户年度完成任务", tags = {"任务" } ,notes = "查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchCurFinishTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前项目任务", tags = {"任务" } ,notes = "查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchCurProjectTaskQuery(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DefaultRow", tags = {"任务" } ,notes = "查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchDefaultRow(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"任务" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchESBulk(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我代理的任务", tags = {"任务" } ,notes = "查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchMyAgentTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我相关的任务", tags = {"任务" } ,notes = "查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchMyAllTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMobDaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMobMonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMonthlyZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"任务" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchMyFavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchMyPlansTaskMobMonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchMyTomorrowPlanTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchMyTomorrowPlanTaskMobDaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekCompleteTaskMobZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekPlanCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询相关任务（计划）", tags = {"任务" } ,notes = "查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchPlanTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目任务（项目立项）", tags = {"任务" } ,notes = "查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchProjectAppTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目任务", tags = {"任务" } ,notes = "查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchProjectTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询根任务", tags = {"任务" } ,notes = "查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchRootTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskLinkPlan(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchThisMonthCompleteTaskChoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskChoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskMobZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询todo列表查询", tags = {"任务" } ,notes = "查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTodoListTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询任务类型分组", tags = {"任务" } ,notes = "查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTypeGroup(@RequestBody TaskSearchContext context) {
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
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

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询任务类型分组（计划）", tags = {"任务" } ,notes = "查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTypeGroupPlan(@RequestBody TaskSearchContext context) {
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/{action}")
    public ResponseEntity<TaskDTO> dynamicCall(@PathVariable("task_id") Long task_id , @PathVariable("action") String action , @RequestBody TaskDTO taskdto) {
        Task domain = taskService.dynamicCall(task_id, action, taskMapping.toDomain(taskdto));
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块建立任务", tags = {"任务" },  notes = "根据任务模块建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks")
    public ResponseEntity<TaskDTO> createByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块批量建立任务", tags = {"任务" },  notes = "根据任务模块批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setModule(projectmodule_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块更新任务", tags = {"任务" },  notes = "根据任务模块更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块批量更新任务", tags = {"任务" },  notes = "根据任务模块批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setModule(projectmodule_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块删除任务", tags = {"任务" },  notes = "根据任务模块删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块批量删除任务", tags = {"任务" },  notes = "根据任务模块批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProjectModule(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块获取任务", tags = {"任务" },  notes = "根据任务模块获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务模块获取任务草稿", tags = {"任务" },  notes = "根据任务模块获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setModule(projectmodule_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块检查任务", tags = {"任务" },  notes = "根据任务模块检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CLOSE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块保存任务", tags = {"任务" },  notes = "根据任务模块保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务模块批量保存任务", tags = {"任务" },  notes = "根据任务模块批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setModule(projectmodule_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务]", tags = {"任务" },  notes = "批量处理[根据任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务", tags = {"任务" },  notes = "根据任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取指派给我任务", tags = {"任务" } ,notes = "根据任务模块获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询指派给我任务", tags = {"任务" } ,notes = "根据任务模块查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据任务模块获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据任务模块查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据任务模块获取Bug相关任务", tags = {"任务" } ,notes = "根据任务模块获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据任务模块查询Bug相关任务", tags = {"任务" } ,notes = "根据任务模块查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取通过模块查询", tags = {"任务" } ,notes = "根据任务模块获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询通过模块查询", tags = {"任务" } ,notes = "根据任务模块查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取数据查询（子任务）", tags = {"任务" } ,notes = "根据任务模块获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询数据查询（子任务）", tags = {"任务" } ,notes = "根据任务模块查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取子任务（更多）", tags = {"任务" } ,notes = "根据任务模块获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询子任务（更多）", tags = {"任务" } ,notes = "根据任务模块查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取子任务", tags = {"任务" } ,notes = "根据任务模块获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询子任务", tags = {"任务" } ,notes = "根据任务模块查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取子任务（树）", tags = {"任务" } ,notes = "根据任务模块获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询子任务（树）", tags = {"任务" } ,notes = "根据任务模块查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取用户年度完成任务", tags = {"任务" } ,notes = "根据任务模块获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询用户年度完成任务", tags = {"任务" } ,notes = "根据任务模块查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取当前项目任务", tags = {"任务" } ,notes = "根据任务模块获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询当前项目任务", tags = {"任务" } ,notes = "根据任务模块查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取DEFAULT", tags = {"任务" } ,notes = "根据任务模块获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询DEFAULT", tags = {"任务" } ,notes = "根据任务模块查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取DefaultRow", tags = {"任务" } ,notes = "根据任务模块获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询DefaultRow", tags = {"任务" } ,notes = "根据任务模块查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取ES批量的导入", tags = {"任务" } ,notes = "根据任务模块获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询ES批量的导入", tags = {"任务" } ,notes = "根据任务模块查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我代理的任务", tags = {"任务" } ,notes = "根据任务模块获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我代理的任务", tags = {"任务" } ,notes = "根据任务模块查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我相关的任务", tags = {"任务" } ,notes = "根据任务模块获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我相关的任务", tags = {"任务" } ,notes = "根据任务模块查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据任务模块获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据任务模块查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据任务模块获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据任务模块查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据任务模块获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据任务模块查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我的收藏", tags = {"任务" } ,notes = "根据任务模块获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我的收藏", tags = {"任务" } ,notes = "根据任务模块查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据任务模块获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据任务模块查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务模块查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据任务模块获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据任务模块查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据任务模块获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据任务模块查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取相关任务（计划）", tags = {"任务" } ,notes = "根据任务模块获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询相关任务（计划）", tags = {"任务" } ,notes = "根据任务模块查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据任务模块获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据任务模块查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取项目任务", tags = {"任务" } ,notes = "根据任务模块获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询项目任务", tags = {"任务" } ,notes = "根据任务模块查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取根任务", tags = {"任务" } ,notes = "根据任务模块获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询根任务", tags = {"任务" } ,notes = "根据任务模块查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据任务模块获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据任务模块查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据任务模块获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据任务模块查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据任务模块获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据任务模块查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据任务模块获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据任务模块查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取todo列表查询", tags = {"任务" } ,notes = "根据任务模块获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询todo列表查询", tags = {"任务" } ,notes = "根据任务模块查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取任务类型分组", tags = {"任务" } ,notes = "根据任务模块获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询任务类型分组", tags = {"任务" } ,notes = "根据任务模块查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据任务模块获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据任务模块查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProjectModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划建立任务", tags = {"任务" },  notes = "根据产品计划建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划批量建立任务", tags = {"任务" },  notes = "根据产品计划批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setPlan(productplan_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划更新任务", tags = {"任务" },  notes = "根据产品计划更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划批量更新任务", tags = {"任务" },  notes = "根据产品计划批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setPlan(productplan_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划删除任务", tags = {"任务" },  notes = "根据产品计划删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划批量删除任务", tags = {"任务" },  notes = "根据产品计划批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlan(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取任务", tags = {"任务" },  notes = "根据产品计划获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划获取任务草稿", tags = {"任务" },  notes = "根据产品计划获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setPlan(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划检查任务", tags = {"任务" },  notes = "根据产品计划检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CLOSE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划保存任务", tags = {"任务" },  notes = "根据产品计划保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划批量保存任务", tags = {"任务" },  notes = "根据产品计划批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setPlan(productplan_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务", tags = {"任务" },  notes = "根据产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取指派给我任务", tags = {"任务" } ,notes = "根据产品计划获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询指派给我任务", tags = {"任务" } ,notes = "根据产品计划查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品计划获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品计划查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取Bug相关任务", tags = {"任务" } ,notes = "根据产品计划获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询Bug相关任务", tags = {"任务" } ,notes = "根据产品计划查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取通过模块查询", tags = {"任务" } ,notes = "根据产品计划获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询通过模块查询", tags = {"任务" } ,notes = "根据产品计划查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取数据查询（子任务）", tags = {"任务" } ,notes = "根据产品计划获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询数据查询（子任务）", tags = {"任务" } ,notes = "根据产品计划查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取子任务（更多）", tags = {"任务" } ,notes = "根据产品计划获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询子任务（更多）", tags = {"任务" } ,notes = "根据产品计划查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取子任务", tags = {"任务" } ,notes = "根据产品计划获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询子任务", tags = {"任务" } ,notes = "根据产品计划查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取子任务（树）", tags = {"任务" } ,notes = "根据产品计划获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询子任务（树）", tags = {"任务" } ,notes = "根据产品计划查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品计划获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品计划查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取当前项目任务", tags = {"任务" } ,notes = "根据产品计划获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询当前项目任务", tags = {"任务" } ,notes = "根据产品计划查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"任务" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询DEFAULT", tags = {"任务" } ,notes = "根据产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取DefaultRow", tags = {"任务" } ,notes = "根据产品计划获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询DefaultRow", tags = {"任务" } ,notes = "根据产品计划查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取ES批量的导入", tags = {"任务" } ,notes = "根据产品计划获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询ES批量的导入", tags = {"任务" } ,notes = "根据产品计划查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我代理的任务", tags = {"任务" } ,notes = "根据产品计划获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我代理的任务", tags = {"任务" } ,notes = "根据产品计划查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我相关的任务", tags = {"任务" } ,notes = "根据产品计划获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我相关的任务", tags = {"任务" } ,notes = "根据产品计划查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品计划获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品计划查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品计划获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品计划查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品计划获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品计划查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我的收藏", tags = {"任务" } ,notes = "根据产品计划获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我的收藏", tags = {"任务" } ,notes = "根据产品计划查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品计划获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品计划查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品计划查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品计划获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品计划查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品计划获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品计划查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取相关任务（计划）", tags = {"任务" } ,notes = "根据产品计划获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询相关任务（计划）", tags = {"任务" } ,notes = "根据产品计划查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品计划获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品计划查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目任务", tags = {"任务" } ,notes = "根据产品计划获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询项目任务", tags = {"任务" } ,notes = "根据产品计划查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取根任务", tags = {"任务" } ,notes = "根据产品计划获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询根任务", tags = {"任务" } ,notes = "根据产品计划查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品计划获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品计划查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品计划获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品计划查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品计划获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品计划查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品计划获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品计划查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取todo列表查询", tags = {"任务" } ,notes = "根据产品计划获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询todo列表查询", tags = {"任务" } ,notes = "根据产品计划查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取任务类型分组", tags = {"任务" } ,notes = "根据产品计划获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询任务类型分组", tags = {"任务" } ,notes = "根据产品计划查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品计划获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品计划查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求建立任务", tags = {"任务" },  notes = "根据需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求批量建立任务", tags = {"任务" },  notes = "根据需求批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求更新任务", tags = {"任务" },  notes = "根据需求更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求批量更新任务", tags = {"任务" },  notes = "根据需求批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求删除任务", tags = {"任务" },  notes = "根据需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求批量删除任务", tags = {"任务" },  notes = "根据需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求获取任务", tags = {"任务" },  notes = "根据需求获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求获取任务草稿", tags = {"任务" },  notes = "根据需求获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByStory(@PathVariable("story_id") Long story_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求检查任务", tags = {"任务" },  notes = "根据需求检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CLOSE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求保存任务", tags = {"任务" },  notes = "根据需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求批量保存任务", tags = {"任务" },  notes = "根据需求批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setStory(story_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取指派给我任务", tags = {"任务" } ,notes = "根据需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询指派给我任务", tags = {"任务" } ,notes = "根据需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Bug相关任务", tags = {"任务" } ,notes = "根据需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Bug相关任务", tags = {"任务" } ,notes = "根据需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取通过模块查询", tags = {"任务" } ,notes = "根据需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询通过模块查询", tags = {"任务" } ,notes = "根据需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取数据查询（子任务）", tags = {"任务" } ,notes = "根据需求获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询数据查询（子任务）", tags = {"任务" } ,notes = "根据需求查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取子任务（更多）", tags = {"任务" } ,notes = "根据需求获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询子任务（更多）", tags = {"任务" } ,notes = "根据需求查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取子任务", tags = {"任务" } ,notes = "根据需求获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询子任务", tags = {"任务" } ,notes = "根据需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取子任务（树）", tags = {"任务" } ,notes = "根据需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询子任务（树）", tags = {"任务" } ,notes = "根据需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取当前项目任务", tags = {"任务" } ,notes = "根据需求获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询当前项目任务", tags = {"任务" } ,notes = "根据需求查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"任务" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"任务" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取DefaultRow", tags = {"任务" } ,notes = "根据需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询DefaultRow", tags = {"任务" } ,notes = "根据需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取ES批量的导入", tags = {"任务" } ,notes = "根据需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询ES批量的导入", tags = {"任务" } ,notes = "根据需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我代理的任务", tags = {"任务" } ,notes = "根据需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我代理的任务", tags = {"任务" } ,notes = "根据需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我相关的任务", tags = {"任务" } ,notes = "根据需求获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我相关的任务", tags = {"任务" } ,notes = "根据需求查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我的收藏", tags = {"任务" } ,notes = "根据需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我的收藏", tags = {"任务" } ,notes = "根据需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取相关任务（计划）", tags = {"任务" } ,notes = "根据需求获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询相关任务（计划）", tags = {"任务" } ,notes = "根据需求查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据需求获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据需求查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目任务", tags = {"任务" } ,notes = "根据需求获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询项目任务", tags = {"任务" } ,notes = "根据需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取根任务", tags = {"任务" } ,notes = "根据需求获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询根任务", tags = {"任务" } ,notes = "根据需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据需求获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据需求查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取todo列表查询", tags = {"任务" } ,notes = "根据需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询todo列表查询", tags = {"任务" } ,notes = "根据需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取任务类型分组", tags = {"任务" } ,notes = "根据需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询任务类型分组", tags = {"任务" } ,notes = "根据需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据需求获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByStory(@PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据需求查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
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

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目批量更新任务", tags = {"任务" },  notes = "根据项目批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setProject(project_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目检查任务", tags = {"任务" },  notes = "根据项目检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

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

    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询指派给我任务", tags = {"任务" } ,notes = "根据项目查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Bug相关任务", tags = {"任务" } ,notes = "根据项目查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询通过模块查询", tags = {"任务" } ,notes = "根据项目查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"任务" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询数据查询（子任务）", tags = {"任务" } ,notes = "根据项目查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"任务" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务（更多）", tags = {"任务" } ,notes = "根据项目查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务", tags = {"任务" } ,notes = "根据项目查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务（树）", tags = {"任务" } ,notes = "根据项目查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询用户年度完成任务", tags = {"任务" } ,notes = "根据项目查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询当前项目任务", tags = {"任务" } ,notes = "根据项目查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DefaultRow", tags = {"任务" } ,notes = "根据项目查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询ES批量的导入", tags = {"任务" } ,notes = "根据项目查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我代理的任务", tags = {"任务" } ,notes = "根据项目查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"任务" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我相关的任务", tags = {"任务" } ,notes = "根据项目查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"任务" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我的收藏", tags = {"任务" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"任务" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询相关任务（计划）", tags = {"任务" } ,notes = "根据项目查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"任务" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目任务", tags = {"任务" } ,notes = "根据项目查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询根任务", tags = {"任务" } ,notes = "根据项目查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询todo列表查询", tags = {"任务" } ,notes = "根据项目查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询任务类型分组", tags = {"任务" } ,notes = "根据项目查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立任务", tags = {"任务" },  notes = "根据产品产品计划建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划批量建立任务", tags = {"任务" },  notes = "根据产品产品计划批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setPlan(productplan_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划更新任务", tags = {"任务" },  notes = "根据产品产品计划更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划批量更新任务", tags = {"任务" },  notes = "根据产品产品计划批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setPlan(productplan_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除任务", tags = {"任务" },  notes = "根据产品产品计划删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划批量删除任务", tags = {"任务" },  notes = "根据产品产品计划批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlan(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取任务", tags = {"任务" },  notes = "根据产品产品计划获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划获取任务草稿", tags = {"任务" },  notes = "根据产品产品计划获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setPlan(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划检查任务", tags = {"任务" },  notes = "根据产品产品计划检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划保存任务", tags = {"任务" },  notes = "根据产品产品计划保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划批量保存任务", tags = {"任务" },  notes = "根据产品产品计划批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setPlan(productplan_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务]", tags = {"任务" },  notes = "批量处理[根据产品产品计划任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务", tags = {"任务" },  notes = "根据产品产品计划任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setPlan(productplan_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取指派给我任务", tags = {"任务" } ,notes = "根据产品产品计划获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询指派给我任务", tags = {"任务" } ,notes = "根据产品产品计划查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品产品计划获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品产品计划查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取Bug相关任务", tags = {"任务" } ,notes = "根据产品产品计划获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询Bug相关任务", tags = {"任务" } ,notes = "根据产品产品计划查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取通过模块查询", tags = {"任务" } ,notes = "根据产品产品计划获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询通过模块查询", tags = {"任务" } ,notes = "根据产品产品计划查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取数据查询（子任务）", tags = {"任务" } ,notes = "根据产品产品计划获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询数据查询（子任务）", tags = {"任务" } ,notes = "根据产品产品计划查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取子任务（更多）", tags = {"任务" } ,notes = "根据产品产品计划获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询子任务（更多）", tags = {"任务" } ,notes = "根据产品产品计划查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取子任务", tags = {"任务" } ,notes = "根据产品产品计划获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询子任务", tags = {"任务" } ,notes = "根据产品产品计划查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取子任务（树）", tags = {"任务" } ,notes = "根据产品产品计划获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询子任务（树）", tags = {"任务" } ,notes = "根据产品产品计划查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品产品计划获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品产品计划查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取当前项目任务", tags = {"任务" } ,notes = "根据产品产品计划获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询当前项目任务", tags = {"任务" } ,notes = "根据产品产品计划查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"任务" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询DEFAULT", tags = {"任务" } ,notes = "根据产品产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取DefaultRow", tags = {"任务" } ,notes = "根据产品产品计划获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询DefaultRow", tags = {"任务" } ,notes = "根据产品产品计划查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取ES批量的导入", tags = {"任务" } ,notes = "根据产品产品计划获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询ES批量的导入", tags = {"任务" } ,notes = "根据产品产品计划查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我代理的任务", tags = {"任务" } ,notes = "根据产品产品计划获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我代理的任务", tags = {"任务" } ,notes = "根据产品产品计划查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我相关的任务", tags = {"任务" } ,notes = "根据产品产品计划获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我相关的任务", tags = {"任务" } ,notes = "根据产品产品计划查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品产品计划获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品产品计划查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品产品计划获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品产品计划查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品产品计划获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品产品计划查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我的收藏", tags = {"任务" } ,notes = "根据产品产品计划获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我的收藏", tags = {"任务" } ,notes = "根据产品产品计划查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品产品计划获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品产品计划查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品产品计划查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取相关任务（计划）", tags = {"任务" } ,notes = "根据产品产品计划获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询相关任务（计划）", tags = {"任务" } ,notes = "根据产品产品计划查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品产品计划获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品产品计划查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目任务", tags = {"任务" } ,notes = "根据产品产品计划获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询项目任务", tags = {"任务" } ,notes = "根据产品产品计划查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取根任务", tags = {"任务" } ,notes = "根据产品产品计划获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询根任务", tags = {"任务" } ,notes = "根据产品产品计划查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品产品计划获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品产品计划查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品产品计划获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品产品计划查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品产品计划获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品产品计划查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品产品计划查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取todo列表查询", tags = {"任务" } ,notes = "根据产品产品计划获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询todo列表查询", tags = {"任务" } ,notes = "根据产品产品计划查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取任务类型分组", tags = {"任务" } ,notes = "根据产品产品计划获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询任务类型分组", tags = {"任务" } ,notes = "根据产品产品计划查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品产品计划获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品产品计划查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody TaskSearchContext context) {
        context.setN_plan_eq(productplan_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求建立任务", tags = {"任务" },  notes = "根据产品需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求批量建立任务", tags = {"任务" },  notes = "根据产品需求批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求更新任务", tags = {"任务" },  notes = "根据产品需求更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求批量更新任务", tags = {"任务" },  notes = "根据产品需求批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求删除任务", tags = {"任务" },  notes = "根据产品需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求批量删除任务", tags = {"任务" },  notes = "根据产品需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求获取任务", tags = {"任务" },  notes = "根据产品需求获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求获取任务草稿", tags = {"任务" },  notes = "根据产品需求获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求检查任务", tags = {"任务" },  notes = "根据产品需求检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求保存任务", tags = {"任务" },  notes = "根据产品需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求批量保存任务", tags = {"任务" },  notes = "根据产品需求批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setStory(story_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取指派给我任务", tags = {"任务" } ,notes = "根据产品需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询指派给我任务", tags = {"任务" } ,notes = "根据产品需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Bug相关任务", tags = {"任务" } ,notes = "根据产品需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Bug相关任务", tags = {"任务" } ,notes = "根据产品需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取通过模块查询", tags = {"任务" } ,notes = "根据产品需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询通过模块查询", tags = {"任务" } ,notes = "根据产品需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取数据查询（子任务）", tags = {"任务" } ,notes = "根据产品需求获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询数据查询（子任务）", tags = {"任务" } ,notes = "根据产品需求查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取子任务（更多）", tags = {"任务" } ,notes = "根据产品需求获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询子任务（更多）", tags = {"任务" } ,notes = "根据产品需求查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取子任务", tags = {"任务" } ,notes = "根据产品需求获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询子任务", tags = {"任务" } ,notes = "根据产品需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取子任务（树）", tags = {"任务" } ,notes = "根据产品需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询子任务（树）", tags = {"任务" } ,notes = "根据产品需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取当前项目任务", tags = {"任务" } ,notes = "根据产品需求获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询当前项目任务", tags = {"任务" } ,notes = "根据产品需求查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"任务" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"任务" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取DefaultRow", tags = {"任务" } ,notes = "根据产品需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询DefaultRow", tags = {"任务" } ,notes = "根据产品需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取ES批量的导入", tags = {"任务" } ,notes = "根据产品需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询ES批量的导入", tags = {"任务" } ,notes = "根据产品需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我代理的任务", tags = {"任务" } ,notes = "根据产品需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我代理的任务", tags = {"任务" } ,notes = "根据产品需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我相关的任务", tags = {"任务" } ,notes = "根据产品需求获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我相关的任务", tags = {"任务" } ,notes = "根据产品需求查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我的收藏", tags = {"任务" } ,notes = "根据产品需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我的收藏", tags = {"任务" } ,notes = "根据产品需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取相关任务（计划）", tags = {"任务" } ,notes = "根据产品需求获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询相关任务（计划）", tags = {"任务" } ,notes = "根据产品需求查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品需求获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据产品需求查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目任务", tags = {"任务" } ,notes = "根据产品需求获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询项目任务", tags = {"任务" } ,notes = "根据产品需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取根任务", tags = {"任务" } ,notes = "根据产品需求获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询根任务", tags = {"任务" } ,notes = "根据产品需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品需求获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据产品需求查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取todo列表查询", tags = {"任务" } ,notes = "根据产品需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询todo列表查询", tags = {"任务" } ,notes = "根据产品需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取任务类型分组", tags = {"任务" } ,notes = "根据产品需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询任务类型分组", tags = {"任务" } ,notes = "根据产品需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品需求获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据产品需求查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块建立任务", tags = {"任务" },  notes = "根据项目任务模块建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks")
    public ResponseEntity<TaskDTO> createByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块批量建立任务", tags = {"任务" },  notes = "根据项目任务模块批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setModule(projectmodule_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块更新任务", tags = {"任务" },  notes = "根据项目任务模块更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块批量更新任务", tags = {"任务" },  notes = "根据项目任务模块批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setModule(projectmodule_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块删除任务", tags = {"任务" },  notes = "根据项目任务模块删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块批量删除任务", tags = {"任务" },  notes = "根据项目任务模块批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProjectModule(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块获取任务", tags = {"任务" },  notes = "根据项目任务模块获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务模块获取任务草稿", tags = {"任务" },  notes = "根据项目任务模块获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setModule(projectmodule_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_ACTIVATION_BUT')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_ASSIGN_BUT')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_CANCEL_BUT')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块检查任务", tags = {"任务" },  notes = "根据项目任务模块检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEndByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/computebeginandendbatch")
    public ResponseEntity<Boolean> computeBeginAndEndByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeBeginAndEndBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4MultipleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/computehours4multiplebatch")
    public ResponseEntity<Boolean> computeHours4MultipleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeHours4MultipleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHoursByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/computeworkinghoursbatch")
    public ResponseEntity<Boolean> computeWorkingHoursByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.computeWorkingHoursBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/createbycyclebatch")
    public ResponseEntity<Boolean> createByCycleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createByCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasksByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/createcycletasksbatch")
    public ResponseEntity<Boolean> createCycleTasksByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.createCycleTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> deleteByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.delete(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/deletebatch")
    public ResponseEntity<Boolean> deleteByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/linkplanbatch")
    public ResponseEntity<Boolean> linkPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.linkPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_PAUSE_BUT')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinueByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/recordtimzeroleftaftercontinuebatch")
    public ResponseEntity<Boolean> recordTimZeroLeftAfterContinueByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimZeroLeftAfterContinueBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/recordtimatezeroleftbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/recordtimatezeroleftafterstartbatch")
    public ResponseEntity<Boolean> recordTimateZeroLeftAfterStartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordTimateZeroLeftAfterStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块保存任务", tags = {"任务" },  notes = "根据项目任务模块保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务模块批量保存任务", tags = {"任务" },  notes = "根据项目任务模块批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setModule(projectmodule_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'SRFUR__TASK_START_BUT')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/updateparentstatus")
    public ResponseEntity<TaskDTO> updateParentStatusByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateParentStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/updateparentstatusbatch")
    public ResponseEntity<Boolean> updateParentStatusByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateParentStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatusByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务]", tags = {"任务" },  notes = "批量处理[根据项目任务模块任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/updaterelatedplanstatusbatch")
    public ResponseEntity<Boolean> updateRelatedPlanStatusByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.updateRelatedPlanStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务", tags = {"任务" },  notes = "根据项目任务模块任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setModule(projectmodule_id);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取指派给我任务", tags = {"任务" } ,notes = "根据项目任务模块获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询指派给我任务", tags = {"任务" } ,notes = "根据项目任务模块查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目任务模块获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目任务模块查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目任务模块获取Bug相关任务", tags = {"任务" } ,notes = "根据项目任务模块获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目任务模块查询Bug相关任务", tags = {"任务" } ,notes = "根据项目任务模块查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取通过模块查询", tags = {"任务" } ,notes = "根据项目任务模块获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询通过模块查询", tags = {"任务" } ,notes = "根据项目任务模块查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取数据查询（子任务）", tags = {"任务" } ,notes = "根据项目任务模块获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchilddefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询数据查询（子任务）", tags = {"任务" } ,notes = "根据项目任务模块查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchilddefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取子任务（更多）", tags = {"任务" } ,notes = "根据项目任务模块获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchilddefaultmore")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildDefaultMoreByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询子任务（更多）", tags = {"任务" } ,notes = "根据项目任务模块查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchilddefaultmore")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildDefaultMoreByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取子任务", tags = {"任务" } ,notes = "根据项目任务模块获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询子任务", tags = {"任务" } ,notes = "根据项目任务模块查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取子任务（树）", tags = {"任务" } ,notes = "根据项目任务模块获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询子任务（树）", tags = {"任务" } ,notes = "根据项目任务模块查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目任务模块获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询用户年度完成任务", tags = {"任务" } ,notes = "根据项目任务模块查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取当前项目任务", tags = {"任务" } ,notes = "根据项目任务模块获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurProjectTaskQueryByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询当前项目任务", tags = {"任务" } ,notes = "根据项目任务模块查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurProjectTaskQueryByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取DEFAULT", tags = {"任务" } ,notes = "根据项目任务模块获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询DEFAULT", tags = {"任务" } ,notes = "根据项目任务模块查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取DefaultRow", tags = {"任务" } ,notes = "根据项目任务模块获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询DefaultRow", tags = {"任务" } ,notes = "根据项目任务模块查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取ES批量的导入", tags = {"任务" } ,notes = "根据项目任务模块获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询ES批量的导入", tags = {"任务" } ,notes = "根据项目任务模块查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我代理的任务", tags = {"任务" } ,notes = "根据项目任务模块获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我代理的任务", tags = {"任务" } ,notes = "根据项目任务模块查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我相关的任务", tags = {"任务" } ,notes = "根据项目任务模块获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyalltask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAllTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我相关的任务", tags = {"任务" } ,notes = "根据项目任务模块查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyalltask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAllTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目任务模块获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目任务模块查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目任务模块获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目任务模块查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目任务模块获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目任务模块查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我的收藏", tags = {"任务" } ,notes = "根据项目任务模块获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我的收藏", tags = {"任务" } ,notes = "根据项目任务模块查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目任务模块获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目任务模块查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务模块查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取相关任务（计划）", tags = {"任务" } ,notes = "根据项目任务模块获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskPlanTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询相关任务（计划）", tags = {"任务" } ,notes = "根据项目任务模块查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskPlanTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目任务模块获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchprojectapptask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectAppTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目任务模块查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchprojectapptask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectAppTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取项目任务", tags = {"任务" } ,notes = "根据项目任务模块获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询项目任务", tags = {"任务" } ,notes = "根据项目任务模块查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取根任务", tags = {"任务" } ,notes = "根据项目任务模块获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询根任务", tags = {"任务" } ,notes = "根据项目任务模块查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目任务模块获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtasklinkplan")
	public ResponseEntity<List<TaskDTO>> fetchTaskTaskLinkPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目任务模块查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtasklinkplan")
	public ResponseEntity<Page<TaskDTO>> searchTaskTaskLinkPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目任务模块获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目任务模块查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目任务模块获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目任务模块查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目任务模块查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取todo列表查询", tags = {"任务" } ,notes = "根据项目任务模块获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询todo列表查询", tags = {"任务" } ,notes = "根据项目任务模块查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取任务类型分组", tags = {"任务" } ,notes = "根据项目任务模块获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询任务类型分组", tags = {"任务" } ,notes = "根据项目任务模块查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目任务模块获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTypeGroupPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id,@RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目任务模块查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchTaskTypeGroupPlanByProjectProjectModule(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody TaskSearchContext context) {
        context.setN_module_eq(projectmodule_id);
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
}

