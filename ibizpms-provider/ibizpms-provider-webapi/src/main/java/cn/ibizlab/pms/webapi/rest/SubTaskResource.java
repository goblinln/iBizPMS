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

@Slf4j
@Api(tags = {"任务" })
@RestController("WebApi-subtask")
@RequestMapping("")
public class SubTaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    @Lazy
    public SubTaskMapping subtaskMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "新建任务", tags = {"任务" },  notes = "新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks")
    public ResponseEntity<SubTaskDTO> create(@Validated @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "批量新建任务", tags = {"任务" },  notes = "批量新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<SubTaskDTO> subtaskdtos) {
        taskService.createBatch(subtaskMapping.toDomain(subtaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "更新任务", tags = {"任务" },  notes = "更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> update(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
		Task domain  = subtaskMapping.toDomain(subtaskdto);
        domain .setId(subtask_id);
		taskService.update(domain );
		SubTaskDTO dto = subtaskMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "批量更新任务", tags = {"任务" },  notes = "批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SubTaskDTO> subtaskdtos) {
        taskService.updateBatch(subtaskMapping.toDomain(subtaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("subtask_id") Long subtask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "批量删除任务", tags = {"任务" },  notes = "批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/subtasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> get(@PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(new Task())));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activate(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.activate(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignTo(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.assignTo(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancel(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.cancel(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "检查任务", tags = {"任务" },  notes = "检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "关闭", tags = {"任务" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> close(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.close(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "需求变更确认", tags = {"任务" },  notes = "需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChange(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.confirmStoryChange(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimate(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.deleteEstimate(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimate(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.editEstimate(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finish(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.finish(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "获取下一个团队成员(完成)", tags = {"任务" },  notes = "获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUser(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getNextTeamUser(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "获取团队成员剩余工时（激活）", tags = {"任务" },  notes = "获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivity(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getTeamUserLeftActivity(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "获取团队成员剩余工时（开始或继续）", tags = {"任务" },  notes = "获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStart(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getTeamUserLeftStart(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "获取团队成员", tags = {"任务" },  notes = "获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernames(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getUsernames(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "其他更新", tags = {"任务" },  notes = "其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdate(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.otherUpdate(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pause(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.pause(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimate(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.recordEstimate(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restart(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.restart(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/save")
    public ResponseEntity<Boolean> save(@RequestBody SubTaskDTO subtaskdto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "批量保存任务", tags = {"任务" },  notes = "批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SubTaskDTO> subtaskdtos) {
        taskService.saveBatch(subtaskMapping.toDomain(subtaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "行为", tags = {"任务" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessage(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.sendMessage(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "发送消息前置处理", tags = {"任务" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcess(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.sendMsgPreProcess(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> start(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.start(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavorites(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.taskFavorites(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "检查多人任务操作权限", tags = {"任务" },  notes = "检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForward(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.taskForward(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "任务收藏", tags = {"任务" },  notes = "任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavorites(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.taskNFavorites(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "更新需求版本", tags = {"任务" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersion(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.updateStoryVersion(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "获取指派给我任务", tags = {"任务" } ,notes = "获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchAssignedToMyTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "查询指派给我任务", tags = {"任务" } ,notes = "查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchAssignedToMyTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "获取指派给我任务（PC）", tags = {"任务" } ,notes = "获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchAssignedToMyTaskPc(TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "查询指派给我任务（PC）", tags = {"任务" } ,notes = "查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchAssignedToMyTaskPc(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "获取Bug相关任务", tags = {"任务" } ,notes = "获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchBugTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "查询Bug相关任务", tags = {"任务" } ,notes = "查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchBugTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "获取通过模块查询", tags = {"任务" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchByModule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "查询通过模块查询", tags = {"任务" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchByModule(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "获取子任务", tags = {"任务" } ,notes = "获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchChildTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "查询子任务", tags = {"任务" } ,notes = "查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchChildTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "获取子任务（树）", tags = {"任务" } ,notes = "获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchChildTaskTree(TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "查询子任务（树）", tags = {"任务" } ,notes = "查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchChildTaskTree(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "获取用户年度完成任务", tags = {"任务" } ,notes = "获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchCurFinishTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "查询用户年度完成任务", tags = {"任务" } ,notes = "查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchCurFinishTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchDefault(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "获取DefaultRow", tags = {"任务" } ,notes = "获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchDefaultRow(TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "查询DefaultRow", tags = {"任务" } ,notes = "查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchDefaultRow(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchMyCompleteTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchMyCompleteTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "获取我完成的任务（汇报）", tags = {"任务" } ,notes = "获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchMyCompleteTaskZS(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "查询我完成的任务（汇报）", tags = {"任务" } ,notes = "查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchMyCompleteTaskZS(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "获取我的收藏", tags = {"任务" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchMyFavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "查询我的收藏", tags = {"任务" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchMyFavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchMyTomorrowPlanTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchMyTomorrowPlanTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "获取项目任务", tags = {"任务" } ,notes = "获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchProjectTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "查询项目任务", tags = {"任务" } ,notes = "查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchProjectTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "获取根任务", tags = {"任务" } ,notes = "获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchRootTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "查询根任务", tags = {"任务" } ,notes = "查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchRootTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "获取todo列表查询", tags = {"任务" } ,notes = "获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchTodoListTask(TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "查询todo列表查询", tags = {"任务" } ,notes = "查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchTodoListTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取任务类型分组", tags = {"任务" } ,notes = "获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/subtasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTypeGroup(TaskSearchContext context) {
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "查询任务类型分组", tags = {"任务" } ,notes = "查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTypeGroup(@RequestBody TaskSearchContext context) {
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}


    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据任务建立任务", tags = {"任务" },  notes = "根据任务建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks")
    public ResponseEntity<SubTaskDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据任务批量建立任务", tags = {"任务" },  notes = "根据任务批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> createBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据任务更新任务", tags = {"任务" },  notes = "根据任务更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
		taskService.update(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据任务批量更新任务", tags = {"任务" },  notes = "根据任务批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> updateBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据任务删除任务", tags = {"任务" },  notes = "根据任务删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据任务批量删除任务", tags = {"任务" },  notes = "根据任务批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> removeBatchByTask(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据任务获取任务", tags = {"任务" },  notes = "根据任务获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务获取任务草稿", tags = {"任务" },  notes = "根据任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByTask(@PathVariable("task_id") Long task_id) {
        Task domain = new Task();
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.activate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignToByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.assignTo(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancelByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.cancel(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据任务检查任务", tags = {"任务" },  notes = "根据任务检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> closeByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.close(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChangeByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.deleteEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.editEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finishByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.finish(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUserByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivityByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStartByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernamesByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getUsernames(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.otherUpdate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pauseByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.pause(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimateByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.recordEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restartByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.restart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据任务保存任务", tags = {"任务" },  notes = "根据任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/save")
    public ResponseEntity<Boolean> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据任务批量保存任务", tags = {"任务" },  notes = "根据任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessageByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMessage(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcessByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> startByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.start(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavoritesByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForwardByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskForward(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavoritesByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskNFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersionByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据任务获取指派给我任务", tags = {"任务" } ,notes = "根据任务获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据任务查询指派给我任务", tags = {"任务" } ,notes = "根据任务查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据任务获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据任务获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskPcByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据任务查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据任务查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskPcByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据任务获取Bug相关任务", tags = {"任务" } ,notes = "根据任务获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskBugTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据任务查询Bug相关任务", tags = {"任务" } ,notes = "根据任务查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskBugTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据任务获取通过模块查询", tags = {"任务" } ,notes = "根据任务获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskByModuleByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据任务查询通过模块查询", tags = {"任务" } ,notes = "根据任务查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskByModuleByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据任务获取子任务", tags = {"任务" } ,notes = "根据任务获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据任务查询子任务", tags = {"任务" } ,notes = "根据任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据任务获取子任务（树）", tags = {"任务" } ,notes = "根据任务获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskTreeByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据任务查询子任务（树）", tags = {"任务" } ,notes = "根据任务查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskTreeByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据任务获取用户年度完成任务", tags = {"任务" } ,notes = "根据任务获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurFinishTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据任务查询用户年度完成任务", tags = {"任务" } ,notes = "根据任务查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurFinishTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"任务" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据任务获取DefaultRow", tags = {"任务" } ,notes = "根据任务获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultRowByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据任务查询DefaultRow", tags = {"任务" } ,notes = "根据任务查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultRowByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskZSByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskZSByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据任务获取我的收藏", tags = {"任务" } ,notes = "根据任务获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyFavoritesByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据任务查询我的收藏", tags = {"任务" } ,notes = "根据任务查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyFavoritesByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据任务获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据任务查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据任务查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据任务获取项目任务", tags = {"任务" } ,notes = "根据任务获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectTaskByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据任务查询项目任务", tags = {"任务" } ,notes = "根据任务查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据任务获取根任务", tags = {"任务" } ,notes = "根据任务获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskRootTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据任务查询根任务", tags = {"任务" } ,notes = "根据任务查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskRootTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据任务获取todo列表查询", tags = {"任务" } ,notes = "根据任务获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTodoListTaskByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据任务查询todo列表查询", tags = {"任务" } ,notes = "根据任务查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTodoListTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据任务获取任务类型分组", tags = {"任务" } ,notes = "根据任务获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/subtasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchSubTaskTypeGroupByTask(@PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据任务查询任务类型分组", tags = {"任务" } ,notes = "根据任务查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchSubTaskTypeGroupByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据需求任务建立任务", tags = {"任务" },  notes = "根据需求任务建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks")
    public ResponseEntity<SubTaskDTO> createByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据需求任务批量建立任务", tags = {"任务" },  notes = "根据需求任务批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> createBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据需求任务更新任务", tags = {"任务" },  notes = "根据需求任务更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> updateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
		taskService.update(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据需求任务批量更新任务", tags = {"任务" },  notes = "根据需求任务批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> updateBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求任务删除任务", tags = {"任务" },  notes = "根据需求任务删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> removeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据需求任务批量删除任务", tags = {"任务" },  notes = "根据需求任务批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> removeBatchByStoryTask(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据需求任务获取任务", tags = {"任务" },  notes = "根据需求任务获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> getByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求任务获取任务草稿", tags = {"任务" },  notes = "根据需求任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = new Task();
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.activate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignToByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.assignTo(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancelByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.cancel(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据需求任务检查任务", tags = {"任务" },  notes = "根据需求任务检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> closeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.close(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChangeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.deleteEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.editEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finishByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.finish(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUserByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivityByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStartByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernamesByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getUsernames(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.otherUpdate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pauseByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.pause(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.recordEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restartByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.restart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据需求任务保存任务", tags = {"任务" },  notes = "根据需求任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<Boolean> saveByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据需求任务批量保存任务", tags = {"任务" },  notes = "根据需求任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessageByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMessage(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcessByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> startByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.start(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavoritesByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForwardByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskForward(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavoritesByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskNFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersionByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求任务获取指派给我任务", tags = {"任务" } ,notes = "根据需求任务获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据需求任务查询指派给我任务", tags = {"任务" } ,notes = "根据需求任务查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求任务获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求任务获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskPcByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据需求任务查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据需求任务查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskPcByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求任务获取Bug相关任务", tags = {"任务" } ,notes = "根据需求任务获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskBugTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据需求任务查询Bug相关任务", tags = {"任务" } ,notes = "根据需求任务查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskBugTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据需求任务获取通过模块查询", tags = {"任务" } ,notes = "根据需求任务获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskByModuleByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据需求任务查询通过模块查询", tags = {"任务" } ,notes = "根据需求任务查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskByModuleByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求任务获取子任务", tags = {"任务" } ,notes = "根据需求任务获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据需求任务查询子任务", tags = {"任务" } ,notes = "根据需求任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求任务获取子任务（树）", tags = {"任务" } ,notes = "根据需求任务获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskTreeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据需求任务查询子任务（树）", tags = {"任务" } ,notes = "根据需求任务查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskTreeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求任务获取用户年度完成任务", tags = {"任务" } ,notes = "根据需求任务获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurFinishTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据需求任务查询用户年度完成任务", tags = {"任务" } ,notes = "根据需求任务查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurFinishTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据需求任务获取DEFAULT", tags = {"任务" } ,notes = "根据需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据需求任务查询DEFAULT", tags = {"任务" } ,notes = "根据需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求任务获取DefaultRow", tags = {"任务" } ,notes = "根据需求任务获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultRowByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据需求任务查询DefaultRow", tags = {"任务" } ,notes = "根据需求任务查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultRowByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据需求任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskZSByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据需求任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskZSByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据需求任务获取我的收藏", tags = {"任务" } ,notes = "根据需求任务获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyFavoritesByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据需求任务查询我的收藏", tags = {"任务" } ,notes = "根据需求任务查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyFavoritesByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求任务获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据需求任务查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据需求任务查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据需求任务获取项目任务", tags = {"任务" } ,notes = "根据需求任务获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据需求任务查询项目任务", tags = {"任务" } ,notes = "根据需求任务查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求任务获取根任务", tags = {"任务" } ,notes = "根据需求任务获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskRootTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据需求任务查询根任务", tags = {"任务" } ,notes = "根据需求任务查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskRootTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求任务获取todo列表查询", tags = {"任务" } ,notes = "根据需求任务获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTodoListTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据需求任务查询todo列表查询", tags = {"任务" } ,notes = "根据需求任务查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTodoListTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求任务获取任务类型分组", tags = {"任务" } ,notes = "根据需求任务获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchSubTaskTypeGroupByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据需求任务查询任务类型分组", tags = {"任务" } ,notes = "根据需求任务查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchSubTaskTypeGroupByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据项目任务建立任务", tags = {"任务" },  notes = "根据项目任务建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks")
    public ResponseEntity<SubTaskDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据项目任务批量建立任务", tags = {"任务" },  notes = "根据项目任务批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> createBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据项目任务更新任务", tags = {"任务" },  notes = "根据项目任务更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
		taskService.update(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据项目任务批量更新任务", tags = {"任务" },  notes = "根据项目任务批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目任务删除任务", tags = {"任务" },  notes = "根据项目任务删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目任务批量删除任务", tags = {"任务" },  notes = "根据项目任务批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据项目任务获取任务", tags = {"任务" },  notes = "根据项目任务获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务获取任务草稿", tags = {"任务" },  notes = "根据项目任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = new Task();
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.activate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignToByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.assignTo(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancelByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.cancel(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据项目任务检查任务", tags = {"任务" },  notes = "根据项目任务检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> closeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.close(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChangeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.deleteEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.editEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finishByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.finish(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUserByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivityByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStartByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernamesByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getUsernames(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.otherUpdate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pauseByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.pause(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.recordEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restartByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.restart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据项目任务保存任务", tags = {"任务" },  notes = "根据项目任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<Boolean> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据项目任务批量保存任务", tags = {"任务" },  notes = "根据项目任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessageByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMessage(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcessByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> startByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.start(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavoritesByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForwardByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskForward(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavoritesByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskNFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersionByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据项目任务获取指派给我任务", tags = {"任务" } ,notes = "根据项目任务获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据项目任务查询指派给我任务", tags = {"任务" } ,notes = "根据项目任务查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据项目任务获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目任务获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskPcByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据项目任务查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据项目任务查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskPcByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据项目任务获取Bug相关任务", tags = {"任务" } ,notes = "根据项目任务获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskBugTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据项目任务查询Bug相关任务", tags = {"任务" } ,notes = "根据项目任务查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskBugTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据项目任务获取通过模块查询", tags = {"任务" } ,notes = "根据项目任务获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskByModuleByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据项目任务查询通过模块查询", tags = {"任务" } ,notes = "根据项目任务查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskByModuleByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据项目任务获取子任务", tags = {"任务" } ,notes = "根据项目任务获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据项目任务查询子任务", tags = {"任务" } ,notes = "根据项目任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据项目任务获取子任务（树）", tags = {"任务" } ,notes = "根据项目任务获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskTreeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据项目任务查询子任务（树）", tags = {"任务" } ,notes = "根据项目任务查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskTreeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据项目任务获取用户年度完成任务", tags = {"任务" } ,notes = "根据项目任务获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurFinishTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据项目任务查询用户年度完成任务", tags = {"任务" } ,notes = "根据项目任务查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurFinishTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据项目任务获取DefaultRow", tags = {"任务" } ,notes = "根据项目任务获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultRowByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据项目任务查询DefaultRow", tags = {"任务" } ,notes = "根据项目任务查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultRowByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据项目任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据项目任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskZSByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据项目任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskZSByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据项目任务获取我的收藏", tags = {"任务" } ,notes = "根据项目任务获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyFavoritesByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据项目任务查询我的收藏", tags = {"任务" } ,notes = "根据项目任务查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyFavoritesByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据项目任务获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据项目任务查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据项目任务查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据项目任务获取项目任务", tags = {"任务" } ,notes = "根据项目任务获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据项目任务查询项目任务", tags = {"任务" } ,notes = "根据项目任务查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据项目任务获取根任务", tags = {"任务" } ,notes = "根据项目任务获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskRootTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据项目任务查询根任务", tags = {"任务" } ,notes = "根据项目任务查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskRootTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据项目任务获取todo列表查询", tags = {"任务" } ,notes = "根据项目任务获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTodoListTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据项目任务查询todo列表查询", tags = {"任务" } ,notes = "根据项目任务查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTodoListTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目任务获取任务类型分组", tags = {"任务" } ,notes = "根据项目任务获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchSubTaskTypeGroupByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据项目任务查询任务类型分组", tags = {"任务" } ,notes = "根据项目任务查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchSubTaskTypeGroupByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据产品需求任务建立任务", tags = {"任务" },  notes = "根据产品需求任务建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks")
    public ResponseEntity<SubTaskDTO> createByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
		taskService.create(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据产品需求任务批量建立任务", tags = {"任务" },  notes = "根据产品需求任务批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据产品需求任务更新任务", tags = {"任务" },  notes = "根据产品需求任务更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> updateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
		taskService.update(domain);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据产品需求任务批量更新任务", tags = {"任务" },  notes = "根据产品需求任务批量更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
            domain.setParent(task_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求任务删除任务", tags = {"任务" },  notes = "根据产品需求任务删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<Boolean> removeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(subtask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据产品需求任务批量删除任务", tags = {"任务" },  notes = "根据产品需求任务批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryTask(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据产品需求任务获取任务", tags = {"任务" },  notes = "根据产品需求任务获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}")
    public ResponseEntity<SubTaskDTO> getByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id) {
        Task domain = taskService.get(subtask_id);
        SubTaskDTO dto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求任务获取任务草稿", tags = {"任务" },  notes = "根据产品需求任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id) {
        Task domain = new Task();
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/activate")
    public ResponseEntity<SubTaskDTO> activateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.activate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/assignto")
    public ResponseEntity<SubTaskDTO> assignToByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.assignTo(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/cancel")
    public ResponseEntity<SubTaskDTO> cancelByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.cancel(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "根据产品需求任务检查任务", tags = {"任务" },  notes = "根据产品需求任务检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(subtaskMapping.toDomain(subtaskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/close")
    public ResponseEntity<SubTaskDTO> closeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.close(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-ConfirmStoryChange-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/confirmstorychange")
    public ResponseEntity<SubTaskDTO> confirmStoryChangeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/deleteestimate")
    public ResponseEntity<SubTaskDTO> deleteEstimateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.deleteEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/editestimate")
    public ResponseEntity<SubTaskDTO> editEstimateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.editEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/finish")
    public ResponseEntity<SubTaskDTO> finishByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.finish(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetNextTeamUser-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getnextteamuser")
    public ResponseEntity<SubTaskDTO> getNextTeamUserByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getNextTeamUser(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftActivity-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftactivity")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftActivityByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftActivity(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetTeamUserLeftStart-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getteamuserleftstart")
    public ResponseEntity<SubTaskDTO> getTeamUserLeftStartByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getTeamUserLeftStart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-GetUsernames-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getusernames")
    public ResponseEntity<SubTaskDTO> getUsernamesByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.getUsernames(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-OtherUpdate-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/otherupdate")
    public ResponseEntity<SubTaskDTO> otherUpdateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.otherUpdate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/pause")
    public ResponseEntity<SubTaskDTO> pauseByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.pause(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/recordestimate")
    public ResponseEntity<SubTaskDTO> recordEstimateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.recordEstimate(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/restart")
    public ResponseEntity<SubTaskDTO> restartByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.restart(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据产品需求任务保存任务", tags = {"任务" },  notes = "根据产品需求任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<Boolean> saveByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据产品需求任务批量保存任务", tags = {"任务" },  notes = "根据产品需求任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMessage-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmessage")
    public ResponseEntity<SubTaskDTO> sendMessageByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMessage(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/sendmsgpreprocess")
    public ResponseEntity<SubTaskDTO> sendMsgPreProcessByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.sendMsgPreProcess(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/start")
    public ResponseEntity<SubTaskDTO> startByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.start(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskFavorites-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskfavorites")
    public ResponseEntity<SubTaskDTO> taskFavoritesByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskForward-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/taskforward")
    public ResponseEntity<SubTaskDTO> taskForwardByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskForward(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-TaskNFavorites-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/tasknfavorites")
    public ResponseEntity<SubTaskDTO> taskNFavoritesByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.taskNFavorites(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-UpdateStoryVersion-all')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatestoryversion")
    public ResponseEntity<SubTaskDTO> updateStoryVersionByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain = taskService.updateStoryVersion(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求任务获取指派给我任务", tags = {"任务" } ,notes = "根据产品需求任务获取指派给我任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTask-all')")
	@ApiOperation(value = "根据产品需求任务查询指派给我任务", tags = {"任务" } ,notes = "根据产品需求任务查询指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求任务获取指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求任务获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchassignedtomytaskpc")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskAssignedToMyTaskPcByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchAssignedToMyTaskPc-all')")
	@ApiOperation(value = "根据产品需求任务查询指派给我任务（PC）", tags = {"任务" } ,notes = "根据产品需求任务查询指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchassignedtomytaskpc")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskAssignedToMyTaskPcByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchAssignedToMyTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求任务获取Bug相关任务", tags = {"任务" } ,notes = "根据产品需求任务获取Bug相关任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchbugtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskBugTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchBugTask-all')")
	@ApiOperation(value = "根据产品需求任务查询Bug相关任务", tags = {"任务" } ,notes = "根据产品需求任务查询Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchbugtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskBugTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchBugTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据产品需求任务获取通过模块查询", tags = {"任务" } ,notes = "根据产品需求任务获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchbymodule")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskByModuleByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据产品需求任务查询通过模块查询", tags = {"任务" } ,notes = "根据产品需求任务查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchbymodule")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskByModuleByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求任务获取子任务", tags = {"任务" } ,notes = "根据产品需求任务获取子任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTask-all')")
	@ApiOperation(value = "根据产品需求任务查询子任务", tags = {"任务" } ,notes = "根据产品需求任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求任务获取子任务（树）", tags = {"任务" } ,notes = "根据产品需求任务获取子任务（树）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtasktree")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskTreeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchChildTaskTree-all')")
	@ApiOperation(value = "根据产品需求任务查询子任务（树）", tags = {"任务" } ,notes = "根据产品需求任务查询子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtasktree")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskTreeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTaskTree(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求任务获取用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求任务获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchcurfinishtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskCurFinishTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchCurFinishTask-all')")
	@ApiOperation(value = "根据产品需求任务查询用户年度完成任务", tags = {"任务" } ,notes = "根据产品需求任务查询用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchcurfinishtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskCurFinishTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchCurFinishTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据产品需求任务获取DEFAULT", tags = {"任务" } ,notes = "根据产品需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefault")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据产品需求任务查询DEFAULT", tags = {"任务" } ,notes = "根据产品需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchdefault")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求任务获取DefaultRow", tags = {"任务" } ,notes = "根据产品需求任务获取DefaultRow")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchdefaultrow")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskDefaultRowByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefaultRow-all')")
	@ApiOperation(value = "根据产品需求任务查询DefaultRow", tags = {"任务" } ,notes = "根据产品需求任务查询DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchdefaultrow")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskDefaultRowByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchDefaultRow(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTask-all')")
	@ApiOperation(value = "根据产品需求任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求任务获取我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmycompletetaskzs")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyCompleteTaskZSByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyCompleteTaskZS-all')")
	@ApiOperation(value = "根据产品需求任务查询我完成的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务查询我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmycompletetaskzs")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyCompleteTaskZSByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyCompleteTaskZS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据产品需求任务获取我的收藏", tags = {"任务" } ,notes = "根据产品需求任务获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmyfavorites")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyFavoritesByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyFavorites-all')")
	@ApiOperation(value = "根据产品需求任务查询我的收藏", tags = {"任务" } ,notes = "根据产品需求任务查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmyfavorites")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyFavoritesByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求任务获取我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchmytomorrowplantask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskMyTomorrowPlanTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchMyTomorrowPlanTask-all')")
	@ApiOperation(value = "根据产品需求任务查询我计划参与的任务（汇报）", tags = {"任务" } ,notes = "根据产品需求任务查询我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchmytomorrowplantask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskMyTomorrowPlanTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchMyTomorrowPlanTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据产品需求任务获取项目任务", tags = {"任务" } ,notes = "根据产品需求任务获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchprojecttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskProjectTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchProjectTask-all')")
	@ApiOperation(value = "根据产品需求任务查询项目任务", tags = {"任务" } ,notes = "根据产品需求任务查询项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchprojecttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskProjectTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchProjectTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求任务获取根任务", tags = {"任务" } ,notes = "根据产品需求任务获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchroottask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskRootTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据产品需求任务查询根任务", tags = {"任务" } ,notes = "根据产品需求任务查询根任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchroottask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskRootTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求任务获取todo列表查询", tags = {"任务" } ,notes = "根据产品需求任务获取todo列表查询")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtodolisttask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskTodoListTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTodoListTask-all')")
	@ApiOperation(value = "根据产品需求任务查询todo列表查询", tags = {"任务" } ,notes = "根据产品需求任务查询todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtodolisttask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskTodoListTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchTodoListTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求任务获取任务类型分组", tags = {"任务" } ,notes = "根据产品需求任务获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchSubTaskTypeGroupByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

	@ApiOperation(value = "根据产品需求任务查询任务类型分组", tags = {"任务" } ,notes = "根据产品需求任务查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchSubTaskTypeGroupByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
}

