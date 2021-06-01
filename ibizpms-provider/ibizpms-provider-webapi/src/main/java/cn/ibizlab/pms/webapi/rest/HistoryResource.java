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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.HistoryRuntime;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("WebApi-history")
@RequestMapping("")
public class HistoryResource {

    @Autowired
    public IHistoryService historyService;

    @Autowired
    public HistoryRuntime historyRuntime;

    @Autowired
    @Lazy
    public HistoryMapping historyMapping;

    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"操作历史" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchdefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/histories/{history_id}/{action}")
    public ResponseEntity<HistoryDTO> dynamicCall(@PathVariable("history_id") Long history_id , @PathVariable("action") String action , @RequestBody HistoryDTO historydto) {
        History domain = historyService.dynamicCall(history_id, action, historyMapping.toDomain(historydto));
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id, 'READ')")
	@ApiOperation(value = "根据系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByAction(@PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@BugRuntime.test(#bug_id, 'READ')")
	@ApiOperation(value = "根据Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id, 'READ')")
	@ApiOperation(value = "根据产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@TaskRuntime.test(#task_id, 'READ')")
	@ApiOperation(value = "根据任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ReleaseRuntime.test(#release_id, 'READ')")
	@ApiOperation(value = "根据发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'READ')")
	@ApiOperation(value = "根据周报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据周报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'READ')")
	@ApiOperation(value = "根据待办系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据待办系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@TestReportRuntime.test(#testreport_id, 'READ')")
	@ApiOperation(value = "根据测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@DocLibRuntime.test(#doclib_id, 'READ')")
	@ApiOperation(value = "根据文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@DocRuntime.test(#doc_id, 'READ')")
	@ApiOperation(value = "根据文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'READ')")
	@ApiOperation(value = "根据日报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据日报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id, 'READ')")
	@ApiOperation(value = "根据月报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据月报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id, 'READ')")
	@ApiOperation(value = "根据汇报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据汇报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id, 'READ')")
	@ApiOperation(value = "根据测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
	@ApiOperation(value = "根据测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@CaseRuntime.test(#case_id, 'READ')")
	@ApiOperation(value = "根据测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@BuildRuntime.test(#build_id, 'READ')")
	@ApiOperation(value = "根据版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id, 'READ')")
	@ApiOperation(value = "根据用例库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据用例库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@StoryRuntime.test(#story_id, 'READ')")
	@ApiOperation(value = "根据需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@DocLibRuntime.test(#doclib_id, 'READ')")
	@ApiOperation(value = "根据文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
	@ApiOperation(value = "根据项目任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

