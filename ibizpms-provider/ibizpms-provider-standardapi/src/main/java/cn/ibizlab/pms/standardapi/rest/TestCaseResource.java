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
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.CaseRuntime;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;

@Slf4j
@Api(tags = {"测试用例" })
@RestController("StandardAPI-testcase")
@RequestMapping("")
public class TestCaseResource {

    @Autowired
    public ICaseService caseService;

    @Autowired
    public CaseRuntime caseRuntime;

    @Autowired
    @Lazy
    public TestCaseMapping testcaseMapping;

    @Autowired
    private ICaseStepService casestepService;


    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'RUNCASE')")
    @ApiOperation(value = "根据产品runCases", tags = {"测试用例" },  notes = "根据产品runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/runcases")
    public ResponseEntity<TestCaseDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.runCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        testcasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立测试用例", tags = {"测试用例" },  notes = "根据产品建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases")
    public ResponseEntity<TestCaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
		caseService.create(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'TESRUNCASE')")
    @ApiOperation(value = "根据产品testRunCases", tags = {"测试用例" },  notes = "根据产品testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/testruncases")
    public ResponseEntity<TestCaseDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.testRunCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        testcasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'DENY')")
    @ApiOperation(value = "根据产品套件关联", tags = {"测试用例" },  notes = "根据产品套件关联")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/testsuitelinkcase")
    public ResponseEntity<TestCaseDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.testsuitelinkCase(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());    
        testcasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @ApiOperation(value = "批量处理[根据产品测试用例]", tags = {"测试用例" },  notes = "批量处理[根据产品测试用例]")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/testsuitelinkcasebatch")
    public ResponseEntity<Boolean> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<TestCaseDTO> testcasedtos) {
        List<Case> domains = testcaseMapping.toDomain(testcasedtos);
        boolean result = caseService.testsuitelinkCaseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试用例" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchnotcurtestsuite")
	public ResponseEntity<List<TestCaseDTO>> fetchNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestSuite(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite", tags = {"测试用例" },  notes = "根据产品CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/casenfavorite")
    public ResponseEntity<TestCaseDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.caseNFavorite(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        testcasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', #testcase_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试用例", tags = {"测试用例" },  notes = "根据产品获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id) {
        Case domain = caseService.get(testcase_id);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试用例" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchcursuite")
	public ResponseEntity<List<TestCaseDTO>> fetchCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurSuite(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除测试用例", tags = {"测试用例" },  notes = "根据产品删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id) {
		return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(testcase_id));
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除测试用例", tags = {"测试用例" },  notes = "根据产品批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        caseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchcurtesttask")
	public ResponseEntity<List<TestCaseDTO>> fetchCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurTestTask(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试用例" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchdefault")
	public ResponseEntity<List<TestCaseDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchDefault(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取测试用例草稿", tags = {"测试用例" },  notes = "根据产品获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/getdraft")
    public ResponseEntity<TestCaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestCaseDTO dto) {
        Case domain = testcaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testcaseMapping.toDto(caseService.getDraft(domain)));
    }

    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新测试用例", tags = {"测试用例" },  notes = "根据产品更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
		caseService.update(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'FAVORITE')")
    @ApiOperation(value = "根据产品行为", tags = {"测试用例" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/casefavorite")
    public ResponseEntity<TestCaseDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.caseFavorite(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        Map<String, Integer> opprivs = caseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        testcasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchnotcurtesttask")
	public ResponseEntity<List<TestCaseDTO>> fetchNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestTask(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

