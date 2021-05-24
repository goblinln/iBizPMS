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
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanHistory;
import cn.ibizlab.pms.core.ibiz.service.IProductPlanHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductPlanHistoryRuntime;

@Slf4j
@Api(tags = {"产品计划历史" })
@RestController("WebApi-productplanhistory")
@RequestMapping("")
public class ProductPlanHistoryResource {

    @Autowired
    public IProductPlanHistoryService productplanhistoryService;

    @Autowired
    public ProductPlanHistoryRuntime productplanhistoryRuntime;

    @Autowired
    @Lazy
    public ProductPlanHistoryMapping productplanhistoryMapping;

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品计划历史", tags = {"产品计划历史" },  notes = "新建产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanhistories")
    @Transactional
    public ResponseEntity<ProductPlanHistoryDTO> create(@Validated @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
		productplanhistoryService.create(domain);
        if(!productplanhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品计划历史", tags = {"产品计划历史" },  notes = "批量新建产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        productplanhistoryService.createBatch(productplanhistoryMapping.toDomain(productplanhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'UPDATE')")
    @ApiOperation(value = "更新产品计划历史", tags = {"产品计划历史" },  notes = "更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanhistories/{productplanhistory_id}")
    @Transactional
    public ResponseEntity<ProductPlanHistoryDTO> update(@PathVariable("productplanhistory_id") Long productplanhistory_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
		ProductPlanHistory domain  = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setId(productplanhistory_id);
		productplanhistoryService.update(domain );
        if(!productplanhistoryRuntime.test(productplanhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanhistoryRuntime.getOPPrivs(productplanhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品计划历史", tags = {"产品计划历史" },  notes = "批量更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        productplanhistoryService.updateBatch(productplanhistoryMapping.toDomain(productplanhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'DELETE')")
    @ApiOperation(value = "删除产品计划历史", tags = {"产品计划历史" },  notes = "删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplanhistory_id") Long productplanhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.remove(productplanhistory_id));
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品计划历史", tags = {"产品计划历史" },  notes = "批量删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productplanhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'READ')")
    @ApiOperation(value = "获取产品计划历史", tags = {"产品计划历史" },  notes = "获取产品计划历史")
	@RequestMapping(method = RequestMethod.GET, value = "/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> get(@PathVariable("productplanhistory_id") Long productplanhistory_id) {
        ProductPlanHistory domain = productplanhistoryService.get(productplanhistory_id);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanhistoryRuntime.getOPPrivs(productplanhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品计划历史草稿", tags = {"产品计划历史" },  notes = "获取产品计划历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productplanhistories/getdraft")
    public ResponseEntity<ProductPlanHistoryDTO> getDraft(ProductPlanHistoryDTO dto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(productplanhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品计划历史", tags = {"产品计划历史" },  notes = "检查产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.checkKey(productplanhistoryMapping.toDomain(productplanhistorydto)));
    }

    @ApiOperation(value = "保存产品计划历史", tags = {"产品计划历史" },  notes = "保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanhistories/save")
    public ResponseEntity<ProductPlanHistoryDTO> save(@RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        productplanhistoryService.save(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = productplanhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存产品计划历史", tags = {"产品计划历史" },  notes = "批量保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        productplanhistoryService.saveBatch(productplanhistoryMapping.toDomain(productplanhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品计划历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanhistories/fetchdefault")
	public ResponseEntity<List<ProductPlanHistoryDTO>> fetchdefault(@RequestBody ProductPlanHistorySearchContext context) {
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
        List<ProductPlanHistoryDTO> list = productplanhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品计划历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanhistories/searchdefault")
	public ResponseEntity<Page<ProductPlanHistoryDTO>> searchDefault(@RequestBody ProductPlanHistorySearchContext context) {
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplanhistories/{productplanhistory_id}/{action}")
    public ResponseEntity<ProductPlanHistoryDTO> dynamicCall(@PathVariable("productplanhistory_id") Long productplanhistory_id , @PathVariable("action") String action , @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryService.dynamicCall(productplanhistory_id, action, productplanhistoryMapping.toDomain(productplanhistorydto));
        productplanhistorydto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistorydto);
    }
    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划日志建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/productplanhistories")
    public ResponseEntity<ProductPlanHistoryDTO> createByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
		productplanhistoryService.create(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划日志批量建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志批量建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划日志更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> updateByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        domain.setId(productplanhistory_id);
		productplanhistoryService.update(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品计划日志批量更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志批量更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'DELETE')")
    @ApiOperation(value = "根据产品计划日志删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<Boolean> removeByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.remove(productplanhistory_id));
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品计划日志批量删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志批量删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlanAction(@RequestBody List<Long> ids) {
        productplanhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'READ')")
    @ApiOperation(value = "根据产品计划日志获取产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志获取产品计划历史")
	@RequestMapping(method = RequestMethod.GET, value = "/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> getByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
        ProductPlanHistory domain = productplanhistoryService.get(productplanhistory_id);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划日志获取产品计划历史草稿", tags = {"产品计划历史" },  notes = "根据产品计划日志获取产品计划历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplanactions/{productplanaction_id}/productplanhistories/getdraft")
    public ResponseEntity<ProductPlanHistoryDTO> getDraftByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, ProductPlanHistoryDTO dto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(dto);
        domain.setAction(productplanaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(productplanhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划日志检查产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志检查产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/productplanhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.checkKey(productplanhistoryMapping.toDomain(productplanhistorydto)));
    }

    @ApiOperation(value = "根据产品计划日志保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/productplanhistories/save")
    public ResponseEntity<ProductPlanHistoryDTO> saveByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        productplanhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划日志批量保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划日志批量保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplanactions/{productplanaction_id}/productplanhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
             domain.setAction(productplanaction_id);
        }
        productplanhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划日志获取数据集", tags = {"产品计划历史" } ,notes = "根据产品计划日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanactions/{productplanaction_id}/productplanhistories/fetchdefault")
	public ResponseEntity<List<ProductPlanHistoryDTO>> fetchProductPlanHistoryDefaultByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id,@RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
        List<ProductPlanHistoryDTO> list = productplanhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划日志查询数据集", tags = {"产品计划历史" } ,notes = "根据产品计划日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplanactions/{productplanaction_id}/productplanhistories/searchdefault")
	public ResponseEntity<Page<ProductPlanHistoryDTO>> searchProductPlanHistoryDefaultByProductPlanAction(@PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划产品计划日志建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories")
    public ResponseEntity<ProductPlanHistoryDTO> createByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
		productplanhistoryService.create(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品计划产品计划日志批量建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志批量建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划产品计划日志更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> updateByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        domain.setId(productplanhistory_id);
		productplanhistoryService.update(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品计划产品计划日志批量更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志批量更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'DELETE')")
    @ApiOperation(value = "根据产品计划产品计划日志删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<Boolean> removeByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.remove(productplanhistory_id));
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品计划产品计划日志批量删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志批量删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlanProductPlanAction(@RequestBody List<Long> ids) {
        productplanhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'READ')")
    @ApiOperation(value = "根据产品计划产品计划日志获取产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志获取产品计划历史")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> getByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
        ProductPlanHistory domain = productplanhistoryService.get(productplanhistory_id);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划产品计划日志获取产品计划历史草稿", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志获取产品计划历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/getdraft")
    public ResponseEntity<ProductPlanHistoryDTO> getDraftByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, ProductPlanHistoryDTO dto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(dto);
        domain.setAction(productplanaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(productplanhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划产品计划日志检查产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志检查产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.checkKey(productplanhistoryMapping.toDomain(productplanhistorydto)));
    }

    @ApiOperation(value = "根据产品计划产品计划日志保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/save")
    public ResponseEntity<ProductPlanHistoryDTO> saveByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        productplanhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划产品计划日志批量保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品计划产品计划日志批量保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
             domain.setAction(productplanaction_id);
        }
        productplanhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划产品计划日志获取数据集", tags = {"产品计划历史" } ,notes = "根据产品计划产品计划日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/fetchdefault")
	public ResponseEntity<List<ProductPlanHistoryDTO>> fetchProductPlanHistoryDefaultByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id,@RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
        List<ProductPlanHistoryDTO> list = productplanhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品计划产品计划日志查询数据集", tags = {"产品计划历史" } ,notes = "根据产品计划产品计划日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/searchdefault")
	public ResponseEntity<Page<ProductPlanHistoryDTO>> searchProductPlanHistoryDefaultByProductPlanProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories")
    public ResponseEntity<ProductPlanHistoryDTO> createByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
		productplanhistoryService.create(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志批量建立产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志批量建立产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> updateByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        domain.setId(productplanhistory_id);
		productplanhistoryService.update(domain);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志批量更新产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志批量更新产品计划历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
            domain.setAction(productplanaction_id);
        }
        productplanhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<Boolean> removeByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.remove(productplanhistory_id));
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品产品计划产品计划日志批量删除产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志批量删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlanProductPlanAction(@RequestBody List<Long> ids) {
        productplanhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'READ')")
    @ApiOperation(value = "根据产品产品计划产品计划日志获取产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志获取产品计划历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<ProductPlanHistoryDTO> getByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @PathVariable("productplanhistory_id") Long productplanhistory_id) {
        ProductPlanHistory domain = productplanhistoryService.get(productplanhistory_id);
        ProductPlanHistoryDTO dto = productplanhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划产品计划日志获取产品计划历史草稿", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志获取产品计划历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/getdraft")
    public ResponseEntity<ProductPlanHistoryDTO> getDraftByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, ProductPlanHistoryDTO dto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(dto);
        domain.setAction(productplanaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(productplanhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划产品计划日志检查产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志检查产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.checkKey(productplanhistoryMapping.toDomain(productplanhistorydto)));
    }

    @ApiOperation(value = "根据产品产品计划产品计划日志保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/save")
    public ResponseEntity<ProductPlanHistoryDTO> saveByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistoryDTO productplanhistorydto) {
        ProductPlanHistory domain = productplanhistoryMapping.toDomain(productplanhistorydto);
        domain.setAction(productplanaction_id);
        productplanhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划产品计划日志批量保存产品计划历史", tags = {"产品计划历史" },  notes = "根据产品产品计划产品计划日志批量保存产品计划历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody List<ProductPlanHistoryDTO> productplanhistorydtos) {
        List<ProductPlanHistory> domainlist=productplanhistoryMapping.toDomain(productplanhistorydtos);
        for(ProductPlanHistory domain:domainlist){
             domain.setAction(productplanaction_id);
        }
        productplanhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品产品计划产品计划日志获取数据集", tags = {"产品计划历史" } ,notes = "根据产品产品计划产品计划日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/fetchdefault")
	public ResponseEntity<List<ProductPlanHistoryDTO>> fetchProductPlanHistoryDefaultByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id,@RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
        List<ProductPlanHistoryDTO> list = productplanhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品产品计划产品计划日志查询数据集", tags = {"产品计划历史" } ,notes = "根据产品产品计划产品计划日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/productplanactions/{productplanaction_id}/productplanhistories/searchdefault")
	public ResponseEntity<Page<ProductPlanHistoryDTO>> searchProductPlanHistoryDefaultByProductProductPlanProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("productplanaction_id") Long productplanaction_id, @RequestBody ProductPlanHistorySearchContext context) {
        context.setN_action_eq(productplanaction_id);
        productplanhistoryRuntime.addAuthorityConditions(context,"READ");
        Page<ProductPlanHistory> domains = productplanhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

