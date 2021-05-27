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

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'CREATE')")
    @ApiOperation(value = "获取测试运行草稿", tags = {"测试运行" },  notes = "获取测试运行草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraft(TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

    @PreAuthorize("@TestRunRuntime.test(#testrun_id,'CREATE')")
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

    @ApiOperation(value = "根据测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

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

    @ApiOperation(value = "根据产品测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据产品测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

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

    @ApiOperation(value = "根据项目测试版本获取测试运行草稿", tags = {"测试运行" },  notes = "根据项目测试版本获取测试运行草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/testruns/getdraft")
    public ResponseEntity<TestRunDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, TestRunDTO dto) {
        TestRun domain = testrunMapping.toDomain(dto);
        domain.setTask(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(testrunMapping.toDto(testrunService.getDraft(domain)));
    }

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

