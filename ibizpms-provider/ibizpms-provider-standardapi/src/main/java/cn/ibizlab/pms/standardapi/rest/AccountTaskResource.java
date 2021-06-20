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
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskRuntime;

@Slf4j
@Api(tags = {"任务"})
@RestController("StandardAPI-accounttask")
@RequestMapping("")
public class AccountTaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public AccountTaskMapping accounttaskMapping;

    @PreAuthorize("test('ZT_TASK', #accounttask_id, 'READ')")
    @ApiOperation(value = "获取任务", tags = {"任务" },  notes = "获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttasks/{accounttask_id}")
    public ResponseEntity<AccountTaskDTO> get(@PathVariable("accounttask_id") Long accounttask_id) {
        Task domain = taskService.get(accounttask_id);
        AccountTaskDTO dto = accounttaskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(accounttask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"任务" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttasks/fetchaccount")
	public ResponseEntity<List<AccountTaskDTO>> fetchaccount(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchAccount(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"任务" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttasks/fetchmy")
	public ResponseEntity<List<AccountTaskDTO>> fetchmy(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMy(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"任务" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/accounttasks/fetchmyfavorites")
	public ResponseEntity<List<AccountTaskDTO>> fetchmyfavorites(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成任务报表", tags = {"任务"}, notes = "生成任务报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttasks/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TaskSearchContext context, HttpServletResponse response) {
        try {
            taskRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", taskRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, taskRuntime);
        }
    }

    @ApiOperation(value = "打印任务", tags = {"任务"}, notes = "打印任务")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttasks/{accounttask_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accounttask_ids") Set<Long> accounttask_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = taskRuntime.getDEPrintRuntime(print_id);
        try {
            List<Task> domains = new ArrayList<>();
            for (Long accounttask_id : accounttask_ids) {
                domains.add(taskService.get( accounttask_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Task[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", taskRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accounttask_ids, e.getMessage()), Errors.INTERNALERROR, taskRuntime);
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


    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
    @ApiOperation(value = "根据系统用户获取任务", tags = {"任务" },  notes = "根据系统用户获取任务")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accounttasks/{accounttask_id}")
    public ResponseEntity<AccountTaskDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accounttask_id") Long accounttask_id) {
        Task domain = taskService.get(accounttask_id);
        AccountTaskDTO dto = accounttaskMapping.toDto(domain);
        Map<String, Integer> opprivs = taskRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"任务" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttasks/fetchaccount")
	public ResponseEntity<List<AccountTaskDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchAccount(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"任务" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttasks/fetchmy")
	public ResponseEntity<List<AccountTaskDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMy(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"任务" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttasks/fetchmyfavorites")
	public ResponseEntity<List<AccountTaskDTO>> fetchMyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TaskSearchContext context) {
        
        Page<Task> domains = taskService.searchMyFavorites(context) ;
        List<AccountTaskDTO> list = accounttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

