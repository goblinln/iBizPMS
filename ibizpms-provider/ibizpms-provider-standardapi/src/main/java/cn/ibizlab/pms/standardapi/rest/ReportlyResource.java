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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportlyRuntime;

@Slf4j
@Api(tags = {"汇报" })
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

    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "获取汇报", tags = {"汇报" },  notes = "获取汇报")
	@RequestMapping(method = RequestMethod.GET, value = "/reportlies/{reportly_id}")
    public ResponseEntity<ReportlyDTO> get(@PathVariable("reportly_id") Long reportly_id) {
        IbzReportly domain = ibzreportlyService.get(reportly_id);
        ReportlyDTO dto = reportlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportlyRuntime.getOPPrivs(reportly_id);
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
        Map<String,Integer> opprivs = ibzreportlyRuntime.getOPPrivs(reportly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_REPORTLY', #reportly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"汇报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies/{reportly_id}/haveread")
    public ResponseEntity<ReportlyDTO> haveRead(@PathVariable("reportly_id") Long reportly_id, @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = reportlyMapping.toDomain(reportlydto);
        domain.setIbzreportlyid(reportly_id);
        domain = ibzreportlyService.haveRead(domain);
        reportlydto = reportlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
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
        Map<String,Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        reportlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(reportlydto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
    @ApiOperation(value = "新建汇报", tags = {"汇报" },  notes = "新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies")
    @Transactional
    public ResponseEntity<ReportlyDTO> create(@Validated @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = reportlyMapping.toDomain(reportlydto);
		ibzreportlyService.create(domain);
        ReportlyDTO dto = reportlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/reportlies/{reportly_id}/{action}")
    public ResponseEntity<ReportlyDTO> dynamicCall(@PathVariable("reportly_id") Long reportly_id , @PathVariable("action") String action , @RequestBody ReportlyDTO reportlydto) {
        IbzReportly domain = ibzreportlyService.dynamicCall(reportly_id, action, reportlyMapping.toDomain(reportlydto));
        reportlydto = reportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(reportlydto);
    }
}

