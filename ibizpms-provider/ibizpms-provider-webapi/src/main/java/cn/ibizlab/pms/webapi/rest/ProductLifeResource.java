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
import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.service.IProductLifeService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"产品生命周期" })
@RestController("WebApi-productlife")
@RequestMapping("")
public class ProductLifeResource {

    @Autowired
    public IProductLifeService productlifeService;


    @Autowired
    @Lazy
    public ProductLifeMapping productlifeMapping;

	@ApiOperation(value = "获取RoadMapYear", tags = {"产品生命周期" } ,notes = "获取RoadMapYear")
    @RequestMapping(method= RequestMethod.GET , value="/productlives/fetchroadmapyear")
	public ResponseEntity<List<ProductLifeDTO>> fetchRoadMapYear(ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchRoadMapYear(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询RoadMapYear", tags = {"产品生命周期" } ,notes = "查询RoadMapYear")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/searchroadmapyear")
	public ResponseEntity<Page<ProductLifeDTO>> searchRoadMapYear(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchRoadMapYear(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productlifeMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取获取产品路线", tags = {"产品生命周期" } ,notes = "获取获取产品路线")
    @RequestMapping(method= RequestMethod.GET , value="/productlives/fetchgetroadmaps")
	public ResponseEntity<List<ProductLifeDTO>> fetchGetRoadmapS(ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmapS(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询获取产品路线", tags = {"产品生命周期" } ,notes = "查询获取产品路线")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/searchgetroadmaps")
	public ResponseEntity<Page<ProductLifeDTO>> searchGetRoadmapS(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmapS(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productlifeMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取GetRoadmap", tags = {"产品生命周期" } ,notes = "获取GetRoadmap")
    @RequestMapping(method= RequestMethod.GET , value="/productlives/fetchgetroadmap")
	public ResponseEntity<List<ProductLifeDTO>> fetchGetRoadmap(ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmap(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询GetRoadmap", tags = {"产品生命周期" } ,notes = "查询GetRoadmap")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/searchgetroadmap")
	public ResponseEntity<Page<ProductLifeDTO>> searchGetRoadmap(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmap(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productlifeMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productlives/{productlife_id}/{action}")
    public ResponseEntity<ProductLifeDTO> dynamicCall(@PathVariable("productlife_id") String productlife_id , @PathVariable("action") String action , @RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeService.dynamicCall(productlife_id, action, productlifeMapping.toDomain(productlifedto));
        productlifedto = productlifeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productlifedto);
    }

}

