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
import cn.ibizlab.pms.core.uaa.domain.SysUser;
import cn.ibizlab.pms.core.uaa.service.ISysUserService;
import cn.ibizlab.pms.core.uaa.filter.SysUserSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统用户"})
@RestController("WebApi-sysuser")
@RequestMapping("")
public class SysUserResource {

    @Autowired
    public ISysUserService sysuserService;


    @Autowired
    @Lazy
    public SysUserMapping sysuserMapping;

    @ApiOperation(value = "新建系统用户", tags = {"系统用户" },  notes = "新建系统用户")
	@RequestMapping(method = RequestMethod.POST, value = "/sysusers")
    @Transactional
    public ResponseEntity<SysUserDTO> create(@Validated @RequestBody SysUserDTO sysuserdto) {
        SysUser domain = sysuserMapping.toDomain(sysuserdto);
		sysuserService.create(domain);
        SysUserDTO dto = sysuserMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取系统用户", tags = {"系统用户" },  notes = "获取系统用户")
	@RequestMapping(method = RequestMethod.GET, value = "/sysusers/{sysuser_id}")
    public ResponseEntity<SysUserDTO> get(@PathVariable("sysuser_id") String sysuser_id) {
        SysUser domain = sysuserService.get(sysuser_id);
        SysUserDTO dto = sysuserMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除系统用户", tags = {"系统用户" },  notes = "删除系统用户")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysusers/{sysuser_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysuser_id") String sysuser_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysuserService.remove(sysuser_id));
    }

    @ApiOperation(value = "批量删除系统用户", tags = {"系统用户" },  notes = "批量删除系统用户")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysusers/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        sysuserService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新系统用户", tags = {"系统用户" },  notes = "更新系统用户")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysusers/{sysuser_id}")
    @Transactional
    public ResponseEntity<SysUserDTO> update(@PathVariable("sysuser_id") String sysuser_id, @RequestBody SysUserDTO sysuserdto) {
		SysUser domain  = sysuserMapping.toDomain(sysuserdto);
        domain.setUserid(sysuser_id);
		sysuserService.update(domain );
		SysUserDTO dto = sysuserMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "修改密码", tags = {"系统用户" },  notes = "修改密码")
	@RequestMapping(method = RequestMethod.POST, value = "/sysusers/{sysuser_id}/changepwd")
    public ResponseEntity<SysUserDTO> changePwd(@PathVariable("sysuser_id") String sysuser_id, @RequestBody SysUserDTO sysuserdto) {
        SysUser domain = sysuserMapping.toDomain(sysuserdto);
        domain.setUserid(sysuser_id);
        domain = sysuserService.changePwd(domain);
        sysuserdto = sysuserMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysuserdto);
    }


    @ApiOperation(value = "检查系统用户", tags = {"系统用户" },  notes = "检查系统用户")
	@RequestMapping(method = RequestMethod.POST, value = "/sysusers/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysUserDTO sysuserdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysuserService.checkKey(sysuserMapping.toDomain(sysuserdto)));
    }

    @ApiOperation(value = "获取系统用户草稿", tags = {"系统用户" },  notes = "获取系统用户草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysusers/getdraft")
    public ResponseEntity<SysUserDTO> getDraft(SysUserDTO dto) {
        SysUser domain = sysuserMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysuserMapping.toDto(sysuserService.getDraft(domain)));
    }

    @ApiOperation(value = "保存系统用户", tags = {"系统用户" },  notes = "保存系统用户")
	@RequestMapping(method = RequestMethod.POST, value = "/sysusers/save")
    public ResponseEntity<SysUserDTO> save(@RequestBody SysUserDTO sysuserdto) {
        SysUser domain = sysuserMapping.toDomain(sysuserdto);
        sysuserService.save(domain);
        SysUserDTO dto = sysuserMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"系统用户" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysusers/fetchdefault")
	public ResponseEntity<List<SysUserDTO>> fetchdefault(@RequestBody SysUserSearchContext context) {
        Page<SysUser> domains = sysuserService.searchDefault(context) ;
        List<SysUserDTO> list = sysuserMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取我的工作", tags = {"系统用户" } ,notes = "获取我的工作")
    @RequestMapping(method= RequestMethod.POST , value="/sysusers/fetchmywork")
	public ResponseEntity<List<SysUserDTO>> fetchmywork(@RequestBody SysUserSearchContext context) {
        Page<SysUser> domains = sysuserService.searchMyWork(context) ;
        List<SysUserDTO> list = sysuserMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取个人信息-个人贡献", tags = {"系统用户" } ,notes = "获取个人信息-个人贡献")
    @RequestMapping(method= RequestMethod.POST , value="/sysusers/fetchpersoninfo")
	public ResponseEntity<List<SysUserDTO>> fetchpersoninfo(@RequestBody SysUserSearchContext context) {
        Page<SysUser> domains = sysuserService.searchPersonInfo(context) ;
        List<SysUserDTO> list = sysuserMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成系统用户报表", tags = {"系统用户"}, notes = "生成系统用户报表")
    @RequestMapping(method = RequestMethod.GET, value = "/sysusers/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysUserSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印系统用户", tags = {"系统用户"}, notes = "打印系统用户")
    @RequestMapping(method = RequestMethod.GET, value = "/sysusers/{sysuser_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("sysuser_ids") Set<String> sysuser_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/sysusers/{sysuser_id}/{action}")
    public ResponseEntity<SysUserDTO> dynamicCall(@PathVariable("sysuser_id") String sysuser_id , @PathVariable("action") String action , @RequestBody SysUserDTO sysuserdto) {
        SysUser domain = sysuserService.dynamicCall(sysuser_id, action, sysuserMapping.toDomain(sysuserdto));
        sysuserdto = sysuserMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysuserdto);
    }
}

