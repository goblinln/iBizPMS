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
import cn.ibizlab.pms.core.ou.domain.SysDepartment;
import cn.ibizlab.pms.core.ou.service.ISysDepartmentService;
import cn.ibizlab.pms.core.ou.filter.SysDepartmentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"部门"})
@RestController("WebApi-sysdepartment")
@RequestMapping("")
public class SysDepartmentResource {

    @Autowired
    public ISysDepartmentService sysdepartmentService;


    @Autowired
    @Lazy
    public SysDepartmentMapping sysdepartmentMapping;

    @ApiOperation(value = "新建部门", tags = {"部门" },  notes = "新建部门")
	@RequestMapping(method = RequestMethod.POST, value = "/sysdepartments")
    @Transactional
    public ResponseEntity<SysDepartmentDTO> create(@Validated @RequestBody SysDepartmentDTO sysdepartmentdto) {
        SysDepartment domain = sysdepartmentMapping.toDomain(sysdepartmentdto);
		sysdepartmentService.create(domain);
        SysDepartmentDTO dto = sysdepartmentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取部门", tags = {"部门" },  notes = "获取部门")
	@RequestMapping(method = RequestMethod.GET, value = "/sysdepartments/{sysdepartment_id}")
    public ResponseEntity<SysDepartmentDTO> get(@PathVariable("sysdepartment_id") String sysdepartment_id) {
        SysDepartment domain = sysdepartmentService.get(sysdepartment_id);
        SysDepartmentDTO dto = sysdepartmentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除部门", tags = {"部门" },  notes = "删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysdepartments/{sysdepartment_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysdepartment_id") String sysdepartment_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysdepartmentService.remove(sysdepartment_id));
    }

    @ApiOperation(value = "批量删除部门", tags = {"部门" },  notes = "批量删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysdepartments/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        sysdepartmentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "sysdepartment" , versionfield = "updatedate")
    @ApiOperation(value = "更新部门", tags = {"部门" },  notes = "更新部门")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysdepartments/{sysdepartment_id}")
    @Transactional
    public ResponseEntity<SysDepartmentDTO> update(@PathVariable("sysdepartment_id") String sysdepartment_id, @RequestBody SysDepartmentDTO sysdepartmentdto) {
		SysDepartment domain  = sysdepartmentMapping.toDomain(sysdepartmentdto);
        domain.setDeptid(sysdepartment_id);
		sysdepartmentService.update(domain );
		SysDepartmentDTO dto = sysdepartmentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "检查部门", tags = {"部门" },  notes = "检查部门")
	@RequestMapping(method = RequestMethod.POST, value = "/sysdepartments/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysDepartmentDTO sysdepartmentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysdepartmentService.checkKey(sysdepartmentMapping.toDomain(sysdepartmentdto)));
    }

    @ApiOperation(value = "获取部门草稿", tags = {"部门" },  notes = "获取部门草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysdepartments/getdraft")
    public ResponseEntity<SysDepartmentDTO> getDraft(SysDepartmentDTO dto) {
        SysDepartment domain = sysdepartmentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysdepartmentMapping.toDto(sysdepartmentService.getDraft(domain)));
    }

    @ApiOperation(value = "保存部门", tags = {"部门" },  notes = "保存部门")
	@RequestMapping(method = RequestMethod.POST, value = "/sysdepartments/save")
    public ResponseEntity<SysDepartmentDTO> save(@RequestBody SysDepartmentDTO sysdepartmentdto) {
        SysDepartment domain = sysdepartmentMapping.toDomain(sysdepartmentdto);
        sysdepartmentService.save(domain);
        SysDepartmentDTO dto = sysdepartmentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"部门" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/sysdepartments/fetchdefault")
	public ResponseEntity<List<SysDepartmentDTO>> fetchdefault(SysDepartmentSearchContext context) {
        Page<SysDepartment> domains = sysdepartmentService.searchDefault(context) ;
        List<SysDepartmentDTO> list = sysdepartmentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成部门报表", tags = {"部门"}, notes = "生成部门报表")
    @RequestMapping(method = RequestMethod.GET, value = "/sysdepartments/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysDepartmentSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印部门", tags = {"部门"}, notes = "打印部门")
    @RequestMapping(method = RequestMethod.GET, value = "/sysdepartments/{sysdepartment_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("sysdepartment_ids") Set<String> sysdepartment_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/sysdepartments/{sysdepartment_id}/{action}")
    public ResponseEntity<SysDepartmentDTO> dynamicCall(@PathVariable("sysdepartment_id") String sysdepartment_id , @PathVariable("action") String action , @RequestBody SysDepartmentDTO sysdepartmentdto) {
        SysDepartment domain = sysdepartmentService.dynamicCall(sysdepartment_id, action, sysdepartmentMapping.toDomain(sysdepartmentdto));
        sysdepartmentdto = sysdepartmentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysdepartmentdto);
    }
}

