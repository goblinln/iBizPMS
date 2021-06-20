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
import cn.ibizlab.pms.core.zentao.domain.Dept;
import cn.ibizlab.pms.core.zentao.service.IDeptService;
import cn.ibizlab.pms.core.zentao.filter.DeptSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DeptRuntime;

@Slf4j
@Api(tags = {"部门"})
@RestController("WebApi-dept")
@RequestMapping("")
public class DeptResource {

    @Autowired
    public IDeptService deptService;

    @Autowired
    public DeptRuntime deptRuntime;

    @Autowired
    @Lazy
    public DeptMapping deptMapping;

    @PreAuthorize("quickTest('ZT_DEPT', 'CREATE')")
    @ApiOperation(value = "新建部门", tags = {"部门" },  notes = "新建部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts")
    @Transactional
    public ResponseEntity<DeptDTO> create(@Validated @RequestBody DeptDTO deptdto) {
        Dept domain = deptMapping.toDomain(deptdto);
		deptService.create(domain);
        if(!deptRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DeptDTO dto = deptMapping.toDto(domain);
        Map<String, Integer> opprivs = deptRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DEPT', #dept_id, 'READ')")
    @ApiOperation(value = "获取部门", tags = {"部门" },  notes = "获取部门")
	@RequestMapping(method = RequestMethod.GET, value = "/depts/{dept_id}")
    public ResponseEntity<DeptDTO> get(@PathVariable("dept_id") Long dept_id) {
        Dept domain = deptService.get(dept_id);
        DeptDTO dto = deptMapping.toDto(domain);
        Map<String, Integer> opprivs = deptRuntime.getOPPrivs(dept_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DEPT', #dept_id, 'DELETE')")
    @ApiOperation(value = "删除部门", tags = {"部门" },  notes = "删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/depts/{dept_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("dept_id") Long dept_id) {
         return ResponseEntity.status(HttpStatus.OK).body(deptService.remove(dept_id));
    }

    @PreAuthorize("quickTest('ZT_DEPT', 'DELETE')")
    @ApiOperation(value = "批量删除部门", tags = {"部门" },  notes = "批量删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/depts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        deptService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DEPT', #dept_id, 'UPDATE')")
    @ApiOperation(value = "更新部门", tags = {"部门" },  notes = "更新部门")
	@RequestMapping(method = RequestMethod.PUT, value = "/depts/{dept_id}")
    @Transactional
    public ResponseEntity<DeptDTO> update(@PathVariable("dept_id") Long dept_id, @RequestBody DeptDTO deptdto) {
		Dept domain  = deptMapping.toDomain(deptdto);
        domain.setId(dept_id);
		deptService.update(domain );
        if(!deptRuntime.test(dept_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DeptDTO dto = deptMapping.toDto(domain);
        Map<String, Integer> opprivs = deptRuntime.getOPPrivs(dept_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DEPT', 'CREATE')")
    @ApiOperation(value = "检查部门", tags = {"部门" },  notes = "检查部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DeptDTO deptdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(deptService.checkKey(deptMapping.toDomain(deptdto)));
    }

    @PreAuthorize("quickTest('ZT_DEPT', 'CREATE')")
    @ApiOperation(value = "获取部门草稿", tags = {"部门" },  notes = "获取部门草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/depts/getdraft")
    public ResponseEntity<DeptDTO> getDraft(DeptDTO dto) {
        Dept domain = deptMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(deptMapping.toDto(deptService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DEPT', 'DENY')")
    @ApiOperation(value = "保存部门", tags = {"部门" },  notes = "保存部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/save")
    public ResponseEntity<DeptDTO> save(@RequestBody DeptDTO deptdto) {
        Dept domain = deptMapping.toDomain(deptdto);
        deptService.save(domain);
        DeptDTO dto = deptMapping.toDto(domain);
        Map<String, Integer> opprivs = deptRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DEPT', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"部门" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/depts/fetchdefault")
	public ResponseEntity<List<DeptDTO>> fetchdefault(@RequestBody DeptSearchContext context) {
        deptRuntime.addAuthorityConditions(context,"READ");
        Page<Dept> domains = deptService.searchDefault(context) ;
        List<DeptDTO> list = deptMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DEPT', 'READ')")
	@ApiOperation(value = "获取根部门", tags = {"部门" } ,notes = "获取根部门")
    @RequestMapping(method= RequestMethod.POST , value="/depts/fetchroot")
	public ResponseEntity<List<DeptDTO>> fetchroot(@RequestBody DeptSearchContext context) {
        deptRuntime.addAuthorityConditions(context,"READ");
        Page<Dept> domains = deptService.searchRoot(context) ;
        List<DeptDTO> list = deptMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成部门报表", tags = {"部门"}, notes = "生成部门报表")
    @RequestMapping(method = RequestMethod.GET, value = "/depts/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DeptSearchContext context, HttpServletResponse response) {
        try {
            deptRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", deptRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, deptRuntime);
        }
    }

    @ApiOperation(value = "打印部门", tags = {"部门"}, notes = "打印部门")
    @RequestMapping(method = RequestMethod.GET, value = "/depts/{dept_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("dept_ids") Set<Long> dept_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = deptRuntime.getDEPrintRuntime(print_id);
        try {
            List<Dept> domains = new ArrayList<>();
            for (Long dept_id : dept_ids) {
                domains.add(deptService.get( dept_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Dept[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", deptRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", dept_ids, e.getMessage()), Errors.INTERNALERROR, deptRuntime);
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

