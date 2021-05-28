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
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TaskTeamRuntime;

@Slf4j
@Api(tags = {"任务团队" })
@RestController("WebApi-taskteam")
@RequestMapping("")
public class TaskTeamResource {

    @Autowired
    public ITaskTeamService taskteamService;

    @Autowired
    public TaskTeamRuntime taskteamRuntime;

    @Autowired
    @Lazy
    public TaskTeamMapping taskteamMapping;


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务建立任务团队", tags = {"任务团队" },  notes = "根据任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新任务团队", tags = {"任务团队" },  notes = "根据任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务删除任务团队", tags = {"任务团队" },  notes = "根据任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取任务团队", tags = {"任务团队" },  notes = "根据任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务检查任务团队", tags = {"任务团队" },  notes = "根据任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据任务保存任务团队", tags = {"任务团队" },  notes = "根据任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立任务团队", tags = {"任务团队" },  notes = "根据项目建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立任务团队", tags = {"任务团队" },  notes = "根据项目批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新任务团队", tags = {"任务团队" },  notes = "根据项目更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除任务团队", tags = {"任务团队" },  notes = "根据项目删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务团队", tags = {"任务团队" },  notes = "根据项目获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取任务团队草稿", tags = {"任务团队" },  notes = "根据项目获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"任务团队" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/activate")
    public ResponseEntity<TaskTeamDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.activate(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"任务团队" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/assignto")
    public ResponseEntity<TaskTeamDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.assignTo(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CANCEL')")
    @ApiOperation(value = "根据项目取消", tags = {"任务团队" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/cancel")
    public ResponseEntity<TaskTeamDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.cancel(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查任务团队", tags = {"任务团队" },  notes = "根据项目检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"任务团队" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/close")
    public ResponseEntity<TaskTeamDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.close(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目计算开始时间和完成时间", tags = {"任务团队" },  notes = "根据项目计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/computebeginandend")
    public ResponseEntity<TaskTeamDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.computeBeginAndEnd(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目更新父任务时间", tags = {"任务团队" },  notes = "根据项目更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/computehours4multiple")
    public ResponseEntity<TaskTeamDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.computeHours4Multiple(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目更新工作时间", tags = {"任务团队" },  notes = "根据项目更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/computeworkinghours")
    public ResponseEntity<TaskTeamDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.computeWorkingHours(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目需求变更确认", tags = {"任务团队" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/confirmstorychange")
    public ResponseEntity<TaskTeamDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.confirmStoryChange(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务团队" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/createbycycle")
    public ResponseEntity<TaskTeamDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.createByCycle(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务团队" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/createcycletasks")
    public ResponseEntity<TaskTeamDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.createCycleTasks(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目删除任务", tags = {"任务团队" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/delete")
    public ResponseEntity<TaskTeamDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.delete(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目删除工时", tags = {"任务团队" },  notes = "根据项目删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/deleteestimate")
    public ResponseEntity<TaskTeamDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.deleteEstimate(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目编辑工时", tags = {"任务团队" },  notes = "根据项目编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/editestimate")
    public ResponseEntity<TaskTeamDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.editEstimate(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'FINISH')")
    @ApiOperation(value = "根据项目完成", tags = {"任务团队" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/finish")
    public ResponseEntity<TaskTeamDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.finish(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)", tags = {"任务团队" },  notes = "根据项目获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/getnextteamuser")
    public ResponseEntity<TaskTeamDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.getNextTeamUser(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）", tags = {"任务团队" },  notes = "根据项目获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/getteamuserleftactivity")
    public ResponseEntity<TaskTeamDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.getTeamUserLeftActivity(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）", tags = {"任务团队" },  notes = "根据项目获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/getteamuserleftstart")
    public ResponseEntity<TaskTeamDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.getTeamUserLeftStart(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员", tags = {"任务团队" },  notes = "根据项目获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/getusernames")
    public ResponseEntity<TaskTeamDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.getUsernames(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目关联计划", tags = {"任务团队" },  notes = "根据项目关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/linkplan")
    public ResponseEntity<TaskTeamDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.linkPlan(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目其他更新", tags = {"任务团队" },  notes = "根据项目其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/otherupdate")
    public ResponseEntity<TaskTeamDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.otherUpdate(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'PAUSE')")
    @ApiOperation(value = "根据项目暂停", tags = {"任务团队" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/pause")
    public ResponseEntity<TaskTeamDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.pause(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RECORDESTIMATE')")
    @ApiOperation(value = "根据项目工时录入", tags = {"任务团队" },  notes = "根据项目工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/recordestimate")
    public ResponseEntity<TaskTeamDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.recordEstimate(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中", tags = {"任务团队" },  notes = "根据项目继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskTeamDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.recordTimZeroLeftAfterContinue(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目预计剩余为0进行中", tags = {"任务团队" },  notes = "根据项目预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/recordtimatezeroleft")
    public ResponseEntity<TaskTeamDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.recordTimateZeroLeft(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中", tags = {"任务团队" },  notes = "根据项目开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskTeamDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.recordTimateZeroLeftAfterStart(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RESTART')")
    @ApiOperation(value = "根据项目继续", tags = {"任务团队" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/restart")
    public ResponseEntity<TaskTeamDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.restart(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目保存任务团队", tags = {"任务团队" },  notes = "根据项目保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量保存任务团队", tags = {"任务团队" },  notes = "根据项目批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目行为", tags = {"任务团队" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/sendmessage")
    public ResponseEntity<TaskTeamDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.sendMessage(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"任务团队" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/sendmsgpreprocess")
    public ResponseEntity<TaskTeamDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.sendMsgPreProcess(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "根据项目开始", tags = {"任务团队" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/start")
    public ResponseEntity<TaskTeamDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.start(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"任务团队" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/taskfavorites")
    public ResponseEntity<TaskTeamDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.taskFavorites(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目检查多人任务操作权限", tags = {"任务团队" },  notes = "根据项目检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/taskforward")
    public ResponseEntity<TaskTeamDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.taskForward(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"任务团队" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskteams/{taskteam_id}/tasknfavorites")
    public ResponseEntity<TaskTeamDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.taskNFavorites(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务状态", tags = {"任务团队" },  notes = "根据项目更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/updateparentstatus")
    public ResponseEntity<TaskTeamDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.updateParentStatus(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务计划状态", tags = {"任务团队" },  notes = "根据项目更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskTeamDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.updateRelatedPlanStatus(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"任务团队" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskteams/{taskteam_id}/updatestoryversion")
    public ResponseEntity<TaskTeamDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        
        domain.setId(taskteam_id);
        domain = taskteamService.updateStoryVersion(domain) ;
        taskteamdto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务团队" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchassignedtomytask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchAssignedToMyTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务团队" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchAssignedToMyTaskPc(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务团队" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchbugtask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchBugTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务团队" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchbymodule")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchByModule(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"任务团队" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchchilddefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchChildDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"任务团队" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchchilddefaultmore")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchChildDefaultMore(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务团队" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchchildtask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchChildTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务团队" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchchildtasktree")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchChildTaskTree(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务团队" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchcurfinishtask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchCurFinishTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务团队" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchCurProjectTaskQuery(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务团队" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务团队" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchdefaultrow")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchDefaultRow(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务团队" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchesbulk")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchESBulk(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务团队" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmyagenttask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyAgentTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"任务团队" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmyalltask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyAllTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务团队" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmycompletetask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyCompleteTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务团队" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务团队" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务团队" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务团队" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyCompleteTaskZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"任务团队" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmyfavorites")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyFavorites(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务团队" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务团队" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyTomorrowPlanTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务团队" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务团队" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务团队" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务团队" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"任务团队" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchplantask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchPlanTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"任务团队" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchprojectapptask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchProjectAppTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"任务团队" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchprojecttask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchProjectTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务团队" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchroottask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchRootTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"任务团队" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchtasklinkplan")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchTaskLinkPlan(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务团队" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务团队" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchThisWeekCompleteTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务团队" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务团队" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务团队" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务团队" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchtodolisttask")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<TaskTeam> domains = taskteamService.searchTodoListTask(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务团队" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskTeamTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<Map> domains = taskteamService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"任务团队" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskteams/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskTeamTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskTeamSearchContext context) {
        
        Page<Map> domains = taskteamService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立任务团队", tags = {"任务团队" },  notes = "根据项目任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务团队", tags = {"任务团队" },  notes = "根据项目任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务团队", tags = {"任务团队" },  notes = "根据项目任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务团队", tags = {"任务团队" },  notes = "根据项目任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据项目任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务检查任务团队", tags = {"任务团队" },  notes = "根据项目任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据项目任务保存任务团队", tags = {"任务团队" },  notes = "根据项目任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

