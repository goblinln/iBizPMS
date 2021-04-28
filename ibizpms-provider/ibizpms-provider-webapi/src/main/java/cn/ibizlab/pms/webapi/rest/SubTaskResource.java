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

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'CREATE')")
    @ApiOperation(value = "GetDraftTemp", tags = {"任务" },  notes = "GetDraftTemp")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTemp() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain);
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "保存任务", tags = {"任务" },  notes = "保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/save")
    public ResponseEntity<SubTaskDTO> save(@RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存任务", tags = {"任务" },  notes = "批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SubTaskDTO> subtaskdtos) {
        taskService.saveBatch(subtaskMapping.toDomain(subtaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'UPDATE')")
    @ApiOperation(value = "UpdateTempMajor", tags = {"任务" },  notes = "UpdateTempMajor")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajor(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @ApiOperation(value = "获取任务草稿", tags = {"任务" },  notes = "获取任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraft(SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'READ')")
    @ApiOperation(value = "GetTempMajor", tags = {"任务" },  notes = "GetTempMajor")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajor(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'DELETE')")
    @ApiOperation(value = "RemoveTempMajor", tags = {"任务" },  notes = "RemoveTempMajor")
	@RequestMapping(method = RequestMethod.DELETE, value = "/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajor(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'CREATE')")
    @ApiOperation(value = "CreateTemp", tags = {"任务" },  notes = "CreateTemp")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTemp(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'UPDATE')")
    @ApiOperation(value = "UpdateTemp", tags = {"任务" },  notes = "UpdateTemp")
	@RequestMapping(method = RequestMethod.PUT, value = "/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTemp(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'DELETE')")
    @ApiOperation(value = "RemoveTemp", tags = {"任务" },  notes = "RemoveTemp")
	@RequestMapping(method = RequestMethod.DELETE, value = "/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTemp(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'CREATE')")
    @ApiOperation(value = "GetDraftTempMajor", tags = {"任务" },  notes = "GetDraftTempMajor")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajor(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'CREATE')")
    @ApiOperation(value = "CreateTempMajor", tags = {"任务" },  notes = "CreateTempMajor")
	@RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajor(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "获取子任务", tags = {"任务" } ,notes = "获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchChildTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子任务", tags = {"任务" } ,notes = "查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchChildTask(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskRuntime.test(#subtask_id,'READ')")
    @ApiOperation(value = "GetTemp", tags = {"任务" },  notes = "GetTemp")
	@RequestMapping(method = RequestMethod.GET, value = "/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTemp(@PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain);
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/{action}")
    public ResponseEntity<SubTaskDTO> dynamicCall(@PathVariable("subtask_id") Long subtask_id , @PathVariable("action") String action , @RequestBody SubTaskDTO subtaskdto) {
        Task domain = taskService.dynamicCall(subtask_id, action, subtaskMapping.toDomain(subtaskdto));
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据任务保存任务", tags = {"任务" },  notes = "根据任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

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

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据任务获取任务草稿", tags = {"任务" },  notes = "根据任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByTask(@PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取子任务", tags = {"任务" } ,notes = "根据任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询子任务", tags = {"任务" } ,notes = "根据任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务任务", tags = {"任务" },  notes = "根据任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByTask(@PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProjectModuleTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据任务模块任务保存任务", tags = {"任务" },  notes = "根据任务模块任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务模块任务批量保存任务", tags = {"任务" },  notes = "根据任务模块任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据任务模块任务获取任务草稿", tags = {"任务" },  notes = "根据任务模块任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务获取子任务", tags = {"任务" } ,notes = "根据任务模块任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务查询子任务", tags = {"任务" } ,notes = "根据任务模块任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务任务", tags = {"任务" },  notes = "根据任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProductPlanTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品计划任务保存任务", tags = {"任务" },  notes = "根据产品计划任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划任务批量保存任务", tags = {"任务" },  notes = "根据产品计划任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品计划任务获取任务草稿", tags = {"任务" },  notes = "根据产品计划任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务获取子任务", tags = {"任务" } ,notes = "根据产品计划任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务查询子任务", tags = {"任务" } ,notes = "根据产品计划任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务任务", tags = {"任务" },  notes = "根据产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByStoryTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据需求任务保存任务", tags = {"任务" },  notes = "根据需求任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

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

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据需求任务获取任务草稿", tags = {"任务" },  notes = "根据需求任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务获取子任务", tags = {"任务" } ,notes = "根据需求任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务查询子任务", tags = {"任务" } ,notes = "根据需求任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务任务", tags = {"任务" },  notes = "根据需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProjectTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据项目任务保存任务", tags = {"任务" },  notes = "根据项目任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据项目任务获取任务草稿", tags = {"任务" },  notes = "根据项目任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取子任务", tags = {"任务" } ,notes = "根据项目任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询子任务", tags = {"任务" } ,notes = "根据项目任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务任务", tags = {"任务" },  notes = "根据项目任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProductProductPlanTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品产品计划任务保存任务", tags = {"任务" },  notes = "根据产品产品计划任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划任务批量保存任务", tags = {"任务" },  notes = "根据产品产品计划任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品产品计划任务获取任务草稿", tags = {"任务" },  notes = "根据产品产品计划任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务获取子任务", tags = {"任务" } ,notes = "根据产品产品计划任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务查询子任务", tags = {"任务" } ,notes = "根据产品产品计划任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务任务", tags = {"任务" },  notes = "根据产品产品计划任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProductStoryTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品需求任务保存任务", tags = {"任务" },  notes = "根据产品需求任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

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

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据产品需求任务获取任务草稿", tags = {"任务" },  notes = "根据产品需求任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务获取子任务", tags = {"任务" } ,notes = "根据产品需求任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务查询子任务", tags = {"任务" } ,notes = "根据产品需求任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务任务", tags = {"任务" },  notes = "根据产品需求任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/getdrafttemp")
    public ResponseEntity<SubTaskDTO> getDraftTempByProjectProjectModuleTask() {
        Task domain =new Task();
        domain = taskService.getDraftTemp(domain) ;
        SubTaskDTO subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据项目任务模块任务保存任务", tags = {"任务" },  notes = "根据项目任务模块任务保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/save")
    public ResponseEntity<SubTaskDTO> saveByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        taskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务模块任务批量保存任务", tags = {"任务" },  notes = "根据项目任务模块任务批量保存任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<SubTaskDTO> subtaskdtos) {
        List<Task> domainlist=subtaskMapping.toDomain(subtaskdtos);
        for(Task domain:domainlist){
             domain.setParent(task_id);
        }
        taskService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetempmajor")
    public ResponseEntity<SubTaskDTO> updateTempMajorByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @ApiOperation(value = "根据项目任务模块任务获取任务草稿", tags = {"任务" },  notes = "根据项目任务模块任务获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/getdraft")
    public ResponseEntity<SubTaskDTO> getDraftByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, SubTaskDTO dto) {
        Task domain = subtaskMapping.toDomain(dto);
        domain.setParent(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/gettempmajor")
    public ResponseEntity<SubTaskDTO> getTempMajorByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/removetempmajor")
    public ResponseEntity<SubTaskDTO> removeTempMajorByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createtemp")
    public ResponseEntity<SubTaskDTO> createTempByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/updatetemp")
    public ResponseEntity<SubTaskDTO> updateTempByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.updateTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/removetemp")
    public ResponseEntity<SubTaskDTO> removeTempByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.removeTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/getdrafttempmajor")
    public ResponseEntity<SubTaskDTO> getDraftTempMajorByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getDraftTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/createtempmajor")
    public ResponseEntity<SubTaskDTO> createTempMajorByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.createTempMajor(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务获取子任务", tags = {"任务" } ,notes = "根据项目任务模块任务获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/fetchchildtask")
	public ResponseEntity<List<SubTaskDTO>> fetchSubTaskChildTaskByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
        List<SubTaskDTO> list = subtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务查询子任务", tags = {"任务" } ,notes = "根据项目任务模块任务查询子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/searchchildtask")
	public ResponseEntity<Page<SubTaskDTO>> searchSubTaskChildTaskByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskSearchContext context) {
        context.setN_parent_eq(task_id);
        Page<Task> domains = taskService.searchChildTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subtaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务任务", tags = {"任务" },  notes = "根据项目任务模块任务任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/subtasks/{subtask_id}/gettemp")
    public ResponseEntity<SubTaskDTO> getTempByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("subtask_id") Long subtask_id, @RequestBody SubTaskDTO subtaskdto) {
        Task domain = subtaskMapping.toDomain(subtaskdto);
        domain.setParent(task_id);
        domain.setId(subtask_id);
        domain = taskService.getTemp(domain) ;
        subtaskdto = subtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subtaskdto);
    }
}

