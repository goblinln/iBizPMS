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

@Slf4j
@Api(tags = {"用例库用例步骤" })
@RestController("WebApi-ibzlibcasesteps")
@RequestMapping("")
public class IbzLibCaseStepsResource {

    @Autowired
    public IIbzLibCaseStepsService ibzlibcasestepsService;

    @Autowired
    @Lazy
    public IbzLibCaseStepsMapping ibzlibcasestepsMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Create-all')")
    @ApiOperation(value = "根据测试用例建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps")
    public ResponseEntity<IbzLibCaseStepsDTO> createByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
		ibzlibcasestepsService.create(domain);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Create-all')")
    @ApiOperation(value = "根据测试用例批量建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例批量建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> createBatchByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
            domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Update-all')")
    @ApiOperation(value = "根据测试用例更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> updateByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        domain.setId(ibzlibcasesteps_id);
		ibzlibcasestepsService.update(domain);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Update-all')")
    @ApiOperation(value = "根据测试用例批量更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例批量更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> updateBatchByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
            domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Remove-all')")
    @ApiOperation(value = "根据测试用例删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<Boolean> removeByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.remove(ibzlibcasesteps_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Remove-all')")
    @ApiOperation(value = "根据测试用例批量删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例批量删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> removeBatchByIbzCase(@RequestBody List<Long> ids) {
        ibzlibcasestepsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Get-all')")
    @ApiOperation(value = "根据测试用例获取用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例获取用例库用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> getByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
        IbzLibCaseSteps domain = ibzlibcasestepsService.get(ibzlibcasesteps_id);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试用例获取用例库用例步骤草稿", tags = {"用例库用例步骤" },  notes = "根据测试用例获取用例库用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/getdraft")
    public ResponseEntity<IbzLibCaseStepsDTO> getDraftByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, IbzLibCaseStepsDTO dto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(dto);
        domain.setIbizcase(ibzcase_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(ibzlibcasestepsService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试用例检查用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例检查用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.checkKey(ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Save-all')")
    @ApiOperation(value = "根据测试用例保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/save")
    public ResponseEntity<IbzLibCaseStepsDTO> saveByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        ibzlibcasestepsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Save-all')")
    @ApiOperation(value = "根据测试用例批量保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据测试用例批量保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcases/{ibzcase_id}/ibzlibcasesteps/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
             domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-searchDefault-all')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault")
	public ResponseEntity<List<IbzLibCaseStepsDTO>> fetchIbzLibCaseStepsDefaultByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id,IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
        List<IbzLibCaseStepsDTO> list = ibzlibcasestepsMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-searchDefault-all')")
	@ApiOperation(value = "根据测试用例查询DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcases/{ibzcase_id}/ibzlibcasesteps/searchdefault")
	public ResponseEntity<Page<IbzLibCaseStepsDTO>> searchIbzLibCaseStepsDefaultByIbzCase(@PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibcasestepsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Create-all')")
    @ApiOperation(value = "根据用例库测试用例建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps")
    public ResponseEntity<IbzLibCaseStepsDTO> createByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
		ibzlibcasestepsService.create(domain);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Create-all')")
    @ApiOperation(value = "根据用例库测试用例批量建立用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例批量建立用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> createBatchByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
            domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Update-all')")
    @ApiOperation(value = "根据用例库测试用例更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> updateByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        domain.setId(ibzlibcasesteps_id);
		ibzlibcasestepsService.update(domain);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Update-all')")
    @ApiOperation(value = "根据用例库测试用例批量更新用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例批量更新用例库用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> updateBatchByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
            domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Remove-all')")
    @ApiOperation(value = "根据用例库测试用例删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<Boolean> removeByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.remove(ibzlibcasesteps_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Remove-all')")
    @ApiOperation(value = "根据用例库测试用例批量删除用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例批量删除用例库用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLibIbzCase(@RequestBody List<Long> ids) {
        ibzlibcasestepsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Get-all')")
    @ApiOperation(value = "根据用例库测试用例获取用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例获取用例库用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/{ibzlibcasesteps_id}")
    public ResponseEntity<IbzLibCaseStepsDTO> getByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @PathVariable("ibzlibcasesteps_id") Long ibzlibcasesteps_id) {
        IbzLibCaseSteps domain = ibzlibcasestepsService.get(ibzlibcasesteps_id);
        IbzLibCaseStepsDTO dto = ibzlibcasestepsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据用例库测试用例获取用例库用例步骤草稿", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例获取用例库用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/getdraft")
    public ResponseEntity<IbzLibCaseStepsDTO> getDraftByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, IbzLibCaseStepsDTO dto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(dto);
        domain.setIbizcase(ibzcase_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(ibzlibcasestepsService.getDraft(domain)));
    }

    @ApiOperation(value = "根据用例库测试用例检查用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例检查用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsService.checkKey(ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Save-all')")
    @ApiOperation(value = "根据用例库测试用例保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/save")
    public ResponseEntity<IbzLibCaseStepsDTO> saveByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsDTO ibzlibcasestepsdto) {
        IbzLibCaseSteps domain = ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdto);
        domain.setIbizcase(ibzcase_id);
        ibzlibcasestepsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibcasestepsMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-Save-all')")
    @ApiOperation(value = "根据用例库测试用例批量保存用例库用例步骤", tags = {"用例库用例步骤" },  notes = "根据用例库测试用例批量保存用例库用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody List<IbzLibCaseStepsDTO> ibzlibcasestepsdtos) {
        List<IbzLibCaseSteps> domainlist=ibzlibcasestepsMapping.toDomain(ibzlibcasestepsdtos);
        for(IbzLibCaseSteps domain:domainlist){
             domain.setIbizcase(ibzcase_id);
        }
        ibzlibcasestepsService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-searchDefault-all')")
	@ApiOperation(value = "根据用例库测试用例获取DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据用例库测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/fetchdefault")
	public ResponseEntity<List<IbzLibCaseStepsDTO>> fetchIbzLibCaseStepsDefaultByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id,IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
        List<IbzLibCaseStepsDTO> list = ibzlibcasestepsMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzLibCaseSteps-searchDefault-all')")
	@ApiOperation(value = "根据用例库测试用例查询DEFAULT", tags = {"用例库用例步骤" } ,notes = "根据用例库测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/ibzcases/{ibzcase_id}/ibzlibcasesteps/searchdefault")
	public ResponseEntity<Page<IbzLibCaseStepsDTO>> searchIbzLibCaseStepsDefaultByIbzLibIbzCase(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("ibzcase_id") Long ibzcase_id, @RequestBody IbzLibCaseStepsSearchContext context) {
        context.setN_case_eq(ibzcase_id);
        Page<IbzLibCaseSteps> domains = ibzlibcasestepsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibcasestepsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

