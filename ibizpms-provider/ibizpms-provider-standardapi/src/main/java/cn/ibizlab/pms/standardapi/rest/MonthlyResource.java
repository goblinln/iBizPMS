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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzMonthlyRuntime;

@Slf4j
@Api(tags = {"月报"})
@RestController("StandardAPI-monthly")
@RequestMapping("")
public class MonthlyResource {

    @Autowired
    public IIbzMonthlyService ibzmonthlyService;

    @Autowired
    public IbzMonthlyRuntime ibzmonthlyRuntime;

    @Autowired
    @Lazy
    public MonthlyMapping monthlyMapping;

    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
    @ApiOperation(value = "新建月报", tags = {"月报" },  notes = "新建月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies")
    @Transactional
    public ResponseEntity<MonthlyDTO> create(@Validated @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
		ibzmonthlyService.create(domain);
        MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "获取月报", tags = {"月报" },  notes = "获取月报")
	@RequestMapping(method = RequestMethod.GET, value = "/monthlies/{monthly_id}")
    public ResponseEntity<MonthlyDTO> get(@PathVariable("monthly_id") Long monthly_id) {
        IbzMonthly domain = ibzmonthlyService.get(monthly_id);
        MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(monthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzmonthly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "更新月报", tags = {"月报" },  notes = "更新月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/monthlies/{monthly_id}")
    @Transactional
    public ResponseEntity<MonthlyDTO> update(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
		IbzMonthly domain  = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
		ibzmonthlyService.update(domain );
		MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(monthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "定时生成用户月报", tags = {"月报" },  notes = "定时生成用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/createusermonthly")
    public ResponseEntity<MonthlyDTO> createUserMonthly(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.createUserMonthly(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
    @ApiOperation(value = "获取月报草稿", tags = {"月报" },  notes = "获取月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/monthlies/getdraft")
    public ResponseEntity<MonthlyDTO> getDraft(MonthlyDTO dto) {
        IbzMonthly domain = monthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(monthlyMapping.toDto(ibzmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户月报", tags = {"月报" },  notes = "定时推送待阅提醒用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/pushusermonthly")
    public ResponseEntity<MonthlyDTO> pushUserMonthly(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.pushUserMonthly(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"月报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/haveread")
    public ResponseEntity<MonthlyDTO> haveRead(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.haveRead(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"月报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/submit")
    public ResponseEntity<MonthlyDTO> submit(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.submit(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/fetchdefault")
	public ResponseEntity<List<MonthlyDTO>> fetchdefault(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault(context) ;
        List<MonthlyDTO> list = monthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成月报报表", tags = {"月报"}, notes = "生成月报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/monthlies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzMonthlySearchContext context, HttpServletResponse response) {
        try {
            ibzmonthlyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzmonthlyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzmonthlyRuntime);
        }
    }

    @ApiOperation(value = "打印月报", tags = {"月报"}, notes = "打印月报")
    @RequestMapping(method = RequestMethod.GET, value = "/monthlies/{monthly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("monthly_ids") Set<Long> monthly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzmonthlyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzMonthly> domains = new ArrayList<>();
            for (Long monthly_id : monthly_ids) {
                domains.add(ibzmonthlyService.get( monthly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzMonthly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzmonthlyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", monthly_ids, e.getMessage()), Errors.INTERNALERROR, ibzmonthlyRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/{action}")
    public ResponseEntity<MonthlyDTO> dynamicCall(@PathVariable("monthly_id") Long monthly_id , @PathVariable("action") String action , @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = ibzmonthlyService.dynamicCall(monthly_id, action, monthlyMapping.toDomain(monthlydto));
        monthlydto = monthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }
}

