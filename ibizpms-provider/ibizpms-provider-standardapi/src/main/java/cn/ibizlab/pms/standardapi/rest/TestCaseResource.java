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


    @PreAuthorize("quickTest('ZT_CASE', 'TESRUNCASE')")
    @ApiOperation(value = "根据系统用户testRunCases", tags = {"测试用例" },  notes = "根据系统用户testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}/testruncases")
    public ResponseEntity<TestCaseDTO> testRunCasesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.testRunCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"测试用例" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testcases/fetchmy")
	public ResponseEntity<List<TestCaseDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchMy(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'DELETE')")
    @ApiOperation(value = "根据系统用户删除测试用例", tags = {"测试用例" },  notes = "根据系统用户删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id) {
		return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(testcase_id));
    }

    @PreAuthorize("quickTest('ZT_CASE', 'DELETE')")
    @ApiOperation(value = "根据系统用户批量删除测试用例", tags = {"测试用例" },  notes = "根据系统用户批量删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/testcases/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        caseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'RUNCASE')")
    @ApiOperation(value = "根据系统用户runCases", tags = {"测试用例" },  notes = "根据系统用户runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}/runcases")
    public ResponseEntity<TestCaseDTO> runCasesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.runCases(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"测试用例" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testcases/fetchaccount")
	public ResponseEntity<List<TestCaseDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchAccount(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"测试用例" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testcases/fetchmyfavorites")
	public ResponseEntity<List<TestCaseDTO>> fetchMyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'CREATE')")
    @ApiOperation(value = "根据系统用户建立测试用例", tags = {"测试用例" },  notes = "根据系统用户建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testcases")
    public ResponseEntity<TestCaseDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
		caseService.create(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
    @ApiOperation(value = "根据系统用户获取测试用例", tags = {"测试用例" },  notes = "根据系统用户获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id) {
        Case domain = caseService.get(testcase_id);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("quickTest('ZT_CASE', 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新测试用例", tags = {"测试用例" },  notes = "根据系统用户更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
		caseService.update(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_CASE', 'FAVORITE')")
    @ApiOperation(value = "根据系统用户行为", tags = {"测试用例" },  notes = "根据系统用户行为")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}/casefavorite")
    public ResponseEntity<TestCaseDTO> caseFavoriteBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.caseFavorite(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("quickTest('ZT_CASE', 'READ')")
	@ApiOperation(value = "根据系统用户获取DEFAULT", tags = {"测试用例" } ,notes = "根据系统用户获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testcases/fetchdefault")
	public ResponseEntity<List<TestCaseDTO>> fetchDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody CaseSearchContext context) {
        
        Page<Case> domains = caseService.searchDefault(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_CASE', 'CREATE')")
    @ApiOperation(value = "根据系统用户获取测试用例草稿", tags = {"测试用例" },  notes = "根据系统用户获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/testcases/getdraft")
    public ResponseEntity<TestCaseDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, TestCaseDTO dto) {
        Case domain = testcaseMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testcaseMapping.toDto(caseService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_CASE', 'NFAVORITE')")
    @ApiOperation(value = "根据系统用户CaseNFavorite", tags = {"测试用例" },  notes = "根据系统用户CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testcases/{testcase_id}/casenfavorite")
    public ResponseEntity<TestCaseDTO> caseNFavoriteBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        
        domain.setId(testcase_id);
        domain = caseService.caseNFavorite(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
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
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的数据", tags = {"测试用例" } ,notes = "根据产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchmy")
	public ResponseEntity<List<TestCaseDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchMy(context) ;
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

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'RUNCASE')")
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

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指定用户数据", tags = {"测试用例" } ,notes = "根据产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchaccount")
	public ResponseEntity<List<TestCaseDTO>> fetchAccountByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchAccount(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"测试用例" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/fetchmyfavorites")
	public ResponseEntity<List<TestCaseDTO>> fetchMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<TestCaseDTO> list = testcaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立测试用例", tags = {"测试用例" },  notes = "根据产品建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases")
    public ResponseEntity<TestCaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
		caseService.create(domain);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'READ', #testcase_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试用例", tags = {"测试用例" },  notes = "根据产品获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/{testcase_id}")
    public ResponseEntity<TestCaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id) {
        Case domain = caseService.get(testcase_id);
        TestCaseDTO dto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
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

    @PreAuthorize("test('ZT_CASE', 'ZT_PRODUCT', #product_id, 'CASEMANAGE', #testcase_id, 'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite", tags = {"测试用例" },  notes = "根据产品CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{testcase_id}/casenfavorite")
    public ResponseEntity<TestCaseDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testcase_id") Long testcase_id, @RequestBody TestCaseDTO testcasedto) {
        Case domain = testcaseMapping.toDomain(testcasedto);
        domain.setProduct(product_id);
        domain.setId(testcase_id);
        domain = caseService.caseNFavorite(domain) ;
        testcasedto = testcaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcasedto);
    }

}

