package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskRuntime;

@Slf4j
@Api(tags = {"任务" })
@RestController("StandardAPI-task")
@RequestMapping("")
public class TaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public TaskMapping taskMapping;


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

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目任务收藏", tags = {"任务" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/tasknfavorites")
    public ResponseEntity<TaskDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskNFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
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
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchgantt")
	public ResponseEntity<List<Map>> fetchGanttByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
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

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
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

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取透视表结果集", tags = {"任务" } ,notes = "根据项目获取透视表结果集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/fetchreport")
	public ResponseEntity<List<TaskDTO>> fetchReportByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchReportDS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #task_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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

    @PreAuthorize("quickTest('ZT_TASK', 'DENY')")
    @ApiOperation(value = "根据项目任务收藏", tags = {"任务" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskfavorites")
    public ResponseEntity<TaskDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.taskFavorites(domain) ;
        taskdto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        taskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("test('ZT_TASK', 'ZT_PROJECT', #project_id, 'READ', #task_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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

}

