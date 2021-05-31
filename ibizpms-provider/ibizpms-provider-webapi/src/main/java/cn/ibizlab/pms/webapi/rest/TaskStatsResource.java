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
import cn.ibizlab.pms.core.ibiz.domain.TaskStats;
import cn.ibizlab.pms.core.ibiz.service.ITaskStatsService;
import cn.ibizlab.pms.core.ibiz.filter.TaskStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TaskStatsRuntime;

@Slf4j
@Api(tags = {"任务统计" })
@RestController("WebApi-taskstats")
@RequestMapping("")
public class TaskStatsResource {

    @Autowired
    public ITaskStatsService taskstatsService;

    @Autowired
    public TaskStatsRuntime taskstatsRuntime;

    @Autowired
    @Lazy
    public TaskStatsMapping taskstatsMapping;

    @PreAuthorize("@TaskStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务统计", tags = {"任务统计" },  notes = "新建任务统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskstats")
    @Transactional
    public ResponseEntity<TaskStatsDTO> create(@Validated @RequestBody TaskStatsDTO taskstatsdto) {
        TaskStats domain = taskstatsMapping.toDomain(taskstatsdto);
		taskstatsService.create(domain);
        if(!taskstatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TaskStatsDTO dto = taskstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = taskstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskStatsRuntime.test(#taskstats_id, 'UPDATE')")
    @ApiOperation(value = "更新任务统计", tags = {"任务统计" },  notes = "更新任务统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/taskstats/{taskstats_id}")
    @Transactional
    public ResponseEntity<TaskStatsDTO> update(@PathVariable("taskstats_id") Long taskstats_id, @RequestBody TaskStatsDTO taskstatsdto) {
		TaskStats domain  = taskstatsMapping.toDomain(taskstatsdto);
        domain.setId(taskstats_id);
		taskstatsService.update(domain );
        if(!taskstatsRuntime.test(taskstats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TaskStatsDTO dto = taskstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = taskstatsRuntime.getOPPrivs(taskstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskStatsRuntime.test(#taskstats_id, 'DELETE')")
    @ApiOperation(value = "删除任务统计", tags = {"任务统计" },  notes = "删除任务统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskstats/{taskstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("taskstats_id") Long taskstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskstatsService.remove(taskstats_id));
    }

    @PreAuthorize("@TaskStatsRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除任务统计", tags = {"任务统计" },  notes = "批量删除任务统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskStatsRuntime.test(#taskstats_id, 'READ')")
    @ApiOperation(value = "获取任务统计", tags = {"任务统计" },  notes = "获取任务统计")
	@RequestMapping(method = RequestMethod.GET, value = "/taskstats/{taskstats_id}")
    public ResponseEntity<TaskStatsDTO> get(@PathVariable("taskstats_id") Long taskstats_id) {
        TaskStats domain = taskstatsService.get(taskstats_id);
        TaskStatsDTO dto = taskstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = taskstatsRuntime.getOPPrivs(taskstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取任务统计草稿", tags = {"任务统计" },  notes = "获取任务统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/taskstats/getdraft")
    public ResponseEntity<TaskStatsDTO> getDraft(TaskStatsDTO dto) {
        TaskStats domain = taskstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskstatsMapping.toDto(taskstatsService.getDraft(domain)));
    }

    @PreAuthorize("@TaskStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查任务统计", tags = {"任务统计" },  notes = "检查任务统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TaskStatsDTO taskstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskstatsService.checkKey(taskstatsMapping.toDomain(taskstatsdto)));
    }

    @PreAuthorize("@TaskStatsRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存任务统计", tags = {"任务统计" },  notes = "保存任务统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskstats/save")
    public ResponseEntity<TaskStatsDTO> save(@RequestBody TaskStatsDTO taskstatsdto) {
        TaskStats domain = taskstatsMapping.toDomain(taskstatsdto);
        taskstatsService.save(domain);
        TaskStatsDTO dto = taskstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = taskstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskStatsRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取数据集", tags = {"任务统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/taskstats/fetchdefault")
	public ResponseEntity<List<TaskStatsDTO>> fetchdefault(@RequestBody TaskStatsSearchContext context) {
        Page<TaskStats> domains = taskstatsService.searchDefault(context) ;
        List<TaskStatsDTO> list = taskstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskStatsRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取任务完成汇总表", tags = {"任务统计" } ,notes = "获取任务完成汇总表")
    @RequestMapping(method= RequestMethod.POST , value="/taskstats/fetchtaskfinishhuizong")
	public ResponseEntity<List<TaskStatsDTO>> fetchtaskfinishhuizong(@RequestBody TaskStatsSearchContext context) {
        Page<TaskStats> domains = taskstatsService.searchTaskFinishHuiZong(context) ;
        List<TaskStatsDTO> list = taskstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskStatsRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取用户完成任务统计", tags = {"任务统计" } ,notes = "获取用户完成任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/taskstats/fetchuserfinishtasksum")
	public ResponseEntity<List<TaskStatsDTO>> fetchuserfinishtasksum(@RequestBody TaskStatsSearchContext context) {
        Page<TaskStats> domains = taskstatsService.searchUserFinishTaskSum(context) ;
        List<TaskStatsDTO> list = taskstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/taskstats/{taskstats_id}/{action}")
    public ResponseEntity<TaskStatsDTO> dynamicCall(@PathVariable("taskstats_id") Long taskstats_id , @PathVariable("action") String action , @RequestBody TaskStatsDTO taskstatsdto) {
        TaskStats domain = taskstatsService.dynamicCall(taskstats_id, action, taskstatsMapping.toDomain(taskstatsdto));
        taskstatsdto = taskstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskstatsdto);
    }
}

