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
import org.springframework.transaction.annotation.Transactional;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.service.IModuleService;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"模块" })
@RestController("WebApi-module")
@RequestMapping("")
public class ModuleResource {

    @Autowired
    public IModuleService moduleService;

    @Autowired
    @Lazy
    public ModuleMapping moduleMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Create-all')")
    @ApiOperation(value = "新建模块", tags = {"模块" },  notes = "新建模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules")
    @Transactional
    public ResponseEntity<ModuleDTO> create(@RequestBody ModuleDTO moduledto) {
        Module domain = moduleMapping.toDomain(moduledto);
		moduleService.create(domain);
        ModuleDTO dto = moduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Create-all')")
    @ApiOperation(value = "批量新建模块", tags = {"模块" },  notes = "批量新建模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ModuleDTO> moduledtos) {
        moduleService.createBatch(moduleMapping.toDomain(moduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Update-all')")
    @ApiOperation(value = "更新模块", tags = {"模块" },  notes = "更新模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/modules/{module_id}")
    @Transactional
    public ResponseEntity<ModuleDTO> update(@PathVariable("module_id") BigInteger module_id, @RequestBody ModuleDTO moduledto) {
		Module domain  = moduleMapping.toDomain(moduledto);
        domain .setId(module_id);
		moduleService.update(domain );
		ModuleDTO dto = moduleMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Update-all')")
    @ApiOperation(value = "批量更新模块", tags = {"模块" },  notes = "批量更新模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/modules/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ModuleDTO> moduledtos) {
        moduleService.updateBatch(moduleMapping.toDomain(moduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Remove-all')")
    @ApiOperation(value = "删除模块", tags = {"模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/modules/{module_id}")
    @Transactional
    public ResponseEntity<Boolean> remove(@PathVariable("module_id") BigInteger module_id) {
         return ResponseEntity.status(HttpStatus.OK).body(moduleService.remove(module_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Remove-all')")
    @ApiOperation(value = "批量删除模块", tags = {"模块" },  notes = "批量删除模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/modules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<BigInteger> ids) {
        moduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Get-all')")
    @ApiOperation(value = "获取模块", tags = {"模块" },  notes = "获取模块")
	@RequestMapping(method = RequestMethod.GET, value = "/modules/{module_id}")
    public ResponseEntity<ModuleDTO> get(@PathVariable("module_id") BigInteger module_id) {
        Module domain = moduleService.get(module_id);
        ModuleDTO dto = moduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取模块草稿", tags = {"模块" },  notes = "获取模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/modules/getdraft")
    public ResponseEntity<ModuleDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(moduleMapping.toDto(moduleService.getDraft(new Module())));
    }

    @ApiOperation(value = "检查模块", tags = {"模块" },  notes = "检查模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ModuleDTO moduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(moduleService.checkKey(moduleMapping.toDomain(moduledto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Fix-all')")
    @ApiOperation(value = "重建模块路径", tags = {"模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/{module_id}/fix")
    @Transactional
    public ResponseEntity<ModuleDTO> fix(@PathVariable("module_id") BigInteger module_id, @RequestBody ModuleDTO moduledto) {
        Module domain = moduleMapping.toDomain(moduledto);
        domain.setId(module_id);
        domain = moduleService.fix(domain);
        moduledto = moduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(moduledto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Save-all')")
    @ApiOperation(value = "保存模块", tags = {"模块" },  notes = "保存模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/save")
    public ResponseEntity<Boolean> save(@RequestBody ModuleDTO moduledto) {
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleMapping.toDomain(moduledto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-Save-all')")
    @ApiOperation(value = "批量保存模块", tags = {"模块" },  notes = "批量保存模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ModuleDTO> moduledtos) {
        moduleService.saveBatch(moduleMapping.toDomain(moduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/modules/fetchdefault")
	public ResponseEntity<List<ModuleDTO>> fetchDefault(ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchDefault(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"模块" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/modules/searchdefault")
	public ResponseEntity<Page<ModuleDTO>> searchDefault(@RequestBody ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(moduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchDocModule-all')")
	@ApiOperation(value = "获取文档目录", tags = {"模块" } ,notes = "获取文档目录")
    @RequestMapping(method= RequestMethod.GET , value="/modules/fetchdocmodule")
	public ResponseEntity<List<ModuleDTO>> fetchDocModule(ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchDocModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchDocModule-all')")
	@ApiOperation(value = "查询文档目录", tags = {"模块" } ,notes = "查询文档目录")
    @RequestMapping(method= RequestMethod.POST , value="/modules/searchdocmodule")
	public ResponseEntity<Page<ModuleDTO>> searchDocModule(@RequestBody ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchDocModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(moduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchLine-all')")
	@ApiOperation(value = "获取产品线", tags = {"模块" } ,notes = "获取产品线")
    @RequestMapping(method= RequestMethod.GET , value="/modules/fetchline")
	public ResponseEntity<List<ModuleDTO>> fetchLine(ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchLine(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchLine-all')")
	@ApiOperation(value = "查询产品线", tags = {"模块" } ,notes = "查询产品线")
    @RequestMapping(method= RequestMethod.POST , value="/modules/searchline")
	public ResponseEntity<Page<ModuleDTO>> searchLine(@RequestBody ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchLine(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(moduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchStoryModule-all')")
	@ApiOperation(value = "获取需求模块", tags = {"模块" } ,notes = "获取需求模块")
    @RequestMapping(method= RequestMethod.GET , value="/modules/fetchstorymodule")
	public ResponseEntity<List<ModuleDTO>> fetchStoryModule(ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchStoryModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Module-searchStoryModule-all')")
	@ApiOperation(value = "查询需求模块", tags = {"模块" } ,notes = "查询需求模块")
    @RequestMapping(method= RequestMethod.POST , value="/modules/searchstorymodule")
	public ResponseEntity<Page<ModuleDTO>> searchStoryModule(@RequestBody ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchStoryModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(moduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

