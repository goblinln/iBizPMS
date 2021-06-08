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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepsService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibCaseStepsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibCaseStepsRuntime;

@Slf4j
@Api(tags = {"用例库用例步骤" })
@RestController("WebApi-ibzlibcasesteps")
@RequestMapping("")
public class IbzLibCaseStepsResource {

    @Autowired
    public IIbzLibCaseStepsService ibzlibcasestepsService;

    @Autowired
    public IbzLibCaseStepsRuntime ibzlibcasestepsRuntime;

    @Autowired
    @Lazy
    public IbzLibCaseStepsMapping ibzlibcasestepsMapping;


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据测试用例建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps")
    public ResponseEntity<IbzLibCaseStepsDTO> createByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
		ibzlibcasestepsService.create(domain);
        if(!ibzlibcasestepsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'READ')")
    @ApiOperation(value = "根据测试用例获取用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例获取用例库用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> getByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
        IbzLibCaseSteps domain = ibzlibcasestepsService.get(ibzlibcasesteps_id);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'DELETE')")
    @ApiOperation(value = "根据测试用例删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<Boolean> removeByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.remove(ibzlibcasesteps_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','DELETE')")
    @ApiOperation(value = "根据测试用例批量删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例批量删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> removeBatchByIbzCase(@RequestBody List<Long> ids) {
        ibzlibcasestepsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'UPDATE')")
    @ApiOperation(value = "根据测试用例更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> updateByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        domain.setId(ibzlibcasesteps_id);
		ibzlibcasestepsService.update(domain);
        if(!ibzlibcasestepsRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据测试用例检查用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例检查用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.checkKey(ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto)));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据测试用例获取用例库用例步骤草稿", tags = {"用例库用例步骤" },  notes = "根据测试用例获取用例库用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/getdraft")
    public ResponseEntity<IbzLibCaseStepsDTO> getDraftByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, IbzLibCaseStepsDTO dto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(dto);
        domain.setIbizcase(ibzcase_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(ibzlibcasestepsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS', 'DENY')")
    @ApiOperation(value = "根据测试用例保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/save")
    public ResponseEntity<IbzLibCaseStepsDTO> saveByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        ibzlibcasestepsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault")
	public ResponseEntity<List<IbzLibCaseStepsDTO>> fetchDefaultByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id,@RequestBody IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        ibzlibcasestepsRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
        List<IbzLibCaseStepsDTO> list = ibzlibcasestepsMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据用例库测试用例建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps")
    public ResponseEntity<IbzLibCaseStepsDTO> createByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
		ibzlibcasestepsService.create(domain);
        if(!ibzlibcasestepsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'READ')")
    @ApiOperation(value = "根据用例库测试用例获取用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例获取用例库用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> getByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
        IbzLibCaseSteps domain = ibzlibcasestepsService.get(ibzlibcasesteps_id);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'DELETE')")
    @ApiOperation(value = "根据用例库测试用例删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<Boolean> removeByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.remove(ibzlibcasesteps_id));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','DELETE')")
    @ApiOperation(value = "根据用例库测试用例批量删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例批量删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLibIbzCase(@RequestBody List<Long> ids) {
        ibzlibcasestepsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_LIBCASESTEPS', #ibzlibcasesteps_id, 'UPDATE')")
    @ApiOperation(value = "根据用例库测试用例更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> updateByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        domain.setId(ibzlibcasesteps_id);
		ibzlibcasestepsService.update(domain);
        if(!ibzlibcasestepsRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibcasestepsRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据用例库测试用例检查用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例检查用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.checkKey(ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto)));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','CREATE')")
    @ApiOperation(value = "根据用例库测试用例获取用例库用例步骤草稿", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例获取用例库用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/getdraft")
    public ResponseEntity<IbzLibCaseStepsDTO> getDraftByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, IbzLibCaseStepsDTO dto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(dto);
        domain.setIbizcase(ibzcase_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(ibzlibcasestepsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS', 'DENY')")
    @ApiOperation(value = "根据用例库测试用例保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/save")
    public ResponseEntity<IbzLibCaseStepsDTO> saveByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        ibzlibcasestepsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('IBZ_LIBCASESTEPS','READ')")
	@ApiOperation(value = "根据用例库测试用例获取DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据用例库测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault")
	public ResponseEntity<List<IbzLibCaseStepsDTO>> fetchDefaultByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id,@RequestBody IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        ibzlibcasestepsRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
        List<IbzLibCaseStepsDTO> list = ibzlibcasestepsMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

