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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskEstimateRuntime;

@Slf4j
@Api(tags = {"任务预计" })
@RestController("StandardAPI-taskestimate")
@RequestMapping("")
public class TaskEstimateResource {

    @Autowired
    public ITaskEstimateService taskestimateService;

    @Autowired
    public TaskEstimateRuntime taskestimateRuntime;

    @Autowired
    @Lazy
    public TaskEstimateMapping taskestimateMapping;



    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据系统用户任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户任务建立任务预计", tags = {"任务预计" },  notes = "根据系统用户任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户任务批量建立任务预计", tags = {"任务预计" },  notes = "根据系统用户任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户任务删除任务预计", tags = {"任务预计" },  notes = "根据系统用户任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户任务批量删除任务预计", tags = {"任务预计" },  notes = "根据系统用户任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据系统用户任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchDefaultBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户任务获取任务预计", tags = {"任务预计" },  notes = "根据系统用户任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户任务更新任务预计", tags = {"任务预计" },  notes = "根据系统用户任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }




    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务建立任务预计", tags = {"任务预计" },  notes = "根据项目任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务批量建立任务预计", tags = {"任务预计" },  notes = "根据项目任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目任务批量删除任务预计", tags = {"任务预计" },  notes = "根据项目任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
    @ApiOperation(value = "根据项目任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务预计", tags = {"任务预计" },  notes = "根据项目任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }





    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据系统用户项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目任务建立任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目任务批量建立任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目任务删除任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目任务批量删除任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProjectTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户项目任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据系统用户项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchDefaultBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户项目任务获取任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户项目任务更新任务预计", tags = {"任务预计" },  notes = "根据系统用户项目任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

