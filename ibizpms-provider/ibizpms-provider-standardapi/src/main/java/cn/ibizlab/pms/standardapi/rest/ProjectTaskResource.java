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
@RestController("StandardAPI-projecttask")
@RequestMapping("")
public class ProjectTaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public ProjectTaskMapping projecttaskMapping;

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目激活任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/activate")
    public ResponseEntity<ProjectTaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.activate(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务", tags = {"任务" },  notes = "根据项目获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttasks/{projecttask_id}")
    public ResponseEntity<ProjectTaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id) {
        Task domain = taskService.get(projecttask_id);
        ProjectTaskDTO dto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目指派/转交任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/assignto")
    public ResponseEntity<ProjectTaskDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.assignTo(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目建立任务", tags = {"任务" },  notes = "根据项目建立任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks")
    public ResponseEntity<ProjectTaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
		taskService.create(domain);
        ProjectTaskDTO dto = projecttaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "根据项目需求变更确认任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/confirmstorychange")
    public ResponseEntity<ProjectTaskDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.confirmStoryChange(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目获取任务草稿", tags = {"任务" },  notes = "根据项目获取任务草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttasks/getdraft")
    public ResponseEntity<ProjectTaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTaskDTO dto) {
        Task domain = projecttaskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskMapping.toDto(taskService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/fetchcurprojecttaskquery")
	public ResponseEntity<List<ProjectTaskDTO>> fetchProjectTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
        List<ProjectTaskDTO> list = projecttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询当前项目任务", tags = {"任务" } ,notes = "根据项目查询当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/searchcurprojecttaskquery")
	public ResponseEntity<Page<ProjectTaskDTO>> searchProjectTaskCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchCurProjectTaskQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目完成任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/finish")
    public ResponseEntity<ProjectTaskDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.finish(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目继续任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/restart")
    public ResponseEntity<ProjectTaskDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.restart(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目暂停任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/pause")
    public ResponseEntity<ProjectTaskDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.pause(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/fetchdefault")
	public ResponseEntity<List<ProjectTaskDTO>> fetchProjectTaskDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<ProjectTaskDTO> list = projecttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttasks/searchdefault")
	public ResponseEntity<Page<ProjectTaskDTO>> searchProjectTaskDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @VersionCheck(entity = "task" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目更新任务", tags = {"任务" },  notes = "根据项目更新任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttasks/{projecttask_id}")
    public ResponseEntity<ProjectTaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
		taskService.update(domain);
        ProjectTaskDTO dto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目删除任务", tags = {"任务" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttasks/{projecttask_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.remove(projecttask_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目取消任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/cancel")
    public ResponseEntity<ProjectTaskDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.cancel(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目关闭任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/close")
    public ResponseEntity<ProjectTaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.close(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'TASKMANAGE')")
    @ApiOperation(value = "根据项目开始任务", tags = {"任务" },  notes = "根据项目任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/{projecttask_id}/start")
    public ResponseEntity<ProjectTaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttask_id") Long projecttask_id, @RequestBody ProjectTaskDTO projecttaskdto) {
        Task domain = projecttaskMapping.toDomain(projecttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttask_id);
        domain = taskService.start(domain) ;
        projecttaskdto = projecttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskdto);
    }

    @ApiOperation(value = "批量处理[根据项目任务]", tags = {"任务" },  notes = "批量处理[根据项目任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttasks/startbatch")
    public ResponseEntity<Boolean> startByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTaskDTO> projecttaskdtos) {
        List<Task> domains = projecttaskMapping.toDomain(projecttaskdtos);
        boolean result = taskService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

