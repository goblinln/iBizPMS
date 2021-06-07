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
import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibRuntime;

@Slf4j
@Api(tags = {"用例库" })
@RestController("StandardAPI-testcaselib")
@RequestMapping("")
public class TestCaseLibResource {

    @Autowired
    public IIbzLibService ibzlibService;

    @Autowired
    public IbzLibRuntime ibzlibRuntime;

    @Autowired
    @Lazy
    public TestCaseLibMapping testcaselibMapping;

    @PreAuthorize("quickTest('IBZ_LIB', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/fetchdefault")
	public ResponseEntity<List<TestCaseLibDTO>> fetchdefault(@RequestBody IbzLibSearchContext context) {
        ibzlibRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLib> domains = ibzlibService.searchDefault(context) ;
        List<TestCaseLibDTO> list = testcaselibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "ibzlib" , versionfield = "lastediteddate")
    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'UPDATE')")
    @ApiOperation(value = "更新用例库", tags = {"用例库" },  notes = "更新用例库")
	@RequestMapping(method = RequestMethod.PUT, value = "/testcaselibs/{testcaselib_id}")
    @Transactional
    public ResponseEntity<TestCaseLibDTO> update(@PathVariable("testcaselib_id") Long testcaselib_id, @RequestBody TestCaseLibDTO testcaselibdto) {
		IbzLib domain  = testcaselibMapping.toDomain(testcaselibdto);
        domain.setId(testcaselib_id);
		ibzlibService.update(domain );
        if(!ibzlibRuntime.test(testcaselib_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(testcaselib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIB', 'CREATE')")
    @ApiOperation(value = "新建用例库", tags = {"用例库" },  notes = "新建用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibs")
    @Transactional
    public ResponseEntity<TestCaseLibDTO> create(@Validated @RequestBody TestCaseLibDTO testcaselibdto) {
        IbzLib domain = testcaselibMapping.toDomain(testcaselibdto);
		ibzlibService.create(domain);
        if(!ibzlibRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'DELETE')")
    @ApiOperation(value = "删除用例库", tags = {"用例库" },  notes = "删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{testcaselib_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testcaselib_id") Long testcaselib_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibService.remove(testcaselib_id));
    }

    @PreAuthorize("quickTest('IBZ_LIB', 'DELETE')")
    @ApiOperation(value = "批量删除用例库", tags = {"用例库" },  notes = "批量删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'READ')")
    @ApiOperation(value = "获取用例库", tags = {"用例库" },  notes = "获取用例库")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/{testcaselib_id}")
    public ResponseEntity<TestCaseLibDTO> get(@PathVariable("testcaselib_id") Long testcaselib_id) {
        IbzLib domain = ibzlibService.get(testcaselib_id);
        TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(testcaselib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_LIB', 'CREATE')")
    @ApiOperation(value = "获取用例库草稿", tags = {"用例库" },  notes = "获取用例库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/getdraft")
    public ResponseEntity<TestCaseLibDTO> getDraft(TestCaseLibDTO dto) {
        IbzLib domain = testcaselibMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibMapping.toDto(ibzlibService.getDraft(domain)));
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testcaselibs/{testcaselib_id}/{action}")
    public ResponseEntity<TestCaseLibDTO> dynamicCall(@PathVariable("testcaselib_id") Long testcaselib_id , @PathVariable("action") String action , @RequestBody TestCaseLibDTO testcaselibdto) {
        IbzLib domain = ibzlibService.dynamicCall(testcaselib_id, action, testcaselibMapping.toDomain(testcaselibdto));
        testcaselibdto = testcaselibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibdto);
    }
}

