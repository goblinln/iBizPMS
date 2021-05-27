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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskEstimateRuntime;

@Slf4j
@Api(tags = {"任务预计" })
@RestController("WebApi-taskestimate")
@RequestMapping("")
public class TaskEstimateResource {

    @Autowired
    public ITaskEstimateService taskestimateService;

    @Autowired
    public TaskEstimateRuntime taskestimateRuntime;

    @Autowired
    @Lazy
    public TaskEstimateMapping taskestimateMapping;

    @PreAuthorize("@TaskEstimateRuntime.test(#taskestimate_id,'READ')")
    @ApiOperation(value = "获取任务预计", tags = {"任务预计" },  notes = "获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> get(@PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        Map<String,Integer> opprivs = taskestimateRuntime.getOPPrivs(taskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.test(#taskestimate_id,'DELETE')")
    @ApiOperation(value = "删除任务预计", tags = {"任务预计" },  notes = "删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("taskestimate_id") Long taskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取任务预计草稿", tags = {"任务预计" },  notes = "获取任务预计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraft(TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务预计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchdefault(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务预计" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchDefault(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务预计", tags = {"任务预计" },  notes = "新建任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates")
    @Transactional
    public ResponseEntity<TaskEstimateDTO> create(@Validated @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
		taskestimateService.create(domain);
        if(!taskestimateRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        Map<String,Integer> opprivs = taskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskEstimateRuntime.test(#taskestimate_id,'UPDATE')")
    @ApiOperation(value = "更新任务预计", tags = {"任务预计" },  notes = "更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/taskestimates/{taskestimate_id}")
    @Transactional
    public ResponseEntity<TaskEstimateDTO> update(@PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
		TaskEstimate domain  = taskestimateMapping.toDomain(taskestimatedto);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain );
        if(!taskestimateRuntime.test(taskestimate_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        Map<String,Integer> opprivs = taskestimateRuntime.getOPPrivs(taskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查任务预计", tags = {"任务预计" },  notes = "检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@TaskEstimateRuntime.test(#taskestimate_id,'MANAGE')")
    @ApiOperation(value = "项目经理评估", tags = {"任务预计" },  notes = "项目经理评估")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluation(@PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setId(taskestimate_id);
        domain = taskestimateService.pMEvaluation(domain);
        taskestimatedto = taskestimateMapping.toDto(domain);
        Map<String,Integer> opprivs = taskestimateRuntime.getOPPrivs(domain.getId());
        taskestimatedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }


    @ApiOperation(value = "保存任务预计", tags = {"任务预计" },  notes = "保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> save(@RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        taskestimateService.save(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        Map<String,Integer> opprivs = taskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取日志月", tags = {"任务预计" } ,notes = "获取日志月")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchactionmonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询日志月", tags = {"任务预计" } ,notes = "查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchActionMonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取日志年", tags = {"任务预计" } ,notes = "获取日志年")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchactionyear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询日志年", tags = {"任务预计" } ,notes = "查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchActionYear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT1", tags = {"任务预计" } ,notes = "获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchdefaults(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT1", tags = {"任务预计" } ,notes = "查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchDefaults(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取日志月（项目）", tags = {"任务预计" } ,notes = "获取日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchprojectactionmonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询日志月（项目）", tags = {"任务预计" } ,notes = "查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectActionMonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取日志年（项目）", tags = {"任务预计" } ,notes = "获取日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchprojectactionyear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询日志年（项目）", tags = {"任务预计" } ,notes = "查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectActionYear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目日志", tags = {"任务预计" } ,notes = "获取项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchprojecttaskestimate(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskEstimateRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目日志", tags = {"任务预计" } ,notes = "查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectTaskEstimate(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/taskestimates/{taskestimate_id}/{action}")
    public ResponseEntity<TaskEstimateDTO> dynamicCall(@PathVariable("taskestimate_id") Long taskestimate_id , @PathVariable("action") String action , @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateService.dynamicCall(taskestimate_id, action, taskestimateMapping.toDomain(taskestimatedto));
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取任务预计", tags = {"任务预计" },  notes = "根据任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务删除任务预计", tags = {"任务预计" },  notes = "根据任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务建立任务预计", tags = {"任务预计" },  notes = "根据任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新任务预计", tags = {"任务预计" },  notes = "根据任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务检查任务预计", tags = {"任务预计" },  notes = "根据任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'MANAGE')")
    @ApiOperation(value = "根据任务项目经理评估任务预计", tags = {"任务预计" },  notes = "根据任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据任务保存任务预计", tags = {"任务预计" },  notes = "根据任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取日志月", tags = {"任务预计" } ,notes = "根据任务获取日志月")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询日志月", tags = {"任务预计" } ,notes = "根据任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取日志年", tags = {"任务预计" } ,notes = "根据任务获取日志年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询日志年", tags = {"任务预计" } ,notes = "根据任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取项目日志", tags = {"任务预计" } ,notes = "根据任务获取项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询项目日志", tags = {"任务预计" } ,notes = "根据任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立任务预计", tags = {"任务预计" },  notes = "根据项目任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务检查任务预计", tags = {"任务预计" },  notes = "根据项目任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目任务项目经理评估任务预计", tags = {"任务预计" },  notes = "根据项目任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目任务保存任务预计", tags = {"任务预计" },  notes = "根据项目任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取日志月", tags = {"任务预计" } ,notes = "根据项目任务获取日志月")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询日志月", tags = {"任务预计" } ,notes = "根据项目任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取日志年", tags = {"任务预计" } ,notes = "根据项目任务获取日志年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询日志年", tags = {"任务预计" } ,notes = "根据项目任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取项目日志", tags = {"任务预计" } ,notes = "根据项目任务获取项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询项目日志", tags = {"任务预计" } ,notes = "根据项目任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

