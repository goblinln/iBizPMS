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
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectModuleRuntime;

@Slf4j
@Api(tags = {"任务模块" })
@RestController("WebApi-projectmodule")
@RequestMapping("")
public class ProjectModuleResource {

    @Autowired
    public IProjectModuleService projectmoduleService;

    @Autowired
    public ProjectModuleRuntime projectmoduleRuntime;

    @Autowired
    @Lazy
    public ProjectModuleMapping projectmoduleMapping;

    @PreAuthorize("@ProjectModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务模块", tags = {"任务模块" },  notes = "新建任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules")
    @Transactional
    public ResponseEntity<ProjectModuleDTO> create(@Validated @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
		projectmoduleService.create(domain);
        if(!projectmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id, 'UPDATE')")
    @ApiOperation(value = "更新任务模块", tags = {"任务模块" },  notes = "更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}")
    @Transactional
    public ResponseEntity<ProjectModuleDTO> update(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
		ProjectModule domain  = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
		projectmoduleService.update(domain );
        if(!projectmoduleRuntime.test(projectmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(projectmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id, 'DELETE')")
    @ApiOperation(value = "删除任务模块", tags = {"任务模块" },  notes = "删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectmodule_id") Long projectmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除任务模块", tags = {"任务模块" },  notes = "批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id, 'READ')")
    @ApiOperation(value = "获取任务模块", tags = {"任务模块" },  notes = "获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> get(@PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(projectmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取任务模块草稿", tags = {"任务模块" },  notes = "获取任务模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraft(ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查任务模块", tags = {"任务模块" },  notes = "检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"任务模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fix(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.fix(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }


    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id, 'DELETE')")
    @ApiOperation(value = "删除模块", tags = {"任务模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.removeModule(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }


    @PreAuthorize("@ProjectModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存任务模块", tags = {"任务模块" },  notes = "保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> save(@RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        projectmoduleService.save(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取BYPATH", tags = {"任务模块" } ,notes = "获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchbypath")
	public ResponseEntity<List<ProjectModuleDTO>> fetchbypath(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchdefault")
	public ResponseEntity<List<ProjectModuleDTO>> fetchdefault(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取父模块", tags = {"任务模块" } ,notes = "获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchparentmodule")
	public ResponseEntity<List<ProjectModuleDTO>> fetchparentmodule(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根模块", tags = {"任务模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchroot")
	public ResponseEntity<List<ProjectModuleDTO>> fetchroot(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根模块_无分支", tags = {"任务模块" } ,notes = "获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProjectModuleDTO>> fetchroot_nobranch(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根模块", tags = {"任务模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchroot_task")
	public ResponseEntity<List<ProjectModuleDTO>> fetchroot_task(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取任务模块", tags = {"任务模块" } ,notes = "获取任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/fetchtaskmodules")
	public ResponseEntity<List<ProjectModuleDTO>> fetchtaskmodules(@RequestBody ProjectModuleSearchContext context) {
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/{action}")
    public ResponseEntity<ProjectModuleDTO> dynamicCall(@PathVariable("projectmodule_id") Long projectmodule_id , @PathVariable("action") String action , @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleService.dynamicCall(projectmodule_id, action, projectmoduleMapping.toDomain(projectmoduledto));
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目建立任务模块", tags = {"任务模块" },  notes = "根据项目建立任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules")
    public ResponseEntity<ProjectModuleDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
		projectmoduleService.create(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新任务模块", tags = {"任务模块" },  notes = "根据项目更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
		projectmoduleService.update(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除任务模块", tags = {"任务模块" },  notes = "根据项目删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据项目批量删除任务模块", tags = {"任务模块" },  notes = "根据项目批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" },  notes = "根据项目获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目获取任务模块草稿", tags = {"任务模块" },  notes = "根据项目获取任务模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目检查任务模块", tags = {"任务模块" },  notes = "根据项目检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目重建模块路径", tags = {"任务模块" },  notes = "根据项目重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fixByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.fix(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除模块", tags = {"任务模块" },  notes = "根据项目删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModuleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.removeModule(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("@ProjectModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存任务模块", tags = {"任务模块" },  notes = "根据项目保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        projectmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取BYPATH", tags = {"任务模块" } ,notes = "根据项目获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchbypath")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleByPathByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务模块" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchdefault")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取父模块", tags = {"任务模块" } ,notes = "根据项目获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchparentmodule")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleParentModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRootByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取根模块_无分支", tags = {"任务模块" } ,notes = "根据项目获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRoot_NoBranchByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot_task")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRoot_TaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectModuleRuntime.quickTest('NONE')")
	@ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" } ,notes = "根据项目获取任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchtaskmodules")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleTaskModulesByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

