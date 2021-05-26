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
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
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
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(task_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("task_id") Long task_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}")
    public ResponseEntity<TaskDTO> get(@PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(task_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraft(TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.activate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'ASSIGNTO')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignTo(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.assignTo(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CANCEL')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancel(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.cancel(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
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
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "计算开始时间和完成时间", tags = {"任务" },  notes = "计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computebeginandend")
    public ResponseEntity<TaskDTO> computeBeginAndEnd(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeBeginAndEnd(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "更新父任务时间", tags = {"任务" },  notes = "更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computehours4multiple")
    public ResponseEntity<TaskDTO> computeHours4Multiple(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeHours4Multiple(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "更新工作时间", tags = {"任务" },  notes = "更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/computeworkinghours")
    public ResponseEntity<TaskDTO> computeWorkingHours(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.computeWorkingHours(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "需求变更确认", tags = {"任务" },  notes = "需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChange(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createbycycle")
    public ResponseEntity<TaskDTO> createByCycle(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createByCycle(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "创建周期任务", tags = {"任务" },  notes = "创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/createcycletasks")
    public ResponseEntity<TaskDTO> createCycleTasks(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.createCycleTasks(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/delete")
    public ResponseEntity<TaskDTO> delete(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.delete(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'FINISH')")
    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finish(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.finish(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取下一个团队成员(完成)", tags = {"任务" },  notes = "获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUser(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivity(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "获取团队成员", tags = {"任务" },  notes = "获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernames(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "关联计划", tags = {"任务" },  notes = "关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/linkplan")
    public ResponseEntity<TaskDTO> linkPlan(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.linkPlan(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "其他更新", tags = {"任务" },  notes = "其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'PAUSE')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pause(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.pause(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'RECORDESTIMATE')")
    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "继续任务时填入预计剩余为0设置为进行中", tags = {"任务" },  notes = "继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskDTO> recordTimZeroLeftAfterContinue(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "预计剩余为0进行中", tags = {"任务" },  notes = "预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleft")
    public ResponseEntity<TaskDTO> recordTimateZeroLeft(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeft(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "开始任务时填入预计剩余为0设为进行中", tags = {"任务" },  notes = "开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskDTO> recordTimateZeroLeftAfterStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'RESTART')")
    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.restart(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/save")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        taskService.save(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "行为", tags = {"任务" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessage(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "发送消息前置处理", tags = {"任务" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcess(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'START')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> start(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.start(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "检查多人任务操作权限", tags = {"任务" },  notes = "检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForward(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskForward(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
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
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "更新父任务计划状态", tags = {"任务" },  notes = "更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskDTO> updateRelatedPlanStatus(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateRelatedPlanStatus(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "更新需求版本", tags = {"任务" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersion(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain);
        taskdto = taskMapping.toDto(domain);
        Map<String,Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());
        taskdto.setSrfopprivs(opprivs);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @ApiOperation(value = "根据项目检查任务", tags = {"任务" },  notes = "根据项目检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/save")
    public ResponseEntity<TaskDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(domain));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
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
}

