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
import cn.ibizlab.pms.core.uaa.domain.SysUser;
import cn.ibizlab.pms.core.uaa.service.ISysUserService;
import cn.ibizlab.pms.core.uaa.filter.SysUserSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统用户"})
@RestController("StandardAPI-sysaccount")
@RequestMapping("")
public class SysAccountResource {

    @Autowired
    public ISysUserService sysuserService;


    @Autowired
    @Lazy
    public SysAccountMapping sysaccountMapping;

    @ApiOperation(value = "获取系统用户", tags = {"系统用户" },  notes = "获取系统用户")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysaccount_id}")
    public ResponseEntity<SysAccountDTO> get(@PathVariable("sysaccount_id") String sysaccount_id) {
        SysUser domain = sysuserService.get(sysaccount_id);
        SysAccountDTO dto = sysaccountMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

	@ApiOperation(value = "获取个人信息-个人贡献", tags = {"系统用户" } ,notes = "获取个人信息-个人贡献")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/countmycontribution")
	public ResponseEntity<List<SysAccountDTO>> countmycontribution(@RequestBody SysUserSearchContext context) {
        Page<SysUser> domains = sysuserService.searchPersonInfo(context) ;
        List<SysAccountDTO> list = sysaccountMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取我的工作", tags = {"系统用户" } ,notes = "获取我的工作")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/countmywork")
	public ResponseEntity<List<SysAccountDTO>> countmywork(@RequestBody SysUserSearchContext context) {
        Page<SysUser> domains = sysuserService.searchMyWork(context) ;
        List<SysAccountDTO> list = sysaccountMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成系统用户报表", tags = {"系统用户"}, notes = "生成系统用户报表")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysUserSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印系统用户", tags = {"系统用户"}, notes = "打印系统用户")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysaccount_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("sysaccount_ids") Set<String> sysaccount_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysaccount_id}/{action}")
    public ResponseEntity<SysAccountDTO> dynamicCall(@PathVariable("sysaccount_id") String sysaccount_id , @PathVariable("action") String action , @RequestBody SysAccountDTO sysaccountdto) {
        SysUser domain = sysuserService.dynamicCall(sysaccount_id, action, sysaccountMapping.toDomain(sysaccountdto));
        sysaccountdto = sysaccountMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysaccountdto);
    }
}

