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
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"项目团队" })
@RestController("WebApi-projectteam")
@RequestMapping("")
public class ProjectTeamResource {

    @Autowired
    public IProjectTeamService projectteamService;

    @Autowired
    @Lazy
    public ProjectTeamMapping projectteamMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Create-all')")
    @ApiOperation(value = "新建项目团队", tags = {"项目团队" },  notes = "新建项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams")
    public ResponseEntity<ProjectTeamDTO> create(@Validated @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
		projectteamService.create(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Create-all')")
    @ApiOperation(value = "批量新建项目团队", tags = {"项目团队" },  notes = "批量新建项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        projectteamService.createBatch(projectteamMapping.toDomain(projectteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Get-all')")
    @ApiOperation(value = "获取项目团队", tags = {"项目团队" },  notes = "获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> get(@PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Remove-all')")
    @ApiOperation(value = "删除项目团队", tags = {"项目团队" },  notes = "删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectteam_id") Long projectteam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Remove-all')")
    @ApiOperation(value = "批量删除项目团队", tags = {"项目团队" },  notes = "批量删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectteams/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Update-all')")
    @ApiOperation(value = "更新项目团队", tags = {"项目团队" },  notes = "更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> update(@PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
		ProjectTeam domain  = projectteamMapping.toDomain(projectteamdto);
        domain .setId(projectteam_id);
		projectteamService.update(domain );
		ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Update-all')")
    @ApiOperation(value = "批量更新项目团队", tags = {"项目团队" },  notes = "批量更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        projectteamService.updateBatch(projectteamMapping.toDomain(projectteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查项目团队", tags = {"项目团队" },  notes = "检查项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectTeamDTO projectteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectteamService.checkKey(projectteamMapping.toDomain(projectteamdto)));
    }

    @ApiOperation(value = "获取项目团队草稿", tags = {"项目团队" },  notes = "获取项目团队草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraft(ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Save-all')")
    @ApiOperation(value = "保存项目团队", tags = {"项目团队" },  notes = "保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/save")
    public ResponseEntity<ProjectTeamDTO> save(@RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        projectteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Save-all')")
    @ApiOperation(value = "批量保存项目团队", tags = {"项目团队" },  notes = "批量保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        projectteamService.saveBatch(projectteamMapping.toDomain(projectteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-GetUserRole-all')")
    @ApiOperation(value = "获取成员角色", tags = {"项目团队" },  notes = "获取成员角色")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRole(@PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setId(projectteam_id);
        domain = projectteamService.getUserRole(domain);
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-GetUserRole-all')")
    @ApiOperation(value = "批量处理[获取成员角色]", tags = {"项目团队" },  notes = "批量处理[获取成员角色]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/getuserrolebatch")
    public ResponseEntity<Boolean> getUserRoleBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        List<ProjectTeam> domains = projectteamMapping.toDomain(projectteamdtos);
        boolean result = projectteamService.getUserRoleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目团队" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projectteams/fetchdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchDefault(ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"项目团队" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchdefault")
	public ResponseEntity<Page<ProjectTeamDTO>> searchDefault(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectAllMembers-all')")
	@ApiOperation(value = "获取项目所有成员", tags = {"项目团队" } ,notes = "获取项目所有成员")
    @RequestMapping(method= RequestMethod.GET , value="/projectteams/fetchprojectallmembers")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectAllMembers(ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchProjectAllMembers(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectAllMembers-all')")
	@ApiOperation(value = "查询项目所有成员", tags = {"项目团队" } ,notes = "查询项目所有成员")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchprojectallmembers")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectAllMembers(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchProjectAllMembers(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectTeamPm-all')")
	@ApiOperation(value = "获取项目成员（项目经理）", tags = {"项目团队" } ,notes = "获取项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.GET , value="/projectteams/fetchprojectteampm")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamPm(ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectTeamPm-all')")
	@ApiOperation(value = "查询项目成员（项目经理）", tags = {"项目团队" } ,notes = "查询项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchprojectteampm")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamPm(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchRowEditDefault-all')")
	@ApiOperation(value = "获取行编辑查询", tags = {"项目团队" } ,notes = "获取行编辑查询")
    @RequestMapping(method= RequestMethod.GET , value="/projectteams/fetchroweditdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchRowEditDefault(ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchRowEditDefault-all')")
	@ApiOperation(value = "查询行编辑查询", tags = {"项目团队" } ,notes = "查询行编辑查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchroweditdefault")
	public ResponseEntity<Page<ProjectTeamDTO>> searchRowEditDefault(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchTaskCntEstimateConsumedLeft-all')")
	@ApiOperation(value = "获取数据查询", tags = {"项目团队" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.GET , value="/projectteams/fetchtaskcntestimateconsumedleft")
	public ResponseEntity<List<ProjectTeamDTO>> fetchTaskCntEstimateConsumedLeft(ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchTaskCntEstimateConsumedLeft-all')")
	@ApiOperation(value = "查询数据查询", tags = {"项目团队" } ,notes = "查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchtaskcntestimateconsumedleft")
	public ResponseEntity<Page<ProjectTeamDTO>> searchTaskCntEstimateConsumedLeft(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Create-all')")
    @ApiOperation(value = "根据项目建立项目团队", tags = {"项目团队" },  notes = "根据项目建立项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams")
    public ResponseEntity<ProjectTeamDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
		projectteamService.create(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Create-all')")
    @ApiOperation(value = "根据项目批量建立项目团队", tags = {"项目团队" },  notes = "根据项目批量建立项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTeamDTO> projectteamdtos) {
        List<ProjectTeam> domainlist=projectteamMapping.toDomain(projectteamdtos);
        for(ProjectTeam domain:domainlist){
            domain.setRoot(project_id);
        }
        projectteamService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Get-all')")
    @ApiOperation(value = "根据项目获取项目团队", tags = {"项目团队" },  notes = "根据项目获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Remove-all')")
    @ApiOperation(value = "根据项目删除项目团队", tags = {"项目团队" },  notes = "根据项目删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Remove-all')")
    @ApiOperation(value = "根据项目批量删除项目团队", tags = {"项目团队" },  notes = "根据项目批量删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectteams/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projectteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Update-all')")
    @ApiOperation(value = "根据项目更新项目团队", tags = {"项目团队" },  notes = "根据项目更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        domain.setId(projectteam_id);
		projectteamService.update(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Update-all')")
    @ApiOperation(value = "根据项目批量更新项目团队", tags = {"项目团队" },  notes = "根据项目批量更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTeamDTO> projectteamdtos) {
        List<ProjectTeam> domainlist=projectteamMapping.toDomain(projectteamdtos);
        for(ProjectTeam domain:domainlist){
            domain.setRoot(project_id);
        }
        projectteamService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目检查项目团队", tags = {"项目团队" },  notes = "根据项目检查项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectteamService.checkKey(projectteamMapping.toDomain(projectteamdto)));
    }

    @ApiOperation(value = "根据项目获取项目团队草稿", tags = {"项目团队" },  notes = "根据项目获取项目团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Save-all')")
    @ApiOperation(value = "根据项目保存项目团队", tags = {"项目团队" },  notes = "根据项目保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/save")
    public ResponseEntity<ProjectTeamDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        projectteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-Save-all')")
    @ApiOperation(value = "根据项目批量保存项目团队", tags = {"项目团队" },  notes = "根据项目批量保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTeamDTO> projectteamdtos) {
        List<ProjectTeam> domainlist=projectteamMapping.toDomain(projectteamdtos);
        for(ProjectTeam domain:domainlist){
             domain.setRoot(project_id);
        }
        projectteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-GetUserRole-all')")
    @ApiOperation(value = "根据项目项目团队", tags = {"项目团队" },  notes = "根据项目项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRoleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        domain = projectteamService.getUserRole(domain) ;
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }
    @ApiOperation(value = "批量处理[根据项目项目团队]", tags = {"项目团队" },  notes = "批量处理[根据项目项目团队]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/getuserrolebatch")
    public ResponseEntity<Boolean> getUserRoleByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTeamDTO> projectteamdtos) {
        List<ProjectTeam> domains = projectteamMapping.toDomain(projectteamdtos);
        boolean result = projectteamService.getUserRoleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchDefault-all')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"项目团队" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectteams/fetchdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamDefaultByProject(@PathVariable("project_id") Long project_id,ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchDefault-all')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"项目团队" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchdefault")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectAllMembers-all')")
	@ApiOperation(value = "根据项目获取项目所有成员", tags = {"项目团队" } ,notes = "根据项目获取项目所有成员")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectteams/fetchprojectallmembers")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamProjectAllMembersByProject(@PathVariable("project_id") Long project_id,ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchProjectAllMembers(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectAllMembers-all')")
	@ApiOperation(value = "根据项目查询项目所有成员", tags = {"项目团队" } ,notes = "根据项目查询项目所有成员")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchprojectallmembers")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamProjectAllMembersByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchProjectAllMembers(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectTeamPm-all')")
	@ApiOperation(value = "根据项目获取项目成员（项目经理）", tags = {"项目团队" } ,notes = "根据项目获取项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectteams/fetchprojectteampm")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamProjectTeamPmByProject(@PathVariable("project_id") Long project_id,ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchProjectTeamPm-all')")
	@ApiOperation(value = "根据项目查询项目成员（项目经理）", tags = {"项目团队" } ,notes = "根据项目查询项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchprojectteampm")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamProjectTeamPmByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchRowEditDefault-all')")
	@ApiOperation(value = "根据项目获取行编辑查询", tags = {"项目团队" } ,notes = "根据项目获取行编辑查询")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectteams/fetchroweditdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamRowEditDefaultByProject(@PathVariable("project_id") Long project_id,ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchRowEditDefault-all')")
	@ApiOperation(value = "根据项目查询行编辑查询", tags = {"项目团队" } ,notes = "根据项目查询行编辑查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchroweditdefault")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamRowEditDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchTaskCntEstimateConsumedLeft-all')")
	@ApiOperation(value = "根据项目获取数据查询", tags = {"项目团队" } ,notes = "根据项目获取数据查询")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectteams/fetchtaskcntestimateconsumedleft")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamTaskCntEstimateConsumedLeftByProject(@PathVariable("project_id") Long project_id,ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTeam-searchTaskCntEstimateConsumedLeft-all')")
	@ApiOperation(value = "根据项目查询数据查询", tags = {"项目团队" } ,notes = "根据项目查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchtaskcntestimateconsumedleft")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamTaskCntEstimateConsumedLeftByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

