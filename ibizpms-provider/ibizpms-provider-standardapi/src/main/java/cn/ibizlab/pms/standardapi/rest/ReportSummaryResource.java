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
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.service.IIbzReportService;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportRuntime;

@Slf4j
@Api(tags = {"汇报汇总"})
@RestController("StandardAPI-reportsummary")
@RequestMapping("")
public class ReportSummaryResource {

    @Autowired
    public IIbzReportService ibzreportService;

    @Autowired
    public IbzReportRuntime ibzreportRuntime;

    @Autowired
    @Lazy
    public ReportSummaryMapping reportsummaryMapping;

    @PreAuthorize("quickTest('IBZ_REPORT', 'READ')")
	@ApiOperation(value = "获取汇报汇总", tags = {"汇报汇总" } ,notes = "获取汇报汇总")
    @RequestMapping(method= RequestMethod.POST , value="/reportsummaries/fetchall")
	public ResponseEntity<List<ReportSummaryDTO>> fetchall(@RequestBody IbzReportSearchContext context) {
        ibzreportRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReport> domains = ibzreportService.searchAllReport(context) ;
        List<ReportSummaryDTO> list = reportsummaryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成汇报汇总报表", tags = {"汇报汇总"}, notes = "生成汇报汇总报表")
    @RequestMapping(method = RequestMethod.GET, value = "/reportsummaries/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzReportSearchContext context, HttpServletResponse response) {
        try {
            ibzreportRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzreportRuntime);
        }
    }

    @ApiOperation(value = "打印汇报汇总", tags = {"汇报汇总"}, notes = "打印汇报汇总")
    @RequestMapping(method = RequestMethod.GET, value = "/reportsummaries/{reportsummary_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("reportsummary_ids") Set<Long> reportsummary_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzreportRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzReport> domains = new ArrayList<>();
            for (Long reportsummary_id : reportsummary_ids) {
                domains.add(ibzreportService.get( reportsummary_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzReport[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", reportsummary_ids, e.getMessage()), Errors.INTERNALERROR, ibzreportRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/reportsummaries/{reportsummary_id}/{action}")
    public ResponseEntity<ReportSummaryDTO> dynamicCall(@PathVariable("reportsummary_id") Long reportsummary_id , @PathVariable("action") String action , @RequestBody ReportSummaryDTO reportsummarydto) {
        IbzReport domain = ibzreportService.dynamicCall(reportsummary_id, action, reportsummaryMapping.toDomain(reportsummarydto));
        reportsummarydto = reportsummaryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(reportsummarydto);
    }
}

