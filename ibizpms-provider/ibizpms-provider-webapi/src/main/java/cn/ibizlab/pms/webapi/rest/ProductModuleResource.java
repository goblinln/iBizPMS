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
import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.service.IProductModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductModuleRuntime;

@Slf4j
@Api(tags = {"需求模块" })
@RestController("WebApi-productmodule")
@RequestMapping("")
public class ProductModuleResource {

    @Autowired
    public IProductModuleService productmoduleService;

    @Autowired
    public ProductModuleRuntime productmoduleRuntime;

    @Autowired
    @Lazy
    public ProductModuleMapping productmoduleMapping;

    @ApiOperation(value = "保存需求模块", tags = {"需求模块" },  notes = "保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/save")
    public ResponseEntity<ProductModuleDTO> save(@RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        productmoduleService.save(domain);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存需求模块", tags = {"需求模块" },  notes = "批量保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductModuleDTO> productmoduledtos) {
        productmoduleService.saveBatch(productmoduleMapping.toDomain(productmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求模块", tags = {"需求模块" },  notes = "新建需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules")
    @Transactional
    public ResponseEntity<ProductModuleDTO> create(@Validated @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
		productmoduleService.create(domain);
        if(!productmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"需求模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchdefault")
	public ResponseEntity<List<ProductModuleDTO>> fetchdefault(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"需求模块" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/searchdefault")
	public ResponseEntity<Page<ProductModuleDTO>> searchDefault(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取需求模块草稿", tags = {"需求模块" },  notes = "获取需求模块草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productmodules/getdraft")
    public ResponseEntity<ProductModuleDTO> getDraft(ProductModuleDTO dto) {
        ProductModule domain = productmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduleMapping.toDto(productmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProductModuleRuntime.test(#productmodule_id,'UPDATE')")
    @ApiOperation(value = "更新需求模块", tags = {"需求模块" },  notes = "更新需求模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}")
    @Transactional
    public ResponseEntity<ProductModuleDTO> update(@PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
		ProductModule domain  = productmoduleMapping.toDomain(productmoduledto);
        domain.setId(productmodule_id);
		productmoduleService.update(domain );
        if(!productmoduleRuntime.test(productmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = productmoduleRuntime.getOPPrivs(productmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductModuleRuntime.test(#productmodule_id,'DELETE')")
    @ApiOperation(value = "删除需求模块", tags = {"需求模块" },  notes = "删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productmodules/{productmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productmodule_id") Long productmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productmoduleService.remove(productmodule_id));
    }


    @PreAuthorize("@ProductModuleRuntime.test(#productmodule_id,'READ')")
    @ApiOperation(value = "获取需求模块", tags = {"需求模块" },  notes = "获取需求模块")
	@RequestMapping(method = RequestMethod.GET, value = "/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> get(@PathVariable("productmodule_id") Long productmodule_id) {
        ProductModule domain = productmoduleService.get(productmodule_id);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = productmoduleRuntime.getOPPrivs(productmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/{action}")
    public ResponseEntity<ProductModuleDTO> dynamicCall(@PathVariable("productmodule_id") Long productmodule_id , @PathVariable("action") String action , @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleService.dynamicCall(productmodule_id, action, productmoduleMapping.toDomain(productmoduledto));
        productmoduledto = productmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }
    @ApiOperation(value = "根据产品保存需求模块", tags = {"需求模块" },  notes = "根据产品保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/save")
    public ResponseEntity<ProductModuleDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
        productmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存需求模块", tags = {"需求模块" },  notes = "根据产品批量保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductModuleDTO> productmoduledtos) {
        List<ProductModule> domainlist=productmoduleMapping.toDomain(productmoduledtos);
        for(ProductModule domain:domainlist){
             domain.setRoot(product_id);
        }
        productmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立需求模块", tags = {"需求模块" },  notes = "根据产品建立需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules")
    public ResponseEntity<ProductModuleDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
		productmoduleService.create(domain);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"需求模块" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchdefault")
	public ResponseEntity<List<ProductModuleDTO>> fetchProductModuleDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"需求模块" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/searchdefault")
	public ResponseEntity<Page<ProductModuleDTO>> searchProductModuleDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取需求模块草稿", tags = {"需求模块" },  notes = "根据产品获取需求模块草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productmodules/getdraft")
    public ResponseEntity<ProductModuleDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductModuleDTO dto) {
        ProductModule domain = productmoduleMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduleMapping.toDto(productmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新需求模块", tags = {"需求模块" },  notes = "根据产品更新需求模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
        domain.setId(productmodule_id);
		productmoduleService.update(domain);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除需求模块", tags = {"需求模块" },  notes = "根据产品删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productmoduleService.remove(productmodule_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求模块", tags = {"需求模块" },  notes = "根据产品获取需求模块")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id) {
        ProductModule domain = productmoduleService.get(productmodule_id);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

