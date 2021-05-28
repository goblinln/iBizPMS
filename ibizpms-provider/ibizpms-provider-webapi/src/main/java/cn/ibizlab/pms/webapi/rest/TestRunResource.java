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
import cn.ibizlab.pms.core.zentao.domain.TestRun;
import cn.ibizlab.pms.core.zentao.service.ITestRunService;
import cn.ibizlab.pms.core.zentao.filter.TestRunSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestRunRuntime;

@Slf4j
@Api(tags = {"测试运行" })
@RestController("WebApi-testrun")
@RequestMapping("")
public class TestRunResource {

    @Autowired
    public ITestRunService testrunService;

    @Autowired
    public TestRunRuntime testrunRuntime;

    @Autowired
    @Lazy
    public TestRunMapping testrunMapping;

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试运行", tags = {"测试运行" },  notes = "新建测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns")
    @Transactional
    public ResponseEntity<TestRunDTO> create(@Validated @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        Map<String,Integer> opprivs = testrunRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "更新测试运行", tags = {"测试运行" },  notes = "更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/testruns/{testrun_id}")
    @Transactional
    public ResponseEntity<TestRunDTO> update(@PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
		TestRun domain  = testrunMapping.toDomain(testrundto);
        domain.setId(testrun_id);
		testrunService.update(domain );
        if(!testrunRuntime.test(testrun_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestRunDTO dto = testrunMapping.toDto(domain);
        Map<String,Integer> opprivs = testrunRuntime.getOPPrivs(testrun_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "删除测试运行", tags = {"测试运行" },  notes = "删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testruns/{testrun_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testrun_id") Long testrun_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "获取测试运行", tags = {"测试运行" },  notes = "获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> get(@PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        Map<String,Integer> opprivs = testrunRuntime.getOPPrivs(testrun_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取测试运行草稿", tags = {"测试运行" },  notes = "获取测试运行草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraft(TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查测试运行", tags = {"测试运行" },  notes = "检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "保存测试运行", tags = {"测试运行" },  notes = "保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testruns/save")
    public ResponseEntity<TestRunDTO> save(@RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        testrunService.save(domain);
        TestRunDTO dto = testrunMapping.toDto(domain);
        Map<String,Integer> opprivs = testrunRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试运行" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchdefault(@RequestBody TestRunSearchContext context) {
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"测试运行" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchDefault(@RequestBody TestRunSearchContext context) {
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testruns/{testrun_id}/{action}")
    public ResponseEntity<TestRunDTO> dynamicCall(@PathVariable("testrun_id") Long testrun_id , @PathVariable("action") String action , @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunService.dynamicCall(testrun_id, action, testrunMapping.toDomain(testrundto));
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据测试版本建立测试运行", tags = {"测试运行" },  notes = "根据测试版本建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns")
    public ResponseEntity<TestRunDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新测试运行", tags = {"测试运行" },  notes = "根据测试版本更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        domain.setId(testrun_id);
		testrunService.update(domain);
        if(!testrunRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据测试版本删除测试运行", tags = {"测试运行" },  notes = "根据测试版本删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<Boolean> removeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据测试版本获取测试运行", tags = {"测试运行" },  notes = "根据测试版本获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据测试版本检查测试运行", tags = {"测试运行" },  notes = "根据测试版本检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "根据测试版本保存测试运行", tags = {"测试运行" },  notes = "根据测试版本保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/testruns/save")
    public ResponseEntity<TestRunDTO> saveByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        testrunService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(domain));
    }


    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据测试版本获取DEFAULT", tags = {"测试运行" } ,notes = "根据测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据测试版本查询DEFAULT", tags = {"测试运行" } ,notes = "根据测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品建立测试运行", tags = {"测试运行" },  notes = "根据产品建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns")
    public ResponseEntity<TestRunDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新测试运行", tags = {"测试运行" },  notes = "根据产品更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
		testrunService.update(domain);
        if(!testrunRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据产品删除测试运行", tags = {"测试运行" },  notes = "根据产品删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testruns/{testrun_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据产品获取测试运行", tags = {"测试运行" },  notes = "根据产品获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品获取测试运行草稿", tags = {"测试运行" },  notes = "根据产品获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品激活测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/activate")
    public ResponseEntity<TestRunDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.activate(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据产品阻塞测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/block")
    public ResponseEntity<TestRunDTO> blockByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.block(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品检查测试运行", tags = {"测试运行" },  notes = "根据产品检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "根据产品关闭测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/close")
    public ResponseEntity<TestRunDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.close(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据产品关联测试用例测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/linkcase")
    public ResponseEntity<TestRunDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.linkCase(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据产品移动端测试版本计数器测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/mobtesttaskcounter")
    public ResponseEntity<TestRunDTO> mobTestTaskCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.mobTestTaskCounter(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据产品保存测试运行", tags = {"测试运行" },  notes = "根据产品保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/save")
    public ResponseEntity<TestRunDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        testrunService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品开始测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/start")
    public ResponseEntity<TestRunDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.start(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据产品关联测试用例测试运行", tags = {"测试运行" },  notes = "根据产品测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testruns/{testrun_id}/unlinkcase")
    public ResponseEntity<TestRunDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.unlinkCase(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试运行" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"测试运行" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品获取我的测试单", tags = {"测试运行" } ,notes = "根据产品获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testruns/fetchmytesttaskpc")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchMyTestTaskPc(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品查询我的测试单", tags = {"测试运行" } ,notes = "根据产品查询我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testruns/searchmytesttaskpc")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchMyTestTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品测试版本建立测试运行", tags = {"测试运行" },  notes = "根据产品测试版本建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns")
    public ResponseEntity<TestRunDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新测试运行", tags = {"测试运行" },  notes = "根据产品测试版本更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        domain.setId(testrun_id);
		testrunService.update(domain);
        if(!testrunRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除测试运行", tags = {"测试运行" },  notes = "根据产品测试版本删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取测试运行", tags = {"测试运行" },  notes = "根据产品测试版本获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据产品测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品测试版本检查测试运行", tags = {"测试运行" },  notes = "根据产品测试版本检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "根据产品测试版本保存测试运行", tags = {"测试运行" },  notes = "根据产品测试版本保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/save")
    public ResponseEntity<TestRunDTO> saveByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        testrunService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(domain));
    }


    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品测试版本获取DEFAULT", tags = {"测试运行" } ,notes = "根据产品测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品测试版本查询DEFAULT", tags = {"测试运行" } ,notes = "根据产品测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目建立测试运行", tags = {"测试运行" },  notes = "根据项目建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns")
    public ResponseEntity<TestRunDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新测试运行", tags = {"测试运行" },  notes = "根据项目更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
		testrunService.update(domain);
        if(!testrunRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据项目删除测试运行", tags = {"测试运行" },  notes = "根据项目删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testruns/{testrun_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据项目获取测试运行", tags = {"测试运行" },  notes = "根据项目获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目获取测试运行草稿", tags = {"测试运行" },  notes = "根据项目获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目激活测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/activate")
    public ResponseEntity<TestRunDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.activate(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据项目阻塞测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/block")
    public ResponseEntity<TestRunDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.block(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目检查测试运行", tags = {"测试运行" },  notes = "根据项目检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "根据项目关闭测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/close")
    public ResponseEntity<TestRunDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.close(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据项目关联测试用例测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/linkcase")
    public ResponseEntity<TestRunDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.linkCase(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据项目移动端测试版本计数器测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/mobtesttaskcounter")
    public ResponseEntity<TestRunDTO> mobTestTaskCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.mobTestTaskCounter(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据项目保存测试运行", tags = {"测试运行" },  notes = "根据项目保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/save")
    public ResponseEntity<TestRunDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        testrunService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目开始测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/start")
    public ResponseEntity<TestRunDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.start(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @ApiOperation(value = "根据项目关联测试用例测试运行", tags = {"测试运行" },  notes = "根据项目测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testruns/{testrun_id}/unlinkcase")
    public ResponseEntity<TestRunDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        
        domain.setId(testrun_id);
        domain = testrunService.unlinkCase(domain) ;
        testrundto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrundto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试运行" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"测试运行" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目获取我的测试单", tags = {"测试运行" } ,notes = "根据项目获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testruns/fetchmytesttaskpc")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunMyTestTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchMyTestTaskPc(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目查询我的测试单", tags = {"测试运行" } ,notes = "根据项目查询我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testruns/searchmytesttaskpc")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunMyTestTaskPcByProject(@PathVariable("project_id") Long project_id, @RequestBody TestRunSearchContext context) {
        
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchMyTestTaskPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目测试版本建立测试运行", tags = {"测试运行" },  notes = "根据项目测试版本建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns")
    public ResponseEntity<TestRunDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新测试运行", tags = {"测试运行" },  notes = "根据项目测试版本更新测试运行")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        domain.setId(testrun_id);
		testrunService.update(domain);
        if(!testrunRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除测试运行", tags = {"测试运行" },  notes = "根据项目测试版本删除测试运行")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testrunService.remove(testrun_id));
    }


    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取测试运行", tags = {"测试运行" },  notes = "根据项目测试版本获取测试运行")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/{testrun_id}")
    public ResponseEntity<TestRunDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("testrun_id") Long testrun_id) {
        TestRun domain = testrunService.get(testrun_id);
        TestRunDTO dto = testrunMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据项目测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目测试版本检查测试运行", tags = {"测试运行" },  notes = "根据项目测试版本检查测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testrunService.checkKey(testrunMapping.toDomain(testrundto)));
    }

    @ApiOperation(value = "根据项目测试版本保存测试运行", tags = {"测试运行" },  notes = "根据项目测试版本保存测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/save")
    public ResponseEntity<TestRunDTO> saveByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setTask(testtask_id);
        testrunService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(domain));
    }


    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目测试版本获取DEFAULT", tags = {"测试运行" } ,notes = "根据项目测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/fetchdefault")
	public ResponseEntity<List<TestRunDTO>> fetchTestRunDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
        List<TestRunDTO> list = testrunMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestRunRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目测试版本查询DEFAULT", tags = {"测试运行" } ,notes = "根据项目测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/testruns/searchdefault")
	public ResponseEntity<Page<TestRunDTO>> searchTestRunDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestRunSearchContext context) {
        context.setN_task_eq(testtask_id);
        testrunRuntime.addAuthorityConditions(context,"READ");
        Page<TestRun> domains = testrunService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(testrunMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

