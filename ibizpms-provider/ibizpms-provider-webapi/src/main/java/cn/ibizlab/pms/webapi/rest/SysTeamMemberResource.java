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

    @ApiOperation(value = "新建组成员", tags = {"组成员" },  notes = "新建组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers")
    @Transactional
    public ResponseEntity<SysTeamMemberDTO> create(@Validated @RequestBody SysTeamMemberDTO systeammemberdto) {
        SysTeamMember domain = systeammemberMapping.toDomain(systeammemberdto);
		systeammemberService.create(domain);
        SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "更新组成员", tags = {"组成员" },  notes = "更新组成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/systeammembers/{systeammember_id}")
    @Transactional
    public ResponseEntity<SysTeamMemberDTO> update(@PathVariable("systeammember_id") String systeammember_id, @RequestBody SysTeamMemberDTO systeammemberdto) {
		SysTeamMember domain  = systeammemberMapping.toDomain(systeammemberdto);
        domain.setTeammemberid(systeammember_id);
		systeammemberService.update(domain );
		SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除组成员", tags = {"组成员" },  notes = "删除组成员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/systeammembers/{systeammember_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("systeammember_id") String systeammember_id) {
         return ResponseEntity.status(HttpStatus.OK).body(systeammemberService.remove(systeammember_id));
    }


    @ApiOperation(value = "获取组成员", tags = {"组成员" },  notes = "获取组成员")
	@RequestMapping(method = RequestMethod.GET, value = "/systeammembers/{systeammember_id}")
    public ResponseEntity<SysTeamMemberDTO> get(@PathVariable("systeammember_id") String systeammember_id) {
        SysTeamMember domain = systeammemberService.get(systeammember_id);
        SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取组成员草稿", tags = {"组成员" },  notes = "获取组成员草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/systeammembers/getdraft")
    public ResponseEntity<SysTeamMemberDTO> getDraft(SysTeamMemberDTO dto) {
        SysTeamMember domain = systeammemberMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(systeammemberMapping.toDto(systeammemberService.getDraft(domain)));
    }

    @ApiOperation(value = "检查组成员", tags = {"组成员" },  notes = "检查组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysTeamMemberDTO systeammemberdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(systeammemberService.checkKey(systeammemberMapping.toDomain(systeammemberdto)));
    }

    @ApiOperation(value = "保存组成员", tags = {"组成员" },  notes = "保存组成员")
	@RequestMapping(method = RequestMethod.POST, value = "/systeammembers/save")
    public ResponseEntity<SysTeamMemberDTO> save(@RequestBody SysTeamMemberDTO systeammemberdto) {
        SysTeamMember domain = systeammemberMapping.toDomain(systeammemberdto);
        systeammemberService.save(domain);
        SysTeamMemberDTO dto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"组成员" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/systeammembers/fetchdefault")
	public ResponseEntity<List<SysTeamMemberDTO>> fetchdefault(@RequestBody SysTeamMemberSearchContext context) {
        Page<SysTeamMember> domains = systeammemberService.searchDefault(context) ;
        List<SysTeamMemberDTO> list = systeammemberMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/systeammembers/{systeammember_id}/{action}")
    public ResponseEntity<SysTeamMemberDTO> dynamicCall(@PathVariable("systeammember_id") String systeammember_id , @PathVariable("action") String action , @RequestBody SysTeamMemberDTO systeammemberdto) {
        SysTeamMember domain = systeammemberService.dynamicCall(systeammember_id, action, systeammemberMapping.toDomain(systeammemberdto));
        systeammemberdto = systeammemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(systeammemberdto);
    }
}

