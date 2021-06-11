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

@Slf4j
@Api(tags = {"任务预计" })
@RestController("WebApi-taskestimate")
@RequestMapping("")
public class TaskEstimateResource {

    @Autowired
    public ITaskEstimateService taskestimateService;

    @Autowired
    @Lazy
    public TaskEstimateMapping taskestimateMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "新建任务预计", tags = {"任务预计" },  notes = "新建任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates")
    public ResponseEntity<TaskEstimateDTO> create(@Validated @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "批量新建任务预计", tags = {"任务预计" },  notes = "批量新建任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        taskestimateService.createBatch(taskestimateMapping.toDomain(taskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "获取任务预计", tags = {"任务预计" },  notes = "获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> get(@PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "删除任务预计", tags = {"任务预计" },  notes = "删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("taskestimate_id") Long taskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "批量删除任务预计", tags = {"任务预计" },  notes = "批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "更新任务预计", tags = {"任务预计" },  notes = "更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> update(@PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
		TaskEstimate domain  = taskestimateMapping.toDomain(taskestimatedto);
        domain .setId(taskestimate_id);
		taskestimateService.update(domain );
		TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "批量更新任务预计", tags = {"任务预计" },  notes = "批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        taskestimateService.updateBatch(taskestimateMapping.toDomain(taskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查任务预计", tags = {"任务预计" },  notes = "检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "获取任务预计草稿", tags = {"任务预计" },  notes = "获取任务预计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraft(TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "项目经理评估", tags = {"任务预计" },  notes = "项目经理评估")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluation(@PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setId(taskestimate_id);
        domain = taskestimateService.pMEvaluation(domain);
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "批量处理[项目经理评估]", tags = {"任务预计" },  notes = "批量处理[项目经理评估]")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationBatch(@RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "保存任务预计", tags = {"任务预计" },  notes = "保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> save(@RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "批量保存任务预计", tags = {"任务预计" },  notes = "批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        taskestimateService.saveBatch(taskestimateMapping.toDomain(taskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "获取日志月", tags = {"任务预计" } ,notes = "获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchActionMonth(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "查询日志月", tags = {"任务预计" } ,notes = "查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchActionMonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "获取日志年", tags = {"任务预计" } ,notes = "获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchActionYear(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "查询日志年", tags = {"任务预计" } ,notes = "查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchActionYear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "获取所有人员", tags = {"任务预计" } ,notes = "获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchAllAccounts(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "查询所有人员", tags = {"任务预计" } ,notes = "查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchAllAccounts(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务预计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchDefault(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务预计" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchDefault(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "获取DEFAULT1", tags = {"任务预计" } ,notes = "获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchDefaults(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "查询DEFAULT1", tags = {"任务预计" } ,notes = "查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchDefaults(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "获取我管辖的所有人员", tags = {"任务预计" } ,notes = "获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchMyAccounts(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "查询我管辖的所有人员", tags = {"任务预计" } ,notes = "查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchMyAccounts(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "获取日志月（项目）", tags = {"任务预计" } ,notes = "获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchProjectActionMonth(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "查询日志月（项目）", tags = {"任务预计" } ,notes = "查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectActionMonth(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "获取日志年（项目）", tags = {"任务预计" } ,notes = "获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchProjectActionYear(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "查询日志年（项目）", tags = {"任务预计" } ,notes = "查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectActionYear(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "获取项目日志", tags = {"任务预计" } ,notes = "获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchProjectTaskEstimate(TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "查询项目日志", tags = {"任务预计" } ,notes = "查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchProjectTaskEstimate(@RequestBody TaskEstimateSearchContext context) {
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据任务建立任务预计", tags = {"任务预计" },  notes = "根据任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据任务获取任务预计", tags = {"任务预计" },  notes = "根据任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据任务删除任务预计", tags = {"任务预计" },  notes = "根据任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据任务批量删除任务预计", tags = {"任务预计" },  notes = "根据任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据任务批量更新任务预计", tags = {"任务预计" },  notes = "根据任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据任务检查任务预计", tags = {"任务预计" },  notes = "根据任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据任务任务预计", tags = {"任务预计" },  notes = "根据任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据任务保存任务预计", tags = {"任务预计" },  notes = "根据任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据任务批量保存任务预计", tags = {"任务预计" },  notes = "根据任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据任务获取日志月", tags = {"任务预计" } ,notes = "根据任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据任务查询日志月", tags = {"任务预计" } ,notes = "根据任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据任务获取日志年", tags = {"任务预计" } ,notes = "根据任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据任务查询日志年", tags = {"任务预计" } ,notes = "根据任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据任务获取所有人员", tags = {"任务预计" } ,notes = "根据任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据任务查询所有人员", tags = {"任务预计" } ,notes = "根据任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据任务获取项目日志", tags = {"任务预计" } ,notes = "根据任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByTask(@PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据任务查询项目日志", tags = {"任务预计" } ,notes = "根据任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据任务模块任务建立任务预计", tags = {"任务预计" },  notes = "根据任务模块任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据任务模块任务批量建立任务预计", tags = {"任务预计" },  notes = "根据任务模块任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据任务模块任务获取任务预计", tags = {"任务预计" },  notes = "根据任务模块任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据任务模块任务删除任务预计", tags = {"任务预计" },  notes = "根据任务模块任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据任务模块任务批量删除任务预计", tags = {"任务预计" },  notes = "根据任务模块任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProjectModuleTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据任务模块任务更新任务预计", tags = {"任务预计" },  notes = "根据任务模块任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据任务模块任务批量更新任务预计", tags = {"任务预计" },  notes = "根据任务模块任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据任务模块任务检查任务预计", tags = {"任务预计" },  notes = "根据任务模块任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据任务模块任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据任务模块任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据任务模块任务任务预计", tags = {"任务预计" },  notes = "根据任务模块任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据任务模块任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据任务模块任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据任务模块任务保存任务预计", tags = {"任务预计" },  notes = "根据任务模块任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据任务模块任务批量保存任务预计", tags = {"任务预计" },  notes = "根据任务模块任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据任务模块任务获取日志月", tags = {"任务预计" } ,notes = "根据任务模块任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据任务模块任务查询日志月", tags = {"任务预计" } ,notes = "根据任务模块任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据任务模块任务获取日志年", tags = {"任务预计" } ,notes = "根据任务模块任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据任务模块任务查询日志年", tags = {"任务预计" } ,notes = "根据任务模块任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据任务模块任务获取所有人员", tags = {"任务预计" } ,notes = "根据任务模块任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据任务模块任务查询所有人员", tags = {"任务预计" } ,notes = "根据任务模块任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据任务模块任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据任务模块任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据任务模块任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据任务模块任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据任务模块任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据任务模块任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据任务模块任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据任务模块任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据任务模块任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据任务模块任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据任务模块任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据任务模块任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据任务模块任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据任务模块任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据任务模块任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据任务模块任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据任务模块任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据任务模块任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据任务模块任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据任务模块任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据任务模块任务获取项目日志", tags = {"任务预计" } ,notes = "根据任务模块任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据任务模块任务查询项目日志", tags = {"任务预计" } ,notes = "根据任务模块任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品计划任务建立任务预计", tags = {"任务预计" },  notes = "根据产品计划任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品计划任务批量建立任务预计", tags = {"任务预计" },  notes = "根据产品计划任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据产品计划任务获取任务预计", tags = {"任务预计" },  notes = "根据产品计划任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品计划任务删除任务预计", tags = {"任务预计" },  notes = "根据产品计划任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品计划任务批量删除任务预计", tags = {"任务预计" },  notes = "根据产品计划任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlanTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品计划任务更新任务预计", tags = {"任务预计" },  notes = "根据产品计划任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品计划任务批量更新任务预计", tags = {"任务预计" },  notes = "根据产品计划任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划任务检查任务预计", tags = {"任务预计" },  notes = "根据产品计划任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据产品计划任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据产品计划任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据产品计划任务任务预计", tags = {"任务预计" },  notes = "根据产品计划任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据产品计划任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据产品计划任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品计划任务保存任务预计", tags = {"任务预计" },  notes = "根据产品计划任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品计划任务批量保存任务预计", tags = {"任务预计" },  notes = "根据产品计划任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品计划任务获取日志月", tags = {"任务预计" } ,notes = "根据产品计划任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品计划任务查询日志月", tags = {"任务预计" } ,notes = "根据产品计划任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品计划任务获取日志年", tags = {"任务预计" } ,notes = "根据产品计划任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品计划任务查询日志年", tags = {"任务预计" } ,notes = "根据产品计划任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品计划任务获取所有人员", tags = {"任务预计" } ,notes = "根据产品计划任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品计划任务查询所有人员", tags = {"任务预计" } ,notes = "根据产品计划任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品计划任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据产品计划任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品计划任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据产品计划任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品计划任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据产品计划任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品计划任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据产品计划任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品计划任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品计划任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品计划任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品计划任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品计划任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据产品计划任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品计划任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据产品计划任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品计划任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据产品计划任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品计划任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据产品计划任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品计划任务获取项目日志", tags = {"任务预计" } ,notes = "根据产品计划任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品计划任务查询项目日志", tags = {"任务预计" } ,notes = "根据产品计划任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据需求任务建立任务预计", tags = {"任务预计" },  notes = "根据需求任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据需求任务批量建立任务预计", tags = {"任务预计" },  notes = "根据需求任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据需求任务获取任务预计", tags = {"任务预计" },  notes = "根据需求任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据需求任务删除任务预计", tags = {"任务预计" },  notes = "根据需求任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据需求任务批量删除任务预计", tags = {"任务预计" },  notes = "根据需求任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByStoryTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据需求任务更新任务预计", tags = {"任务预计" },  notes = "根据需求任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据需求任务批量更新任务预计", tags = {"任务预计" },  notes = "根据需求任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求任务检查任务预计", tags = {"任务预计" },  notes = "根据需求任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据需求任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据需求任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据需求任务任务预计", tags = {"任务预计" },  notes = "根据需求任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据需求任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据需求任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据需求任务保存任务预计", tags = {"任务预计" },  notes = "根据需求任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据需求任务批量保存任务预计", tags = {"任务预计" },  notes = "根据需求任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据需求任务获取日志月", tags = {"任务预计" } ,notes = "根据需求任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据需求任务查询日志月", tags = {"任务预计" } ,notes = "根据需求任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据需求任务获取日志年", tags = {"任务预计" } ,notes = "根据需求任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据需求任务查询日志年", tags = {"任务预计" } ,notes = "根据需求任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据需求任务获取所有人员", tags = {"任务预计" } ,notes = "根据需求任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据需求任务查询所有人员", tags = {"任务预计" } ,notes = "根据需求任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据需求任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据需求任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据需求任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据需求任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据需求任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据需求任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据需求任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据需求任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据需求任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据需求任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据需求任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据需求任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据需求任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据需求任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据需求任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据需求任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据需求任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据需求任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据需求任务获取项目日志", tags = {"任务预计" } ,notes = "根据需求任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据需求任务查询项目日志", tags = {"任务预计" } ,notes = "根据需求任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据项目任务建立任务预计", tags = {"任务预计" },  notes = "根据项目任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据项目任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据项目任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据项目任务批量删除任务预计", tags = {"任务预计" },  notes = "根据项目任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据项目任务批量更新任务预计", tags = {"任务预计" },  notes = "根据项目任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目任务检查任务预计", tags = {"任务预计" },  notes = "根据项目任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据项目任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据项目任务任务预计", tags = {"任务预计" },  notes = "根据项目任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据项目任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据项目任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据项目任务保存任务预计", tags = {"任务预计" },  notes = "根据项目任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据项目任务批量保存任务预计", tags = {"任务预计" },  notes = "根据项目任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据项目任务获取日志月", tags = {"任务预计" } ,notes = "根据项目任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据项目任务查询日志月", tags = {"任务预计" } ,notes = "根据项目任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据项目任务获取日志年", tags = {"任务预计" } ,notes = "根据项目任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据项目任务查询日志年", tags = {"任务预计" } ,notes = "根据项目任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据项目任务获取所有人员", tags = {"任务预计" } ,notes = "根据项目任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据项目任务查询所有人员", tags = {"任务预计" } ,notes = "根据项目任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据项目任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据项目任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据项目任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据项目任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据项目任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据项目任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据项目任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据项目任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据项目任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据项目任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据项目任务获取项目日志", tags = {"任务预计" } ,notes = "根据项目任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据项目任务查询项目日志", tags = {"任务预计" } ,notes = "根据项目任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品产品计划任务建立任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品产品计划任务批量建立任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据产品产品计划任务获取任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品产品计划任务删除任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品产品计划任务批量删除任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlanTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品产品计划任务更新任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品产品计划任务批量更新任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划任务检查任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据产品产品计划任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据产品产品计划任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据产品产品计划任务任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据产品产品计划任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品产品计划任务保存任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品产品计划任务批量保存任务预计", tags = {"任务预计" },  notes = "根据产品产品计划任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品产品计划任务获取日志月", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品产品计划任务查询日志月", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品产品计划任务获取日志年", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品产品计划任务查询日志年", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品产品计划任务获取所有人员", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品产品计划任务查询所有人员", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品产品计划任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品产品计划任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品产品计划任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品产品计划任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品产品计划任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品产品计划任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品产品计划任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品产品计划任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品产品计划任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品产品计划任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品产品计划任务获取项目日志", tags = {"任务预计" } ,notes = "根据产品产品计划任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品产品计划任务查询项目日志", tags = {"任务预计" } ,notes = "根据产品产品计划任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品需求任务建立任务预计", tags = {"任务预计" },  notes = "根据产品需求任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据产品需求任务批量建立任务预计", tags = {"任务预计" },  notes = "根据产品需求任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据产品需求任务获取任务预计", tags = {"任务预计" },  notes = "根据产品需求任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品需求任务删除任务预计", tags = {"任务预计" },  notes = "根据产品需求任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据产品需求任务批量删除任务预计", tags = {"任务预计" },  notes = "根据产品需求任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品需求任务更新任务预计", tags = {"任务预计" },  notes = "根据产品需求任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据产品需求任务批量更新任务预计", tags = {"任务预计" },  notes = "根据产品需求任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求任务检查任务预计", tags = {"任务预计" },  notes = "根据产品需求任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据产品需求任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据产品需求任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据产品需求任务任务预计", tags = {"任务预计" },  notes = "根据产品需求任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据产品需求任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据产品需求任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品需求任务保存任务预计", tags = {"任务预计" },  notes = "根据产品需求任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据产品需求任务批量保存任务预计", tags = {"任务预计" },  notes = "根据产品需求任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品需求任务获取日志月", tags = {"任务预计" } ,notes = "根据产品需求任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据产品需求任务查询日志月", tags = {"任务预计" } ,notes = "根据产品需求任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品需求任务获取日志年", tags = {"任务预计" } ,notes = "根据产品需求任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据产品需求任务查询日志年", tags = {"任务预计" } ,notes = "根据产品需求任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品需求任务获取所有人员", tags = {"任务预计" } ,notes = "根据产品需求任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据产品需求任务查询所有人员", tags = {"任务预计" } ,notes = "根据产品需求任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品需求任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据产品需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据产品需求任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据产品需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品需求任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据产品需求任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据产品需求任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据产品需求任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品需求任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品需求任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据产品需求任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据产品需求任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品需求任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据产品需求任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据产品需求任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据产品需求任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品需求任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据产品需求任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据产品需求任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据产品需求任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品需求任务获取项目日志", tags = {"任务预计" } ,notes = "根据产品需求任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据产品需求任务查询项目日志", tags = {"任务预计" } ,notes = "根据产品需求任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据项目任务模块任务建立任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates")
    public ResponseEntity<TaskEstimateDTO> createByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
		taskestimateService.create(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Create-all')")
    @ApiOperation(value = "根据项目任务模块任务批量建立任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务批量建立任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> createBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Get-all')")
    @ApiOperation(value = "根据项目任务模块任务获取任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务获取任务预计")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> getByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
        TaskEstimate domain = taskestimateService.get(taskestimate_id);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据项目任务模块任务删除任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<Boolean> removeByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskestimateService.remove(taskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Remove-all')")
    @ApiOperation(value = "根据项目任务模块任务批量删除任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务批量删除任务预计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProjectModuleTask(@RequestBody List<Long> ids) {
        taskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据项目任务模块任务更新任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}")
    public ResponseEntity<TaskEstimateDTO> updateByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain.setId(taskestimate_id);
		taskestimateService.update(domain);
        TaskEstimateDTO dto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Update-all')")
    @ApiOperation(value = "根据项目任务模块任务批量更新任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务批量更新任务预计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/batch")
    public ResponseEntity<Boolean> updateBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
            domain.setTask(task_id);
        }
        taskestimateService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目任务模块任务检查任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务检查任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimateService.checkKey(taskestimateMapping.toDomain(taskestimatedto)));
    }

    @ApiOperation(value = "根据项目任务模块任务获取任务预计草稿", tags = {"任务预计" },  notes = "根据项目任务模块任务获取任务预计草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/getdraft")
    public ResponseEntity<TaskEstimateDTO> getDraftByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, TaskEstimateDTO dto) {
        TaskEstimate domain = taskestimateMapping.toDomain(dto);
        domain.setTask(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(taskestimateService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-PMEvaluation-all')")
    @ApiOperation(value = "根据项目任务模块任务任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/{taskestimate_id}/pmevaluation")
    public ResponseEntity<TaskEstimateDTO> pMEvaluationByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskestimate_id") Long taskestimate_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        domain = taskestimateService.pMEvaluation(domain) ;
        taskestimatedto = taskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatedto);
    }
    @ApiOperation(value = "批量处理[根据项目任务模块任务任务预计]", tags = {"任务预计" },  notes = "批量处理[根据项目任务模块任务任务预计]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/pmevaluationbatch")
    public ResponseEntity<Boolean> pMEvaluationByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domains = taskestimateMapping.toDomain(taskestimatedtos);
        boolean result = taskestimateService.pMEvaluationBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据项目任务模块任务保存任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/save")
    public ResponseEntity<TaskEstimateDTO> saveByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateDTO taskestimatedto) {
        TaskEstimate domain = taskestimateMapping.toDomain(taskestimatedto);
        domain.setTask(task_id);
        taskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-Save-all')")
    @ApiOperation(value = "根据项目任务模块任务批量保存任务预计", tags = {"任务预计" },  notes = "根据项目任务模块任务批量保存任务预计")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskEstimateDTO> taskestimatedtos) {
        List<TaskEstimate> domainlist=taskestimateMapping.toDomain(taskestimatedtos);
        for(TaskEstimate domain:domainlist){
             domain.setTask(task_id);
        }
        taskestimateService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据项目任务模块任务获取日志月", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取日志月")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionMonthByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionMonth-all')")
	@ApiOperation(value = "根据项目任务模块任务查询日志月", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询日志月")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionMonthByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据项目任务模块任务获取日志年", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取日志年")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateActionYearByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchActionYear-all')")
	@ApiOperation(value = "根据项目任务模块任务查询日志年", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询日志年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateActionYearByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据项目任务模块任务获取所有人员", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchallaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateAllAccountsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchAllAccounts-all')")
	@ApiOperation(value = "根据项目任务模块任务查询所有人员", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchallaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateAllAccountsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchAllAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据项目任务模块任务获取DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefault")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefault-all')")
	@ApiOperation(value = "根据项目任务模块任务查询DEFAULT", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefault")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据项目任务模块任务获取DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取DEFAULT1")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchdefaults")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateDefaultsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchDefaults-all')")
	@ApiOperation(value = "根据项目任务模块任务查询DEFAULT1", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchdefaults")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateDefaultsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchDefaults(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据项目任务模块任务获取我管辖的所有人员", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取我管辖的所有人员")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchmyaccounts")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateMyAccountsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchMyAccounts-all')")
	@ApiOperation(value = "根据项目任务模块任务查询我管辖的所有人员", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询我管辖的所有人员")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchmyaccounts")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateMyAccountsByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchMyAccounts(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据项目任务模块任务获取日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取日志月（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionmonth")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionMonthByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionMonth-all')")
	@ApiOperation(value = "根据项目任务模块任务查询日志月（项目）", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询日志月（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionmonth")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionMonthByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionMonth(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据项目任务模块任务获取日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取日志年（项目）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojectactionyear")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectActionYearByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectActionYear-all')")
	@ApiOperation(value = "根据项目任务模块任务查询日志年（项目）", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询日志年（项目）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojectactionyear")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectActionYearByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectActionYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据项目任务模块任务获取项目日志", tags = {"任务预计" } ,notes = "根据项目任务模块任务获取项目日志")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/fetchprojecttaskestimate")
	public ResponseEntity<List<TaskEstimateDTO>> fetchTaskEstimateProjectTaskEstimateByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
        List<TaskEstimateDTO> list = taskestimateMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-TaskEstimate-searchProjectTaskEstimate-all')")
	@ApiOperation(value = "根据项目任务模块任务查询项目日志", tags = {"任务预计" } ,notes = "根据项目任务模块任务查询项目日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskestimates/searchprojecttaskestimate")
	public ResponseEntity<Page<TaskEstimateDTO>> searchTaskEstimateProjectTaskEstimateByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskEstimateSearchContext context) {
        context.setN_task_eq(task_id);
        Page<TaskEstimate> domains = taskestimateService.searchProjectTaskEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

