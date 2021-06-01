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
import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.service.IProductLifeService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductLifeRuntime;

@Slf4j
@Api(tags = {"产品生命周期" })
@RestController("StandardAPI-productlife")
@RequestMapping("")
public class ProductLifeResource {

    @Autowired
    public IProductLifeService productlifeService;

    @Autowired
    public ProductLifeRuntime productlifeRuntime;

    @Autowired
    @Lazy
    public ProductLifeMapping productlifeMapping;


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取RoadMapYear", tags = {"产品生命周期" } ,notes = "根据产品获取RoadMapYear")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productlives/fetchroadmapyear")
	public ResponseEntity<List<ProductLifeDTO>> fetchRoadMapYearByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductLifeSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductLife> domains = productlifeService.searchRoadMapYear(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取GetRoadmap", tags = {"产品生命周期" } ,notes = "根据产品获取GetRoadmap")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productlives/fetchroadmap")
	public ResponseEntity<List<ProductLifeDTO>> fetchRoadmapByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductLifeSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductLife> domains = productlifeService.searchGetRoadmap(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

