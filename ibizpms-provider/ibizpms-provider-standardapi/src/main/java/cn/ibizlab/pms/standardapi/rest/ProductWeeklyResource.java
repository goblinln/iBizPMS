package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductWeeklyRuntime;

@Slf4j
@Api(tags = {"产品周报" })
@RestController("StandardAPI-productweekly")
@RequestMapping("")
public class ProductWeeklyResource {

    @Autowired
    public IIbizproProductWeeklyService ibizproproductweeklyService;

    @Autowired
    public IbizproProductWeeklyRuntime ibizproproductweeklyRuntime;

    @Autowired
    @Lazy
    public ProductWeeklyMapping productweeklyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
    @ApiOperation(value = "新建产品周报", tags = {"产品周报" },  notes = "新建产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/productweeklies")
    @Transactional
    public ResponseEntity<ProductWeeklyDTO> create(@Validated @RequestBody ProductWeeklyDTO productweeklydto) {
        IbizproProductWeekly domain = productweeklyMapping.toDomain(productweeklydto);
		ibizproproductweeklyService.create(domain);
        ProductWeeklyDTO dto = productweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
    @ApiOperation(value = "获取产品周报草稿", tags = {"产品周报" },  notes = "获取产品周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productweeklies/getdraft")
    public ResponseEntity<ProductWeeklyDTO> getDraft(ProductWeeklyDTO dto) {
        IbizproProductWeekly domain = productweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productweeklyMapping.toDto(ibizproproductweeklyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #productweekly_id, 'NONE')")
    @ApiOperation(value = "统计产品周报", tags = {"产品周报" },  notes = "统计产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/productweeklies/{productweekly_id}/sumproductweekly")
    public ResponseEntity<ProductWeeklyDTO> sumProductWeekly(@PathVariable("productweekly_id") Long productweekly_id, @RequestBody ProductWeeklyDTO productweeklydto) {
        IbizproProductWeekly domain = productweeklyMapping.toDomain(productweeklydto);
        domain.setIbizproProductweeklyid(productweekly_id);
        domain = ibizproproductweeklyService.sumProductWeekly(domain);
        productweeklydto = productweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        productweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productweeklydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productweeklies/fetchdefault")
	public ResponseEntity<List<ProductWeeklyDTO>> fetchdefault(@RequestBody IbizproProductWeeklySearchContext context) {
        Page<IbizproProductWeekly> domains = ibizproproductweeklyService.searchDefault(context) ;
        List<ProductWeeklyDTO> list = productweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "ibizproproductweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #productweekly_id, 'NONE')")
    @ApiOperation(value = "更新产品周报", tags = {"产品周报" },  notes = "更新产品周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/productweeklies/{productweekly_id}")
    @Transactional
    public ResponseEntity<ProductWeeklyDTO> update(@PathVariable("productweekly_id") Long productweekly_id, @RequestBody ProductWeeklyDTO productweeklydto) {
		IbizproProductWeekly domain  = productweeklyMapping.toDomain(productweeklydto);
        domain.setIbizproProductweeklyid(productweekly_id);
		ibizproproductweeklyService.update(domain );
		ProductWeeklyDTO dto = productweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(productweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #productweekly_id, 'NONE')")
    @ApiOperation(value = "获取产品周报", tags = {"产品周报" },  notes = "获取产品周报")
	@RequestMapping(method = RequestMethod.GET, value = "/productweeklies/{productweekly_id}")
    public ResponseEntity<ProductWeeklyDTO> get(@PathVariable("productweekly_id") Long productweekly_id) {
        IbizproProductWeekly domain = ibizproproductweeklyService.get(productweekly_id);
        ProductWeeklyDTO dto = productweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(productweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productweeklies/{productweekly_id}/{action}")
    public ResponseEntity<ProductWeeklyDTO> dynamicCall(@PathVariable("productweekly_id") Long productweekly_id , @PathVariable("action") String action , @RequestBody ProductWeeklyDTO productweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyService.dynamicCall(productweekly_id, action, productweeklyMapping.toDomain(productweeklydto));
        productweeklydto = productweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productweeklydto);
    }
}

