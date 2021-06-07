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


    @PreAuthorize("quickTest('IBZ_LIBMODULE','CREATE')")
    @ApiOperation(value = "根据用例库获取用例库模块草稿", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/getdraft")
    public ResponseEntity<TestCaseLibModuleDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, TestCaseLibModuleDTO dto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(dto);
        domain.setRoot(ibzlib_id);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibmoduleMapping.toDto(ibzlibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据用例库更新用例库模块", tags = {"用例库模块" },  notes = "根据用例库更新用例库模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/{testcaselibmodule_id}")
    public ResponseEntity<TestCaseLibModuleDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("testcaselibmodule_id") Long testcaselibmodule_id, @RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(testcaselibmoduledto);
        domain.setRoot(ibzlib_id);
        domain.setId(testcaselibmodule_id);
		ibzlibmoduleService.update(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'READ')")
    @ApiOperation(value = "根据用例库获取用例库模块", tags = {"用例库模块" },  notes = "根据用例库获取用例库模块")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/{testcaselibmodule_id}")
    public ResponseEntity<TestCaseLibModuleDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("testcaselibmodule_id") Long testcaselibmodule_id) {
        IbzLibModule domain = ibzlibmoduleService.get(testcaselibmodule_id);
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBMODULE', #testcaselibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据用例库删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/{testcaselibmodule_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("testcaselibmodule_id") Long testcaselibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibmoduleService.remove(testcaselibmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE','DELETE')")
    @ApiOperation(value = "根据用例库批量删除用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量删除用例库模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLib(@RequestBody List<Long> ids) {
        ibzlibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }


    @PreAuthorize("quickTest('IBZ_LIBMODULE', 'DENY')")
    @ApiOperation(value = "根据用例库批量保存用例库模块", tags = {"用例库模块" },  notes = "根据用例库批量保存用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody List<TestCaseLibModuleDTO> testcaselibmoduledtos) {
        List<IbzLibModule> domainlist=testcaselibmoduleMapping.toDomain(testcaselibmoduledtos);
        for(IbzLibModule domain:domainlist){
             domain.setRoot(ibzlib_id);
        }
        ibzlibmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_LIBMODULE','READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"用例库模块" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/{ibzlib_id}/testcaselibmodules/fetchdefault")
	public ResponseEntity<List<TestCaseLibModuleDTO>> fetchDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody IbzLibModuleSearchContext context) {
        context.setN_root_eq(ibzlib_id);
        ibzlibmoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibModule> domains = ibzlibmoduleService.searchDefault(context) ;
        List<TestCaseLibModuleDTO> list = testcaselibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_LIBMODULE','CREATE')")
    @ApiOperation(value = "根据用例库建立用例库模块", tags = {"用例库模块" },  notes = "根据用例库建立用例库模块")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibs/{ibzlib_id}/testcaselibmodules")
    public ResponseEntity<TestCaseLibModuleDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody TestCaseLibModuleDTO testcaselibmoduledto) {
        IbzLibModule domain = testcaselibmoduleMapping.toDomain(testcaselibmoduledto);
        domain.setRoot(ibzlib_id);
		ibzlibmoduleService.create(domain);
        if(!ibzlibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestCaseLibModuleDTO dto = testcaselibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibmoduleRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

