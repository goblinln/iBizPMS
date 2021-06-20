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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzWeeklyRuntime;

@Slf4j
@Api(tags = {"周报"})
@RestController("StandardAPI-weekly")
@RequestMapping("")
public class WeeklyResource {

    @Autowired
    public IIbzWeeklyService ibzweeklyService;

    @Autowired
    public IbzWeeklyRuntime ibzweeklyRuntime;

    @Autowired
    @Lazy
    public WeeklyMapping weeklyMapping;

    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "新建周报", tags = {"周报" },  notes = "新建周报")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies")
    @Transactional
    public ResponseEntity<WeeklyDTO> create(@Validated @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
		ibzweeklyService.create(domain);
        WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "获取周报", tags = {"周报" },  notes = "获取周报")
	@RequestMapping(method = RequestMethod.GET, value = "/weeklies/{weekly_id}")
    public ResponseEntity<WeeklyDTO> get(@PathVariable("weekly_id") Long weekly_id) {
        IbzWeekly domain = ibzweeklyService.get(weekly_id);
        WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(weekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "更新周报", tags = {"周报" },  notes = "更新周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/weeklies/{weekly_id}")
    @Transactional
    public ResponseEntity<WeeklyDTO> update(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
		IbzWeekly domain  = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
		ibzweeklyService.update(domain );
		WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(weekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "定时生成每周周报", tags = {"周报" },  notes = "定时生成每周周报")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/autocreate")
    public ResponseEntity<WeeklyDTO> autoCreate(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.createEveryWeekReport(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "获取周报草稿", tags = {"周报" },  notes = "获取周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/weeklies/getdraft")
    public ResponseEntity<WeeklyDTO> getDraft(WeeklyDTO dto) {
        IbzWeekly domain = weeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(weeklyMapping.toDto(ibzweeklyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户周报提交", tags = {"周报" },  notes = "定时推送待阅提醒用户周报提交")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/notice")
    public ResponseEntity<WeeklyDTO> notice(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.pushUserWeekly(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"周报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/read")
    public ResponseEntity<WeeklyDTO> read(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.haveRead(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"周报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/submit")
    public ResponseEntity<WeeklyDTO> submit(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.submit(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/fetchdefault")
	public ResponseEntity<List<WeeklyDTO>> fetchdefault(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchDefault(context) ;
        List<WeeklyDTO> list = weeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成周报报表", tags = {"周报"}, notes = "生成周报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/weeklies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzWeeklySearchContext context, HttpServletResponse response) {
        try {
            ibzweeklyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzweeklyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzweeklyRuntime);
        }
    }

    @ApiOperation(value = "打印周报", tags = {"周报"}, notes = "打印周报")
    @RequestMapping(method = RequestMethod.GET, value = "/weeklies/{weekly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("weekly_ids") Set<Long> weekly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzweeklyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzWeekly> domains = new ArrayList<>();
            for (Long weekly_id : weekly_ids) {
                domains.add(ibzweeklyService.get( weekly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzWeekly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzweeklyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", weekly_ids, e.getMessage()), Errors.INTERNALERROR, ibzweeklyRuntime);
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

