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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzDailyRuntime;

@Slf4j
@Api(tags = {"日报"})
@RestController("StandardAPI-daily")
@RequestMapping("")
public class DailyResource {

    @Autowired
    public IIbzDailyService ibzdailyService;

    @Autowired
    public IbzDailyRuntime ibzdailyRuntime;

    @Autowired
    @Lazy
    public DailyMapping dailyMapping;

    @PreAuthorize("quickTest('IBZ_DAILY', 'NONE')")
    @ApiOperation(value = "新建日报", tags = {"日报" },  notes = "新建日报")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies")
    @Transactional
    public ResponseEntity<DailyDTO> create(@Validated @RequestBody DailyDTO dailydto) {
        IbzDaily domain = dailyMapping.toDomain(dailydto);
		ibzdailyService.create(domain);
        DailyDTO dto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "获取日报", tags = {"日报" },  notes = "获取日报")
	@RequestMapping(method = RequestMethod.GET, value = "/dailies/{daily_id}")
    public ResponseEntity<DailyDTO> get(@PathVariable("daily_id") Long daily_id) {
        IbzDaily domain = ibzdailyService.get(daily_id);
        DailyDTO dto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(daily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzdaily" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "更新日报", tags = {"日报" },  notes = "更新日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/dailies/{daily_id}")
    @Transactional
    public ResponseEntity<DailyDTO> update(@PathVariable("daily_id") Long daily_id, @RequestBody DailyDTO dailydto) {
		IbzDaily domain  = dailyMapping.toDomain(dailydto);
        domain.setIbzdailyid(daily_id);
		ibzdailyService.update(domain );
		DailyDTO dto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(daily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "定时生成用户日报", tags = {"日报" },  notes = "定时生成用户日报")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies/{daily_id}/autocreate")
    public ResponseEntity<DailyDTO> autoCreate(@PathVariable("daily_id") Long daily_id, @RequestBody DailyDTO dailydto) {
        IbzDaily domain = dailyMapping.toDomain(dailydto);
        domain.setIbzdailyid(daily_id);
        domain = ibzdailyService.createUserDaily(domain);
        dailydto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dailydto);
    }


    @PreAuthorize("quickTest('IBZ_DAILY', 'NONE')")
    @ApiOperation(value = "获取日报草稿", tags = {"日报" },  notes = "获取日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/dailies/getdraft")
    public ResponseEntity<DailyDTO> getDraft(DailyDTO dto) {
        IbzDaily domain = dailyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dailyMapping.toDto(ibzdailyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户日报", tags = {"日报" },  notes = "定时推送待阅提醒用户日报")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies/{daily_id}/notice")
    public ResponseEntity<DailyDTO> notice(@PathVariable("daily_id") Long daily_id, @RequestBody DailyDTO dailydto) {
        IbzDaily domain = dailyMapping.toDomain(dailydto);
        domain.setIbzdailyid(daily_id);
        domain = ibzdailyService.pushUserDaily(domain);
        dailydto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dailydto);
    }


    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"日报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies/{daily_id}/read")
    public ResponseEntity<DailyDTO> read(@PathVariable("daily_id") Long daily_id, @RequestBody DailyDTO dailydto) {
        IbzDaily domain = dailyMapping.toDomain(dailydto);
        domain.setIbzdailyid(daily_id);
        domain = ibzdailyService.haveRead(domain);
        dailydto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dailydto);
    }


    @PreAuthorize("test('IBZ_DAILY', #daily_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"日报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies/{daily_id}/submit")
    public ResponseEntity<DailyDTO> submit(@PathVariable("daily_id") Long daily_id, @RequestBody DailyDTO dailydto) {
        IbzDaily domain = dailyMapping.toDomain(dailydto);
        domain.setIbzdailyid(daily_id);
        domain = ibzdailyService.submit(domain);
        dailydto = dailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dailydto);
    }


    @PreAuthorize("quickTest('IBZ_DAILY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/fetchdefault")
	public ResponseEntity<List<DailyDTO>> fetchdefault(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchDefault(context) ;
        List<DailyDTO> list = dailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成日报报表", tags = {"日报"}, notes = "生成日报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/dailies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzDailySearchContext context, HttpServletResponse response) {
        try {
            ibzdailyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzdailyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzdailyRuntime);
        }
    }

    @ApiOperation(value = "打印日报", tags = {"日报"}, notes = "打印日报")
    @RequestMapping(method = RequestMethod.GET, value = "/dailies/{daily_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("daily_ids") Set<Long> daily_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzdailyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzDaily> domains = new ArrayList<>();
            for (Long daily_id : daily_ids) {
                domains.add(ibzdailyService.get( daily_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzDaily[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzdailyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", daily_ids, e.getMessage()), Errors.INTERNALERROR, ibzdailyRuntime);
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

