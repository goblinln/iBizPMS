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
import cn.ibizlab.pms.core.zentao.domain.Branch;
import cn.ibizlab.pms.core.zentao.service.IBranchService;
import cn.ibizlab.pms.core.zentao.filter.BranchSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BranchRuntime;

@Slf4j
@Api(tags = {"产品的分支和平台信息" })
@RestController("StandardAPI-productbranch")
@RequestMapping("")
public class ProductBranchResource {

    @Autowired
    public IBranchService branchService;

    @Autowired
    public BranchRuntime branchRuntime;

    @Autowired
    @Lazy
    public ProductBranchMapping productbranchMapping;



    @PreAuthorize("@BranchRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品批量保存产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品批量保存产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbranches/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductBranchDTO> productbranchdtos) {
        List<Branch> domainlist=productbranchMapping.toDomain(productbranchdtos);
        for(Branch domain:domainlist){
             domain.setProduct(product_id);
        }
        branchService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取CurProduct", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productbranches/fetchcurproduct")
	public ResponseEntity<List<ProductBranchDTO>> fetchProductBranchCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchCurProduct(context) ;
        List<ProductBranchDTO> list = productbranchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品建立产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbranches")
    public ResponseEntity<ProductBranchDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductBranchDTO productbranchdto) {
        Branch domain = productbranchMapping.toDomain(productbranchdto);
        domain.setProduct(product_id);
		branchService.create(domain);
        ProductBranchDTO dto = productbranchMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品更新产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productbranches/{productbranch_id}")
    public ResponseEntity<ProductBranchDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbranch_id") Long productbranch_id, @RequestBody ProductBranchDTO productbranchdto) {
        Branch domain = productbranchMapping.toDomain(productbranchdto);
        domain.setProduct(product_id);
        domain.setId(productbranch_id);
		branchService.update(domain);
        ProductBranchDTO dto = productbranchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取产品的分支和平台信息草稿", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productbranches/getdraft")
    public ResponseEntity<ProductBranchDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductBranchDTO dto) {
        Branch domain = productbranchMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productbranchMapping.toDto(branchService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productbranches/fetchdefault")
	public ResponseEntity<List<ProductBranchDTO>> fetchProductBranchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchDefault(context) ;
        List<ProductBranchDTO> list = productbranchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productbranches/{productbranch_id}")
    public ResponseEntity<ProductBranchDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbranch_id") Long productbranch_id) {
        Branch domain = branchService.get(productbranch_id);
        ProductBranchDTO dto = productbranchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productbranches/{productbranch_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbranch_id") Long productbranch_id) {
		return ResponseEntity.status(HttpStatus.OK).body(branchService.remove(productbranch_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品批量删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productbranches/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        branchService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

}

