package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectRuntime;

@Slf4j
@Api(tags = {"项目" })
@RestController("StandardAPI-project")
@RequestMapping("")
public class ProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    public ProjectRuntime projectRuntime;

    @Autowired
    @Lazy
    public ProjectMapping projectMapping;

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'PUTOFF')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'ACTIVATE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CLOSE')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'START')")
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

    @PreAuthorize("@ProjectRuntime.quickTest('DENY')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> get(@PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'MANAGE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "删除项目", tags = {"项目" },  notes = "删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("project_id") Long project_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除项目", tags = {"项目" },  notes = "批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DENY')")
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


    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'MANAGE')")
    @ApiOperation(value = "解除关联需求", tags = {"项目" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkstory")
    public ResponseEntity<ProjectDTO> unlinkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目草稿", tags = {"项目" },  notes = "获取项目草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraft(ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'SUSPEND')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "取消置顶", tags = {"项目" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "置顶", tags = {"项目" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/{action}")
    public ResponseEntity<ProjectDTO> dynamicCall(@PathVariable("project_id") Long project_id , @PathVariable("action") String action , @RequestBody ProjectDTO projectdto) {
        Project domain = projectService.dynamicCall(project_id, action, projectMapping.toDomain(projectdto));
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'PUTOFF')")
    @ApiOperation(value = "根据系统用户延期", tags = {"项目" },  notes = "根据系统用户延期")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoffBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.putoff(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'ACTIVATE')")
    @ApiOperation(value = "根据系统用户激活", tags = {"项目" },  notes = "根据系统用户激活")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.activate(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CLOSE')")
    @ApiOperation(value = "根据系统用户关闭", tags = {"项目" },  notes = "根据系统用户关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> closeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.close(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取默认查询", tags = {"项目" } ,notes = "根据系统用户获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/fetchcurdefaultquery")
	public ResponseEntity<List<ProjectDTO>> fetchCurDefaultQueryBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchCurDefaultQuery(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'START')")
    @ApiOperation(value = "根据系统用户开始", tags = {"项目" },  notes = "根据系统用户开始")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> startBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.start(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户建立项目", tags = {"项目" },  notes = "根据系统用户建立项目")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects")
    public ResponseEntity<ProjectDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
		projectService.create(domain);
        if(!projectRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectDTO dto = projectMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户解除关联产品", tags = {"项目" },  notes = "根据系统用户解除关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/unlinkproduct")
    public ResponseEntity<ProjectDTO> unlinkProductBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.unlinkProduct(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户获取项目", tags = {"项目" },  notes = "根据系统用户获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}")
    public ResponseEntity<ProjectDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户关联需求", tags = {"项目" },  notes = "根据系统用户关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStoryBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.linkStory(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户删除项目", tags = {"项目" },  notes = "根据系统用户删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/{project_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户批量删除项目", tags = {"项目" },  notes = "根据系统用户批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户关联产品", tags = {"项目" },  notes = "根据系统用户关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/linkproduct")
    public ResponseEntity<ProjectDTO> linkProductBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.linkProduct(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"项目" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/fetchaccount")
	public ResponseEntity<List<ProjectDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchAccount(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户解除关联需求", tags = {"项目" },  notes = "根据系统用户解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/unlinkstory")
    public ResponseEntity<ProjectDTO> unlinkStoryBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.unlinkStory(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取项目草稿", tags = {"项目" },  notes = "根据系统用户获取项目草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新项目", tags = {"项目" },  notes = "根据系统用户更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}")
    public ResponseEntity<ProjectDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
		projectService.update(domain);
        if(!projectRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        ProjectDTO dto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'SUSPEND')")
    @ApiOperation(value = "根据系统用户挂起", tags = {"项目" },  notes = "根据系统用户挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspendBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.suspend(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户取消置顶", tags = {"项目" },  notes = "根据系统用户取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTopBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户置顶", tags = {"项目" },  notes = "根据系统用户置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTopBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.projectTop(domain) ;
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取当前项目", tags = {"项目" } ,notes = "根据系统用户获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/fetchcurproduct")
	public ResponseEntity<List<ProjectDTO>> fetchCurProductBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
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
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"项目" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/fetchmy")
	public ResponseEntity<List<ProjectDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMy(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

