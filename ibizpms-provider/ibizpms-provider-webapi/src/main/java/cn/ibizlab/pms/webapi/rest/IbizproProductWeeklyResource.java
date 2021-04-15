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

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品周报", tags = {"产品周报" },  notes = "新建产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> create(@Validated @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
		ibizproproductweeklyService.create(domain);
        if(!ibizproproductweeklyRuntime.test(domain.getIbizproProductweeklyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品周报", tags = {"产品周报" },  notes = "批量新建产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbizproProductWeeklyDTO> ibizproproductweeklydtos) {
        ibizproproductweeklyService.createBatch(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproproductweekly" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id,'UPDATE')")
    @ApiOperation(value = "更新产品周报", tags = {"产品周报" },  notes = "更新产品周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> update(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
		IbizproProductWeekly domain  = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
		ibizproproductweeklyService.update(domain );
        if(!ibizproproductweeklyRuntime.test(ibizproproductweekly_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品周报", tags = {"产品周报" },  notes = "批量更新产品周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductweeklies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbizproProductWeeklyDTO> ibizproproductweeklydtos) {
        ibizproproductweeklyService.updateBatch(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id,'DELETE')")
    @ApiOperation(value = "删除产品周报", tags = {"产品周报" },  notes = "删除产品周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.remove(ibizproproductweekly_id));
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品周报", tags = {"产品周报" },  notes = "批量删除产品周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproproductweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.test(#ibizproproductweekly_id,'READ')")
    @ApiOperation(value = "获取产品周报", tags = {"产品周报" },  notes = "获取产品周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<IbizproProductWeeklyDTO> get(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
        IbizproProductWeekly domain = ibizproproductweeklyService.get(ibizproproductweekly_id);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品周报草稿", tags = {"产品周报" },  notes = "获取产品周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/getdraft")
    public ResponseEntity<IbizproProductWeeklyDTO> getDraft(IbizproProductWeeklyDTO dto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyMapping.toDto(ibizproproductweeklyService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品周报", tags = {"产品周报" },  notes = "检查产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.checkKey(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto)));
    }

    @ApiOperation(value = "保存产品周报", tags = {"产品周报" },  notes = "保存产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/save")
    public ResponseEntity<IbizproProductWeeklyDTO> save(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        ibizproproductweeklyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存产品周报", tags = {"产品周报" },  notes = "批量保存产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbizproProductWeeklyDTO> ibizproproductweeklydtos) {
        ibizproproductweeklyService.saveBatch(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "统计产品周报", tags = {"产品周报" },  notes = "统计产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/{ibizproproductweekly_id}/sumproductweekly")
    public ResponseEntity<IbizproProductWeeklyDTO> sumProductWeekly(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
        domain = ibizproproductweeklyService.sumProductWeekly(domain);
        ibizproproductweeklydto = ibizproproductweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklydto);
    }
    @ApiOperation(value = "批量处理[统计产品周报]", tags = {"产品周报" },  notes = "批量处理[统计产品周报]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/sumproductweeklybatch")
    public ResponseEntity<Boolean> sumProductWeeklyBatch(@RequestBody List<IbizproProductWeeklyDTO> ibizproproductweeklydtos) {
        List<IbizproProductWeekly> domains = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydtos);
        boolean result = ibizproproductweeklyService.sumProductWeeklyBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProductWeeklyDTO>> fetchDefault(@RequestBody IbizproProductWeeklySearchContext context) {
        ibizproproductweeklyRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproProductWeekly> domains = ibizproproductweeklyService.searchDefault(context) ;
        List<IbizproProductWeeklyDTO> list = ibizproproductweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbizproProductWeeklyRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品周报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductweeklies/searchdefault")
	public ResponseEntity<Page<IbizproProductWeeklyDTO>> searchDefault(@RequestBody IbizproProductWeeklySearchContext context) {
        ibizproproductweeklyRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproProductWeekly> domains = ibizproproductweeklyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproproductweeklyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/{ibizproproductweekly_id}/{action}")
    public ResponseEntity<IbizproProductWeeklyDTO> dynamicCall(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id , @PathVariable("action") String action , @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyService.dynamicCall(ibizproproductweekly_id, action, ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto));
        ibizproproductweeklydto = ibizproproductweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklydto);
    }
}

