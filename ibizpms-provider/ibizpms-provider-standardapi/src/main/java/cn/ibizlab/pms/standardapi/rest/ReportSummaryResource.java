package cn.ibizlab.pms.standardapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
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
@Api(tags = {"汇报汇总" })
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/reportsummaries/{reportsummary_id}/{action}")
    public ResponseEntity<ReportSummaryDTO> dynamicCall(@PathVariable("reportsummary_id") Long reportsummary_id , @PathVariable("action") String action , @RequestBody ReportSummaryDTO reportsummarydto) {
        IbzReport domain = ibzreportService.dynamicCall(reportsummary_id, action, reportsummaryMapping.toDomain(reportsummarydto));
        reportsummarydto = reportsummaryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(reportsummarydto);
    }
}

