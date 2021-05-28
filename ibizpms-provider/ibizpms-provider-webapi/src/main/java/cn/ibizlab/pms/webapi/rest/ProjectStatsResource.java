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
import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectStatsRuntime;

@Slf4j
@Api(tags = {"项目统计" })
@RestController("WebApi-projectstats")
@RequestMapping("")
public class ProjectStatsResource {

    @Autowired
    public IProjectStatsService projectstatsService;

    @Autowired
    public ProjectStatsRuntime projectstatsRuntime;

    @Autowired
    @Lazy
    public ProjectStatsMapping projectstatsMapping;

    @PreAuthorize("@ProjectStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目统计", tags = {"项目统计" },  notes = "新建项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats")
    @Transactional
    public ResponseEntity<ProjectStatsDTO> create(@Validated @RequestBody ProjectStatsDTO projectstatsdto) {
        ProjectStats domain = projectstatsMapping.toDomain(projectstatsdto);
		projectstatsService.create(domain);
        if(!projectstatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = projectstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectStatsRuntime.test(#projectstats_id, 'UPDATE')")
    @ApiOperation(value = "更新项目统计", tags = {"项目统计" },  notes = "更新项目统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectstats/{projectstats_id}")
    @Transactional
    public ResponseEntity<ProjectStatsDTO> update(@PathVariable("projectstats_id") Long projectstats_id, @RequestBody ProjectStatsDTO projectstatsdto) {
		ProjectStats domain  = projectstatsMapping.toDomain(projectstatsdto);
        domain.setId(projectstats_id);
		projectstatsService.update(domain );
        if(!projectstatsRuntime.test(projectstats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = projectstatsRuntime.getOPPrivs(projectstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectStatsRuntime.test(#projectstats_id, 'DELETE')")
    @ApiOperation(value = "删除项目统计", tags = {"项目统计" },  notes = "删除项目统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectstats/{projectstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectstats_id") Long projectstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectstatsService.remove(projectstats_id));
    }


    @PreAuthorize("@ProjectStatsRuntime.test(#projectstats_id, 'NONE')")
    @ApiOperation(value = "获取项目统计", tags = {"项目统计" },  notes = "获取项目统计")
	@RequestMapping(method = RequestMethod.GET, value = "/projectstats/{projectstats_id}")
    public ResponseEntity<ProjectStatsDTO> get(@PathVariable("projectstats_id") Long projectstats_id) {
        ProjectStats domain = projectstatsService.get(projectstats_id);
        ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = projectstatsRuntime.getOPPrivs(projectstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目统计草稿", tags = {"项目统计" },  notes = "获取项目统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectstats/getdraft")
    public ResponseEntity<ProjectStatsDTO> getDraft(ProjectStatsDTO dto) {
        ProjectStats domain = projectstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectstatsMapping.toDto(projectstatsService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectStatsRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查项目统计", tags = {"项目统计" },  notes = "检查项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectStatsDTO projectstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectstatsService.checkKey(projectstatsMapping.toDomain(projectstatsdto)));
    }

    @PreAuthorize("@ProjectStatsRuntime.test(#projectstats_id, 'NONE')")
    @ApiOperation(value = "项目质量表聚合逻辑", tags = {"项目统计" },  notes = "项目质量表聚合逻辑")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/{projectstats_id}/projectqualitysum")
    public ResponseEntity<ProjectStatsDTO> projectQualitySum(@PathVariable("projectstats_id") Long projectstats_id, @RequestBody ProjectStatsDTO projectstatsdto) {
        ProjectStats domain = projectstatsMapping.toDomain(projectstatsdto);
        domain.setId(projectstats_id);
        domain = projectstatsService.projectQualitySum(domain);
        projectstatsdto = projectstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = projectstatsRuntime.getOPPrivs(domain.getId());
        projectstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectstatsdto);
    }


    @PreAuthorize("@ProjectStatsRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存项目统计", tags = {"项目统计" },  notes = "保存项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/save")
    public ResponseEntity<ProjectStatsDTO> save(@RequestBody ProjectStatsDTO projectstatsdto) {
        ProjectStats domain = projectstatsMapping.toDomain(projectstatsdto);
        projectstatsService.save(domain);
        ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = projectstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目统计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchdefault")
	public ResponseEntity<List<ProjectStatsDTO>> fetchdefault(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchDefault(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取未关闭产品", tags = {"项目统计" } ,notes = "获取未关闭产品")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchnoopenproduct")
	public ResponseEntity<List<ProjectStatsDTO>> fetchnoopenproduct(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchNoOpenProduct(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目bug类型统计", tags = {"项目统计" } ,notes = "获取项目bug类型统计")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectbugtype")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectbugtype(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectBugType(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目投入统计", tags = {"项目统计" } ,notes = "获取项目投入统计")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectinputstats")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectinputstats(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectInputStats(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目进度", tags = {"项目统计" } ,notes = "获取项目进度")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectprogress")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectprogress(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectProgress(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目质量", tags = {"项目统计" } ,notes = "获取项目质量")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectquality")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectquality(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectQuality(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目需求阶段统计", tags = {"项目统计" } ,notes = "获取项目需求阶段统计")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectstorystagestats")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectstorystagestats(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectStoryStageStats(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目需求状态统计", tags = {"项目统计" } ,notes = "获取项目需求状态统计")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojectstorystatusstats")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojectstorystatusstats(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectStoryStatusStats(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目任务统计(任务状态)", tags = {"项目统计" } ,notes = "获取项目任务统计(任务状态)")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojecttaskcountbytaskstatus")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojecttaskcountbytaskstatus(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectTaskCountByTaskStatus(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目任务类型统计", tags = {"项目统计" } ,notes = "获取项目任务类型统计")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchprojecttaskcountbytype")
	public ResponseEntity<List<ProjectStatsDTO>> fetchprojecttaskcountbytype(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchProjectTaskCountByType(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectStatsRuntime.quickTest('READ')")
	@ApiOperation(value = "获取任务工时消耗剩余查询", tags = {"项目统计" } ,notes = "获取任务工时消耗剩余查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/fetchtasktime")
	public ResponseEntity<List<ProjectStatsDTO>> fetchtasktime(@RequestBody ProjectStatsSearchContext context) {
        projectstatsRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectStats> domains = projectstatsService.searchTaskTime(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectstats/{projectstats_id}/{action}")
    public ResponseEntity<ProjectStatsDTO> dynamicCall(@PathVariable("projectstats_id") Long projectstats_id , @PathVariable("action") String action , @RequestBody ProjectStatsDTO projectstatsdto) {
        ProjectStats domain = projectstatsService.dynamicCall(projectstats_id, action, projectstatsMapping.toDomain(projectstatsdto));
        projectstatsdto = projectstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectstatsdto);
    }
}

