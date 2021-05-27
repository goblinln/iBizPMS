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
import cn.ibizlab.pms.core.zentao.domain.TestResult;
import cn.ibizlab.pms.core.zentao.service.ITestResultService;
import cn.ibizlab.pms.core.zentao.filter.TestResultSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestResultRuntime;

@Slf4j
@Api(tags = {"测试结果" })
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

    @PreAuthorize("@TestResultRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试结果", tags = {"测试结果" },  notes = "新建测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults")
    @Transactional
    public ResponseEntity<TestResultDTO> create(@Validated @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
		testresultService.create(domain);
        if(!testresultRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String,Integer> opprivs = testresultRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestResultRuntime.test(#testresult_id,'UPDATE')")
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
        Map<String,Integer> opprivs = testresultRuntime.getOPPrivs(testresult_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestResultRuntime.test(#testresult_id,'DELETE')")
    @ApiOperation(value = "删除测试结果", tags = {"测试结果" },  notes = "删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testresults/{testresult_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testresult_id") Long testresult_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@TestResultRuntime.test(#testresult_id,'READ')")
    @ApiOperation(value = "获取测试结果", tags = {"测试结果" },  notes = "获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> get(@PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String,Integer> opprivs = testresultRuntime.getOPPrivs(testresult_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestResultRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取测试结果草稿", tags = {"测试结果" },  notes = "获取测试结果草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraft(TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@TestResultRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查测试结果", tags = {"测试结果" },  notes = "检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "保存测试结果", tags = {"测试结果" },  notes = "保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/save")
    public ResponseEntity<TestResultDTO> save(@RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        testresultService.save(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        Map<String,Integer> opprivs = testresultRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestResultRuntime.quickTest('READ')")
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

    @PreAuthorize("@TestResultRuntime.quickTest('READ')")
	@ApiOperation(value = "查询CurTestRun", tags = {"测试结果" } ,notes = "查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchCurTestRun(@RequestBody TestResultSearchContext context) {
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TestResultRuntime.quickTest('READ')")
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

    @PreAuthorize("@TestResultRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"测试结果" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchDefault(@RequestBody TestResultSearchContext context) {
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testresults/{testresult_id}/{action}")
    public ResponseEntity<TestResultDTO> dynamicCall(@PathVariable("testresult_id") Long testresult_id , @PathVariable("action") String action , @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultService.dynamicCall(testresult_id, action, testresultMapping.toDomain(testresultdto));
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }
    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'CREATE')")
    @ApiOperation(value = "根据测试运行建立测试结果", tags = {"测试结果" },  notes = "根据测试运行建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns/{testrun_id}/testresults")
    public ResponseEntity<TestResultDTO> createByTestRun(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据测试运行更新测试结果", tags = {"测试结果" },  notes = "根据测试运行更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByTestRun(@PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据测试运行删除测试结果", tags = {"测试结果" },  notes = "根据测试运行删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByTestRun(@PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据测试运行获取测试结果", tags = {"测试结果" },  notes = "根据测试运行获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByTestRun(@PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'CREATE')")
    @ApiOperation(value = "根据测试运行获取测试结果草稿", tags = {"测试结果" },  notes = "根据测试运行获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testruns/{testrun_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByTestRun(@PathVariable("testrun_id") Long testrun_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setRun(testrun_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'CREATE')")
    @ApiOperation(value = "根据测试运行检查测试结果", tags = {"测试结果" },  notes = "根据测试运行检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns/{testrun_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestRun(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据测试运行保存测试结果", tags = {"测试结果" },  notes = "根据测试运行保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns/{testrun_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByTestRun(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
	@ApiOperation(value = "根据测试运行获取CurTestRun", tags = {"测试结果" } ,notes = "根据测试运行获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/{testrun_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByTestRun(@PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
	@ApiOperation(value = "根据测试运行查询CurTestRun", tags = {"测试结果" } ,notes = "根据测试运行查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/{testrun_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByTestRun(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
	@ApiOperation(value = "根据测试运行获取DEFAULT", tags = {"测试结果" } ,notes = "根据测试运行获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/{testrun_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByTestRun(@PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
	@ApiOperation(value = "根据测试运行查询DEFAULT", tags = {"测试结果" } ,notes = "根据测试运行查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/{testrun_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByTestRun(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例建立测试结果", tags = {"测试结果" },  notes = "根据测试用例建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testresults")
    public ResponseEntity<TestResultDTO> createByCase(@PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例更新测试结果", tags = {"测试结果" },  notes = "根据测试用例更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByCase(@PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例删除测试结果", tags = {"测试结果" },  notes = "根据测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByCase(@PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取测试结果", tags = {"测试结果" },  notes = "根据测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByCase(@PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例检查测试结果", tags = {"测试结果" },  notes = "根据测试用例检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByCase(@PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据测试用例保存测试结果", tags = {"测试结果" },  notes = "根据测试用例保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByCase(@PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取CurTestRun", tags = {"测试结果" } ,notes = "根据测试用例获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByCase(@PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询CurTestRun", tags = {"测试结果" } ,notes = "根据测试用例查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByCase(@PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"测试结果" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByCase(@PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询DEFAULT", tags = {"测试结果" } ,notes = "根据测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByCase(@PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例建立测试结果", tags = {"测试结果" },  notes = "根据产品测试用例建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例更新测试结果", tags = {"测试结果" },  notes = "根据产品测试用例更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除测试结果", tags = {"测试结果" },  notes = "根据产品测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取测试结果", tags = {"测试结果" },  notes = "根据产品测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例检查测试结果", tags = {"测试结果" },  notes = "根据产品测试用例检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据产品测试用例保存测试结果", tags = {"测试结果" },  notes = "根据产品测试用例保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取CurTestRun", tags = {"测试结果" } ,notes = "根据产品测试用例获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询CurTestRun", tags = {"测试结果" } ,notes = "根据产品测试用例查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询DEFAULT", tags = {"测试结果" } ,notes = "根据产品测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试运行建立测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults")
    public ResponseEntity<TestResultDTO> createByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本测试运行更新测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本测试运行删除测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本测试运行获取测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试运行获取测试结果草稿", tags = {"测试结果" },  notes = "根据测试版本测试运行获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setRun(testrun_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试运行检查测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据测试版本测试运行保存测试结果", tags = {"测试结果" },  notes = "根据测试版本测试运行保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试运行获取CurTestRun", tags = {"测试结果" } ,notes = "根据测试版本测试运行获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试运行查询CurTestRun", tags = {"测试结果" } ,notes = "根据测试版本测试运行查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试运行获取DEFAULT", tags = {"测试结果" } ,notes = "根据测试版本测试运行获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试运行查询DEFAULT", tags = {"测试结果" } ,notes = "根据测试版本测试运行查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByTestTaskTestRun(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试运行建立测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本测试运行更新测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本测试运行删除测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本测试运行获取测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试运行获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品测试版本测试运行获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setRun(testrun_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试运行检查测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据产品测试版本测试运行保存测试结果", tags = {"测试结果" },  notes = "根据产品测试版本测试运行保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试运行获取CurTestRun", tags = {"测试结果" } ,notes = "根据产品测试版本测试运行获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试运行查询CurTestRun", tags = {"测试结果" } ,notes = "根据产品测试版本测试运行查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试运行获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品测试版本测试运行获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试运行查询DEFAULT", tags = {"测试结果" } ,notes = "根据产品测试版本测试运行查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByProductTestTaskTestRun(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试运行建立测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本测试运行更新测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本测试运行删除测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本测试运行获取测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试运行获取测试结果草稿", tags = {"测试结果" },  notes = "根据项目测试版本测试运行获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setRun(testrun_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试运行检查测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据项目测试版本测试运行保存测试结果", tags = {"测试结果" },  notes = "根据项目测试版本测试运行保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setRun(testrun_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试运行获取CurTestRun", tags = {"测试结果" } ,notes = "根据项目测试版本测试运行获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试运行查询CurTestRun", tags = {"测试结果" } ,notes = "根据项目测试版本测试运行查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试运行获取DEFAULT", tags = {"测试结果" } ,notes = "根据项目测试版本测试运行获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id,@RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试运行查询DEFAULT", tags = {"测试结果" } ,notes = "根据项目测试版本测试运行查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByProjectTestTaskTestRun(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestResultSearchContext context) {
        context.setN_run_eq(testrun_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

