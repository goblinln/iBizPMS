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
import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.service.IProductStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductStatsRuntime;

@Slf4j
@Api(tags = {"产品统计" })
@RestController("WebApi-productstats")
@RequestMapping("")
public class ProductStatsResource {

    @Autowired
    public IProductStatsService productstatsService;

    @Autowired
    public ProductStatsRuntime productstatsRuntime;

    @Autowired
    @Lazy
    public ProductStatsMapping productstatsMapping;

    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'CREATE')")
    @ApiOperation(value = "新建产品统计", tags = {"产品统计" },  notes = "新建产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats")
    @Transactional
    public ResponseEntity<ProductStatsDTO> create(@Validated @RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
		productstatsService.create(domain);
        if(!productstatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductStatsDTO dto = productstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = productstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTSTATS', #productstats_id, 'UPDATE')")
    @ApiOperation(value = "更新产品统计", tags = {"产品统计" },  notes = "更新产品统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/productstats/{productstats_id}")
    @Transactional
    public ResponseEntity<ProductStatsDTO> update(@PathVariable("productstats_id") Long productstats_id, @RequestBody ProductStatsDTO productstatsdto) {
		ProductStats domain  = productstatsMapping.toDomain(productstatsdto);
        domain.setId(productstats_id);
		productstatsService.update(domain );
        if(!productstatsRuntime.test(productstats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductStatsDTO dto = productstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = productstatsRuntime.getOPPrivs(productstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTSTATS', #productstats_id, 'DELETE')")
    @ApiOperation(value = "删除产品统计", tags = {"产品统计" },  notes = "删除产品统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productstats/{productstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productstats_id") Long productstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productstatsService.remove(productstats_id));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'DELETE')")
    @ApiOperation(value = "批量删除产品统计", tags = {"产品统计" },  notes = "批量删除产品统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTSTATS', #productstats_id, 'NONE')")
    @ApiOperation(value = "获取产品统计", tags = {"产品统计" },  notes = "获取产品统计")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/{productstats_id}")
    public ResponseEntity<ProductStatsDTO> get(@PathVariable("productstats_id") Long productstats_id) {
        ProductStats domain = productstatsService.get(productstats_id);
        ProductStatsDTO dto = productstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = productstatsRuntime.getOPPrivs(productstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'CREATE')")
    @ApiOperation(value = "获取产品统计草稿", tags = {"产品统计" },  notes = "获取产品统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/getdraft")
    public ResponseEntity<ProductStatsDTO> getDraft(ProductStatsDTO dto) {
        ProductStats domain = productstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsMapping.toDto(productstatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'CREATE')")
    @ApiOperation(value = "检查产品统计", tags = {"产品统计" },  notes = "检查产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductStatsDTO productstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productstatsService.checkKey(productstatsMapping.toDomain(productstatsdto)));
    }

    @PreAuthorize("test('IBZ_PRODUCTSTATS', #productstats_id, 'NONE')")
    @ApiOperation(value = "获取测试统计详情", tags = {"产品统计" },  notes = "获取测试统计详情")
	@RequestMapping(method = RequestMethod.GET, value = "/productstats/{productstats_id}/getteststats")
    public ResponseEntity<ProductStatsDTO> getTestStats(@PathVariable("productstats_id") Long productstats_id, ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
        domain.setId(productstats_id);
        domain = productstatsService.getTestStats(domain);
        productstatsdto = productstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = productstatsRuntime.getOPPrivs(domain.getId());
        productstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsdto);
    }


    @PreAuthorize("@ProductStatsRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存产品统计", tags = {"产品统计" },  notes = "保存产品统计")
	@RequestMapping(method = RequestMethod.POST, value = "/productstats/save")
    public ResponseEntity<ProductStatsDTO> save(@RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsMapping.toDomain(productstatsdto);
        productstatsService.save(domain);
        ProductStatsDTO dto = productstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = productstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'NONE')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品统计" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/fetchdefault")
	public ResponseEntity<List<ProductStatsDTO>> fetchdefault(@RequestBody ProductStatsSearchContext context) {
        Page<ProductStats> domains = productstatsService.searchDefault(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'NONE')")
	@ApiOperation(value = "获取未关闭产品", tags = {"产品统计" } ,notes = "获取未关闭产品")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/fetchnoopenproduct")
	public ResponseEntity<List<ProductStatsDTO>> fetchnoopenproduct(@RequestBody ProductStatsSearchContext context) {
        Page<ProductStats> domains = productstatsService.searchNoOpenProduct(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'NONE')")
	@ApiOperation(value = "获取产品质量表", tags = {"产品统计" } ,notes = "获取产品质量表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/fetchprodctquantigird")
	public ResponseEntity<List<ProductStatsDTO>> fetchprodctquantigird(@RequestBody ProductStatsSearchContext context) {
        Page<ProductStats> domains = productstatsService.searchProdctQuantiGird(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'NONE')")
	@ApiOperation(value = "获取产品投入表", tags = {"产品统计" } ,notes = "获取产品投入表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/fetchproductinputtable")
	public ResponseEntity<List<ProductStatsDTO>> fetchproductinputtable(@RequestBody ProductStatsSearchContext context) {
        Page<ProductStats> domains = productstatsService.searchProductInputTable(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSTATS', 'NONE')")
	@ApiOperation(value = "获取产品完成统计表", tags = {"产品统计" } ,notes = "获取产品完成统计表")
    @RequestMapping(method= RequestMethod.POST , value="/productstats/fetchproductcompletionstatistics")
	public ResponseEntity<List<ProductStatsDTO>> fetchproductcompletionstatistics(@RequestBody ProductStatsSearchContext context) {
        Page<ProductStats> domains = productstatsService.searchProductcompletionstatistics(context) ;
        List<ProductStatsDTO> list = productstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productstats/{productstats_id}/{action}")
    public ResponseEntity<ProductStatsDTO> dynamicCall(@PathVariable("productstats_id") Long productstats_id , @PathVariable("action") String action , @RequestBody ProductStatsDTO productstatsdto) {
        ProductStats domain = productstatsService.dynamicCall(productstats_id, action, productstatsMapping.toDomain(productstatsdto));
        productstatsdto = productstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstatsdto);
    }
}

