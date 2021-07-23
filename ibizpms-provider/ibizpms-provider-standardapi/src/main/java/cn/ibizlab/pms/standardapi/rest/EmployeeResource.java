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
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"人员"})
@RestController("StandardAPI-employee")
@RequestMapping("")
public class EmployeeResource {

    @Autowired
    public ISysEmployeeService sysemployeeService;


    @Autowired
    @Lazy
    public EmployeeMapping employeeMapping;

    @ApiOperation(value = "获取人员", tags = {"人员" },  notes = "获取人员")
	@RequestMapping(method = RequestMethod.GET, value = "/employees/{employee_id}")
    public ResponseEntity<EmployeeDTO> get(@PathVariable("employee_id") String employee_id) {
        SysEmployee domain = sysemployeeService.get(employee_id);
        EmployeeDTO dto = employeeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

	@ApiOperation(value = "获取Bug用户", tags = {"人员" } ,notes = "获取Bug用户")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchbug")
	public ResponseEntity<List<EmployeeDTO>> fetchbug(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchBugUser(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取联系人用户", tags = {"人员" } ,notes = "获取联系人用户")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchcontact")
	public ResponseEntity<List<EmployeeDTO>> fetchcontact(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchContActList(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取数据集", tags = {"人员" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchdefault")
	public ResponseEntity<List<EmployeeDTO>> fetchdefault(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchDefault(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队管理", tags = {"人员" } ,notes = "获取项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchproduct")
	public ResponseEntity<List<EmployeeDTO>> fetchproduct(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProductTeamM(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队成员", tags = {"人员" } ,notes = "获取项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchproject")
	public ResponseEntity<List<EmployeeDTO>> fetchproject(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamUser1(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取数据查询2", tags = {"人员" } ,notes = "获取数据查询2")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchtask")
	public ResponseEntity<List<EmployeeDTO>> fetchtask(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchTaskTeam(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取任务多人团队", tags = {"人员" } ,notes = "获取任务多人团队")
    @RequestMapping(method= RequestMethod.POST , value="/employees/fetchtaskmulti")
	public ResponseEntity<List<EmployeeDTO>> fetchtaskmulti(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchTaskMTeam(context) ;
        List<EmployeeDTO> list = employeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成人员报表", tags = {"人员"}, notes = "生成人员报表")
    @RequestMapping(method = RequestMethod.GET, value = "/employees/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysEmployeeSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印人员", tags = {"人员"}, notes = "打印人员")
    @RequestMapping(method = RequestMethod.GET, value = "/employees/{employee_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("employee_ids") Set<String> employee_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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

}

