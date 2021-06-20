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
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug"})
@RestController("StandardAPI-accountbug")
@RequestMapping("")
public class AccountBugResource {

    @Autowired
    public IBugService bugService;

    @Autowired
    public BugRuntime bugRuntime;

    @Autowired
    @Lazy
    public AccountBugMapping accountbugMapping;

    @PreAuthorize("test('ZT_BUG', #accountbug_id, 'READ')")
    @ApiOperation(value = "获取Bug", tags = {"Bug" },  notes = "获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/accountbugs/{accountbug_id}")
    public ResponseEntity<AccountBugDTO> get(@PathVariable("accountbug_id") Long accountbug_id) {
        Bug domain = bugService.get(accountbug_id);
        AccountBugDTO dto = accountbugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs(accountbug_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"Bug" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountbugs/fetchaccount")
	public ResponseEntity<List<AccountBugDTO>> fetchaccount(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"Bug" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountbugs/fetchmy")
	public ResponseEntity<List<AccountBugDTO>> fetchmy(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMy(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"Bug" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/accountbugs/fetchmyfavorites")
	public ResponseEntity<List<AccountBugDTO>> fetchmyfavorites(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成Bug报表", tags = {"Bug"}, notes = "生成Bug报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accountbugs/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, BugSearchContext context, HttpServletResponse response) {
        try {
            bugRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", bugRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, bugRuntime);
        }
    }

    @ApiOperation(value = "打印Bug", tags = {"Bug"}, notes = "打印Bug")
    @RequestMapping(method = RequestMethod.GET, value = "/accountbugs/{accountbug_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accountbug_ids") Set<Long> accountbug_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = bugRuntime.getDEPrintRuntime(print_id);
        try {
            List<Bug> domains = new ArrayList<>();
            for (Long accountbug_id : accountbug_ids) {
                domains.add(bugService.get( accountbug_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Bug[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", bugRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accountbug_ids, e.getMessage()), Errors.INTERNALERROR, bugRuntime);
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


    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
    @ApiOperation(value = "根据系统用户获取Bug", tags = {"Bug" },  notes = "根据系统用户获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accountbugs/{accountbug_id}")
    public ResponseEntity<AccountBugDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accountbug_id") Long accountbug_id) {
        Bug domain = bugService.get(accountbug_id);
        AccountBugDTO dto = accountbugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"Bug" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountbugs/fetchaccount")
	public ResponseEntity<List<AccountBugDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"Bug" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountbugs/fetchmy")
	public ResponseEntity<List<AccountBugDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchMy(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUG', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"Bug" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountbugs/fetchmyfavorites")
	public ResponseEntity<List<AccountBugDTO>> fetchMyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<AccountBugDTO> list = accountbugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

