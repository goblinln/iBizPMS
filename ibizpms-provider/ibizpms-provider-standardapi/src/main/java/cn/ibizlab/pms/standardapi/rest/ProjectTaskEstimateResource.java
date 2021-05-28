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
@RestController("StandardAPI-projecttaskestimate")
@RequestMapping("")
public class ProjectTaskEstimateResource {

    @Autowired
    public ITaskEstimateService taskestimateService;

    @Autowired
    public TaskEstimateRuntime taskestimateRuntime;

    @Autowired
    @Lazy
    public ProjectTaskEstimateMapping projecttaskestimateMapping;


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CANCEL')")
    @ApiOperation(value = "根据项目取消任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/cancel")
    public ResponseEntity<ProjectTaskEstimateDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.cancel(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'FINISH')")
    @ApiOperation(value = "根据项目完成任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/finish")
    public ResponseEntity<ProjectTaskEstimateDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.finish(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttaskestimates/fetchdefault")
	public ResponseEntity<List<ProjectTaskEstimateDTO>> fetchProjectTaskEstimateDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<ProjectTaskEstimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务预计" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttaskestimates/searchdefault")
	public ResponseEntity<Page<ProjectTaskEstimateDTO>> searchProjectTaskEstimateDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/assignto")
    public ResponseEntity<ProjectTaskEstimateDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.assignTo(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目关闭任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/close")
    public ResponseEntity<ProjectTaskEstimateDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.close(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务预计" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttaskestimates/fetchcurprojecttaskquery")
	public ResponseEntity<List<ProjectTaskEstimateDTO>> fetchProjectTaskEstimateCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchCurProjectTaskQuery(context) ;
        List<ProjectTaskEstimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询当前项目任务", tags = {"任务预计" } ,notes = "根据项目查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttaskestimates/searchcurprojecttaskquery")
	public ResponseEntity<Page<ProjectTaskEstimateDTO>> searchProjectTaskEstimateCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "根据项目开始任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/start")
    public ResponseEntity<ProjectTaskEstimateDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.start(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RESTART')")
    @ApiOperation(value = "根据项目继续任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/restart")
    public ResponseEntity<ProjectTaskEstimateDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.restart(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新任务预计", tags = {"任务预计" },  notes = "根据项目更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskEstimateDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
		taskestimateService.update(domain);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "根据项目激活任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/activate")
    public ResponseEntity<ProjectTaskEstimateDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.activate(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除任务预计", tags = {"任务预计" },  notes = "根据项目删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(projecttaskestimate_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立任务预计", tags = {"任务预计" },  notes = "根据项目建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates")
    public ResponseEntity<ProjectTaskEstimateDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
		taskestimateService.create(domain);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立任务预计", tags = {"任务预计" },  notes = "根据项目批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTaskEstimateDTO> projecttaskestimatedtos) {
        List<TaskEstimate> domainlist=projecttaskestimateMapping.toDomain(projecttaskestimatedtos);
        for(TaskEstimate domain:domainlist){
            
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务预计", tags = {"任务预计" },  notes = "根据项目获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskEstimateDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id) {
        TaskEstimate domain = taskestimateService.get(projecttaskestimate_id);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttaskestimates/getdraft")
    public ResponseEntity<ProjectTaskEstimateDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTaskEstimateDTO dto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目需求变更确认任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/confirmstorychange")
    public ResponseEntity<ProjectTaskEstimateDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.confirmStoryChange(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'PAUSE')")
    @ApiOperation(value = "根据项目暂停任务预计", tags = {"任务预计" },  notes = "根据项目任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskestimates/{projecttaskestimate_id}/pause")
    public ResponseEntity<ProjectTaskEstimateDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        
        domain.setId(projecttaskestimate_id);
        domain = taskestimateService.pause(domain) ;
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskEstimateDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id) {
        TaskEstimate domain = taskestimateService.get(projecttaskestimate_id);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立任务预计", tags = {"任务预计" },  notes = "根据项目任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates")
    public ResponseEntity<ProjectTaskEstimateDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务批量建立任务预计", tags = {"任务预计" },  notes = "根据项目任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<ProjectTaskEstimateDTO> projecttaskestimatedtos) {
        List<TaskEstimate> domainlist=projecttaskestimateMapping.toDomain(projecttaskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/getdraft")
    public ResponseEntity<ProjectTaskEstimateDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ProjectTaskEstimateDTO dto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/fetchdefault")
	public ResponseEntity<List<ProjectTaskEstimateDTO>> fetchProjectTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<ProjectTaskEstimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/searchdefault")
	public ResponseEntity<Page<ProjectTaskEstimateDTO>> searchProjectTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务预计", tags = {"任务预计" },  notes = "根据项目任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskEstimateDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id, @RequestBody ProjectTaskEstimateDTO projecttaskestimatedto) {
        TaskEstimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        domain.setTask(task_id);
        domain.setId(projecttaskestimate_id);
		taskestimateService.update(domain);
        ProjectTaskEstimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttasks/{task_id}/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("projecttaskestimate_id") Long projecttaskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(projecttaskestimate_id));
    }


}

