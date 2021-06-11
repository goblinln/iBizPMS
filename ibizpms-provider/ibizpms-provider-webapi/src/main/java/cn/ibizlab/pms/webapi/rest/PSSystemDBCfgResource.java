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

    @PreAuthorize("hasPermission(this.pssystemdbcfgMapping.toDomain(#pssystemdbcfgdto),'pms-PSSystemDBCfg-Create')")
    @ApiOperation(value = "新建系统数据库", tags = {"系统数据库" },  notes = "新建系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs")
    public ResponseEntity<PSSystemDBCfgDTO> create(@Validated @RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
		pssystemdbcfgService.create(domain);
        PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgMapping.toDomain(#pssystemdbcfgdtos),'pms-PSSystemDBCfg-Create')")
    @ApiOperation(value = "批量新建系统数据库", tags = {"系统数据库" },  notes = "批量新建系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<PSSystemDBCfgDTO> pssystemdbcfgdtos) {
        pssystemdbcfgService.createBatch(pssystemdbcfgMapping.toDomain(pssystemdbcfgdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.pssystemdbcfgMapping.toDomain(returnObject.body),'pms-PSSystemDBCfg-Get')")
    @ApiOperation(value = "获取系统数据库", tags = {"系统数据库" },  notes = "获取系统数据库")
	@RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    public ResponseEntity<PSSystemDBCfgDTO> get(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id) {
        PSSystemDBCfg domain = pssystemdbcfgService.get(pssystemdbcfg_id);
        PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgService.get(#pssystemdbcfg_id),'pms-PSSystemDBCfg-Remove')")
    @ApiOperation(value = "删除系统数据库", tags = {"系统数据库" },  notes = "删除系统数据库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id) {
         return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgService.remove(pssystemdbcfg_id));
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgService.getPssystemdbcfgByIds(#ids),'pms-PSSystemDBCfg-Remove')")
    @ApiOperation(value = "批量删除系统数据库", tags = {"系统数据库" },  notes = "批量删除系统数据库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        pssystemdbcfgService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "pssystemdbcfg" , versionfield = "updatedate")
    @PreAuthorize("hasPermission(this.pssystemdbcfgService.get(#pssystemdbcfg_id),'pms-PSSystemDBCfg-Update')")
    @ApiOperation(value = "更新系统数据库", tags = {"系统数据库" },  notes = "更新系统数据库")
	@RequestMapping(method = RequestMethod.PUT, value = "/pssystemdbcfgs/{pssystemdbcfg_id}")
    public ResponseEntity<PSSystemDBCfgDTO> update(@PathVariable("pssystemdbcfg_id") String pssystemdbcfg_id, @RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
		PSSystemDBCfg domain  = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
        domain .setPssystemdbcfgid(pssystemdbcfg_id);
		pssystemdbcfgService.update(domain );
		PSSystemDBCfgDTO dto = pssystemdbcfgMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgService.getPssystemdbcfgByEntities(this.pssystemdbcfgMapping.toDomain(#pssystemdbcfgdtos)),'pms-PSSystemDBCfg-Update')")
    @ApiOperation(value = "批量更新系统数据库", tags = {"系统数据库" },  notes = "批量更新系统数据库")
	@RequestMapping(method = RequestMethod.PUT, value = "/pssystemdbcfgs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<PSSystemDBCfgDTO> pssystemdbcfgdtos) {
        pssystemdbcfgService.updateBatch(pssystemdbcfgMapping.toDomain(pssystemdbcfgdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查系统数据库", tags = {"系统数据库" },  notes = "检查系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgService.checkKey(pssystemdbcfgMapping.toDomain(pssystemdbcfgdto)));
    }

    @ApiOperation(value = "获取系统数据库草稿", tags = {"系统数据库" },  notes = "获取系统数据库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/getdraft")
    public ResponseEntity<PSSystemDBCfgDTO> getDraft(PSSystemDBCfgDTO dto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgMapping.toDto(pssystemdbcfgService.getDraft(domain)));
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgMapping.toDomain(#pssystemdbcfgdto),'pms-PSSystemDBCfg-Save')")
    @ApiOperation(value = "保存系统数据库", tags = {"系统数据库" },  notes = "保存系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/save")
    public ResponseEntity<PSSystemDBCfgDTO> save(@RequestBody PSSystemDBCfgDTO pssystemdbcfgdto) {
        PSSystemDBCfg domain = pssystemdbcfgMapping.toDomain(pssystemdbcfgdto);
        pssystemdbcfgService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(pssystemdbcfgMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.pssystemdbcfgMapping.toDomain(#pssystemdbcfgdtos),'pms-PSSystemDBCfg-Save')")
    @ApiOperation(value = "批量保存系统数据库", tags = {"系统数据库" },  notes = "批量保存系统数据库")
	@RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<PSSystemDBCfgDTO> pssystemdbcfgdtos) {
        pssystemdbcfgService.saveBatch(pssystemdbcfgMapping.toDomain(pssystemdbcfgdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-PSSystemDBCfg-searchBuild-all') and hasPermission(#context,'pms-PSSystemDBCfg-Get')")
	@ApiOperation(value = "获取版本", tags = {"系统数据库" } ,notes = "获取版本")
    @RequestMapping(method= RequestMethod.GET , value="/pssystemdbcfgs/fetchbuild")
	public ResponseEntity<List<PSSystemDBCfgDTO>> fetchBuild(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchBuild(context) ;
        List<PSSystemDBCfgDTO> list = pssystemdbcfgMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-PSSystemDBCfg-searchBuild-all') and hasPermission(#context,'pms-PSSystemDBCfg-Get')")
	@ApiOperation(value = "查询版本", tags = {"系统数据库" } ,notes = "查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/pssystemdbcfgs/searchbuild")
	public ResponseEntity<Page<PSSystemDBCfgDTO>> searchBuild(@RequestBody PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(pssystemdbcfgMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-PSSystemDBCfg-searchDefault-all') and hasPermission(#context,'pms-PSSystemDBCfg-Get')")
	@ApiOperation(value = "获取数据集", tags = {"系统数据库" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/pssystemdbcfgs/fetchdefault")
	public ResponseEntity<List<PSSystemDBCfgDTO>> fetchDefault(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchDefault(context) ;
        List<PSSystemDBCfgDTO> list = pssystemdbcfgMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-PSSystemDBCfg-searchDefault-all') and hasPermission(#context,'pms-PSSystemDBCfg-Get')")
	@ApiOperation(value = "查询数据集", tags = {"系统数据库" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/pssystemdbcfgs/searchdefault")
	public ResponseEntity<Page<PSSystemDBCfgDTO>> searchDefault(@RequestBody PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> domains = pssystemdbcfgService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(pssystemdbcfgMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

