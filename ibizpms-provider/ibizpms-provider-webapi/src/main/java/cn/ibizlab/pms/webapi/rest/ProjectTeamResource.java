package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectTeamRuntime;

@Slf4j
@Api(tags = {"项目团队"})
@RestController("WebApi-projectteam")
@RequestMapping("")
public class ProjectTeamResource {

    @Autowired
    public IProjectTeamService projectteamService;

    @Autowired
    public ProjectTeamRuntime projectteamRuntime;

    @Autowired
    @Lazy
    public ProjectTeamMapping projectteamMapping;

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'CREATE')")
    @ApiOperation(value = "新建项目团队", tags = {"项目团队" },  notes = "新建项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams")
    @Transactional
    public ResponseEntity<ProjectTeamDTO> create(@Validated @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
		projectteamService.create(domain);
        if(!projectteamRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', #projectteam_id, 'READ')")
    @ApiOperation(value = "获取项目团队", tags = {"项目团队" },  notes = "获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> get(@PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs(projectteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', #projectteam_id, 'DELETE')")
    @ApiOperation(value = "删除项目团队", tags = {"项目团队" },  notes = "删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectteam_id") Long projectteam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'DELETE')")
    @ApiOperation(value = "批量删除项目团队", tags = {"项目团队" },  notes = "批量删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectteams/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', #projectteam_id, 'UPDATE')")
    @ApiOperation(value = "更新项目团队", tags = {"项目团队" },  notes = "更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/{projectteam_id}")
    @Transactional
    public ResponseEntity<ProjectTeamDTO> update(@PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
		ProjectTeam domain  = projectteamMapping.toDomain(projectteamdto);
        domain.setId(projectteam_id);
		projectteamService.update(domain );
        if(!projectteamRuntime.test(projectteam_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs(projectteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'CREATE')")
    @ApiOperation(value = "检查项目团队", tags = {"项目团队" },  notes = "检查项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectTeamDTO projectteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectteamService.checkKey(projectteamMapping.toDomain(projectteamdto)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'CREATE')")
    @ApiOperation(value = "获取项目团队草稿", tags = {"项目团队" },  notes = "获取项目团队草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraft(ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'DENY')")
    @ApiOperation(value = "获取成员角色", tags = {"项目团队" },  notes = "获取成员角色")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRole(@PathVariable("projectteam_id") Long projectteam_id, ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setId(projectteam_id);
        domain = projectteamService.getUserRole(domain);
        projectteamdto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());
        projectteamdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }



    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'CREATE')")
    @ApiOperation(value = "批量保存项目团队", tags = {"项目团队" },  notes = "批量保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        projectteamService.saveBatch(projectteamMapping.toDomain(projectteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目团队" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/fetchdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchdefault(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'READ')")
	@ApiOperation(value = "获取项目成员（项目经理）", tags = {"项目团队" } ,notes = "获取项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/fetchprojectteampm")
	public ResponseEntity<List<ProjectTeamDTO>> fetchprojectteampm(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'READ')")
	@ApiOperation(value = "获取行编辑查询", tags = {"项目团队" } ,notes = "获取行编辑查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/fetchroweditdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchroweditdefault(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'READ')")
	@ApiOperation(value = "获取指定团队", tags = {"项目团队" } ,notes = "获取指定团队")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/fetchspecifyteam")
	public ResponseEntity<List<ProjectTeamDTO>> fetchspecifyteam(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchSpecifyTeam(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'READ')")
	@ApiOperation(value = "获取数据查询", tags = {"项目团队" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/fetchtaskcntestimateconsumedleft")
	public ResponseEntity<List<ProjectTeamDTO>> fetchtaskcntestimateconsumedleft(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目团队报表", tags = {"项目团队"}, notes = "生成项目团队报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projectteams/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProjectTeamSearchContext context, HttpServletResponse response) {
        try {
            projectteamRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectteamRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, projectteamRuntime);
        }
    }

    @ApiOperation(value = "打印项目团队", tags = {"项目团队"}, notes = "打印项目团队")
    @RequestMapping(method = RequestMethod.GET, value = "/projectteams/{projectteam_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("projectteam_ids") Set<Long> projectteam_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = projectteamRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProjectTeam> domains = new ArrayList<>();
            for (Long projectteam_id : projectteam_ids) {
                domains.add(projectteamService.get( projectteam_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProjectTeam[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectteamRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", projectteam_ids, e.getMessage()), Errors.INTERNALERROR, projectteamRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectteams/{projectteam_id}/{action}")
    public ResponseEntity<ProjectTeamDTO> dynamicCall(@PathVariable("projectteam_id") Long projectteam_id , @PathVariable("action") String action , @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamService.dynamicCall(projectteam_id, action, projectteamMapping.toDomain(projectteamdto));
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目建立项目团队", tags = {"项目团队" },  notes = "根据项目建立项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams")
    public ResponseEntity<ProjectTeamDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
		projectteamService.create(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', #projectteam_id, 'READ')")
    @ApiOperation(value = "根据项目获取项目团队", tags = {"项目团队" },  notes = "根据项目获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        if (domain == null || domain.getRoot() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'DELETE', #projectteam_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除项目团队", tags = {"项目团队" },  notes = "根据项目删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam testget = projectteamService.get(projectteam_id);
        if (testget == null || testget.getRoot() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除项目团队", tags = {"项目团队" },  notes = "根据项目批量删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectteams/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projectteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'UPDATE', #projectteam_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新项目团队", tags = {"项目团队" },  notes = "根据项目更新项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam testget = projectteamService.get(projectteam_id);
        if (testget == null || testget.getRoot() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        domain.setId(projectteam_id);
		projectteamService.update(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目检查项目团队", tags = {"项目团队" },  notes = "根据项目检查项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectteamService.checkKey(projectteamMapping.toDomain(projectteamdto)));
    }

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目获取项目团队草稿", tags = {"项目团队" },  notes = "根据项目获取项目团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTTEAM', 'DENY')")
    @ApiOperation(value = "根据项目获取成员角色", tags = {"项目团队" },  notes = "根据项目获取成员角色")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRoleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id, ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        domain.setId(projectteam_id);
        domain = projectteamService.getUserRole(domain) ;
        projectteamdto = projectteamMapping.toDto(domain);
        Map<String, Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());    
        projectteamdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }


    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
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

    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"项目团队" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目成员（项目经理）", tags = {"项目团队" } ,notes = "根据项目获取项目成员（项目经理）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchprojectteampm")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamPmByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchProjectTeamPm(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取行编辑查询", tags = {"项目团队" } ,notes = "根据项目获取行编辑查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchroweditdefault")
	public ResponseEntity<List<ProjectTeamDTO>> fetchRowEditDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchRowEditDefault(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指定团队", tags = {"项目团队" } ,notes = "根据项目获取指定团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchspecifyteam")
	public ResponseEntity<List<ProjectTeamDTO>> fetchSpecifyTeamByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchSpecifyTeam(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTTEAM', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取数据查询", tags = {"项目团队" } ,notes = "根据项目获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchtaskcntestimateconsumedleft")
	public ResponseEntity<List<ProjectTeamDTO>> fetchTaskCntEstimateConsumedLeftByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

