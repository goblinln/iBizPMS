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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportlyRuntime;

@Slf4j
@Api(tags = {"汇报"})
@RestController("StandardAPI-reportly")
@RequestMapping("")
public class ReportlyResource {

    @Autowired
    public IIbzReportlyService ibzreportlyService;

    @Autowired
    public IbzReportlyRuntime ibzreportlyRuntime;

    @Autowired
    @Lazy
    public ReportlyMapping reportlyMapping;

    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
    @ApiOperation(value = "新建汇报", tags = {"汇报" },  notes = "新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies")
    @Transactional
    public ResponseEntity<ReportlyDTO> create(@Validated @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = reportlyMapping.toDomain(reportlydto);
		ibzreportlyService.create(domain);
        ReportlyDTO dto = reportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "获取汇报", tags = {"汇报" },  notes = "获取汇报")
	@RequestMapping(method = RequestMethod.GET, value = "/reportlies/{reportly_id}")
    public ResponseEntity<ReportlyDTO> get(@PathVariable("reportly_id") Long reportly_id) {
        IbzReportly domain = ibzreportlyService.get(reportly_id);
        ReportlyDTO dto = reportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(reportly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzreportly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "更新汇报", tags = {"汇报" },  notes = "更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/reportlies/{reportly_id}")
    @Transactional
    public ResponseEntity<ReportlyDTO> update(@PathVariable("reportly_id") Long reportly_id, @RequestBody ReportlyDTO reportlydto) {
		IbzReportly domain  = reportlyMapping.toDomain(reportlydto);
        domain.setIbzreportlyid(reportly_id);
		ibzreportlyService.update(domain );
		ReportlyDTO dto = reportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(reportly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'CREATE')")
    @ApiOperation(value = "获取汇报草稿", tags = {"汇报" },  notes = "获取汇报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/reportlies/getdraft")
    public ResponseEntity<ReportlyDTO> getDraft(ReportlyDTO dto) {
        IbzReportly domain = reportlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(reportlyMapping.toDto(ibzreportlyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"汇报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies/{reportly_id}/read")
    public ResponseEntity<ReportlyDTO> read(@PathVariable("reportly_id") Long reportly_id, @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = reportlyMapping.toDomain(reportlydto);
        domain.setIbzreportlyid(reportly_id);
        domain = ibzreportlyService.haveRead(domain);
        reportlydto = reportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        reportlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(reportlydto);
    }


    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"汇报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies/{reportly_id}/submit")
    public ResponseEntity<ReportlyDTO> submit(@PathVariable("reportly_id") Long reportly_id, @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = reportlyMapping.toDomain(reportlydto);
        domain.setIbzreportlyid(reportly_id);
        domain = ibzreportlyService.submit(domain);
        reportlydto = reportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        reportlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(reportlydto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"汇报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/fetchdefault")
	public ResponseEntity<List<ReportlyDTO>> fetchdefault(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
        List<ReportlyDTO> list = reportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成汇报报表", tags = {"汇报"}, notes = "生成汇报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/reportlies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzReportlySearchContext context, HttpServletResponse response) {
        try {
            ibzreportlyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportlyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzreportlyRuntime);
        }
    }

    @ApiOperation(value = "打印汇报", tags = {"汇报"}, notes = "打印汇报")
    @RequestMapping(method = RequestMethod.GET, value = "/reportlies/{reportly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("reportly_ids") Set<Long> reportly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzreportlyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzReportly> domains = new ArrayList<>();
            for (Long reportly_id : reportly_ids) {
                domains.add(ibzreportlyService.get( reportly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzReportly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportlyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", reportly_ids, e.getMessage()), Errors.INTERNALERROR, ibzreportlyRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/reportlies/{reportly_id}/{action}")
    public ResponseEntity<ReportlyDTO> dynamicCall(@PathVariable("reportly_id") Long reportly_id , @PathVariable("action") String action , @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = ibzreportlyService.dynamicCall(reportly_id, action, reportlyMapping.toDomain(reportlydto));
        reportlydto = reportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(reportlydto);
    }
}

