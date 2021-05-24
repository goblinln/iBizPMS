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
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.core.ibiz.service.IProductPlanActionService;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductPlanActionRuntime;

@Slf4j
@Api(tags = {"产品计划日志" })
@RestController("WebApi-productplanaction")
@RequestMapping("")
public class ProductPlanActionResource {

    @Autowired
    public IProductPlanActionService productplanactionService;

    @Autowired
    public ProductPlanActionRuntime productplanactionRuntime;

    @Autowired
    @Lazy
    public ProductPlanActionMapping productplanactionMapping;

    @PreAuthorize("@ProductPlanActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品计划日志", tags = {"产品计划日志" },  notes = "新建产品计划日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions")
    @Transactional
    public ResponseEntity<ProductPlanActionDTO> create(@Validated @RequestBody ProductPlanActionDTO productplanactiondto) {
        ProductPlanAction domain = productplanactionMapping.toDomain(productplanactiondto);
		productplanactionService.create(domain);
        if(!productplanactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductPlanActionDTO dto = productplanactionMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品计划日志", tags = {"产品计划日志" },  notes = "批量新建产品计划日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductPlanActionDTO> productplanactiondtos) {
        productplanactionService.createBatch(productplanactionMapping.toDomain(productplanactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanActionRuntime.test(#productplanaction_id,'UPDATE')")
    @ApiOperation(value = "更新产品计划日志", tags = {"产品计划日志" },  notes = "更新产品计划日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanactions/{productplanaction_id}")
    @Transactional
    public ResponseEntity<ProductPlanActionDTO> update(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanActionDTO productplanactiondto) {
		ProductPlanAction domain  = productplanactionMapping.toDomain(productplanactiondto);
        domain.setId(productplanaction_id);
		productplanactionService.update(domain );
        if(!productplanactionRuntime.test(productplanaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductPlanActionDTO dto = productplanactionMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanactionRuntime.getOPPrivs(productplanaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品计划日志", tags = {"产品计划日志" },  notes = "批量更新产品计划日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductPlanActionDTO> productplanactiondtos) {
        productplanactionService.updateBatch(productplanactionMapping.toDomain(productplanactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanActionRuntime.test(#productplanaction_id,'DELETE')")
    @ApiOperation(value = "删除产品计划日志", tags = {"产品计划日志" },  notes = "删除产品计划日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanactions/{productplanaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplanaction_id") Long productplanaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanactionService.remove(productplanaction_id));
    }

    @PreAuthorize("@ProductPlanActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品计划日志", tags = {"产品计划日志" },  notes = "批量删除产品计划日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productplanactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanActionRuntime.test(#productplanaction_id,'READ')")
    @ApiOperation(value = "获取产品计划日志", tags = {"产品计划日志" },  notes = "获取产品计划日志")
	@RequestMapping(method = RequestMethod.GET, value = "/productplanactions/{productplanaction_id}")
    public ResponseEntity<ProductPlanActionDTO> get(@PathVariable("productplanaction_id") Long productplanaction_id) {
        ProductPlanAction domain = productplanactionService.get(productplanaction_id);
        ProductPlanActionDTO dto = productplanactionMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanactionRuntime.getOPPrivs(productplanaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品计划日志草稿", tags = {"产品计划日志" },  notes = "获取产品计划日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productplanactions/getdraft")
    public ResponseEntity<ProductPlanActionDTO> getDraft(ProductPlanActionDTO dto) {
        ProductPlanAction domain = productplanactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productplanactionMapping.toDto(productplanactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品计划日志", tags = {"产品计划日志" },  notes = "检查产品计划日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductPlanActionDTO productplanactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanactionService.checkKey(productplanactionMapping.toDomain(productplanactiondto)));
    }

    @ApiOperation(value = "保存产品计划日志", tags = {"产品计划日志" },  notes = "保存产品计划日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/save")
    public ResponseEntity<ProductPlanActionDTO> save(@RequestBody ProductPlanActionDTO productplanactiondto) {
        ProductPlanAction domain = productplanactionMapping.toDomain(productplanactiondto);
        productplanactionService.save(domain);
        ProductPlanActionDTO dto = productplanactionMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存产品计划日志", tags = {"产品计划日志" },  notes = "批量保存产品计划日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductPlanActionDTO> productplanactiondtos) {
        productplanactionService.saveBatch(productplanactionMapping.toDomain(productplanactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品计划日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanactions/fetchdefault")
	public ResponseEntity<List<ProductPlanActionDTO>> fetchdefault(@RequestBody ProductPlanActionSearchContext context) {
        productplanactionRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanAction> domains = productplanactionService.searchDefault(context) ;
        List<ProductPlanActionDTO> list = productplanactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品计划日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanactions/searchdefault")
	public ResponseEntity<Page<ProductPlanActionDTO>> searchDefault(@RequestBody ProductPlanActionSearchContext context) {
        productplanactionRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanAction> domains = productplanactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/{action}")
    public ResponseEntity<ProductPlanActionDTO> dynamicCall(@PathVariable("productplanaction_id") Long productplanaction_id , @PathVariable("action") String action , @RequestBody ProductPlanActionDTO productplanactiondto) {
        ProductPlanAction domain = productplanactionService.dynamicCall(productplanaction_id, action, productplanactionMapping.toDomain(productplanactiondto));
        productplanactiondto = productplanactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanactiondto);
    }
}

