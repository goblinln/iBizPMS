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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProjectUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProjectUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProjectUserTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbzproProjectUserTaskRuntime;

@Slf4j
@Api(tags = {"项目汇报用户任务"})
@RestController("WebApi-ibzproprojectusertask")
@RequestMapping("")
public class IbzproProjectUserTaskResource {

    @Autowired
    public IIbzproProjectUserTaskService ibzproprojectusertaskService;

    @Autowired
    public IbzproProjectUserTaskRuntime ibzproprojectusertaskRuntime;

    @Autowired
    @Lazy
    public IbzproProjectUserTaskMapping ibzproprojectusertaskMapping;

    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'CREATE')")
    @ApiOperation(value = "新建项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "新建项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks")
    @Transactional
    public ResponseEntity<IbzproProjectUserTaskDTO> create(@Validated @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
		ibzproprojectusertaskService.create(domain);
        if(!ibzproprojectusertaskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproprojectusertaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PROJECTUSERTASK', #ibzproprojectusertask_id, 'READ')")
    @ApiOperation(value = "获取项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "获取项目汇报用户任务")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    public ResponseEntity<IbzproProjectUserTaskDTO> get(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id) {
        IbzproProjectUserTask domain = ibzproprojectusertaskService.get(ibzproprojectusertask_id);
        IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproprojectusertaskRuntime.getOPPrivs(ibzproprojectusertask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PROJECTUSERTASK', #ibzproprojectusertask_id, 'DELETE')")
    @ApiOperation(value = "删除项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "删除项目汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskService.remove(ibzproprojectusertask_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'DELETE')")
    @ApiOperation(value = "批量删除项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "批量删除项目汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectusertasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproprojectusertaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBIZPRO_PROJECTUSERTASK', #ibzproprojectusertask_id, 'UPDATE')")
    @ApiOperation(value = "更新项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "更新项目汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    @Transactional
    public ResponseEntity<IbzproProjectUserTaskDTO> update(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id, @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
		IbzproProjectUserTask domain  = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
        domain.setId(ibzproprojectusertask_id);
		ibzproprojectusertaskService.update(domain );
        if(!ibzproprojectusertaskRuntime.test(ibzproprojectusertask_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproprojectusertaskRuntime.getOPPrivs(ibzproprojectusertask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'CREATE')")
    @ApiOperation(value = "检查项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "检查项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskService.checkKey(ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'CREATE')")
    @ApiOperation(value = "获取项目汇报用户任务草稿", tags = {"项目汇报用户任务" },  notes = "获取项目汇报用户任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/getdraft")
    public ResponseEntity<IbzproProjectUserTaskDTO> getDraft(IbzproProjectUserTaskDTO dto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskMapping.toDto(ibzproprojectusertaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'DENY')")
    @ApiOperation(value = "保存项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "保存项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/save")
    public ResponseEntity<IbzproProjectUserTaskDTO> save(@RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
        ibzproprojectusertaskService.save(domain);
        IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproprojectusertaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"项目汇报用户任务" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchdefault")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchdefault(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchDefault(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取项目日报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectdailytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectdailytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectDailyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取项目日报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectmonthlytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectmonthlytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectMonthlyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PROJECTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取项目周报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目周报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectweeklytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectweeklytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectWeeklyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目汇报用户任务报表", tags = {"项目汇报用户任务"}, notes = "生成项目汇报用户任务报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzproProjectUserTaskSearchContext context, HttpServletResponse response) {
        try {
            ibzproprojectusertaskRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproprojectusertaskRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzproprojectusertaskRuntime);
        }
    }

    @ApiOperation(value = "打印项目汇报用户任务", tags = {"项目汇报用户任务"}, notes = "打印项目汇报用户任务")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/{ibzproprojectusertask_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzproprojectusertask_ids") Set<Long> ibzproprojectusertask_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzproprojectusertaskRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzproProjectUserTask> domains = new ArrayList<>();
            for (Long ibzproprojectusertask_id : ibzproprojectusertask_ids) {
                domains.add(ibzproprojectusertaskService.get( ibzproprojectusertask_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzproProjectUserTask[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproprojectusertaskRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzproprojectusertask_ids, e.getMessage()), Errors.INTERNALERROR, ibzproprojectusertaskRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}/{action}")
    public ResponseEntity<IbzproProjectUserTaskDTO> dynamicCall(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id , @PathVariable("action") String action , @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskService.dynamicCall(ibzproprojectusertask_id, action, ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto));
        ibzproprojectusertaskdto = ibzproprojectusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskdto);
    }
}

