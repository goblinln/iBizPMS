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
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.service.IIbzReportService;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportRuntime;

@Slf4j
@Api(tags = {"汇报汇总"})
@RestController("WebApi-ibzreport")
@RequestMapping("")
public class IbzReportResource {

    @Autowired
    public IIbzReportService ibzreportService;

    @Autowired
    public IbzReportRuntime ibzreportRuntime;

    @Autowired
    @Lazy
    public IbzReportMapping ibzreportMapping;

    @PreAuthorize("quickTest('IBZ_REPORT', 'CREATE')")
    @ApiOperation(value = "新建汇报汇总", tags = {"汇报汇总" },  notes = "新建汇报汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports")
    @Transactional
    public ResponseEntity<IbzReportDTO> create(@Validated @RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportMapping.toDomain(ibzreportdto);
		ibzreportService.create(domain);
        if(!ibzreportRuntime.test(domain.getIbzdailyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzReportDTO dto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'NONE')")
    @ApiOperation(value = "获取汇报汇总", tags = {"汇报汇总" },  notes = "获取汇报汇总")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreports/{ibzreport_id}")
    public ResponseEntity<IbzReportDTO> get(@PathVariable("ibzreport_id") Long ibzreport_id) {
        IbzReport domain = ibzreportService.get(ibzreport_id);
        IbzReportDTO dto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(ibzreport_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'DELETE')")
    @ApiOperation(value = "删除汇报汇总", tags = {"汇报汇总" },  notes = "删除汇报汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreports/{ibzreport_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzreport_id") Long ibzreport_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportService.remove(ibzreport_id));
    }

    @PreAuthorize("quickTest('IBZ_REPORT', 'DELETE')")
    @ApiOperation(value = "批量删除汇报汇总", tags = {"汇报汇总" },  notes = "批量删除汇报汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreports/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzreport" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'UPDATE')")
    @ApiOperation(value = "更新汇报汇总", tags = {"汇报汇总" },  notes = "更新汇报汇总")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreports/{ibzreport_id}")
    @Transactional
    public ResponseEntity<IbzReportDTO> update(@PathVariable("ibzreport_id") Long ibzreport_id, @RequestBody IbzReportDTO ibzreportdto) {
		IbzReport domain  = ibzreportMapping.toDomain(ibzreportdto);
        domain.setIbzdailyid(ibzreport_id);
		ibzreportService.update(domain );
        if(!ibzreportRuntime.test(ibzreport_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzReportDTO dto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(ibzreport_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_REPORT', 'CREATE')")
    @ApiOperation(value = "检查汇报汇总", tags = {"汇报汇总" },  notes = "检查汇报汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportDTO ibzreportdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportService.checkKey(ibzreportMapping.toDomain(ibzreportdto)));
    }

    @PreAuthorize("quickTest('IBZ_REPORT', 'CREATE')")
    @ApiOperation(value = "获取汇报汇总草稿", tags = {"汇报汇总" },  notes = "获取汇报汇总草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreports/getdraft")
    public ResponseEntity<IbzReportDTO> getDraft(IbzReportDTO dto) {
        IbzReport domain = ibzreportMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportMapping.toDto(ibzreportService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'NONE')")
    @ApiOperation(value = "我未提交的（计数器）", tags = {"汇报汇总" },  notes = "我未提交的（计数器）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/{ibzreport_id}/myreportinotsubmit")
    public ResponseEntity<IbzReportDTO> myReportINotSubmit(@PathVariable("ibzreport_id") Long ibzreport_id, @RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportMapping.toDomain(ibzreportdto);
        domain.setIbzdailyid(ibzreport_id);
        domain = ibzreportService.myReportINotSubmit(domain);
        ibzreportdto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportdto);
    }


    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'NONE')")
    @ApiOperation(value = "我收到的汇报（计数器）", tags = {"汇报汇总" },  notes = "我收到的汇报（计数器）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/{ibzreport_id}/reportireceived")
    public ResponseEntity<IbzReportDTO> reportIReceived(@PathVariable("ibzreport_id") Long ibzreport_id, @RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportMapping.toDomain(ibzreportdto);
        domain.setIbzdailyid(ibzreport_id);
        domain = ibzreportService.reportIReceived(domain);
        ibzreportdto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportdto);
    }


    @PreAuthorize("quickTest('IBZ_REPORT', 'DENY')")
    @ApiOperation(value = "保存汇报汇总", tags = {"汇报汇总" },  notes = "保存汇报汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/save")
    public ResponseEntity<IbzReportDTO> save(@RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportMapping.toDomain(ibzreportdto);
        ibzreportService.save(domain);
        IbzReportDTO dto = ibzreportMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_REPORT', 'READ')")
	@ApiOperation(value = "获取汇报汇总", tags = {"汇报汇总" } ,notes = "获取汇报汇总")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreports/fetchallreport")
	public ResponseEntity<List<IbzReportDTO>> fetchallreport(@RequestBody IbzReportSearchContext context) {
        ibzreportRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReport> domains = ibzreportService.searchAllReport(context) ;
        List<IbzReportDTO> list = ibzreportMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_REPORT', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"汇报汇总" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreports/fetchdefault")
	public ResponseEntity<List<IbzReportDTO>> fetchdefault(@RequestBody IbzReportSearchContext context) {
        ibzreportRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReport> domains = ibzreportService.searchDefault(context) ;
        List<IbzReportDTO> list = ibzreportMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_REPORT', 'READ')")
	@ApiOperation(value = "获取汇报汇总（我收到的）", tags = {"汇报汇总" } ,notes = "获取汇报汇总（我收到的）")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreports/fetchmyreallreport")
	public ResponseEntity<List<IbzReportDTO>> fetchmyreallreport(@RequestBody IbzReportSearchContext context) {
        ibzreportRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReport> domains = ibzreportService.searchMyReAllReport(context) ;
        List<IbzReportDTO> list = ibzreportMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成汇报汇总报表", tags = {"汇报汇总"}, notes = "生成汇报汇总报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreports/report/{report_id}.{type}")
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
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreports/{ibzreport_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzreport_ids") Set<Long> ibzreport_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzreportRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzReport> domains = new ArrayList<>();
            for (Long ibzreport_id : ibzreport_ids) {
                domains.add(ibzreportService.get( ibzreport_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzReport[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzreport_ids, e.getMessage()), Errors.INTERNALERROR, ibzreportRuntime);
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

}

