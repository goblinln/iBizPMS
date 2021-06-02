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
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.service.ITestModuleService;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TestModuleRuntime;

@Slf4j
@Api(tags = {"测试模块" })
@RestController("StandardAPI-testmodule")
@RequestMapping("")
public class TestModuleResource {

    @Autowired
    public ITestModuleService testmoduleService;

    @Autowired
    public TestModuleRuntime testmoduleRuntime;

    @Autowired
    @Lazy
    public TestModuleMapping testmoduleMapping;


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试模块" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testmodules/fetchdefault")
	public ResponseEntity<List<TestModuleDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchDefault(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTMODULEMANAGE')")
    @ApiOperation(value = "根据产品删除测试模块", tags = {"测试模块" },  notes = "根据产品删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testmoduleService.remove(testmodule_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTMODULEMANAGE')")
    @ApiOperation(value = "根据产品批量删除测试模块", tags = {"测试模块" },  notes = "根据产品批量删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }


    @PreAuthorize("@TestModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品批量保存测试模块", tags = {"测试模块" },  notes = "根据产品批量保存测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<TestModuleDTO> testmoduledtos) {
        List<TestModule> domainlist=testmoduleMapping.toDomain(testmoduledtos);
        for(TestModule domain:domainlist){
             domain.setRoot(product_id);
        }
        testmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTMODULEMANAGE')")
    @ApiOperation(value = "根据产品获取测试模块草稿", tags = {"测试模块" },  notes = "根据产品获取测试模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testmodules/getdraft")
    public ResponseEntity<TestModuleDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestModuleDTO dto) {
        TestModule domain = testmoduleMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduleMapping.toDto(testmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTMODULEMANAGE')")
    @ApiOperation(value = "根据产品更新测试模块", tags = {"测试模块" },  notes = "根据产品更新测试模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<TestModuleDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
        domain.setId(testmodule_id);
		testmoduleService.update(domain);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTMODULEMANAGE')")
    @ApiOperation(value = "根据产品建立测试模块", tags = {"测试模块" },  notes = "根据产品建立测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testmodules")
    public ResponseEntity<TestModuleDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
		testmoduleService.create(domain);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试模块", tags = {"测试模块" },  notes = "根据产品获取测试模块")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<TestModuleDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id) {
        TestModule domain = testmoduleService.get(testmodule_id);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

