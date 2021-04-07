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
import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.service.IProductStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.model.impl.ProductStatsModelImpl;

@Slf4j
@Api(tags = {"产品统计" })
@RestController("WebApi-productstats")
@RequestMapping("")
public class ProductStatsResource {

    @Autowired
    public IProductStatsService productstatsService;

    @Autowired
    public ProductStatsModelImpl productstatsModelImpl;

    @Autowired
    @Lazy
    public ProductStatsMapping productstatsMapping;

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'CREATE')")
    @ApiOperation(value = "新建产品统计", tags = {"产品统计" },  notes = "新建产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats")
    public ResponseEntity<ProductStatsDTO> create(@Validated @RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
		productstatsService.create(domain);
        ProductStatsDTO dto = productstatsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'CREATE')")
    @ApiOperation(value = "批量新建产品统计", tags = {"产品统计" },  notes = "批量新建产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductStatsDTO> productstatsdtos) {
        productstatsService.createBatch(productstatsMapping.toDomain(productstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'UPDATE')")
    @ApiOperation(value = "更新产品统计", tags = {"产品统计" },  notes = "更新产品统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/productstats/{productstats_id}")
    public ResponseEntity<ProductStatsDTO> update(@PathVariable("productstats_id") Long productstats_id, @RequestBody ProductStatsDTO productstatsdto) {
		ProductStats domain  = productstatsMapping.toDomain(productstatsdto);
        domain .setId(productstats_id);
		productstatsService.update(domain );
		ProductStatsDTO dto = productstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'UPDATE')")
    @ApiOperation(value = "批量更新产品统计", tags = {"产品统计" },  notes = "批量更新产品统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/productstats/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductStatsDTO> productstatsdtos) {
        productstatsService.updateBatch(productstatsMapping.toDomain(productstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'DELETE')")
    @ApiOperation(value = "删除产品统计", tags = {"产品统计" },  notes = "删除产品统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productstats/{productstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productstats_id") Long productstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productstatsService.remove(productstats_id));
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'DELETE')")
    @ApiOperation(value = "批量删除产品统计", tags = {"产品统计" },  notes = "批量删除产品统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'READ')")
    @ApiOperation(value = "获取产品统计", tags = {"产品统计" },  notes = "获取产品统计")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/{productstats_id}")
    public ResponseEntity<ProductStatsDTO> get(@PathVariable("productstats_id") Long productstats_id) {
        ProductStats domain = productstatsService.get(productstats_id);
        ProductStatsDTO dto = productstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品统计草稿", tags = {"产品统计" },  notes = "获取产品统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/getdraft")
    public ResponseEntity<ProductStatsDTO> getDraft(ProductStatsDTO dto) {
        ProductStats domain = productstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsMapping.toDto(productstatsService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品统计", tags = {"产品统计" },  notes = "检查产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductStatsDTO productstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productstatsService.checkKey(productstatsMapping.toDomain(productstatsdto)));
    }

    @PreAuthorize("@ProductStatsModelImpl.test(#productstats_id,'READ')")
    @ApiOperation(value = "获取测试统计详情", tags = {"产品统计" },  notes = "获取测试统计详情")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/{productstats_id}/getteststats")
    public ResponseEntity<ProductStatsDTO> getTestStats(@PathVariable("productstats_id") Long productstats_id, @RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
        domain.setId(productstats_id);
        domain = productstatsService.getTestStats(domain);
        productstatsdto = productstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsdto);
    }

    @ApiOperation(value = "保存产品统计", tags = {"产品统计" },  notes = "保存产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/save")
    public ResponseEntity<ProductStatsDTO> save(@RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
        productstatsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存产品统计", tags = {"产品统计" },  notes = "批量保存产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductStatsDTO> productstatsdtos) {
        productstatsService.saveBatch(productstatsMapping.toDomain(productstatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品统计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/productstats/fetchdefault")
	public ResponseEntity<List<ProductStatsDTO>> fetchDefault(ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchDefault(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"产品统计" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/searchdefault")
	public ResponseEntity<Page<ProductStatsDTO>> searchDefault(@RequestBody ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取未关闭产品", tags = {"产品统计" } ,notes = "获取未关闭产品")
    @RequestMapping(method= RequestMethod.GET , value="/productstats/fetchnoopenproduct")
	public ResponseEntity<List<ProductStatsDTO>> fetchNoOpenProduct(ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchNoOpenProduct(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询未关闭产品", tags = {"产品统计" } ,notes = "查询未关闭产品")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/searchnoopenproduct")
	public ResponseEntity<Page<ProductStatsDTO>> searchNoOpenProduct(@RequestBody ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchNoOpenProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取产品质量表", tags = {"产品统计" } ,notes = "获取产品质量表")
    @RequestMapping(method= RequestMethod.GET , value="/productstats/fetchprodctquantigird")
	public ResponseEntity<List<ProductStatsDTO>> fetchProdctQuantiGird(ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProdctQuantiGird(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询产品质量表", tags = {"产品统计" } ,notes = "查询产品质量表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/searchprodctquantigird")
	public ResponseEntity<Page<ProductStatsDTO>> searchProdctQuantiGird(@RequestBody ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProdctQuantiGird(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取产品投入表", tags = {"产品统计" } ,notes = "获取产品投入表")
    @RequestMapping(method= RequestMethod.GET , value="/productstats/fetchproductinputtable")
	public ResponseEntity<List<ProductStatsDTO>> fetchProductInputTable(ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProductInputTable(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询产品投入表", tags = {"产品统计" } ,notes = "查询产品投入表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/searchproductinputtable")
	public ResponseEntity<Page<ProductStatsDTO>> searchProductInputTable(@RequestBody ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProductInputTable(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取产品完成统计表", tags = {"产品统计" } ,notes = "获取产品完成统计表")
    @RequestMapping(method= RequestMethod.GET , value="/productstats/fetchproductcompletionstatistics")
	public ResponseEntity<List<ProductStatsDTO>> fetchProductcompletionstatistics(ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProductcompletionstatistics(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询产品完成统计表", tags = {"产品统计" } ,notes = "查询产品完成统计表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/searchproductcompletionstatistics")
	public ResponseEntity<Page<ProductStatsDTO>> searchProductcompletionstatistics(@RequestBody ProductStatsSearchContext context) {
        productstatsModelImpl.addAuthorityConditions(context,"READ");
        Page<ProductStats> domains = productstatsService.searchProductcompletionstatistics(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productstats/{productstats_id}/{action}")
    public ResponseEntity<ProductStatsDTO> dynamicCall(@PathVariable("productstats_id") Long productstats_id , @PathVariable("action") String action , @RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsService.dynamicCall(productstats_id, action, productstatsMapping.toDomain(productstatsdto));
        productstatsdto = productstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsdto);
    }

}

