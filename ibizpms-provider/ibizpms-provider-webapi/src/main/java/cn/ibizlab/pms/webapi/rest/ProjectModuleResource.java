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
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.model.impl.ProjectModuleModelImpl;

@Slf4j
@Api(tags = {"任务模块" })
@RestController("WebApi-projectmodule")
@RequestMapping("")
public class ProjectModuleResource {

    @Autowired
    public IProjectModuleService projectmoduleService;

    @Autowired
    public ProjectModuleModelImpl projectmoduleModelImpl;

    @Autowired
    @Lazy
    public ProjectModuleMapping projectmoduleMapping;

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "新建任务模块", tags = {"任务模块" },  notes = "新建任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules")
    public ResponseEntity<ProjectModuleDTO> create(@Validated @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
		projectmoduleService.create(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "批量新建任务模块", tags = {"任务模块" },  notes = "批量新建任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        projectmoduleService.createBatch(projectmoduleMapping.toDomain(projectmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "更新任务模块", tags = {"任务模块" },  notes = "更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> update(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
		ProjectModule domain  = projectmoduleMapping.toDomain(projectmoduledto);
        domain .setId(projectmodule_id);
		projectmoduleService.update(domain );
		ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "批量更新任务模块", tags = {"任务模块" },  notes = "批量更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        projectmoduleService.updateBatch(projectmoduleMapping.toDomain(projectmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "删除任务模块", tags = {"任务模块" },  notes = "删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectmodule_id") Long projectmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "批量删除任务模块", tags = {"任务模块" },  notes = "批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "获取任务模块", tags = {"任务模块" },  notes = "获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> get(@PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务模块草稿", tags = {"任务模块" },  notes = "获取任务模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraft(ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @ApiOperation(value = "检查任务模块", tags = {"任务模块" },  notes = "检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @ApiOperation(value = "重建模块路径", tags = {"任务模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fix(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.fix(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "删除模块", tags = {"任务模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.removeModule(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @ApiOperation(value = "保存任务模块", tags = {"任务模块" },  notes = "保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> save(@RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        projectmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存任务模块", tags = {"任务模块" },  notes = "批量保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        projectmoduleService.saveBatch(projectmoduleMapping.toDomain(projectmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取BYPATH", tags = {"任务模块" } ,notes = "获取BYPATH")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchbypath")
	public ResponseEntity<List<ProjectModuleDTO>> fetchByPath(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询BYPATH", tags = {"任务模块" } ,notes = "查询BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchbypath")
	public ResponseEntity<Page<ProjectModuleDTO>> searchByPath(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"任务模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchdefault")
	public ResponseEntity<List<ProjectModuleDTO>> fetchDefault(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"任务模块" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchdefault")
	public ResponseEntity<Page<ProjectModuleDTO>> searchDefault(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取父模块", tags = {"任务模块" } ,notes = "获取父模块")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchparentmodule")
	public ResponseEntity<List<ProjectModuleDTO>> fetchParentModule(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询父模块", tags = {"任务模块" } ,notes = "查询父模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchparentmodule")
	public ResponseEntity<Page<ProjectModuleDTO>> searchParentModule(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取根模块", tags = {"任务模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchroot")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRoot(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询根模块", tags = {"任务模块" } ,notes = "查询根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchroot")
	public ResponseEntity<Page<ProjectModuleDTO>> searchRoot(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取根模块_无分支", tags = {"任务模块" } ,notes = "获取根模块_无分支")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRoot_NoBranch(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询根模块_无分支", tags = {"任务模块" } ,notes = "查询根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchroot_nobranch")
	public ResponseEntity<Page<ProjectModuleDTO>> searchRoot_NoBranch(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取根模块", tags = {"任务模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchroot_task")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRoot_Task(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询根模块", tags = {"任务模块" } ,notes = "查询根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchroot_task")
	public ResponseEntity<Page<ProjectModuleDTO>> searchRoot_Task(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取任务模块", tags = {"任务模块" } ,notes = "获取任务模块")
    @RequestMapping(method= RequestMethod.GET , value="/projectmodules/fetchtaskmodules")
	public ResponseEntity<List<ProjectModuleDTO>> fetchTaskModules(ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询任务模块", tags = {"任务模块" } ,notes = "查询任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/searchtaskmodules")
	public ResponseEntity<Page<ProjectModuleDTO>> searchTaskModules(@RequestBody ProjectModuleSearchContext context) {
        projectmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/{action}")
    public ResponseEntity<ProjectModuleDTO> dynamicCall(@PathVariable("projectmodule_id") Long projectmodule_id , @PathVariable("action") String action , @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleService.dynamicCall(projectmodule_id, action, projectmoduleMapping.toDomain(projectmoduledto));
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @ApiOperation(value = "根据项目建立任务模块", tags = {"任务模块" },  notes = "根据项目建立任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules")
    public ResponseEntity<ProjectModuleDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
		projectmoduleService.create(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目批量建立任务模块", tags = {"任务模块" },  notes = "根据项目批量建立任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        List<ProjectModule> domainlist=projectmoduleMapping.toDomain(projectmoduledtos);
        for(ProjectModule domain:domainlist){
            domain.setRoot(project_id);
        }
        projectmoduleService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

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

    @ApiOperation(value = "根据项目批量更新任务模块", tags = {"任务模块" },  notes = "根据项目批量更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        List<ProjectModule> domainlist=projectmoduleMapping.toDomain(projectmoduledtos);
        for(ProjectModule domain:domainlist){
            domain.setRoot(project_id);
        }
        projectmoduleService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目删除任务模块", tags = {"任务模块" },  notes = "根据项目删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @ApiOperation(value = "根据项目批量删除任务模块", tags = {"任务模块" },  notes = "根据项目批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" },  notes = "根据项目获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取任务模块草稿", tags = {"任务模块" },  notes = "根据项目获取任务模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目检查任务模块", tags = {"任务模块" },  notes = "根据项目检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @ApiOperation(value = "根据项目任务模块", tags = {"任务模块" },  notes = "根据项目任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fixByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain = projectmoduleService.fix(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }
    @ApiOperation(value = "根据项目任务模块", tags = {"任务模块" },  notes = "根据项目任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModuleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain = projectmoduleService.removeModule(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }
    @ApiOperation(value = "根据项目保存任务模块", tags = {"任务模块" },  notes = "根据项目保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        projectmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目批量保存任务模块", tags = {"任务模块" },  notes = "根据项目批量保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectModuleDTO> projectmoduledtos) {
        List<ProjectModule> domainlist=projectmoduleMapping.toDomain(projectmoduledtos);
        for(ProjectModule domain:domainlist){
             domain.setRoot(project_id);
        }
        projectmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "根据项目获取BYPATH", tags = {"任务模块" } ,notes = "根据项目获取BYPATH")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchbypath")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleByPathByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询BYPATH", tags = {"任务模块" } ,notes = "根据项目查询BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchbypath")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleByPathByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务模块" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchdefault")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleDefaultByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"任务模块" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchdefault")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取父模块", tags = {"任务模块" } ,notes = "根据项目获取父模块")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchparentmodule")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleParentModuleByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询父模块", tags = {"任务模块" } ,notes = "根据项目查询父模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchparentmodule")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleParentModuleByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchroot")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRootByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询根模块", tags = {"任务模块" } ,notes = "根据项目查询根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchroot")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleRootByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取根模块_无分支", tags = {"任务模块" } ,notes = "根据项目获取根模块_无分支")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRoot_NoBranchByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询根模块_无分支", tags = {"任务模块" } ,notes = "根据项目查询根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchroot_nobranch")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleRoot_NoBranchByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchroot_task")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleRoot_TaskByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询根模块", tags = {"任务模块" } ,notes = "根据项目查询根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchroot_task")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleRoot_TaskByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" } ,notes = "根据项目获取任务模块")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projectmodules/fetchtaskmodules")
	public ResponseEntity<List<ProjectModuleDTO>> fetchProjectModuleTaskModulesByProject(@PathVariable("project_id") Long project_id,ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询任务模块", tags = {"任务模块" } ,notes = "根据项目查询任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/searchtaskmodules")
	public ResponseEntity<Page<ProjectModuleDTO>> searchProjectModuleTaskModulesByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchTaskModules(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

