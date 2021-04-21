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
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.ibiz.filter.TaskTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TaskTeamRuntime;

@Slf4j
@Api(tags = {"任务团队" })
@RestController("WebApi-taskteam")
@RequestMapping("")
public class TaskTeamResource {

    @Autowired
    public ITaskTeamService taskteamService;

    @Autowired
    public TaskTeamRuntime taskteamRuntime;

    @Autowired
    @Lazy
    public TaskTeamMapping taskteamMapping;

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据任务建立任务团队", tags = {"任务团队" },  notes = "根据任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据任务批量建立任务团队", tags = {"任务团队" },  notes = "根据任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新任务团队", tags = {"任务团队" },  notes = "根据任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据任务批量更新任务团队", tags = {"任务团队" },  notes = "根据任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据任务删除任务团队", tags = {"任务团队" },  notes = "根据任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据任务批量删除任务团队", tags = {"任务团队" },  notes = "根据任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据任务获取任务团队", tags = {"任务团队" },  notes = "根据任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByTask(@PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务检查任务团队", tags = {"任务团队" },  notes = "根据任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据任务保存任务团队", tags = {"任务团队" },  notes = "根据任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务批量保存任务团队", tags = {"任务团队" },  notes = "根据任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据任务模块任务建立任务团队", tags = {"任务团队" },  notes = "根据任务模块任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据任务模块任务批量建立任务团队", tags = {"任务团队" },  notes = "根据任务模块任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务更新任务团队", tags = {"任务团队" },  notes = "根据任务模块任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据任务模块任务批量更新任务团队", tags = {"任务团队" },  notes = "根据任务模块任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据任务模块任务删除任务团队", tags = {"任务团队" },  notes = "根据任务模块任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据任务模块任务批量删除任务团队", tags = {"任务团队" },  notes = "根据任务模块任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProjectModuleTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据任务模块任务获取任务团队", tags = {"任务团队" },  notes = "根据任务模块任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务模块任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据任务模块任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务模块任务检查任务团队", tags = {"任务团队" },  notes = "根据任务模块任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据任务模块任务保存任务团队", tags = {"任务团队" },  notes = "根据任务模块任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务模块任务批量保存任务团队", tags = {"任务团队" },  notes = "根据任务模块任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据任务模块任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据任务模块任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据任务模块任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据任务模块任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划任务建立任务团队", tags = {"任务团队" },  notes = "根据产品计划任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划任务批量建立任务团队", tags = {"任务团队" },  notes = "根据产品计划任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务更新任务团队", tags = {"任务团队" },  notes = "根据产品计划任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品计划任务批量更新任务团队", tags = {"任务团队" },  notes = "根据产品计划任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据产品计划任务删除任务团队", tags = {"任务团队" },  notes = "根据产品计划任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品计划任务批量删除任务团队", tags = {"任务团队" },  notes = "根据产品计划任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlanTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据产品计划任务获取任务团队", tags = {"任务团队" },  notes = "根据产品计划任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据产品计划任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划任务检查任务团队", tags = {"任务团队" },  notes = "根据产品计划任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据产品计划任务保存任务团队", tags = {"任务团队" },  notes = "根据产品计划任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划任务批量保存任务团队", tags = {"任务团队" },  notes = "根据产品计划任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据产品计划任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据产品计划任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据需求任务建立任务团队", tags = {"任务团队" },  notes = "根据需求任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据需求任务批量建立任务团队", tags = {"任务团队" },  notes = "根据需求任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务更新任务团队", tags = {"任务团队" },  notes = "根据需求任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据需求任务批量更新任务团队", tags = {"任务团队" },  notes = "根据需求任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据需求任务删除任务团队", tags = {"任务团队" },  notes = "根据需求任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据需求任务批量删除任务团队", tags = {"任务团队" },  notes = "根据需求任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByStoryTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据需求任务获取任务团队", tags = {"任务团队" },  notes = "根据需求任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据需求任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求任务检查任务团队", tags = {"任务团队" },  notes = "根据需求任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据需求任务保存任务团队", tags = {"任务团队" },  notes = "根据需求任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求任务批量保存任务团队", tags = {"任务团队" },  notes = "根据需求任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据需求任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据需求任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务建立任务团队", tags = {"任务团队" },  notes = "根据项目任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务批量建立任务团队", tags = {"任务团队" },  notes = "根据项目任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务团队", tags = {"任务团队" },  notes = "根据项目任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据项目任务批量更新任务团队", tags = {"任务团队" },  notes = "根据项目任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务团队", tags = {"任务团队" },  notes = "根据项目任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目任务批量删除任务团队", tags = {"任务团队" },  notes = "根据项目任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务团队", tags = {"任务团队" },  notes = "根据项目任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据项目任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目任务检查任务团队", tags = {"任务团队" },  notes = "根据项目任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据项目任务保存任务团队", tags = {"任务团队" },  notes = "根据项目任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务批量保存任务团队", tags = {"任务团队" },  notes = "根据项目任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品产品计划任务建立任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品产品计划任务批量建立任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务更新任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务批量更新任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划任务删除任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品产品计划任务批量删除任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlanTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务获取任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据产品产品计划任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划任务检查任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据产品产品计划任务保存任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划任务批量保存任务团队", tags = {"任务团队" },  notes = "根据产品产品计划任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品产品计划任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据产品产品计划任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品产品计划任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据产品产品计划任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品需求任务建立任务团队", tags = {"任务团队" },  notes = "根据产品需求任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品需求任务批量建立任务团队", tags = {"任务团队" },  notes = "根据产品需求任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务更新任务团队", tags = {"任务团队" },  notes = "根据产品需求任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品需求任务批量更新任务团队", tags = {"任务团队" },  notes = "根据产品需求任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据产品需求任务删除任务团队", tags = {"任务团队" },  notes = "根据产品需求任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品需求任务批量删除任务团队", tags = {"任务团队" },  notes = "根据产品需求任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据产品需求任务获取任务团队", tags = {"任务团队" },  notes = "根据产品需求任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据产品需求任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求任务检查任务团队", tags = {"任务团队" },  notes = "根据产品需求任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据产品需求任务保存任务团队", tags = {"任务团队" },  notes = "根据产品需求任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求任务批量保存任务团队", tags = {"任务团队" },  notes = "根据产品需求任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品需求任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据产品需求任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品需求任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据产品需求任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务模块任务建立任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams")
    public ResponseEntity<TaskTeamDTO> createByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
		taskteamService.create(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目任务模块任务批量建立任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务批量建立任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> createBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务更新任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> updateByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        domain.setId(taskteam_id);
		taskteamService.update(domain);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务批量更新任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务批量更新任务团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> updateBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
            domain.setRoot(task_id);
        }
        taskteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块任务删除任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<Boolean> removeByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskteamService.remove(taskteam_id));
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目任务模块任务批量删除任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务批量删除任务团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProjectModuleTask(@RequestBody List<Long> ids) {
        taskteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.test(#taskteam_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务获取任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务获取任务团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/{taskteam_id}")
    public ResponseEntity<TaskTeamDTO> getByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("taskteam_id") Long taskteam_id) {
        TaskTeam domain = taskteamService.get(taskteam_id);
        TaskTeamDTO dto = taskteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务模块任务获取任务团队草稿", tags = {"任务团队" },  notes = "根据项目任务模块任务获取任务团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/getdraft")
    public ResponseEntity<TaskTeamDTO> getDraftByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, TaskTeamDTO dto) {
        TaskTeam domain = taskteamMapping.toDomain(dto);
        domain.setRoot(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(taskteamService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目任务模块任务检查任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务检查任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskteamService.checkKey(taskteamMapping.toDomain(taskteamdto)));
    }

    @ApiOperation(value = "根据项目任务模块任务保存任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/save")
    public ResponseEntity<TaskTeamDTO> saveByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamDTO taskteamdto) {
        TaskTeam domain = taskteamMapping.toDomain(taskteamdto);
        domain.setRoot(task_id);
        taskteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskteamMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务模块任务批量保存任务团队", tags = {"任务团队" },  notes = "根据项目任务模块任务批量保存任务团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<TaskTeamDTO> taskteamdtos) {
        List<TaskTeam> domainlist=taskteamMapping.toDomain(taskteamdtos);
        for(TaskTeam domain:domainlist){
             domain.setRoot(task_id);
        }
        taskteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目任务模块任务获取DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务模块任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/fetchdefault")
	public ResponseEntity<List<TaskTeamDTO>> fetchTaskTeamDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
        List<TaskTeamDTO> list = taskteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目任务模块任务查询DEFAULT", tags = {"任务团队" } ,notes = "根据项目任务模块任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/taskteams/searchdefault")
	public ResponseEntity<Page<TaskTeamDTO>> searchTaskTeamDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody TaskTeamSearchContext context) {
        context.setN_root_eq(task_id);
        taskteamRuntime.addAuthorityConditions(context,"READ");
        Page<TaskTeam> domains = taskteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(taskteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

