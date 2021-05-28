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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysAppService;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysAppSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统应用" })
@RestController("WebApi-pssysapp")
@RequestMapping("")
public class PSSysAppResource {

    @Autowired
    public IPSSysAppService pssysappService;


    @Autowired
    @Lazy
    public PSSysAppMapping pssysappMapping;

    @PreAuthorize("@PSSysAppRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建系统应用", tags = {"系统应用" },  notes = "新建系统应用")
	@RequestMapping(method = RequestMethod.POST, value = "/pssysapps")
    @Transactional
    public ResponseEntity<PSSysAppDTO> create(@Validated @RequestBody PSSysAppDTO pssysappdto) {
        PSSysApp domain = pssysappMapping.toDomain(pssysappdto);
		pssysappService.create(domain);
        PSSysAppDTO dto = pssysappMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "pssysapp" , versionfield = "updatedate")
    @PreAuthorize("@PSSysAppRuntime.test(#pssysapp_id, 'UPDATE')")
    @ApiOperation(value = "更新系统应用", tags = {"系统应用" },  notes = "更新系统应用")
	@RequestMapping(method = RequestMethod.PUT, value = "/pssysapps/{pssysapp_id}")
    @Transactional
    public ResponseEntity<PSSysAppDTO> update(@PathVariable("pssysapp_id") String pssysapp_id, @RequestBody PSSysAppDTO pssysappdto) {
		PSSysApp domain  = pssysappMapping.toDomain(pssysappdto);
        domain.setPssysappid(pssysapp_id);
		pssysappService.update(domain );
		PSSysAppDTO dto = pssysappMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@PSSysAppRuntime.test(#pssysapp_id, 'DELETE')")
    @ApiOperation(value = "删除系统应用", tags = {"系统应用" },  notes = "删除系统应用")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssysapps/{pssysapp_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("pssysapp_id") String pssysapp_id) {
         return ResponseEntity.status(HttpStatus.OK).body(pssysappService.remove(pssysapp_id));
    }

    @PreAuthorize("@PSSysAppRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除系统应用", tags = {"系统应用" },  notes = "批量删除系统应用")
	@RequestMapping(method = RequestMethod.DELETE, value = "/pssysapps/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        pssysappService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@PSSysAppRuntime.test(#pssysapp_id, 'READ')")
    @ApiOperation(value = "获取系统应用", tags = {"系统应用" },  notes = "获取系统应用")
	@RequestMapping(method = RequestMethod.GET, value = "/pssysapps/{pssysapp_id}")
    public ResponseEntity<PSSysAppDTO> get(@PathVariable("pssysapp_id") String pssysapp_id) {
        PSSysApp domain = pssysappService.get(pssysapp_id);
        PSSysAppDTO dto = pssysappMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@PSSysAppRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取系统应用草稿", tags = {"系统应用" },  notes = "获取系统应用草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/pssysapps/getdraft")
    public ResponseEntity<PSSysAppDTO> getDraft(PSSysAppDTO dto) {
        PSSysApp domain = pssysappMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(pssysappMapping.toDto(pssysappService.getDraft(domain)));
    }

    @PreAuthorize("@PSSysAppRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查系统应用", tags = {"系统应用" },  notes = "检查系统应用")
	@RequestMapping(method = RequestMethod.POST, value = "/pssysapps/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody PSSysAppDTO pssysappdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(pssysappService.checkKey(pssysappMapping.toDomain(pssysappdto)));
    }

    @PreAuthorize("@PSSysAppRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存系统应用", tags = {"系统应用" },  notes = "保存系统应用")
	@RequestMapping(method = RequestMethod.POST, value = "/pssysapps/save")
    public ResponseEntity<PSSysAppDTO> save(@RequestBody PSSysAppDTO pssysappdto) {
        PSSysApp domain = pssysappMapping.toDomain(pssysappdto);
        pssysappService.save(domain);
        PSSysAppDTO dto = pssysappMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@PSSysAppRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本", tags = {"系统应用" } ,notes = "获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/pssysapps/fetchbuild")
	public ResponseEntity<List<PSSysAppDTO>> fetchbuild(@RequestBody PSSysAppSearchContext context) {
        Page<PSSysApp> domains = pssysappService.searchBuild(context) ;
        List<PSSysAppDTO> list = pssysappMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@PSSysAppRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"系统应用" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/pssysapps/fetchdefault")
	public ResponseEntity<List<PSSysAppDTO>> fetchdefault(@RequestBody PSSysAppSearchContext context) {
        Page<PSSysApp> domains = pssysappService.searchDefault(context) ;
        List<PSSysAppDTO> list = pssysappMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps/{pssysapp_id}/{action}")
    public ResponseEntity<PSSysAppDTO> dynamicCall(@PathVariable("pssysapp_id") String pssysapp_id , @PathVariable("action") String action , @RequestBody PSSysAppDTO pssysappdto) {
        PSSysApp domain = pssysappService.dynamicCall(pssysapp_id, action, pssysappMapping.toDomain(pssysappdto));
        pssysappdto = pssysappMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(pssysappdto);
    }
}

