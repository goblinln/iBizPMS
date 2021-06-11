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
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateFeaturesService;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.SysUpdateFeaturesRuntime;

@Slf4j
@Api(tags = {"系统更新功能"})
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

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "新建系统更新功能", tags = {"系统更新功能" },  notes = "新建系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures")
    @Transactional
    public ResponseEntity<SysUpdateFeaturesDTO> create(@Validated @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
		sysupdatefeaturesService.create(domain);
        if(!sysupdatefeaturesRuntime.test(domain.getSysupdatefeaturesid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(domain.getSysupdatefeaturesid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('SYS_UPDATE_FEATURES', #sysupdatefeatures_id, 'READ')")
    @ApiOperation(value = "获取系统更新功能", tags = {"系统更新功能" },  notes = "获取系统更新功能")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> get(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
        SysUpdateFeatures domain = sysupdatefeaturesService.get(sysupdatefeatures_id);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(sysupdatefeatures_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('SYS_UPDATE_FEATURES', #sysupdatefeatures_id, 'DELETE')")
    @ApiOperation(value = "删除系统更新功能", tags = {"系统更新功能" },  notes = "删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.remove(sysupdatefeatures_id));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'DELETE')")
    @ApiOperation(value = "批量删除系统更新功能", tags = {"系统更新功能" },  notes = "批量删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        sysupdatefeaturesService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysupdatefeatures" , versionfield = "updatedate")
    @PreAuthorize("test('SYS_UPDATE_FEATURES', #sysupdatefeatures_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(sysupdatefeatures_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "检查系统更新功能", tags = {"系统更新功能" },  notes = "检查系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.checkKey(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto)));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "获取系统更新功能草稿", tags = {"系统更新功能" },  notes = "获取系统更新功能草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/getdraft")
    public ResponseEntity<SysUpdateFeaturesDTO> getDraft(SysUpdateFeaturesDTO dto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(sysupdatefeaturesService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'DENY')")
    @ApiOperation(value = "保存系统更新功能", tags = {"系统更新功能" },  notes = "保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/save")
    public ResponseEntity<SysUpdateFeaturesDTO> save(@RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        sysupdatefeaturesService.save(domain);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(domain.getSysupdatefeaturesid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'READ')")
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

    @ApiOperation(value = "生成系统更新功能报表", tags = {"系统更新功能"}, notes = "生成系统更新功能报表")
    @RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysUpdateFeaturesSearchContext context, HttpServletResponse response) {
        try {
            sysupdatefeaturesRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", sysupdatefeaturesRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, sysupdatefeaturesRuntime);
        }
    }

    @ApiOperation(value = "打印系统更新功能", tags = {"系统更新功能"}, notes = "打印系统更新功能")
    @RequestMapping(method = RequestMethod.GET, value = "/sysupdatefeatures/{sysupdatefeatures_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("sysupdatefeatures_ids") Set<String> sysupdatefeatures_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = sysupdatefeaturesRuntime.getDEPrintRuntime(print_id);
        try {
            List<SysUpdateFeatures> domains = new ArrayList<>();
            for (String sysupdatefeatures_id : sysupdatefeatures_ids) {
                domains.add(sysupdatefeaturesService.get( sysupdatefeatures_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new SysUpdateFeatures[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", sysupdatefeaturesRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", sysupdatefeatures_ids, e.getMessage()), Errors.INTERNALERROR, sysupdatefeaturesRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/sysupdatefeatures/{sysupdatefeatures_id}/{action}")
    public ResponseEntity<SysUpdateFeaturesDTO> dynamicCall(@PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id , @PathVariable("action") String action , @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesService.dynamicCall(sysupdatefeatures_id, action, sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto));
        sysupdatefeaturesdto = sysupdatefeaturesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesdto);
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "根据更新日志建立系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志建立系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures")
    public ResponseEntity<SysUpdateFeaturesDTO> createBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
		sysupdatefeaturesService.create(domain);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(domain.getSysupdatefeaturesid());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'READ')")
    @ApiOperation(value = "根据更新日志获取系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志获取系统更新功能")
	@RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> getBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
        SysUpdateFeatures domain = sysupdatefeaturesService.get(sysupdatefeatures_id);
        if (domain == null || !(sysupdatelog_id.equals(domain.getSysupdatelogid())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(domain.getSysupdatefeaturesid());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'DELETE')")
    @ApiOperation(value = "根据更新日志删除系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<Boolean> removeBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id) {
        SysUpdateFeatures testget = sysupdatefeaturesService.get(sysupdatefeatures_id);
        if (testget == null || !(sysupdatelog_id.equals(testget.getSysupdatelogid())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.remove(sysupdatefeatures_id));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'DELETE')")
    @ApiOperation(value = "根据更新日志批量删除系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志批量删除系统更新功能")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/batch")
    public ResponseEntity<Boolean> removeBatchBySysUpdateLog(@RequestBody List<String> ids) {
        sysupdatefeaturesService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysupdatefeatures" , versionfield = "updatedate")
    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'UPDATE')")
    @ApiOperation(value = "根据更新日志更新系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志更新系统更新功能")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/{sysupdatefeatures_id}")
    public ResponseEntity<SysUpdateFeaturesDTO> updateBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @PathVariable("sysupdatefeatures_id") String sysupdatefeatures_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures testget = sysupdatefeaturesService.get(sysupdatefeatures_id);
        if (testget == null || !(sysupdatelog_id.equals(testget.getSysupdatelogid())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
        domain.setSysupdatefeaturesid(sysupdatefeatures_id);
		sysupdatefeaturesService.update(domain);
        SysUpdateFeaturesDTO dto = sysupdatefeaturesMapping.toDto(domain);
        Map<String, Integer> opprivs = sysupdatefeaturesRuntime.getOPPrivs(domain.getSysupdatefeaturesid());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "根据更新日志检查系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志检查系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/checkkey")
    public ResponseEntity<Boolean> checkKeyBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesService.checkKey(sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto)));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'CREATE')")
    @ApiOperation(value = "根据更新日志获取系统更新功能草稿", tags = {"系统更新功能" },  notes = "根据更新日志获取系统更新功能草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/getdraft")
    public ResponseEntity<SysUpdateFeaturesDTO> getDraftBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, SysUpdateFeaturesDTO dto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(dto);
        domain.setSysupdatelogid(sysupdatelog_id);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(sysupdatefeaturesService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'DENY')")
    @ApiOperation(value = "根据更新日志保存系统更新功能", tags = {"系统更新功能" },  notes = "根据更新日志保存系统更新功能")
	@RequestMapping(method = RequestMethod.POST, value = "/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/save")
    public ResponseEntity<SysUpdateFeaturesDTO> saveBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id, @RequestBody SysUpdateFeaturesDTO sysupdatefeaturesdto) {
        SysUpdateFeatures domain = sysupdatefeaturesMapping.toDomain(sysupdatefeaturesdto);
        domain.setSysupdatelogid(sysupdatelog_id);
        sysupdatefeaturesService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysupdatefeaturesMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('SYS_UPDATE_FEATURES', 'READ')")
	@ApiOperation(value = "根据更新日志获取数据集", tags = {"系统更新功能" } ,notes = "根据更新日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysupdatelogs/{sysupdatelog_id}/sysupdatefeatures/fetchdefault")
	public ResponseEntity<List<SysUpdateFeaturesDTO>> fetchDefaultBySysUpdateLog(@PathVariable("sysupdatelog_id") String sysupdatelog_id,@RequestBody SysUpdateFeaturesSearchContext context) {
        context.setN_sys_update_logid_eq(sysupdatelog_id);
        Page<SysUpdateFeatures> domains = sysupdatefeaturesService.searchDefault(context) ;
        List<SysUpdateFeaturesDTO> list = sysupdatefeaturesMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

