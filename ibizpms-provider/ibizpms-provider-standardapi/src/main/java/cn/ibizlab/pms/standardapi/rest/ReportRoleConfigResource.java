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
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.core.report.service.IIbzReportRoleConfigService;
import cn.ibizlab.pms.core.report.filter.IbzReportRoleConfigSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportRoleConfigRuntime;

@Slf4j
@Api(tags = {"汇报角色配置" })
@RestController("StandardAPI-reportroleconfig")
@RequestMapping("")
public class ReportRoleConfigResource {

    @Autowired
    public IIbzReportRoleConfigService ibzreportroleconfigService;

    @Autowired
    public IbzReportRoleConfigRuntime ibzreportroleconfigRuntime;

    @Autowired
    @Lazy
    public ReportRoleConfigMapping reportroleconfigMapping;

    @PreAuthorize("quickTest('IBZ_REPORT_ROLE_CONFIG', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"汇报角色配置" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/reportroleconfigs/fetchdefault")
	public ResponseEntity<List<ReportRoleConfigDTO>> fetchdefault(@RequestBody IbzReportRoleConfigSearchContext context) {
        ibzreportroleconfigRuntime.addAuthorityConditions(context,"READ");
        Page<IbzReportRoleConfig> domains = ibzreportroleconfigService.searchDefault(context) ;
        List<ReportRoleConfigDTO> list = reportroleconfigMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "ibzreportroleconfig" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_REPORT_ROLE_CONFIG', #reportroleconfig_id, 'UPDATE')")
    @ApiOperation(value = "更新汇报角色配置", tags = {"汇报角色配置" },  notes = "更新汇报角色配置")
	@RequestMapping(method = RequestMethod.PUT, value = "/reportroleconfigs/{reportroleconfig_id}")
    @Transactional
    public ResponseEntity<ReportRoleConfigDTO> update(@PathVariable("reportroleconfig_id") String reportroleconfig_id, @RequestBody ReportRoleConfigDTO reportroleconfigdto) {
		IbzReportRoleConfig domain  = reportroleconfigMapping.toDomain(reportroleconfigdto);
        domain.setIbzreportroleconfigid(reportroleconfig_id);
		ibzreportroleconfigService.update(domain );
        if(!ibzreportroleconfigRuntime.test(reportroleconfig_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ReportRoleConfigDTO dto = reportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(reportroleconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_REPORT_ROLE_CONFIG', #reportroleconfig_id, 'READ')")
    @ApiOperation(value = "获取汇报角色配置", tags = {"汇报角色配置" },  notes = "获取汇报角色配置")
	@RequestMapping(method = RequestMethod.GET, value = "/reportroleconfigs/{reportroleconfig_id}")
    public ResponseEntity<ReportRoleConfigDTO> get(@PathVariable("reportroleconfig_id") String reportroleconfig_id) {
        IbzReportRoleConfig domain = ibzreportroleconfigService.get(reportroleconfig_id);
        ReportRoleConfigDTO dto = reportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(reportroleconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_REPORT_ROLE_CONFIG', 'CREATE')")
    @ApiOperation(value = "新建汇报角色配置", tags = {"汇报角色配置" },  notes = "新建汇报角色配置")
	@RequestMapping(method = RequestMethod.POST, value = "/reportroleconfigs")
    @Transactional
    public ResponseEntity<ReportRoleConfigDTO> create(@Validated @RequestBody ReportRoleConfigDTO reportroleconfigdto) {
        IbzReportRoleConfig domain = reportroleconfigMapping.toDomain(reportroleconfigdto);
		ibzreportroleconfigService.create(domain);
        if(!ibzreportroleconfigRuntime.test(domain.getIbzreportroleconfigid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ReportRoleConfigDTO dto = reportroleconfigMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzreportroleconfigRuntime.getOPPrivs(domain.getIbzreportroleconfigid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORT_ROLE_CONFIG', #reportroleconfig_id, 'DELETE')")
    @ApiOperation(value = "删除汇报角色配置", tags = {"汇报角色配置" },  notes = "删除汇报角色配置")
	@RequestMapping(method = RequestMethod.DELETE, value = "/reportroleconfigs/{reportroleconfig_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("reportroleconfig_id") String reportroleconfig_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportroleconfigService.remove(reportroleconfig_id));
    }

    @PreAuthorize("quickTest('IBZ_REPORT_ROLE_CONFIG', 'DELETE')")
    @ApiOperation(value = "批量删除汇报角色配置", tags = {"汇报角色配置" },  notes = "批量删除汇报角色配置")
	@RequestMapping(method = RequestMethod.DELETE, value = "/reportroleconfigs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibzreportroleconfigService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_REPORT_ROLE_CONFIG', 'CREATE')")
    @ApiOperation(value = "获取汇报角色配置草稿", tags = {"汇报角色配置" },  notes = "获取汇报角色配置草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/reportroleconfigs/getdraft")
    public ResponseEntity<ReportRoleConfigDTO> getDraft(ReportRoleConfigDTO dto) {
        IbzReportRoleConfig domain = reportroleconfigMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(reportroleconfigMapping.toDto(ibzreportroleconfigService.getDraft(domain)));
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/reportroleconfigs/{reportroleconfig_id}/{action}")
    public ResponseEntity<ReportRoleConfigDTO> dynamicCall(@PathVariable("reportroleconfig_id") String reportroleconfig_id , @PathVariable("action") String action , @RequestBody ReportRoleConfigDTO reportroleconfigdto) {
        IbzReportRoleConfig domain = ibzreportroleconfigService.dynamicCall(reportroleconfig_id, action, reportroleconfigMapping.toDomain(reportroleconfigdto));
        reportroleconfigdto = reportroleconfigMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(reportroleconfigdto);
    }
}

