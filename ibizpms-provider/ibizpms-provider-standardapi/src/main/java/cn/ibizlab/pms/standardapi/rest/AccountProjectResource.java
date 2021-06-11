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
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectRuntime;

@Slf4j
@Api(tags = {"项目"})
@RestController("StandardAPI-accountproject")
@RequestMapping("")
public class AccountProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    public ProjectRuntime projectRuntime;

    @Autowired
    @Lazy
    public AccountProjectMapping accountprojectMapping;

    @PreAuthorize("test('ZT_PROJECT', #accountproject_id, 'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/accountprojects/{accountproject_id}")
    public ResponseEntity<AccountProjectDTO> get(@PathVariable("accountproject_id") Long accountproject_id) {
        Project domain = projectService.get(accountproject_id);
        AccountProjectDTO dto = accountprojectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(accountproject_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"项目" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountprojects/fetchaccount")
	public ResponseEntity<List<AccountProjectDTO>> fetchaccount(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchAccount(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"项目" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountprojects/fetchmy")
	public ResponseEntity<List<AccountProjectDTO>> fetchmy(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchMy(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目报表", tags = {"项目"}, notes = "生成项目报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accountprojects/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProjectSearchContext context, HttpServletResponse response) {
        try {
            projectRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, projectRuntime);
        }
    }

    @ApiOperation(value = "打印项目", tags = {"项目"}, notes = "打印项目")
    @RequestMapping(method = RequestMethod.GET, value = "/accountprojects/{accountproject_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accountproject_ids") Set<Long> accountproject_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = projectRuntime.getDEPrintRuntime(print_id);
        try {
            List<Project> domains = new ArrayList<>();
            for (Long accountproject_id : accountproject_ids) {
                domains.add(projectService.get( accountproject_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Project[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accountproject_ids, e.getMessage()), Errors.INTERNALERROR, projectRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/accountprojects/{accountproject_id}/{action}")
    public ResponseEntity<AccountProjectDTO> dynamicCall(@PathVariable("accountproject_id") Long accountproject_id , @PathVariable("action") String action , @RequestBody AccountProjectDTO accountprojectdto) {
        Project domain = projectService.dynamicCall(accountproject_id, action, accountprojectMapping.toDomain(accountprojectdto));
        accountprojectdto = accountprojectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accountprojectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
    @ApiOperation(value = "根据系统用户获取项目", tags = {"项目" },  notes = "根据系统用户获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accountprojects/{accountproject_id}")
    public ResponseEntity<AccountProjectDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accountproject_id") Long accountproject_id) {
        Project domain = projectService.get(accountproject_id);
        AccountProjectDTO dto = accountprojectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"项目" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountprojects/fetchaccount")
	public ResponseEntity<List<AccountProjectDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        Page<Project> domains = projectService.searchAccount(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"项目" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountprojects/fetchmy")
	public ResponseEntity<List<AccountProjectDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        Page<Project> domains = projectService.searchMy(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

