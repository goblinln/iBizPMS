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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"产品月报" })
@RestController("WebApi-ibizproproductmonthly")
@RequestMapping("")
public class IbizproProductMonthlyResource {

    @Autowired
    public IIbizproProductMonthlyService ibizproproductmonthlyService;

    @Autowired
    @Lazy
    public IbizproProductMonthlyMapping ibizproproductmonthlyMapping;

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'CREATE')")
    @ApiOperation(value = "新建产品月报", tags = {"产品月报" },  notes = "新建产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies")
    public ResponseEntity<IbizproProductMonthlyDTO> create(@Validated @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
		ibizproproductmonthlyService.create(domain);
        IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'CREATE')")
    @ApiOperation(value = "批量新建产品月报", tags = {"产品月报" },  notes = "批量新建产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbizproProductMonthlyDTO> ibizproproductmonthlydtos) {
        ibizproproductmonthlyService.createBatch(ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproproductmonthly" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'UPDATE')")
    @ApiOperation(value = "更新产品月报", tags = {"产品月报" },  notes = "更新产品月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    public ResponseEntity<IbizproProductMonthlyDTO> update(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
		IbizproProductMonthly domain  = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain .setIbizproproductmonthlyid(ibizproproductmonthly_id);
		ibizproproductmonthlyService.update(domain );
		IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'UPDATE')")
    @ApiOperation(value = "批量更新产品月报", tags = {"产品月报" },  notes = "批量更新产品月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductmonthlies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbizproProductMonthlyDTO> ibizproproductmonthlydtos) {
        ibizproproductmonthlyService.updateBatch(ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'DELETE')")
    @ApiOperation(value = "删除产品月报", tags = {"产品月报" },  notes = "删除产品月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyService.remove(ibizproproductmonthly_id));
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'DELETE')")
    @ApiOperation(value = "批量删除产品月报", tags = {"产品月报" },  notes = "批量删除产品月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductmonthlies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproproductmonthlyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test(#ibizproproductmonthly_id,'READ')")
    @ApiOperation(value = "获取产品月报", tags = {"产品月报" },  notes = "获取产品月报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    public ResponseEntity<IbizproProductMonthlyDTO> get(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.get(ibizproproductmonthly_id);
        IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品月报草稿", tags = {"产品月报" },  notes = "获取产品月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/getdraft")
    public ResponseEntity<IbizproProductMonthlyDTO> getDraft(IbizproProductMonthlyDTO dto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyMapping.toDto(ibizproproductmonthlyService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品月报", tags = {"产品月报" },  notes = "检查产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyService.checkKey(ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto)));
    }

    @ApiOperation(value = "手动生成产品月报", tags = {"产品月报" },  notes = "手动生成产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/manualcreatemonthly")
    public ResponseEntity<IbizproProductMonthlyDTO> manualCreateMonthly(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain.setIbizproproductmonthlyid(ibizproproductmonthly_id);
        domain = ibizproproductmonthlyService.manualCreateMonthly(domain);
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbizproProductMonthly-ManualCreateMonthly-all')")
    @ApiOperation(value = "批量处理[手动生成产品月报]", tags = {"产品月报" },  notes = "批量处理[手动生成产品月报]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/manualcreatemonthlybatch")
    public ResponseEntity<Boolean> manualCreateMonthlyBatch(@RequestBody List<IbizproProductMonthlyDTO> ibizproproductmonthlydtos) {
        List<IbizproProductMonthly> domains = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydtos);
        boolean result = ibizproproductmonthlyService.manualCreateMonthlyBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存产品月报", tags = {"产品月报" },  notes = "保存产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/save")
    public ResponseEntity<IbizproProductMonthlyDTO> save(@RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        ibizproproductmonthlyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存产品月报", tags = {"产品月报" },  notes = "批量保存产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbizproProductMonthlyDTO> ibizproproductmonthlydtos) {
        ibizproproductmonthlyService.saveBatch(ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "汇总产品月报", tags = {"产品月报" },  notes = "汇总产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/statsproductmonthly")
    public ResponseEntity<IbizproProductMonthlyDTO> statsProductMonthly(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain.setIbizproproductmonthlyid(ibizproproductmonthly_id);
        domain = ibizproproductmonthlyService.statsProductMonthly(domain);
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbizproProductMonthly-StatsProductMonthly-all')")
    @ApiOperation(value = "批量处理[汇总产品月报]", tags = {"产品月报" },  notes = "批量处理[汇总产品月报]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/statsproductmonthlybatch")
    public ResponseEntity<Boolean> statsProductMonthlyBatch(@RequestBody List<IbizproProductMonthlyDTO> ibizproproductmonthlydtos) {
        List<IbizproProductMonthly> domains = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydtos);
        boolean result = ibizproproductmonthlyService.statsProductMonthlyBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproproductmonthlies/fetchdefault")
	public ResponseEntity<List<IbizproProductMonthlyDTO>> fetchDefault(IbizproProductMonthlySearchContext context) {
        Page<IbizproProductMonthly> domains = ibizproproductmonthlyService.searchDefault(context) ;
        List<IbizproProductMonthlyDTO> list = ibizproproductmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbizproProductMonthlyModelImpl.test('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品月报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductmonthlies/searchdefault")
	public ResponseEntity<Page<IbizproProductMonthlyDTO>> searchDefault(@RequestBody IbizproProductMonthlySearchContext context) {
        Page<IbizproProductMonthly> domains = ibizproproductmonthlyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproproductmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/{action}")
    public ResponseEntity<IbizproProductMonthlyDTO> dynamicCall(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id , @PathVariable("action") String action , @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.dynamicCall(ibizproproductmonthly_id, action, ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto));
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }

}

