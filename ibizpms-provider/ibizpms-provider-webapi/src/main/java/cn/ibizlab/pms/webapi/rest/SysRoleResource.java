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
import cn.ibizlab.pms.core.uaa.domain.SysRole;
import cn.ibizlab.pms.core.uaa.service.ISysRoleService;
import cn.ibizlab.pms.core.uaa.filter.SysRoleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统角色" })
@RestController("WebApi-sysrole")
@RequestMapping("")
public class SysRoleResource {

    @Autowired
    public ISysRoleService sysroleService;


    @Autowired
    @Lazy
    public SysRoleMapping sysroleMapping;

    @ApiOperation(value = "新建系统角色", tags = {"系统角色" },  notes = "新建系统角色")
	@RequestMapping(method = RequestMethod.POST, value = "/sysroles")
    @Transactional
    public ResponseEntity<SysRoleDTO> create(@Validated @RequestBody SysRoleDTO sysroledto) {
        SysRole domain = sysroleMapping.toDomain(sysroledto);
		sysroleService.create(domain);
        SysRoleDTO dto = sysroleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "sysrole" , versionfield = "updatedate")
    @ApiOperation(value = "更新系统角色", tags = {"系统角色" },  notes = "更新系统角色")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysroles/{sysrole_id}")
    @Transactional
    public ResponseEntity<SysRoleDTO> update(@PathVariable("sysrole_id") String sysrole_id, @RequestBody SysRoleDTO sysroledto) {
		SysRole domain  = sysroleMapping.toDomain(sysroledto);
        domain.setRoleid(sysrole_id);
		sysroleService.update(domain );
		SysRoleDTO dto = sysroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除系统角色", tags = {"系统角色" },  notes = "删除系统角色")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysroles/{sysrole_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysrole_id") String sysrole_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysroleService.remove(sysrole_id));
    }


    @ApiOperation(value = "获取系统角色", tags = {"系统角色" },  notes = "获取系统角色")
	@RequestMapping(method = RequestMethod.GET, value = "/sysroles/{sysrole_id}")
    public ResponseEntity<SysRoleDTO> get(@PathVariable("sysrole_id") String sysrole_id) {
        SysRole domain = sysroleService.get(sysrole_id);
        SysRoleDTO dto = sysroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取系统角色草稿", tags = {"系统角色" },  notes = "获取系统角色草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysroles/getdraft")
    public ResponseEntity<SysRoleDTO> getDraft(SysRoleDTO dto) {
        SysRole domain = sysroleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysroleMapping.toDto(sysroleService.getDraft(domain)));
    }

    @ApiOperation(value = "检查系统角色", tags = {"系统角色" },  notes = "检查系统角色")
	@RequestMapping(method = RequestMethod.POST, value = "/sysroles/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysRoleDTO sysroledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysroleService.checkKey(sysroleMapping.toDomain(sysroledto)));
    }

    @ApiOperation(value = "保存系统角色", tags = {"系统角色" },  notes = "保存系统角色")
	@RequestMapping(method = RequestMethod.POST, value = "/sysroles/save")
    public ResponseEntity<SysRoleDTO> save(@RequestBody SysRoleDTO sysroledto) {
        SysRole domain = sysroleMapping.toDomain(sysroledto);
        sysroleService.save(domain);
        SysRoleDTO dto = sysroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"系统角色" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysroles/fetchdefault")
	public ResponseEntity<List<SysRoleDTO>> fetchdefault(@RequestBody SysRoleSearchContext context) {
        Page<SysRole> domains = sysroleService.searchDefault(context) ;
        List<SysRoleDTO> list = sysroleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysroles/{sysrole_id}/{action}")
    public ResponseEntity<SysRoleDTO> dynamicCall(@PathVariable("sysrole_id") String sysrole_id , @PathVariable("action") String action , @RequestBody SysRoleDTO sysroledto) {
        SysRole domain = sysroleService.dynamicCall(sysrole_id, action, sysroleMapping.toDomain(sysroledto));
        sysroledto = sysroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysroledto);
    }
}

