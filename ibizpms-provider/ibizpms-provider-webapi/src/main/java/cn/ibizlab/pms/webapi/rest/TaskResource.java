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
import org.springframework.transaction.annotation.Transactional;
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
@RestController("WebApi-task")
@RequestMapping("")
public class TaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    @Lazy
    public TaskMapping taskMapping;

    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(new Task())));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "关闭", tags = {"任务" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/close")
    @Transactional
    public ResponseEntity<TaskDTO> close(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.close(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "暂停", tags = {"任务" },  notes = "暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/pause")
    @Transactional
    public ResponseEntity<TaskDTO> pause(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.pause(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "开始", tags = {"任务" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/start")
    @Transactional
    public ResponseEntity<TaskDTO> start(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.start(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "指派/转交", tags = {"任务" },  notes = "指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/assignto")
    @Transactional
    public ResponseEntity<TaskDTO> assignTo(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.assignTo(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "删除任务", tags = {"任务" },  notes = "删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}")
    @Transactional
    public ResponseEntity<Boolean> remove(@PathVariable("task_id") BigInteger task_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "批量删除任务", tags = {"任务" },  notes = "批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<BigInteger> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "更新任务", tags = {"任务" },  notes = "更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}")
    @Transactional
    public ResponseEntity<TaskDTO> update(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "取消", tags = {"任务" },  notes = "取消")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/cancel")
    @Transactional
    public ResponseEntity<TaskDTO> cancel(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.cancel(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "删除工时", tags = {"任务" },  notes = "删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/deleteestimate")
    @Transactional
    public ResponseEntity<TaskDTO> deleteEstimate(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.deleteEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "继续", tags = {"任务" },  notes = "继续")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/restart")
    @Transactional
    public ResponseEntity<TaskDTO> restart(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.restart(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "编辑工时", tags = {"任务" },  notes = "编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/editestimate")
    @Transactional
    public ResponseEntity<TaskDTO> editEstimate(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.editEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}")
    public ResponseEntity<TaskDTO> get(@PathVariable("task_id") BigInteger task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "工时录入", tags = {"任务" },  notes = "工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/recordestimate")
    @Transactional
    public ResponseEntity<TaskDTO> recordEstimate(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.recordEstimate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @ApiOperation(value = "检查任务", tags = {"任务" },  notes = "检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "新建任务", tags = {"任务" },  notes = "新建任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks")
    @Transactional
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskdto) {
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "完成", tags = {"任务" },  notes = "完成")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/finish")
    @Transactional
    public ResponseEntity<TaskDTO> finish(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.finish(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "激活", tags = {"任务" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/activate")
    @Transactional
    public ResponseEntity<TaskDTO> activate(@PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setId(task_id);
        domain = taskService.activate(domain);
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "获取通过模块查询", tags = {"任务" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchByModule(TaskSearchContext context) {
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
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTypeGroup-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTypeGroup-all')")
	@ApiOperation(value = "查询任务类型分组", tags = {"任务" } ,notes = "查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTypeGroup(@RequestBody TaskSearchContext context) {
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchDefault(TaskSearchContext context) {
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
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/getdraft")
    public ResponseEntity<TaskDTO> getDraftByProject(@PathVariable("project_id") BigInteger project_id) {
        Task domain = new Task();
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Close-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/close")
    @Transactional
    public ResponseEntity<TaskDTO> closeByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.close(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Pause-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/pause")
    @Transactional
    public ResponseEntity<TaskDTO> pauseByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.pause(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Start-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/start")
    @Transactional
    public ResponseEntity<TaskDTO> startByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.start(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-AssignTo-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/assignto")
    @Transactional
    public ResponseEntity<TaskDTO> assignToByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.assignTo(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}")
    @Transactional
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(task_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Remove-all')")
    @ApiOperation(value = "根据项目批量删除任务", tags = {"任务" },  notes = "根据项目批量删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<BigInteger> ids) {
        taskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Update-all')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}")
    @Transactional
    public ResponseEntity<TaskDTO> updateByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
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
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setProject(project_id);
        }
        taskService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Cancel-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/cancel")
    @Transactional
    public ResponseEntity<TaskDTO> cancelByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.cancel(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-DeleteEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/deleteestimate")
    @Transactional
    public ResponseEntity<TaskDTO> deleteEstimateByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.deleteEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Restart-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/restart")
    @Transactional
    public ResponseEntity<TaskDTO> restartByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.restart(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-EditEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/editestimate")
    @Transactional
    public ResponseEntity<TaskDTO> editEstimateByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.editEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据项目保存任务", tags = {"任务" },  notes = "根据项目保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/save")
    public ResponseEntity<Boolean> saveByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Save-all')")
    @ApiOperation(value = "根据项目批量保存任务", tags = {"任务" },  notes = "根据项目批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
             domain.setProject(project_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Get-all')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}")
    public ResponseEntity<TaskDTO> getByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id) {
        Task domain = taskService.get(task_id);
        TaskDTO dto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-RecordEstimate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/recordestimate")
    @Transactional
    public ResponseEntity<TaskDTO> recordEstimateByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.recordEstimate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @ApiOperation(value = "根据项目检查任务", tags = {"任务" },  notes = "根据项目检查任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskDTO taskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskService.checkKey(taskMapping.toDomain(taskdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks")
    @Transactional
    public ResponseEntity<TaskDTO> createByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        TaskDTO dto = taskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Create-all')")
    @ApiOperation(value = "根据项目批量建立任务", tags = {"任务" },  notes = "根据项目批量建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody List<TaskDTO> taskdtos) {
        List<Task> domainlist=taskMapping.toDomain(taskdtos);
        for(Task domain:domainlist){
            domain.setProject(project_id);
        }
        taskService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Finish-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/finish")
    @Transactional
    public ResponseEntity<TaskDTO> finishByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.finish(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-Activate-all')")
    @ApiOperation(value = "根据项目任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/activate")
    @Transactional
    public ResponseEntity<TaskDTO> activateByProject(@PathVariable("project_id") BigInteger project_id, @PathVariable("task_id") BigInteger task_id, @RequestBody TaskDTO taskdto) {
        Task domain = taskMapping.toDomain(taskdto);
        domain.setProject(project_id);
        domain = taskService.activate(domain) ;
        taskdto = taskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchRootTask-all')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchroottask")
	public ResponseEntity<List<TaskDTO>> fetchTaskRootTaskByProject(@PathVariable("project_id") BigInteger project_id,TaskSearchContext context) {
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
	public ResponseEntity<Page<TaskDTO>> searchTaskRootTaskByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchRootTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchByModule-all')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchbymodule")
	public ResponseEntity<List<TaskDTO>> fetchTaskByModuleByProject(@PathVariable("project_id") BigInteger project_id,TaskSearchContext context) {
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
	public ResponseEntity<Page<TaskDTO>> searchTaskByModuleByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTypeGroup-all')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchtypegroup")
	public ResponseEntity<List<HashMap>> fetchTaskTypeGroupByProject(@PathVariable("project_id") BigInteger project_id,TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchTypeGroup-all')")
	@ApiOperation(value = "根据项目查询任务类型分组", tags = {"任务" } ,notes = "根据项目查询任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/searchtypegroup")
	public ResponseEntity<Page<HashMap>> searchTaskTypeGroupByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<HashMap> domains = taskService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(domains.getContent(), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Task-searchDefault-all')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/fetchdefault")
	public ResponseEntity<List<TaskDTO>> fetchTaskDefaultByProject(@PathVariable("project_id") BigInteger project_id,TaskSearchContext context) {
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
	public ResponseEntity<Page<TaskDTO>> searchTaskDefaultByProject(@PathVariable("project_id") BigInteger project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

