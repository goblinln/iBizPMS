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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductWeeklyRuntime;

@Slf4j
@Api(tags = {"产品周报" })
@RestController("WebApi-ibizproproductweekly")
@RequestMapping("")
public class IbizproProductWeeklyResource {

    @Autowired
    public IIbizproProductWeeklyService ibizproproductweeklyService;

    @Autowired
    public IbizproProductWeeklyRuntime ibizproproductweeklyRuntime;

    @Autowired
    @Lazy
    public IbizproProductWeeklyMapping ibizproproductweeklyMapping;

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('NONE')")
    @ApiOperation(value = "新建产品周报", tags = {"产品周报" },  notes = "新建产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> create(@Validated @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
		ibizproproductweeklyService.create(domain);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproproductweekly" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "更新产品周报", tags = {"产品周报" },  notes = "更新产品周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> update(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
		IbizproProductWeekly domain  = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
		ibizproproductweeklyService.update(domain );
		IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(ibizproproductweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "删除产品周报", tags = {"产品周报" },  notes = "删除产品周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.remove(ibizproproductweekly_id));
    }


    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "获取产品周报", tags = {"产品周报" },  notes = "获取产品周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<IbizproProductWeeklyDTO> get(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
        IbizproProductWeekly domain = ibizproproductweeklyService.get(ibizproproductweekly_id);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(ibizproproductweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('NONE')")
    @ApiOperation(value = "获取产品周报草稿", tags = {"产品周报" },  notes = "获取产品周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/getdraft")
    public ResponseEntity<IbizproProductWeeklyDTO> getDraft(IbizproProductWeeklyDTO dto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyMapping.toDto(ibizproproductweeklyService.getDraft(domain)));
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查产品周报", tags = {"产品周报" },  notes = "检查产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.checkKey(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto)));
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存产品周报", tags = {"产品周报" },  notes = "保存产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/save")
    public ResponseEntity<IbizproProductWeeklyDTO> save(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        ibizproproductweeklyService.save(domain);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "统计产品周报", tags = {"产品周报" },  notes = "统计产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/{ibizproproductweekly_id}/sumproductweekly")
    public ResponseEntity<IbizproProductWeeklyDTO> sumProductWeekly(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
        domain = ibizproproductweeklyService.sumProductWeekly(domain);
        ibizproproductweeklydto = ibizproproductweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        ibizproproductweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklydto);
    }


    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProductWeeklyDTO>> fetchdefault(@RequestBody IbizproProductWeeklySearchContext context) {
        Page<IbizproProductWeekly> domains = ibizproproductweeklyService.searchDefault(context) ;
        List<IbizproProductWeeklyDTO> list = ibizproproductweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/{ibizproproductweekly_id}/{action}")
    public ResponseEntity<IbizproProductWeeklyDTO> dynamicCall(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id , @PathVariable("action") String action , @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyService.dynamicCall(ibizproproductweekly_id, action, ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto));
        ibizproproductweeklydto = ibizproproductweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklydto);
    }
}

