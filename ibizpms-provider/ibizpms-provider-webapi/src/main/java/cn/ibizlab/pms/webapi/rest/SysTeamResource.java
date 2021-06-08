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
import cn.ibizlab.pms.core.ou.domain.SysTeam;
import cn.ibizlab.pms.core.ou.service.ISysTeamService;
import cn.ibizlab.pms.core.ou.filter.SysTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"组" })
@RestController("WebApi-systeam")
@RequestMapping("")
public class SysTeamResource {

    @Autowired
    public ISysTeamService systeamService;


    @Autowired
    @Lazy
    public SysTeamMapping systeamMapping;

    @ApiOperation(value = "新建组", tags = {"组" },  notes = "新建组")
	@RequestMapping(method = RequestMethod.POST, value = "/systeams")
    @Transactional
    public ResponseEntity<SysTeamDTO> create(@Validated @RequestBody SysTeamDTO systeamdto) {
        SysTeam domain = systeamMapping.toDomain(systeamdto);
		systeamService.create(domain);
        SysTeamDTO dto = systeamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取组", tags = {"组" },  notes = "获取组")
	@RequestMapping(method = RequestMethod.GET, value = "/systeams/{systeam_id}")
    public ResponseEntity<SysTeamDTO> get(@PathVariable("systeam_id") String systeam_id) {
        SysTeam domain = systeamService.get(systeam_id);
        SysTeamDTO dto = systeamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除组", tags = {"组" },  notes = "删除组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/systeams/{systeam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("systeam_id") String systeam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(systeamService.remove(systeam_id));
    }

    @ApiOperation(value = "批量删除组", tags = {"组" },  notes = "批量删除组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/systeams/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        systeamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新组", tags = {"组" },  notes = "更新组")
	@RequestMapping(method = RequestMethod.PUT, value = "/systeams/{systeam_id}")
    @Transactional
    public ResponseEntity<SysTeamDTO> update(@PathVariable("systeam_id") String systeam_id, @RequestBody SysTeamDTO systeamdto) {
		SysTeam domain  = systeamMapping.toDomain(systeamdto);
        domain.setTeamid(systeam_id);
		systeamService.update(domain );
		SysTeamDTO dto = systeamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "检查组", tags = {"组" },  notes = "检查组")
	@RequestMapping(method = RequestMethod.POST, value = "/systeams/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysTeamDTO systeamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(systeamService.checkKey(systeamMapping.toDomain(systeamdto)));
    }

    @ApiOperation(value = "获取组草稿", tags = {"组" },  notes = "获取组草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/systeams/getdraft")
    public ResponseEntity<SysTeamDTO> getDraft(SysTeamDTO dto) {
        SysTeam domain = systeamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(systeamMapping.toDto(systeamService.getDraft(domain)));
    }

    @ApiOperation(value = "保存组", tags = {"组" },  notes = "保存组")
	@RequestMapping(method = RequestMethod.POST, value = "/systeams/save")
    public ResponseEntity<SysTeamDTO> save(@RequestBody SysTeamDTO systeamdto) {
        SysTeam domain = systeamMapping.toDomain(systeamdto);
        systeamService.save(domain);
        SysTeamDTO dto = systeamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"组" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/systeams/fetchdefault")
	public ResponseEntity<List<SysTeamDTO>> fetchdefault(@RequestBody SysTeamSearchContext context) {
        Page<SysTeam> domains = systeamService.searchDefault(context) ;
        List<SysTeamDTO> list = systeamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/systeams/{systeam_id}/{action}")
    public ResponseEntity<SysTeamDTO> dynamicCall(@PathVariable("systeam_id") String systeam_id , @PathVariable("action") String action , @RequestBody SysTeamDTO systeamdto) {
        SysTeam domain = systeamService.dynamicCall(systeam_id, action, systeamMapping.toDomain(systeamdto));
        systeamdto = systeamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(systeamdto);
    }
}

