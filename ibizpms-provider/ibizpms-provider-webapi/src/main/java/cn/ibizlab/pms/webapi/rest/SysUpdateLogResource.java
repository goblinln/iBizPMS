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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.SysUpdateLogRuntime;

@Slf4j
@Api(tags = {"更新日志" })
@RestController("WebApi-sysupdatelog")
@RequestMapping("")
public class SysUpdateLogResource {

    @Autowired
    public ISysUpdateLogService sysupdatelogService;

    @Autowired
    public SysUpdateLogRuntime sysupdatelogRuntime;

    @Autowired
    @Lazy
    public SysUpdateLogMapping sysupdatelogMapping;

    @PreAuthorize("@SysUpdateLogRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建更新日志", tags = {"更新日志" },  notes = "新建更新日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs")
    @Transactional
    public ResponseEntity<SysUpdateLogDTO> create(@Validated @RequestBody SysUpdateLogDTO sysupdatelogdto) {
        SysUpdateLog domain = sysupdatelogMapping.toDomain(sysupdatelogdto);
		sysupdatelogService.create(domain);
        if(!sysupdatelogRuntime.test(domain.getSysupdatelogid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        SysUpdateLogDTO dto = sysupdatelogMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatelogRuntime.getOPPrivs(domain.getSysupdatelogid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "sysupdatelog" , versionfield = "updatedate")
    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'UPDATE')")
    @ApiOperation(value = "更新更新日志", tags = {"更新日志" },  notes = "更新更新日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatelogs/{sysupdatelog_id}")
    @Transactional
    public ResponseEntity<SysUpdateLogDTO> update(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateLogDTO sysupdatelogdto) {
		SysUpdateLog domain  = sysupdatelogMapping.toDomain(sysupdatelogdto);
        domain.setSysupdatelogid(sysupdatelog_id);
		sysupdatelogService.update(domain );
        if(!sysupdatelogRuntime.test(sysupdatelog_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		SysUpdateLogDTO dto = sysupdatelogMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatelogRuntime.getOPPrivs(sysupdatelog_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'DELETE')")
    @ApiOperation(value = "删除更新日志", tags = {"更新日志" },  notes = "删除更新日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatelogs/{sysupdatelog_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysupdatelog_id") String sysupdatelog_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysupdatelogService.remove(sysupdatelog_id));
    }


    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'READ')")
    @ApiOperation(value = "获取更新日志", tags = {"更新日志" },  notes = "获取更新日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/{sysupdatelog_id}")
    public ResponseEntity<SysUpdateLogDTO> get(@PathVariable("sysupdatelog_id") String sysupdatelog_id) {
        SysUpdateLog domain = sysupdatelogService.get(sysupdatelog_id);
        SysUpdateLogDTO dto = sysupdatelogMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatelogRuntime.getOPPrivs(sysupdatelog_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'CREATE')")
    @ApiOperation(value = "获取更新日志草稿", tags = {"更新日志" },  notes = "获取更新日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/getdraft")
    public ResponseEntity<SysUpdateLogDTO> getDraft(SysUpdateLogDTO dto) {
        SysUpdateLog domain = sysupdatelogMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatelogMapping.toDto(sysupdatelogService.getDraft(domain)));
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'CREATE')")
    @ApiOperation(value = "检查更新日志", tags = {"更新日志" },  notes = "检查更新日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysUpdateLogDTO sysupdatelogdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysupdatelogService.checkKey(sysupdatelogMapping.toDomain(sysupdatelogdto)));
    }

    @PreAuthorize("@SysUpdateLogRuntime.test(#sysupdatelog_id,'READ')")
    @ApiOperation(value = "获取最新更新信息", tags = {"更新日志" },  notes = "获取最新更新信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatelogs/{sysupdatelog_id}/getlastupdateinfo")
    public ResponseEntity<SysUpdateLogDTO> getLastUpdateInfo(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateLogDTO sysupdatelogdto) {
        SysUpdateLog domain = sysupdatelogMapping.toDomain(sysupdatelogdto);
        domain.setSysupdatelogid(sysupdatelog_id);
        domain = sysupdatelogService.getLastUpdateInfo(domain);
        sysupdatelogdto = sysupdatelogMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatelogRuntime.getOPPrivs(domain.getSysupdatelogid());
        sysupdatelogdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatelogdto);
    }


    @ApiOperation(value = "保存更新日志", tags = {"更新日志" },  notes = "保存更新日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/save")
    public ResponseEntity<SysUpdateLogDTO> save(@RequestBody SysUpdateLogDTO sysupdatelogdto) {
        SysUpdateLog domain = sysupdatelogMapping.toDomain(sysupdatelogdto);
        sysupdatelogService.save(domain);
        SysUpdateLogDTO dto = sysupdatelogMapping.toDto(domain);
        Map<String,Integer> opprivs = sysupdatelogRuntime.getOPPrivs(domain.getSysupdatelogid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"更新日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatelogs/fetchdefault")
	public ResponseEntity<List<SysUpdateLogDTO>> fetchdefault(@RequestBody SysUpdateLogSearchContext context) {
        Page<SysUpdateLog> domains = sysupdatelogService.searchDefault(context) ;
        List<SysUpdateLogDTO> list = sysupdatelogMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"更新日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatelogs/searchdefault")
	public ResponseEntity<Page<SysUpdateLogDTO>> searchDefault(@RequestBody SysUpdateLogSearchContext context) {
        Page<SysUpdateLog> domains = sysupdatelogService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(sysupdatelogMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/{action}")
    public ResponseEntity<SysUpdateLogDTO> dynamicCall(@PathVariable("sysupdatelog_id") String sysupdatelog_id , @PathVariable("action") String action , @RequestBody SysUpdateLogDTO sysupdatelogdto) {
        SysUpdateLog domain = sysupdatelogService.dynamicCall(sysupdatelog_id, action, sysupdatelogMapping.toDomain(sysupdatelogdto));
        sysupdatelogdto = sysupdatelogMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatelogdto);
    }
}

