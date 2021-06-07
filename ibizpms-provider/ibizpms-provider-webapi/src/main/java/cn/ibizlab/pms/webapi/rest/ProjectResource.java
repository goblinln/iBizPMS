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

    @PreAuthorize("quickTest('ZT_PROJECT', 'CREATE')")
    @ApiOperation(value = "新建项目", tags = {"项目" },  notes = "新建项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects")
    @Transactional
    public ResponseEntity<ProjectDTO> create(@Validated @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
		projectService.create(domain);
        if(!projectRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'DELETE')")
    @ApiOperation(value = "删除项目", tags = {"项目" },  notes = "删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("project_id") Long project_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'DELETE')")
    @ApiOperation(value = "批量删除项目", tags = {"项目" },  notes = "批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> get(@PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'CREATE')")
    @ApiOperation(value = "获取项目草稿", tags = {"项目" },  notes = "获取项目草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraft(ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"项目" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activate(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.activate(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "批量解除关联需求", tags = {"项目" },  notes = "批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/batchunlinkstory")
    public ResponseEntity<ProjectDTO> batchUnlinkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.batchUnlinkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "取消置顶", tags = {"项目" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'CREATE')")
    @ApiOperation(value = "检查项目", tags = {"项目" },  notes = "检查项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectDTO projectdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectService.checkKey(projectMapping.toDomain(projectdto)));
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"项目" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> close(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.close(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "项目关联需求-按计划关联", tags = {"项目" },  notes = "项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/importplanstories")
    public ResponseEntity<ProjectDTO> importPlanStories(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.importPlanStories(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "关联产品", tags = {"项目" },  notes = "关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkproduct")
    public ResponseEntity<ProjectDTO> linkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "关联需求", tags = {"项目" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "团队管理", tags = {"项目" },  notes = "团队管理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/managemembers")
    public ResponseEntity<ProjectDTO> manageMembers(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.manageMembers(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'NONE')")
    @ApiOperation(value = "移动端项目计数器", tags = {"项目" },  notes = "移动端项目计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/mobprojectcount")
    public ResponseEntity<ProjectDTO> mobProjectCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.mobProjectCount(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'NONE')")
    @ApiOperation(value = "项目立项任务快速分组计数器", tags = {"项目" },  notes = "项目立项任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/pmseeprojectalltaskcount")
    public ResponseEntity<ProjectDTO> pmsEeProjectAllTaskCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.pmsEeProjectAllTaskCount(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'NONE')")
    @ApiOperation(value = "项目立项待办任务快速分组计数器", tags = {"项目" },  notes = "项目立项待办任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/pmseeprojecttodotaskcount")
    public ResponseEntity<ProjectDTO> pmsEeProjectTodoTaskCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.pmsEeProjectTodoTaskCount(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'NONE')")
    @ApiOperation(value = "项目任务快速分组计数器", tags = {"项目" },  notes = "项目任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskqcnt")
    public ResponseEntity<ProjectDTO> projectTaskQCnt(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTaskQCnt(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "置顶", tags = {"项目" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'PUTOFF')")
    @ApiOperation(value = "延期", tags = {"项目" },  notes = "延期")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoff(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.putoff(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "保存项目", tags = {"项目" },  notes = "保存项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/save")
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        projectService.save(domain);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'START')")
    @ApiOperation(value = "开始", tags = {"项目" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> start(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.start(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'SUSPEND')")
    @ApiOperation(value = "挂起", tags = {"项目" },  notes = "挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspend(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.suspend(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "移除成员", tags = {"项目" },  notes = "移除成员")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkmember")
    public ResponseEntity<ProjectDTO> unlinkMember(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkMember(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "解除关联产品", tags = {"项目" },  notes = "解除关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkproduct")
    public ResponseEntity<ProjectDTO> unlinkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "解除关联需求", tags = {"项目" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkstory")
    public ResponseEntity<ProjectDTO> unlinkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "排序", tags = {"项目" },  notes = "排序")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/updateorder")
    public ResponseEntity<ProjectDTO> updateOrder(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.updateOrder(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"项目" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchaccount")
	public ResponseEntity<List<ProjectDTO>> fetchaccount(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchAccount(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'NONE')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取当前用户项目（企业版）", tags = {"项目" } ,notes = "获取当前用户项目（企业版）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurusersa")
	public ResponseEntity<List<ProjectDTO>> fetchcurusersa(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurUserSa(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchdefault")
	public ResponseEntity<List<ProjectDTO>> fetchdefault(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchDefault(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"项目" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchesbulk")
	public ResponseEntity<List<ProjectDTO>> fetchesbulk(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchESBulk(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取参与项目(年度总结)", tags = {"项目" } ,notes = "获取参与项目(年度总结)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchinvolvedproject")
	public ResponseEntity<List<ProjectDTO>> fetchinvolvedproject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchInvolvedProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取参与项目完成需求任务bug", tags = {"项目" } ,notes = "获取参与项目完成需求任务bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchinvolvedproject_storytaskbug")
	public ResponseEntity<List<ProjectDTO>> fetchinvolvedproject_storytaskbug(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchInvolvedProject_StoryTaskBug(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"项目" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchmy")
	public ResponseEntity<List<ProjectDTO>> fetchmy(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMy(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取需求影响项目", tags = {"项目" } ,notes = "获取需求影响项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchstoryproject")
	public ResponseEntity<List<ProjectDTO>> fetchstoryproject(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchStoryProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/{action}")
    public ResponseEntity<ProjectDTO> dynamicCall(@PathVariable("project_id") Long project_id , @PathVariable("action") String action , @RequestBody ProjectDTO projectdto) {
        Project domain = projectService.dynamicCall(project_id, action, projectMapping.toDomain(projectdto));
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
}

