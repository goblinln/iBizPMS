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
import cn.ibizlab.pms.core.ou.domain.SysTeamMember;
import cn.ibizlab.pms.core.ou.service.ISysTeamMemberService;
import cn.ibizlab.pms.core.ou.filter.SysTeamMemberSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"组成员" })
@RestController("WebApi-systeammember")
@RequestMapping("")
public class SysTeamMemberResource {

    @Autowired
    public ISysTeamMemberService systeammemberService;

    @Autowired
    @Lazy
    public SysTeamMemberMapping systeammemberMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Create-all')")
    @ApiOperation(value = "新建组成员", tags = {"组成员" },  notes = "新建组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers")
    public ResponseEntity<SysTeamMemberDTO> create(@Validated @RequestBody SysTeamMemberDTO systeammemberdto) {
        SysTeamMember domain = systeammemberMapping.toDomain(systeammemberdto);
		systeammemberService.create(domain);
        SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Create-all')")
    @ApiOperation(value = "批量新建组成员", tags = {"组成员" },  notes = "批量新建组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<SysTeamMemberDTO> systeammemberdtos) {
        systeammemberService.createBatch(systeammemberMapping.toDomain(systeammemberdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Get-all')")
    @ApiOperation(value = "获取组成员", tags = {"组成员" },  notes = "获取组成员")
	@RequestMapping(method = RequestMethod.GET, value = "/systeammembers/{systeammember_id}")
    public ResponseEntity<SysTeamMemberDTO> get(@PathVariable("systeammember_id") String systeammember_id) {
        SysTeamMember domain = systeammemberService.get(systeammember_id);
        SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Remove-all')")
    @ApiOperation(value = "删除组成员", tags = {"组成员" },  notes = "删除组成员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/systeammembers/{systeammember_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("systeammember_id") String systeammember_id) {
         return ResponseEntity.status(HttpStatus.OK).body(systeammemberService.remove(systeammember_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Remove-all')")
    @ApiOperation(value = "批量删除组成员", tags = {"组成员" },  notes = "批量删除组成员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/systeammembers/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        systeammemberService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Update-all')")
    @ApiOperation(value = "更新组成员", tags = {"组成员" },  notes = "更新组成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/systeammembers/{systeammember_id}")
    public ResponseEntity<SysTeamMemberDTO> update(@PathVariable("systeammember_id") String systeammember_id, @RequestBody SysTeamMemberDTO systeammemberdto) {
		SysTeamMember domain  = systeammemberMapping.toDomain(systeammemberdto);
        domain .setTeammemberid(systeammember_id);
		systeammemberService.update(domain );
		SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Update-all')")
    @ApiOperation(value = "批量更新组成员", tags = {"组成员" },  notes = "批量更新组成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/systeammembers/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SysTeamMemberDTO> systeammemberdtos) {
        systeammemberService.updateBatch(systeammemberMapping.toDomain(systeammemberdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查组成员", tags = {"组成员" },  notes = "检查组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysTeamMemberDTO systeammemberdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(systeammemberService.checkKey(systeammemberMapping.toDomain(systeammemberdto)));
    }

    @ApiOperation(value = "获取组成员草稿", tags = {"组成员" },  notes = "获取组成员草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/systeammembers/getdraft")
    public ResponseEntity<SysTeamMemberDTO> getDraft(SysTeamMemberDTO dto) {
        SysTeamMember domain = systeammemberMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(systeammemberMapping.toDto(systeammemberService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Save-all')")
    @ApiOperation(value = "保存组成员", tags = {"组成员" },  notes = "保存组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/save")
    public ResponseEntity<SysTeamMemberDTO> save(@RequestBody SysTeamMemberDTO systeammemberdto) {
        SysTeamMember domain = systeammemberMapping.toDomain(systeammemberdto);
        systeammemberService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(systeammemberMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-Save-all')")
    @ApiOperation(value = "批量保存组成员", tags = {"组成员" },  notes = "批量保存组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SysTeamMemberDTO> systeammemberdtos) {
        systeammemberService.saveBatch(systeammemberMapping.toDomain(systeammemberdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"组成员" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/systeammembers/fetchdefault")
	public ResponseEntity<List<SysTeamMemberDTO>> fetchDefault(SysTeamMemberSearchContext context) {
        Page<SysTeamMember> domains = systeammemberService.searchDefault(context) ;
        List<SysTeamMemberDTO> list = systeammemberMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-SysTeamMember-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"组成员" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/systeammembers/searchdefault")
	public ResponseEntity<Page<SysTeamMemberDTO>> searchDefault(@RequestBody SysTeamMemberSearchContext context) {
        Page<SysTeamMember> domains = systeammemberService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(systeammemberMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

