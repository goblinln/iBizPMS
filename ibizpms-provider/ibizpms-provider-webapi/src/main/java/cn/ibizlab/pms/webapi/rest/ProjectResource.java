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
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectRuntime;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;

@Slf4j
@Api(tags = {"项目" })
@RestController("WebApi-project")
@RequestMapping("")
public class ProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    public ProjectRuntime projectRuntime;

    @Autowired
    @Lazy
    public ProjectMapping projectMapping;

    @Autowired
    private IProjectTeamService projectteamService;

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> get(@PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取默认查询（项目导航）", tags = {"项目" } ,notes = "获取默认查询（项目导航）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurdefaultqueryexp")
	public ResponseEntity<List<ProjectDTO>> fetchcurdefaultqueryexp(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurDefaultQueryExp(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询默认查询（项目导航）", tags = {"项目" } ,notes = "查询默认查询（项目导航）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurdefaultqueryexp")
	public ResponseEntity<Page<ProjectDTO>> searchCurDefaultQueryExp(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurDefaultQueryExp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取默认查询", tags = {"项目" } ,notes = "获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurdefaultquery")
	public ResponseEntity<List<ProjectDTO>> fetchcurdefaultquery(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurDefaultQuery(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询默认查询", tags = {"项目" } ,notes = "查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurdefaultquery")
	public ResponseEntity<Page<ProjectDTO>> searchCurDefaultQuery(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurDefaultQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @ApiOperation(value = "解除关联产品", tags = {"项目" },  notes = "解除关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkproduct")
    public ResponseEntity<ProjectDTO> unlinkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'PUTOFF')")
    @ApiOperation(value = "延期", tags = {"项目" },  notes = "延期")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoff(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.putoff(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @ApiOperation(value = "关联产品", tags = {"项目" },  notes = "关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkproduct")
    public ResponseEntity<ProjectDTO> linkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "关联需求", tags = {"项目" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前计划项目", tags = {"项目" } ,notes = "获取当前计划项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurplanproject")
	public ResponseEntity<List<ProjectDTO>> fetchcurplanproject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurPlanProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前计划项目", tags = {"项目" } ,notes = "查询当前计划项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurplanproject")
	public ResponseEntity<Page<ProjectDTO>> searchCurPlanProject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurPlanProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目草稿", tags = {"项目" },  notes = "获取项目草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraft(ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "删除项目", tags = {"项目" },  notes = "删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("project_id") Long project_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'SUSPEND')")
    @ApiOperation(value = "挂起", tags = {"项目" },  notes = "挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspend(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.suspend(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


	@ApiOperation(value = "获取BugProject", tags = {"项目" } ,notes = "获取BugProject")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchbugproject")
	public ResponseEntity<List<ProjectDTO>> fetchbugproject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchBugProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询BugProject", tags = {"项目" } ,notes = "查询BugProject")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchbugproject")
	public ResponseEntity<Page<ProjectDTO>> searchBugProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchBugProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前项目", tags = {"项目" } ,notes = "获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurproduct")
	public ResponseEntity<List<ProjectDTO>> fetchcurproduct(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurProduct(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前项目", tags = {"项目" } ,notes = "查询当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurproduct")
	public ResponseEntity<Page<ProjectDTO>> searchCurProduct(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目团队", tags = {"项目" } ,notes = "获取项目团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchprojectteam")
	public ResponseEntity<List<ProjectDTO>> fetchprojectteam(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchProjectTeam(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目团队", tags = {"项目" } ,notes = "查询项目团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchprojectteam")
	public ResponseEntity<Page<ProjectDTO>> searchProjectTeam(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchProjectTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "更新项目", tags = {"项目" },  notes = "更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}")
    @Transactional
    public ResponseEntity<ProjectDTO> update(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
		Project domain  = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
		projectService.update(domain );
        if(!projectRuntime.test(project_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectDTO dto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "开始", tags = {"项目" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> start(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.start(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"项目" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> close(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.close(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目", tags = {"项目" },  notes = "新建项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects")
    @Transactional
    public ResponseEntity<ProjectDTO> create(@Validated @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
		projectService.create(domain);
        if(!projectRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的项目", tags = {"项目" } ,notes = "获取我的项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchmyproject")
	public ResponseEntity<List<ProjectDTO>> fetchmyproject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMyProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的项目", tags = {"项目" } ,notes = "查询我的项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchmyproject")
	public ResponseEntity<Page<ProjectDTO>> searchMyProject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMyProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取未完成项目", tags = {"项目" } ,notes = "获取未完成项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchundoneproject")
	public ResponseEntity<List<ProjectDTO>> fetchundoneproject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchUnDoneProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询未完成项目", tags = {"项目" } ,notes = "查询未完成项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchundoneproject")
	public ResponseEntity<Page<ProjectDTO>> searchUnDoneProject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchUnDoneProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"项目" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activate(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.activate(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前用户项目", tags = {"项目" } ,notes = "获取当前用户项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcuruser")
	public ResponseEntity<List<ProjectDTO>> fetchcuruser(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurUser(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前用户项目", tags = {"项目" } ,notes = "查询当前用户项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcuruser")
	public ResponseEntity<Page<ProjectDTO>> searchCurUser(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurUser(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/{action}")
    public ResponseEntity<ProjectDTO> dynamicCall(@PathVariable("project_id") Long project_id , @PathVariable("action") String action , @RequestBody ProjectDTO projectdto) {
        Project domain = projectService.dynamicCall(project_id, action, projectMapping.toDomain(projectdto));
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
}

