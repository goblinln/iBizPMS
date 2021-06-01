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


    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户建立测试用例", tags = {"测试用例" },  notes = "根据系统用户建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/testcases")
    public ResponseEntity<TestCaseDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
		caseService.create(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.quickTest('TESRUNCASE')")
    @ApiOperation(value = "根据系统用户testRunCases", tags = {"测试用例" },  notes = "根据系统用户testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/testcases/{testcase_id}/testruncases")
    public ResponseEntity<TestCaseDTO> testRunCasesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.testRunCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("@CaseRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户删除测试用例", tags = {"测试用例" },  notes = "根据系统用户删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id) {
		return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(testcase_id));
    }

    @PreAuthorize("@CaseRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户批量删除测试用例", tags = {"测试用例" },  notes = "根据系统用户批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/testcases/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        caseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户获取测试用例", tags = {"测试用例" },  notes = "根据系统用户获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id) {
        Case domain = caseService.get(testcase_id);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取测试用例草稿", tags = {"测试用例" },  notes = "根据系统用户获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/testcases/getdraft")
    public ResponseEntity<TestCaseDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, TestCaseDTO dto) {
        Case domain = testcaseMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testcaseMapping.toDto(caseService.getDraft(domain)));
    }

    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("@CaseRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户更新测试用例", tags = {"测试用例" },  notes = "根据系统用户更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
		caseService.update(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.quickTest('RUNCASE')")
    @ApiOperation(value = "根据系统用户runCases", tags = {"测试用例" },  notes = "根据系统用户runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/testcases/{testcase_id}/runcases")
    public ResponseEntity<TestCaseDTO> runCasesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.runCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取DEFAULT", tags = {"测试用例" } ,notes = "根据系统用户获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/testcases/fetchdefault")
	public ResponseEntity<List<TestCaseDTO>> fetchDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchDefault(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品建立测试用例", tags = {"测试用例" },  notes = "根据产品建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases")
    public ResponseEntity<TestCaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
		caseService.create(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品testRunCases", tags = {"测试用例" },  notes = "根据产品testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/testruncases")
    public ResponseEntity<TestCaseDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.testRunCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品删除测试用例", tags = {"测试用例" },  notes = "根据产品删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id) {
		return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(testcase_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品批量删除测试用例", tags = {"测试用例" },  notes = "根据产品批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        caseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试用例", tags = {"测试用例" },  notes = "根据产品获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id) {
        Case domain = caseService.get(testcase_id);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品获取测试用例草稿", tags = {"测试用例" },  notes = "根据产品获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/getdraft")
    public ResponseEntity<TestCaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestCaseDTO dto) {
        Case domain = testcaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testcaseMapping.toDto(caseService.getDraft(domain)));
    }

    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品更新测试用例", tags = {"测试用例" },  notes = "根据产品更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
		caseService.update(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CASEMANAGE')")
    @ApiOperation(value = "根据产品runCases", tags = {"测试用例" },  notes = "根据产品runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/runcases")
    public ResponseEntity<TestCaseDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.runCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
}

