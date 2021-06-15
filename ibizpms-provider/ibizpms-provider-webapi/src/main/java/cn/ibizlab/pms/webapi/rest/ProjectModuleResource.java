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
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectModuleRuntime;

@Slf4j
@Api(tags = {"任务模块"})
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

    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'CREATE')")
    @ApiOperation(value = "新建任务模块", tags = {"任务模块" },  notes = "新建任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules")
    @Transactional
    public ResponseEntity<ProjectModuleDTO> create(@Validated @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
		projectmoduleService.create(domain);
        if(!projectmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', #projectmodule_id, 'READ')")
    @ApiOperation(value = "获取任务模块", tags = {"任务模块" },  notes = "获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> get(@PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(projectmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', #projectmodule_id, 'DELETE')")
    @ApiOperation(value = "删除任务模块", tags = {"任务模块" },  notes = "删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectmodule_id") Long projectmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除任务模块", tags = {"任务模块" },  notes = "批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', #projectmodule_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(projectmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'CREATE')")
    @ApiOperation(value = "检查任务模块", tags = {"任务模块" },  notes = "检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"任务模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fix(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.fix(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'CREATE')")
    @ApiOperation(value = "获取任务模块草稿", tags = {"任务模块" },  notes = "获取任务模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraft(ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', #projectmodule_id, 'DELETE')")
    @ApiOperation(value = "删除模块", tags = {"任务模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModule(@PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.removeModule(domain);
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'DENY')")
    @ApiOperation(value = "保存任务模块", tags = {"任务模块" },  notes = "保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> save(@RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        projectmoduleService.save(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'NONE')")
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

    @ApiOperation(value = "生成任务模块报表", tags = {"任务模块"}, notes = "生成任务模块报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProjectModuleSearchContext context, HttpServletResponse response) {
        try {
            projectmoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectmoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, projectmoduleRuntime);
        }
    }

    @ApiOperation(value = "打印任务模块", tags = {"任务模块"}, notes = "打印任务模块")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("projectmodule_ids") Set<Long> projectmodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = projectmoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProjectModule> domains = new ArrayList<>();
            for (Long projectmodule_id : projectmodule_ids) {
                domains.add(projectmoduleService.get( projectmodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProjectModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectmoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", projectmodule_ids, e.getMessage()), Errors.INTERNALERROR, projectmoduleRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/{action}")
    public ResponseEntity<ProjectModuleDTO> dynamicCall(@PathVariable("projectmodule_id") Long projectmodule_id , @PathVariable("action") String action , @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleService.dynamicCall(projectmodule_id, action, projectmoduleMapping.toDomain(projectmoduledto));
        projectmoduledto = projectmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目建立任务模块", tags = {"任务模块" },  notes = "根据项目建立任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules")
    public ResponseEntity<ProjectModuleDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
		projectmoduleService.create(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', #projectmodule_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" },  notes = "根据项目获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
        if (domain == null || !(project_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'DELETE', #projectmodule_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除任务模块", tags = {"任务模块" },  notes = "根据项目删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule testget = projectmoduleService.get(projectmodule_id);
        if (testget == null || !(project_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.remove(projectmodule_id));
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除任务模块", tags = {"任务模块" },  notes = "根据项目批量删除任务模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projectmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'UPDATE', #projectmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新任务模块", tags = {"任务模块" },  notes = "根据项目更新任务模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule testget = projectmoduleService.get(projectmodule_id);
        if (testget == null || !(project_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
		projectmoduleService.update(domain);
        ProjectModuleDTO dto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目检查任务模块", tags = {"任务模块" },  notes = "根据项目检查任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectmoduleService.checkKey(projectmoduleMapping.toDomain(projectmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'DENY')")
    @ApiOperation(value = "根据项目重建模块路径", tags = {"任务模块" },  notes = "根据项目重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/fix")
    public ResponseEntity<ProjectModuleDTO> fixByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.fix(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs(domain.getId());    
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目获取任务模块草稿", tags = {"任务模块" },  notes = "根据项目获取任务模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'DELETE', #projectmodule_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除模块", tags = {"任务模块" },  notes = "根据项目删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/removemodule")
    public ResponseEntity<ProjectModuleDTO> removeModuleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        domain.setId(projectmodule_id);
        domain = projectmoduleService.removeModule(domain) ;
        projectmoduledto = projectmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = projectmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        projectmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'DENY')")
    @ApiOperation(value = "根据项目保存任务模块", tags = {"任务模块" },  notes = "根据项目保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        projectmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(domain));
    }


    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取BYPATH", tags = {"任务模块" } ,notes = "根据项目获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchbypath")
	public ResponseEntity<List<ProjectModuleDTO>> fetchByPathByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchByPath(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务模块" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchdefault")
	public ResponseEntity<List<ProjectModuleDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchDefault(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取父模块", tags = {"任务模块" } ,notes = "根据项目获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchparentmodule")
	public ResponseEntity<List<ProjectModuleDTO>> fetchParentModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchParentModule(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRootByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取根模块_无分支", tags = {"任务模块" } ,notes = "根据项目获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRoot_NoBranchByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_NoBranch(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PROJECTMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取根模块", tags = {"任务模块" } ,notes = "根据项目获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchroot_task")
	public ResponseEntity<List<ProjectModuleDTO>> fetchRoot_TaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
        context.setN_root_eq(project_id);
        Page<ProjectModule> domains = projectmoduleService.searchRoot_Task(context) ;
        List<ProjectModuleDTO> list = projectmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PROJECTMODULE', 'NONE')")
	@ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" } ,notes = "根据项目获取任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/fetchtaskmodules")
	public ResponseEntity<List<ProjectModuleDTO>> fetchTaskModulesByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectModuleSearchContext context) {
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

