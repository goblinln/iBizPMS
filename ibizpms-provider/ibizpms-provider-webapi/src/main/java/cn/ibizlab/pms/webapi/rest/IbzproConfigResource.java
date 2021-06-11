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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproConfigSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbzproConfigRuntime;

@Slf4j
@Api(tags = {"系统配置表"})
@RestController("WebApi-ibzproconfig")
@RequestMapping("")
public class IbzproConfigResource {

    @Autowired
    public IIbzproConfigService ibzproconfigService;

    @Autowired
    public IbzproConfigRuntime ibzproconfigRuntime;

    @Autowired
    @Lazy
    public IbzproConfigMapping ibzproconfigMapping;

    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'CREATE')")
    @ApiOperation(value = "新建系统配置表", tags = {"系统配置表" },  notes = "新建系统配置表")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproconfigs")
    @Transactional
    public ResponseEntity<IbzproConfigDTO> create(@Validated @RequestBody IbzproConfigDTO ibzproconfigdto) {
        IbzproConfig domain = ibzproconfigMapping.toDomain(ibzproconfigdto);
		ibzproconfigService.create(domain);
        if(!ibzproconfigRuntime.test(domain.getIbzproconfigid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzproConfigDTO dto = ibzproconfigMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproconfigRuntime.getOPPrivs(domain.getIbzproconfigid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_CONFIG', #ibzproconfig_id, 'READ')")
    @ApiOperation(value = "获取系统配置表", tags = {"系统配置表" },  notes = "获取系统配置表")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproconfigs/{ibzproconfig_id}")
    public ResponseEntity<IbzproConfigDTO> get(@PathVariable("ibzproconfig_id") String ibzproconfig_id) {
        IbzproConfig domain = ibzproconfigService.get(ibzproconfig_id);
        IbzproConfigDTO dto = ibzproconfigMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproconfigRuntime.getOPPrivs(ibzproconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_CONFIG', #ibzproconfig_id, 'DELETE')")
    @ApiOperation(value = "删除系统配置表", tags = {"系统配置表" },  notes = "删除系统配置表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproconfigs/{ibzproconfig_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproconfig_id") String ibzproconfig_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproconfigService.remove(ibzproconfig_id));
    }

    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'DELETE')")
    @ApiOperation(value = "批量删除系统配置表", tags = {"系统配置表" },  notes = "批量删除系统配置表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproconfigs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibzproconfigService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzproconfig" , versionfield = "updatedate")
    @PreAuthorize("test('IBZPRO_CONFIG', #ibzproconfig_id, 'UPDATE')")
    @ApiOperation(value = "更新系统配置表", tags = {"系统配置表" },  notes = "更新系统配置表")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproconfigs/{ibzproconfig_id}")
    @Transactional
    public ResponseEntity<IbzproConfigDTO> update(@PathVariable("ibzproconfig_id") String ibzproconfig_id, @RequestBody IbzproConfigDTO ibzproconfigdto) {
		IbzproConfig domain  = ibzproconfigMapping.toDomain(ibzproconfigdto);
        domain.setIbzproconfigid(ibzproconfig_id);
		ibzproconfigService.update(domain );
        if(!ibzproconfigRuntime.test(ibzproconfig_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzproConfigDTO dto = ibzproconfigMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproconfigRuntime.getOPPrivs(ibzproconfig_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'CREATE')")
    @ApiOperation(value = "检查系统配置表", tags = {"系统配置表" },  notes = "检查系统配置表")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproconfigs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzproConfigDTO ibzproconfigdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproconfigService.checkKey(ibzproconfigMapping.toDomain(ibzproconfigdto)));
    }

    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'CREATE')")
    @ApiOperation(value = "获取系统配置表草稿", tags = {"系统配置表" },  notes = "获取系统配置表草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproconfigs/getdraft")
    public ResponseEntity<IbzproConfigDTO> getDraft(IbzproConfigDTO dto) {
        IbzproConfig domain = ibzproconfigMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproconfigMapping.toDto(ibzproconfigService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZPRO_CONFIG', #ibzproconfig_id, 'READ')")
    @ApiOperation(value = "获取系统配置", tags = {"系统配置表" },  notes = "获取系统配置")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproconfigs/{ibzproconfig_id}/getsystemconfig")
    public ResponseEntity<IbzproConfigDTO> getSystemConfig(@PathVariable("ibzproconfig_id") String ibzproconfig_id, @RequestBody IbzproConfigDTO ibzproconfigdto) {
        IbzproConfig domain = ibzproconfigMapping.toDomain(ibzproconfigdto);
        domain.setIbzproconfigid(ibzproconfig_id);
        domain = ibzproconfigService.getSystemConfig(domain);
        ibzproconfigdto = ibzproconfigMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproconfigRuntime.getOPPrivs(domain.getIbzproconfigid());
        ibzproconfigdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproconfigdto);
    }


    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'DENY')")
    @ApiOperation(value = "保存系统配置表", tags = {"系统配置表" },  notes = "保存系统配置表")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproconfigs/save")
    public ResponseEntity<IbzproConfigDTO> save(@RequestBody IbzproConfigDTO ibzproconfigdto) {
        IbzproConfig domain = ibzproconfigMapping.toDomain(ibzproconfigdto);
        ibzproconfigService.save(domain);
        IbzproConfigDTO dto = ibzproconfigMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproconfigRuntime.getOPPrivs(domain.getIbzproconfigid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_CONFIG', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"系统配置表" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproconfigs/fetchdefault")
	public ResponseEntity<List<IbzproConfigDTO>> fetchdefault(@RequestBody IbzproConfigSearchContext context) {
        ibzproconfigRuntime.addAuthorityConditions(context,"READ");
        Page<IbzproConfig> domains = ibzproconfigService.searchDefault(context) ;
        List<IbzproConfigDTO> list = ibzproconfigMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成系统配置表报表", tags = {"系统配置表"}, notes = "生成系统配置表报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproconfigs/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzproConfigSearchContext context, HttpServletResponse response) {
        try {
            ibzproconfigRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproconfigRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzproconfigRuntime);
        }
    }

    @ApiOperation(value = "打印系统配置表", tags = {"系统配置表"}, notes = "打印系统配置表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproconfigs/{ibzproconfig_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzproconfig_ids") Set<String> ibzproconfig_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzproconfigRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzproConfig> domains = new ArrayList<>();
            for (String ibzproconfig_id : ibzproconfig_ids) {
                domains.add(ibzproconfigService.get( ibzproconfig_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzproConfig[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproconfigRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzproconfig_ids, e.getMessage()), Errors.INTERNALERROR, ibzproconfigRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproconfigs/{ibzproconfig_id}/{action}")
    public ResponseEntity<IbzproConfigDTO> dynamicCall(@PathVariable("ibzproconfig_id") String ibzproconfig_id , @PathVariable("action") String action , @RequestBody IbzproConfigDTO ibzproconfigdto) {
        IbzproConfig domain = ibzproconfigService.dynamicCall(ibzproconfig_id, action, ibzproconfigMapping.toDomain(ibzproconfigdto));
        ibzproconfigdto = ibzproconfigMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproconfigdto);
    }
}

