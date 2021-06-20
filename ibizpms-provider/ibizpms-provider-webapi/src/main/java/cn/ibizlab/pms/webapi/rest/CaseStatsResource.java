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
import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
import cn.ibizlab.pms.core.ibiz.service.ICaseStatsService;
import cn.ibizlab.pms.core.ibiz.filter.CaseStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.CaseStatsRuntime;

@Slf4j
@Api(tags = {"测试用例统计"})
@RestController("WebApi-casestats")
@RequestMapping("")
public class CaseStatsResource {

    @Autowired
    public ICaseStatsService casestatsService;

    @Autowired
    public CaseStatsRuntime casestatsRuntime;

    @Autowired
    @Lazy
    public CaseStatsMapping casestatsMapping;

    @PreAuthorize("quickTest('IBZ_CASESTATS', 'CREATE')")
    @ApiOperation(value = "新建测试用例统计", tags = {"测试用例统计" },  notes = "新建测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats")
    @Transactional
    public ResponseEntity<CaseStatsDTO> create(@Validated @RequestBody CaseStatsDTO casestatsdto) {
        CaseStats domain = casestatsMapping.toDomain(casestatsdto);
		casestatsService.create(domain);
        if(!casestatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        CaseStatsDTO dto = casestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = casestatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_CASESTATS', #casestats_id, 'READ')")
    @ApiOperation(value = "获取测试用例统计", tags = {"测试用例统计" },  notes = "获取测试用例统计")
	@RequestMapping(method = RequestMethod.GET, value = "/casestats/{casestats_id}")
    public ResponseEntity<CaseStatsDTO> get(@PathVariable("casestats_id") Long casestats_id) {
        CaseStats domain = casestatsService.get(casestats_id);
        CaseStatsDTO dto = casestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = casestatsRuntime.getOPPrivs(casestats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_CASESTATS', #casestats_id, 'DELETE')")
    @ApiOperation(value = "删除测试用例统计", tags = {"测试用例统计" },  notes = "删除测试用例统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/casestats/{casestats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("casestats_id") Long casestats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(casestatsService.remove(casestats_id));
    }

    @PreAuthorize("quickTest('IBZ_CASESTATS', 'DELETE')")
    @ApiOperation(value = "批量删除测试用例统计", tags = {"测试用例统计" },  notes = "批量删除测试用例统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/casestats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        casestatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_CASESTATS', #casestats_id, 'UPDATE')")
    @ApiOperation(value = "更新测试用例统计", tags = {"测试用例统计" },  notes = "更新测试用例统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/casestats/{casestats_id}")
    @Transactional
    public ResponseEntity<CaseStatsDTO> update(@PathVariable("casestats_id") Long casestats_id, @RequestBody CaseStatsDTO casestatsdto) {
		CaseStats domain  = casestatsMapping.toDomain(casestatsdto);
        domain.setId(casestats_id);
		casestatsService.update(domain );
        if(!casestatsRuntime.test(casestats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		CaseStatsDTO dto = casestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = casestatsRuntime.getOPPrivs(casestats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_CASESTATS', 'CREATE')")
    @ApiOperation(value = "检查测试用例统计", tags = {"测试用例统计" },  notes = "检查测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CaseStatsDTO casestatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestatsService.checkKey(casestatsMapping.toDomain(casestatsdto)));
    }

    @PreAuthorize("quickTest('IBZ_CASESTATS', 'CREATE')")
    @ApiOperation(value = "获取测试用例统计草稿", tags = {"测试用例统计" },  notes = "获取测试用例统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/casestats/getdraft")
    public ResponseEntity<CaseStatsDTO> getDraft(CaseStatsDTO dto) {
        CaseStats domain = casestatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(casestatsMapping.toDto(casestatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_CASESTATS', 'DENY')")
    @ApiOperation(value = "保存测试用例统计", tags = {"测试用例统计" },  notes = "保存测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/save")
    public ResponseEntity<CaseStatsDTO> save(@RequestBody CaseStatsDTO casestatsdto) {
        CaseStats domain = casestatsMapping.toDomain(casestatsdto);
        casestatsService.save(domain);
        CaseStatsDTO dto = casestatsMapping.toDto(domain);
        Map<String, Integer> opprivs = casestatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_CASESTATS', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"测试用例统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/casestats/fetchdefault")
	public ResponseEntity<List<CaseStatsDTO>> fetchdefault(@RequestBody CaseStatsSearchContext context) {
        Page<CaseStats> domains = casestatsService.searchDefault(context) ;
        List<CaseStatsDTO> list = casestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_CASESTATS', 'NONE')")
	@ApiOperation(value = "获取测试用例统计", tags = {"测试用例统计" } ,notes = "获取测试用例统计")
    @RequestMapping(method= RequestMethod.POST , value="/casestats/fetchtestcasestats")
	public ResponseEntity<List<CaseStatsDTO>> fetchtestcasestats(@RequestBody CaseStatsSearchContext context) {
        Page<CaseStats> domains = casestatsService.searchTestCaseStats(context) ;
        List<CaseStatsDTO> list = casestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试用例统计报表", tags = {"测试用例统计"}, notes = "生成测试用例统计报表")
    @RequestMapping(method = RequestMethod.GET, value = "/casestats/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, CaseStatsSearchContext context, HttpServletResponse response) {
        try {
            casestatsRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", casestatsRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, casestatsRuntime);
        }
    }

    @ApiOperation(value = "打印测试用例统计", tags = {"测试用例统计"}, notes = "打印测试用例统计")
    @RequestMapping(method = RequestMethod.GET, value = "/casestats/{casestats_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("casestats_ids") Set<Long> casestats_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = casestatsRuntime.getDEPrintRuntime(print_id);
        try {
            List<CaseStats> domains = new ArrayList<>();
            for (Long casestats_id : casestats_ids) {
                domains.add(casestatsService.get( casestats_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new CaseStats[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", casestatsRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", casestats_ids, e.getMessage()), Errors.INTERNALERROR, casestatsRuntime);
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

