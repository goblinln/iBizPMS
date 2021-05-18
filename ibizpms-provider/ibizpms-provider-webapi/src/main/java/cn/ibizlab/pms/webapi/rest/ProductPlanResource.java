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
@RestController("WebApi-productplan")
@RequestMapping("")
public class ProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public ProductPlanMapping productplanMapping;

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品计划", tags = {"产品计划" },  notes = "新建产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans")
    @Transactional
    public ResponseEntity<ProductPlanDTO> create(@Validated @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
		productplanService.create(domain);
        if(!productplanRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品计划", tags = {"产品计划" },  notes = "批量新建产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        productplanService.createBatch(productplanMapping.toDomain(productplandtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "更新产品计划", tags = {"产品计划" },  notes = "更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}")
    @Transactional
    public ResponseEntity<ProductPlanDTO> update(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
		ProductPlan domain  = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
		productplanService.update(domain );
        if(!productplanRuntime.test(productplan_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品计划", tags = {"产品计划" },  notes = "批量更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        productplanService.updateBatch(productplanMapping.toDomain(productplandtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "删除产品计划", tags = {"产品计划" },  notes = "删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplan_id") Long productplan_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品计划", tags = {"产品计划" },  notes = "批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "获取产品计划", tags = {"产品计划" },  notes = "获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> get(@PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品计划草稿", tags = {"产品计划" },  notes = "获取产品计划草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraft(ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "批量解除关联Bug", tags = {"产品计划" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[批量解除关联Bug]", tags = {"产品计划" },  notes = "批量处理[批量解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量解除关联需求", tags = {"产品计划" },  notes = "批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[批量解除关联需求]", tags = {"产品计划" },  notes = "批量处理[批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查产品计划", tags = {"产品计划" },  notes = "检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductPlanDTO productplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(productplanMapping.toDomain(productplandto)));
    }

    @ApiOperation(value = "EE激活计划", tags = {"产品计划" },  notes = "EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eeactiveplan")
    public ResponseEntity<ProductPlanDTO> eeActivePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeActivePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE激活计划]", tags = {"产品计划" },  notes = "批量处理[EE激活计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eeactiveplanbatch")
    public ResponseEntity<Boolean> eeActivePlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeActivePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "EE取消计划", tags = {"产品计划" },  notes = "EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eecancelplan")
    public ResponseEntity<ProductPlanDTO> eeCancelPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeCancelPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE取消计划]", tags = {"产品计划" },  notes = "批量处理[EE取消计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eecancelplanbatch")
    public ResponseEntity<Boolean> eeCancelPlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeCancelPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "EE关闭计划", tags = {"产品计划" },  notes = "EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eecloseplan")
    public ResponseEntity<ProductPlanDTO> eeClosePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeClosePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE关闭计划]", tags = {"产品计划" },  notes = "批量处理[EE关闭计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eecloseplanbatch")
    public ResponseEntity<Boolean> eeClosePlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeClosePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "EE完成计划", tags = {"产品计划" },  notes = "EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eefinishplan")
    public ResponseEntity<ProductPlanDTO> eeFinishPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeFinishPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE完成计划]", tags = {"产品计划" },  notes = "批量处理[EE完成计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eefinishplanbatch")
    public ResponseEntity<Boolean> eeFinishPlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeFinishPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "EE暂停计划", tags = {"产品计划" },  notes = "EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eepauseplan")
    public ResponseEntity<ProductPlanDTO> eePausePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eePausePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE暂停计划]", tags = {"产品计划" },  notes = "批量处理[EE暂停计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eepauseplanbatch")
    public ResponseEntity<Boolean> eePausePlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eePausePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "继续计划", tags = {"产品计划" },  notes = "继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eerestartplan")
    public ResponseEntity<ProductPlanDTO> eeRestartPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeRestartPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[继续计划]", tags = {"产品计划" },  notes = "批量处理[继续计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eerestartplanbatch")
    public ResponseEntity<Boolean> eeRestartPlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeRestartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "EE开始计划", tags = {"产品计划" },  notes = "EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eestartplan")
    public ResponseEntity<ProductPlanDTO> eeStartPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeStartPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[EE开始计划]", tags = {"产品计划" },  notes = "批量处理[EE开始计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/eestartplanbatch")
    public ResponseEntity<Boolean> eeStartPlanBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeStartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "获取上一个计划的名称", tags = {"产品计划" },  notes = "获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/getoldplanname")
    public ResponseEntity<ProductPlanDTO> getOldPlanName(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.getOldPlanName(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @ApiOperation(value = "导入计划模板", tags = {"产品计划" },  notes = "导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTemplet(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[导入计划模板]", tags = {"产品计划" },  notes = "批量处理[导入计划模板]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/importplantempletbatch")
    public ResponseEntity<Boolean> importPlanTempletBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.importPlanTempletBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联Bug", tags = {"产品计划" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[关联Bug]", tags = {"产品计划" },  notes = "批量处理[关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/linkbugbatch")
    public ResponseEntity<Boolean> linkBugBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联需求", tags = {"产品计划" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[关联需求]", tags = {"产品计划" },  notes = "批量处理[关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联任务", tags = {"产品计划" },  notes = "关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linktask")
    public ResponseEntity<ProductPlanDTO> linkTask(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkTask(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[关联任务]", tags = {"产品计划" },  notes = "批量处理[关联任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/linktaskbatch")
    public ResponseEntity<Boolean> linkTaskBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkTaskBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "移动端产品计划计数器", tags = {"产品计划" },  notes = "移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/mobproductplancounter")
    public ResponseEntity<ProductPlanDTO> mobProductPlanCounter(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @ApiOperation(value = "保存产品计划", tags = {"产品计划" },  notes = "保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/save")
    public ResponseEntity<ProductPlanDTO> save(@RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存产品计划", tags = {"产品计划" },  notes = "批量保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        productplanService.saveBatch(productplanMapping.toDomain(productplandtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "解除关联Bug", tags = {"产品计划" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[解除关联Bug]", tags = {"产品计划" },  notes = "批量处理[解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "解除关联需求", tags = {"产品计划" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[解除关联需求]", tags = {"产品计划" },  notes = "批量处理[解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryBatch(@RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取子计划", tags = {"产品计划" } ,notes = "获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchchildplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchchildplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子计划", tags = {"产品计划" } ,notes = "查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchchildplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchChildPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取CurProductPlan", tags = {"产品计划" } ,notes = "获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchcurproductplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchcurproductplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询CurProductPlan", tags = {"产品计划" } ,notes = "查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchcurproductplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchCurProductPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取数据集", tags = {"产品计划" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchcurproductplanstory")
	public ResponseEntity<List<ProductPlanDTO>> fetchcurproductplanstory(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"产品计划" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchcurproductplanstory")
	public ResponseEntity<Page<ProductPlanDTO>> searchCurProductPlanStory(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取DEFAULT", tags = {"产品计划" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchdefault")
	public ResponseEntity<List<ProductPlanDTO>> fetchdefault(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询DEFAULT", tags = {"产品计划" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchdefault")
	public ResponseEntity<Page<ProductPlanDTO>> searchDefault(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取默认查询", tags = {"产品计划" } ,notes = "获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchdefaultparent")
	public ResponseEntity<List<ProductPlanDTO>> fetchdefaultparent(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询默认查询", tags = {"产品计划" } ,notes = "查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchdefaultparent")
	public ResponseEntity<Page<ProductPlanDTO>> searchDefaultParent(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取计划（代码表）", tags = {"产品计划" } ,notes = "获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchplancodelist")
	public ResponseEntity<List<ProductPlanDTO>> fetchplancodelist(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询计划（代码表）", tags = {"产品计划" } ,notes = "查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchplancodelist")
	public ResponseEntity<Page<ProductPlanDTO>> searchPlanCodeList(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchplantasks")
	public ResponseEntity<List<ProductPlanDTO>> fetchplantasks(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchplantasks")
	public ResponseEntity<Page<ProductPlanDTO>> searchPlanTasks(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品默认查询", tags = {"产品计划" } ,notes = "获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchproductquery(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品默认查询", tags = {"产品计划" } ,notes = "查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchproductquery")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductQuery(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目立项", tags = {"产品计划" } ,notes = "获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchprojectapp")
	public ResponseEntity<List<ProductPlanDTO>> fetchprojectapp(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目立项", tags = {"产品计划" } ,notes = "查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchprojectapp")
	public ResponseEntity<Page<ProductPlanDTO>> searchProjectApp(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取项目计划列表", tags = {"产品计划" } ,notes = "获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchprojectplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询项目计划列表", tags = {"产品计划" } ,notes = "查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchprojectplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProjectPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取跟计划", tags = {"产品计划" } ,notes = "获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchrootplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchrootplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询跟计划", tags = {"产品计划" } ,notes = "查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchrootplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchRootPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "获取任务计划", tags = {"产品计划" } ,notes = "获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/fetchtaskplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchtaskplan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.quickTest('READ')")
	@ApiOperation(value = "查询任务计划", tags = {"产品计划" } ,notes = "查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/searchtaskplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchTaskPlan(@RequestBody ProductPlanSearchContext context) {
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/{action}")
    public ResponseEntity<ProductPlanDTO> dynamicCall(@PathVariable("productplan_id") Long productplan_id , @PathVariable("action") String action , @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanService.dynamicCall(productplan_id, action, productplanMapping.toDomain(productplandto));
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "根据产品建立产品计划", tags = {"产品计划" },  notes = "根据产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品批量建立产品计划", tags = {"产品计划" },  notes = "根据产品批量建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domainlist=productplanMapping.toDomain(productplandtos);
        for(ProductPlan domain:domainlist){
            domain.setProduct(product_id);
        }
        productplanService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品更新产品计划", tags = {"产品计划" },  notes = "根据产品更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品批量更新产品计划", tags = {"产品计划" },  notes = "根据产品批量更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> updateBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domainlist=productplanMapping.toDomain(productplandtos);
        for(ProductPlan domain:domainlist){
            domain.setProduct(product_id);
        }
        productplanService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品删除产品计划", tags = {"产品计划" },  notes = "根据产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @ApiOperation(value = "根据产品批量删除产品计划", tags = {"产品计划" },  notes = "根据产品批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品获取产品计划", tags = {"产品计划" },  notes = "根据产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品检查产品计划", tags = {"产品计划" },  notes = "根据产品检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(productplanMapping.toDomain(productplandto)));
    }

    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eeactiveplan")
    public ResponseEntity<ProductPlanDTO> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eeactiveplanbatch")
    public ResponseEntity<Boolean> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeActivePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eecancelplan")
    public ResponseEntity<ProductPlanDTO> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eecancelplanbatch")
    public ResponseEntity<Boolean> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeCancelPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eecloseplan")
    public ResponseEntity<ProductPlanDTO> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eecloseplanbatch")
    public ResponseEntity<Boolean> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeClosePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eefinishplan")
    public ResponseEntity<ProductPlanDTO> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eefinishplanbatch")
    public ResponseEntity<Boolean> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeFinishPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eepauseplan")
    public ResponseEntity<ProductPlanDTO> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eePausePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eepauseplanbatch")
    public ResponseEntity<Boolean> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eePausePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eerestartplan")
    public ResponseEntity<ProductPlanDTO> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eerestartplanbatch")
    public ResponseEntity<Boolean> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeRestartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eestartplan")
    public ResponseEntity<ProductPlanDTO> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/eestartplanbatch")
    public ResponseEntity<Boolean> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.eeStartPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/getoldplanname")
    public ResponseEntity<ProductPlanDTO> getOldPlanNameByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/importplantempletbatch")
    public ResponseEntity<Boolean> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.importPlanTempletBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linktask")
    public ResponseEntity<ProductPlanDTO> linkTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkTask(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/linktaskbatch")
    public ResponseEntity<Boolean> linkTaskByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.linkTaskBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/mobproductplancounter")
    public ResponseEntity<ProductPlanDTO> mobProductPlanCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "根据产品保存产品计划", tags = {"产品计划" },  notes = "根据产品保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/save")
    public ResponseEntity<ProductPlanDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存产品计划", tags = {"产品计划" },  notes = "根据产品批量保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domainlist=productplanMapping.toDomain(productplandtos);
        for(ProductPlan domain:domainlist){
             domain.setProduct(product_id);
        }
        productplanService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品计划", tags = {"产品计划" },  notes = "根据产品产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
    @ApiOperation(value = "批量处理[根据产品产品计划]", tags = {"产品计划" },  notes = "批量处理[根据产品产品计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductPlanDTO> productplandtos) {
        List<ProductPlan> domains = productplanMapping.toDomain(productplandtos);
        boolean result = productplanService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	@ApiOperation(value = "根据产品获取子计划", tags = {"产品计划" } ,notes = "根据产品获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchchildplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanChildPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询子计划", tags = {"产品计划" } ,notes = "根据产品查询子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchchildplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanChildPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询CurProductPlan", tags = {"产品计划" } ,notes = "根据产品查询CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchcurproductplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanCurProductPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品计划" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplanstory")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询数据集", tags = {"产品计划" } ,notes = "根据产品查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchcurproductplanstory")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchdefault")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"产品计划" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchdefault")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取默认查询", tags = {"产品计划" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchdefaultparent")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanDefaultParentByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询默认查询", tags = {"产品计划" } ,notes = "根据产品查询默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchdefaultparent")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanDefaultParentByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchplancodelist")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询计划（代码表）", tags = {"产品计划" } ,notes = "根据产品查询计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchplancodelist")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanPlanCodeListByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchplantasks")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanPlanTasksByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品查询项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchplantasks")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanPlanTasksByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询产品默认查询", tags = {"产品计划" } ,notes = "根据产品查询产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchproductquery")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanProductQueryByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取项目立项", tags = {"产品计划" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectapp")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询项目立项", tags = {"产品计划" } ,notes = "根据产品查询项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchprojectapp")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanProjectAppByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询项目计划列表", tags = {"产品计划" } ,notes = "根据产品查询项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchprojectplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanProjectPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取跟计划", tags = {"产品计划" } ,notes = "根据产品获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchrootplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanRootPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询跟计划", tags = {"产品计划" } ,notes = "根据产品查询跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchrootplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanRootPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取任务计划", tags = {"产品计划" } ,notes = "根据产品获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchtaskplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductPlanTaskPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询任务计划", tags = {"产品计划" } ,notes = "根据产品查询任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/searchtaskplan")
	public ResponseEntity<Page<ProductPlanDTO>> searchProductPlanTaskPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productplanMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

