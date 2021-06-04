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
import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.core.zentao.service.ITestSuiteService;
import cn.ibizlab.pms.core.zentao.filter.TestSuiteSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestSuiteRuntime;

@Slf4j
@Api(tags = {"测试套件" })
@RestController("WebApi-testsuite")
@RequestMapping("")
public class TestSuiteResource {

    @Autowired
    public ITestSuiteService testsuiteService;

    @Autowired
    public TestSuiteRuntime testsuiteRuntime;

    @Autowired
    @Lazy
    public TestSuiteMapping testsuiteMapping;

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'CREATE')")
    @ApiOperation(value = "新建测试套件", tags = {"测试套件" },  notes = "新建测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites")
    @Transactional
    public ResponseEntity<TestSuiteDTO> create(@Validated @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
		testsuiteService.create(domain);
        if(!testsuiteRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "testsuite" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_TESTSUITE', #testsuite_id, 'UPDATE')")
    @ApiOperation(value = "更新测试套件", tags = {"测试套件" },  notes = "更新测试套件")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}")
    @Transactional
    public ResponseEntity<TestSuiteDTO> update(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
		TestSuite domain  = testsuiteMapping.toDomain(testsuitedto);
        domain.setId(testsuite_id);
		testsuiteService.update(domain );
        if(!testsuiteRuntime.test(testsuite_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(testsuite_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTSUITE', #testsuite_id, 'DELETE')")
    @ApiOperation(value = "删除测试套件", tags = {"测试套件" },  notes = "删除测试套件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testsuite_id") Long testsuite_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testsuiteService.remove(testsuite_id));
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DELETE')")
    @ApiOperation(value = "批量删除测试套件", tags = {"测试套件" },  notes = "批量删除测试套件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testsuiteService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTSUITE', #testsuite_id, 'READ')")
    @ApiOperation(value = "获取测试套件", tags = {"测试套件" },  notes = "获取测试套件")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}")
    public ResponseEntity<TestSuiteDTO> get(@PathVariable("testsuite_id") Long testsuite_id) {
        TestSuite domain = testsuiteService.get(testsuite_id);
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(testsuite_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'CREATE')")
    @ApiOperation(value = "获取测试套件草稿", tags = {"测试套件" },  notes = "获取测试套件草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/getdraft")
    public ResponseEntity<TestSuiteDTO> getDraft(TestSuiteDTO dto) {
        TestSuite domain = testsuiteMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testsuiteMapping.toDto(testsuiteService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'CREATE')")
    @ApiOperation(value = "检查测试套件", tags = {"测试套件" },  notes = "检查测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestSuiteDTO testsuitedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testsuiteService.checkKey(testsuiteMapping.toDomain(testsuitedto)));
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "关联测试", tags = {"测试套件" },  notes = "关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/linkcase")
    public ResponseEntity<TestSuiteDTO> linkCase(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setId(testsuite_id);
        domain = testsuiteService.linkCase(domain);
        testsuitedto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(domain.getId());
        testsuitedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }


    @PreAuthorize("test('ZT_TESTSUITE', #testsuite_id, 'NONE')")
    @ApiOperation(value = "移动端测试套件计数器", tags = {"测试套件" },  notes = "移动端测试套件计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/mobtestsuitecount")
    public ResponseEntity<TestSuiteDTO> mobTestSuiteCount(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setId(testsuite_id);
        domain = testsuiteService.mobTestSuiteCount(domain);
        testsuitedto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(domain.getId());
        testsuitedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }


    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "保存测试套件", tags = {"测试套件" },  notes = "保存测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/save")
    public ResponseEntity<TestSuiteDTO> save(@RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        testsuiteService.save(domain);
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "未关联测试", tags = {"测试套件" },  notes = "未关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/unlinkcase")
    public ResponseEntity<TestSuiteDTO> unlinkCase(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setId(testsuite_id);
        domain = testsuiteService.unlinkCase(domain);
        testsuitedto = testsuiteMapping.toDto(domain);
        Map<String,Integer> opprivs = testsuiteRuntime.getOPPrivs(domain.getId());
        testsuitedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }


    @PreAuthorize("quickTest('ZT_TESTSUITE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试套件" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/fetchdefault")
	public ResponseEntity<List<TestSuiteDTO>> fetchdefault(@RequestBody TestSuiteSearchContext context) {
        Page<TestSuite> domains = testsuiteService.searchDefault(context) ;
        List<TestSuiteDTO> list = testsuiteMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTSUITE', 'READ')")
	@ApiOperation(value = "获取公开套件", tags = {"测试套件" } ,notes = "获取公开套件")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/fetchpublictestsuite")
	public ResponseEntity<List<TestSuiteDTO>> fetchpublictestsuite(@RequestBody TestSuiteSearchContext context) {
        Page<TestSuite> domains = testsuiteService.searchPublicTestSuite(context) ;
        List<TestSuiteDTO> list = testsuiteMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/{action}")
    public ResponseEntity<TestSuiteDTO> dynamicCall(@PathVariable("testsuite_id") Long testsuite_id , @PathVariable("action") String action , @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteService.dynamicCall(testsuite_id, action, testsuiteMapping.toDomain(testsuitedto));
        testsuitedto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立测试套件", tags = {"测试套件" },  notes = "根据产品建立测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites")
    public ResponseEntity<TestSuiteDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
		testsuiteService.create(domain);
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "testsuite" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'UPDATE', #testsuite_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新测试套件", tags = {"测试套件" },  notes = "根据产品更新测试套件")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}")
    public ResponseEntity<TestSuiteDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
        domain.setId(testsuite_id);
		testsuiteService.update(domain);
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'DELETE', #testsuite_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除测试套件", tags = {"测试套件" },  notes = "根据产品删除测试套件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testsuiteService.remove(testsuite_id));
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除测试套件", tags = {"测试套件" },  notes = "根据产品批量删除测试套件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testsuiteService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'READ', #testsuite_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试套件", tags = {"测试套件" },  notes = "根据产品获取测试套件")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}")
    public ResponseEntity<TestSuiteDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id) {
        TestSuite domain = testsuiteService.get(testsuite_id);
        TestSuiteDTO dto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取测试套件草稿", tags = {"测试套件" },  notes = "根据产品获取测试套件草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/getdraft")
    public ResponseEntity<TestSuiteDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestSuiteDTO dto) {
        TestSuite domain = testsuiteMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testsuiteMapping.toDto(testsuiteService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查测试套件", tags = {"测试套件" },  notes = "根据产品检查测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestSuiteDTO testsuitedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testsuiteService.checkKey(testsuiteMapping.toDomain(testsuitedto)));
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "根据产品关联测试", tags = {"测试套件" },  notes = "根据产品关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/linkcase")
    public ResponseEntity<TestSuiteDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
        domain.setId(testsuite_id);
        domain = testsuiteService.linkCase(domain) ;
        testsuitedto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }

    @PreAuthorize("test('ZT_TESTSUITE', #testsuite_id, 'NONE')")
    @ApiOperation(value = "根据产品移动端测试套件计数器", tags = {"测试套件" },  notes = "根据产品移动端测试套件计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/mobtestsuitecount")
    public ResponseEntity<TestSuiteDTO> mobTestSuiteCountByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
        domain.setId(testsuite_id);
        domain = testsuiteService.mobTestSuiteCount(domain) ;
        testsuitedto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }

    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "根据产品保存测试套件", tags = {"测试套件" },  notes = "根据产品保存测试套件")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/save")
    public ResponseEntity<TestSuiteDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
        testsuiteService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testsuiteMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_TESTSUITE', 'DENY')")
    @ApiOperation(value = "根据产品未关联测试", tags = {"测试套件" },  notes = "根据产品未关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/unlinkcase")
    public ResponseEntity<TestSuiteDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody TestSuiteDTO testsuitedto) {
        TestSuite domain = testsuiteMapping.toDomain(testsuitedto);
        domain.setProduct(product_id);
        domain.setId(testsuite_id);
        domain = testsuiteService.unlinkCase(domain) ;
        testsuitedto = testsuiteMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testsuitedto);
    }

    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试套件" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/fetchdefault")
	public ResponseEntity<List<TestSuiteDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestSuiteSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestSuite> domains = testsuiteService.searchDefault(context) ;
        List<TestSuiteDTO> list = testsuiteMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTSUITE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取公开套件", tags = {"测试套件" } ,notes = "根据产品获取公开套件")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/fetchpublictestsuite")
	public ResponseEntity<List<TestSuiteDTO>> fetchPublicTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestSuiteSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestSuite> domains = testsuiteService.searchPublicTestSuite(context) ;
        List<TestSuiteDTO> list = testsuiteMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

