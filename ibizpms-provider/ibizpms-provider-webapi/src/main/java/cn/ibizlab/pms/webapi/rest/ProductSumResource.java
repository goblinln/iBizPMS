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
import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.service.IProductSumService;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"产品汇总表" })
@RestController("WebApi-productsum")
@RequestMapping("")
public class ProductSumResource {

    @Autowired
    public IProductSumService productsumService;

    @Autowired
    @Lazy
    public ProductSumMapping productsumMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Create-all')")
    @ApiOperation(value = "新建产品汇总表", tags = {"产品汇总表" },  notes = "新建产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums")
    public ResponseEntity<ProductSumDTO> create(@Validated @RequestBody ProductSumDTO productsumdto) {
        ProductSum domain = productsumMapping.toDomain(productsumdto);
		productsumService.create(domain);
        ProductSumDTO dto = productsumMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Create-all')")
    @ApiOperation(value = "批量新建产品汇总表", tags = {"产品汇总表" },  notes = "批量新建产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductSumDTO> productsumdtos) {
        productsumService.createBatch(productsumMapping.toDomain(productsumdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Update-all')")
    @ApiOperation(value = "更新产品汇总表", tags = {"产品汇总表" },  notes = "更新产品汇总表")
	@RequestMapping(method = RequestMethod.PUT, value = "/productsums/{productsum_id}")
    public ResponseEntity<ProductSumDTO> update(@PathVariable("productsum_id") Long productsum_id, @RequestBody ProductSumDTO productsumdto) {
		ProductSum domain  = productsumMapping.toDomain(productsumdto);
        domain .setId(productsum_id);
		productsumService.update(domain );
		ProductSumDTO dto = productsumMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Update-all')")
    @ApiOperation(value = "批量更新产品汇总表", tags = {"产品汇总表" },  notes = "批量更新产品汇总表")
	@RequestMapping(method = RequestMethod.PUT, value = "/productsums/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductSumDTO> productsumdtos) {
        productsumService.updateBatch(productsumMapping.toDomain(productsumdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Remove-all')")
    @ApiOperation(value = "删除产品汇总表", tags = {"产品汇总表" },  notes = "删除产品汇总表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productsums/{productsum_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productsum_id") Long productsum_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productsumService.remove(productsum_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Remove-all')")
    @ApiOperation(value = "批量删除产品汇总表", tags = {"产品汇总表" },  notes = "批量删除产品汇总表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productsums/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productsumService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Get-all')")
    @ApiOperation(value = "获取产品汇总表", tags = {"产品汇总表" },  notes = "获取产品汇总表")
	@RequestMapping(method = RequestMethod.GET, value = "/productsums/{productsum_id}")
    public ResponseEntity<ProductSumDTO> get(@PathVariable("productsum_id") Long productsum_id) {
        ProductSum domain = productsumService.get(productsum_id);
        ProductSumDTO dto = productsumMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品汇总表草稿", tags = {"产品汇总表" },  notes = "获取产品汇总表草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productsums/getdraft")
    public ResponseEntity<ProductSumDTO> getDraft(ProductSumDTO dto) {
        ProductSum domain = productsumMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productsumMapping.toDto(productsumService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品汇总表", tags = {"产品汇总表" },  notes = "检查产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductSumDTO productsumdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productsumService.checkKey(productsumMapping.toDomain(productsumdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Save-all')")
    @ApiOperation(value = "保存产品汇总表", tags = {"产品汇总表" },  notes = "保存产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/save")
    public ResponseEntity<Boolean> save(@RequestBody ProductSumDTO productsumdto) {
        return ResponseEntity.status(HttpStatus.OK).body(productsumService.save(productsumMapping.toDomain(productsumdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-Save-all')")
    @ApiOperation(value = "批量保存产品汇总表", tags = {"产品汇总表" },  notes = "批量保存产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductSumDTO> productsumdtos) {
        productsumService.saveBatch(productsumMapping.toDomain(productsumdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"产品汇总表" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchdefault")
	public ResponseEntity<List<ProductSumDTO>> fetchDefault(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchDefault(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"产品汇总表" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchdefault")
	public ResponseEntity<Page<ProductSumDTO>> searchDefault(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductBugcnt_QA-all')")
	@ApiOperation(value = "获取产品创建bug数及占比", tags = {"产品汇总表" } ,notes = "获取产品创建bug数及占比")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductbugcnt_qa")
	public ResponseEntity<List<ProductSumDTO>> fetchProductBugcnt_QA(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductBugcnt_QA(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductBugcnt_QA-all')")
	@ApiOperation(value = "查询产品创建bug数及占比", tags = {"产品汇总表" } ,notes = "查询产品创建bug数及占比")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductbugcnt_qa")
	public ResponseEntity<Page<ProductSumDTO>> searchProductBugcnt_QA(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductBugcnt_QA(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductCreateStory-all')")
	@ApiOperation(value = "获取产品创建需求占比", tags = {"产品汇总表" } ,notes = "获取产品创建需求占比")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductcreatestory")
	public ResponseEntity<List<ProductSumDTO>> fetchProductCreateStory(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductCreateStory(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductCreateStory-all')")
	@ApiOperation(value = "查询产品创建需求占比", tags = {"产品汇总表" } ,notes = "查询产品创建需求占比")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductcreatestory")
	public ResponseEntity<Page<ProductSumDTO>> searchProductCreateStory(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductCreateStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStoryHoursSum-all')")
	@ApiOperation(value = "获取产品需求工时汇总", tags = {"产品汇总表" } ,notes = "获取产品需求工时汇总")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductstoryhourssum")
	public ResponseEntity<List<ProductSumDTO>> fetchProductStoryHoursSum(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStoryHoursSum(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStoryHoursSum-all')")
	@ApiOperation(value = "查询产品需求工时汇总", tags = {"产品汇总表" } ,notes = "查询产品需求工时汇总")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductstoryhourssum")
	public ResponseEntity<Page<ProductSumDTO>> searchProductStoryHoursSum(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStoryHoursSum(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStorySum-all')")
	@ApiOperation(value = "获取产品需求汇总查询", tags = {"产品汇总表" } ,notes = "获取产品需求汇总查询")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductstorysum")
	public ResponseEntity<List<ProductSumDTO>> fetchProductStorySum(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorySum(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStorySum-all')")
	@ApiOperation(value = "查询产品需求汇总查询", tags = {"产品汇总表" } ,notes = "查询产品需求汇总查询")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductstorysum")
	public ResponseEntity<Page<ProductSumDTO>> searchProductStorySum(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorySum(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStorycntAndPlancnt-all')")
	@ApiOperation(value = "获取产品计划数和需求数", tags = {"产品汇总表" } ,notes = "获取产品计划数和需求数")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductstorycntandplancnt")
	public ResponseEntity<List<ProductSumDTO>> fetchProductStorycntAndPlancnt(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorycntAndPlancnt(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductStorycntAndPlancnt-all')")
	@ApiOperation(value = "查询产品计划数和需求数", tags = {"产品汇总表" } ,notes = "查询产品计划数和需求数")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductstorycntandplancnt")
	public ResponseEntity<Page<ProductSumDTO>> searchProductStorycntAndPlancnt(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorycntAndPlancnt(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductSumBugType-all')")
	@ApiOperation(value = "获取产品Bug类型统计", tags = {"产品汇总表" } ,notes = "获取产品Bug类型统计")
    @RequestMapping(method= RequestMethod.GET , value="/productsums/fetchproductsumbugtype")
	public ResponseEntity<List<ProductSumDTO>> fetchProductSumBugType(ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductSumBugType(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-ProductSum-searchProductSumBugType-all')")
	@ApiOperation(value = "查询产品Bug类型统计", tags = {"产品汇总表" } ,notes = "查询产品Bug类型统计")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/searchproductsumbugtype")
	public ResponseEntity<Page<ProductSumDTO>> searchProductSumBugType(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductSumBugType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productsumMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productsums/{productsum_id}/{action}")
    public ResponseEntity<ProductSumDTO> dynamicCall(@PathVariable("productsum_id") Long productsum_id , @PathVariable("action") String action , @RequestBody ProductSumDTO productsumdto) {
        ProductSum domain = productsumService.dynamicCall(productsum_id, action, productsumMapping.toDomain(productsumdto));
        productsumdto = productsumMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productsumdto);
    }

}

