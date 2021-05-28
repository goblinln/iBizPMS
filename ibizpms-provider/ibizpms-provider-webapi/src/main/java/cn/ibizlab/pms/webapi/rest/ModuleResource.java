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
import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.service.IModuleService;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ModuleRuntime;

@Slf4j
@Api(tags = {"模块" })
@RestController("WebApi-module")
@RequestMapping("")
public class ModuleResource {

    @Autowired
    public IModuleService moduleService;

    @Autowired
    public ModuleRuntime moduleRuntime;

    @Autowired
    @Lazy
    public ModuleMapping moduleMapping;

    @PreAuthorize("@ModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建模块", tags = {"模块" },  notes = "新建模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules")
    @Transactional
    public ResponseEntity<ModuleDTO> create(@Validated @RequestBody ModuleDTO moduledto) {
        Module domain = moduleMapping.toDomain(moduledto);
		moduleService.create(domain);
        if(!moduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ModuleDTO dto = moduleMapping.toDto(domain);
        Map<String,Integer> opprivs = moduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ModuleRuntime.test(#module_id,'UPDATE')")
    @ApiOperation(value = "更新模块", tags = {"模块" },  notes = "更新模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/modules/{module_id}")
    @Transactional
    public ResponseEntity<ModuleDTO> update(@PathVariable("module_id") Long module_id, @RequestBody ModuleDTO moduledto) {
		Module domain  = moduleMapping.toDomain(moduledto);
        domain.setId(module_id);
		moduleService.update(domain );
        if(!moduleRuntime.test(module_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ModuleDTO dto = moduleMapping.toDto(domain);
        Map<String,Integer> opprivs = moduleRuntime.getOPPrivs(module_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ModuleRuntime.test(#module_id,'DELETE')")
    @ApiOperation(value = "删除模块", tags = {"模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/modules/{module_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("module_id") Long module_id) {
         return ResponseEntity.status(HttpStatus.OK).body(moduleService.remove(module_id));
    }


    @PreAuthorize("@ModuleRuntime.test(#module_id,'READ')")
    @ApiOperation(value = "获取模块", tags = {"模块" },  notes = "获取模块")
	@RequestMapping(method = RequestMethod.GET, value = "/modules/{module_id}")
    public ResponseEntity<ModuleDTO> get(@PathVariable("module_id") Long module_id) {
        Module domain = moduleService.get(module_id);
        ModuleDTO dto = moduleMapping.toDto(domain);
        Map<String,Integer> opprivs = moduleRuntime.getOPPrivs(module_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取模块草稿", tags = {"模块" },  notes = "获取模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/modules/getdraft")
    public ResponseEntity<ModuleDTO> getDraft(ModuleDTO dto) {
        Module domain = moduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(moduleMapping.toDto(moduleService.getDraft(domain)));
    }

    @PreAuthorize("@ModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查模块", tags = {"模块" },  notes = "检查模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ModuleDTO moduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(moduleService.checkKey(moduleMapping.toDomain(moduledto)));
    }

    @ApiOperation(value = "重建模块路径", tags = {"模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/{module_id}/fix")
    public ResponseEntity<ModuleDTO> fix(@PathVariable("module_id") Long module_id, @RequestBody ModuleDTO moduledto) {
        Module domain = moduleMapping.toDomain(moduledto);
        domain.setId(module_id);
        domain = moduleService.fix(domain);
        moduledto = moduleMapping.toDto(domain);
        Map<String,Integer> opprivs = moduleRuntime.getOPPrivs(domain.getId());
        moduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(moduledto);
    }


    @ApiOperation(value = "保存模块", tags = {"模块" },  notes = "保存模块")
	@RequestMapping(method = RequestMethod.POST, value = "/modules/save")
    public ResponseEntity<ModuleDTO> save(@RequestBody ModuleDTO moduledto) {
        Module domain = moduleMapping.toDomain(moduledto);
        moduleService.save(domain);
        ModuleDTO dto = moduleMapping.toDto(domain);
        Map<String,Integer> opprivs = moduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取BugModule", tags = {"模块" } ,notes = "获取BugModule")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchbugmodule")
	public ResponseEntity<List<ModuleDTO>> fetchbugmodule(@RequestBody ModuleSearchContext context) {
        Page<Module> domains = moduleService.searchBugModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"模块" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchbugmodulecodelist")
	public ResponseEntity<List<ModuleDTO>> fetchbugmodulecodelist(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchBugModuleCodeList(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchdefault")
	public ResponseEntity<List<ModuleDTO>> fetchdefault(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchDefault(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档目录", tags = {"模块" } ,notes = "获取文档目录")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchdocmodule")
	public ResponseEntity<List<ModuleDTO>> fetchdocmodule(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchDocModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品线", tags = {"模块" } ,notes = "获取产品线")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchline")
	public ResponseEntity<List<ModuleDTO>> fetchline(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchLine(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取需求模块", tags = {"模块" } ,notes = "获取需求模块")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchstorymodule")
	public ResponseEntity<List<ModuleDTO>> fetchstorymodule(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchStoryModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取任务模块", tags = {"模块" } ,notes = "获取任务模块")
    @RequestMapping(method= RequestMethod.POST , value="/modules/fetchtaskmodule")
	public ResponseEntity<List<ModuleDTO>> fetchtaskmodule(@RequestBody ModuleSearchContext context) {
        moduleRuntime.addAuthorityConditions(context,"READ");
        Page<Module> domains = moduleService.searchTaskModule(context) ;
        List<ModuleDTO> list = moduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/modules/{module_id}/{action}")
    public ResponseEntity<ModuleDTO> dynamicCall(@PathVariable("module_id") Long module_id , @PathVariable("action") String action , @RequestBody ModuleDTO moduledto) {
        Module domain = moduleService.dynamicCall(module_id, action, moduleMapping.toDomain(moduledto));
        moduledto = moduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(moduledto);
    }
}

