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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.HistoryRuntime;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("StandardAPI-history")
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
	@ApiOperation(value = "根据系统用户系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserBugAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户产品系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户产品系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProductAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserStoryAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserTaskAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserTestTaskAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户待办系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户待办系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/todos/{todo_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserTodoAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据项目产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectProductPlanAction(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据项目Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectBugAction(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据项目版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据项目任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据项目需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectStoryAction(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户产品版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户产品版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProductBuildAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户产品产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户产品产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProductProductPlanAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户产品需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户产品需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProductStoryAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectProductPlanAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectBugAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectBuildAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectTaskAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectStoryAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectTestReportAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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
	@ApiOperation(value = "根据系统用户项目测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统用户项目测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefaultBySysUserProjectTestTaskAction(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
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

