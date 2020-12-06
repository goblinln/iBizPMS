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
import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"项目统计" })
@RestController("WebApi-projectstats")
@RequestMapping("")
public class ProjectStatsResource {

    @Autowired
    public IProjectStatsService projectstatsService;

    @Autowired
    @Lazy
    public ProjectStatsMapping projectstatsMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Create-all')")
    @ApiOperation(value = "新建项目统计", tags = {"项目统计" },  notes = "新建项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats")
    public ResponseEntity<ProjectStatsDTO> create(@Validated @RequestBody ProjectStatsDTO projectstatsdto) {
        ProjectStats domain = projectstatsMapping.toDomain(projectstatsdto);
		projectstatsService.create(domain);
        ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Create-all')")
    @ApiOperation(value = "批量新建项目统计", tags = {"项目统计" },  notes = "批量新建项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectStatsDTO> projectstatsdtos) {
        projectstatsService.createBatch(projectstatsMapping.toDomain(projectstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Update-all')")
    @ApiOperation(value = "更新项目统计", tags = {"项目统计" },  notes = "更新项目统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectstats/{projectstats_id}")
    public ResponseEntity<ProjectStatsDTO> update(@PathVariable("projectstats_id") Long projectstats_id, @RequestBody ProjectStatsDTO projectstatsdto) {
		ProjectStats domain  = projectstatsMapping.toDomain(projectstatsdto);
        domain .setId(projectstats_id);
		projectstatsService.update(domain );
		ProjectStatsDTO dto = projectstatsMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Update-all')")
    @ApiOperation(value = "批量更新项目统计", tags = {"项目统计" },  notes = "批量更新项目统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectstats/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectStatsDTO> projectstatsdtos) {
        projectstatsService.updateBatch(projectstatsMapping.toDomain(projectstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Remove-all')")
    @ApiOperation(value = "删除项目统计", tags = {"项目统计" },  notes = "删除项目统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectstats/{projectstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectstats_id") Long projectstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectstatsService.remove(projectstats_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Remove-all')")
    @ApiOperation(value = "批量删除项目统计", tags = {"项目统计" },  notes = "批量删除项目统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Get-all')")
    @ApiOperation(value = "获取项目统计", tags = {"项目统计" },  notes = "获取项目统计")
	@RequestMapping(method = RequestMethod.GET, value = "/projectstats/{projectstats_id}")
    public ResponseEntity<ProjectStatsDTO> get(@PathVariable("projectstats_id") Long projectstats_id) {
        ProjectStats domain = projectstatsService.get(projectstats_id);
        ProjectStatsDTO dto = projectstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目统计草稿", tags = {"项目统计" },  notes = "获取项目统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectstats/getdraft")
    public ResponseEntity<ProjectStatsDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(projectstatsMapping.toDto(projectstatsService.getDraft(new ProjectStats())));
    }

    @ApiOperation(value = "检查项目统计", tags = {"项目统计" },  notes = "检查项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectStatsDTO projectstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectstatsService.checkKey(projectstatsMapping.toDomain(projectstatsdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Save-all')")
    @ApiOperation(value = "保存项目统计", tags = {"项目统计" },  notes = "保存项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/save")
    public ResponseEntity<Boolean> save(@RequestBody ProjectStatsDTO projectstatsdto) {
        return ResponseEntity.status(HttpStatus.OK).body(projectstatsService.save(projectstatsMapping.toDomain(projectstatsdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-Save-all')")
    @ApiOperation(value = "批量保存项目统计", tags = {"项目统计" },  notes = "批量保存项目统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projectstats/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectStatsDTO> projectstatsdtos) {
        projectstatsService.saveBatch(projectstatsMapping.toDomain(projectstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目统计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projectstats/fetchdefault")
	public ResponseEntity<List<ProjectStatsDTO>> fetchDefault(ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchDefault(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"项目统计" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/searchdefault")
	public ResponseEntity<Page<ProjectStatsDTO>> searchDefault(@RequestBody ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchNoOpenProduct-all')")
	@ApiOperation(value = "获取未关闭产品", tags = {"项目统计" } ,notes = "获取未关闭产品")
    @RequestMapping(method= RequestMethod.GET , value="/projectstats/fetchnoopenproduct")
	public ResponseEntity<List<ProjectStatsDTO>> fetchNoOpenProduct(ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchNoOpenProduct(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchNoOpenProduct-all')")
	@ApiOperation(value = "查询未关闭产品", tags = {"项目统计" } ,notes = "查询未关闭产品")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/searchnoopenproduct")
	public ResponseEntity<Page<ProjectStatsDTO>> searchNoOpenProduct(@RequestBody ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchNoOpenProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchProjectTaskCountByTaskStatus-all')")
	@ApiOperation(value = "获取项目任务统计(任务状态)", tags = {"项目统计" } ,notes = "获取项目任务统计(任务状态)")
    @RequestMapping(method= RequestMethod.GET , value="/projectstats/fetchprojecttaskcountbytaskstatus")
	public ResponseEntity<List<ProjectStatsDTO>> fetchProjectTaskCountByTaskStatus(ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchProjectTaskCountByTaskStatus(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchProjectTaskCountByTaskStatus-all')")
	@ApiOperation(value = "查询项目任务统计(任务状态)", tags = {"项目统计" } ,notes = "查询项目任务统计(任务状态)")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/searchprojecttaskcountbytaskstatus")
	public ResponseEntity<Page<ProjectStatsDTO>> searchProjectTaskCountByTaskStatus(@RequestBody ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchProjectTaskCountByTaskStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchTaskTime-all')")
	@ApiOperation(value = "获取任务工时消耗剩余查询", tags = {"项目统计" } ,notes = "获取任务工时消耗剩余查询")
    @RequestMapping(method= RequestMethod.GET , value="/projectstats/fetchtasktime")
	public ResponseEntity<List<ProjectStatsDTO>> fetchTaskTime(ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchTaskTime(context) ;
        List<ProjectStatsDTO> list = projectstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectStats-searchTaskTime-all')")
	@ApiOperation(value = "查询任务工时消耗剩余查询", tags = {"项目统计" } ,notes = "查询任务工时消耗剩余查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectstats/searchtasktime")
	public ResponseEntity<Page<ProjectStatsDTO>> searchTaskTime(@RequestBody ProjectStatsSearchContext context) {
        Page<ProjectStats> domains = projectstatsService.searchTaskTime(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


}

