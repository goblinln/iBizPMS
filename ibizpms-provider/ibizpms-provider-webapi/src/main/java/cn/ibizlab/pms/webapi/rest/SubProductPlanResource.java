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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductPlanRuntime;

@Slf4j
@Api(tags = {"产品计划" })
@RestController("WebApi-subproductplan")
@RequestMapping("")
public class SubProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public SubProductPlanMapping subproductplanMapping;


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
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


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划批关联BUG产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchLinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划批关联需求产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchLinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划批量解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划批量解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @ApiOperation(value = "根据产品计划EE激活计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划EE取消计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划EE关闭计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划EE完成计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划EE暂停计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划继续计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划EE开始计划产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取上一个计划的名称产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划导入计划模板产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'LINKBUG')")
    @ApiOperation(value = "根据产品计划关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'LINKSTORY')")
    @ApiOperation(value = "根据产品计划关联需求产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划关联任务产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划移动端产品计划计数器产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
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


    @ApiOperation(value = "根据产品计划解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品计划解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品计划获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanChildPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询子计划", tags = {"产品计划" } ,notes = "根据产品计划查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchchildplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanChildPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品计划查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchcurproductplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取数据集", tags = {"产品计划" } ,notes = "根据产品计划获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchcurproductplanstory")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询数据集", tags = {"产品计划" } ,notes = "根据产品计划查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchcurproductplanstory")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchdefault")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
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

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询默认查询", tags = {"产品计划" } ,notes = "根据产品计划查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchdefaultparent")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultParentByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanCodeListByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品计划查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchplancodelist")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanCodeListByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品计划查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchplantasks")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProductQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品计划查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchproductquery")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProductQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectAppByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询项目立项", tags = {"产品计划" } ,notes = "根据产品计划查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchprojectapp")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectAppByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品计划查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchprojectplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanRootPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询跟计划", tags = {"产品计划" } ,notes = "根据产品计划查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchrootplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanRootPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanTaskPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询任务计划", tags = {"产品计划" } ,notes = "根据产品计划查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/searchtaskplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanTaskPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立产品计划", tags = {"产品计划" },  notes = "根据产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新产品计划", tags = {"产品计划" },  notes = "根据产品更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除产品计划", tags = {"产品计划" },  notes = "根据产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取产品计划", tags = {"产品计划" },  notes = "根据产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品批关联BUG产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/batchlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品批关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/batchlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品批量解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查产品计划", tags = {"产品计划" },  notes = "根据产品检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @ApiOperation(value = "根据产品EE激活计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品EE取消计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品EE关闭计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品EE完成计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品EE暂停计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品继续计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品EE开始计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取上一个计划的名称产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品关联任务产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品移动端产品计划计数器产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品保存产品计划", tags = {"产品计划" },  notes = "根据产品保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/save")
    public ResponseEntity<SubProductPlanDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子计划", tags = {"产品计划" } ,notes = "根据产品获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanChildPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询子计划", tags = {"产品计划" } ,notes = "根据产品查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchchildplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanChildPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchcurproductplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品计划" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchcurproductplanstory")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询数据集", tags = {"产品计划" } ,notes = "根据产品查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchcurproductplanstory")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchdefault")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取默认查询", tags = {"产品计划" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchdefaultparent")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultParentByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询默认查询", tags = {"产品计划" } ,notes = "根据产品查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchdefaultparent")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultParentByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchplancodelist")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanTasksByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchplantasks")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanTasksByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchproductquery")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目立项", tags = {"产品计划" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目立项", tags = {"产品计划" } ,notes = "根据产品查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchprojectapp")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectAppByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchprojectplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取跟计划", tags = {"产品计划" } ,notes = "根据产品获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanRootPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询跟计划", tags = {"产品计划" } ,notes = "根据产品查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchrootplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanRootPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务计划", tags = {"产品计划" } ,notes = "根据产品获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanTaskPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询任务计划", tags = {"产品计划" } ,notes = "根据产品查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/subproductplans/searchtaskplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanTaskPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划批关联BUG产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchLinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划批关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchLinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划批量解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划批量解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @ApiOperation(value = "根据产品产品计划EE激活计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划EE取消计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划EE关闭计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划EE完成计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划EE暂停计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划继续计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划EE开始计划产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取上一个计划的名称产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划导入计划模板产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品计划关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品计划关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划关联任务产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划移动端产品计划计数器产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
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


    @ApiOperation(value = "根据产品产品计划解除关联Bug产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @ApiOperation(value = "根据产品产品计划解除关联需求产品计划", tags = {"产品计划" },  notes = "根据产品产品计划产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanChildPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询子计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchchildplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanChildPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品产品计划查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchcurproductplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取数据集", tags = {"产品计划" } ,notes = "根据产品产品计划获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplanstory")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanCurProductPlanStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询数据集", tags = {"产品计划" } ,notes = "根据产品产品计划查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchcurproductplanstory")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanCurProductPlanStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefault")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefaultparent")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanDefaultParentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanCodeListByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品产品计划查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplancodelist")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanCodeListByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanPlanTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplantasks")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanPlanTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProductQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchproductquery")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProductQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectAppByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询项目立项", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectapp")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectAppByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanProjectPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品产品计划查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanProjectPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanRootPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询跟计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchrootplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanRootPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchSubProductPlanTaskPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询任务计划", tags = {"产品计划" } ,notes = "根据产品产品计划查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/searchtaskplan")
	public ResponseEntity<Page<SubProductPlanDTO>> searchSubProductPlanTaskPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(subproductplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

