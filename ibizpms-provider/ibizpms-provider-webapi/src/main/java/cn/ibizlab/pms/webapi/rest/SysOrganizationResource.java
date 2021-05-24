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
import cn.ibizlab.pms.core.ou.domain.SysOrganization;
import cn.ibizlab.pms.core.ou.service.ISysOrganizationService;
import cn.ibizlab.pms.core.ou.filter.SysOrganizationSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"单位" })
@RestController("WebApi-sysorganization")
@RequestMapping("")
public class SysOrganizationResource {

    @Autowired
    public ISysOrganizationService sysorganizationService;


    @Autowired
    @Lazy
    public SysOrganizationMapping sysorganizationMapping;

    @ApiOperation(value = "新建单位", tags = {"单位" },  notes = "新建单位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysorganizations")
    @Transactional
    public ResponseEntity<SysOrganizationDTO> create(@Validated @RequestBody SysOrganizationDTO sysorganizationdto) {
        SysOrganization domain = sysorganizationMapping.toDomain(sysorganizationdto);
		sysorganizationService.create(domain);
        SysOrganizationDTO dto = sysorganizationMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量新建单位", tags = {"单位" },  notes = "批量新建单位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysorganizations/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<SysOrganizationDTO> sysorganizationdtos) {
        sysorganizationService.createBatch(sysorganizationMapping.toDomain(sysorganizationdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysorganization" , versionfield = "updatedate")
    @ApiOperation(value = "更新单位", tags = {"单位" },  notes = "更新单位")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysorganizations/{sysorganization_id}")
    @Transactional
    public ResponseEntity<SysOrganizationDTO> update(@PathVariable("sysorganization_id") String sysorganization_id, @RequestBody SysOrganizationDTO sysorganizationdto) {
		SysOrganization domain  = sysorganizationMapping.toDomain(sysorganizationdto);
        domain.setOrgid(sysorganization_id);
		sysorganizationService.update(domain );
		SysOrganizationDTO dto = sysorganizationMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量更新单位", tags = {"单位" },  notes = "批量更新单位")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysorganizations/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SysOrganizationDTO> sysorganizationdtos) {
        sysorganizationService.updateBatch(sysorganizationMapping.toDomain(sysorganizationdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "删除单位", tags = {"单位" },  notes = "删除单位")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysorganizations/{sysorganization_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysorganization_id") String sysorganization_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysorganizationService.remove(sysorganization_id));
    }

    @ApiOperation(value = "批量删除单位", tags = {"单位" },  notes = "批量删除单位")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysorganizations/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        sysorganizationService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "获取单位", tags = {"单位" },  notes = "获取单位")
	@RequestMapping(method = RequestMethod.GET, value = "/sysorganizations/{sysorganization_id}")
    public ResponseEntity<SysOrganizationDTO> get(@PathVariable("sysorganization_id") String sysorganization_id) {
        SysOrganization domain = sysorganizationService.get(sysorganization_id);
        SysOrganizationDTO dto = sysorganizationMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取单位草稿", tags = {"单位" },  notes = "获取单位草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysorganizations/getdraft")
    public ResponseEntity<SysOrganizationDTO> getDraft(SysOrganizationDTO dto) {
        SysOrganization domain = sysorganizationMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysorganizationMapping.toDto(sysorganizationService.getDraft(domain)));
    }

    @ApiOperation(value = "检查单位", tags = {"单位" },  notes = "检查单位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysorganizations/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysOrganizationDTO sysorganizationdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysorganizationService.checkKey(sysorganizationMapping.toDomain(sysorganizationdto)));
    }

    @ApiOperation(value = "保存单位", tags = {"单位" },  notes = "保存单位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysorganizations/save")
    public ResponseEntity<SysOrganizationDTO> save(@RequestBody SysOrganizationDTO sysorganizationdto) {
        SysOrganization domain = sysorganizationMapping.toDomain(sysorganizationdto);
        sysorganizationService.save(domain);
        SysOrganizationDTO dto = sysorganizationMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存单位", tags = {"单位" },  notes = "批量保存单位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysorganizations/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SysOrganizationDTO> sysorganizationdtos) {
        sysorganizationService.saveBatch(sysorganizationMapping.toDomain(sysorganizationdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "获取数据集", tags = {"单位" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/sysorganizations/fetchdefault")
	public ResponseEntity<List<SysOrganizationDTO>> fetchdefault(SysOrganizationSearchContext context) {
        Page<SysOrganization> domains = sysorganizationService.searchDefault(context) ;
        List<SysOrganizationDTO> list = sysorganizationMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"单位" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysorganizations/searchdefault")
	public ResponseEntity<Page<SysOrganizationDTO>> searchDefault(@RequestBody SysOrganizationSearchContext context) {
        Page<SysOrganization> domains = sysorganizationService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(sysorganizationMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysorganizations/{sysorganization_id}/{action}")
    public ResponseEntity<SysOrganizationDTO> dynamicCall(@PathVariable("sysorganization_id") String sysorganization_id , @PathVariable("action") String action , @RequestBody SysOrganizationDTO sysorganizationdto) {
        SysOrganization domain = sysorganizationService.dynamicCall(sysorganization_id, action, sysorganizationMapping.toDomain(sysorganizationdto));
        sysorganizationdto = sysorganizationMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysorganizationdto);
    }
}

