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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateFeaturesService;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.SysUpdateFeaturesRuntime;

@Slf4j
@Api(tags = {"系统更新功能" })
@RestController("WebApi-sysupdatefeatures")
@RequestMapping("")
public class SysUpdateFeaturesResource {

    @Autowired
    public ISysUpdateFeaturesService sysupdatefeaturesService;

    @Autowired
    public SysUpdateFeaturesRuntime sysupdatefeaturesRuntime;

    @Autowired
    @Lazy
    public SysUpdateFeaturesMapping sysupdatefeaturesMapping;

    @PreAuthorize("@SysUpdateFeaturesRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建系统更新功能", tags = {"系统更新功能" },  notes = "新建系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures")
    @Transactional
    public ResponseEntity<SysUpdateFeaturesDTO> create(@Validated @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
		sysupdatefeaturesService.create(domain);
        if(!sysupdatefeaturesRuntime.test(domain.getSysupdatefeaturesid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建系统更新功能", tags = {"系统更新功能" },  notes = "批量新建系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        sysupdatefeaturesService.createBatch(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysupdatefeatures" , versionfield = "updatedate")
    @PreAuthorize("@SysUpdateFeaturesRuntime.test(#sysupdatefeatures_id,'UPDATE')")
    @ApiOperation(value = "更新系统更新功能", tags = {"系统更新功能" },  notes = "更新系统更新功能")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatefeatures/{sysupdatefeatures_id}")
    @Transactional
    public ResponseEntity<SysUpdateFeaturesDTO> update(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
		SysUpdateFeatures domain  = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatefeaturesid(sysupdatefeatures_id);
		sysupdatefeaturesService.update(domain );
        if(!sysupdatefeaturesRuntime.test(sysupdatefeatures_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新系统更新功能", tags = {"系统更新功能" },  notes = "批量更新系统更新功能")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        sysupdatefeaturesService.updateBatch(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.test(#sysupdatefeatures_id,'DELETE')")
    @ApiOperation(value = "删除系统更新功能", tags = {"系统更新功能" },  notes = "删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.remove(sysupdatefeatures_id));
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除系统更新功能", tags = {"系统更新功能" },  notes = "批量删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        sysupdatefeaturesService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.test(#sysupdatefeatures_id,'READ')")
    @ApiOperation(value = "获取系统更新功能", tags = {"系统更新功能" },  notes = "获取系统更新功能")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> get(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
        SysUpdateFeatures domain = sysupdatefeaturesService.get(sysupdatefeatures_id);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs({sysupdatefeatures_id});
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取系统更新功能草稿", tags = {"系统更新功能" },  notes = "获取系统更新功能草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/getdraft")
    public ResponseEntity<SysUpdateFeaturesDTO> getDraft(SysUpdateFeaturesDTO dto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(sysupdatefeaturesService.getDraft(domain)));
    }

    @ApiOperation(value = "检查系统更新功能", tags = {"系统更新功能" },  notes = "检查系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.checkKey(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto)));
    }

    @ApiOperation(value = "保存系统更新功能", tags = {"系统更新功能" },  notes = "保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/save")
    public ResponseEntity<SysUpdateFeaturesDTO> save(@RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        sysupdatefeaturesService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存系统更新功能", tags = {"系统更新功能" },  notes = "批量保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        sysupdatefeaturesService.saveBatch(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateFeaturesRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"系统更新功能" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatefeatures/fetchdefault")
	public ResponseEntity<List<SysUpdateFeaturesDTO>> fetchdefault(@RequestBody SysUpdateFeaturesSearchContext context) {
        Page<SysUpdateFeatures> domains = sysupdatefeaturesService.searchDefault(context) ;
        List<SysUpdateFeaturesDTO> list = sysupdatefeaturesMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@SysUpdateFeaturesRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"系统更新功能" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatefeatures/searchdefault")
	public ResponseEntity<Page<SysUpdateFeaturesDTO>> searchDefault(@RequestBody SysUpdateFeaturesSearchContext context) {
        Page<SysUpdateFeatures> domains = sysupdatefeaturesService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(sysupdatefeaturesMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/{sysupdatefeatures_id}/{action}")
    public ResponseEntity<SysUpdateFeaturesDTO> dynamicCall(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id , @PathVariable("action") String action , @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesService.dynamicCall(sysupdatefeatures_id, action, sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto));
        sysupdatefeaturesdto = sysupdatefeaturesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesdto);
    }
    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'CREATE')")
    @ApiOperation(value = "根据更新日志建立系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志建立系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures")
    public ResponseEntity<SysUpdateFeaturesDTO> createBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
		sysupdatefeaturesService.create(domain);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'CREATE')")
    @ApiOperation(value = "根据更新日志批量建立系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志批量建立系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> createBatchBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        List<SysUpdateFeatures> domainlist=sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos);
        for(SysUpdateFeatures domain:domainlist){
            domain.setSysupdatelogid(sysupdatelog_id);
        }
        sysupdatefeaturesService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysupdatefeatures" , versionfield = "updatedate")
    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'UPDATE')")
    @ApiOperation(value = "根据更新日志更新系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志更新系统更新功能")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> updateBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
        domain.setSysupdatefeaturesid(sysupdatefeatures_id);
		sysupdatefeaturesService.update(domain);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'UPDATE')")
    @ApiOperation(value = "根据更新日志批量更新系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志批量更新系统更新功能")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> updateBatchBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        List<SysUpdateFeatures> domainlist=sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos);
        for(SysUpdateFeatures domain:domainlist){
            domain.setSysupdatelogid(sysupdatelog_id);
        }
        sysupdatefeaturesService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'DELETE')")
    @ApiOperation(value = "根据更新日志删除系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<Boolean> removeBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
		return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.remove(sysupdatefeatures_id));
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'DELETE')")
    @ApiOperation(value = "根据更新日志批量删除系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志批量删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> removeBatchBySysUpdateLog(@RequestBody List<String> ids) {
        sysupdatefeaturesService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'READ')")
    @ApiOperation(value = "根据更新日志获取系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志获取系统更新功能")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> getBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
        SysUpdateFeatures domain = sysupdatefeaturesService.get(sysupdatefeatures_id);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据更新日志获取系统更新功能草稿", tags = {"系统更新功能" },  notes = "根据更新日志获取系统更新功能草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/getdraft")
    public ResponseEntity<SysUpdateFeaturesDTO> getDraftBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, SysUpdateFeaturesDTO dto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(dto);
        domain.setSysupdatelogid(sysupdatelog_id);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(sysupdatefeaturesService.getDraft(domain)));
    }

    @ApiOperation(value = "根据更新日志检查系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志检查系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/checkkey")
    public ResponseEntity<Boolean> checkKeyBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.checkKey(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto)));
    }

    @ApiOperation(value = "根据更新日志保存系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/save")
    public ResponseEntity<SysUpdateFeaturesDTO> saveBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
        sysupdatefeaturesService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(domain));
    }

    @ApiOperation(value = "根据更新日志批量保存系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志批量保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/savebatch")
    public ResponseEntity<Boolean> saveBatchBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody List<SysUpdateFeaturesDTO> sysupdatefeaturesdtos) {
        List<SysUpdateFeatures> domainlist=sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdtos);
        for(SysUpdateFeatures domain:domainlist){
             domain.setSysupdatelogid(sysupdatelog_id);
        }
        sysupdatefeaturesService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'READ')")
	@ApiOperation(value = "根据更新日志获取数据集", tags = {"系统更新功能" } ,notes = "根据更新日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/fetchdefault")
	public ResponseEntity<List<SysUpdateFeaturesDTO>> fetchSysUpdateFeaturesDefaultBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id,@RequestBody SysUpdateFeaturesSearchContext context) {
        context.setN_sys_update_logid_eq(sysupdatelog_id);
        Page<SysUpdateFeatures> domains = sysupdatefeaturesService.searchDefault(context) ;
        List<SysUpdateFeaturesDTO> list = sysupdatefeaturesMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'READ')")
	@ApiOperation(value = "根据更新日志查询数据集", tags = {"系统更新功能" } ,notes = "根据更新日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/searchdefault")
	public ResponseEntity<Page<SysUpdateFeaturesDTO>> searchSysUpdateFeaturesDefaultBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesSearchContext context) {
        context.setN_sys_update_logid_eq(sysupdatelog_id);
        Page<SysUpdateFeatures> domains = sysupdatefeaturesService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(sysupdatefeaturesMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

