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
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductRuntime;

@Slf4j
@Api(tags = {"产品" })
@RestController("StandardAPI-product")
@RequestMapping("")
public class ProductResource {

    @Autowired
    public IProductService productService;

    @Autowired
    public ProductRuntime productRuntime;

    @Autowired
    @Lazy
    public ProductMapping productMapping;

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "删除产品", tags = {"产品" },  notes = "删除产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("product_id") Long product_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productService.remove(product_id));
    }

    @PreAuthorize("@ProductRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品", tags = {"产品" },  notes = "批量删除产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "product" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "更新产品", tags = {"产品" },  notes = "更新产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}")
    @Transactional
    public ResponseEntity<ProductDTO> update(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
		Product domain  = productMapping.toDomain(productdto);
        domain.setId(product_id);
		productService.update(domain );
        if(!productRuntime.test(product_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductDTO dto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(product_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品", tags = {"产品" },  notes = "批量更新产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductDTO> productdtos) {
        productService.updateBatch(productMapping.toDomain(productdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品", tags = {"产品" },  notes = "新建产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products")
    @Transactional
    public ResponseEntity<ProductDTO> create(@Validated @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
		productService.create(domain);
        if(!productRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductDTO dto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品", tags = {"产品" },  notes = "批量新建产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductDTO> productdtos) {
        productService.createBatch(productMapping.toDomain(productdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "获取产品", tags = {"产品" },  notes = "获取产品")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}")
    public ResponseEntity<ProductDTO> get(@PathVariable("product_id") Long product_id) {
        Product domain = productService.get(product_id);
        ProductDTO dto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(product_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品草稿", tags = {"产品" },  notes = "获取产品草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/products/getdraft")
    public ResponseEntity<ProductDTO> getDraft(ProductDTO dto) {
        Product domain = productMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productMapping.toDto(productService.getDraft(domain)));
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/{action}")
    public ResponseEntity<ProductDTO> dynamicCall(@PathVariable("product_id") Long product_id , @PathVariable("action") String action , @RequestBody ProductDTO productdto) {
        Product domain = productService.dynamicCall(product_id, action, productMapping.toDomain(productdto));
        productdto = productMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }
}

