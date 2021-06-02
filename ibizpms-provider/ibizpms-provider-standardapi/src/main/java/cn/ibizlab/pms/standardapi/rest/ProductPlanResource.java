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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductPlanRuntime;

@Slf4j
@Api(tags = {"产品计划" })
@RestController("StandardAPI-productplan")
@RequestMapping("")
public class ProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public ProductPlanMapping productplanMapping;


    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品计划", tags = {"产品计划" },  notes = "根据产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品计划", tags = {"产品计划" },  notes = "根据产品批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立产品计划", tags = {"产品计划" },  notes = "根据产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"产品计划" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板", tags = {"产品计划" },  notes = "根据产品导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品关联需求", tags = {"产品计划" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解除关联需求", tags = {"产品计划" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品计划", tags = {"产品计划" },  notes = "根据产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
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


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"产品计划" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目删除产品计划", tags = {"产品计划" },  notes = "根据项目删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目批量删除产品计划", tags = {"产品计划" },  notes = "根据项目批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目建立产品计划", tags = {"产品计划" },  notes = "根据项目建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取项目计划列表", tags = {"产品计划" } ,notes = "根据项目获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.quickTest('LINKBUG')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"产品计划" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目导入计划模板", tags = {"产品计划" },  notes = "根据项目导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取产品默认查询", tags = {"产品计划" } ,notes = "根据项目获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.quickTest('LINKSTORY')")
    @ApiOperation(value = "根据项目关联需求", tags = {"产品计划" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目解除关联需求", tags = {"产品计划" },  notes = "根据项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取产品计划", tags = {"产品计划" },  notes = "根据项目获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据项目更新产品计划", tags = {"产品计划" },  notes = "根据项目更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目获取产品计划草稿", tags = {"产品计划" },  notes = "根据项目获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目解除关联Bug", tags = {"产品计划" },  notes = "根据项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }



    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品删除产品计划", tags = {"产品计划" },  notes = "根据系统用户产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品批量删除产品计划", tags = {"产品计划" },  notes = "根据系统用户产品批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProduct(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品建立产品计划", tags = {"产品计划" },  notes = "根据系统用户产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据系统用户产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectPlanBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品关联Bug", tags = {"产品计划" },  notes = "根据系统用户产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品导入计划模板", tags = {"产品计划" },  notes = "根据系统用户产品导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据系统用户产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductQueryBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品关联需求", tags = {"产品计划" },  notes = "根据系统用户产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户产品解除关联需求", tags = {"产品计划" },  notes = "根据系统用户产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品获取产品计划", tags = {"产品计划" },  notes = "根据系统用户产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品更新产品计划", tags = {"产品计划" },  notes = "根据系统用户产品更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据系统用户产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户产品解除关联Bug", tags = {"产品计划" },  notes = "根据系统用户产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }



    @PreAuthorize("@ProductPlanRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目删除产品计划", tags = {"产品计划" },  notes = "根据系统用户项目删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目批量删除产品计划", tags = {"产品计划" },  notes = "根据系统用户项目批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProject(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目建立产品计划", tags = {"产品计划" },  notes = "根据系统用户项目建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取项目计划列表", tags = {"产品计划" } ,notes = "根据系统用户项目获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectPlanBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.quickTest('LINKBUG')")
    @ApiOperation(value = "根据系统用户项目关联Bug", tags = {"产品计划" },  notes = "根据系统用户项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目导入计划模板", tags = {"产品计划" },  notes = "根据系统用户项目导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取产品默认查询", tags = {"产品计划" } ,notes = "根据系统用户项目获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductQueryBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.quickTest('LINKSTORY')")
    @ApiOperation(value = "根据系统用户项目关联需求", tags = {"产品计划" },  notes = "根据系统用户项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户项目解除关联需求", tags = {"产品计划" },  notes = "根据系统用户项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目获取产品计划", tags = {"产品计划" },  notes = "根据系统用户项目获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户项目更新产品计划", tags = {"产品计划" },  notes = "根据系统用户项目更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户项目获取产品计划草稿", tags = {"产品计划" },  notes = "根据系统用户项目获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户项目解除关联Bug", tags = {"产品计划" },  notes = "根据系统用户项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

}

