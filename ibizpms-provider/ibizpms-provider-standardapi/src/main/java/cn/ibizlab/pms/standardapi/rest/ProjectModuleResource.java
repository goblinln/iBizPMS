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
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectModuleRuntime;

@Slf4j
@Api(tags = {"任务模块" })
@RestController("StandardAPI-projectmodule")
@RequestMapping("")
public class ProjectModuleResource {

    @Autowired
    public IProjectModuleService projectmoduleService;

    @Autowired
    public ProjectModuleRuntime projectmoduleRuntime;

    @Autowired
    @Lazy
    public ProjectModuleMapping projectmoduleMapping;


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取任务模块", tags = {"任务模块" },  notes = "根据项目获取任务模块")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}")
    public ResponseEntity<ProjectModuleDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id) {
        ProjectModule domain = projectmoduleService.get(projectmodule_id);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目获取任务模块草稿", tags = {"任务模块" },  notes = "根据项目获取任务模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/getdraft")
    public ResponseEntity<ProjectModuleDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectModuleDTO dto) {
        ProjectModule domain = projectmoduleMapping.toDomain(dto);
        domain.setRoot(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(projectmoduleService.getDraft(domain)));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存任务模块", tags = {"任务模块" },  notes = "根据项目保存任务模块")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/save")
    public ResponseEntity<ProjectModuleDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectModuleDTO projectmoduledto) {
        ProjectModule domain = projectmoduleMapping.toDomain(projectmoduledto);
        domain.setRoot(project_id);
        projectmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectmoduleMapping.toDto(domain));
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


}

