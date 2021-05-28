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


    @PreAuthorize("@TaskEstimateRuntime.test(#taskestimate_id,'DELETE')")
    @ApiOperation(value = "删除任务预计", tags = {"任务预计" },  notes = "删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("taskestimate_id") Long taskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


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

    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建任务预计", tags = {"任务预计" },  notes = "批量新建任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        taskestimateService.createBatch(taskestimateMapping.toDomain(taskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @PreAuthorize("@TaskEstimateRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取任务预计草稿", tags = {"任务预计" },  notes = "获取任务预计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraft(TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/taskestimates/{taskestimate_id}/{action}")
    public ResponseEntity<TaskEstimateDTO> dynamicCall(@PathVariable("taskestimate_id") Long taskestimate_id , @PathVariable("action") String action , @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateService.dynamicCall(taskestimate_id, action, taskestimateMapping.toDomain(taskestimatedto));
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
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


    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务删除任务预计", tags = {"任务预计" },  notes = "根据任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取任务预计", tags = {"任务预计" },  notes = "根据任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务批量建立任务预计", tags = {"任务预计" },  notes = "根据任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务检查任务预计", tags = {"任务预计" },  notes = "根据任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'MANAGE')")
    @ApiOperation(value = "根据任务项目经理评估", tags = {"任务预计" },  notes = "根据任务项目经理评估")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立任务预计", tags = {"任务预计" },  notes = "根据项目建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立任务预计", tags = {"任务预计" },  notes = "根据项目批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新任务预计", tags = {"任务预计" },  notes = "根据项目更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除任务预计", tags = {"任务预计" },  notes = "根据项目删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取任务预计", tags = {"任务预计" },  notes = "根据项目获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"任务预计" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/activate")
    public ResponseEntity<TaskEstimateDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.activate(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"任务预计" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/assignto")
    public ResponseEntity<TaskEstimateDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.assignTo(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CANCEL')")
    @ApiOperation(value = "根据项目取消", tags = {"任务预计" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/cancel")
    public ResponseEntity<TaskEstimateDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.cancel(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查任务预计", tags = {"任务预计" },  notes = "根据项目检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"任务预计" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/close")
    public ResponseEntity<TaskEstimateDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.close(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目计算开始时间和完成时间", tags = {"任务预计" },  notes = "根据项目计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/computebeginandend")
    public ResponseEntity<TaskEstimateDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.computeBeginAndEnd(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目更新父任务时间", tags = {"任务预计" },  notes = "根据项目更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/computehours4multiple")
    public ResponseEntity<TaskEstimateDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.computeHours4Multiple(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目更新工作时间", tags = {"任务预计" },  notes = "根据项目更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/computeworkinghours")
    public ResponseEntity<TaskEstimateDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.computeWorkingHours(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目需求变更确认", tags = {"任务预计" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/confirmstorychange")
    public ResponseEntity<TaskEstimateDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.confirmStoryChange(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务预计" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/createbycycle")
    public ResponseEntity<TaskEstimateDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.createByCycle(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"任务预计" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/createcycletasks")
    public ResponseEntity<TaskEstimateDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.createCycleTasks(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目删除任务", tags = {"任务预计" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/delete")
    public ResponseEntity<TaskEstimateDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.delete(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目删除工时", tags = {"任务预计" },  notes = "根据项目删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/deleteestimate")
    public ResponseEntity<TaskEstimateDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.deleteEstimate(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目编辑工时", tags = {"任务预计" },  notes = "根据项目编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/editestimate")
    public ResponseEntity<TaskEstimateDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.editEstimate(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'FINISH')")
    @ApiOperation(value = "根据项目完成", tags = {"任务预计" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/finish")
    public ResponseEntity<TaskEstimateDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.finish(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)", tags = {"任务预计" },  notes = "根据项目获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/getnextteamuser")
    public ResponseEntity<TaskEstimateDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.getNextTeamUser(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）", tags = {"任务预计" },  notes = "根据项目获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/getteamuserleftactivity")
    public ResponseEntity<TaskEstimateDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.getTeamUserLeftActivity(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）", tags = {"任务预计" },  notes = "根据项目获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/getteamuserleftstart")
    public ResponseEntity<TaskEstimateDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.getTeamUserLeftStart(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员", tags = {"任务预计" },  notes = "根据项目获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/getusernames")
    public ResponseEntity<TaskEstimateDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.getUsernames(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目关联计划", tags = {"任务预计" },  notes = "根据项目关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/linkplan")
    public ResponseEntity<TaskEstimateDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.linkPlan(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目其他更新", tags = {"任务预计" },  notes = "根据项目其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/otherupdate")
    public ResponseEntity<TaskEstimateDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.otherUpdate(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'PAUSE')")
    @ApiOperation(value = "根据项目暂停", tags = {"任务预计" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/pause")
    public ResponseEntity<TaskEstimateDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.pause(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RECORDESTIMATE')")
    @ApiOperation(value = "根据项目工时录入", tags = {"任务预计" },  notes = "根据项目工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/recordestimate")
    public ResponseEntity<TaskEstimateDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.recordEstimate(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中", tags = {"任务预计" },  notes = "根据项目继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<TaskEstimateDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.recordTimZeroLeftAfterContinue(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目预计剩余为0进行中", tags = {"任务预计" },  notes = "根据项目预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/recordtimatezeroleft")
    public ResponseEntity<TaskEstimateDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.recordTimateZeroLeft(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中", tags = {"任务预计" },  notes = "根据项目开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<TaskEstimateDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.recordTimateZeroLeftAfterStart(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RESTART')")
    @ApiOperation(value = "根据项目继续", tags = {"任务预计" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/restart")
    public ResponseEntity<TaskEstimateDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.restart(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目保存任务预计", tags = {"任务预计" },  notes = "根据项目保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量保存任务预计", tags = {"任务预计" },  notes = "根据项目批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目行为", tags = {"任务预计" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/sendmessage")
    public ResponseEntity<TaskEstimateDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.sendMessage(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"任务预计" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/sendmsgpreprocess")
    public ResponseEntity<TaskEstimateDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.sendMsgPreProcess(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "根据项目开始", tags = {"任务预计" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/start")
    public ResponseEntity<TaskEstimateDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.start(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"任务预计" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/taskfavorites")
    public ResponseEntity<TaskEstimateDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.taskFavorites(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目检查多人任务操作权限", tags = {"任务预计" },  notes = "根据项目检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/taskforward")
    public ResponseEntity<TaskEstimateDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.taskForward(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"任务预计" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/tasknfavorites")
    public ResponseEntity<TaskEstimateDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.taskNFavorites(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务状态", tags = {"任务预计" },  notes = "根据项目更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/updateparentstatus")
    public ResponseEntity<TaskEstimateDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.updateParentStatus(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务计划状态", tags = {"任务预计" },  notes = "根据项目更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/updaterelatedplanstatus")
    public ResponseEntity<TaskEstimateDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.updateRelatedPlanStatus(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"任务预计" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/taskestimates/{taskestimate_id}/updatestoryversion")
    public ResponseEntity<TaskEstimateDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        
        domain.setId(taskestimate_id);
        domain = taskestimateService.updateStoryVersion(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"任务预计" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchassignedtomytask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchAssignedToMyTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"任务预计" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchassignedtomytaskpc")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchAssignedToMyTaskPc(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"任务预计" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchbugtask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchBugTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"任务预计" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchbymodule")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchByModule(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"任务预计" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchchilddefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchChildDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"任务预计" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchchilddefaultmore")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchChildDefaultMore(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"任务预计" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchchildtask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchChildTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"任务预计" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchchildtasktree")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchChildTaskTree(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"任务预计" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchcurfinishtask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchCurFinishTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"任务预计" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchcurprojecttaskquery")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchCurProjectTaskQuery(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"任务预计" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchdefaultrow")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchDefaultRow(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"任务预计" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchesbulk")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchESBulk(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"任务预计" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmyagenttask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyAgentTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"任务预计" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmyalltask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyAllTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务预计" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmycompletetask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyCompleteTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"任务预计" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyCompleteTaskMobDaily(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"任务预计" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyCompleteTaskMobMonthly(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"任务预计" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyCompleteTaskMonthlyZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"任务预计" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmycompletetaskzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyCompleteTaskZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"任务预计" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmyfavorites")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyFavorites(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"任务预计" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyPlansTaskMobMonthly(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务预计" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmytomorrowplantask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyTomorrowPlanTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"任务预计" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"任务预计" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchNextWeekCompleteTaskMobZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务预计" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchNextWeekCompleteTaskZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"任务预计" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchnextweekplancompletetask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchNextWeekPlanCompleteTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"任务预计" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchplantask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimatePlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchPlanTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"任务预计" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchprojectapptask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchProjectAppTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"任务预计" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchprojecttask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchProjectTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"任务预计" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchroottask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchRootTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"任务预计" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchtasklinkplan")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchTaskLinkPlan(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"任务预计" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchThisMonthCompleteTaskChoice(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务预计" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchthisweekcompletetask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchThisWeekCompleteTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"任务预计" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchThisWeekCompleteTaskChoice(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"任务预计" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchThisWeekCompleteTaskMobZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"任务预计" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchThisWeekCompleteTaskZS(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"任务预计" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchtodolisttask")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<TaskEstimate> domains = taskestimateService.searchTodoListTask(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"任务预计" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchTaskEstimateTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<Map> domains = taskestimateService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"任务预计" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/taskestimates/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchTaskEstimateTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskEstimateSearchContext context) {
        
        Page<Map> domains = taskestimateService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务检查任务预计", tags = {"任务预计" },  notes = "根据项目任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目任务项目经理评估", tags = {"任务预计" },  notes = "根据项目任务项目经理评估")
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
}

