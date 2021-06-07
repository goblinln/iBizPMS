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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductMonthlyRuntime;

@Slf4j
@Api(tags = {"产品月报" })
@RestController("StandardAPI-productmonthly")
@RequestMapping("")
public class ProductMonthlyResource {

    @Autowired
    public IIbizproProductMonthlyService ibizproproductmonthlyService;

    @Autowired
    public IbizproProductMonthlyRuntime ibizproproductmonthlyRuntime;

    @Autowired
    @Lazy
    public ProductMonthlyMapping productmonthlyMapping;

    @VersionCheck(entity = "ibizproproductmonthly" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #productmonthly_id, 'NONE')")
    @ApiOperation(value = "更新产品月报", tags = {"产品月报" },  notes = "更新产品月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmonthlies/{productmonthly_id}")
    @Transactional
    public ResponseEntity<ProductMonthlyDTO> update(@PathVariable("productmonthly_id") Long productmonthly_id, @RequestBody ProductMonthlyDTO productmonthlydto) {
		IbizproProductMonthly domain  = productmonthlyMapping.toDomain(productmonthlydto);
        domain.setIbizproproductmonthlyid(productmonthly_id);
		ibizproproductmonthlyService.update(domain );
		ProductMonthlyDTO dto = productmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(productmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productmonthlies/fetchdefault")
	public ResponseEntity<List<ProductMonthlyDTO>> fetchdefault(@RequestBody IbizproProductMonthlySearchContext context) {
        Page<IbizproProductMonthly> domains = ibizproproductmonthlyService.searchDefault(context) ;
        List<ProductMonthlyDTO> list = productmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
    @ApiOperation(value = "新建产品月报", tags = {"产品月报" },  notes = "新建产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/productmonthlies")
    @Transactional
    public ResponseEntity<ProductMonthlyDTO> create(@Validated @RequestBody ProductMonthlyDTO productmonthlydto) {
        IbizproProductMonthly domain = productmonthlyMapping.toDomain(productmonthlydto);
		ibizproproductmonthlyService.create(domain);
        ProductMonthlyDTO dto = productmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #productmonthly_id, 'NONE')")
    @ApiOperation(value = "获取产品月报", tags = {"产品月报" },  notes = "获取产品月报")
	@RequestMapping(method = RequestMethod.GET, value = "/productmonthlies/{productmonthly_id}")
    public ResponseEntity<ProductMonthlyDTO> get(@PathVariable("productmonthly_id") Long productmonthly_id) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.get(productmonthly_id);
        ProductMonthlyDTO dto = productmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(productmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #productmonthly_id, 'NONE')")
    @ApiOperation(value = "手动生成产品月报", tags = {"产品月报" },  notes = "手动生成产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/productmonthlies/{productmonthly_id}/manualcreatemonthly")
    public ResponseEntity<ProductMonthlyDTO> manualCreateMonthly(@PathVariable("productmonthly_id") Long productmonthly_id, @RequestBody ProductMonthlyDTO productmonthlydto) {
        IbizproProductMonthly domain = productmonthlyMapping.toDomain(productmonthlydto);
        domain.setIbizproproductmonthlyid(productmonthly_id);
        domain = ibizproproductmonthlyService.manualCreateMonthly(domain);
        productmonthlydto = productmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        productmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productmonthlydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
    @ApiOperation(value = "获取产品月报草稿", tags = {"产品月报" },  notes = "获取产品月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productmonthlies/getdraft")
    public ResponseEntity<ProductMonthlyDTO> getDraft(ProductMonthlyDTO dto) {
        IbizproProductMonthly domain = productmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productmonthlyMapping.toDto(ibizproproductmonthlyService.getDraft(domain)));
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productmonthlies/{productmonthly_id}/{action}")
    public ResponseEntity<ProductMonthlyDTO> dynamicCall(@PathVariable("productmonthly_id") Long productmonthly_id , @PathVariable("action") String action , @RequestBody ProductMonthlyDTO productmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.dynamicCall(productmonthly_id, action, productmonthlyMapping.toDomain(productmonthlydto));
        productmonthlydto = productmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productmonthlydto);
    }
}

