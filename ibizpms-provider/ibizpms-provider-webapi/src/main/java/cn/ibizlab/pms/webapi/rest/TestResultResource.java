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
import cn.ibizlab.pms.core.zentao.domain.TestResult;
import cn.ibizlab.pms.core.zentao.service.ITestResultService;
import cn.ibizlab.pms.core.zentao.filter.TestResultSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestResultRuntime;

@Slf4j
@Api(tags = {"测试结果"})
@RestController("WebApi-testresult")
@RequestMapping("")
public class TestResultResource {

    @Autowired
    public ITestResultService testresultService;

    @Autowired
    public TestResultRuntime testresultRuntime;

    @Autowired
    @Lazy
    public TestResultMapping testresultMapping;

    @PreAuthorize("quickTest('ZT_TESTRESULT', 'CREATE')")
    @ApiOperation(value = "新建测试结果", tags = {"测试结果" },  notes = "新建测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults")
    @Transactional
    public ResponseEntity<TestResultDTO> create(@Validated @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
		testresultService.create(domain);
        if(!testresultRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String, Integer> opprivs = testresultRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTRESULT', #testresult_id, 'READ')")
    @ApiOperation(value = "获取测试结果", tags = {"测试结果" },  notes = "获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> get(@PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String, Integer> opprivs = testresultRuntime.getOPPrivs(testresult_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTRESULT', #testresult_id, 'DELETE')")
    @ApiOperation(value = "删除测试结果", tags = {"测试结果" },  notes = "删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testresults/{testresult_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testresult_id") Long testresult_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("quickTest('ZT_TESTRESULT', 'DELETE')")
    @ApiOperation(value = "批量删除测试结果", tags = {"测试结果" },  notes = "批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testresults/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTRESULT', #testresult_id, 'UPDATE')")
    @ApiOperation(value = "更新测试结果", tags = {"测试结果" },  notes = "更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/testresults/{testresult_id}")
    @Transactional
    public ResponseEntity<TestResultDTO> update(@PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
		TestResult domain  = testresultMapping.toDomain(testresultdto);
        domain.setId(testresult_id);
		testresultService.update(domain );
        if(!testresultRuntime.test(testresult_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String, Integer> opprivs = testresultRuntime.getOPPrivs(testresult_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTRESULT', 'CREATE')")
    @ApiOperation(value = "检查测试结果", tags = {"测试结果" },  notes = "检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @PreAuthorize("quickTest('ZT_TESTRESULT', 'CREATE')")
    @ApiOperation(value = "获取测试结果草稿", tags = {"测试结果" },  notes = "获取测试结果草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraft(TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTRESULT', 'DENY')")
    @ApiOperation(value = "保存测试结果", tags = {"测试结果" },  notes = "保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/save")
    public ResponseEntity<TestResultDTO> save(@RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        testresultService.save(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String, Integer> opprivs = testresultRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTRESULT', 'READ')")
	@ApiOperation(value = "获取CurTestRun", tags = {"测试结果" } ,notes = "获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchcurtestrun(@RequestBody TestResultSearchContext context) {
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTRESULT', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试结果" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchdefault(@RequestBody TestResultSearchContext context) {
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试结果报表", tags = {"测试结果"}, notes = "生成测试结果报表")
    @RequestMapping(method = RequestMethod.GET, value = "/testresults/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TestResultSearchContext context, HttpServletResponse response) {
        try {
            testresultRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testresultRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, testresultRuntime);
        }
    }

    @ApiOperation(value = "打印测试结果", tags = {"测试结果"}, notes = "打印测试结果")
    @RequestMapping(method = RequestMethod.GET, value = "/testresults/{testresult_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("testresult_ids") Set<Long> testresult_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = testresultRuntime.getDEPrintRuntime(print_id);
        try {
            List<TestResult> domains = new ArrayList<>();
            for (Long testresult_id : testresult_ids) {
                domains.add(testresultService.get( testresult_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new TestResult[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testresultRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", testresult_ids, e.getMessage()), Errors.INTERNALERROR, testresultRuntime);
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

