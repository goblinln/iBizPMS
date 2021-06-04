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
@RestController("StandardAPI-test")
@RequestMapping("")
public class TestResource {

    @Autowired
    public IProductService productService;

    @Autowired
    public ProductRuntime productRuntime;

    @Autowired
    @Lazy
    public TestMapping testMapping;

    @PreAuthorize("test('ZT_PRODUCT', #test_id, 'READ')")
    @ApiOperation(value = "获取产品", tags = {"产品" },  notes = "获取产品")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{test_id}")
    public ResponseEntity<TestDTO> get(@PathVariable("test_id") Long test_id) {
        Product domain = productService.get(test_id);
        TestDTO dto = testMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(test_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PRODUCT', #test_id, 'READ')")
    @ApiOperation(value = "取消置顶", tags = {"产品" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{test_id}/cancelproducttop")
    public ResponseEntity<TestDTO> cancelProductTop(@PathVariable("test_id") Long test_id, @RequestBody TestDTO testdto) {
        Product domain = testMapping.toDomain(testdto);
        domain.setId(test_id);
        domain = productService.cancelProductTop(domain);
        testdto = testMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        testdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testdto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCT', 'READ')")
	@ApiOperation(value = "获取默认查询", tags = {"产品" } ,notes = "获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/fetchcurdefault")
	public ResponseEntity<List<TestDTO>> fetchcurdefault(@RequestBody ProductSearchContext context) {
        productRuntime.addAuthorityConditions(context,"READ");
        Page<Product> domains = productService.searchCurDefault(context) ;
        List<TestDTO> list = testMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCT', #test_id, 'READ')")
    @ApiOperation(value = "置顶", tags = {"产品" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{test_id}/producttop")
    public ResponseEntity<TestDTO> productTop(@PathVariable("test_id") Long test_id, @RequestBody TestDTO testdto) {
        Product domain = testMapping.toDomain(testdto);
        domain.setId(test_id);
        domain = productService.productTop(domain);
        testdto = testMapping.toDto(domain);
        Map<String,Integer> opprivs = productRuntime.getOPPrivs(domain.getId());
        testdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testdto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/tests/{test_id}/{action}")
    public ResponseEntity<TestDTO> dynamicCall(@PathVariable("test_id") Long test_id , @PathVariable("action") String action , @RequestBody TestDTO testdto) {
        Product domain = productService.dynamicCall(test_id, action, testMapping.toDomain(testdto));
        testdto = testMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testdto);
    }
}

