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
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductRuntime;

@Slf4j
@Api(tags = {"产品" })
@RestController("WebApi-product")
@RequestMapping("")
public class ProductResource {

    @Autowired
    public IProductService productService;

    @Autowired
    public ProductRuntime productRuntime;

    @Autowired
    @Lazy
    public ProductMapping productMapping;

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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "删除产品", tags = {"产品" },  notes = "删除产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("product_id") Long product_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productService.remove(product_id));
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

    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品草稿", tags = {"产品" },  notes = "获取产品草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/products/getdraft")
    public ResponseEntity<ProductDTO> getDraft(ProductDTO dto) {
        Product domain = productMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productMapping.toDto(productService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "取消置顶", tags = {"产品" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cancelproducttop")
    public ResponseEntity<ProductDTO> cancelProductTop(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        domain.setId(product_id);
        domain = productService.cancelProductTop(domain);
        productdto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        productdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }


    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查产品", tags = {"产品" },  notes = "检查产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductDTO productdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productService.checkKey(productMapping.toDomain(productdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "关闭", tags = {"产品" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/close")
    public ResponseEntity<ProductDTO> close(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        domain.setId(product_id);
        domain = productService.close(domain);
        productdto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        productdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }


    @ApiOperation(value = "产品移动端计数器方法", tags = {"产品" },  notes = "产品移动端计数器方法")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/mobproductcounter")
    public ResponseEntity<ProductDTO> mobProductCounter(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        domain.setId(product_id);
        domain = productService.mobProductCounter(domain);
        productdto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        productdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }


    @ApiOperation(value = "移动端测试计数器", tags = {"产品" },  notes = "移动端测试计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/mobproducttestcounter")
    public ResponseEntity<ProductDTO> mobProductTestCounter(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        domain.setId(product_id);
        domain = productService.mobProductTestCounter(domain);
        productdto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        productdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "置顶", tags = {"产品" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/producttop")
    public ResponseEntity<ProductDTO> productTop(@PathVariable("product_id") Long product_id, @RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        domain.setId(product_id);
        domain = productService.productTop(domain);
        productdto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        productdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }


    @ApiOperation(value = "保存产品", tags = {"产品" },  notes = "保存产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/save")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productdto) {
        Product domain = productMapping.toDomain(productdto);
        productService.save(domain);
        ProductDTO dto = productMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取全部产品", tags = {"产品" } ,notes = "获取全部产品")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchalllist")
	public ResponseEntity<List<ProductDTO>> fetchalllist(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchAllList(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取所有产品", tags = {"产品" } ,notes = "获取所有产品")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchallproduct")
	public ResponseEntity<List<ProductDTO>> fetchallproduct(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchAllProduct(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取校验产品名称或产品代号是否已经存在", tags = {"产品" } ,notes = "获取校验产品名称或产品代号是否已经存在")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchchecknameorcode")
	public ResponseEntity<List<ProductDTO>> fetchchecknameorcode(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchCheckNameOrCode(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取默认查询", tags = {"产品" } ,notes = "获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchcurdefault")
	public ResponseEntity<List<ProductDTO>> fetchcurdefault(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchCurDefault(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前项目", tags = {"产品" } ,notes = "获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchcurproject")
	public ResponseEntity<List<ProductDTO>> fetchcurproject(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchCurProject(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前用户", tags = {"产品" } ,notes = "获取当前用户")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchcuruer")
	public ResponseEntity<List<ProductDTO>> fetchcuruer(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchCurUer(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchdefault")
	public ResponseEntity<List<ProductDTO>> fetchdefault(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchDefault(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"产品" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchesbulk")
	public ResponseEntity<List<ProductDTO>> fetchesbulk(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchESBulk(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品总览", tags = {"产品" } ,notes = "获取产品总览")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchproductpm")
	public ResponseEntity<List<ProductDTO>> fetchproductpm(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchProductPM(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品团队", tags = {"产品" } ,notes = "获取产品团队")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchproductteam")
	public ResponseEntity<List<ProductDTO>> fetchproductteam(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchProductTeam(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前项目", tags = {"产品" } ,notes = "获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/products/fetchstorycurproject")
	public ResponseEntity<List<ProductDTO>> fetchstorycurproject(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchStoryCurProject(context) ;
        List<ProductDTO> list = productMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/{action}")
    public ResponseEntity<ProductDTO> dynamicCall(@PathVariable("product_id") Long product_id , @PathVariable("action") String action , @RequestBody ProductDTO productdto) {
        Product domain = productService.dynamicCall(product_id, action, productMapping.toDomain(productdto));
        productdto = productMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productdto);
    }
}

