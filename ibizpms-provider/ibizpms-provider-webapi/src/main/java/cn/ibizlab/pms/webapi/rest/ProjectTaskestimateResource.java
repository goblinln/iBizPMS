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
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
import cn.ibizlab.pms.core.ibizpro.service.IProjectTaskestimateService;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTaskestimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"项目工时统计" })
@RestController("WebApi-projecttaskestimate")
@RequestMapping("")
public class ProjectTaskestimateResource {

    @Autowired
    public IProjectTaskestimateService projecttaskestimateService;

    @Autowired
    @Lazy
    public ProjectTaskestimateMapping projecttaskestimateMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Create-all')")
    @ApiOperation(value = "新建项目工时统计", tags = {"项目工时统计" },  notes = "新建项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates")
    public ResponseEntity<ProjectTaskestimateDTO> create(@Validated @RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
		projecttaskestimateService.create(domain);
        ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Create-all')")
    @ApiOperation(value = "批量新建项目工时统计", tags = {"项目工时统计" },  notes = "批量新建项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectTaskestimateDTO> projecttaskestimatedtos) {
        projecttaskestimateService.createBatch(projecttaskestimateMapping.toDomain(projecttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Update-all')")
    @ApiOperation(value = "更新项目工时统计", tags = {"项目工时统计" },  notes = "更新项目工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskestimateDTO> update(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id, @RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
		ProjectTaskestimate domain  = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        domain .setId(projecttaskestimate_id);
		projecttaskestimateService.update(domain );
		ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Update-all')")
    @ApiOperation(value = "批量更新项目工时统计", tags = {"项目工时统计" },  notes = "批量更新项目工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projecttaskestimates/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectTaskestimateDTO> projecttaskestimatedtos) {
        projecttaskestimateService.updateBatch(projecttaskestimateMapping.toDomain(projecttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Remove-all')")
    @ApiOperation(value = "删除项目工时统计", tags = {"项目工时统计" },  notes = "删除项目工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateService.remove(projecttaskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Remove-all')")
    @ApiOperation(value = "批量删除项目工时统计", tags = {"项目工时统计" },  notes = "批量删除项目工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttaskestimates/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        projecttaskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Get-all')")
    @ApiOperation(value = "获取项目工时统计", tags = {"项目工时统计" },  notes = "获取项目工时统计")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskestimateDTO> get(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id) {
        ProjectTaskestimate domain = projecttaskestimateService.get(projecttaskestimate_id);
        ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目工时统计草稿", tags = {"项目工时统计" },  notes = "获取项目工时统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/getdraft")
    public ResponseEntity<ProjectTaskestimateDTO> getDraft(ProjectTaskestimateDTO dto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateMapping.toDto(projecttaskestimateService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目工时统计", tags = {"项目工时统计" },  notes = "检查项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateService.checkKey(projecttaskestimateMapping.toDomain(projecttaskestimatedto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Save-all')")
    @ApiOperation(value = "保存项目工时统计", tags = {"项目工时统计" },  notes = "保存项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/save")
    public ResponseEntity<ProjectTaskestimateDTO> save(@RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        projecttaskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-Save-all')")
    @ApiOperation(value = "批量保存项目工时统计", tags = {"项目工时统计" },  notes = "批量保存项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectTaskestimateDTO> projecttaskestimatedtos) {
        projecttaskestimateService.saveBatch(projecttaskestimateMapping.toDomain(projecttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchAccountDetail-all')")
	@ApiOperation(value = "获取用户月工时详情", tags = {"项目工时统计" } ,notes = "获取用户月工时详情")
    @RequestMapping(method= RequestMethod.GET , value="/projecttaskestimates/fetchaccountdetail")
	public ResponseEntity<List<ProjectTaskestimateDTO>> fetchAccountDetail(ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchAccountDetail(context) ;
        List<ProjectTaskestimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchAccountDetail-all')")
	@ApiOperation(value = "查询用户月工时详情", tags = {"项目工时统计" } ,notes = "查询用户月工时详情")
    @RequestMapping(method= RequestMethod.POST , value="/projecttaskestimates/searchaccountdetail")
	public ResponseEntity<Page<ProjectTaskestimateDTO>> searchAccountDetail(@RequestBody ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchAccountDetail(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchCurProjectUserEstimate-all')")
	@ApiOperation(value = "获取项目下用户工时详情", tags = {"项目工时统计" } ,notes = "获取项目下用户工时详情")
    @RequestMapping(method= RequestMethod.GET , value="/projecttaskestimates/fetchcurprojectuserestimate")
	public ResponseEntity<List<ProjectTaskestimateDTO>> fetchCurProjectUserEstimate(ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchCurProjectUserEstimate(context) ;
        List<ProjectTaskestimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchCurProjectUserEstimate-all')")
	@ApiOperation(value = "查询项目下用户工时详情", tags = {"项目工时统计" } ,notes = "查询项目下用户工时详情")
    @RequestMapping(method= RequestMethod.POST , value="/projecttaskestimates/searchcurprojectuserestimate")
	public ResponseEntity<Page<ProjectTaskestimateDTO>> searchCurProjectUserEstimate(@RequestBody ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchCurProjectUserEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"项目工时统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/projecttaskestimates/fetchdefault")
	public ResponseEntity<List<ProjectTaskestimateDTO>> fetchDefault(ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchDefault(context) ;
        List<ProjectTaskestimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTaskestimate-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"项目工时统计" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projecttaskestimates/searchdefault")
	public ResponseEntity<Page<ProjectTaskestimateDTO>> searchDefault(@RequestBody ProjectTaskestimateSearchContext context) {
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

