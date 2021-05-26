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


    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'DELETE')")
    @ApiOperation(value = "删除产品计划历史", tags = {"产品计划历史" },  notes = "删除产品计划历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplanhistories/{productplanhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplanhistory_id") Long productplanhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanhistoryService.remove(productplanhistory_id));
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

    @PreAuthorize("@ProductPlanHistoryRuntime.test(#productplanhistory_id,'CREATE')")
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
}

