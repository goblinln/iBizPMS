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


    @PreAuthorize("@TaskRuntime.quickTest('START')")
    @ApiOperation(value = "根据系统用户开始", tags = {"任务" },  notes = "根据系统用户开始")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@TaskRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户更新任务", tags = {"任务" },  notes = "根据系统用户更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.quickTest('FINISH')")
    @ApiOperation(value = "根据系统用户完成", tags = {"任务" },  notes = "根据系统用户完成")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户建立任务", tags = {"任务" },  notes = "根据系统用户建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks")
    public ResponseEntity<TaskDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户批量建立任务", tags = {"任务" },  notes = "根据系统用户批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.quickTest('RESTART')")
    @ApiOperation(value = "根据系统用户继续", tags = {"任务" },  notes = "根据系统用户继续")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CANCEL')")
    @ApiOperation(value = "根据系统用户取消", tags = {"任务" },  notes = "根据系统用户取消")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取当前项目任务", tags = {"任务" } ,notes = "根据系统用户获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchCurProjectTaskQueryBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.quickTest('ASSIGNTO')")
    @ApiOperation(value = "根据系统用户指派/转交", tags = {"任务" },  notes = "根据系统用户指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户需求变更确认", tags = {"任务" },  notes = "根据系统用户需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CLOSE')")
    @ApiOperation(value = "根据系统用户关闭", tags = {"任务" },  notes = "根据系统用户关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取任务草稿", tags = {"任务" },  notes = "根据系统用户获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取任务类型分组", tags = {"任务" } ,notes = "根据系统用户获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/tasks/fetchgantt")
	public ResponseEntity<List<Map>> fetchGanttBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@TaskRuntime.quickTest('ACTIVATE')")
    @ApiOperation(value = "根据系统用户激活", tags = {"任务" },  notes = "根据系统用户激活")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取DEFAULT", tags = {"任务" } ,notes = "根据系统用户获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.quickTest('PAUSE')")
    @ApiOperation(value = "根据系统用户暂停", tags = {"任务" },  notes = "根据系统用户暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取透视表结果集", tags = {"任务" } ,notes = "根据系统用户获取透视表结果集")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/tasks/fetchreport")
	public ResponseEntity<List<TaskDTO>> fetchReportBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchReportDS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户删除任务", tags = {"任务" },  notes = "根据系统用户删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@TaskRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户批量删除任务", tags = {"任务" },  notes = "根据系统用户批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户获取任务", tags = {"任务" },  notes = "根据系统用户获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目开始", tags = {"任务" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目完成", tags = {"任务" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目继续", tags = {"任务" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目取消", tags = {"任务" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"任务" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目需求变更确认", tags = {"任务" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目关闭", tags = {"任务" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目激活", tags = {"任务" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目暂停", tags = {"任务" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目开始", tags = {"任务" },  notes = "根据系统用户项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/start")
    public ResponseEntity<TaskDTO> startBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目更新任务", tags = {"任务" },  notes = "根据系统用户项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> updateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
		taskService.update(domain);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目完成", tags = {"任务" },  notes = "根据系统用户项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/finish")
    public ResponseEntity<TaskDTO> finishBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目建立任务", tags = {"任务" },  notes = "根据系统用户项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks")
    public ResponseEntity<TaskDTO> createBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目批量建立任务", tags = {"任务" },  notes = "根据系统用户项目批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setProject(project_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目继续", tags = {"任务" },  notes = "根据系统用户项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/restart")
    public ResponseEntity<TaskDTO> restartBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目取消", tags = {"任务" },  notes = "根据系统用户项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/cancel")
    public ResponseEntity<TaskDTO> cancelBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取当前项目任务", tags = {"任务" } ,notes = "根据系统用户项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/tasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskDTO>> fetchCurProjectTaskQueryBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目指派/转交", tags = {"任务" },  notes = "根据系统用户项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/assignto")
    public ResponseEntity<TaskDTO> assignToBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户项目需求变更确认", tags = {"任务" },  notes = "根据系统用户项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/confirmstorychange")
    public ResponseEntity<TaskDTO> confirmStoryChangeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.confirmStoryChange(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目关闭", tags = {"任务" },  notes = "根据系统用户项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/close")
    public ResponseEntity<TaskDTO> closeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目获取任务草稿", tags = {"任务" },  notes = "根据系统用户项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, TaskDTO dto) {
        Task domain = taskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取任务类型分组", tags = {"任务" } ,notes = "根据系统用户项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/tasks/fetchgantt")
	public ResponseEntity<List<Map>> fetchGanttBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Map> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目激活", tags = {"任务" },  notes = "根据系统用户项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/activate")
    public ResponseEntity<TaskDTO> activateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取DEFAULT", tags = {"任务" } ,notes = "根据系统用户项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchDefaultBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目暂停", tags = {"任务" },  notes = "根据系统用户项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/pause")
    public ResponseEntity<TaskDTO> pauseBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain.setId(task_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取透视表结果集", tags = {"任务" } ,notes = "根据系统用户项目获取透视表结果集")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/tasks/fetchreport")
	public ResponseEntity<List<TaskDTO>> fetchReportBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchReportDS(context) ;
        List<TaskDTO> list = taskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目删除任务", tags = {"任务" },  notes = "根据系统用户项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<Boolean> removeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据系统用户项目批量删除任务", tags = {"任务" },  notes = "根据系统用户项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProject(@RequestBody List<Long> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目获取任务", tags = {"任务" },  notes = "根据系统用户项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

