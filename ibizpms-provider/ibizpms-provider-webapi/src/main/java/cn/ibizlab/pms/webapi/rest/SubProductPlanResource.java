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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductPlanRuntime;

@Slf4j
@Api(tags = {"产品计划" })
@RestController("WebApi-subproductplan")
@RequestMapping("")
public class SubProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public SubProductPlanMapping subproductplanMapping;

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProductQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品计划查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchproductquery")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProductQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProductQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchproductquery")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProductQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

