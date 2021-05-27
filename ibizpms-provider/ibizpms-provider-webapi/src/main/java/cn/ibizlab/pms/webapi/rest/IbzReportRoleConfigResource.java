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
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.report.filter.IbzReportRoleConfigSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportRoleConfigRuntime;

@Slf4j
@Api(tags = {"汇报角色配置" })
@RestController("WebApi-ibzreportroleconfig")
@RequestMapping("")
public class IbzReportRoleConfigResource {

    @Autowired
    public IIbzReportRoleConfigService ibzreportroleconfigService;

    @Autowired
    public IbzReportRoleConfigRuntime ibzreportroleconfigRuntime;

    @Autowired
    @Lazy
    public IbzReportRoleConfigMapping ibzreportroleconfigMapping;

    @PreAuthorize("@IbzReportRoleConfigRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建汇报角色配置", tags = {"汇报角色配置" },  notes = "新建汇报角色配置")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportroleconfigs")
    @Transactional
    public ResponseEntity<IbzReportRoleConfigDTO> create(@Validated @RequestBody IbzReportRoleConfigDTO ibzreportroleconfigdto) {
        IbzReportRoleConfig domain = ibzreportroleconfigMapping.toDomain(ibzreportroleconfigdto);
		ibzreportroleconfigService.create(domain);
        if(!ibzreportroleconfigRuntime.test(domain.getIbzreportroleconfigid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzReportRoleConfigDTO dto = ibzreportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(domain.getIbzreportroleconfigid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzreportroleconfig" , versionfield = "updatedate")
    @PreAuthorize("@IbzReportRoleConfigRuntime.test(#ibzreportroleconfig_id,'UPDATE')")
    @ApiOperation(value = "更新汇报角色配置", tags = {"汇报角色配置" },  notes = "更新汇报角色配置")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportroleconfigs/{ibzreportroleconfig_id}")
    @Transactional
    public ResponseEntity<IbzReportRoleConfigDTO> update(@PathVariable("ibzreportroleconfig_id") String ibzreportroleconfig_id, @RequestBody IbzReportRoleConfigDTO ibzreportroleconfigdto) {
		IbzReportRoleConfig domain  = ibzreportroleconfigMapping.toDomain(ibzreportroleconfigdto);
        domain.setIbzreportroleconfigid(ibzreportroleconfig_id);
		ibzreportroleconfigService.update(domain );
        if(!ibzreportroleconfigRuntime.test(ibzreportroleconfig_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzReportRoleConfigDTO dto = ibzreportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(ibzreportroleconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportRoleConfigRuntime.test(#ibzreportroleconfig_id,'DELETE')")
    @ApiOperation(value = "删除汇报角色配置", tags = {"汇报角色配置" },  notes = "删除汇报角色配置")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportroleconfigs/{ibzreportroleconfig_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzreportroleconfig_id") String ibzreportroleconfig_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportroleconfigService.remove(ibzreportroleconfig_id));
    }


    @PreAuthorize("@IbzReportRoleConfigRuntime.test(#ibzreportroleconfig_id,'READ')")
    @ApiOperation(value = "获取汇报角色配置", tags = {"汇报角色配置" },  notes = "获取汇报角色配置")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportroleconfigs/{ibzreportroleconfig_id}")
    public ResponseEntity<IbzReportRoleConfigDTO> get(@PathVariable("ibzreportroleconfig_id") String ibzreportroleconfig_id) {
        IbzReportRoleConfig domain = ibzreportroleconfigService.get(ibzreportroleconfig_id);
        IbzReportRoleConfigDTO dto = ibzreportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(ibzreportroleconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportRoleConfigRuntime.test(#ibzreportroleconfig_id,'CREATE')")
    @ApiOperation(value = "获取汇报角色配置草稿", tags = {"汇报角色配置" },  notes = "获取汇报角色配置草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportroleconfigs/getdraft")
    public ResponseEntity<IbzReportRoleConfigDTO> getDraft(IbzReportRoleConfigDTO dto) {
        IbzReportRoleConfig domain = ibzreportroleconfigMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportroleconfigMapping.toDto(ibzreportroleconfigService.getDraft(domain)));
    }

    @PreAuthorize("@IbzReportRoleConfigRuntime.test(#ibzreportroleconfig_id,'CREATE')")
    @ApiOperation(value = "检查汇报角色配置", tags = {"汇报角色配置" },  notes = "检查汇报角色配置")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportroleconfigs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportRoleConfigDTO ibzreportroleconfigdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportroleconfigService.checkKey(ibzreportroleconfigMapping.toDomain(ibzreportroleconfigdto)));
    }

    @ApiOperation(value = "保存汇报角色配置", tags = {"汇报角色配置" },  notes = "保存汇报角色配置")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportroleconfigs/save")
    public ResponseEntity<IbzReportRoleConfigDTO> save(@RequestBody IbzReportRoleConfigDTO ibzreportroleconfigdto) {
        IbzReportRoleConfig domain = ibzreportroleconfigMapping.toDomain(ibzreportroleconfigdto);
        ibzreportroleconfigService.save(domain);
        IbzReportRoleConfigDTO dto = ibzreportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(domain.getIbzreportroleconfigid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportRoleConfigRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"汇报角色配置" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportroleconfigs/fetchdefault")
	public ResponseEntity<List<IbzReportRoleConfigDTO>> fetchdefault(@RequestBody IbzReportRoleConfigSearchContext context) {
        ibzreportroleconfigRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReportRoleConfig> domains = ibzreportroleconfigService.searchDefault(context) ;
        List<IbzReportRoleConfigDTO> list = ibzreportroleconfigMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzReportRoleConfigRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"汇报角色配置" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportroleconfigs/searchdefault")
	public ResponseEntity<Page<IbzReportRoleConfigDTO>> searchDefault(@RequestBody IbzReportRoleConfigSearchContext context) {
        ibzreportroleconfigRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReportRoleConfig> domains = ibzreportroleconfigService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportroleconfigMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzreportroleconfigs/{ibzreportroleconfig_id}/{action}")
    public ResponseEntity<IbzReportRoleConfigDTO> dynamicCall(@PathVariable("ibzreportroleconfig_id") String ibzreportroleconfig_id , @PathVariable("action") String action , @RequestBody IbzReportRoleConfigDTO ibzreportroleconfigdto) {
        IbzReportRoleConfig domain = ibzreportroleconfigService.dynamicCall(ibzreportroleconfig_id, action, ibzreportroleconfigMapping.toDomain(ibzreportroleconfigdto));
        ibzreportroleconfigdto = ibzreportroleconfigMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportroleconfigdto);
    }
}

