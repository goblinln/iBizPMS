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
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectTeamRuntime;

@Slf4j
@Api(tags = {"项目团队" })
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

    @PreAuthorize("@ProjectTeamRuntime.test(#projectteam_id,'DELETE')")
    @ApiOperation(value = "删除项目团队", tags = {"项目团队" },  notes = "删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectteam_id") Long projectteam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }


    @PreAuthorize("@ProjectTeamRuntime.quickTest('READ')")
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

    @PreAuthorize("@ProjectTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指定团队", tags = {"项目团队" } ,notes = "查询指定团队")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchspecifyteam")
	public ResponseEntity<Page<ProjectTeamDTO>> searchSpecifyTeam(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchSpecifyTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "保存项目团队", tags = {"项目团队" },  notes = "保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/save")
    public ResponseEntity<ProjectTeamDTO> save(@RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        projectteamService.save(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String,Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量保存项目团队", tags = {"项目团队" },  notes = "批量保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectTeamDTO> projectteamdtos) {
        projectteamService.saveBatch(projectteamMapping.toDomain(projectteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectTeamRuntime.test(#projectteam_id,'READ')")
    @ApiOperation(value = "获取项目团队", tags = {"项目团队" },  notes = "获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> get(@PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String,Integer> opprivs = projectteamRuntime.getOPPrivs(projectteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectTeamRuntime.test(#projectteam_id,'UPDATE')")
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
        Map<String,Integer> opprivs = projectteamRuntime.getOPPrivs(projectteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目团队草稿", tags = {"项目团队" },  notes = "获取项目团队草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraft(ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectTeamRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目团队", tags = {"项目团队" },  notes = "新建项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projectteams")
    @Transactional
    public ResponseEntity<ProjectTeamDTO> create(@Validated @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
		projectteamService.create(domain);
        if(!projectteamRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        Map<String,Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectTeamRuntime.quickTest('READ')")
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

    @PreAuthorize("@ProjectTeamRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据查询", tags = {"项目团队" } ,notes = "查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projectteams/searchtaskcntestimateconsumedleft")
	public ResponseEntity<Page<ProjectTeamDTO>> searchTaskCntEstimateConsumedLeft(@RequestBody ProjectTeamSearchContext context) {
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectteams/{projectteam_id}/{action}")
    public ResponseEntity<ProjectTeamDTO> dynamicCall(@PathVariable("projectteam_id") Long projectteam_id , @PathVariable("action") String action , @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamService.dynamicCall(projectteam_id, action, projectteamMapping.toDomain(projectteamdto));
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除项目团队", tags = {"项目团队" },  notes = "根据项目删除项目团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectteamService.remove(projectteam_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指定团队", tags = {"项目团队" } ,notes = "根据项目获取指定团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchspecifyteam")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamSpecifyTeamByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchSpecifyTeam(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询指定团队", tags = {"项目团队" } ,notes = "根据项目查询指定团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchspecifyteam")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamSpecifyTeamByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchSpecifyTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目保存项目团队", tags = {"项目团队" },  notes = "根据项目保存项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams/save")
    public ResponseEntity<ProjectTeamDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        projectteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取项目团队", tags = {"项目团队" },  notes = "根据项目获取项目团队")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/{projectteam_id}")
    public ResponseEntity<ProjectTeamDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id) {
        ProjectTeam domain = projectteamService.get(projectteam_id);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取项目团队草稿", tags = {"项目团队" },  notes = "根据项目获取项目团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectteams/getdraft")
    public ResponseEntity<ProjectTeamDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTeamDTO dto) {
        ProjectTeam domain = projectteamMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamMapping.toDto(projectteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立项目团队", tags = {"项目团队" },  notes = "根据项目建立项目团队")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectteams")
    public ResponseEntity<ProjectTeamDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
		projectteamService.create(domain);
        ProjectTeamDTO dto = projectteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询", tags = {"项目团队" } ,notes = "根据项目获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/fetchtaskcntestimateconsumedleft")
	public ResponseEntity<List<ProjectTeamDTO>> fetchProjectTeamTaskCntEstimateConsumedLeftByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
        List<ProjectTeamDTO> list = projectteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询数据查询", tags = {"项目团队" } ,notes = "根据项目查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectteams/searchtaskcntestimateconsumedleft")
	public ResponseEntity<Page<ProjectTeamDTO>> searchProjectTeamTaskCntEstimateConsumedLeftByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTeamSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectTeam> domains = projectteamService.searchTaskCntEstimateConsumedLeft(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

