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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProductUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbzproProductUserTaskRuntime;

@Slf4j
@Api(tags = {"产品汇报用户任务"})
@RestController("WebApi-ibzproproductusertask")
@RequestMapping("")
public class IbzproProductUserTaskResource {

    @Autowired
    public IIbzproProductUserTaskService ibzproproductusertaskService;

    @Autowired
    public IbzproProductUserTaskRuntime ibzproproductusertaskRuntime;

    @Autowired
    @Lazy
    public IbzproProductUserTaskMapping ibzproproductusertaskMapping;

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'CREATE')")
    @ApiOperation(value = "新建产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "新建产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks")
    @Transactional
    public ResponseEntity<IbzproProductUserTaskDTO> create(@Validated @RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
		ibzproproductusertaskService.create(domain);
        if(!ibzproproductusertaskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductusertaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTUSERTASK', #ibzproproductusertask_id, 'READ')")
    @ApiOperation(value = "获取产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "获取产品汇报用户任务")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    public ResponseEntity<IbzproProductUserTaskDTO> get(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id) {
        IbzproProductUserTask domain = ibzproproductusertaskService.get(ibzproproductusertask_id);
        IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductusertaskRuntime.getOPPrivs(ibzproproductusertask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTUSERTASK', #ibzproproductusertask_id, 'DELETE')")
    @ApiOperation(value = "删除产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "删除产品汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskService.remove(ibzproproductusertask_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'DELETE')")
    @ApiOperation(value = "批量删除产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "批量删除产品汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductusertasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproproductusertaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTUSERTASK', #ibzproproductusertask_id, 'UPDATE')")
    @ApiOperation(value = "更新产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "更新产品汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    @Transactional
    public ResponseEntity<IbzproProductUserTaskDTO> update(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id, @RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
		IbzproProductUserTask domain  = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
        domain.setId(ibzproproductusertask_id);
		ibzproproductusertaskService.update(domain );
        if(!ibzproproductusertaskRuntime.test(ibzproproductusertask_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductusertaskRuntime.getOPPrivs(ibzproproductusertask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'CREATE')")
    @ApiOperation(value = "检查产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "检查产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskService.checkKey(ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'CREATE')")
    @ApiOperation(value = "获取产品汇报用户任务草稿", tags = {"产品汇报用户任务" },  notes = "获取产品汇报用户任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/getdraft")
    public ResponseEntity<IbzproProductUserTaskDTO> getDraft(IbzproProductUserTaskDTO dto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskMapping.toDto(ibzproproductusertaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'DENY')")
    @ApiOperation(value = "保存产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "保存产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/save")
    public ResponseEntity<IbzproProductUserTaskDTO> save(@RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
        ibzproproductusertaskService.save(domain);
        IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductusertaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品汇报用户任务" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/fetchdefault")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchdefault(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchDefault(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取产品日报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品日报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/fetchproductdailyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchproductdailyusertaskstats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductDailyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取产品月报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品月报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/fetchproductmonthlyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchproductmonthlyusertaskstats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductMonthlyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PRODUCTUSERTASK', 'NONE')")
	@ApiOperation(value = "获取产品周报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品周报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/fetchproductweeklyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchproductweeklyusertaskstats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductWeeklyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品汇报用户任务报表", tags = {"产品汇报用户任务"}, notes = "生成产品汇报用户任务报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzproProductUserTaskSearchContext context, HttpServletResponse response) {
        try {
            ibzproproductusertaskRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductusertaskRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzproproductusertaskRuntime);
        }
    }

    @ApiOperation(value = "打印产品汇报用户任务", tags = {"产品汇报用户任务"}, notes = "打印产品汇报用户任务")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/{ibzproproductusertask_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzproproductusertask_ids") Set<Long> ibzproproductusertask_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzproproductusertaskRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzproProductUserTask> domains = new ArrayList<>();
            for (Long ibzproproductusertask_id : ibzproproductusertask_ids) {
                domains.add(ibzproproductusertaskService.get( ibzproproductusertask_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzproProductUserTask[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductusertaskRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzproproductusertask_ids, e.getMessage()), Errors.INTERNALERROR, ibzproproductusertaskRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/{ibzproproductusertask_id}/{action}")
    public ResponseEntity<IbzproProductUserTaskDTO> dynamicCall(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id , @PathVariable("action") String action , @RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        IbzproProductUserTask domain = ibzproproductusertaskService.dynamicCall(ibzproproductusertask_id, action, ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto));
        ibzproproductusertaskdto = ibzproproductusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskdto);
    }
}

