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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSystemDBCfgService;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSystemDBCfgSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统数据库" })
@RestController("WebApi-pssystemdbcfg")
@RequestMapping("")
public class PSSystemDBCfgResource {

    @Autowired
    public IPSSystemDBCfgService pssystemdbcfgService;


    @Autowired
    @Lazy
    public PSSystemDBCfgMapping pssystemdbcfgMapping;

    @ApiOperation(value = "新建系统数据库", tags = {"系统数据库" },  notes = "新建系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs")
    @Transactional
    public ResponseEntity<PSSystemDBCfgDTO> create(@Validated @RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
		pssystemdbcfgService.create(domain);
        PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "pssystemdbcfg" , versionfield = "updatedate")
    @ApiOperation(value = "更新系统数据库", tags = {"系统数据库" },  notes = "更新系统数据库")
	@RequestMapping(method = RequestMethod.PUT, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    @Transactional
    public ResponseEntity<PSSystemDBCfgDTO> update(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id, @RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
		PSSystemDBCfg domain  = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
        domain.setPssystemdbcfgid(pssystemdbcfg_id);
		pssystemdbcfgService.update(domain );
		PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除系统数据库", tags = {"系统数据库" },  notes = "删除系统数据库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id) {
         return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgService.remove(pssystemdbcfg_id));
    }

    @ApiOperation(value = "批量删除系统数据库", tags = {"系统数据库" },  notes = "批量删除系统数据库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        pssystemdbcfgService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "获取系统数据库", tags = {"系统数据库" },  notes = "获取系统数据库")
	@RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    public ResponseEntity<PSSystemDBCfgDTO> get(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id) {
        PSSystemDBCfg domain = pssystemdbcfgService.get(pssystemdbcfg_id);
        PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取系统数据库草稿", tags = {"系统数据库" },  notes = "获取系统数据库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/getdraft")
    public ResponseEntity<PSSystemDBCfgDTO> getDraft(PSSystemDBCfgDTO dto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgMapping.toDto(pssystemdbcfgService.getDraft(domain)));
    }

    @ApiOperation(value = "检查系统数据库", tags = {"系统数据库" },  notes = "检查系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgService.checkKey(pssystemdbcfgMapping.toDomain(pssystemdbcfgdto)));
    }

    @ApiOperation(value = "保存系统数据库", tags = {"系统数据库" },  notes = "保存系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/save")
    public ResponseEntity<PSSystemDBCfgDTO> save(@RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
        pssystemdbcfgService.save(domain);
        PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取版本", tags = {"系统数据库" } ,notes = "获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/pssystemdbcfgs/fetchbuild")
	public ResponseEntity<List<PSSystemDBCfgDTO>> fetchbuild(@RequestBody PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchBuild(context) ;
        List<PSSystemDBCfgDTO> list = pssystemdbcfgMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取数据集", tags = {"系统数据库" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/pssystemdbcfgs/fetchdefault")
	public ResponseEntity<List<PSSystemDBCfgDTO>> fetchdefault(@RequestBody PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchDefault(context) ;
        List<PSSystemDBCfgDTO> list = pssystemdbcfgMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/{pssystemdbcfg_id}/{action}")
    public ResponseEntity<PSSystemDBCfgDTO> dynamicCall(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id , @PathVariable("action") String action , @RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        PSSystemDBCfg domain = pssystemdbcfgService.dynamicCall(pssystemdbcfg_id, action, pssystemdbcfgMapping.toDomain(pssystemdbcfgdto));
        pssystemdbcfgdto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgdto);
    }
}

