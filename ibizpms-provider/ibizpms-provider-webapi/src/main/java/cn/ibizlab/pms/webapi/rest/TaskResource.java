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
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;

@Slf4j
@Api(tags = {"任务" })
@RestController("WebApi-task")
@RequestMapping("")
public class TaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    @Lazy
    public TaskMapping taskMapping;

    @Autowired
    private ITaskEstimateService taskestimateService;

    @Autowired
    private ITaskTeamService taskteamService;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "新建任务", tags = {"任务" },  notes = "新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public ResponseEntity<TaskDTO> create(@Validated @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "批量新建任务", tags = {"任务" },  notes = "批量新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.createBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "更新任务", tags = {"任务" },  notes = "更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
		Task domain  = taskMapping.toDomain(taskdto);
        domain .setId(task_id);
		taskService.update(domain );
		TaskDTO dto = taskMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "批量更新任务", tags = {"任务" },  notes = "批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.updateBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("task_id") Long task_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "批量删除任务", tags = {"任务" },  notes = "批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.activate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "批量处理[激活]", tags = {"任务" },  notes = "批量处理[激活]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignTo(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.assignTo(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "批量处理[指派/转交]", tags = {"任务" },  notes = "批量处理[指派/转交]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancel(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.cancel(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "关闭", tags = {"任务" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> close(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.close(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "批量处理[关闭]", tags = {"任务" },  notes = "批量处理[关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "需求变更确认", tags = {"任务" },  notes = "需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChange(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "批量处理[需求变更确认]", tags = {"任务" },  notes = "批量处理[需求变更确认]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "批量处理[删除工时]", tags = {"任务" },  notes = "批量处理[删除工时]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "下载", tags = {"任务" },  notes = "下载")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> download(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.download(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "批量处理[下载]", tags = {"任务" },  notes = "批量处理[下载]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "批量处理[编辑工时]", tags = {"任务" },  notes = "批量处理[编辑工时]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finish(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.finish(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "批量处理[完成]", tags = {"任务" },  notes = "批量处理[完成]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/finishbatch")
    public ResponseEntity<Boolean> finishBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "获取下一个团队成员(完成)", tags = {"任务" },  notes = "获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUser(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getNextTeamUser(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "批量处理[获取下一个团队成员(完成)]", tags = {"任务" },  notes = "批量处理[获取下一个团队成员(完成)]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivity(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftActivity(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "批量处理[获取团队成员剩余工时（激活）]", tags = {"任务" },  notes = "批量处理[获取团队成员剩余工时（激活）]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getTeamUserLeftStart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "批量处理[获取团队成员剩余工时（开始或继续）]", tags = {"任务" },  notes = "批量处理[获取团队成员剩余工时（开始或继续）]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "获取团队成员", tags = {"任务" },  notes = "获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernames(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.getUsernames(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "其他更新", tags = {"任务" },  notes = "其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.otherUpdate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "批量处理[其他更新]", tags = {"任务" },  notes = "批量处理[其他更新]")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pause(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.pause(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "批量处理[暂停]", tags = {"任务" },  notes = "批量处理[暂停]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimate(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "批量处理[工时录入]", tags = {"任务" },  notes = "批量处理[工时录入]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restart(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.restart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "批量处理[继续]", tags = {"任务" },  notes = "批量处理[继续]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/restartbatch")
    public ResponseEntity<Boolean> restartBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/save")
    public ResponseEntity<Boolean> save(@RequestBody TaskDTO taskdto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "批量保存任务", tags = {"任务" },  notes = "批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TaskDTO> taskdtos) {
        taskService.saveBatch(taskMapping.toDomain(taskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "行为", tags = {"任务" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessage(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMessage(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "批量处理[行为]", tags = {"任务" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "发送消息前置处理", tags = {"任务" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcess(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.sendMsgPreProcess(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "批量处理[发送消息前置处理]", tags = {"任务" },  notes = "批量处理[发送消息前置处理]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> start(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.start(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "批量处理[开始]", tags = {"任务" },  notes = "批量处理[开始]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/startbatch")
    public ResponseEntity<Boolean> startBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "检查多人任务操作权限", tags = {"任务" },  notes = "检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForward(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskForward(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "批量处理[检查多人任务操作权限]", tags = {"任务" },  notes = "批量处理[检查多人任务操作权限]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardBatch(@RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavorites(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "更新需求版本", tags = {"任务" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersion(@PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.updateStoryVersion(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "获取指派给我任务", tags = {"任务" } ,notes = "获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchAssignedToMyTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "查询指派给我任务", tags = {"任务" } ,notes = "查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchAssignedToMyTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "获取指派给我任务（PC）", tags = {"任务" } ,notes = "获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchAssignedToMyTaskPc(TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "查询指派给我任务（PC）", tags = {"任务" } ,notes = "查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchAssignedToMyTaskPc(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "获取Bug相关任务", tags = {"任务" } ,notes = "获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchBugTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "查询Bug相关任务", tags = {"任务" } ,notes = "查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchBugTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "获取通过模块查询", tags = {"任务" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchByModule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "查询通过模块查询", tags = {"任务" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchByModule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "获取子任务", tags = {"任务" } ,notes = "获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchChildTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "查询子任务", tags = {"任务" } ,notes = "查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchChildTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "获取子任务（树）", tags = {"任务" } ,notes = "获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchChildTaskTree(TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "查询子任务（树）", tags = {"任务" } ,notes = "查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchChildTaskTree(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "获取用户年度完成任务", tags = {"任务" } ,notes = "获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchCurFinishTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "查询用户年度完成任务", tags = {"任务" } ,notes = "查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchCurFinishTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "获取DefaultRow", tags = {"任务" } ,notes = "获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchDefaultRow(TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "查询DefaultRow", tags = {"任务" } ,notes = "查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchDefaultRow(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"任务" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchESBulk(TaskSearchContext context) {
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"任务" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchESBulk(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "获取我代理的任务", tags = {"任务" } ,notes = "获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchMyAgentTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "查询我代理的任务", tags = {"任务" } ,notes = "查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchMyAgentTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMobDaily(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMobDaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMobMonthly(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMobMonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskMonthlyZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskMonthlyZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchMyCompleteTaskZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchMyCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "获取我的收藏", tags = {"任务" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchMyFavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "查询我的收藏", tags = {"任务" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchMyFavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchMyPlansTaskMobMonthly(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchMyPlansTaskMobMonthly(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchMyTomorrowPlanTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchMyTomorrowPlanTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchMyTomorrowPlanTaskMobDaily(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchMyTomorrowPlanTaskMobDaily(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekCompleteTaskMobZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekCompleteTaskMobZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekCompleteTaskZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchNextWeekPlanCompleteTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchNextWeekPlanCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "获取项目任务", tags = {"任务" } ,notes = "获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchProjectTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "查询项目任务", tags = {"任务" } ,notes = "查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchProjectTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "获取根任务", tags = {"任务" } ,notes = "获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchRootTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "查询根任务", tags = {"任务" } ,notes = "查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchRootTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchThisMonthCompleteTaskChoice(TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchThisMonthCompleteTaskChoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskChoice(TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskChoice(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskMobZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskMobZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchThisWeekCompleteTaskZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchThisWeekCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "获取todo列表查询", tags = {"任务" } ,notes = "获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTodoListTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "查询todo列表查询", tags = {"任务" } ,notes = "查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTodoListTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取任务类型分组", tags = {"任务" } ,notes = "获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTypeGroup(TaskSearchContext context) {
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "查询任务类型分组", tags = {"任务" } ,notes = "查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTypeGroup(@RequestBody TaskSearchContext context) {
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}


    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据需求建立任务", tags = {"任务" },  notes = "根据需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求删除任务", tags = {"任务" },  notes = "根据需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求批量删除任务", tags = {"任务" },  notes = "根据需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> downloadByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.download(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求任务]", tags = {"任务" },  notes = "批量处理[根据需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadByStory(@PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据需求保存任务", tags = {"任务" },  notes = "根据需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/save")
    public ResponseEntity<Boolean> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据需求任务", tags = {"任务" },  notes = "根据需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByStory(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求获取指派给我任务", tags = {"任务" } ,notes = "根据需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求查询指派给我任务", tags = {"任务" } ,notes = "根据需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求获取Bug相关任务", tags = {"任务" } ,notes = "根据需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求查询Bug相关任务", tags = {"任务" } ,notes = "根据需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据需求查询通过模块查询", tags = {"任务" } ,notes = "根据需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求获取子任务", tags = {"任务" } ,notes = "根据需求获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求查询子任务", tags = {"任务" } ,notes = "根据需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求获取子任务（树）", tags = {"任务" } ,notes = "根据需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求查询子任务（树）", tags = {"任务" } ,notes = "根据需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"任务" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求获取DefaultRow", tags = {"任务" } ,notes = "根据需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求查询DefaultRow", tags = {"任务" } ,notes = "根据需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据需求获取ES批量的导入", tags = {"任务" } ,notes = "根据需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据需求查询ES批量的导入", tags = {"任务" } ,notes = "根据需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据需求获取我代理的任务", tags = {"任务" } ,notes = "根据需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据需求查询我代理的任务", tags = {"任务" } ,notes = "根据需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据需求查询我的收藏", tags = {"任务" } ,notes = "根据需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据需求查询项目任务", tags = {"任务" } ,notes = "根据需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求获取根任务", tags = {"任务" } ,notes = "根据需求获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求查询根任务", tags = {"任务" } ,notes = "根据需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求获取todo列表查询", tags = {"任务" } ,notes = "根据需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求查询todo列表查询", tags = {"任务" } ,notes = "根据需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取任务类型分组", tags = {"任务" } ,notes = "根据需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByStory(@PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据需求查询任务类型分组", tags = {"任务" } ,notes = "根据需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByStory(@PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> downloadByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.download(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/save")
    public ResponseEntity<Boolean> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据项目查询指派给我任务", tags = {"任务" } ,notes = "根据项目查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据项目查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据项目查询Bug相关任务", tags = {"任务" } ,notes = "根据项目查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据项目查询通过模块查询", tags = {"任务" } ,notes = "根据项目查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据项目查询子任务", tags = {"任务" } ,notes = "根据项目查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据项目查询子任务（树）", tags = {"任务" } ,notes = "根据项目查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据项目查询用户年度完成任务", tags = {"任务" } ,notes = "根据项目查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据项目查询DefaultRow", tags = {"任务" } ,notes = "根据项目查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据项目查询ES批量的导入", tags = {"任务" } ,notes = "根据项目查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据项目查询我代理的任务", tags = {"任务" } ,notes = "根据项目查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据项目查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据项目查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据项目查询我的收藏", tags = {"任务" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据项目查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据项目查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据项目查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据项目查询项目任务", tags = {"任务" } ,notes = "根据项目查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据项目查询根任务", tags = {"任务" } ,notes = "根据项目查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据项目查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据项目查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据项目查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据项目查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据项目查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据项目查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据项目查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据项目查询todo列表查询", tags = {"任务" } ,notes = "根据项目查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByProject(@PathVariable("project_id") Long project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据项目查询任务类型分组", tags = {"任务" } ,notes = "根据项目查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据产品需求建立任务", tags = {"任务" },  notes = "根据产品需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求删除任务", tags = {"任务" },  notes = "根据产品需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求批量删除任务", tags = {"任务" },  notes = "根据产品需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> downloadByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.download(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据产品需求保存任务", tags = {"任务" },  notes = "根据产品需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/save")
    public ResponseEntity<Boolean> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据产品需求任务", tags = {"任务" },  notes = "根据产品需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求获取指派给我任务", tags = {"任务" } ,notes = "根据产品需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求查询指派给我任务", tags = {"任务" } ,notes = "根据产品需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求获取Bug相关任务", tags = {"任务" } ,notes = "根据产品需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求查询Bug相关任务", tags = {"任务" } ,notes = "根据产品需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据产品需求查询通过模块查询", tags = {"任务" } ,notes = "根据产品需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求获取子任务", tags = {"任务" } ,notes = "根据产品需求获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求查询子任务", tags = {"任务" } ,notes = "根据产品需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求获取子任务（树）", tags = {"任务" } ,notes = "根据产品需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求查询子任务（树）", tags = {"任务" } ,notes = "根据产品需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"任务" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求获取DefaultRow", tags = {"任务" } ,notes = "根据产品需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求查询DefaultRow", tags = {"任务" } ,notes = "根据产品需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据产品需求获取ES批量的导入", tags = {"任务" } ,notes = "根据产品需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据产品需求查询ES批量的导入", tags = {"任务" } ,notes = "根据产品需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据产品需求获取我代理的任务", tags = {"任务" } ,notes = "根据产品需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据产品需求查询我代理的任务", tags = {"任务" } ,notes = "根据产品需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据产品需求查询我的收藏", tags = {"任务" } ,notes = "根据产品需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据产品需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据产品需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据产品需求查询项目任务", tags = {"任务" } ,notes = "根据产品需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求获取根任务", tags = {"任务" } ,notes = "根据产品需求获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求查询根任务", tags = {"任务" } ,notes = "根据产品需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求获取todo列表查询", tags = {"任务" } ,notes = "根据产品需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求查询todo列表查询", tags = {"任务" } ,notes = "根据产品需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取任务类型分组", tags = {"任务" } ,notes = "根据产品需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据产品需求查询任务类型分组", tags = {"任务" } ,notes = "根据产品需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据需求模块需求建立任务", tags = {"任务" },  notes = "根据需求模块需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据需求模块需求批量建立任务", tags = {"任务" },  notes = "根据需求模块需求批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据需求模块需求更新任务", tags = {"任务" },  notes = "根据需求模块需求更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据需求模块需求批量更新任务", tags = {"任务" },  notes = "根据需求模块需求批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求模块需求删除任务", tags = {"任务" },  notes = "根据需求模块需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求模块需求批量删除任务", tags = {"任务" },  notes = "根据需求模块需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductModuleStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据需求模块需求获取任务", tags = {"任务" },  notes = "根据需求模块需求获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求模块需求获取任务草稿", tags = {"任务" },  notes = "根据需求模块需求获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求模块需求检查任务", tags = {"任务" },  notes = "根据需求模块需求检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> downloadByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.download(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据需求模块需求保存任务", tags = {"任务" },  notes = "根据需求模块需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/save")
    public ResponseEntity<Boolean> saveByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据需求模块需求批量保存任务", tags = {"任务" },  notes = "根据需求模块需求批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setStory(story_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据需求模块需求任务", tags = {"任务" },  notes = "根据需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求模块需求获取指派给我任务", tags = {"任务" } ,notes = "根据需求模块需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求模块需求查询指派给我任务", tags = {"任务" } ,notes = "根据需求模块需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求模块需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求模块需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求模块需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求模块需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求模块需求获取Bug相关任务", tags = {"任务" } ,notes = "根据需求模块需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求模块需求查询Bug相关任务", tags = {"任务" } ,notes = "根据需求模块需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据需求模块需求获取通过模块查询", tags = {"任务" } ,notes = "根据需求模块需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据需求模块需求查询通过模块查询", tags = {"任务" } ,notes = "根据需求模块需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求模块需求获取子任务", tags = {"任务" } ,notes = "根据需求模块需求获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求模块需求查询子任务", tags = {"任务" } ,notes = "根据需求模块需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求模块需求获取子任务（树）", tags = {"任务" } ,notes = "根据需求模块需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求模块需求查询子任务（树）", tags = {"任务" } ,notes = "根据需求模块需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求模块需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据需求模块需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求模块需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据需求模块需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据需求模块需求获取DEFAULT", tags = {"任务" } ,notes = "根据需求模块需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据需求模块需求查询DEFAULT", tags = {"任务" } ,notes = "根据需求模块需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求模块需求获取DefaultRow", tags = {"任务" } ,notes = "根据需求模块需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求模块需求查询DefaultRow", tags = {"任务" } ,notes = "根据需求模块需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据需求模块需求获取ES批量的导入", tags = {"任务" } ,notes = "根据需求模块需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据需求模块需求查询ES批量的导入", tags = {"任务" } ,notes = "根据需求模块需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据需求模块需求获取我代理的任务", tags = {"任务" } ,notes = "根据需求模块需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据需求模块需求查询我代理的任务", tags = {"任务" } ,notes = "根据需求模块需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据需求模块需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求模块需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据需求模块需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据需求模块需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求模块需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求模块需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求模块需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求模块需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据需求模块需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求模块需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据需求模块需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据需求模块需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据需求模块需求获取我的收藏", tags = {"任务" } ,notes = "根据需求模块需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据需求模块需求查询我的收藏", tags = {"任务" } ,notes = "根据需求模块需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求模块需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求模块需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据需求模块需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据需求模块需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求模块需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求模块需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据需求模块需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据需求模块需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求模块需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求模块需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求模块需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据需求模块需求获取项目任务", tags = {"任务" } ,notes = "根据需求模块需求获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据需求模块需求查询项目任务", tags = {"任务" } ,notes = "根据需求模块需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求模块需求获取根任务", tags = {"任务" } ,notes = "根据需求模块需求获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求模块需求查询根任务", tags = {"任务" } ,notes = "根据需求模块需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求模块需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求模块需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求模块需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据需求模块需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求模块需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求模块需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据需求模块需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据需求模块需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求模块需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据需求模块需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求模块需求获取todo列表查询", tags = {"任务" } ,notes = "根据需求模块需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求模块需求查询todo列表查询", tags = {"任务" } ,notes = "根据需求模块需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求模块需求获取任务类型分组", tags = {"任务" } ,notes = "根据需求模块需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据需求模块需求查询任务类型分组", tags = {"任务" } ,notes = "根据需求模块需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByProductModuleStory(@PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据产品需求模块需求建立任务", tags = {"任务" },  notes = "根据产品需求模块需求建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks")
    public ResponseEntity<TaskDTO> createByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据产品需求模块需求批量建立任务", tags = {"任务" },  notes = "根据产品需求模块需求批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据产品需求模块需求更新任务", tags = {"任务" },  notes = "根据产品需求模块需求更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据产品需求模块需求批量更新任务", tags = {"任务" },  notes = "根据产品需求模块需求批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setStory(story_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求模块需求删除任务", tags = {"任务" },  notes = "根据产品需求模块需求删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求模块需求批量删除任务", tags = {"任务" },  notes = "根据产品需求模块需求批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductModuleStory(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据产品需求模块需求获取任务", tags = {"任务" },  notes = "根据产品需求模块需求获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求模块需求获取任务草稿", tags = {"任务" },  notes = "根据产品需求模块需求获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/activatebatch")
    public ResponseEntity<Boolean> activateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/cancelbatch")
    public ResponseEntity<Boolean> cancelByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.cancelBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求模块需求检查任务", tags = {"任务" },  notes = "根据产品需求模块需求检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/closebatch")
    public ResponseEntity<Boolean> closeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/confirmstorychangebatch")
    public ResponseEntity<Boolean> confirmStoryChangeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.confirmStoryChangeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/deleteestimate")
    public ResponseEntity<TaskDTO> deleteEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/deleteestimatebatch")
    public ResponseEntity<Boolean> deleteEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.deleteEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Download-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/download")
    public ResponseEntity<TaskDTO> downloadByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.download(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/downloadbatch")
    public ResponseEntity<Boolean> downloadByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.downloadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/editestimate")
    public ResponseEntity<TaskDTO> editEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/editestimatebatch")
    public ResponseEntity<Boolean> editEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.editEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/finishbatch")
    public ResponseEntity<Boolean> finishByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getnextteamuser")
    public ResponseEntity<TaskDTO> getNextTeamUserByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getNextTeamUser(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/getnextteamuserbatch")
    public ResponseEntity<Boolean> getNextTeamUserByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getNextTeamUserBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftactivity")
    public ResponseEntity<TaskDTO> getTeamUserLeftActivityByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/getteamuserleftactivitybatch")
    public ResponseEntity<Boolean> getTeamUserLeftActivityByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftActivityBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getteamuserleftstart")
    public ResponseEntity<TaskDTO> getTeamUserLeftStartByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/getteamuserleftstartbatch")
    public ResponseEntity<Boolean> getTeamUserLeftStartByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.getTeamUserLeftStartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/getusernames")
    public ResponseEntity<TaskDTO> getUsernamesByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.getUsernames(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/otherupdate")
    public ResponseEntity<TaskDTO> otherUpdateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.otherUpdate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/otherupdatebatch")
    public ResponseEntity<Boolean> otherUpdateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.otherUpdateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/pausebatch")
    public ResponseEntity<Boolean> pauseByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.pauseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/recordestimate")
    public ResponseEntity<TaskDTO> recordEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/recordestimatebatch")
    public ResponseEntity<Boolean> recordEstimateByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.recordEstimateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/restartbatch")
    public ResponseEntity<Boolean> restartByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.restartBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据产品需求模块需求保存任务", tags = {"任务" },  notes = "根据产品需求模块需求保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/save")
    public ResponseEntity<Boolean> saveByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据产品需求模块需求批量保存任务", tags = {"任务" },  notes = "根据产品需求模块需求批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setStory(story_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/sendmessage")
    public ResponseEntity<TaskDTO> sendMessageByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.sendMessage(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/sendmsgpreprocess")
    public ResponseEntity<TaskDTO> sendMsgPreProcessByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/startbatch")
    public ResponseEntity<Boolean> startByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/taskforward")
    public ResponseEntity<TaskDTO> taskForwardByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskForward(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求模块需求任务]", tags = {"任务" },  notes = "批量处理[根据产品需求模块需求任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/taskforwardbatch")
    public ResponseEntity<Boolean> taskForwardByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domains = taskMapping.toDomain(taskdtos);
        boolean result = taskService.taskForwardBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据产品需求模块需求任务", tags = {"任务" },  notes = "根据产品需求模块需求任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/{task_id}/updatestoryversion")
    public ResponseEntity<TaskDTO> updateStoryVersionByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setStory(story_id);
        domain = taskService.updateStoryVersion(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取指派给我任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchassignedtomytask")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询指派给我任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchassignedtomytask")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求模块需求获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求模块需求获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskDTO>> fetchTaskAssignedToMyTaskPcByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求模块需求查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求模块需求查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<TaskDTO>> searchTaskAssignedToMyTaskPcByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取Bug相关任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchbugtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskBugTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询Bug相关任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchbugtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskBugTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据产品需求模块需求获取通过模块查询", tags = {"任务" } ,notes = "根据产品需求模块需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据产品需求模块需求查询通过模块查询", tags = {"任务" } ,notes = "根据产品需求模块需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchbymodule")
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取子任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchchildtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询子任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchchildtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求模块需求获取子任务（树）", tags = {"任务" } ,notes = "根据产品需求模块需求获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchchildtasktree")
	public ResponseEntity<List<TaskDTO>> fetchTaskChildTaskTreeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求模块需求查询子任务（树）", tags = {"任务" } ,notes = "根据产品需求模块需求查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchchildtasktree")
	public ResponseEntity<Page<TaskDTO>> searchTaskChildTaskTreeByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchcurfinishtask")
	public ResponseEntity<List<TaskDTO>> fetchTaskCurFinishTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchcurfinishtask")
	public ResponseEntity<Page<TaskDTO>> searchTaskCurFinishTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据产品需求模块需求获取DEFAULT", tags = {"任务" } ,notes = "根据产品需求模块需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据产品需求模块需求查询DEFAULT", tags = {"任务" } ,notes = "根据产品需求模块需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchdefault")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求模块需求获取DefaultRow", tags = {"任务" } ,notes = "根据产品需求模块需求获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchdefaultrow")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultRowByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求模块需求查询DefaultRow", tags = {"任务" } ,notes = "根据产品需求模块需求查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchdefaultrow")
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultRowByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据产品需求模块需求获取ES批量的导入", tags = {"任务" } ,notes = "根据产品需求模块需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchesbulk")
	public ResponseEntity<List<TaskDTO>> fetchTaskESBulkByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchESBulk-all')")
	@ApiOperation(value = "根据产品需求模块需求查询ES批量的导入", tags = {"任务" } ,notes = "根据产品需求模块需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchesbulk")
	public ResponseEntity<Page<TaskDTO>> searchTaskESBulkByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我代理的任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取我代理的任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyagenttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyAgentTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyAgentTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我代理的任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyagenttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyAgentTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyAgentTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobDailyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我完成的任务（移动端日报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobDailyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMobMonthlyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我完成的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMobMonthlyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskMonthlyZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskMonthlyZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我完成的任务（月报展示）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskmonthlyzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskMonthlyZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskMonthlyZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmycompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我的收藏", tags = {"任务" } ,notes = "根据产品需求模块需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyfavorites")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyFavoritesByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我的收藏", tags = {"任务" } ,notes = "根据产品需求模块需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyfavorites")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyFavoritesByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyPlansTaskMobMonthlyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyPlansTaskMobMonthly-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我计划参与的任务（移动端月报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmyplanstaskmobmonthly")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyPlansTaskMobMonthlyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyPlansTaskMobMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmytomorrowplantask")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskDTO>> fetchTaskMyTomorrowPlanTaskMobDailyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTaskMobDaily-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchmytomorrowplantaskmobdaily")
	public ResponseEntity<Page<TaskDTO>> searchTaskMyTomorrowPlanTaskMobDailyByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTaskMobDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskMobZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询移动端下周计划参与(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskMobZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskNextWeekPlanCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchNextWeekPlanCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询下周计划完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchnextweekplancompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskNextWeekPlanCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchNextWeekPlanCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取项目任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchprojecttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskProjectTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,@RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询项目任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchprojecttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskProjectTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取根任务", tags = {"任务" } ,notes = "根据产品需求模块需求获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询根任务", tags = {"任务" } ,notes = "根据产品需求模块需求查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchroottask")
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求模块需求获取我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求模块需求获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisMonthCompleteTaskChoiceByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisMonthCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求模块需求查询我本月完成的任务（下拉列表框）", tags = {"任务" } ,notes = "根据产品需求模块需求查询我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthismonthcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisMonthCompleteTaskChoiceByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisMonthCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetask")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求模块需求获取本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求模块需求获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskChoiceByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskChoice-all')")
	@ApiOperation(value = "根据产品需求模块需求查询本周已完成任务(下拉框选择)", tags = {"任务" } ,notes = "根据产品需求模块需求查询本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskchoice")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskChoiceByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskChoice(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskMobZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskMobZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询移动端本周已完成任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskmobzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskMobZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskMobZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求获取本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskDTO>> fetchTaskThisWeekCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchThisWeekCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求模块需求查询本周完成的任务(汇报)", tags = {"任务" } ,notes = "根据产品需求模块需求查询本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchthisweekcompletetaskzs")
	public ResponseEntity<Page<TaskDTO>> searchTaskThisWeekCompleteTaskZSByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchThisWeekCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求模块需求获取todo列表查询", tags = {"任务" } ,notes = "根据产品需求模块需求获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchtodolisttask")
	public ResponseEntity<List<TaskDTO>> fetchTaskTodoListTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求模块需求查询todo列表查询", tags = {"任务" } ,notes = "根据产品需求模块需求查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchtodolisttask")
	public ResponseEntity<Page<TaskDTO>> searchTaskTodoListTaskByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求模块需求获取任务类型分组", tags = {"任务" } ,notes = "根据产品需求模块需求获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id,TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据产品需求模块需求查询任务类型分组", tags = {"任务" } ,notes = "根据产品需求模块需求查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/{productmodule_id}/stories/{story_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByProductProductModuleStory(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @PathVariable("story_id") Long story_id, @RequestBody TaskSearchContext context) {
        context.setN_story_eq(story_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
}

