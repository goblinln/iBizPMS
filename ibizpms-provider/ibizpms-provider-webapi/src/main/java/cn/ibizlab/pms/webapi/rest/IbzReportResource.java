package cn.ibizlab.pms.webapi.rest;

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
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.service.IIbzReportService;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportRuntime;

@Slf4j
@Api(tags = {"汇报汇总" })
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
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
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
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(ibzreport_id);
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

    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'NONE')")
    @ApiOperation(value = "获取汇报汇总", tags = {"汇报汇总" },  notes = "获取汇报汇总")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreports/{ibzreport_id}")
    public ResponseEntity<IbzReportDTO> get(@PathVariable("ibzreport_id") Long ibzreport_id) {
        IbzReport domain = ibzreportService.get(ibzreport_id);
        IbzReportDTO dto = ibzreportMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(ibzreport_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_REPORT', 'CREATE')")
    @ApiOperation(value = "获取汇报汇总草稿", tags = {"汇报汇总" },  notes = "获取汇报汇总草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreports/getdraft")
    public ResponseEntity<IbzReportDTO> getDraft(IbzReportDTO dto) {
        IbzReport domain = ibzreportMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportMapping.toDto(ibzreportService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_REPORT', 'CREATE')")
    @ApiOperation(value = "检查汇报汇总", tags = {"汇报汇总" },  notes = "检查汇报汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportDTO ibzreportdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportService.checkKey(ibzreportMapping.toDomain(ibzreportdto)));
    }

    @PreAuthorize("test('IBZ_REPORT', #ibzreport_id, 'NONE')")
    @ApiOperation(value = "我未提交的（计数器）", tags = {"汇报汇总" },  notes = "我未提交的（计数器）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreports/{ibzreport_id}/myreportinotsubmit")
    public ResponseEntity<IbzReportDTO> myReportINotSubmit(@PathVariable("ibzreport_id") Long ibzreport_id, @RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportMapping.toDomain(ibzreportdto);
        domain.setIbzdailyid(ibzreport_id);
        domain = ibzreportService.myReportINotSubmit(domain);
        ibzreportdto = ibzreportMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
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
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
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
        Map<String,Integer> opprivs = ibzreportRuntime.getOPPrivs(domain.getIbzdailyid());
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzreports/{ibzreport_id}/{action}")
    public ResponseEntity<IbzReportDTO> dynamicCall(@PathVariable("ibzreport_id") Long ibzreport_id , @PathVariable("action") String action , @RequestBody IbzReportDTO ibzreportdto) {
        IbzReport domain = ibzreportService.dynamicCall(ibzreport_id, action, ibzreportMapping.toDomain(ibzreportdto));
        ibzreportdto = ibzreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportdto);
    }
}

