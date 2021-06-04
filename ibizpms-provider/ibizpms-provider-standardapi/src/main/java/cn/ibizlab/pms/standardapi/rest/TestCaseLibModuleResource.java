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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibModuleRuntime;

@Slf4j
@Api(tags = {"用例库模块" })
@RestController("StandardAPI-testcaselibmodule")
@RequestMapping("")
public class TestCaseLibModuleResource {

    @Autowired
    public IIbzLibModuleService ibzlibmoduleService;

    @Autowired
    public IbzLibModuleRuntime ibzlibmoduleRuntime;

    @Autowired
    @Lazy
    public TestCaseLibModuleMapping testcaselibmoduleMapping;

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "新建用例库模块", tags = {"用例库模块" },  notes = "新建用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibmodules")
    @Transactional
    public ResponseEntity<TestCaseLibModuleDTO> create(@Validated @RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(testcaselibmoduledto);
		ibzlibmoduleService.create(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'UPDATE')")
    @ApiOperation(value = "更新用例库模块", tags = {"用例库模块" },  notes = "更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/testcaselibmodules/{testcaselibmodule_id}")
    @Transactional
    public ResponseEntity<TestCaseLibModuleDTO> update(@PathVariable("testcaselibmodule_id") Long testcaselibmodule_id, @RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
		IbzLibModule domain  = testcaselibmoduleMapping.toDomain(testcaselibmoduledto);
        domain.setId(testcaselibmodule_id);
		ibzlibmoduleService.update(domain );
        if(!ibzlibmoduleRuntime.test(testcaselibmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(testcaselibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'DELETE')")
    @ApiOperation(value = "删除用例库模块", tags = {"用例库模块" },  notes = "删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibmodules/{testcaselibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testcaselibmodule_id") Long testcaselibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(testcaselibmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除用例库模块", tags = {"用例库模块" },  notes = "批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'READ')")
    @ApiOperation(value = "获取用例库模块", tags = {"用例库模块" },  notes = "获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibmodules/{testcaselibmodule_id}")
    public ResponseEntity<TestCaseLibModuleDTO> get(@PathVariable("testcaselibmodule_id") Long testcaselibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(testcaselibmodule_id);
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(testcaselibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "获取用例库模块草稿", tags = {"用例库模块" },  notes = "获取用例库模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibmodules/getdraft")
    public ResponseEntity<TestCaseLibModuleDTO> getDraft(TestCaseLibModuleDTO dto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'CREATE')")
    @ApiOperation(value = "检查用例库模块", tags = {"用例库模块" },  notes = "检查用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.checkKey(testcaselibmoduleMapping.toDomain(testcaselibmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DENY')")
    @ApiOperation(value = "保存用例库模块", tags = {"用例库模块" },  notes = "保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibmodules/save")
    public ResponseEntity<TestCaseLibModuleDTO> save(@RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(testcaselibmoduledto);
        ibzlibmoduleService.save(domain);
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibmodules/fetchdefault")
	public ResponseEntity<List<TestCaseLibModuleDTO>> fetchdefault(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
        List<TestCaseLibModuleDTO> list = testcaselibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'READ')")
	@ApiOperation(value = "获取无枝叶", tags = {"用例库模块" } ,notes = "获取无枝叶")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibmodules/fetchroot_nobranch")
	public ResponseEntity<List<TestCaseLibModuleDTO>> fetchroot_nobranch(@RequestBody IbzLibModuleSearchContext context) {
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchRoot_NoBranch(context) ;
        List<TestCaseLibModuleDTO> list = testcaselibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testcaselibmodules/{testcaselibmodule_id}/{action}")
    public ResponseEntity<TestCaseLibModuleDTO> dynamicCall(@PathVariable("testcaselibmodule_id") Long testcaselibmodule_id , @PathVariable("action") String action , @RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        IbzLibModule domain = ibzlibmoduleService.dynamicCall(testcaselibmodule_id, action, testcaselibmoduleMapping.toDomain(testcaselibmoduledto));
        testcaselibmoduledto = testcaselibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibmoduledto);
    }
}

