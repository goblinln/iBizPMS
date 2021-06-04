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
@RestController("StandardAPI-accountproduct")
@RequestMapping("")
public class AccountProductResource {

    @Autowired
    public IProductService productService;

    @Autowired
    public ProductRuntime productRuntime;

    @Autowired
    @Lazy
    public AccountProductMapping accountproductMapping;

    @PreAuthorize("test('ZT_PRODUCT', #accountproduct_id, 'READ')")
    @ApiOperation(value = "获取产品", tags = {"产品" },  notes = "获取产品")
	@RequestMapping(method = RequestMethod.GET, value = "/accountproducts/{accountproduct_id}")
    public ResponseEntity<AccountProductDTO> get(@PathVariable("accountproduct_id") Long accountproduct_id) {
        Product domain = productService.get(accountproduct_id);
        AccountProductDTO dto = accountproductMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(accountproduct_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCT', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"产品" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountproducts/fetchmy")
	public ResponseEntity<List<AccountProductDTO>> fetchmy(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchMy(context) ;
        List<AccountProductDTO> list = accountproductMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCT', 'READ')")
	@ApiOperation(value = "获取用户数据", tags = {"产品" } ,notes = "获取用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountproducts/fetchaccount")
	public ResponseEntity<List<AccountProductDTO>> fetchaccount(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchAccount(context) ;
        List<AccountProductDTO> list = accountproductMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/accountproducts/{accountproduct_id}/{action}")
    public ResponseEntity<AccountProductDTO> dynamicCall(@PathVariable("accountproduct_id") Long accountproduct_id , @PathVariable("action") String action , @RequestBody AccountProductDTO accountproductdto) {
        Product domain = productService.dynamicCall(accountproduct_id, action, accountproductMapping.toDomain(accountproductdto));
        accountproductdto = accountproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accountproductdto);
    }

    @PreAuthorize("test('ZT_PRODUCT', #accountproduct_id, 'READ')")
    @ApiOperation(value = "根据系统用户获取产品", tags = {"产品" },  notes = "根据系统用户获取产品")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accountproducts/{accountproduct_id}")
    public ResponseEntity<AccountProductDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accountproduct_id") Long accountproduct_id) {
        Product domain = productService.get(accountproduct_id);
        AccountProductDTO dto = accountproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCT','READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"产品" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountproducts/fetchmy")
	public ResponseEntity<List<AccountProductDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProductSearchContext context) {
        
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchMy(context) ;
        List<AccountProductDTO> list = accountproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCT','READ')")
	@ApiOperation(value = "根据系统用户获取用户数据", tags = {"产品" } ,notes = "根据系统用户获取用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountproducts/fetchaccount")
	public ResponseEntity<List<AccountProductDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProductSearchContext context) {
        
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchAccount(context) ;
        List<AccountProductDTO> list = accountproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

