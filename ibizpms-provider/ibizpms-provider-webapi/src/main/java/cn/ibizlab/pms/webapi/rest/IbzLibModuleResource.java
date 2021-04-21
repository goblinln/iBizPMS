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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibModuleRuntime;

@Slf4j
@Api(tags = {"用例库模块" })
@RestController("WebApi-ibzlibmodule")
@RequestMapping("")
public class IbzLibModuleResource {

    @Autowired
    public IIbzLibModuleService ibzlibmoduleService;

    @Autowired
    public IbzLibModuleRuntime ibzlibmoduleRuntime;

    @Autowired
    @Lazy
    public IbzLibModuleMapping ibzlibmoduleMapping;

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建用例库模块", tags = {"用例库模块" },  notes = "新建用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules")
    @Transactional
    public ResponseEntity<IbzLibModuleDTO> create(@Validated @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
		ibzlibmoduleService.create(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建用例库模块", tags = {"用例库模块" },  notes = "批量新建用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        ibzlibmoduleService.createBatch(ibzlibmoduleMapping.toDomain(ibzlibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.test(#ibzlibmodule_id,'UPDATE')")
    @ApiOperation(value = "更新用例库模块", tags = {"用例库模块" },  notes = "更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibmodules/{ibzlibmodule_id}")
    @Transactional
    public ResponseEntity<IbzLibModuleDTO> update(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
		IbzLibModule domain  = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setId(ibzlibmodule_id);
		ibzlibmoduleService.update(domain );
        if(!ibzlibmoduleRuntime.test(ibzlibmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新用例库模块", tags = {"用例库模块" },  notes = "批量更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibmodules/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        ibzlibmoduleService.updateBatch(ibzlibmoduleMapping.toDomain(ibzlibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.test(#ibzlibmodule_id,'DELETE')")
    @ApiOperation(value = "删除用例库模块", tags = {"用例库模块" },  notes = "删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(ibzlibmodule_id));
    }

    @PreAuthorize("@IbzLibModuleRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除用例库模块", tags = {"用例库模块" },  notes = "批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.test(#ibzlibmodule_id,'READ')")
    @ApiOperation(value = "获取用例库模块", tags = {"用例库模块" },  notes = "获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> get(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(ibzlibmodule_id);
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用例库模块草稿", tags = {"用例库模块" },  notes = "获取用例库模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/getdraft")
    public ResponseEntity<IbzLibModuleDTO> getDraft(IbzLibModuleDTO dto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用例库模块", tags = {"用例库模块" },  notes = "检查用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.checkKey(ibzlibmoduleMapping.toDomain(ibzlibmoduledto)));
    }

    @ApiOperation(value = "保存用例库模块", tags = {"用例库模块" },  notes = "保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/save")
    public ResponseEntity<IbzLibModuleDTO> save(@RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        ibzlibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存用例库模块", tags = {"用例库模块" },  notes = "批量保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        ibzlibmoduleService.saveBatch(ibzlibmoduleMapping.toDomain(ibzlibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/fetchdefault")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchDefault(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"用例库模块" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/searchdefault")
	public ResponseEntity<Page<IbzLibModuleDTO>> searchDefault(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取无枝叶", tags = {"用例库模块" } ,notes = "获取无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/fetchroot_nobranch")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchRoot_NoBranch(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "查询无枝叶", tags = {"用例库模块" } ,notes = "查询无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/searchroot_nobranch")
	public ResponseEntity<Page<IbzLibModuleDTO>> searchRoot_NoBranch(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/{ibzlibmodule_id}/{action}")
    public ResponseEntity<IbzLibModuleDTO> dynamicCall(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id , @PathVariable("action") String action , @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleService.dynamicCall(ibzlibmodule_id, action, ibzlibmoduleMapping.toDomain(ibzlibmoduledto));
        ibzlibmoduledto = ibzlibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduledto);
    }
    @PreAuthorize("@IbzLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据用例库建立用例库模块", tags = {"用例库模块" },  notes = "根据用例库建立用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules")
    public ResponseEntity<IbzLibModuleDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setRoot(ibzlib_id);
		ibzlibmoduleService.create(domain);
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据用例库批量建立用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量建立用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/batch")
    public ResponseEntity<Boolean> createBatchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        List<IbzLibModule> domainlist=ibzlibmoduleMapping.toDomain(ibzlibmoduledtos);
        for(IbzLibModule domain:domainlist){
            domain.setRoot(ibzlib_id);
        }
        ibzlibmoduleService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据用例库更新用例库模块", tags = {"用例库模块" },  notes = "根据用例库更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setRoot(ibzlib_id);
        domain.setId(ibzlibmodule_id);
		ibzlibmoduleService.update(domain);
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据用例库批量更新用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/batch")
    public ResponseEntity<Boolean> updateBatchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        List<IbzLibModule> domainlist=ibzlibmoduleMapping.toDomain(ibzlibmoduledtos);
        for(IbzLibModule domain:domainlist){
            domain.setRoot(ibzlib_id);
        }
        ibzlibmoduleService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据用例库删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(ibzlibmodule_id));
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据用例库批量删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLib(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
    @ApiOperation(value = "根据用例库获取用例库模块", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(ibzlibmodule_id);
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据用例库获取用例库模块草稿", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/getdraft")
    public ResponseEntity<IbzLibModuleDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, IbzLibModuleDTO dto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(dto);
        domain.setRoot(ibzlib_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }

    @ApiOperation(value = "根据用例库检查用例库模块", tags = {"用例库模块" },  notes = "根据用例库检查用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.checkKey(ibzlibmoduleMapping.toDomain(ibzlibmoduledto)));
    }

    @ApiOperation(value = "根据用例库保存用例库模块", tags = {"用例库模块" },  notes = "根据用例库保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/save")
    public ResponseEntity<IbzLibModuleDTO> saveByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setRoot(ibzlib_id);
        ibzlibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "根据用例库批量保存用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        List<IbzLibModule> domainlist=ibzlibmoduleMapping.toDomain(ibzlibmoduledtos);
        for(IbzLibModule domain:domainlist){
             domain.setRoot(ibzlib_id);
        }
        ibzlibmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"用例库模块" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/fetchdefault")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchIbzLibModuleDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzLibModuleSearchContext context) {
        context.setN_root_eq(ibzlib_id);
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "根据用例库查询DEFAULT", tags = {"用例库模块" } ,notes = "根据用例库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/searchdefault")
	public ResponseEntity<Page<IbzLibModuleDTO>> searchIbzLibModuleDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleSearchContext context) {
        context.setN_root_eq(ibzlib_id);
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "根据用例库获取无枝叶", tags = {"用例库模块" } ,notes = "根据用例库获取无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/fetchroot_nobranch")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchIbzLibModuleRoot_NoBranchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzLibModuleSearchContext context) {
        context.setN_root_eq(ibzlib_id);
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "根据用例库查询无枝叶", tags = {"用例库模块" } ,notes = "根据用例库查询无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/searchroot_nobranch")
	public ResponseEntity<Page<IbzLibModuleDTO>> searchIbzLibModuleRoot_NoBranchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleSearchContext context) {
        context.setN_root_eq(ibzlib_id);
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

