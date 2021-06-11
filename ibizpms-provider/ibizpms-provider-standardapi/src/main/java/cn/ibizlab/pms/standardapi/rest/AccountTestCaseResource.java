package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.CaseRuntime;

@Slf4j
@Api(tags = {"测试用例"})
@RestController("StandardAPI-accounttestcase")
@RequestMapping("")
public class AccountTestCaseResource {

    @Autowired
    public ICaseService caseService;

    @Autowired
    public CaseRuntime caseRuntime;

    @Autowired
    @Lazy
    public AccountTestCaseMapping accounttestcaseMapping;

    @PreAuthorize("test('ZT_CASE', #accounttestcase_id, 'READ')")
    @ApiOperation(value = "获取测试用例", tags = {"测试用例" },  notes = "获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttestcases/{accounttestcase_id}")
    public ResponseEntity<AccountTestCaseDTO> get(@PathVariable("accounttestcase_id") Long accounttestcase_id) {
        Case domain = caseService.get(accounttestcase_id);
        AccountTestCaseDTO dto = accounttestcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs(accounttestcase_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"测试用例" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttestcases/fetchaccount")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchaccount(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchAccount(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"测试用例" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttestcases/fetchmy")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchmy(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchMy(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"测试用例" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/accounttestcases/fetchmyfavorite")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchmyfavorite(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试用例报表", tags = {"测试用例"}, notes = "生成测试用例报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttestcases/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, CaseSearchContext context, HttpServletResponse response) {
        try {
            caseRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", caseRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, caseRuntime);
        }
    }

    @ApiOperation(value = "打印测试用例", tags = {"测试用例"}, notes = "打印测试用例")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttestcases/{accounttestcase_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accounttestcase_ids") Set<Long> accounttestcase_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = caseRuntime.getDEPrintRuntime(print_id);
        try {
            List<Case> domains = new ArrayList<>();
            for (Long accounttestcase_id : accounttestcase_ids) {
                domains.add(caseService.get( accounttestcase_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Case[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", caseRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accounttestcase_ids, e.getMessage()), Errors.INTERNALERROR, caseRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/accounttestcases/{accounttestcase_id}/{action}")
    public ResponseEntity<AccountTestCaseDTO> dynamicCall(@PathVariable("accounttestcase_id") Long accounttestcase_id , @PathVariable("action") String action , @RequestBody AccountTestCaseDTO accounttestcasedto) {
        Case domain = caseService.dynamicCall(accounttestcase_id, action, accounttestcaseMapping.toDomain(accounttestcasedto));
        accounttestcasedto = accounttestcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accounttestcasedto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
    @ApiOperation(value = "根据系统用户获取测试用例", tags = {"测试用例" },  notes = "根据系统用户获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accounttestcases/{accounttestcase_id}")
    public ResponseEntity<AccountTestCaseDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accounttestcase_id") Long accounttestcase_id) {
        Case domain = caseService.get(accounttestcase_id);
        AccountTestCaseDTO dto = accounttestcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"测试用例" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttestcases/fetchaccount")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchAccount(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"测试用例" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttestcases/fetchmy")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchMy(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"测试用例" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttestcases/fetchmyfavorite")
	public ResponseEntity<List<AccountTestCaseDTO>> fetchMyFavoriteBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<AccountTestCaseDTO> list = accounttestcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

