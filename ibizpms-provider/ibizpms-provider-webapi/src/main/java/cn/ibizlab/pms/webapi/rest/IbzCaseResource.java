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
import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.core.ibiz.service.IIbzCaseService;
import cn.ibizlab.pms.core.ibiz.filter.IbzCaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzCaseRuntime;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibCaseStepsSearchContext;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepsService;

@Slf4j
@Api(tags = {"测试用例"})
@RestController("WebApi-ibzcase")
@RequestMapping("")
public class IbzCaseResource {

    @Autowired
    public IIbzCaseService ibzcaseService;

    @Autowired
    public IbzCaseRuntime ibzcaseRuntime;

    @Autowired
    @Lazy
    public IbzCaseMapping ibzcaseMapping;

    @Autowired
    private IIbzLibCaseStepsService ibzlibcasestepsService;
    @PreAuthorize("quickTest('IBZ_CASE', 'CREATE')")
    @ApiOperation(value = "新建测试用例", tags = {"测试用例" },  notes = "新建测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases")
    @Transactional
    public ResponseEntity<IbzCaseDTO> create(@Validated @RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase domain = ibzcaseMapping.toDomain(ibzcasedto);
		ibzcaseService.create(domain);
        if(!ibzcaseRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'READ')")
    @ApiOperation(value = "获取测试用例", tags = {"测试用例" },  notes = "获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_id}")
    public ResponseEntity<IbzCaseDTO> get(@PathVariable("ibzcase_id") Long ibzcase_id) {
        IbzCase domain = ibzcaseService.get(ibzcase_id);
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(ibzcase_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'DELETE')")
    @ApiOperation(value = "删除测试用例", tags = {"测试用例" },  notes = "删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/{ibzcase_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzcase_id") Long ibzcase_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzcaseService.remove(ibzcase_id));
    }

    @PreAuthorize("quickTest('IBZ_CASE', 'DELETE')")
    @ApiOperation(value = "批量删除测试用例", tags = {"测试用例" },  notes = "批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzcaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'UPDATE')")
    @ApiOperation(value = "更新测试用例", tags = {"测试用例" },  notes = "更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcases/{ibzcase_id}")
    @Transactional
    public ResponseEntity<IbzCaseDTO> update(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzCaseDTO ibzcasedto) {
		IbzCase domain  = ibzcaseMapping.toDomain(ibzcasedto);
        domain.setId(ibzcase_id);
		ibzcaseService.update(domain );
        if(!ibzcaseRuntime.test(ibzcase_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(ibzcase_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_CASE', 'CREATE')")
    @ApiOperation(value = "检查测试用例", tags = {"测试用例" },  notes = "检查测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzCaseDTO ibzcasedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseService.checkKey(ibzcaseMapping.toDomain(ibzcasedto)));
    }

    @PreAuthorize("quickTest('IBZ_CASE', 'CREATE')")
    @ApiOperation(value = "获取测试用例草稿", tags = {"测试用例" },  notes = "获取测试用例草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcases/getdraft")
    public ResponseEntity<IbzCaseDTO> getDraft(IbzCaseDTO dto) {
        IbzCase domain = ibzcaseMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseMapping.toDto(ibzcaseService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_CASE', 'DENY')")
    @ApiOperation(value = "保存测试用例", tags = {"测试用例" },  notes = "保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/save")
    public ResponseEntity<IbzCaseDTO> save(@RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase domain = ibzcaseMapping.toDomain(ibzcasedto);
        ibzcaseService.save(domain);
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_CASE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试用例" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcases/fetchdefault")
	public ResponseEntity<List<IbzCaseDTO>> fetchdefault(@RequestBody IbzCaseSearchContext context) {
        ibzcaseRuntime.addAuthorityConditions(context,"READ");
        Page<IbzCase> domains = ibzcaseService.searchDefault(context) ;
        List<IbzCaseDTO> list = ibzcaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试用例报表", tags = {"测试用例"}, notes = "生成测试用例报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzcases/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzCaseSearchContext context, HttpServletResponse response) {
        try {
            ibzcaseRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzcaseRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzcaseRuntime);
        }
    }

    @ApiOperation(value = "打印测试用例", tags = {"测试用例"}, notes = "打印测试用例")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzcase_ids") Set<Long> ibzcase_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzcaseRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzCase> domains = new ArrayList<>();
            for (Long ibzcase_id : ibzcase_ids) {
                domains.add(ibzcaseService.get( ibzcase_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzCase[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzcaseRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzcase_ids, e.getMessage()), Errors.INTERNALERROR, ibzcaseRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/{action}")
    public ResponseEntity<IbzCaseDTO> dynamicCall(@PathVariable("ibzcase_id") Long ibzcase_id , @PathVariable("action") String action , @RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase domain = ibzcaseService.dynamicCall(ibzcase_id, action, ibzcaseMapping.toDomain(ibzcasedto));
        ibzcasedto = ibzcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasedto);
    }

    @PreAuthorize("quickTest('IBZ_CASE','CREATE')")
    @ApiOperation(value = "根据用例库建立测试用例", tags = {"测试用例" },  notes = "根据用例库建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases")
    public ResponseEntity<IbzCaseDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase domain = ibzcaseMapping.toDomain(ibzcasedto);
        domain.setLib(ibzlib_id);
		ibzcaseService.create(domain);
        if(!ibzcaseRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'READ')")
    @ApiOperation(value = "根据用例库获取测试用例", tags = {"测试用例" },  notes = "根据用例库获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}")
    public ResponseEntity<IbzCaseDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id) {
        IbzCase domain = ibzcaseService.get(ibzcase_id);
        if (domain == null || domain.getLib() != ibzlib_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'DELETE')")
    @ApiOperation(value = "根据用例库删除测试用例", tags = {"测试用例" },  notes = "根据用例库删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id) {
        IbzCase testget = ibzcaseService.get(ibzcase_id);
        if (testget == null || testget.getLib() != ibzlib_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(ibzcaseService.remove(ibzcase_id));
    }

    @PreAuthorize("quickTest('IBZ_CASE','DELETE')")
    @ApiOperation(value = "根据用例库批量删除测试用例", tags = {"测试用例" },  notes = "根据用例库批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLib(@RequestBody List<Long> ids) {
        ibzcaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_CASE', #ibzcase_id, 'UPDATE')")
    @ApiOperation(value = "根据用例库更新测试用例", tags = {"测试用例" },  notes = "根据用例库更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}")
    public ResponseEntity<IbzCaseDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase testget = ibzcaseService.get(ibzcase_id);
        if (testget == null || testget.getLib() != ibzlib_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        IbzCase domain = ibzcaseMapping.toDomain(ibzcasedto);
        domain.setLib(ibzlib_id);
        domain.setId(ibzcase_id);
		ibzcaseService.update(domain);
        if(!ibzcaseRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        IbzCaseDTO dto = ibzcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzcaseRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_CASE','CREATE')")
    @ApiOperation(value = "根据用例库检查测试用例", tags = {"测试用例" },  notes = "根据用例库检查测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzCaseDTO ibzcasedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseService.checkKey(ibzcaseMapping.toDomain(ibzcasedto)));
    }

    @PreAuthorize("quickTest('IBZ_CASE','CREATE')")
    @ApiOperation(value = "根据用例库获取测试用例草稿", tags = {"测试用例" },  notes = "根据用例库获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/getdraft")
    public ResponseEntity<IbzCaseDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, IbzCaseDTO dto) {
        IbzCase domain = ibzcaseMapping.toDomain(dto);
        domain.setLib(ibzlib_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseMapping.toDto(ibzcaseService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_CASE', 'DENY')")
    @ApiOperation(value = "根据用例库保存测试用例", tags = {"测试用例" },  notes = "根据用例库保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/save")
    public ResponseEntity<IbzCaseDTO> saveByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzCaseDTO ibzcasedto) {
        IbzCase domain = ibzcaseMapping.toDomain(ibzcasedto);
        domain.setLib(ibzlib_id);
        ibzcaseService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('IBZ_CASE','READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"测试用例" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzcases/fetchdefault")
	public ResponseEntity<List<IbzCaseDTO>> fetchDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzCaseSearchContext context) {
        context.setN_lib_eq(ibzlib_id);
        ibzcaseRuntime.addAuthorityConditions(context,"READ");
        Page<IbzCase> domains = ibzcaseService.searchDefault(context) ;
        List<IbzCaseDTO> list = ibzcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

