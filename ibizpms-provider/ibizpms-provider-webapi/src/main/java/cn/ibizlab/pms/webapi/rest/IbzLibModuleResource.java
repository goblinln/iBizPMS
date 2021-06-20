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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibModuleRuntime;

@Slf4j
@Api(tags = {"用例库模块"})
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

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "新建用例库模块", tags = {"用例库模块" },  notes = "新建用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules")
    @Transactional
    public ResponseEntity<IbzLibModuleDTO> create(@Validated @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
		ibzlibmoduleService.create(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'READ')")
    @ApiOperation(value = "获取用例库模块", tags = {"用例库模块" },  notes = "获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> get(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(ibzlibmodule_id);
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(ibzlibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'DELETE')")
    @ApiOperation(value = "删除用例库模块", tags = {"用例库模块" },  notes = "删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(ibzlibmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除用例库模块", tags = {"用例库模块" },  notes = "批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(ibzlibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "检查用例库模块", tags = {"用例库模块" },  notes = "检查用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.checkKey(ibzlibmoduleMapping.toDomain(ibzlibmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "获取用例库模块草稿", tags = {"用例库模块" },  notes = "获取用例库模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/getdraft")
    public ResponseEntity<IbzLibModuleDTO> getDraft(IbzLibModuleDTO dto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DENY')")
    @ApiOperation(value = "批量保存用例库模块", tags = {"用例库模块" },  notes = "批量保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzLibModuleDTO> ibzlibmoduledtos) {
        ibzlibmoduleService.saveBatch(ibzlibmoduleMapping.toDomain(ibzlibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/fetchdefault")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchdefault(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'READ')")
	@ApiOperation(value = "获取无枝叶", tags = {"用例库模块" } ,notes = "获取无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibmodules/fetchroot_nobranch")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchroot_nobranch(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
        List<IbzLibModuleDTO> list = ibzlibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成用例库模块报表", tags = {"用例库模块"}, notes = "生成用例库模块报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzLibModuleSearchContext context, HttpServletResponse response) {
        try {
            ibzlibmoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzlibmoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzlibmoduleRuntime);
        }
    }

    @ApiOperation(value = "打印用例库模块", tags = {"用例库模块"}, notes = "打印用例库模块")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibmodules/{ibzlibmodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzlibmodule_ids") Set<Long> ibzlibmodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzlibmoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzLibModule> domains = new ArrayList<>();
            for (Long ibzlibmodule_id : ibzlibmodule_ids) {
                domains.add(ibzlibmoduleService.get( ibzlibmodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzLibModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzlibmoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzlibmodule_ids, e.getMessage()), Errors.INTERNALERROR, ibzlibmoduleRuntime);
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


    @PreAuthorize("quickTest('IBZ_LIBMODULE','CREATE')")
    @ApiOperation(value = "根据用例库建立用例库模块", tags = {"用例库模块" },  notes = "根据用例库建立用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules")
    public ResponseEntity<IbzLibModuleDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setRoot(ibzlib_id);
		ibzlibmoduleService.create(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'READ')")
    @ApiOperation(value = "根据用例库获取用例库模块", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(ibzlibmodule_id);
        if (domain == null || !(ibzlib_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据用例库删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id) {
        IbzLibModule testget = ibzlibmoduleService.get(ibzlibmodule_id);
        if (testget == null || !(ibzlib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(ibzlibmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE','DELETE')")
    @ApiOperation(value = "根据用例库批量删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLib(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #ibzlibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据用例库更新用例库模块", tags = {"用例库模块" },  notes = "根据用例库更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/{ibzlibmodule_id}")
    public ResponseEntity<IbzLibModuleDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzlibmodule_id") Long ibzlibmodule_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        IbzLibModule testget = ibzlibmoduleService.get(ibzlibmodule_id);
        if (testget == null || !(ibzlib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(ibzlibmoduledto);
        domain.setRoot(ibzlib_id);
        domain.setId(ibzlibmodule_id);
		ibzlibmoduleService.update(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        IbzLibModuleDTO dto = ibzlibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE','CREATE')")
    @ApiOperation(value = "根据用例库检查用例库模块", tags = {"用例库模块" },  notes = "根据用例库检查用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibModuleDTO ibzlibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.checkKey(ibzlibmoduleMapping.toDomain(ibzlibmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE','CREATE')")
    @ApiOperation(value = "根据用例库获取用例库模块草稿", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzlibmodules/getdraft")
    public ResponseEntity<IbzLibModuleDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, IbzLibModuleDTO dto) {
        IbzLibModule domain = ibzlibmoduleMapping.toDomain(dto);
        domain.setRoot(ibzlib_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DENY')")
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

    @PreAuthorize("quickTest('IBZ_LIBMODULE','READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"用例库模块" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/fetchdefault")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzLibModuleSearchContext context) {
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
    @PreAuthorize("quickTest('IBZ_LIBMODULE','READ')")
	@ApiOperation(value = "根据用例库获取无枝叶", tags = {"用例库模块" } ,notes = "根据用例库获取无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzlibmodules/fetchroot_nobranch")
	public ResponseEntity<List<IbzLibModuleDTO>> fetchRoot_NoBranchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzLibModuleSearchContext context) {
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
}

