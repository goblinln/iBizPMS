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

@Slf4j
@Api(tags = {"部门" })
@RestController("WebApi-dept")
@RequestMapping("")
public class DeptResource {

    @Autowired
    public IDeptService deptService;

    @Autowired
    @Lazy
    public DeptMapping deptMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Create-all')")
    @ApiOperation(value = "新建部门", tags = {"部门" },  notes = "新建部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts")
    public ResponseEntity<DeptDTO> create(@Validated @RequestBody DeptDTO deptdto) {
        Dept domain = deptMapping.toDomain(deptdto);
		deptService.create(domain);
        DeptDTO dto = deptMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Create-all')")
    @ApiOperation(value = "批量新建部门", tags = {"部门" },  notes = "批量新建部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<DeptDTO> deptdtos) {
        deptService.createBatch(deptMapping.toDomain(deptdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Get-all')")
    @ApiOperation(value = "获取部门", tags = {"部门" },  notes = "获取部门")
	@RequestMapping(method = RequestMethod.GET, value = "/depts/{dept_id}")
    public ResponseEntity<DeptDTO> get(@PathVariable("dept_id") Long dept_id) {
        Dept domain = deptService.get(dept_id);
        DeptDTO dto = deptMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Remove-all')")
    @ApiOperation(value = "删除部门", tags = {"部门" },  notes = "删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/depts/{dept_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("dept_id") Long dept_id) {
         return ResponseEntity.status(HttpStatus.OK).body(deptService.remove(dept_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Remove-all')")
    @ApiOperation(value = "批量删除部门", tags = {"部门" },  notes = "批量删除部门")
	@RequestMapping(method = RequestMethod.DELETE, value = "/depts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        deptService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Update-all')")
    @ApiOperation(value = "更新部门", tags = {"部门" },  notes = "更新部门")
	@RequestMapping(method = RequestMethod.PUT, value = "/depts/{dept_id}")
    public ResponseEntity<DeptDTO> update(@PathVariable("dept_id") Long dept_id, @RequestBody DeptDTO deptdto) {
		Dept domain  = deptMapping.toDomain(deptdto);
        domain .setId(dept_id);
		deptService.update(domain );
		DeptDTO dto = deptMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Update-all')")
    @ApiOperation(value = "批量更新部门", tags = {"部门" },  notes = "批量更新部门")
	@RequestMapping(method = RequestMethod.PUT, value = "/depts/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DeptDTO> deptdtos) {
        deptService.updateBatch(deptMapping.toDomain(deptdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查部门", tags = {"部门" },  notes = "检查部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DeptDTO deptdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(deptService.checkKey(deptMapping.toDomain(deptdto)));
    }

    @ApiOperation(value = "获取部门草稿", tags = {"部门" },  notes = "获取部门草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/depts/getdraft")
    public ResponseEntity<DeptDTO> getDraft(DeptDTO dto) {
        Dept domain = deptMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(deptMapping.toDto(deptService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Save-all')")
    @ApiOperation(value = "保存部门", tags = {"部门" },  notes = "保存部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/save")
    public ResponseEntity<DeptDTO> save(@RequestBody DeptDTO deptdto) {
        Dept domain = deptMapping.toDomain(deptdto);
        deptService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(deptMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-Save-all')")
    @ApiOperation(value = "批量保存部门", tags = {"部门" },  notes = "批量保存部门")
	@RequestMapping(method = RequestMethod.POST, value = "/depts/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DeptDTO> deptdtos) {
        deptService.saveBatch(deptMapping.toDomain(deptdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"部门" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/depts/fetchdefault")
	public ResponseEntity<List<DeptDTO>> fetchDefault(DeptSearchContext context) {
        Page<Dept> domains = deptService.searchDefault(context) ;
        List<DeptDTO> list = deptMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"部门" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/depts/searchdefault")
	public ResponseEntity<Page<DeptDTO>> searchDefault(@RequestBody DeptSearchContext context) {
        Page<Dept> domains = deptService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(deptMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-searchRoot-all')")
	@ApiOperation(value = "获取根部门", tags = {"部门" } ,notes = "获取根部门")
    @RequestMapping(method= RequestMethod.GET , value="/depts/fetchroot")
	public ResponseEntity<List<DeptDTO>> fetchRoot(DeptSearchContext context) {
        Page<Dept> domains = deptService.searchRoot(context) ;
        List<DeptDTO> list = deptMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Dept-searchRoot-all')")
	@ApiOperation(value = "查询根部门", tags = {"部门" } ,notes = "查询根部门")
    @RequestMapping(method= RequestMethod.POST , value="/depts/searchroot")
	public ResponseEntity<Page<DeptDTO>> searchRoot(@RequestBody DeptSearchContext context) {
        Page<Dept> domains = deptService.searchRoot(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(deptMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

