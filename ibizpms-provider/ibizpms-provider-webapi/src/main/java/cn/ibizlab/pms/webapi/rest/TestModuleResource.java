package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.service.ITestModuleService;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.TestModuleRuntime;

@Slf4j
@Api(tags = {"测试模块"})
@RestController("WebApi-testmodule")
@RequestMapping("")
public class TestModuleResource {

    @Autowired
    public ITestModuleService testmoduleService;

    @Autowired
    public TestModuleRuntime testmoduleRuntime;

    @Autowired
    @Lazy
    public TestModuleMapping testmoduleMapping;

    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'CREATE')")
    @ApiOperation(value = "新建测试模块", tags = {"测试模块" },  notes = "新建测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testmodules")
    @Transactional
    public ResponseEntity<TestModuleDTO> create(@Validated @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
		testmoduleService.create(domain);
        if(!testmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', #testmodule_id, 'READ')")
    @ApiOperation(value = "获取测试模块", tags = {"测试模块" },  notes = "获取测试模块")
	@RequestMapping(method = RequestMethod.GET, value = "/testmodules/{testmodule_id}")
    public ResponseEntity<TestModuleDTO> get(@PathVariable("testmodule_id") Long testmodule_id) {
        TestModule domain = testmoduleService.get(testmodule_id);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(testmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', #testmodule_id, 'DELETE')")
    @ApiOperation(value = "删除测试模块", tags = {"测试模块" },  notes = "删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testmodules/{testmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testmodule_id") Long testmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testmoduleService.remove(testmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除测试模块", tags = {"测试模块" },  notes = "批量删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', #testmodule_id, 'UPDATE')")
    @ApiOperation(value = "更新测试模块", tags = {"测试模块" },  notes = "更新测试模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/testmodules/{testmodule_id}")
    @Transactional
    public ResponseEntity<TestModuleDTO> update(@PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
		TestModule domain  = testmoduleMapping.toDomain(testmoduledto);
        domain.setId(testmodule_id);
		testmoduleService.update(domain );
        if(!testmoduleRuntime.test(testmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(testmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'CREATE')")
    @ApiOperation(value = "检查测试模块", tags = {"测试模块" },  notes = "检查测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestModuleDTO testmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testmoduleService.checkKey(testmoduleMapping.toDomain(testmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"测试模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/testmodules/{testmodule_id}/fix")
    public ResponseEntity<TestModuleDTO> fix(@PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setId(testmodule_id);
        domain = testmoduleService.fix(domain);
        testmoduledto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(domain.getId());
        testmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'CREATE')")
    @ApiOperation(value = "获取测试模块草稿", tags = {"测试模块" },  notes = "获取测试模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testmodules/getdraft")
    public ResponseEntity<TestModuleDTO> getDraft(TestModuleDTO dto) {
        TestModule domain = testmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduleMapping.toDto(testmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_TESTMODULE', #testmodule_id, 'DELETE')")
    @ApiOperation(value = "删除模块", tags = {"测试模块" },  notes = "删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/testmodules/{testmodule_id}/removemodule")
    public ResponseEntity<TestModuleDTO> removeModule(@PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setId(testmodule_id);
        domain = testmoduleService.removeModule(domain);
        testmoduledto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(domain.getId());
        testmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduledto);
    }



    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'DENY')")
    @ApiOperation(value = "批量保存测试模块", tags = {"测试模块" },  notes = "批量保存测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<TestModuleDTO> testmoduledtos) {
        testmoduleService.saveBatch(testmoduleMapping.toDomain(testmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取BYPATH", tags = {"测试模块" } ,notes = "获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchbypath")
	public ResponseEntity<List<TestModuleDTO>> fetchbypath(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchByPath(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchdefault")
	public ResponseEntity<List<TestModuleDTO>> fetchdefault(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchDefault(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取父模块", tags = {"测试模块" } ,notes = "获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchparentmodule")
	public ResponseEntity<List<TestModuleDTO>> fetchparentmodule(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchParentModule(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取根模块", tags = {"测试模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchroot")
	public ResponseEntity<List<TestModuleDTO>> fetchroot(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchRoot(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取根模块_无分支", tags = {"测试模块" } ,notes = "获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchroot_nobranch")
	public ResponseEntity<List<TestModuleDTO>> fetchroot_nobranch(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchRoot_NoBranch(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'READ')")
	@ApiOperation(value = "获取TestModule", tags = {"测试模块" } ,notes = "获取TestModule")
    @RequestMapping(method= RequestMethod.POST , value="/testmodules/fetchtestmodule")
	public ResponseEntity<List<TestModuleDTO>> fetchtestmodule(@RequestBody TestModuleSearchContext context) {
        Page<TestModule> domains = testmoduleService.searchTestModule(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试模块报表", tags = {"测试模块"}, notes = "生成测试模块报表")
    @RequestMapping(method = RequestMethod.GET, value = "/testmodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TestModuleSearchContext context, HttpServletResponse response) {
        try {
            testmoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testmoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, testmoduleRuntime);
        }
    }

    @ApiOperation(value = "打印测试模块", tags = {"测试模块"}, notes = "打印测试模块")
    @RequestMapping(method = RequestMethod.GET, value = "/testmodules/{testmodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("testmodule_ids") Set<Long> testmodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = testmoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<TestModule> domains = new ArrayList<>();
            for (Long testmodule_id : testmodule_ids) {
                domains.add(testmoduleService.get( testmodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new TestModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testmoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", testmodule_ids, e.getMessage()), Errors.INTERNALERROR, testmoduleRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testmodules/{testmodule_id}/{action}")
    public ResponseEntity<TestModuleDTO> dynamicCall(@PathVariable("testmodule_id") Long testmodule_id , @PathVariable("action") String action , @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleService.dynamicCall(testmodule_id, action, testmoduleMapping.toDomain(testmoduledto));
        testmoduledto = testmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduledto);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立测试模块", tags = {"测试模块" },  notes = "根据产品建立测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testmodules")
    public ResponseEntity<TestModuleDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
		testmoduleService.create(domain);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', #testmodule_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试模块", tags = {"测试模块" },  notes = "根据产品获取测试模块")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<TestModuleDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id) {
        TestModule domain = testmoduleService.get(testmodule_id);
        if (domain == null || domain.getRoot() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', #testmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除测试模块", tags = {"测试模块" },  notes = "根据产品删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id) {
        TestModule testget = testmoduleService.get(testmodule_id);
        if (testget == null || testget.getRoot() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(testmoduleService.remove(testmodule_id));
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除测试模块", tags = {"测试模块" },  notes = "根据产品批量删除测试模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', #testmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新测试模块", tags = {"测试模块" },  notes = "根据产品更新测试模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testmodules/{testmodule_id}")
    public ResponseEntity<TestModuleDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule testget = testmoduleService.get(testmodule_id);
        if (testget == null || testget.getRoot() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
        domain.setId(testmodule_id);
		testmoduleService.update(domain);
        TestModuleDTO dto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品检查测试模块", tags = {"测试模块" },  notes = "根据产品检查测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestModuleDTO testmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testmoduleService.checkKey(testmoduleMapping.toDomain(testmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'DENY')")
    @ApiOperation(value = "根据产品重建模块路径", tags = {"测试模块" },  notes = "根据产品重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testmodules/{testmodule_id}/fix")
    public ResponseEntity<TestModuleDTO> fixByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
        domain.setId(testmodule_id);
        domain = testmoduleService.fix(domain) ;
        testmoduledto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs(domain.getId());    
        testmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduledto);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取测试模块草稿", tags = {"测试模块" },  notes = "根据产品获取测试模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testmodules/getdraft")
    public ResponseEntity<TestModuleDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestModuleDTO dto) {
        TestModule domain = testmoduleMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduleMapping.toDto(testmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'TESTMODULEMANAGE', #testmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除模块", tags = {"测试模块" },  notes = "根据产品删除模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testmodules/{testmodule_id}/removemodule")
    public ResponseEntity<TestModuleDTO> removeModuleByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testmodule_id") Long testmodule_id, @RequestBody TestModuleDTO testmoduledto) {
        TestModule domain = testmoduleMapping.toDomain(testmoduledto);
        domain.setRoot(product_id);
        domain.setId(testmodule_id);
        domain = testmoduleService.removeModule(domain) ;
        testmoduledto = testmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = testmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        testmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_TESTMODULE', 'DENY')")
    @ApiOperation(value = "根据产品批量保存测试模块", tags = {"测试模块" },  notes = "根据产品批量保存测试模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<TestModuleDTO> testmoduledtos) {
        List<TestModule> domainlist=testmoduleMapping.toDomain(testmoduledtos);
        for(TestModule domain:domainlist){
             domain.setRoot(product_id);
        }
        testmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取BYPATH", tags = {"测试模块" } ,notes = "根据产品获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchbypath")
	public ResponseEntity<List<TestModuleDTO>> fetchByPathByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchByPath(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试模块" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchdefault")
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
    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取父模块", tags = {"测试模块" } ,notes = "根据产品获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchparentmodule")
	public ResponseEntity<List<TestModuleDTO>> fetchParentModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchParentModule(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取根模块", tags = {"测试模块" } ,notes = "根据产品获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchroot")
	public ResponseEntity<List<TestModuleDTO>> fetchRootByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchRoot(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取根模块_无分支", tags = {"测试模块" } ,notes = "根据产品获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchroot_nobranch")
	public ResponseEntity<List<TestModuleDTO>> fetchRoot_NoBranchByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchRoot_NoBranch(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_TESTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取TestModule", tags = {"测试模块" } ,notes = "根据产品获取TestModule")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testmodules/fetchtestmodule")
	public ResponseEntity<List<TestModuleDTO>> fetchTestModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<TestModule> domains = testmoduleService.searchTestModule(context) ;
        List<TestModuleDTO> list = testmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

