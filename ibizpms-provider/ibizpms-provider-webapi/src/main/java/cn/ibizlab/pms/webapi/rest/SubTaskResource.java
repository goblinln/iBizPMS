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


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/subtasks/{subtask_id}/{action}")
    public ResponseEntity<SubTaskDTO> dynamicCall(@PathVariable("subtask_id") Long subtask_id , @PathVariable("action") String action , @RequestBody SubTaskDTO subtaskdto) {
        Task domain = taskService.dynamicCall(subtask_id, action, subtaskMapping.toDomain(subtaskdto));
        subtaskdto = subtaskMapping.toDto(domain);
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
}

