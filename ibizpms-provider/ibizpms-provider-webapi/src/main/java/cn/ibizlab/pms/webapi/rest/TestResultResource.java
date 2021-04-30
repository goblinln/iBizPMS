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
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestResultRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试结果", tags = {"测试结果" },  notes = "批量新建测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<TestResultDTO> testresultdtos) {
        testresultService.createBatch(testresultMapping.toDomain(testresultdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestResultRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试结果", tags = {"测试结果" },  notes = "批量更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/testresults/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<TestResultDTO> testresultdtos) {
        testresultService.updateBatch(testresultMapping.toDomain(testresultdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestResultRuntime.test(#testresult_id,'DELETE')")
    @ApiOperation(value = "删除测试结果", tags = {"测试结果" },  notes = "删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testresults/{testresult_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testresult_id") Long testresult_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("@TestResultRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试结果", tags = {"测试结果" },  notes = "批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testresults/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestResultRuntime.test(#testresult_id,'READ')")
    @ApiOperation(value = "获取测试结果", tags = {"测试结果" },  notes = "获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> get(@PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试结果草稿", tags = {"测试结果" },  notes = "获取测试结果草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraft(TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

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
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存测试结果", tags = {"测试结果" },  notes = "批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TestResultDTO> testresultdtos) {
        testresultService.saveBatch(testresultMapping.toDomain(testresultdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例批量建立测试结果", tags = {"测试结果" },  notes = "根据测试用例批量建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> createBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例批量更新测试结果", tags = {"测试结果" },  notes = "根据测试用例批量更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> updateBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例删除测试结果", tags = {"测试结果" },  notes = "根据测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByCase(@PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例批量删除测试结果", tags = {"测试结果" },  notes = "根据测试用例批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> removeBatchByCase(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取测试结果", tags = {"测试结果" },  notes = "根据测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByCase(@PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

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

    @ApiOperation(value = "根据测试用例批量保存测试结果", tags = {"测试结果" },  notes = "根据测试用例批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
             domain.setIbizcase(case_id);
        }
        testresultService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例批量建立测试结果", tags = {"测试结果" },  notes = "根据产品测试用例批量建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> createBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例批量更新测试结果", tags = {"测试结果" },  notes = "根据产品测试用例批量更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> updateBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除测试结果", tags = {"测试结果" },  notes = "根据产品测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例批量删除测试结果", tags = {"测试结果" },  notes = "根据产品测试用例批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> removeBatchByProductCase(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取测试结果", tags = {"测试结果" },  notes = "根据产品测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

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

    @ApiOperation(value = "根据产品测试用例批量保存测试结果", tags = {"测试结果" },  notes = "根据产品测试用例批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
             domain.setIbizcase(case_id);
        }
        testresultService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求测试用例建立测试结果", tags = {"测试结果" },  notes = "根据需求测试用例建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/testresults")
    public ResponseEntity<TestResultDTO> createByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求测试用例批量建立测试结果", tags = {"测试结果" },  notes = "根据需求测试用例批量建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> createBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求测试用例更新测试结果", tags = {"测试结果" },  notes = "根据需求测试用例更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求测试用例批量更新测试结果", tags = {"测试结果" },  notes = "根据需求测试用例批量更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> updateBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求测试用例删除测试结果", tags = {"测试结果" },  notes = "根据需求测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求测试用例批量删除测试结果", tags = {"测试结果" },  notes = "根据需求测试用例批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> removeBatchByStoryCase(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求测试用例获取测试结果", tags = {"测试结果" },  notes = "根据需求测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据需求测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求测试用例检查测试结果", tags = {"测试结果" },  notes = "根据需求测试用例检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据需求测试用例保存测试结果", tags = {"测试结果" },  notes = "根据需求测试用例保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求测试用例批量保存测试结果", tags = {"测试结果" },  notes = "根据需求测试用例批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
             domain.setIbizcase(case_id);
        }
        testresultService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例获取CurTestRun", tags = {"测试结果" } ,notes = "根据需求测试用例获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例查询CurTestRun", tags = {"测试结果" } ,notes = "根据需求测试用例查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例获取DEFAULT", tags = {"测试结果" } ,notes = "根据需求测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
        List<TestResultDTO> list = testresultMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例查询DEFAULT", tags = {"测试结果" } ,notes = "根据需求测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求测试用例建立测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults")
    public ResponseEntity<TestResultDTO> createByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
		testresultService.create(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求测试用例批量建立测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例批量建立测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求测试用例更新测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> updateByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        domain.setId(testresult_id);
		testresultService.update(domain);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求测试用例批量更新测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例批量更新测试结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
            domain.setIbizcase(case_id);
        }
        testresultService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求测试用例删除测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<Boolean> removeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testresultService.remove(testresult_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求测试用例批量删除测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例批量删除测试结果")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryCase(@RequestBody List<Long> ids) {
        testresultService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求测试用例获取测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/{testresult_id}")
    public ResponseEntity<TestResultDTO> getByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id) {
        TestResult domain = testresultService.get(testresult_id);
        TestResultDTO dto = testresultMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求测试用例获取测试结果草稿", tags = {"测试结果" },  notes = "根据产品需求测试用例获取测试结果草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/getdraft")
    public ResponseEntity<TestResultDTO> getDraftByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, TestResultDTO dto) {
        TestResult domain = testresultMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(testresultService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求测试用例检查测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例检查测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testresultService.checkKey(testresultMapping.toDomain(testresultdto)));
    }

    @ApiOperation(value = "根据产品需求测试用例保存测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/save")
    public ResponseEntity<TestResultDTO> saveByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultDTO testresultdto) {
        TestResult domain = testresultMapping.toDomain(testresultdto);
        domain.setIbizcase(case_id);
        testresultService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testresultMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求测试用例批量保存测试结果", tags = {"测试结果" },  notes = "根据产品需求测试用例批量保存测试结果")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<TestResultDTO> testresultdtos) {
        List<TestResult> domainlist=testresultMapping.toDomain(testresultdtos);
        for(TestResult domain:domainlist){
             domain.setIbizcase(case_id);
        }
        testresultService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例获取CurTestRun", tags = {"测试结果" } ,notes = "根据产品需求测试用例获取CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/fetchcurtestrun")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultCurTestRunByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
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
	@ApiOperation(value = "根据产品需求测试用例查询CurTestRun", tags = {"测试结果" } ,notes = "根据产品需求测试用例查询CurTestRun")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/searchcurtestrun")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultCurTestRunByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchCurTestRun(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例获取DEFAULT", tags = {"测试结果" } ,notes = "根据产品需求测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/fetchdefault")
	public ResponseEntity<List<TestResultDTO>> fetchTestResultDefaultByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody TestResultSearchContext context) {
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
	@ApiOperation(value = "根据产品需求测试用例查询DEFAULT", tags = {"测试结果" } ,notes = "根据产品需求测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/testresults/searchdefault")
	public ResponseEntity<Page<TestResultDTO>> searchTestResultDefaultByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody TestResultSearchContext context) {
        context.setN_case_eq(case_id);
        Page<TestResult> domains = testresultService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testresultMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

