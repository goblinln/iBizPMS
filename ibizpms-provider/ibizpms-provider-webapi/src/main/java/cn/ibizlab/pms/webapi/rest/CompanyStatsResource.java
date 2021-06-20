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
import cn.ibizlab.pms.core.ibiz.domain.CompanyStats;
import cn.ibizlab.pms.core.ibiz.service.ICompanyStatsService;
import cn.ibizlab.pms.core.ibiz.filter.CompanyStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.CompanyStatsRuntime;

@Slf4j
@Api(tags = {"公司动态汇总"})
@RestController("WebApi-companystats")
@RequestMapping("")
public class CompanyStatsResource {

    @Autowired
    public ICompanyStatsService companystatsService;

    @Autowired
    public CompanyStatsRuntime companystatsRuntime;

    @Autowired
    @Lazy
    public CompanyStatsMapping companystatsMapping;

    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'CREATE')")
    @ApiOperation(value = "新建公司动态汇总", tags = {"公司动态汇总" },  notes = "新建公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats")
    @Transactional
    public ResponseEntity<CompanyStatsDTO> create(@Validated @RequestBody CompanyStatsDTO companystatsdto) {
        CompanyStats domain = companystatsMapping.toDomain(companystatsdto);
		companystatsService.create(domain);
        if(!companystatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        Map<String, Integer> opprivs = companystatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_COMPANYSTATS', #companystats_id, 'READ')")
    @ApiOperation(value = "获取公司动态汇总", tags = {"公司动态汇总" },  notes = "获取公司动态汇总")
	@RequestMapping(method = RequestMethod.GET, value = "/companystats/{companystats_id}")
    public ResponseEntity<CompanyStatsDTO> get(@PathVariable("companystats_id") Long companystats_id) {
        CompanyStats domain = companystatsService.get(companystats_id);
        CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        Map<String, Integer> opprivs = companystatsRuntime.getOPPrivs(companystats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_COMPANYSTATS', #companystats_id, 'DELETE')")
    @ApiOperation(value = "删除公司动态汇总", tags = {"公司动态汇总" },  notes = "删除公司动态汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companystats/{companystats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("companystats_id") Long companystats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(companystatsService.remove(companystats_id));
    }

    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'DELETE')")
    @ApiOperation(value = "批量删除公司动态汇总", tags = {"公司动态汇总" },  notes = "批量删除公司动态汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companystats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        companystatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_COMPANYSTATS', #companystats_id, 'UPDATE')")
    @ApiOperation(value = "更新公司动态汇总", tags = {"公司动态汇总" },  notes = "更新公司动态汇总")
	@RequestMapping(method = RequestMethod.PUT, value = "/companystats/{companystats_id}")
    @Transactional
    public ResponseEntity<CompanyStatsDTO> update(@PathVariable("companystats_id") Long companystats_id, @RequestBody CompanyStatsDTO companystatsdto) {
		CompanyStats domain  = companystatsMapping.toDomain(companystatsdto);
        domain.setId(companystats_id);
		companystatsService.update(domain );
        if(!companystatsRuntime.test(companystats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        Map<String, Integer> opprivs = companystatsRuntime.getOPPrivs(companystats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'CREATE')")
    @ApiOperation(value = "检查公司动态汇总", tags = {"公司动态汇总" },  notes = "检查公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CompanyStatsDTO companystatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(companystatsService.checkKey(companystatsMapping.toDomain(companystatsdto)));
    }

    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'CREATE')")
    @ApiOperation(value = "获取公司动态汇总草稿", tags = {"公司动态汇总" },  notes = "获取公司动态汇总草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/companystats/getdraft")
    public ResponseEntity<CompanyStatsDTO> getDraft(CompanyStatsDTO dto) {
        CompanyStats domain = companystatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(companystatsMapping.toDto(companystatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'DENY')")
    @ApiOperation(value = "保存公司动态汇总", tags = {"公司动态汇总" },  notes = "保存公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/save")
    public ResponseEntity<CompanyStatsDTO> save(@RequestBody CompanyStatsDTO companystatsdto) {
        CompanyStats domain = companystatsMapping.toDomain(companystatsdto);
        companystatsService.save(domain);
        CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        Map<String, Integer> opprivs = companystatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'NONE')")
	@ApiOperation(value = "获取公司动态统计", tags = {"公司动态汇总" } ,notes = "获取公司动态统计")
    @RequestMapping(method= RequestMethod.POST , value="/companystats/fetchcompanydynamicstats")
	public ResponseEntity<List<CompanyStatsDTO>> fetchcompanydynamicstats(@RequestBody CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchCompanyDynamicStats(context) ;
        List<CompanyStatsDTO> list = companystatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_COMPANYSTATS', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"公司动态汇总" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/companystats/fetchdefault")
	public ResponseEntity<List<CompanyStatsDTO>> fetchdefault(@RequestBody CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchDefault(context) ;
        List<CompanyStatsDTO> list = companystatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成公司动态汇总报表", tags = {"公司动态汇总"}, notes = "生成公司动态汇总报表")
    @RequestMapping(method = RequestMethod.GET, value = "/companystats/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, CompanyStatsSearchContext context, HttpServletResponse response) {
        try {
            companystatsRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", companystatsRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, companystatsRuntime);
        }
    }

    @ApiOperation(value = "打印公司动态汇总", tags = {"公司动态汇总"}, notes = "打印公司动态汇总")
    @RequestMapping(method = RequestMethod.GET, value = "/companystats/{companystats_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("companystats_ids") Set<Long> companystats_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = companystatsRuntime.getDEPrintRuntime(print_id);
        try {
            List<CompanyStats> domains = new ArrayList<>();
            for (Long companystats_id : companystats_ids) {
                domains.add(companystatsService.get( companystats_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new CompanyStats[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", companystatsRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", companystats_ids, e.getMessage()), Errors.INTERNALERROR, companystatsRuntime);
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

