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
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.service.ISysUserRoleService;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"用户角色关系" })
@RestController("WebApi-sysuserrole")
@RequestMapping("")
public class SysUserRoleResource {

    @Autowired
    public ISysUserRoleService sysuserroleService;


    @Autowired
    @Lazy
    public SysUserRoleMapping sysuserroleMapping;

    @ApiOperation(value = "新建用户角色关系", tags = {"用户角色关系" },  notes = "新建用户角色关系")
	@RequestMapping(method = RequestMethod.POST, value = "/sysuserroles")
    @Transactional
    public ResponseEntity<SysUserRoleDTO> create(@Validated @RequestBody SysUserRoleDTO sysuserroledto) {
        SysUserRole domain = sysuserroleMapping.toDomain(sysuserroledto);
		sysuserroleService.create(domain);
        SysUserRoleDTO dto = sysuserroleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "sysuserrole" , versionfield = "updatedate")
    @ApiOperation(value = "更新用户角色关系", tags = {"用户角色关系" },  notes = "更新用户角色关系")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysuserroles/{sysuserrole_id}")
    @Transactional
    public ResponseEntity<SysUserRoleDTO> update(@PathVariable("sysuserrole_id") String sysuserrole_id, @RequestBody SysUserRoleDTO sysuserroledto) {
		SysUserRole domain  = sysuserroleMapping.toDomain(sysuserroledto);
        domain.setUserroleid(sysuserrole_id);
		sysuserroleService.update(domain );
		SysUserRoleDTO dto = sysuserroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除用户角色关系", tags = {"用户角色关系" },  notes = "删除用户角色关系")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysuserroles/{sysuserrole_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysuserrole_id") String sysuserrole_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysuserroleService.remove(sysuserrole_id));
    }


    @ApiOperation(value = "获取用户角色关系", tags = {"用户角色关系" },  notes = "获取用户角色关系")
	@RequestMapping(method = RequestMethod.GET, value = "/sysuserroles/{sysuserrole_id}")
    public ResponseEntity<SysUserRoleDTO> get(@PathVariable("sysuserrole_id") String sysuserrole_id) {
        SysUserRole domain = sysuserroleService.get(sysuserrole_id);
        SysUserRoleDTO dto = sysuserroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用户角色关系草稿", tags = {"用户角色关系" },  notes = "获取用户角色关系草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysuserroles/getdraft")
    public ResponseEntity<SysUserRoleDTO> getDraft(SysUserRoleDTO dto) {
        SysUserRole domain = sysuserroleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysuserroleMapping.toDto(sysuserroleService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用户角色关系", tags = {"用户角色关系" },  notes = "检查用户角色关系")
	@RequestMapping(method = RequestMethod.POST, value = "/sysuserroles/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysUserRoleDTO sysuserroledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysuserroleService.checkKey(sysuserroleMapping.toDomain(sysuserroledto)));
    }

    @ApiOperation(value = "保存用户角色关系", tags = {"用户角色关系" },  notes = "保存用户角色关系")
	@RequestMapping(method = RequestMethod.POST, value = "/sysuserroles/save")
    public ResponseEntity<SysUserRoleDTO> save(@RequestBody SysUserRoleDTO sysuserroledto) {
        SysUserRole domain = sysuserroleMapping.toDomain(sysuserroledto);
        sysuserroleService.save(domain);
        SysUserRoleDTO dto = sysuserroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"用户角色关系" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysuserroles/fetchdefault")
	public ResponseEntity<List<SysUserRoleDTO>> fetchdefault(@RequestBody SysUserRoleSearchContext context) {
        Page<SysUserRole> domains = sysuserroleService.searchDefault(context) ;
        List<SysUserRoleDTO> list = sysuserroleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysuserroles/{sysuserrole_id}/{action}")
    public ResponseEntity<SysUserRoleDTO> dynamicCall(@PathVariable("sysuserrole_id") String sysuserrole_id , @PathVariable("action") String action , @RequestBody SysUserRoleDTO sysuserroledto) {
        SysUserRole domain = sysuserroleService.dynamicCall(sysuserrole_id, action, sysuserroleMapping.toDomain(sysuserroledto));
        sysuserroledto = sysuserroleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysuserroledto);
    }
}

