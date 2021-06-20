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
import cn.ibizlab.pms.core.ibiz.domain.EmpLoyeeload;
import cn.ibizlab.pms.core.ibiz.service.IEmpLoyeeloadService;
import cn.ibizlab.pms.core.ibiz.filter.EmpLoyeeloadSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.EmpLoyeeloadRuntime;

@Slf4j
@Api(tags = {"员工负载表"})
@RestController("WebApi-employeeload")
@RequestMapping("")
public class EmpLoyeeloadResource {

    @Autowired
    public IEmpLoyeeloadService employeeloadService;

    @Autowired
    public EmpLoyeeloadRuntime employeeloadRuntime;

    @Autowired
    @Lazy
    public EmpLoyeeloadMapping employeeloadMapping;

    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'CREATE')")
    @ApiOperation(value = "新建员工负载表", tags = {"员工负载表" },  notes = "新建员工负载表")
	@RequestMapping(method = RequestMethod.POST, value = "/employeeloads")
    @Transactional
    public ResponseEntity<EmpLoyeeloadDTO> create(@Validated @RequestBody EmpLoyeeloadDTO employeeloaddto) {
        EmpLoyeeload domain = employeeloadMapping.toDomain(employeeloaddto);
		employeeloadService.create(domain);
        if(!employeeloadRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        EmpLoyeeloadDTO dto = employeeloadMapping.toDto(domain);
        Map<String, Integer> opprivs = employeeloadRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_EMPLOYEELOAD', #employeeload_id, 'READ')")
    @ApiOperation(value = "获取员工负载表", tags = {"员工负载表" },  notes = "获取员工负载表")
	@RequestMapping(method = RequestMethod.GET, value = "/employeeloads/{employeeload_id}")
    public ResponseEntity<EmpLoyeeloadDTO> get(@PathVariable("employeeload_id") Long employeeload_id) {
        EmpLoyeeload domain = employeeloadService.get(employeeload_id);
        EmpLoyeeloadDTO dto = employeeloadMapping.toDto(domain);
        Map<String, Integer> opprivs = employeeloadRuntime.getOPPrivs(employeeload_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_EMPLOYEELOAD', #employeeload_id, 'DELETE')")
    @ApiOperation(value = "删除员工负载表", tags = {"员工负载表" },  notes = "删除员工负载表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/employeeloads/{employeeload_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("employeeload_id") Long employeeload_id) {
         return ResponseEntity.status(HttpStatus.OK).body(employeeloadService.remove(employeeload_id));
    }

    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'DELETE')")
    @ApiOperation(value = "批量删除员工负载表", tags = {"员工负载表" },  notes = "批量删除员工负载表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/employeeloads/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        employeeloadService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_EMPLOYEELOAD', #employeeload_id, 'UPDATE')")
    @ApiOperation(value = "更新员工负载表", tags = {"员工负载表" },  notes = "更新员工负载表")
	@RequestMapping(method = RequestMethod.PUT, value = "/employeeloads/{employeeload_id}")
    @Transactional
    public ResponseEntity<EmpLoyeeloadDTO> update(@PathVariable("employeeload_id") Long employeeload_id, @RequestBody EmpLoyeeloadDTO employeeloaddto) {
		EmpLoyeeload domain  = employeeloadMapping.toDomain(employeeloaddto);
        domain.setId(employeeload_id);
		employeeloadService.update(domain );
        if(!employeeloadRuntime.test(employeeload_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		EmpLoyeeloadDTO dto = employeeloadMapping.toDto(domain);
        Map<String, Integer> opprivs = employeeloadRuntime.getOPPrivs(employeeload_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'CREATE')")
    @ApiOperation(value = "检查员工负载表", tags = {"员工负载表" },  notes = "检查员工负载表")
	@RequestMapping(method = RequestMethod.POST, value = "/employeeloads/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody EmpLoyeeloadDTO employeeloaddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(employeeloadService.checkKey(employeeloadMapping.toDomain(employeeloaddto)));
    }

    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'CREATE')")
    @ApiOperation(value = "获取员工负载表草稿", tags = {"员工负载表" },  notes = "获取员工负载表草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/employeeloads/getdraft")
    public ResponseEntity<EmpLoyeeloadDTO> getDraft(EmpLoyeeloadDTO dto) {
        EmpLoyeeload domain = employeeloadMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(employeeloadMapping.toDto(employeeloadService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'DENY')")
    @ApiOperation(value = "保存员工负载表", tags = {"员工负载表" },  notes = "保存员工负载表")
	@RequestMapping(method = RequestMethod.POST, value = "/employeeloads/save")
    public ResponseEntity<EmpLoyeeloadDTO> save(@RequestBody EmpLoyeeloadDTO employeeloaddto) {
        EmpLoyeeload domain = employeeloadMapping.toDomain(employeeloaddto);
        employeeloadService.save(domain);
        EmpLoyeeloadDTO dto = employeeloadMapping.toDto(domain);
        Map<String, Integer> opprivs = employeeloadRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"员工负载表" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/employeeloads/fetchdefault")
	public ResponseEntity<List<EmpLoyeeloadDTO>> fetchdefault(@RequestBody EmpLoyeeloadSearchContext context) {
        Page<EmpLoyeeload> domains = employeeloadService.searchDefault(context) ;
        List<EmpLoyeeloadDTO> list = employeeloadMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_EMPLOYEELOAD', 'NONE')")
	@ApiOperation(value = "获取获取员工负载表", tags = {"员工负载表" } ,notes = "获取获取员工负载表")
    @RequestMapping(method= RequestMethod.POST , value="/employeeloads/fetchgetwoerkload")
	public ResponseEntity<List<EmpLoyeeloadDTO>> fetchgetwoerkload(@RequestBody EmpLoyeeloadSearchContext context) {
        Page<EmpLoyeeload> domains = employeeloadService.searchGETWOERKLOAD(context) ;
        List<EmpLoyeeloadDTO> list = employeeloadMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成员工负载表报表", tags = {"员工负载表"}, notes = "生成员工负载表报表")
    @RequestMapping(method = RequestMethod.GET, value = "/employeeloads/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, EmpLoyeeloadSearchContext context, HttpServletResponse response) {
        try {
            employeeloadRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", employeeloadRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, employeeloadRuntime);
        }
    }

    @ApiOperation(value = "打印员工负载表", tags = {"员工负载表"}, notes = "打印员工负载表")
    @RequestMapping(method = RequestMethod.GET, value = "/employeeloads/{employeeload_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("employeeload_ids") Set<Long> employeeload_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = employeeloadRuntime.getDEPrintRuntime(print_id);
        try {
            List<EmpLoyeeload> domains = new ArrayList<>();
            for (Long employeeload_id : employeeload_ids) {
                domains.add(employeeloadService.get( employeeload_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new EmpLoyeeload[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", employeeloadRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", employeeload_ids, e.getMessage()), Errors.INTERNALERROR, employeeloadRuntime);
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

}

