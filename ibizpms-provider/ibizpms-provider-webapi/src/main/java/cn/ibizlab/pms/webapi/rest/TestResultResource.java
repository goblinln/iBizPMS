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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立测试结果", tags = {"测试结果" },  notes = "根据产品建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新测试结果", tags = {"测试结果" },  notes = "根据产品更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除测试结果", tags = {"测试结果" },  notes = "根据产品删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取测试结果", tags = {"测试结果" },  notes = "根据产品获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITE')")
    @ApiOperation(value = "根据产品行为", tags = {"测试结果" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/casefavorite")
    public ResponseEntity<TestResultDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.caseFavorite(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite", tags = {"测试结果" },  notes = "根据产品CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/casenfavorite")
    public ResponseEntity<TestResultDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.caseNFavorite(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查测试结果", tags = {"测试结果" },  notes = "根据产品检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRMCHANGE')")
    @ApiOperation(value = "根据产品确认用例变更", tags = {"测试结果" },  notes = "根据产品确认用例变更")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testresults/{testresult_id}/confirmchange")
    public ResponseEntity<TestResultDTO> confirmChangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.confirmChange(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品确认需求变更", tags = {"测试结果" },  notes = "根据产品确认需求变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/confirmstorychange")
    public ResponseEntity<TestResultDTO> confirmstorychangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.confirmstorychange(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取或者状态", tags = {"测试结果" },  notes = "根据产品根据测试单获取或者状态")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testresults/{testresult_id}/getbytesttask")
    public ResponseEntity<TestResultDTO> getByTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.getByTestTask(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASERESULT')")
    @ApiOperation(value = "根据产品获取测试单执行结果", tags = {"测试结果" },  notes = "根据产品获取测试单执行结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testresults/{testresult_id}/gettesttaskcntrun")
    public ResponseEntity<TestResultDTO> getTestTaskCntRunByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.getTestTaskCntRun(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品测试单关联测试用例", tags = {"测试结果" },  notes = "根据产品测试单关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/linkcase")
    public ResponseEntity<TestResultDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.linkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'STORYLINK')")
    @ApiOperation(value = "根据产品移动端关联需求", tags = {"测试结果" },  notes = "根据产品移动端关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/moblinkcase")
    public ResponseEntity<TestResultDTO> mobLinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.mobLinkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"测试结果" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/runcase")
    public ResponseEntity<TestResultDTO> runCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.runCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品runCases", tags = {"测试结果" },  notes = "根据产品runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/runcases")
    public ResponseEntity<TestResultDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.runCases(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存测试结果", tags = {"测试结果" },  notes = "根据产品保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存测试结果", tags = {"测试结果" },  notes = "根据产品批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
             
        }
        testresultService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"测试结果" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/testruncase")
    public ResponseEntity<TestResultDTO> testRunCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.testRunCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品testRunCases", tags = {"测试结果" },  notes = "根据产品testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/testruncases")
    public ResponseEntity<TestResultDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.testRunCases(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品套件关联", tags = {"测试结果" },  notes = "根据产品套件关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/testsuitelinkcase")
    public ResponseEntity<TestResultDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.testsuitelinkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"测试结果" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/unlinkcase")
    public ResponseEntity<TestResultDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品unlinkCases", tags = {"测试结果" },  notes = "根据产品unlinkCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/unlinkcases")
    public ResponseEntity<TestResultDTO> unlinkCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkCases(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"测试结果" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/unlinksuitecase")
    public ResponseEntity<TestResultDTO> unlinkSuiteCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkSuiteCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品unlinkSuiteCases", tags = {"测试结果" },  notes = "根据产品unlinkSuiteCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/unlinksuitecases")
    public ResponseEntity<TestResultDTO> unlinkSuiteCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkSuiteCases(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取批量新建用例", tags = {"测试结果" } ,notes = "根据产品获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchbatchnew")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultBatchNewByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchBatchNew(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的用例", tags = {"测试结果" } ,notes = "根据产品获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchcuropenedcase")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchCurOpenedCase(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试结果" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchcursuite")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchCurSuite(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试结果" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchcurtesttask")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchCurTestTask(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"测试结果" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchesbulk")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchESBulk(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchmodulereportcase")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchModuleRePortCase(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-按模块-条目", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchmodulereportcaseentry")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchModuleRePortCaseEntry(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-按模块", tags = {"测试结果" } ,notes = "根据产品获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchmodulereportcase_project")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchModuleRePortCase_Project(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"测试结果" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchmyfavorites")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchMyFavorites(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试结果" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchnotcurtestsuite")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchNotCurTestSuite(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试结果" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchnotcurtesttask")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchNotCurTestTask(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例（项目关联）", tags = {"测试结果" } ,notes = "根据产品获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchnotcurtesttaskproject")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchNotCurTestTaskProject(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchreportcase")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRePortCase(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例-条目", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchreportcaseentry")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRePortCaseEntry(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联用例-关联用例", tags = {"测试结果" } ,notes = "根据产品获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchreportcase_project")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRePortCase_Project(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunerreportcase")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunERRePortCase(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人-条目", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunerreportcaseentry")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunERRePortCaseEntry(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行人", tags = {"测试结果" } ,notes = "根据产品获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunerreportcase_project")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunERRePortCase_Project(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunreportcase")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunRePortCase(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联--执行结果条目", tags = {"测试结果" } ,notes = "根据产品获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunreportcaseentry")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunRePortCaseEntry(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行结果", tags = {"测试结果" } ,notes = "根据产品获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchrunreportcase_project")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchRunRePortCase_Project(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本建立测试结果", tags = {"测试结果" },  notes = "根据测试版本建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testresults")
    public ResponseEntity<TestResultDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新测试结果", tags = {"测试结果" },  notes = "根据测试版本更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本删除测试结果", tags = {"测试结果" },  notes = "根据测试版本删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取测试结果", tags = {"测试结果" },  notes = "根据测试版本获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本获取测试结果草稿", tags = {"测试结果" },  notes = "根据测试版本获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本检查测试结果", tags = {"测试结果" },  notes = "根据测试版本检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据测试版本保存测试结果", tags = {"测试结果" },  notes = "根据测试版本保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取DEFAULT", tags = {"测试结果" } ,notes = "根据测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立测试结果", tags = {"测试结果" },  notes = "根据产品建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新测试结果", tags = {"测试结果" },  notes = "根据产品更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除测试结果", tags = {"测试结果" },  notes = "根据产品删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取测试结果", tags = {"测试结果" },  notes = "根据产品获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品激活", tags = {"测试结果" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/activate")
    public ResponseEntity<TestResultDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.activate(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品阻塞", tags = {"测试结果" },  notes = "根据产品阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/block")
    public ResponseEntity<TestResultDTO> blockByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.block(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查测试结果", tags = {"测试结果" },  notes = "根据产品检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据产品关闭", tags = {"测试结果" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/close")
    public ResponseEntity<TestResultDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.close(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试结果" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/linkcase")
    public ResponseEntity<TestResultDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.linkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品移动端测试版本计数器", tags = {"测试结果" },  notes = "根据产品移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/mobtesttaskcounter")
    public ResponseEntity<TestResultDTO> mobTestTaskCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.mobTestTaskCounter(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品保存测试结果", tags = {"测试结果" },  notes = "根据产品保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品开始", tags = {"测试结果" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/start")
    public ResponseEntity<TestResultDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.start(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试结果" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testresults/{testresult_id}/unlinkcase")
    public ResponseEntity<TestResultDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的测试单", tags = {"测试结果" } ,notes = "根据产品获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testresults/fetchmytesttaskpc")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchMyTestTaskPc(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本建立测试结果", tags = {"测试结果" },  notes = "根据产品测试版本建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新测试结果", tags = {"测试结果" },  notes = "根据产品测试版本更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除测试结果", tags = {"测试结果" },  notes = "根据产品测试版本删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取测试结果", tags = {"测试结果" },  notes = "根据产品测试版本获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品测试版本获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本检查测试结果", tags = {"测试结果" },  notes = "根据产品测试版本检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据产品测试版本保存测试结果", tags = {"测试结果" },  notes = "根据产品测试版本保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立测试结果", tags = {"测试结果" },  notes = "根据项目建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新测试结果", tags = {"测试结果" },  notes = "根据项目更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除测试结果", tags = {"测试结果" },  notes = "根据项目删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取测试结果", tags = {"测试结果" },  notes = "根据项目获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取测试结果草稿", tags = {"测试结果" },  notes = "根据项目获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目激活", tags = {"测试结果" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/activate")
    public ResponseEntity<TestResultDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.activate(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据项目阻塞", tags = {"测试结果" },  notes = "根据项目阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/block")
    public ResponseEntity<TestResultDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.block(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查测试结果", tags = {"测试结果" },  notes = "根据项目检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据项目关闭", tags = {"测试结果" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/close")
    public ResponseEntity<TestResultDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.close(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试结果" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/linkcase")
    public ResponseEntity<TestResultDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.linkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据项目移动端测试版本计数器", tags = {"测试结果" },  notes = "根据项目移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/mobtesttaskcounter")
    public ResponseEntity<TestResultDTO> mobTestTaskCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.mobTestTaskCounter(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据项目保存测试结果", tags = {"测试结果" },  notes = "根据项目保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目开始", tags = {"测试结果" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/start")
    public ResponseEntity<TestResultDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.start(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试结果" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testresults/{testresult_id}/unlinkcase")
    public ResponseEntity<TestResultDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
        domain = testresultService.unlinkCase(domain) ;
        testresultdto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试结果" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的测试单", tags = {"测试结果" } ,notes = "根据项目获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testresults/fetchmytesttaskpc")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultMyTestTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchMyTestTaskPc(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立测试结果", tags = {"测试结果" },  notes = "根据项目测试版本建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新测试结果", tags = {"测试结果" },  notes = "根据项目测试版本更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除测试结果", tags = {"测试结果" },  notes = "根据项目测试版本删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取测试结果", tags = {"测试结果" },  notes = "根据项目测试版本获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取测试结果草稿", tags = {"测试结果" },  notes = "根据项目测试版本获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本检查测试结果", tags = {"测试结果" },  notes = "根据项目测试版本检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据项目测试版本保存测试结果", tags = {"测试结果" },  notes = "根据项目测试版本保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取DEFAULT", tags = {"测试结果" } ,notes = "根据项目测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody TestResultSearchContext context) {
        
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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
}

