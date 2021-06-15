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
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TaskTeamRuntime;

@Slf4j
@Api(tags = {"任务团队"})
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


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据任务建立任务团队", tags = {"任务团队" },  notes = "根据任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_TASK", task_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'READ', #taskteam_id, 'READ')")
    @ApiOperation(value = "根据任务获取任务团队", tags = {"任务团队" },  notes = "根据任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        if (domain == null || !(task_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_TASK", task_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'DELETE', #taskteam_id, 'DELETE')")
    @ApiOperation(value = "根据任务删除任务团队", tags = {"任务团队" },  notes = "根据任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam testget = taskteamService.get(taskteam_id);
        if (testget == null || !(task_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据任务批量删除任务团队", tags = {"任务团队" },  notes = "根据任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'UPDATE', #taskteam_id, 'UPDATE')")
    @ApiOperation(value = "根据任务更新任务团队", tags = {"任务团队" },  notes = "根据任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam testget = taskteamService.get(taskteam_id);
        if (testget == null || !(task_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_TASK", task_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据任务检查任务团队", tags = {"任务团队" },  notes = "根据任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_TASKTEAM', 'DENY')")
    @ApiOperation(value = "根据任务保存任务团队", tags = {"任务团队" },  notes = "根据任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_TASK', #task_id, 'READ', 'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务建立任务团队", tags = {"任务团队" },  notes = "根据项目任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'READ', #taskteam_id, 'READ')")
    @ApiOperation(value = "根据项目任务获取任务团队", tags = {"任务团队" },  notes = "根据项目任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        if (domain == null || !(task_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #taskteam_id, 'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务团队", tags = {"任务团队" },  notes = "根据项目任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam testget = taskteamService.get(taskteam_id);
        if (testget == null || !(task_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目任务批量删除任务团队", tags = {"任务团队" },  notes = "根据项目任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #taskteam_id, 'UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务团队", tags = {"任务团队" },  notes = "根据项目任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam testget = taskteamService.get(taskteam_id);
        if (testget == null || !(task_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        Map<String, Integer> opprivs = taskteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务检查任务团队", tags = {"任务团队" },  notes = "根据项目任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据项目任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_TASKTEAM', 'DENY')")
    @ApiOperation(value = "根据项目任务保存任务团队", tags = {"任务团队" },  notes = "根据项目任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }


    @PreAuthorize("test('IBZ_TASKTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
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

