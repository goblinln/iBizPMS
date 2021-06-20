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
import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.service.IBugStatsService;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.BugStatsRuntime;

@Slf4j
@Api(tags = {"Bug统计"})
@RestController("WebApi-bugstats")
@RequestMapping("")
public class BugStatsResource {

    @Autowired
    public IBugStatsService bugstatsService;

    @Autowired
    public BugStatsRuntime bugstatsRuntime;

    @Autowired
    @Lazy
    public BugStatsMapping bugstatsMapping;

    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'CREATE')")
    @ApiOperation(value = "新建Bug统计", tags = {"Bug统计" },  notes = "新建Bug统计")
	@RequestMapping(method = RequestMethod.POST, value = "/bugstats")
    @Transactional
    public ResponseEntity<BugStatsDTO> create(@Validated @RequestBody BugStatsDTO bugstatsdto) {
        BugStats domain = bugstatsMapping.toDomain(bugstatsdto);
		bugstatsService.create(domain);
        if(!bugstatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BugStatsDTO dto = bugstatsMapping.toDto(domain);
        Map<String, Integer> opprivs = bugstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_BUGSTATS', #bugstats_id, 'READ')")
    @ApiOperation(value = "获取Bug统计", tags = {"Bug统计" },  notes = "获取Bug统计")
	@RequestMapping(method = RequestMethod.GET, value = "/bugstats/{bugstats_id}")
    public ResponseEntity<BugStatsDTO> get(@PathVariable("bugstats_id") Long bugstats_id) {
        BugStats domain = bugstatsService.get(bugstats_id);
        BugStatsDTO dto = bugstatsMapping.toDto(domain);
        Map<String, Integer> opprivs = bugstatsRuntime.getOPPrivs(bugstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_BUGSTATS', #bugstats_id, 'DELETE')")
    @ApiOperation(value = "删除Bug统计", tags = {"Bug统计" },  notes = "删除Bug统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugstats/{bugstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("bugstats_id") Long bugstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(bugstatsService.remove(bugstats_id));
    }

    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'DELETE')")
    @ApiOperation(value = "批量删除Bug统计", tags = {"Bug统计" },  notes = "批量删除Bug统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        bugstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_BUGSTATS', #bugstats_id, 'UPDATE')")
    @ApiOperation(value = "更新Bug统计", tags = {"Bug统计" },  notes = "更新Bug统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugstats/{bugstats_id}")
    @Transactional
    public ResponseEntity<BugStatsDTO> update(@PathVariable("bugstats_id") Long bugstats_id, @RequestBody BugStatsDTO bugstatsdto) {
		BugStats domain  = bugstatsMapping.toDomain(bugstatsdto);
        domain.setId(bugstats_id);
		bugstatsService.update(domain );
        if(!bugstatsRuntime.test(bugstats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		BugStatsDTO dto = bugstatsMapping.toDto(domain);
        Map<String, Integer> opprivs = bugstatsRuntime.getOPPrivs(bugstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'CREATE')")
    @ApiOperation(value = "检查Bug统计", tags = {"Bug统计" },  notes = "检查Bug统计")
	@RequestMapping(method = RequestMethod.POST, value = "/bugstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BugStatsDTO bugstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugstatsService.checkKey(bugstatsMapping.toDomain(bugstatsdto)));
    }

    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'CREATE')")
    @ApiOperation(value = "获取Bug统计草稿", tags = {"Bug统计" },  notes = "获取Bug统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/bugstats/getdraft")
    public ResponseEntity<BugStatsDTO> getDraft(BugStatsDTO dto) {
        BugStats domain = bugstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(bugstatsMapping.toDto(bugstatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'DENY')")
    @ApiOperation(value = "保存Bug统计", tags = {"Bug统计" },  notes = "保存Bug统计")
	@RequestMapping(method = RequestMethod.POST, value = "/bugstats/save")
    public ResponseEntity<BugStatsDTO> save(@RequestBody BugStatsDTO bugstatsdto) {
        BugStats domain = bugstatsMapping.toDomain(bugstatsdto);
        bugstatsService.save(domain);
        BugStatsDTO dto = bugstatsMapping.toDto(domain);
        Map<String, Integer> opprivs = bugstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取Bug在每个解决方案的Bug数", tags = {"Bug统计" } ,notes = "获取Bug在每个解决方案的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchbugcountinresolution")
	public ResponseEntity<List<BugStatsDTO>> fetchbugcountinresolution(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchBugCountInResolution(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取Bug完成表", tags = {"Bug统计" } ,notes = "获取Bug完成表")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchbugresolvedby")
	public ResponseEntity<List<BugStatsDTO>> fetchbugresolvedby(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchBugResolvedBy(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取bug解决汇总表", tags = {"Bug统计" } ,notes = "获取bug解决汇总表")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchbugresolvedgird")
	public ResponseEntity<List<BugStatsDTO>> fetchbugresolvedgird(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchBugResolvedGird(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取Bug指派表", tags = {"Bug统计" } ,notes = "获取Bug指派表")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchbugassignedto")
	public ResponseEntity<List<BugStatsDTO>> fetchbugassignedto(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchBugassignedTo(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"Bug统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchdefault")
	public ResponseEntity<List<BugStatsDTO>> fetchdefault(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchDefault(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取产品Bug解决方案汇总", tags = {"Bug统计" } ,notes = "获取产品Bug解决方案汇总")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchproductbugresolutionstats")
	public ResponseEntity<List<BugStatsDTO>> fetchproductbugresolutionstats(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchProductBugResolutionStats(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取产品Bug状态汇总", tags = {"Bug统计" } ,notes = "获取产品Bug状态汇总")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchproductbugstatussum")
	public ResponseEntity<List<BugStatsDTO>> fetchproductbugstatussum(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchProductBugStatusSum(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取产品创建bug占比", tags = {"Bug统计" } ,notes = "获取产品创建bug占比")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchproductcreatebug")
	public ResponseEntity<List<BugStatsDTO>> fetchproductcreatebug(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchProductCreateBug(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_BUGSTATS', 'NONE')")
	@ApiOperation(value = "获取项目bug状态统计", tags = {"Bug统计" } ,notes = "获取项目bug状态统计")
    @RequestMapping(method= RequestMethod.POST , value="/bugstats/fetchprojectbugstatuscount")
	public ResponseEntity<List<BugStatsDTO>> fetchprojectbugstatuscount(@RequestBody BugStatsSearchContext context) {
        Page<BugStats> domains = bugstatsService.searchProjectBugStatusCount(context) ;
        List<BugStatsDTO> list = bugstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成Bug统计报表", tags = {"Bug统计"}, notes = "生成Bug统计报表")
    @RequestMapping(method = RequestMethod.GET, value = "/bugstats/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, BugStatsSearchContext context, HttpServletResponse response) {
        try {
            bugstatsRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", bugstatsRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, bugstatsRuntime);
        }
    }

    @ApiOperation(value = "打印Bug统计", tags = {"Bug统计"}, notes = "打印Bug统计")
    @RequestMapping(method = RequestMethod.GET, value = "/bugstats/{bugstats_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("bugstats_ids") Set<Long> bugstats_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = bugstatsRuntime.getDEPrintRuntime(print_id);
        try {
            List<BugStats> domains = new ArrayList<>();
            for (Long bugstats_id : bugstats_ids) {
                domains.add(bugstatsService.get( bugstats_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new BugStats[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", bugstatsRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", bugstats_ids, e.getMessage()), Errors.INTERNALERROR, bugstatsRuntime);
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

