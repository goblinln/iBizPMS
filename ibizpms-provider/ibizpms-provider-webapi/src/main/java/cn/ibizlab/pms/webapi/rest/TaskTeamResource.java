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

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

