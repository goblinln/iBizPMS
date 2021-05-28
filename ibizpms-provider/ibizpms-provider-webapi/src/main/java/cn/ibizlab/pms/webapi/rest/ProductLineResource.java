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
import cn.ibizlab.pms.core.ibiz.domain.ProductLine;
import cn.ibizlab.pms.core.ibiz.service.IProductLineService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLineSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductLineRuntime;

@Slf4j
@Api(tags = {"产品线（废弃）" })
@RestController("WebApi-productline")
@RequestMapping("")
public class ProductLineResource {

    @Autowired
    public IProductLineService productlineService;

    @Autowired
    public ProductLineRuntime productlineRuntime;

    @Autowired
    @Lazy
    public ProductLineMapping productlineMapping;

    @PreAuthorize("@ProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品线（废弃）", tags = {"产品线（废弃）" },  notes = "新建产品线（废弃）")
	@RequestMapping(method = RequestMethod.POST, value = "/productlines")
    @Transactional
    public ResponseEntity<ProductLineDTO> create(@Validated @RequestBody ProductLineDTO productlinedto) {
        ProductLine domain = productlineMapping.toDomain(productlinedto);
		productlineService.create(domain);
        if(!productlineRuntime.test(domain.getProductlineid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = productlineRuntime.getOPPrivs(domain.getProductlineid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "productline" , versionfield = "updatedate")
    @PreAuthorize("@ProductLineRuntime.test(#productline_id, 'UPDATE')")
    @ApiOperation(value = "更新产品线（废弃）", tags = {"产品线（废弃）" },  notes = "更新产品线（废弃）")
	@RequestMapping(method = RequestMethod.PUT, value = "/productlines/{productline_id}")
    @Transactional
    public ResponseEntity<ProductLineDTO> update(@PathVariable("productline_id") String productline_id, @RequestBody ProductLineDTO productlinedto) {
		ProductLine domain  = productlineMapping.toDomain(productlinedto);
        domain.setProductlineid(productline_id);
		productlineService.update(domain );
        if(!productlineRuntime.test(productline_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = productlineRuntime.getOPPrivs(productline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductLineRuntime.test(#productline_id, 'DELETE')")
    @ApiOperation(value = "删除产品线（废弃）", tags = {"产品线（废弃）" },  notes = "删除产品线（废弃）")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlines/{productline_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productline_id") String productline_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productlineService.remove(productline_id));
    }

    @PreAuthorize("@ProductLineRuntime.test(#ids, 'DELETE')")
    @ApiOperation(value = "批量删除产品线（废弃）", tags = {"产品线（废弃）" },  notes = "批量删除产品线（废弃）")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlines/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        productlineService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductLineRuntime.test(#productline_id, 'READ')")
    @ApiOperation(value = "获取产品线（废弃）", tags = {"产品线（废弃）" },  notes = "获取产品线（废弃）")
	@RequestMapping(method = RequestMethod.GET, value = "/productlines/{productline_id}")
    public ResponseEntity<ProductLineDTO> get(@PathVariable("productline_id") String productline_id) {
        ProductLine domain = productlineService.get(productline_id);
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = productlineRuntime.getOPPrivs(productline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品线（废弃）草稿", tags = {"产品线（废弃）" },  notes = "获取产品线（废弃）草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productlines/getdraft")
    public ResponseEntity<ProductLineDTO> getDraft(ProductLineDTO dto) {
        ProductLine domain = productlineMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productlineMapping.toDto(productlineService.getDraft(domain)));
    }

    @PreAuthorize("@ProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查产品线（废弃）", tags = {"产品线（废弃）" },  notes = "检查产品线（废弃）")
	@RequestMapping(method = RequestMethod.POST, value = "/productlines/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductLineDTO productlinedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productlineService.checkKey(productlineMapping.toDomain(productlinedto)));
    }

    @PreAuthorize("@ProductLineRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存产品线（废弃）", tags = {"产品线（废弃）" },  notes = "保存产品线（废弃）")
	@RequestMapping(method = RequestMethod.POST, value = "/productlines/save")
    public ResponseEntity<ProductLineDTO> save(@RequestBody ProductLineDTO productlinedto) {
        ProductLine domain = productlineMapping.toDomain(productlinedto);
        productlineService.save(domain);
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = productlineRuntime.getOPPrivs(domain.getProductlineid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductLineRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品线（废弃）" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productlines/fetchdefault")
	public ResponseEntity<List<ProductLineDTO>> fetchdefault(@RequestBody ProductLineSearchContext context) {
        productlineRuntime.addAuthorityConditions(context,"READ");
        Page<ProductLine> domains = productlineService.searchDefault(context) ;
        List<ProductLineDTO> list = productlineMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productlines/{productline_id}/{action}")
    public ResponseEntity<ProductLineDTO> dynamicCall(@PathVariable("productline_id") String productline_id , @PathVariable("action") String action , @RequestBody ProductLineDTO productlinedto) {
        ProductLine domain = productlineService.dynamicCall(productline_id, action, productlineMapping.toDomain(productlinedto));
        productlinedto = productlineMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productlinedto);
    }
}

