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
@RestController("WebApi-productplan")
@RequestMapping("")
public class ProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public ProductPlanMapping productplanMapping;

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'LINKSTORY')")
    @ApiOperation(value = "关联需求", tags = {"产品计划" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @ApiOperation(value = "解除关联Bug", tags = {"产品计划" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'LINKBUG')")
    @ApiOperation(value = "关联Bug", tags = {"产品计划" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目计划列表", tags = {"产品计划" } ,notes = "获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchprojectplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目计划列表", tags = {"产品计划" } ,notes = "查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchprojectplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProjectPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取计划（代码表）", tags = {"产品计划" } ,notes = "获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchplancodelist")
	public ResponseEntity<List<ProductPlanDTO>> fetchplancodelist(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询计划（代码表）", tags = {"产品计划" } ,notes = "查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchplancodelist")
	public ResponseEntity<Page<ProductPlanDTO>> searchPlanCodeList(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "删除产品计划", tags = {"产品计划" },  notes = "删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplan_id") Long productplan_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }


    @ApiOperation(value = "批量解除关联需求", tags = {"产品计划" },  notes = "批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品默认查询", tags = {"产品计划" } ,notes = "获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchproductquery(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品默认查询", tags = {"产品计划" } ,notes = "查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchproductquery")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductQuery(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "导入计划模板", tags = {"产品计划" },  notes = "导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTemplet(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "获取产品计划", tags = {"产品计划" },  notes = "获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> get(@PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(productplan_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量解除关联Bug", tags = {"产品计划" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品计划", tags = {"产品计划" },  notes = "新建产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans")
    @Transactional
    public ResponseEntity<ProductPlanDTO> create(@Validated @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
		productplanService.create(domain);
        if(!productplanRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "更新产品计划", tags = {"产品计划" },  notes = "更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}")
    @Transactional
    public ResponseEntity<ProductPlanDTO> update(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
		ProductPlan domain  = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
		productplanService.update(domain );
        if(!productplanRuntime.test(productplan_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(productplan_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "批关联BUG", tags = {"产品计划" },  notes = "批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchlinkbug")
    public ResponseEntity<ProductPlanDTO> batchLinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品计划草稿", tags = {"产品计划" },  notes = "获取产品计划草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraft(ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品计划" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchcurproductplanstory")
	public ResponseEntity<List<ProductPlanDTO>> fetchcurproductplanstory(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品计划" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchcurproductplanstory")
	public ResponseEntity<Page<ProductPlanDTO>> searchCurProductPlanStory(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取CurProductPlan", tags = {"产品计划" } ,notes = "获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchcurproductplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchcurproductplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询CurProductPlan", tags = {"产品计划" } ,notes = "查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchcurproductplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchCurProductPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @ApiOperation(value = "解除关联需求", tags = {"产品计划" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @ApiOperation(value = "批关联需求", tags = {"产品计划" },  notes = "批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchlinkstory")
    public ResponseEntity<ProductPlanDTO> batchLinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/{action}")
    public ResponseEntity<ProductPlanDTO> dynamicCall(@PathVariable("productplan_id") Long productplan_id , @PathVariable("action") String action , @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanService.dynamicCall(productplan_id, action, productplanMapping.toDomain(productplandto));
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @ApiOperation(value = "根据产品解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchprojectplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchplancodelist")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchplancodelist")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除产品计划", tags = {"产品计划" },  notes = "根据产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }


    @ApiOperation(value = "根据产品批量解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchproductquery")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取产品计划", tags = {"产品计划" },  notes = "根据产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立产品计划", tags = {"产品计划" },  notes = "根据产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新产品计划", tags = {"产品计划" },  notes = "根据产品更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "根据产品批关联BUG产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchlinkbug")
    public ResponseEntity<ProductPlanDTO> batchLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品计划" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplanstory")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询数据集", tags = {"产品计划" } ,notes = "根据产品查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchcurproductplanstory")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchcurproductplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @ApiOperation(value = "根据产品批关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchlinkstory")
    public ResponseEntity<ProductPlanDTO> batchLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

}

