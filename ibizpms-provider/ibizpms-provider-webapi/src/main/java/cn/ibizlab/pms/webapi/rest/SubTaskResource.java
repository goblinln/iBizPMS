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

@Slf4j
@Api(tags = {"任务" })
@RestController("WebApi-subtask")
@RequestMapping("")
public class SubTaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public SubTaskMapping subtaskMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/{action}")
    public ResponseEntity<SubTaskDTO> dynamicCall(@PathVariable("subtask_id") Long subtask_id , @PathVariable("action") String action , @RequestBody SubTaskDTO subtaskdto) {
        Task domain = taskService.dynamicCall(subtask_id, action, subtaskMapping.toDomain(subtaskdto));
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks")
    public ResponseEntity<SubTaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目批量建立任务", tags = {"任务" },  notes = "根据项目批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
		taskService.update(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目激活任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.activate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目指派/转交任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.assignTo(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目取消任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.cancel(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目检查任务", tags = {"任务" },  notes = "根据项目检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目关闭任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.close(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目计算开始时间和完成时间任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/computebeginandend")
    public ResponseEntity<SubTaskDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.computeBeginAndEnd(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目更新父任务时间任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/computehours4multiple")
    public ResponseEntity<SubTaskDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.computeHours4Multiple(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目更新工作时间任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/computeworkinghours")
    public ResponseEntity<SubTaskDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.computeWorkingHours(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目需求变更确认任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.confirmStoryChange(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目创建周期任务任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/createbycycle")
    public ResponseEntity<SubTaskDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.createByCycle(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目创建周期任务任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/createcycletasks")
    public ResponseEntity<SubTaskDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.createCycleTasks(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目删除任务任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/delete")
    public ResponseEntity<SubTaskDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.delete(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目删除工时任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.deleteEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目编辑工时任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.editEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目完成任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.finish(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.getNextTeamUser(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.getUsernames(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目关联计划任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/linkplan")
    public ResponseEntity<SubTaskDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.linkPlan(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目其他更新任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.otherUpdate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目暂停任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.pause(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目工时录入任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.recordEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<SubTaskDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.recordTimZeroLeftAfterContinue(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目预计剩余为0进行中任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/recordtimatezeroleft")
    public ResponseEntity<SubTaskDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.recordTimateZeroLeft(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<SubTaskDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.recordTimateZeroLeftAfterStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目继续任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.restart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目批量保存任务", tags = {"任务" },  notes = "根据项目批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目行为任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.sendMessage(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目发送消息前置处理任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目开始任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.start(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目任务收藏任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.taskFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目检查多人任务操作权限任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.taskForward(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目任务收藏任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.taskNFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目更新父任务状态任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/updateparentstatus")
    public ResponseEntity<SubTaskDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.updateParentStatus(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目更新父任务计划状态任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/updaterelatedplanstatus")
    public ResponseEntity<SubTaskDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.updateRelatedPlanStatus(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目更新需求版本任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        
        domain.setId(subtask_id);
        domain = taskService.updateStoryVersion(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询指派给我任务", tags = {"任务" } ,notes = "根据项目查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Bug相关任务", tags = {"任务" } ,notes = "根据项目查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskBugTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询通过模块查询", tags = {"任务" } ,notes = "根据项目查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskByModuleByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"任务" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchchilddefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询数据查询（子任务）", tags = {"任务" } ,notes = "根据项目查询数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchchilddefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"任务" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchchilddefaultmore")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务（更多）", tags = {"任务" } ,notes = "根据项目查询子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchchilddefaultmore")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildDefaultMoreByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildDefaultMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务", tags = {"任务" } ,notes = "根据项目查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询子任务（树）", tags = {"任务" } ,notes = "根据项目查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询用户年度完成任务", tags = {"任务" } ,notes = "根据项目查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询当前项目任务", tags = {"任务" } ,notes = "根据项目查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DefaultRow", tags = {"任务" } ,notes = "根据项目查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultRowByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchesbulk")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询ES批量的导入", tags = {"任务" } ,notes = "根据项目查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchesbulk")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskESBulkByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmyagenttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我代理的任务", tags = {"任务" } ,notes = "根据项目查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmyagenttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"任务" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmyalltask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyAllTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我相关的任务", tags = {"任务" } ,notes = "根据项目查询我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmyalltask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyAllTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyAllTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"任务" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我的收藏", tags = {"任务" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyFavoritesByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"任务" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询相关任务（计划）", tags = {"任务" } ,notes = "根据项目查询相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskPlanTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchprojectapptask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目任务（项目立项）", tags = {"任务" } ,notes = "根据项目查询项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchprojectapptask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectAppTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchProjectAppTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"任务" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目任务", tags = {"任务" } ,notes = "根据项目查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询根任务", tags = {"任务" } ,notes = "根据项目查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskRootTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchtasklinkplan")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询关联计划（当前项目未关联）", tags = {"任务" } ,notes = "根据项目查询关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchtasklinkplan")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTaskLinkPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchTaskLinkPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchthisweekcompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchthisweekcompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询todo列表查询", tags = {"任务" } ,notes = "根据项目查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchSubTaskTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询任务类型分组", tags = {"任务" } ,notes = "根据项目查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchtypegroup")
	public ResponseEntity<Page<Map>> searchSubTaskTypeGroupByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchSubTaskTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询任务类型分组（计划）", tags = {"任务" } ,notes = "根据项目查询任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/subtasks/searchtypegroupplan")
	public ResponseEntity<Page<Map>> searchSubTaskTypeGroupPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        
        Page<Map> domains = taskService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}

}

