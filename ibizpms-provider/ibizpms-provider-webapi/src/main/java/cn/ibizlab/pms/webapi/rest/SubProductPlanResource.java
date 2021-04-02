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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"产品计划" })
@RestController("WebApi-subproductplan")
@RequestMapping("")
public class SubProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    @Lazy
    public SubProductPlanMapping subproductplanMapping;

    @ApiOperation(value = "根据产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划批量建立产品计划", tags = {"产品计划" },  notes = "根据产品计划批量建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> createBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
            domain.setParent(productplan_id);
        }
        productplanService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划批量更新产品计划", tags = {"产品计划" },  notes = "根据产品计划批量更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
            domain.setParent(productplan_id);
        }
        productplanService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }

    @ApiOperation(value = "根据产品计划批量删除产品计划", tags = {"产品计划" },  notes = "根据产品计划批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlan(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eeactiveplanbatch")
    public ResponseEntity<Boolean> eeActivePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeActivePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eecancelplanbatch")
    public ResponseEntity<Boolean> eeCancelPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeCancelPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eecloseplanbatch")
    public ResponseEntity<Boolean> eeClosePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeClosePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eefinishplanbatch")
    public ResponseEntity<Boolean> eeFinishPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeFinishPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eepauseplanbatch")
    public ResponseEntity<Boolean> eePausePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eePausePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eerestartplanbatch")
    public ResponseEntity<Boolean> eeRestartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeRestartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/eestartplanbatch")
    public ResponseEntity<Boolean> eeStartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeStartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/importplantempletbatch")
    public ResponseEntity<Boolean> importPlanTempletByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.importPlanTempletBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/linktaskbatch")
    public ResponseEntity<Boolean> linkTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkTaskBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "根据产品计划保存产品计划", tags = {"产品计划" },  notes = "根据产品计划保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/save")
    public ResponseEntity<SubProductPlanDTO> saveByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划批量保存产品计划", tags = {"产品计划" },  notes = "根据产品计划批量保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
             domain.setParent(productplan_id);
        }
        productplanService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	@ApiOperation(value = "根据产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品计划获取子计划")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanChildPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询子计划", tags = {"产品计划" } ,notes = "根据产品计划查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchchildplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanChildPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品计划查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchcurproductplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchdefault")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取默认查询", tags = {"产品计划" } ,notes = "根据产品计划获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchdefaultparent")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultParentByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询默认查询", tags = {"产品计划" } ,notes = "根据产品计划查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchdefaultparent")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultParentByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanCodeListByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品计划查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchplancodelist")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanCodeListByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品计划查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchplantasks")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectAppByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询项目立项", tags = {"产品计划" } ,notes = "根据产品计划查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchprojectapp")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectAppByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品计划查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchprojectplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanRootPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询跟计划", tags = {"产品计划" } ,notes = "根据产品计划查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchrootplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanRootPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.GET , value="/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanTaskPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品计划查询任务计划", tags = {"产品计划" } ,notes = "根据产品计划查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchtaskplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanTaskPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划批量建立产品计划", tags = {"产品计划" },  notes = "根据产品产品计划批量建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
            domain.setParent(productplan_id);
        }
        productplanService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划批量更新产品计划", tags = {"产品计划" },  notes = "根据产品产品计划批量更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
            domain.setParent(productplan_id);
        }
        productplanService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }

    @ApiOperation(value = "根据产品产品计划批量删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlan(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eeactiveplanbatch")
    public ResponseEntity<Boolean> eeActivePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeActivePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eecancelplanbatch")
    public ResponseEntity<Boolean> eeCancelPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeCancelPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eecloseplanbatch")
    public ResponseEntity<Boolean> eeClosePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeClosePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eefinishplanbatch")
    public ResponseEntity<Boolean> eeFinishPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeFinishPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eepauseplanbatch")
    public ResponseEntity<Boolean> eePausePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eePausePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eerestartplanbatch")
    public ResponseEntity<Boolean> eeRestartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeRestartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/eestartplanbatch")
    public ResponseEntity<Boolean> eeStartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.eeStartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/importplantempletbatch")
    public ResponseEntity<Boolean> importPlanTempletByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.importPlanTempletBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/linktaskbatch")
    public ResponseEntity<Boolean> linkTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.linkTaskBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "根据产品产品计划保存产品计划", tags = {"产品计划" },  notes = "根据产品产品计划保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/save")
    public ResponseEntity<SubProductPlanDTO> saveByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划批量保存产品计划", tags = {"产品计划" },  notes = "根据产品产品计划批量保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domainlist=subproductplanMapping.toDomain(subproductplandtos);
        for(ProductPlan domain:domainlist){
             domain.setParent(productplan_id);
        }
        productplanService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody List<SubProductPlanDTO> subproductplandtos) {
        List<ProductPlan> domains = subproductplanMapping.toDomain(subproductplandtos);
        boolean result = productplanService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	@ApiOperation(value = "根据产品产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取子计划")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanChildPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询子计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchchildplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanChildPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品产品计划查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchcurproductplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefault")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefaultparent")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultParentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefaultparent")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultParentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanCodeListByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品产品计划查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplancodelist")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanCodeListByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplantasks")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectAppByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询项目立项", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectapp")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectAppByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanRootPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询跟计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchrootplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanRootPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanTaskPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品产品计划查询任务计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchtaskplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanTaskPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

