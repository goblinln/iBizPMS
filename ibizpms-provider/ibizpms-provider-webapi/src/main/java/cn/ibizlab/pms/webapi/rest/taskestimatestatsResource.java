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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimateStats;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateStatsService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskEstimateStatsRuntime;

@Slf4j
@Api(tags = {"任务工时统计" })
@RestController("WebApi-taskestimatestats")
@RequestMapping("")
public class taskestimatestatsResource {

    @Autowired
    public ITaskEstimateStatsService taskestimatestatsService;

    @Autowired
    public TaskEstimateStatsRuntime taskestimatestatsRuntime;

    @Autowired
    @Lazy
    public taskestimatestatsMapping taskestimatestatsMapping;

    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'CREATE')")
    @ApiOperation(value = "新建任务工时统计", tags = {"任务工时统计" },  notes = "新建任务工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimatestats")
    @Transactional
    public ResponseEntity<taskestimatestatsDTO> create(@Validated @RequestBody taskestimatestatsDTO taskestimatestatsdto) {
        TaskEstimateStats domain = taskestimatestatsMapping.toDomain(taskestimatestatsdto);
		taskestimatestatsService.create(domain);
        if(!taskestimatestatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        taskestimatestatsDTO dto = taskestimatestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = taskestimatestatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('TASKESTIMATESTATS', #taskestimatestats_id, 'UPDATE')")
    @ApiOperation(value = "更新任务工时统计", tags = {"任务工时统计" },  notes = "更新任务工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/taskestimatestats/{taskestimatestats_id}")
    @Transactional
    public ResponseEntity<taskestimatestatsDTO> update(@PathVariable("taskestimatestats_id") Long taskestimatestats_id, @RequestBody taskestimatestatsDTO taskestimatestatsdto) {
		TaskEstimateStats domain  = taskestimatestatsMapping.toDomain(taskestimatestatsdto);
        domain.setId(taskestimatestats_id);
		taskestimatestatsService.update(domain );
        if(!taskestimatestatsRuntime.test(taskestimatestats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		taskestimatestatsDTO dto = taskestimatestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = taskestimatestatsRuntime.getOPPrivs(taskestimatestats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('TASKESTIMATESTATS', #taskestimatestats_id, 'DELETE')")
    @ApiOperation(value = "删除任务工时统计", tags = {"任务工时统计" },  notes = "删除任务工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimatestats/{taskestimatestats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("taskestimatestats_id") Long taskestimatestats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(taskestimatestatsService.remove(taskestimatestats_id));
    }

    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'DELETE')")
    @ApiOperation(value = "批量删除任务工时统计", tags = {"任务工时统计" },  notes = "批量删除任务工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/taskestimatestats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        taskestimatestatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('TASKESTIMATESTATS', #taskestimatestats_id, 'READ')")
    @ApiOperation(value = "获取任务工时统计", tags = {"任务工时统计" },  notes = "获取任务工时统计")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimatestats/{taskestimatestats_id}")
    public ResponseEntity<taskestimatestatsDTO> get(@PathVariable("taskestimatestats_id") Long taskestimatestats_id) {
        TaskEstimateStats domain = taskestimatestatsService.get(taskestimatestats_id);
        taskestimatestatsDTO dto = taskestimatestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = taskestimatestatsRuntime.getOPPrivs(taskestimatestats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'CREATE')")
    @ApiOperation(value = "获取任务工时统计草稿", tags = {"任务工时统计" },  notes = "获取任务工时统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/taskestimatestats/getdraft")
    public ResponseEntity<taskestimatestatsDTO> getDraft(taskestimatestatsDTO dto) {
        TaskEstimateStats domain = taskestimatestatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatestatsMapping.toDto(taskestimatestatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'CREATE')")
    @ApiOperation(value = "检查任务工时统计", tags = {"任务工时统计" },  notes = "检查任务工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimatestats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody taskestimatestatsDTO taskestimatestatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(taskestimatestatsService.checkKey(taskestimatestatsMapping.toDomain(taskestimatestatsdto)));
    }

    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'DENY')")
    @ApiOperation(value = "保存任务工时统计", tags = {"任务工时统计" },  notes = "保存任务工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/taskestimatestats/save")
    public ResponseEntity<taskestimatestatsDTO> save(@RequestBody taskestimatestatsDTO taskestimatestatsdto) {
        TaskEstimateStats domain = taskestimatestatsMapping.toDomain(taskestimatestatsdto);
        taskestimatestatsService.save(domain);
        taskestimatestatsDTO dto = taskestimatestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = taskestimatestatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'READ')")
	@ApiOperation(value = "获取日志月", tags = {"任务工时统计" } ,notes = "获取日志月")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimatestats/fetchactionmonth")
	public ResponseEntity<List<taskestimatestatsDTO>> fetchactionmonth(@RequestBody TaskEstimateStatsSearchContext context) {
        taskestimatestatsRuntime.addAuthorityConditions(context,"READ");
        Page<TaskEstimateStats> domains = taskestimatestatsService.searchActionMonth(context) ;
        List<taskestimatestatsDTO> list = taskestimatestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'READ')")
	@ApiOperation(value = "获取日志年", tags = {"任务工时统计" } ,notes = "获取日志年")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimatestats/fetchactionyear")
	public ResponseEntity<List<taskestimatestatsDTO>> fetchactionyear(@RequestBody TaskEstimateStatsSearchContext context) {
        taskestimatestatsRuntime.addAuthorityConditions(context,"READ");
        Page<TaskEstimateStats> domains = taskestimatestatsService.searchActionYear(context) ;
        List<taskestimatestatsDTO> list = taskestimatestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('TASKESTIMATESTATS', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务工时统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/taskestimatestats/fetchdefault")
	public ResponseEntity<List<taskestimatestatsDTO>> fetchdefault(@RequestBody TaskEstimateStatsSearchContext context) {
        taskestimatestatsRuntime.addAuthorityConditions(context,"READ");
        Page<TaskEstimateStats> domains = taskestimatestatsService.searchDefault(context) ;
        List<taskestimatestatsDTO> list = taskestimatestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/taskestimatestats/{taskestimatestats_id}/{action}")
    public ResponseEntity<taskestimatestatsDTO> dynamicCall(@PathVariable("taskestimatestats_id") Long taskestimatestats_id , @PathVariable("action") String action , @RequestBody taskestimatestatsDTO taskestimatestatsdto) {
        TaskEstimateStats domain = taskestimatestatsService.dynamicCall(taskestimatestats_id, action, taskestimatestatsMapping.toDomain(taskestimatestatsdto));
        taskestimatestatsdto = taskestimatestatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(taskestimatestatsdto);
    }
}

