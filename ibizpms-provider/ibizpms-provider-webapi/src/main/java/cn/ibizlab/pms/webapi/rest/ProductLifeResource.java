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
import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.service.IProductLifeService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductLifeRuntime;

@Slf4j
@Api(tags = {"产品生命周期" })
@RestController("WebApi-productlife")
@RequestMapping("")
public class ProductLifeResource {

    @Autowired
    public IProductLifeService productlifeService;

    @Autowired
    public ProductLifeRuntime productlifeRuntime;

    @Autowired
    @Lazy
    public ProductLifeMapping productlifeMapping;

    @PreAuthorize("@ProductLifeRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品生命周期", tags = {"产品生命周期" },  notes = "新建产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives")
    @Transactional
    public ResponseEntity<ProductLifeDTO> create(@Validated @RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeMapping.toDomain(productlifedto);
		productlifeService.create(domain);
        if(!productlifeRuntime.test(domain.getProductlifeid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String,Integer> opprivs = productlifeRuntime.getOPPrivs(domain.getProductlifeid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "productlife" , versionfield = "updatedate")
    @PreAuthorize("@ProductLifeRuntime.test(#productlife_id, 'UPDATE')")
    @ApiOperation(value = "更新产品生命周期", tags = {"产品生命周期" },  notes = "更新产品生命周期")
	@RequestMapping(method = RequestMethod.PUT, value = "/productlives/{productlife_id}")
    @Transactional
    public ResponseEntity<ProductLifeDTO> update(@PathVariable("productlife_id") String productlife_id, @RequestBody ProductLifeDTO productlifedto) {
		ProductLife domain  = productlifeMapping.toDomain(productlifedto);
        domain.setProductlifeid(productlife_id);
		productlifeService.update(domain );
        if(!productlifeRuntime.test(productlife_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String,Integer> opprivs = productlifeRuntime.getOPPrivs(productlife_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductLifeRuntime.test(#productlife_id, 'DELETE')")
    @ApiOperation(value = "删除产品生命周期", tags = {"产品生命周期" },  notes = "删除产品生命周期")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlives/{productlife_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productlife_id") String productlife_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productlifeService.remove(productlife_id));
    }

    @PreAuthorize("@ProductLifeRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除产品生命周期", tags = {"产品生命周期" },  notes = "批量删除产品生命周期")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlives/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        productlifeService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductLifeRuntime.test(#productlife_id, 'READ')")
    @ApiOperation(value = "获取产品生命周期", tags = {"产品生命周期" },  notes = "获取产品生命周期")
	@RequestMapping(method = RequestMethod.GET, value = "/productlives/{productlife_id}")
    public ResponseEntity<ProductLifeDTO> get(@PathVariable("productlife_id") String productlife_id) {
        ProductLife domain = productlifeService.get(productlife_id);
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String,Integer> opprivs = productlifeRuntime.getOPPrivs(productlife_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductLifeRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品生命周期草稿", tags = {"产品生命周期" },  notes = "获取产品生命周期草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productlives/getdraft")
    public ResponseEntity<ProductLifeDTO> getDraft(ProductLifeDTO dto) {
        ProductLife domain = productlifeMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productlifeMapping.toDto(productlifeService.getDraft(domain)));
    }

    @PreAuthorize("@ProductLifeRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查产品生命周期", tags = {"产品生命周期" },  notes = "检查产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductLifeDTO productlifedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productlifeService.checkKey(productlifeMapping.toDomain(productlifedto)));
    }

    @PreAuthorize("@ProductLifeRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存产品生命周期", tags = {"产品生命周期" },  notes = "保存产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives/save")
    public ResponseEntity<ProductLifeDTO> save(@RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeMapping.toDomain(productlifedto);
        productlifeService.save(domain);
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String,Integer> opprivs = productlifeRuntime.getOPPrivs(domain.getProductlifeid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductLifeRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品生命周期" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchdefault")
	public ResponseEntity<List<ProductLifeDTO>> fetchdefault(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchDefault(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductLifeRuntime.quickTest('READ')")
	@ApiOperation(value = "获取GetRoadmap", tags = {"产品生命周期" } ,notes = "获取GetRoadmap")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchgetroadmap")
	public ResponseEntity<List<ProductLifeDTO>> fetchgetroadmap(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmap(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductLifeRuntime.quickTest('READ')")
	@ApiOperation(value = "获取获取产品路线", tags = {"产品生命周期" } ,notes = "获取获取产品路线")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchgetroadmaps")
	public ResponseEntity<List<ProductLifeDTO>> fetchgetroadmaps(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmapS(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductLifeRuntime.quickTest('READ')")
	@ApiOperation(value = "获取RoadMapYear", tags = {"产品生命周期" } ,notes = "获取RoadMapYear")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchroadmapyear")
	public ResponseEntity<List<ProductLifeDTO>> fetchroadmapyear(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchRoadMapYear(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productlives/{productlife_id}/{action}")
    public ResponseEntity<ProductLifeDTO> dynamicCall(@PathVariable("productlife_id") String productlife_id , @PathVariable("action") String action , @RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeService.dynamicCall(productlife_id, action, productlifeMapping.toDomain(productlifedto));
        productlifedto = productlifeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productlifedto);
    }
}

